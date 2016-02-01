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


;;; study : https://clojuredocs.org/clojure.walk/macroexpand-all
(use 'clojure.walk)
(macroexpand-all '(-> c (+ 3) (* 2)))
; => (* (+ c 3) 2)

(macroexpand-all '(map #(str "Hello " % "!" ) ["Ford" "Arthur" "Tricia"]))
; => (map (fn* [p1__1247#] (str "Hello " p1__1247# "!")) ["Ford" "Arthur" "Tricia"])

(map (fn [a] (str "Hi " a "!")) ["aaaa" "bbbb" "cccc"])
; => ("Hi aaaa!" "Hi bbbb!" "Hi cccc!")

