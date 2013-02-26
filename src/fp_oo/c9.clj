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

;;; exercise 6
(defn always [thing-to-return] (fn [& args] thing-to-return))

;;; exercise 7
;(def isbns [0131774115 0977716614 1934356190])
(defn check-sum-isbn [coll]
  {:pre [(every? integer? coll)]}
  (apply + (map * (map inc (range)) coll)))

;;; exercise 8
(defn isbn*? [candidate-isbn]
  (let [int-seq (map #(-> % str (Integer.)) (seq (reverse candidate-isbn)))
        cs      (check-sum-isbn int-seq)]
    (zero? (rem cs 11))))

;;; exercise 9
(defn check-sum-upc [coll]
  {:pre [(every? integer? coll)]}
  (apply + (map * (cycle [1 3]) coll)))

(defn upc*? [candidate-upc]
  (let [int-seq (map #(-> % str (Integer.)) (seq (reverse candidate-upc)))
        cs      (check-sum-upc int-seq)]
    (zero? (rem cs 10))))

;;; exercise 10
(defn number-checker [multiple-seq div]
  (fn [num]
    (let [int-seq   (map #(-> % str (Integer.)) (seq (reverse num)))
          check-sum (apply + (map * multiple-seq int-seq))]
      (zero? (rem check-sum div)))))

(def isbn? (number-checker (map inc (range)) 11))
(def upc? (number-checker (cycle [1 3]) 10))
