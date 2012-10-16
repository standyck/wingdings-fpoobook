(ns fp-oo.c3
  (:use [clojure.core :exclude '[class]]))

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

;;exercise 1
(def add
  (fn [p1 p2]
    (Point (+ (x p1) (x p2))
           (+ (y p1) (y p2)))))

(def add2
  (fn [p1 p2]
    (shift p1 (x p2) (y p2))))

;;exercise 2
(def a
  (fn [cls & params]
    (apply cls params)))

(def Triangle
  (fn [p1 p2 p3]
    {:p1 p1, :p2 p2 :p3 p3, :__class_symbol__ 'Triangle}))


;;exercise 3
(defn equal-tri?
  "Tests"
  [triangle1 triangle2] (= triangle1 triangle2))

;;exercise 4
(defn equal-triangles? [& triangles] (apply = triangles))

;;exercise 5
(defn valid-triangle? [triangle]
  (= 3 (count (distinct [(:p1 triangle)
                        (:p2 triangle)
                        (:p3 triangle)]))))
