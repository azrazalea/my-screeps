(defproject my-screeps "0.1.0-SNAPSHOT"
  :description "Personal Screeps!"
  :url "https://gitlab.com/azrazalea/my-screeps"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.229"]
                 [cljs-screeps "0.1.0-SNAPSHOT"]]
  :jvm-opts ^:replace ["-Xmx1g" "-server"]
  :plugins [[lein-npm "0.6.1"]]
  :npm {:dependencies [[source-map-support "0.4.0"]]}
  :source-paths ["src" "target/classes"]
  :clean-targets ["dist" "release"]
  :target-path "target")
