(+ 1 2) ;; ` C-x C-e ` => 3

(-map (lambda (n) (* n n)) '(1 2 3 4)) ;;=> (1 4 9 16)
;; ` M-x describe-function RET map RET ` or ` C-h f map RET `
;;=> -map is a Lisp function in `dash.el'. (-map FN LIST)

