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

