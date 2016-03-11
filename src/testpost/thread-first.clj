(defmacro -m>
  {:added "1.0"}
  [x & forms]
  (loop [x x, forms forms]
    (if forms
      
      (let [
            form ;=>
            (first forms)
            threaded ;=> 
            (if (seq? form)
              (with-meta `(~(first form) ~x ~@(next form)) (meta form))
              (list form x)
              )
            ]
        (println (str threaded "====" forms "----"))
        (println (str x "..." form ":..." (list form x) "..."))
        (println (str "***" `(~(first form) ~x ~@(next form)) "***"))
        (recur threaded (next forms))
        
        )

      
      x)
    ))

(-m> 8 (+ 100) (* 5))
; (+ 8 100)====((+ 100) (* 5))----
; 8...(+ 100):...((+ 100) 8)...
; ***(+ 8 100)***
; (* (+ 8 100) 5)====((* 5))----
; (+ 8 100)...(* 5):...((* 5) (+ 8 100))...
; ***(* (+ 8 100) 5)***

; (loop [bindings*] exprs*)
(loop [iter 1 acc  0]
  ;(println (str iter "====" acc "----"))
  (if (> iter 10)
    (println acc)
    (recur
     (inc iter) ; +1  => loop iter 
     (+ acc iter) ; sum = sum + i => loop acc 
     ))) ;=> 55

(next '(:alpha :bravo :charlie)); => (:bravo :charlie)
(first '(:alpha :bravo :charlie));=> :alpha
(list (+ 8) 1) ;=> (8 1)

; 判断是否求值
(seq? 1) ;false
(seq? '()) ;true
(seq? '(+ 1)); true
(seq? '(+ 1 2)); true
(seq? nil) ;false

(seq '(1))  ;;=> (1)
(seq [1 2]) ;;=> (1 2) , (class [1 2]) => clojure.lang.PersistentVector
(seq "abc") ;;=> (\a \b \c)

;; Corner cases
(seq nil)   ;;=> nil
(seq '())   ;;=> nil
(seq "")    ;;=> nil

(seq ["1" [1] '(1) {:1 1} #{1}]); => ("1" [1] (1) {:1 1} #{1})
(seq {:key1 "value1" :key2 "value2"}); => ([:key1 "value1"] [:key2 "value2"])



(with-meta [1 2 3] {:my "meta"}); =>  [1 2 3] , with-meta设置meta
(meta (with-meta [1 2 3] {:my "meta"})); => {:my "meta"}, meta提取meta数据

; 去除a的s表达式,生成新的s表达式
(let [a '(1 2)] `(1 ~@a) ) ; => (1 1 2)
