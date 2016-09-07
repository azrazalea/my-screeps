(require
  '[cljs.build.api :as b]
  '[cljs.repl :as repl]
  '[cljs.repl.browser :as browser])

(b/build "src"
  {:main 'my-screeps.core
   :output-to "dist/main.js"
   :output-dir "dist"
   :verbose true})

(repl/repl (browser/repl-env)
  :output-dir "dist")
