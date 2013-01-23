(ns fp-oo.c9)

;;; exercise 1
(def add2-1 (map (fn [n] (+ 2 n)) [1 2 3]))
(def add2-2 (map (partial + 2)   [1 2 3]))
(def add2-3 (map + (repeat 2)    [1 2 3]))

;;; exercise 2
(defn separate-original [pred sequence]
  [(filter pred sequence) (remove pred sequence)])

(defn separate-with-juxt [pred sequence]
  ((juxt (partial filter pred) (partial remove pred)) sequence))

;;; exercise 3
(def myfun
  (let [x 3]
    (fn [] x)))
;;; x => "Unable to resolve symbol x
;;; (myfun) => 3
;;; The value myfun closes around x so x isn't bound.

;;; exercise 4
(def my-letlessfun
  (fn []
    ((fn [x] x) 3)))

;;; exercise 5
(def my-atom (atom 0))
(def make-it-a-35 (constantly 35))
;;; (swap! my-atom make-it-a-35)
;;; oops! I used constantly
