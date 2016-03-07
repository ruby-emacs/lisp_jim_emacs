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
  ;;(spy-env) ;=> {a 111, b 222, c 333}
  )

;; => [keys vals]
(zipmap '(a b c) [1 2 3] ) ;=> {a 1, b 2, c 3}

(let [aa (seq [5 6 7 8])]
  ;(println aa) ; 
  ) ; (5 6 7 8)

(defn drop-one
  [coll]
  (when-let [s (seq coll)] 
    ;(println s) ; (1 2 3) 
    (rest s)))
(drop-one [1 2 3]) ; (2 3)

(when-let [s 1]
  ;(println 111)
  ) ;=> 111

(list 'a 'b 'c 'd 'e 'f 'g); => (a b c d e f g)
(let [m {:1 1 :2 2 :3 3 :4 4}] (map list (keys m) (vals m)))
;((:1 1) (:2 2) (:3 3) (:4 4))

(let [x 1 y 2]
  `(~x ~y)) ; => (1 2)

(list* 1 2 [3 4]); => (1 2 3 4)

(list* () [1 2]);=> (() 1 2)

(list?
 (into () (list* 1 '(2 3))) ;=> (3 2 1)
); true

; (cons x seq) ; Returns a new seq where x is the first element and seq is the rest.
(cons [1 2] [4 5 6]); => ([1 2] 4 5 6)
(cons 1 '(2 3 4 5 6)) ; => (1 2 3 4 5 6)

(juxt identity type seq? list?) ; => #object[clojure.core$juxt$fn__4514 0x781715d1 "clojure.core$juxt$fn__4514@781715d1"]
(map (juxt identity type seq? list?)
     [(cons 1 nil)
      (cons 1 '())])
;=> (
; [(1) clojure.lang.PersistentList true true]
; [(1) clojure.lang.Cons true false]
; )


(conj [1 2 3] 4); => [1 2 3 4]
(conj [[1 2] [3 4]] [5 6]) ; => [[1 2] [3 4] [5 6]]
(conj {:firstname "John" :lastname "Doe"} {:age 25 :nationality "Chinese"})
;=> {:firstname "John", :lastname "Doe", :age 25, :nationality "Chinese"}
(conj #{1 3 4} 2); => #{1 4 3 2}

;;; (conj 1 3 4 2);   java.lang.Long cannot be cast to clojure.lang.IPersistentCollection

; 
(peek '(:a :b :c)) ;=> :a

(def large-vec (vec (range 0 10000)))
;(last large-vec) ;=> 9999
(time (last large-vec)) ; "Elapsed time: 1.027048 msecs"
(time (peek large-vec)); "Elapsed time: 0.050122 msecs"

(first '(:alpha :bravo :charlie)) ;=> :alpha

(rest ["a" "b" "c" "d" "e"])  ;;=> ("b" "c" "d" "e")

;;;; For a list or queue, same as first, for a vector, same as, but much
;;;; more efficient than, last. If the collection is empty, returns nil.
;(cons 1 '()); => (1)
;(peek (cons 1 '()));  Evaluation aborted. , erro
(conj '() 1) ; => (1)
(peek (conj '() 1)) ; 1 ;;;;;;;;;;   11111111111;;;;

