(ns fp-oo.c5)

(def apply-message-to (fn [class instance message args]
                        (let [method (or (message (:__instance_methods__ class))
                                         message)]                   ;;Add or condition for execise 4
                          (apply method instance args))))

(def make (fn [class & args]
            (let [seeded {:__class_symbol__ (:__own_symbol__ class)}]
              (apply-message-to class seeded :add-instance-values args))))

(def send-to (fn [instance message & args]
               (let [class (eval (:__class_symbol__ instance))]
                 (apply-message-to class instance message args))))

;;These are the pre-refactored make and send-to from before exercise 1.
(def make-old (fn [class & args]
                (let [seeded      {:__class_symbol__ (:__own_symbol__ class)}
                      constructor (:add-instance-values (:__instance_methods__ class))]
                  (apply constructor seeded args))))

(def send-to-old (fn [instance message & args]
                   (let [class  (eval (:__class_symbol__ instance))
                         method (message (:__instance_methods__ class))]
                     (apply method instance args))))

(def Point
  {:__own_symbol__       'Point
   :__instance_methods__ {:class-name          :__class_symbol__
                          :class               (fn [this] (eval (:__class_symbol__ this)))
                          :add-instance-values (fn [this x y]
                                                 (assoc this :x x :y y))
                          :shift               (fn [this dx dy]
                                                 (make Point
                                                       (+ (:x this) dx)
                                                       (+ (:y this) dy)))
                          :origin              (fn [this] (make Point 0 0))}})

;;exercise 3
;;before:
;; (def p1 (make Point 1 2))
;; (send-to p1 :origin) => NullPointerException
;;... Add :origin function to Point...
;; (send-to p1 :origin) => {:y 0, :x 0, :__class_symbol__ Point}
;; Since :origin is attached to the class (Point) and not the instance (p1), we can
;; modify the class and have those changes apply to existing instances. The send-to
;; function looks up the class each time so includes changes.

;;exercise 4 Here is Holder. See apply-messasge-to above.
(def Holder
  {:__own_symbol__        'Holder
   :__instance_methods__  {:add-instance-values
                           (fn [this held] (assoc this :held held))}})

;;before
;; (def h1 (make Holder "stuff"))
;; (send-to h1 :held) => NullPointerException
;;after changes to apply-message-to...
;; (send-to h1 :held) => "stuff"

;;exercise 5
;; (send-to h1 :some-unknown-message) => nil
;; Since retrieval from a clojure map returns nil when the key doesn't exist.
