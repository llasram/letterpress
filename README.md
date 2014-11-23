# letterpress

Locally-configurable Clojure data printers.

## Installation

Letterpress is available on Clojars.  Add this `:dependency` to your Leiningen
`project.clj`:

```clj
[org.platypope/letterpress "0.1.0"]
```

## Purpose

Since tagged literals and data-readers were introduced in Clojure 1.4, the
mechanisms for establishing data-readers have allowed for code-local
configuration of data-reader functions.  The original 1.4 `*data-readers*` var
was and is dynamic, allowing locally-scoped specification of tagged literal
reader functions.  The functions in the Clojure 1.5 `clojure.edn` namespace make
this local scope even more specific by using only tagged literal readers
explicitly specified via (optional) function arguments.

The print side unfortunately has remained entirely global.  Implementations of
the `clojure.core/print-method` multimethod entirely determine how objects
print, with no ability to provide local overrides or variant printing behavior.

The letterpress library repairs this deficiency, allowing hand-crafted,
hyper-local configuration for printing of Clojure data.

## Usage

Simply require the `letterpress.core` namespace, then call that namespace’s
`pr`, `prn`, and/or `pr-str` functions.  These functions are just their
`clojure.core` namesakes, except that they require an an initial map of
configuration options.  This map may contain the following keys:

- `:printers` – A map of additional `print-method` dispatch-values and
  implementation functions; may override default implementations.
- `:unreadable` – A `print-method` implementation function to invoke when the
  normal printing process produces a conventionally unreadable result
  ("#<...>"); this slows printing significantly.
- `:middleware` – A function which which must accept and return a `print-method`
  top-level function.

For example, here is how Java `Class` objects pass through EDN by default:

```clj
(->> String (pr-str) (edn/read-string))
;;=> java.lang.String
(class *1)
;;=> clojure.lang.Symbol
```

And here is how letterpress allows `Class`es to correctly round-trip through
local print and read configuration:

```clj
(require '[letterpress.core :as lp])

(defn class-print [^Class x ^Writer w]
  (doto w (.write "#java.lang/class ") (.write (.getName x))))
(defn class-read [x] (Class/forName (name x)))

(->> String
     (lp/pr-str {:printers {Class class-print}})
     (edn/read-string {:readers {'java.lang/class class-read}}))
;;=> java.lang.String
(class *1)
;;=> java.lang.Class
```

## Warnings

The capabilities letterpress provides are implemented in part by replacing
`clojure.core/print-method` with a different object.  All effort is made to make
this replacement as safe and compatible as possible, but monkey-patching remains
inherently a kludgy, dangerous, and shameful mechanism.  We hope that one day
the capabilities of letterpress will be part of Clojure itself instead.

## License

Copyright © 2014 Marshall Bockrath-Vandegrift

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
