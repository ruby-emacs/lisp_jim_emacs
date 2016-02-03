(defmacro p [& body] (pprint (macroexpand-all (do body))));;; for print macro
