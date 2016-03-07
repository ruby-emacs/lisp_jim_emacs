(ns testpost.core
  (:gen-class))
(defn -main
  [& args]
  (println "Hello, World!"))
(require '[clojure.core.match :refer [match]])
; 打印101内所有的 FizzBuzz
(doseq [n (range 1 101)]
  ;(println
   (match [(mod n 3) (mod n 5)]
          [0 0] "FizzBuzz"
          [0 _] "Fizz"
          [_ 0] "Buzz"
          :else n));)
(require '[clojure.string :as str])
(str/split "q1w2e3r4t5y6u7i8o9p0" #"\d+" 5) ;=> ["q" "w" "e" "r" "t5y6u7i8o9p0"]

;;;;;;;;;;;;;;;;;;;;;;

(def ruby-lambda
  "-> x {
  -> y {
  -> z {
  x + y + z
  }
  }
  }")
(re-seq #"-> [a-z] \{" ruby-lambda) ; => ("-> x {" "-> y {" "-> z {") 匹配的get

(str/split ruby-lambda #"-> [a-z] \{") ; => ["" "\n  " "\n  " "\n  x + y + z\n  }\n  }\n  }"] 匹配的remove

(
 (
  (
   (fn [x]
     (fn [y]
       (fn [z]
         (+ x y z)
         )
       )
     )
   1)
  2)
 3) ; => 6

;(defn calc [exp]
;  (fn [exp]
;    
;    (match exp                                ; 匹配表达式的两种情况
;           [(? number? x) x]                       ; 是数字，直接返回
;           [`(,op ,e1 ,e2)                         ; 匹配并且提取出操作符 op 和两个操作数 e1, e2
;            (let ([v1 (calc e1)]                   ; 递归调用 calc 自己，得到 e1 的值
;                  [v2 (calc e2)])                  ; 递归调用 calc 自己，得到 e2 的值
;              (match op                            ; 分支：处理操作符 op 的 4 种情况
;                     ['+ (+ v1 v2)]                     ; 如果是加号，输出结果为 (+ v1 v2)
;                     ['- (- v1 v2)]                     ; 如果是减号，乘号，除号，相似的处理
;                     ['* (* v1 v2)]
;                     ['/ (/ v1 v2)]))]
;           )
;    
;    )
;  )
;
;(defn calca [] )

(defn mymax
  ([x] x)
  ([x y] (if (> x y) x y))
  ([x y & more]
   (reduce mymax (mymax x y) more)))

(meta #'mymax)

