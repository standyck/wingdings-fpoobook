(ns fp-oo.c4
  (:require [fp-oo.c3 :only [make] :as c3]))

(def send-to
  (fn [object message & args]
    (apply (message (:__methods__ object)) object args)))

(def Point
  (fn [x y]
    {:x x
     :y y

     ;;Metadata
     :__class_symbol__ 'Point
     :__methods__ {:class :__class_symbol__
                   :x :x
                   :y :y
                   :shift (fn [this dx dy]
                            (c3/make Point (+ (:x this) dx)
                                     (+ (:y this) dy)))
                   :add (fn [this that]
                          (send-to this
                                   :shift
                                   (send-to that :x)
                                   (send-to that :y)))}}))
