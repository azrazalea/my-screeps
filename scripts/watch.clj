(require '[cljs.build.api :as b])

(b/watch "src"
  {:main 'my-screeps.core
   :output-to "dist/main.js"
   :output-dir "dist"})
