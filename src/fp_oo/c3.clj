(ns fp-oo.c3)

;;The exercises from Chapter 3
(def Point
  (fn [x y]
    {:x x, :y y :__class_symbol__ 'Point}))

(def x :x)

(def y :y)

(def class :__class_symbol__)

(def shift
  (fn [this dx dy]
    (Point (+ (x this) dx)
           (+ (y this) dy))))

(def add
  (fn [p1 p2]
    (Point (+ (x p1) (x p2))
           (+ (y p1) (y p2)))))

(def add2
  (fn [p1 p2]
    (shift p1 (x p2) (y p2))))

(def a
  (fn [cls & params]
    (apply cls params)))

(def Triangle
  (fn [p1 p2 p3]
    {:p1 p1, :p2 p2 :p3 p3, :__class_symbol__ 'Triangle}))

(def p (Point 1 2))
