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
(concat-rest 0 1 2 3) ;=> "0 === Hi, ==== (1 2 3)" ; println
;=> "12"
(butlast '(1 2 3));=> (1 2) , if no quote , is  erro 

(apply println "aaaaaaaaaaaaaaa");=> "a a a a a a a a a a a a a a a" : one by one get arguments 


