(ns fp-oo.c6)

(def class-symbol-above (fn [class-symbol]
                          (:__superclass_symbol__ (eval class-symbol))))

(def class-instance-methods (fn [class-symbol]
                              (:__instance_methods__ (eval class-symbol))))

(def lineage (fn [class-symbol]
               (loop [sym class-symbol so-far []]
                 (if (nil? sym)
                   so-far
                   (recur (class-symbol-above sym)
                          (cons sym so-far))))))

(def method-cache (fn [class]
                    (let [class-symbol (:__own_symbol__ class)
                          method-maps  (map class-instance-methods
                                            (lineage class-symbol))]
                      (apply merge method-maps))))

(def apply-message-to (fn [class instance message args]
                        (let [method (message (method-cache class))]
                          (apply method instance args))))

(def make (fn [class & args]
            (let [seeded {:__class_symbol__ (:__own_symbol__ class)}]
              (apply-message-to class seeded :add-instance-values args))))

(def send-to (fn [instance message & args]
               (let [class (eval (:__class_symbol__ instance))]
                 (apply-message-to class instance message args))))

(def class-instance-methods (fn [class-symbol]
                              (:__instance_methods__ (eval class-symbol))))

(def Point
  {:__own_symbol__        'Point
   :__superclass_symbol__ 'Anything
   :__instance_methods__  {:add-instance-values (fn [this x y]
                                                  (assoc this :x x :y y))
                           :shift               (fn [this dx dy]
                                                  (make Point
                                                        (+ (:x this) dx)
                                                        (+ (:y this) dy)))
                           :add                 (fn [this that]
                                                  (send-to this
                                                           :shift
                                                           (:x that)
                                                           (:y that)))
                           :mirror              (fn [this]
                                                  (make Point
                                                        (:y this)
                                                        (:x this)))}})

(def Anything
  {:__own_symbol__       'Anything
   :__instance_methods__ {:add-instance-values identity
                          :class-name :__class_symbol__
                          :class      (fn [this] (eval (:__class_symbol__ this)))}})


;;It'd be nice to define a macro to create a class.
;;We want something like this...
(comment
  (defclass Boom
    :extends Point
    :members [x y z]
    :methods [(foo [arg1 arg2] (str arg1 ":" arg2))
              (tostring [] (str "x=" ))]))

;;exercise 1
(def factorial-1 (fn [n]
                   (if (<= n 1)      ;;ending case
                     1               ;;ending value
                     (* n            ;;combiner
                        (factorial-1 (dec n))))))  ;;smaller-struture-from

;;exercise 2
(def factorial-2 (fn [n]
                   (loop [something n so-far 1]
                     (if (<= something 1)               ;;ending case
                       so-far
                       (recur (dec something)           ;;smaller-structure-from
                              (* something (bigint so-far))))))) ;;combiner

;;exercise 3
(def add-sequence (fn [seq start-val]
                    (if (empty? seq)
                      start-val
                      (recur (rest seq)
                             (+ start-val (first seq))))))

;;exercise 4
(def mult-sequence (fn [seq start-val]
                     (if (empty? seq)
                       start-val
                       (recur (rest seq)
                              (* start-val (first seq))))))

(def apply-to-sequence (fn [fn seq start-val]
                         (if (empty? seq)
                           start-val
                           (recur fn (rest seq)
                                  (fn start-val (first seq))))))

;;exercise 5
(def silly-map-combiner (fn [start-val kw]
                          (assoc start-val kw 0)))

(def position-combiner (fn [start-val kw]
                         (assoc start-val kw (count start-val))))

(apply-to-sequence silly-map-combiner [:a :b :c] {})
(apply-to-sequence position-combiner [:a :b :c] {})
