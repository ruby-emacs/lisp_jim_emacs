(ns testpost.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

; 参数 []
(defn aaa
  [args]
  (println args "=== Hi, ===="))
;(aaa 111) ;=> "111 === Hi, ===="

; 可选参数 [& args]
(defn aaa-2
  [& args]
  (println args "=== Hi, ===="))
;(aaa-2 2222) ;=> "(2222) === Hi, ===="
;(aaa-2) ;=> "nil === Hi, ===="
(defn aaa-3
  [x & args]
  (println x  "=== Hi, ====" args))
;(aaa-3 5555);=> "5555 === Hi, ==== nil"

;;; study : https://clojuredocs.org/clojure.walk/macroexpand-all
(use 'clojure.walk)
(macroexpand-all '(-> c (+ 3) (* 2)))
; => (* (+ c 3) 2)

(macroexpand-all '(map #(str "Hello " % "!" ) ["Ford" "Arthur" "Tricia"]))
; => (map (fn* [p1__1247#] (str "Hello " p1__1247# "!")) ["Ford" "Arthur" "Tricia"])

(map (fn [a] (str "Hi " a "!")) ["aaaa" "bbbb" "cccc"])
; => ("Hi aaaa!" "Hi bbbb!" "Hi cccc!")

; [x & rest] like ruby method(x, rest*)
(defn concat-rest
  [x & rest]
  (println x  "=== Hi, ====" rest)
  (apply str (butlast rest))
  )
;(concat-rest 0 1 2 3) ;=> "0 === Hi, ==== (1 2 3)" ; println
;=> "12"
(butlast '(1 2 3));=> (1 2) , if no quote , is  erro 

;(apply println "aaaaaaaaaaaaaaa");=> "a a a a a a a a a a a a a a a" : one by one get arguments 

(macroexpand-all '(apply println "aaaaaaaaaaaaaaa")); => (apply println "aaaaaaaaaaaaaaa")

;(doc apply)
;([f args] [f x args] [f x y args] [f x y z args] [f a b c d & args])
;  Applies fn f to the argument list formed by prepending intervening arguments to args.
println '(111 222 333 555);=> (111 222 333 555)
; (apply println '(111 222 333 555));=> 111 222 333 555 ; 

;; Haskell 没有宏, 只有高阶函数(源自lambda), 如果没有宏, 只能用lambda来演算表达foreach-1
(defmacro foreach-1 [[sym coll] & body]
  `(loop [coll# ~coll]
     (when-let [[~sym & xs#] (seq coll#)]
       ~@body
       (recur xs#))))

(foreach-1 [x [1 2 3 4 5]]
           ;(println x)
           ) ;=> 1  2 3 4 5

(macroexpand-all '(foreach-1 [x [1 2 3]] (println x) )) ; ==> 没有数据模板, 而宏就是数据模板
(comment "
(loop* [coll__1710__auto__ [1 2 3]]
       (let* [temp__4425__auto__ (clojure.core/seq coll__1710__auto__)]
             (if temp__4425__auto__
               (do (let* [vec__1727 temp__4425__auto__ x
                          (clojure.core/nth vec__1727 0 nil)
                          xs__1711__auto__ (clojure.core/nthnext vec__1727 1)]
                         (println x)
                         (recur xs__1711__auto__)))))) ")

;(pprint (macroexpand-all '(foreach-1 [x [1 2 3]] (println x) )) )
;=> 
(comment "
(loop*
 [coll__1786__auto__ [1 2 3]]
 (let*
  [temp__4425__auto__ (clojure.core/seq coll__1786__auto__)]
  (if
   temp__4425__auto__
   (do
    (let*
     [vec__1806
      temp__4425__auto__
      x
      (clojure.core/nth vec__1806 0 nil)
      xs__1787__auto__
      (clojure.core/nthnext vec__1806 1)]
     (println x)
     (recur xs__1787__auto__))))))
")

"------------"

(macroexpand-all '#(println 1111));=> (fn* [] (println 1111))
#(println 1111) ; => #object[testpost.core$eval5315$fn__5316 0x2d726d05 "testpost.core$eval5315$fn__5316@2d726d05"]


;( #(println 1111) ) ;=> 1111

;(map #(println "++++++++") ["aaaa" "bbbb" "cccc"]) ;=> Wrong number of args

(defmacro myprint [x]
  `(println ~x) ; ~x 是反引号的去除, 替换为x变量
  )
;(myprint "222222") ;=> 222222

(def a (atom 10))
;(a 111) ;=> clojure.lang.Atom cannot be cast to clojure.lang.IFn,
(println @a) ;=> 10

