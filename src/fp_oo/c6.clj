(ns fp-oo.c6)

(def apply-message-to (fn [class instance message args]
                        (let [method (or (message (:__instance_methods__ class))
                                         message)]
                          (apply method instance args))))

(def make (fn [class & args]
            (let [seeded {:__class_symbol__ (:__own_symbol__ class)}]
              (apply-message-to class seeded :add-instance-values args))))

(def send-to (fn [instance message & args]
               (let [class (eval (:__class_symbol__ instance))]
                 (apply-message-to class instance message args))))

(def lineage (fn [class-symbol]
               ))
(def class-instance-methods (fn [class-symbol]
                              (:__instance_methods__ (eval class-symbol))))

(def Point
  {:__own_symbol__       'Point
   :__superclass_symbol__ 'Anything
   :__instance_methods__ {:add-instance-values (fn [this x y]
                                                 (assoc this :x x :y y))
                          :shift               (fn [this dx dy]
                                                 (make Point
                                                       (+ (:x this) dx)
                                                       (+ (:y this) dy)))
                          :add                 (fn [this that]
                                                 (send-to this
                                                          :shift
                                                          (:x that)
                                                          (:y that)))}})

(def Anything
  {:__own_symbol__ 'Anything
   :__instance_methods__ {:add-instance-values identity
                          :class-name :__class_symbol__
                          :class      (fn [this] (eval (:__class_symbol__ this)))}})


;;exercise 1
(def factorial-1 (fn [n]
                   (if (<= n 1)      ;;ending case
                     1               ;;ending value
                     (* n            ;;combiner
                        factorial-1
                        (dec n)))))  ;;smaller-struture-from

;;exercise 2
(def factorial-2 (fn [n]
                   (loop [someting n so-far 1]
                     (if (<= something 1)               ;;ending case
                       so-far
                       (recur (dec something)           ;;smaller-structure-from
                              (* something so-far)))))) ;;combiner
