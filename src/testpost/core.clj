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
(aaa 111) ;=> "111 === Hi, ===="

; 可选参数 [& args]
(defn aaa-2
  [& args]
  (println args "=== Hi, ===="))
(aaa-2 2222) ;=> "(2222) === Hi, ===="
(aaa-2) ;=> "nil === Hi, ===="
