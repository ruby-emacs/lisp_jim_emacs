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

(str/split ruby-lambda #"->")
;=> ["" " x {\n  " " y {\n  " " z {\n  x + y + z\n  }\n  }\n  }"]

