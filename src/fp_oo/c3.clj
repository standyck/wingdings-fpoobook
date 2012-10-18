(ns fp-oo.c3)

(def Point
  (fn [x y]
    {:x x, :y y :__class_symbol__ 'Point}))

(def x :x)

(def y :y)

(def class-of :__class_symbol__)

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

(def make
  (fn [cls & params]
    (apply cls params)))

(def Triangle
  (fn [p1 p2 p3]
    {:p1 p1, :p2 p2 :p3 p3, :__class_symbol__ 'Triangle}))

(defn equal-tri?
  "Tests that triangles are equal. Note doesn't work for triangles with the same points but in a different order, but the exercise doesn't call for that."
  [triangle1 triangle2] (= triangle1 triangle2))

(defn equal-triangles? [& triangles] (apply = triangles))

(defn valid-triangle? [triangle]
  (= 3 (count (distinct [(:p1 triangle)
                        (:p2 triangle)
                        (:p3 triangle)]))))