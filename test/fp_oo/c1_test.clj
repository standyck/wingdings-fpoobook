(ns fp-oo.c1-test
  (:use clojure.test)
  (:require [fp-oo.c1 :as c1]))

(def test-list '(0 1 2 3))
(def test-vector [1 2 3 4])

(deftest exercise1-test
  (testing "Exercise 1 tests..."
    (is (= (c1/my-second test-list)
           1))
    (is (= (c1/my-second test-vector)
           2))))

(deftest exercise2=test
  (testing "Exercise 2 tests..."
    (is (= (c1/third1 test-list)
           (c1/third2 test-list)
           2))))

(deftest exercise3-test
  (testing "Exercise 3 tests..."
    (is (= (c1/add-squares 1 4 9)
           98))))

(deftest exercise4-test
  (testing "Exercise 4 tests..."
    (is (= (c1/factorial-with-range 0) 1))
    (is (= (c1/factorial-with-range 1) 1))
    (is (= (c1/factorial-with-range 5) 120))))

(deftest exercise6-test
  (testing "Exercise 6 tests..."
    (is (true? (c1/prefix-of? [1 2] [1 2 3 4])))
    (is (false? (c1/prefix-of? '(2 3) [1 2 3 4])))
    (is (true? (c1/prefix-of? '(1 2) [1 2 3 4])))
    (is (false? (c1/prefix-of? [1 2 3 4] [1 2])))
    (is (true? (c1/prefix-of? [1 2 3] [1 2 3])))
    (is (false? (c1/prefix-of? 1 1)))))

(deftest exercise7-test
  (let [result '((1 2 3 4) (2 3 4) (3 4) (4) ())]
    (testing "Exercise 7 tests..."
      (is (= (c1/tails '(1 2 3 4)) result))
      (is (= (c1/tails [1 2 3 4]) result)))))
