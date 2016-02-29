(ns testpost.core
  (:gen-class))
(defn -main
  [& args]
  (println "Hello, World!"))
;;;;;;;;;;;;;;;;;;;;;;
(defmacro spy-env []
  (let [ks (keys &env)]
    ;(println (list ks)) ;=> (a b c)
    ;(println @ks) ;=> clojure.lang.APersistentMap$KeySeq cannot be cast to java.util.concurrent.Future
    ;(println [ks]) ;=>  [(a b c)]
    `(println (zipmap '~ks [~@ks])))
  )
(let [a 111 b 222 c 333]
  (+ a b c)
  ;(macroexpand-1 '(spy-env)) ;=> (clojure.core/println (clojure.core/zipmap (quote nil) []))
  (spy-env) ;=> {a 111, b 222, c 333}
  )

;; => [keys vals]
(zipmap '(a b c) [1 2 3] ) ;=> {a 1, b 2, c 3}


