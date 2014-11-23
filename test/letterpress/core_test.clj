(ns letterpress.core-test
  (:require [clojure.test :refer :all]
            [clojure.edn :as edn]
            [letterpress.core :as lp])
  (:import [java.io Writer]
           [clojure.lang Symbol Var]))

(defn class-print [^Class x ^Writer w]
  (doto w (.write "#java.lang/class ") (.write (.getName x))))
(defn class-read [x] (Class/forName (name x)))

(defn var-print [^Var x ^Writer w]
  (.write w (str "#clojure.lang/var " (.-ns x) "/" (.-sym x))))
(defn var-read [x]
  (let [ns (symbol (namespace x)), n (symbol (name x))]
    (require ns)
    (Var/intern ns n)))

(deftest test-printers
  (is (= Symbol (->> String pr-str edn/read-string class)))
  (is (= Symbol (->> String (lp/pr-str {}) edn/read-string class)))
  (is (= Class (->> String
                    (lp/pr-str {:printers {Class class-print}})
                    (edn/read-string
                     {:readers {'java.lang/class class-read}})
                    class)))
  (is (thrown? RuntimeException (->> #'str pr-str edn/read-string)))
  (is (thrown? RuntimeException (->> #'str (lp/pr-str {}) edn/read-string)))
  (is (= #'str (->> #'str
                    (lp/pr-str {:printers {Var var-print}})
                    (edn/read-string
                     {:readers {'clojure.lang/var var-read}})))))

(defn unreadable-print [x w]
  (print-method {:class (class x), :str (str x)} w))

(deftest test-unreadable
  (let [unreadable? #(.startsWith ^String % "#<")]
    (is (unreadable? (pr-str (Object.))))
    (is (unreadable? (lp/pr-str {} (Object.))))
    (is (= 'java.lang.Object
           (->> (Object.)
                (lp/pr-str {:unreadable unreadable-print})
                (edn/read-string)
                (:class))))
    (is (= Object
           (->> (Object.)
                (lp/pr-str {:unreadable unreadable-print
                            :printers {Class class-print}})
                (edn/read-string
                 {:readers {'java.lang/class class-read}})
                (:class))))))

(defn vector-middleware
  [f]
  (fn [x ^Writer w]
    (.write w "[")
    (f x w)
    (.write w "]")))

(deftest test-middleware
  (is (= 0 (->> 0 pr-str edn/read-string)))
  (is (= 0 (->> 0 (lp/pr-str {}) edn/read-string)))
  (is (= [0] (->> 0
                  (lp/pr-str {:middleware vector-middleware})
                  edn/read-string))))
