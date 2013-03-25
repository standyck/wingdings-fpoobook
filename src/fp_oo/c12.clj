(ns fp-oo.c12)
(defn rrange [first past-end]
  (new clojure.lang.LazySeq
       (fn []
         (if (= first past-end)
           nil
           (cons first
                 (rrange (inc first) past-end))))))

;;; excercise 1
(defn mmap [f coll]
  (new clojure.lang.LazySeq
       (fn []
         (if (empty? coll)
           nil
           (cons (f (first coll))
               (mmap f (rest coll)))))))

;;; excercise 2
; let's use lazy-seq instead of (new clojure.lang.LazySeq)
(defn ffilter [pred coll]
  (if (empty? coll) nil
      (if (pred (first coll))
        (cons (first coll)
              (lazy-seq (ffilter pred (rest coll))))
        (lazy-seq (ffilter pred (rest coll))))))
;;;looks a little cleaner.

;;; excercise 3
(defn eager-filter [pred coll]
  (if (empty? coll) nil
      (if (pred (first coll))
        (cons (first coll) (eager-filter pred (rest coll)))
        (eager-filter pred (rest coll)))))

;;; This throws an exception if the last element of a sequence is
;;; evaluated when only the `first` is asked for. It uses a large
;;; source sequence because many builtin sequence functions precalculate
;;; more than one element when asked for the first. Therefore, if
;;; the source sequence were something like [1 2], `filter` would count
;;; as eager. The number of elements precalculated is subject to change, but
;;; it's awfully unlikely to be 10,000.

(def eager?
  (fn [filter-function]
    (try
      (not (first (filter-function (fn [elt]
                                     (if (= elt 9999)
                                       (throw (Error.)))
                                     true)
                                   (range 10000))))
      (catch Error e
        true))))

(def prompt-and-read
  (fn []
    (print "> ")
    (.flush *out*)
    (.readLine
     (new java.io.BufferedReader *in*))))

;;; For exercise 3
(def counted-sum
  (fn [number-count numbers]
    (apply +
           (take number-count
                 numbers))))

(def number-string?
  (fn [string]
    (try
      (Integer/parseInt string)
      true
      (catch NumberFormatException ignored
        false))))

(def to-integer
  (fn [string]
    (Integer/parseInt string)))

(def ys-and-ns
  (filter #(or (.startsWith % "y") (.startsWith % "n"))
          (repeatedly prompt-and-read)))
(defn counted-sum2 []
  (let [nums (map to-integer
                  (filter number-string? (repeatedly prompt-and-read)))
        num-to-add (first nums)
        num-list (take num-to-add (rest nums))]
    (apply + num-list)))
