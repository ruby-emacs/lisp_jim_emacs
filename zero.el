(+ 1 2) ;; ` C-x C-e ` => 3

(-map (lambda (n) (* n n)) '(1 2 3 4)) ;;=> (1 4 9 16)
;; ` M-x describe-function RET map RET ` or ` C-h f map RET `
;;=> -map is a Lisp function in `dash.el'. (-map FN LIST)

;; 直接不用文件名写好有意思了再拷贝到文件上, 只是写在Emacs缓存`*scratch*`上
(1+ 2) ;;=> 3  ;; function ` 1+ ` #=> (1+ NUMBER)

;; (def ctwo(arg);2 * arg;end);  [2,4,6].map(&:ctwo)
(defun 2* (arg)
  (* 2 arg)) ;; 先按一次 ` C-x C-e ` ; 将函数定义压入缓存
(mapcar 'aa '(2 4 6)) ;; 先按一次 ` C-x C-e ` => (4 6 8)
;; (mapcar FUNCTION SEQUENCE)


;; about quote 
(quote 1) ;; => 1 <=> '1
(+ '1 2) ;; => 3

;; Lisp的列表是一种可嵌套的树形结构 ;;=> graph 图音书
(defun factorial (N)
  (if (= N 1)
      1
    (* N (factorial (- N 1)))
    )
  )
(factorial 10) ;; => 3628800
(format "hello world") ;;=> "hello world"

