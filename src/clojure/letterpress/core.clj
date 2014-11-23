(ns letterpress.core
  (:refer-clojure :exclude [print-method pr prn pr-str])
  (:require [clojure.core :as cc])
  (:import [java.io StringWriter Writer]
           [clojure.lang MultiFn]
           [letterpress ProxyMultiFn]))

(defonce
  ^:private ^MultiFn
  ^{:doc "The original clojure.core/print-method `MultiFn` instance."}
  cc-print-method
  cc/print-method)

(def ^:private ^:dynamic *print-method*
  "Dynamically bound variant print method."
  nil)

(defn ^:private print-method
  "Dynamically variable wrapper for core `print-method`."
  [x w]
  (if-let [f *print-method*]
    (f x w)
    (cc-print-method x w)))

(alter-var-root
 #'cc/print-method
 (constantly
  (ProxyMultiFn/wrap cc-print-method print-method)))

(defn ^:private add-printers
  "Return a new multimethod merging the existing dispatch table for multimethod
  `of` and map of additional `printers`."
  [^MultiFn of printers]
  (let [df (.-dispatchFn of), ddv (.-defaultDispatchVal of), h (.-hierarchy of)]
    (as-> (MultiFn. "print-method" df ddv h) nf
          (reduce (fn [nf [dv f]]
                    (.addMethod ^MultiFn nf dv f))
                  nf (concat (methods of) printers))
          (reduce (fn [nf [dv dvs]]
                    (reduce #(prefer-method %1 dv %2) nf dvs))
                  nf (prefers of)))))

(defn ^:private add-unreadable
  "Return a printing function which invokes `f` but replaces any unreadable
  results with the result of invoking `inreadable` instead."
  [f unreadable]
  (fn [x ^Writer w]
    (let [w' (StringWriter.), _ (f x w'), s (str w')]
      (if (.startsWith s "#<")
        (unreadable x w)
        (.write w s)))))

(defn ^:internal mk-print-method
  "Return a new variant print method as specified by `opts`."
  [opts]
  (let [{:keys [printers unreadable middleware]} opts]
    (cond-> cc-print-method
            printers (add-printers printers)
            unreadable (add-unreadable unreadable)
            middleware (middleware))))

(defmacro with-opts
  [opts & body]
  `(binding [*print-method* (mk-print-method ~opts)]
     ~@body))

(defn ^:private pr*
  [f opts x xs]
  (with-opts opts
    (if (nil? xs)
      (f x)
      (apply f x xs))))

(defn pr
  "Print the object(s) as per `clojure.core/pr`, but as modified by the options
  map `opts`, which may include the following keys:
    `:printers` -- A map of additional `print-method` dispatch-values and
      implementation functions; may override default implementations.
    `:unreadable` -- A `print-method` implementation function to invoke when
      the normal printing process produces a conventionally unreadable result
      (\"#<...>\"); this slows printing significantly.
    `:middleware` -- A function which which must accept and return a
      `print-method` top-level function."
  ([opts] nil)
  ([opts x] (pr* cc/pr opts x nil))
  ([opts x & xs] (pr* cc/pr opts x xs)))

(defn prn
  "Print the objects as per `clojure.core/prn`, but modified by the options map
  `opts` as per `letterpress.core/pr`."
  ([opts] nil)
  ([opts x] (pr* cc/prn opts x nil))
  ([opts x & xs] (pr* cc/prn opts x xs)))

(defn pr-str
  "Print the objects as per `clojure.core/pr-str`, but modified by the options
  map `opts` as per `letterpress.core/pr`."
  ([opts] "")
  ([opts x] (pr* cc/pr-str opts x nil))
  ([opts x & xs] (pr* cc/pr-str opts x xs)))
