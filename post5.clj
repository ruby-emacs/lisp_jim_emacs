;;   每个文件前面要加引号
;;   :1,$s/:=\~/:=regex/g
;;   :1,$s/:\[\]/:array/g
;;   :1,$s/:@/\:at\//

(ns test
  (:require [clojure.string :as str])
  (:use clojure.walk)
  (:use clojure.java.shell))
;;(println (sh "pwd")) ;;=> {:exit 0, :out /Users/emacs/Desktop, :err }
(println
 ((fn []
    ;;;
    (def rbs (load-file "./pry-ast/lib/pry/code.rb.ast"))
    
    (clojure.pprint/pprint
     (postwalk
      #(if (coll? %)
         ((fn []
;;            (if (or
;;                 (= 'def (first %))
;;                 (= 'defs (first %))
;;                 (= 'class (first %))
;;                 (= 'module (first %))
;;                 )
            (print (str % "\n")
                     ;;)
                   )
            ))
         %)
      rbs))
    ;;=>
    
    ;;;
    )))

