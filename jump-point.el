(defvar line-num (point))
(defun jump-point-now (x)
  (message "Line %s" line-num)
  ;(goto-line point)
  )
(jump-point-now 1 )
; =>  "Line 26"

