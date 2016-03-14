;;clojure cons vs conj:=> 看class的变化,类型变化, OO是底层操作
;; cons是 前小的list 合并到 后大的list 里面=>  "aa" + [1, 2, 3] ======> 一般用于加Code前缀代码fn, 如do
(conj '(1 2 3) 4 5 6) ;; => (6 5 4 1 2 3) ;; 前大 merge 后面的小的list, 一般用于加后缀的list数据
;; (fn list_data) 左fn右list, 所以一般cons加fn的 使用率 高些
;;(cons 4 5 6 '(1 2 3)) ;; erro
(class (conj '(1 2 3) 4)) ;; => clojure.lang.PersistentList
(class (cons 4 '(1 2 3))) ;; => clojure.lang.Cons
(list? (conj () 1)) ;=> true
(list? (cons 1 ())) ; => false
;;; `,~,~@ 较好的统一了cons和conj的使用 11111111111
`(1 2 ~@(list 3 4)) ;;=> (1 2 3 4)
`(~@(list 3 4) 1 2) ;;=> (3 4 1 2)
;;;;;;;; 前缀first => cons ;  后缀rest => conj http://paulsanwald.com/blog/231.html
(def aa (list 1 2 3 4))
(def bb (list 5 6 7 8))
;;(println aa)
(println (conj (rest aa) (first aa))) ; => (1 2 3 4)
;;(println (cons (rest aa) (first aa))) ;;=> erro  java.lang.IllegalArgumentException: Don't know how to create ISeq from: java.lang.Long
(println (cons (first aa) (rest aa))) ;;=> (1 2 3 4)


;; concat vs. conj vs. cons vs. list vs. list* https://gist.github.com/noahlz/5510191

(concat '(1 2 3) '(4 5 6)) ;=> (1 2 3 4 5 6)

(conj '(1 2 3) '(4 5 6));=>((4 5 6) 1 2 3)

(cons '(1 2 3) '(4 5 6)) ;=>((1 2 3) 4 5 6)

(list '(1 2 3) '(4 5 6)) ;=> ((1 2 3) (4 5 6))

(list* '(1 2 3) '(4 5 6)) ;=> ((1 2 3) 4 5 6)

(concat [1 2 3] [4 5 6]) ;=> (1 2 3 4 5 6)

(conj [1 2 3] [4 5 6]) ;=> [1 2 3 [4 5 6]]

(cons [1 2 3] [4 5 6]) ;=> ([1 2 3] 4 5 6)

(list [1 2 3] [4 5 6]) ;=> ([1 2 3] [4 5 6])

(list* [1 2 3] [4 5 6]) ;=> ([1 2 3] 4 5 6)


(defn when-p
  [test & body]
  ;;(println (list 'if test (cons 'do body)))
  ;;(println (list 'if test ('do body))) ;; => (if 1 nil)
  ;;(println (list 'if test (cons 100 body))) ;; => (if 1 (100 2 3 4))
  ;;(println (list 'if test (list body))) ;;=> (if 1 ((2 3 4)))
  ;;(println (list 'if test body )) ;; => (if 1 (2 3 4))
  ;;(println `(if ~test (do ~@body))) ;; => (if 1 (do 2 3 4))
  )
;; <==>
(macroexpand '(when 1 2 3 4)) ;; only no eval it :) ;;=> (if 1 (do 2 3 4))

(when-p 1 2 3 4) ;;=> (if 1 (do 2 3 4))


