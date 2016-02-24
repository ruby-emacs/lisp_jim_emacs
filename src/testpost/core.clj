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
  ;(println (+ a b c))
  ) ;=> 6
;
;(#(.close %) "aaa") ;=> No matching field found: close for class java.lang.String

; (.readLine "aaaai") ;=> No matching field found: readLine for class java.lang.String

; binding => [page stream] ; [a 1 b 2 c 3]
; close-fn => #(.close %)
; body => (.readLine page)

;;;;;;;;;;;;;;;;;;;
(defmacro catch-exception-string
  "If an exception occurs while excuting body, print
  it to a string and return the string. Also log the error
  with the s string."
  [s & body]
  `(try ~@body
        (catch Exception e#
          (println e#)
          ;(error e# ~s)
          ;(with-out-str
            ;(print-stack-trace e#)
           ;e#)
          )))

;(catch-exception-string "aaa" (+ "1" 1))
;=> #error { :cause java.lang.String cannot be cast to java.lang.Number

; 记忆化搜索-关于memoize结合递归 : http://clojure-china.org/t/memoize/51
(def m-fib
  (memoize (fn [n]
             (condp = n
               0 1
               1 1
               (+ (m-fib (dec n)) (m-fib (- n 2)))))))

(time (m-fib 50))
;=> 1346269
; "Elapsed time: 167.617558 msecs"  ... 1
; "Elapsed time: 1.026374 msecs"  ... 2 
;  50 =>  1.320158 msecs 
