(println
 ((fn []

    (def a [{:user_id 1 :name "user 1"}
            {:user_id 2 :name "user 2"}])
    (def b [{:user_id 2 :email "e 2"}
            {:user_id 1 :email "e 1"}])
    (map :user_id a) ;; (1 2)
    (zipmap (map :user_id a) a) ;; {1 {:user_id 1, :name user 1}, 2 {:user_id 2, :name user 2}}
    
;;    (defmacro local-context0 []
;;      (let [symbols &env)]
;;        (println symbols)  ))
;;
    
    (defmacro local-context1 []
      (let [symbols (keys &env)]
        (println symbols)  ))
    
    (let [a 1 b 2 c 3]
      (let [b 200] (local-context1)))  ;;=>  (a b c)
    
    ;;okokokokok next=> 
    (defmacro local-context2 []
      (let [symbols (keys &env)]
        (zipmap (map
                 (fn [sym] `(quote ~sym)) symbols)
                symbols) ))
    
    (let [a 1 b 2 c 3]
      (let [b 200] (local-context2)))  ;;=>  {a 1, b 200, c 3}

    
    
    
    )))
