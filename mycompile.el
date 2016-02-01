;; in ruby project: *rspec-compilation* , ` M: ` EVAL: 
(eval-and-compile (defvar aaa 11))
aaa ;=> 11
;;
(eval-when-compile (defvar bbb 11))
bbb

(regexp-opt '("aaa" "aba" "abb")) ;=> "\\(?:a\\(?:aa\\|b[ab]\\)\\)"
(defvar my-regexp
  (eval-when-compile (regexp-opt '("aaa" "aba" "abb"))))
my-regexp;;=> '"\\(?:a\\(?:aa\\|b[ab]\\)\\)"'

(eq 1 2) ;=> nil
(eq 2 2) ;=> t

(eq (list 21) 52) ;=> nil
(eq (list 21) (list 52)) ;=> nil

(eq (list 2) (list 2)) ;=> nil

(eq (quote 111) '111 ) ;=> t


(list 1 2 3) ;=> (1 2 3)

;;;;;;;;;;

(fboundp 'aaaooo) ;=> nil
;; 查看是否有定义某某函数 response_to?(method_name)
(fboundp 'car) ; => t
(fboundp 'aaaaaaaa) ; => nil
(fboundp 'if) ;=> t
(defmacro my-macro (x) `',x)
(fboundp 'my-macro)
(my-macro '(1 2 3));=> (quote (1 2 3))




