(destructure (list 1)) ;;=> (1)
;;(destructure (list 1 2)) ;;=> erro
(destructure []) ;;=> []
(defn loop-p
  [bindings & body]
  (let [db (destructure bindings)]
    (if (= db bindings)
      ;;(println (str db "===" bindings"===")) ;;`(loop* ~bindings ~@body)
      (let [vs (take-nth 2 (drop 1 bindings))
            bs (take-nth 2 bindings)
            gs (map (fn [b] (if (symbol? b) b (gensym))) bs)
            bfs (reduce (fn [ret [b v g]]
                          (if (symbol? b)
                            (conj ret g v)
                            (conj ret g v b g)))
                        [] (map vector bs vs gs))]
        (println
         `(let ~bfs
            (loop* ~(vec (interleave gs gs))
                   (let ~(vec (interleave bs gs))
                     ~@body))) )
        ))))

(loop-p [] 111 ) ;; => (clojure.core/let [] (loop* [] (clojure.core/let [] 111)))


