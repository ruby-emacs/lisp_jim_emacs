
(setq y 2)
(let ( (y 1) (z y) ) ; (2 1) =>let1 => (2 1) => let2 => (1 2)
  (list y z) ) ;=> (1 2)

(setq k 3)
(let* ((k 1) (z k)); (3 1) => let1 => (3 1) => let2 => (1 1)
  (list k z k z z k)) ;=> (1 1 1 1 1 1)

(setq u 3)
(let* ((u 8) (z u) (a)) ;=>'(u 8) (z u i)' => `let' bindings can have only one value-form: z, u, t 
  (list z z u z z u a u)) ;=> (8 8 8 8 8 8 nil 8)

(setq u 3)
(let* ((u 8) (z) (a)) ;=>'(u 8) (z u i)' => `let' bindings can have only one value-form: z, u, t 
  (list z u a)) ;=> (nil 8 nil)

(setq oo 88)
(let ((oo 99))) ;=> nil
oo;=>88


;; (let VARLIST BODY...)
;; Bind variables according to VARLIST then eval BODY.
(setq oo 68)
(let ((oo 99) (a))
  (+ oo 1) ) ;=> 100

