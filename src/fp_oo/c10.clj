(ns fp-oo.c10
  (:use [clojure.algo.monads]))

(+ (* (+ 1 2) 3) 4)



(-> (+ 1 2)
    ((fn [step1-value]
       (-> (* step1-value 3)
           ((fn [step2-value]
              (+ step2-value 4)))))))

;;; exercise 10.3.1
(let [a (concat '(a b c) '(d e f))
      b (count a)]
  (odd? b))
;;; ...is the same as...
(-> (concat '(a b c) '(d e f))
    count
    odd?)
;;; ...is the same as...
(-> (concat '(a b c) '(d e f))
    ((fn [x]
       (-> (count x)
           ((fn [y]
              (odd? y)))))))
