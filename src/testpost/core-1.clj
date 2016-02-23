(ns testpost.core
  (:gen-class))
(defn -main
  [& args]
  (println "Hello, World!"))

(defmacro p
  [& body]
  (pprint (macroexpand-all (do body))))
;;; for print macro , test macro foreach-1 is ok , it can print stuct code, and you can (do body) or (p body), so cool ! :) , (p (cond a b c d) ) ;=> (if a b (if c d nil))

(def a (atom 10))
(defmacro while-t
  [test & body]
  (list 'loop []
        (concat (list 'when test) body)
        '(recur)))

;(do (while-t (pos? @a) (do (println @a) (swap! a dec))))
;(do (while   (pos? @a) (do (println @a) (swap! a dec))))


