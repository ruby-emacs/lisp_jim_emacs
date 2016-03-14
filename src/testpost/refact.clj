(defn when-p
  [test & body]
  (println (list 'if test (cons 'do body)))
  )
;; <==>
(macroexpand '(when 1 2 3 4)) ;; only no eval it :) ;;=> (if 1 (do 2 3 4))

(when-p 1 2 3 4) ;;=> (if 1 (do 2 3 4))
