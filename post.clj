(ns test
  (:require [clojure.string :as str])
  (:use clojure.walk))
(println
 ((fn []
    ;; http://stackoverflow.com/questions/15296300/walk-vs-map-for-processing-a-seq
    (def s1 (range 8)) ;; (0 1 2 3 4 5 6 7)
    (def s2 (partition 2 s1)) ;; ((0 1) (2 3) (4 5) (6 7)) 两个作为一组
    (def s3 (partition 2 s2)) ;; (((0 1) (2 3)) ((4 5) (6 7)))　再次两个作为一组
    (def s4 (partition 2 s3)) ;; ((((0 1) (2 3)) ((4 5) (6 7))))　
    (map inc #{0 1 2}) ;;=> (1 2 3)
    (walk inc identity #{0 1 2}) ;;=> #{1 3 2} ;; (identity any) => any

    ;; 下面两种计算等同: postwalk正是要解决多层map的问题, 他是递归map的,不管多少层它都"写法一样"
    (postwalk
     #(if (number? %) (inc %) %)
     s4) ;; => ((((1 2) (3 4)) ((5 6) (7 8))))
    (map (partial map (partial map (partial map inc))) s4) ;;=> ((((1 2) (3 4)) ((5 6) (7 8))))

    ;;当两层结构时
    (map (partial map inc) s2) ;; ((1 2) (3 4) (5 6) (7 8))
    (postwalk #(if (number? %) (inc %) %) s2) ;; ((1 2) (3 4) (5 6) (7 8))

    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
    ;;; 当多层数组结构递归求值的时候: 代码就像Ruby的数组一样
    (def t [+ [* [- 6 2] [/ 9 3]] [* 2 [+ 7 8]]])
    (def s
      (postwalk
       #(if (coll? %)
          (apply list %) ;; 将数组转化为列表
          %)
       t)
      ) ;;=> (#object[clojure.core$_PLUS_ 0x15d8c81e clojure.core$_PLUS_@15d8c81e] (#object[clojure.core$_STAR_ 0x89f474b clojure.core$_STAR_@89f474b] (#object[clojure.core$_ 0xc405555 clojure.core$_@c405555] 6 2) (#object[clojure.core$_SLASH_ 0x3d4551a clojure.core$_SLASH_@3d4551a] 9 3)) (#object[clojure.core$_STAR_ 0x89f474b clojure.core$_STAR_@89f474b] 2 (#object[clojure.core$_PLUS_ 0x15d8c81e clojure.core$_PLUS_@15d8c81e] 7 8)))
    (eval s) ;;=> 42

    (postwalk
     #(if (and (coll? %) (fn? (first %)))
        (apply (first %) (next %)) ;; 直接执行每个单列表结构
        %)
     t) ;;=> 42 ,之间apply求值了
    
    ;;
    )))
