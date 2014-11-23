(defproject org.platypope/letterpress "0.1.1-SNAPSHOT"
  :description "Locally-configurable Clojure data printers."
  :url "https://github.com/llasram/letterpress"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :global-vars {*warn-on-reflection* true}
  :source-paths ["src/clojure"]
  :java-source-paths ["src/java"]
  :dependencies [[org.clojure/clojure "1.6.0"]])
