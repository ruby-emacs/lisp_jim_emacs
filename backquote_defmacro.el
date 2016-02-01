`(a b c) ;;=> (a b c)

;;反引号的优点是，在一个反引号表达式里，你可以使用 , (逗号)与 ,@ (comma-at)来重启求值。

;;在某个东西前面加逗号，则它会被求值=> 使用反引号与逗号来建构列表模版
`(1 is ,1 and 2 is a) ;;=> (1 is 1 and 2 is a) ;; a is no def,but no report erro
`(1 is ,1 and 2 is ,2) ;;=> (1 is 1 and 2 is 2)

;; setf function
(setf a 2)
(+ a a a a a) ;; => 10
(defmacro nils (x)
  `(setf ,x 1)) ;; 对x求值
(nils a) ;; 不可以a没有定义
(+ a a) ;;=> 2

;;;

