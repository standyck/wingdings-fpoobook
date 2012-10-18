(ns fp-oo.c3-test
  (:use clojure.test)
  (:require [fp-oo.c3 :as c3]))

(def p1 (c3/Point 1 2))

(def p2 (c3/Point 3 4))

(def p3 (c3/Point 1 3))

(def tri1 (c3/Triangle p1 p2 p3))

(def tri2 (c3/Triangle p1 p2 p3))

(def tri3 (c3/Triangle p1 p2 (c3/add p1 p3)))

(deftest exercise1-tests
  (testing "Exercise 1 tests..."
    (is (= (c3/add p1 p2)
           (c3/add2 p1 p2)
           (c3/Point 4 6)))))

(deftest exercise2-tests
  (testing "Exercise 2 tests..."
    (is (= (c3/make c3/Point (c3/x p1) (c3/y p1))
           p1))
    (is (= (c3/make c3/Triangle p1 p2 p3)
           tri1))))

(deftest exercise3-tests
  (testing "Exercise 3 tests..."
    (is (true? (c3/equal-tri? tri1 tri1)))
    (is (true? (c3/equal-tri? tri1 tri2)))
    (is (false? (c3/equal-tri? tri1 tri3)))))

(deftest exercise4-tests
  (testing "Exercise 4 tests..."
    (is (false? (c3/equal-triangles? tri1 tri2 tri3)))
    (is (true? (c3/equal-triangles? tri1 tri1 tri2 tri2)))))

(deftest exercise5-tests
  (testing "Exercise 5 tests..."
    (is (true? (c3/valid-triangle? tri1)))
    (is (false? (c3/valid-triangle? (c3/make c3/Triangle p1 p1 p3))))))