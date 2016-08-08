(ns test
  (:require [clojure.string :as str])
  (:use clojure.walk))
(println
 ((fn []
    ;;;
    (def rbs
      '(module
        (const nil :Best)
        (begin
         (class
          (const nil :Post) nil
          (begin
           (def :ddd
             (args)
             (int 1))
           (defs
             (self) :ccc
             (args)
             (int 2))))
         (def :aaa
           (args)
           (int 3))
         (defs
           (self) :bbb
           (args)
           (int 4))))
      )
    
    (clojure.pprint/pprint
     (postwalk
      #(if (coll? %)
         ((fn []
            (if (or
                 (= 'def (first %))
                 (= 'defs (first %))
                 (= 'class (first %))
                 (= 'module (first %))
                 )
              (print (str % "\n"))
              )
            ))
         %)
      rbs))
    ;;=>
    
    ;;;
    )))
