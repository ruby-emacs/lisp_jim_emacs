(defmacro loop-m
  [bindings & body]
  ;;(println (str "===" bindings "====" body "===")) ;; ===[x 10]====((when (> x 1) (println x) (recur (- x 2))))===
  (let [db (destructure bindings)] ;; => db: [x 10] vector
    (if (= db bindings)
      `(loop* ~bindings ~@body)
      (let [vs (take-nth 2 (drop 1 bindings))
            bs (take-nth 2 bindings)
            gs (map (fn [b] (if (symbol? b) b (gensym))) bs)
            bfs (reduce (fn [ret [b v g]]
                          (if (symbol? b)
                            (conj ret g v)
                            (conj ret g v b g)))
                        [] (map vector bs vs gs))]
        `(let ~bfs
           (loop* ~(vec (interleave gs gs))
                  (let ~(vec (interleave bs gs))
                    ~@body)))))))

(destructure '[[a b] ["a" "b"]])
;; => [vec__6148 ["a" "b"] a (clojure.core/nth vec__6148 0 nil) b (clojure.core/nth vec__6148 1 nil)]


(loop* ;; when (= bindings (destructure bindings) ) loop-m == loop* 
 [x 10 y 20] ;; <=> (loop* [x 10] (when (> x 1) (println x) (recur (- x 2))))
 (when (> x 1)
   (println (str x "---" y))
   (recur (- x 2) (- y 4))))


;;;;;;;; Use form S-express look Use Lambda  ;;;
(let [a '(+ 2)] `(~@a 1)) ;;=> (+ 2 1)
(let [a '((+ 2))] `(~@a 1)) ;; => ((+ 2) 1)
(let [a '((+ 2))]  `(~@(first a) 1)  ) ;; => (+ 2 1)
(let [a '(((((+ 2)))))]
  `(~@(-> a (first) (first) (first) (first) ) 1)
  ) ;; => (+ 2 1)

(defmacro aaa [x & forms]
  (println (str x "===" forms "==="))
  ;;`(~@(first forms) 222) ;; good 222 * 100 
  ;;'fn [x] (cons (first forms) x) )
  `(fn [x#] (~@(first forms) x#))
  ) 
(defmacro bbb [x & forms] `(aaa ~x ~@forms) )

;;(bbb (list 1 2) (fn [x] (* x 100))) ;;=> (list 1 2)===((fn [x] (* x 100)))===
((bbb (list 1 2) (* 100)) 222) ;;=> => 22200

