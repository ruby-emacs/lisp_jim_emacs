(ns testpost.core
  (:gen-class))
(defn -main
  [& args]
  (println "Hello, World!"))
;; 11111 (let (try (do) (finally (do))))
(defmacro withresource [binding close-fn & body]
  `(let ~binding
     (try
       (do ~@body)
       (finally
         (~close-fn ~(binding 0)) ;=> java.lang.String cannot be cast to clojure.lang.IFn
         )
       )  
     )
  )
;; (withresource "aaaa" "aaaa") ;=> java.lang.String cannot be cast to clojure.lang.IFn
;; (joc-www) ; Unable to resolve symbol: joc-www in this context,

(comment "
(let [stream (joc-www)]
  (withresource [page stream]
                #(.close %)
                (.readLine page)
                )
  )
")

; (println (+ a b c)) ; Unable to resolve symbol: a in this context
(let [a 1 b 2 c 3] ;=> let is context, small scope 
  (println (+ a b c))
  ) ;=> 6
;
;(#(.close %) "aaa") ;=> No matching field found: close for class java.lang.String

; (.readLine "aaaai") ;=> No matching field found: readLine for class java.lang.String

