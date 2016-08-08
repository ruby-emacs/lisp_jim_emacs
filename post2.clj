;;module Best
;;  class Post
;;    def ddd
;;      1
;;    end
;;    def self.ccc
;;      2
;;    end
;;  end
;;  def aaa
;;    3
;;  end
;;  def self.bbb
;;    4
;;  end
;;end
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
         (apply vector %)
         %)
      rbs))
    ;;=>
    [module
     [const nil :Best]
     [begin
      [class
       [const nil :Post]
       nil
       [begin [def :ddd [args] [int 1]] [defs [self] :ccc [args] [int 2]]]]
      [def :aaa [args] [int 3]]
      [defs [self] :bbb [args] [int 4]]]]

    ;;;
    )))
