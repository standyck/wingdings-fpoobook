(ns fp-oo.c13
  (:use [patterned.sweet]))

;;; exercise 1
(defpatterned count-sequence
  [so-far [           ] ] so-far
  [so-far [head & tail] ] (count-sequence (inc so-far) tail)
  [       coll          ] (count-sequence 0 coll))

;;; excercise 2
(defpatterned pattern-reduce
  [f so-far []] so-far
  [f so-far [head & tail]] (pattern-reduce f (f so-far head) tail))
