(defn map-m
  {:added "1.0" :static true}
  
  ([f]
   (fn [rf]
     (fn
       ([] (rf))
       ([result] (rf result))
       ([result input]
        (rf result (f input)))
       
       ([result input & inputs]
        (rf result (apply f input inputs)))
       
       )
     )
   )
  ;; =======
  ([f coll] ; 当参数只有两个时
   (lazy-seq
    (when-let [s (seq coll)]
      (if (chunked-seq? s)

        
        (let [c (chunk-first s)
              size (int (count c))
              b (chunk-buffer size)]
          
          (dotimes [i size]
            (chunk-append b (f (.nth c i))))
          
          (chunk-cons (chunk b) (map-m f (chunk-rest s)))

          )
        

        (cons (f (first s)) (map-m f (rest s)))

        
        )
      )
    )

   )
  ;; =======
  
  ([f c1 c2]
   (lazy-seq
    (let [s1 (seq c1) s2 (seq c2)]
      (when (and s1 s2)
        
        (cons
         (f (first s1) (first s2))
         (map-m f (rest s1) (rest s2))
         )

        ))))
  
  ;; =======
  
  ([f c1 c2 c3]
   (lazy-seq
    (let [s1 (seq c1) s2 (seq c2) s3 (seq c3)]
      (when (and  s1 s2 s3)
        
        (cons
         (f (first s1) (first s2) (first s3))
         (map-m f (rest s1) (rest s2) (rest s3))
         )

        ))))

  ;; =======
  
  ([f c1 c2 c3 & colls]
   
   (let [
         step (fn step [cs]
                (lazy-seq
                 (let [ss (map-m seq cs)]
                   (when (every? identity ss)
                     
                     (cons (map-m first ss) (step (map-m rest ss)))

                     )))

                )]
     
     (map-m #(apply f %) (step (conj colls c3 c2 c1)))

     )))

;; ---------------------------
(map-m #(str "Hello " % "!" ) ["Ford" "Arthur" "Tricia"]) ; => ("Hello Ford!" "Hello Arthur!" "Hello Tricia!")

(class []) ; clojure.lang.PersistentVector
;; apply 逐个接收 Vector[]参数
(apply str ["str1" "str2" "str3"])  ;;=> "str1str2str3"
(str "str1" "str2" "str3")          ;;=> "str1str2str3"

(take 3 '(2 4 6 8 10 12)); => (2 4 6)
;;,以下定义了一个lazy-seq正数。请注意, 　;,lazy-seq允许我们做一个递归调用,因为在一个安全的方式 　　;;调用不会立即发生,而是创建了一个闭包。
(defn positive-numbers
  ([] (positive-numbers 1))
  ([n]
   (lazy-seq
    ;;(println n) ; 1 , 2, 3
    (cons n
          (positive-numbers (inc n))
          )
    )
   ))
;;(take 5 (无限list)) 的 take 闭包
(take 5 (positive-numbers)) ;=> (1 2 3 4 5)

(defn fib [a b]
  ;;(lazy-seq ; 和take10闭包结合, 当去除lazy-seq时,eval6365.invoke错误
  ;;(println (str "----" a "----" b))
  (if (> 56 a)
    (cons  a  (fib b (+ b a))  ) ; 求和 1+2+3+4...10
    (println "stop"))
  ;;)
  )
(take 10 (fib 1 2)) ; => => (1 2 3 5 8 13 21 34 55 89) 
;=> => (1 2 3 5 8 13 21 34 55) 当使用 (if (> 56 a) ..) 时
