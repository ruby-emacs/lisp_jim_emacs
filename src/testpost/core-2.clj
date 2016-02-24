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

(defn fib-no-mem [n]
  (condp = n
    0 1
    1 1
    (+ (fib-no-mem (dec n)) (fib-no-mem (- n 2)))))

; 记忆化搜索-关于memoize结合递归 : http://clojure-china.org/t/memoize/51
(def m-fib
  (memoize (fn [n]
             (condp = n
               0 1
               1 1
               (+ (m-fib (dec n)) (m-fib (- n 2)))))))

;(time (m-fib 50))
;=> 1346269
; "Elapsed time: 167.617558 msecs"  ... 1
; "Elapsed time: 1.026374 msecs"  ... 2 
;  50 =>  1.320158 msecs 

;;;;;;;;;;;;;;; https://coldnew.github.io/pratice/4clojure/
(= true true) ;=> true
(= "111" "111") ;=> true

(.toUpperCase "hello world") ;=> "HELLO WORLD"

(= (list :a :b :c) '(:a :b :c)) ;=> true
; connect & join => conj
(conj '(2 3 4) 1) ;=> (1 2 3 4)

(vec '(:a :b :c)) ;=> [:a :b :c]

(vector :a :b :c) ;=> [:a :b :c]

(= "aaa" "aaa" "aaa" "aaa") ;=> true

(set '(:a :a :b :c :c :c :c :d :d)) ;=> #{:c :b :d :a}

(clojure.set/union #{:a :b :c} #{:b :c :d}) ;=> #{:c :b :d :a}
; conj like ruby '<<'
(conj #{1 4 3} 2) ;=> #{1 4 3 2}

(hash-map :a 10, :b 20, :c 30) ;=> {:c 30, :b 20, :a 10}

((hash-map :a 10, :b 20, :c 30) :b) ;=> 20

(conj {:a 1} [:b 3] [:c 3]) ;=> {:a 1, :b 3, :c 3}

(first '(3 2 1)) ;=> 1
(second [2 3 4]) ;=> 3
(last (list 1 2 3)) ;=> 3
;;;;;; conj, rest is no first sets
(rest [10 20 30 40]) ;=> (20 30 40)

((fn add-five [x] (+ x 5)) 3) ;=> 8
;;;; '#( % )' is fn, but no name
(#(+ % 5) 3) ;=> 8
((partial + 5 5) 3 2 1) ;=> 16 ; like inject(:+)

(#(str "Hello, " % "!") "Dave") ;=> "Hello, Dave!"

(map #(+ % 5) '(1 2 3)) ;=> (6 7 8)
; like ruby select
(filter #(> % 5) '(3 4 5 6 7)) ;=> (6 7)

;;;;;; 

