(defn map-m
  {:added "1.0" :static true}
  
  ([f]
   (fn [rf]
     (fn
       ([] (rf))
       ([result] (rf result))
       ([result input]
        (rf result (f input)))
       
       ([result input & inputs]
        (rf result (apply f input inputs)))
       
       )
     )
   )
  ;; =======
  ([f coll]
   (lazy-seq
    (when-let [s (seq coll)]
      (if (chunked-seq? s)

        
        (let [c (chunk-first s)
              size (int (count c))
              b (chunk-buffer size)]
          
          (dotimes [i size]
            (chunk-append b (f (.nth c i))))
          
          (chunk-cons (chunk b) (map-m f (chunk-rest s)))

          )
        

        (cons (f (first s)) (map-m f (rest s)))

        
        )
      )
    )

   )
  ;; =======
  
  ([f c1 c2]
   (lazy-seq
    (let [s1 (seq c1) s2 (seq c2)]
      (when (and s1 s2)
        
        (cons
         (f (first s1) (first s2))
         (map-m f (rest s1) (rest s2))
         )

        ))))
  
  ;; =======
  
  ([f c1 c2 c3]
   (lazy-seq
    (let [s1 (seq c1) s2 (seq c2) s3 (seq c3)]
      (when (and  s1 s2 s3)
        
        (cons
         (f (first s1) (first s2) (first s3))
         (map-m f (rest s1) (rest s2) (rest s3))
         )

        ))))

  ;; =======
  
  ([f c1 c2 c3 & colls]
   (let [
         step (fn step [cs]
                (lazy-seq
                 (let [ss (map-m seq cs)]
                   (when (every? identity ss)
                     
                     (cons (map-m first ss) (step (map-m rest ss)))

                     )))

                )]
     
     (map-m #(apply f %) (step (conj colls c3 c2 c1)))

     )))

;; ---------------------------
(map #(str "Hello " % "!" ) ["Ford" "Arthur" "Tricia"]) ; => ("Hello Ford!" "Hello Arthur!" "Hello Tricia!")


