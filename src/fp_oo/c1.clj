(ns fp-oo.c1)

;;exercies for chapter 1

;;excercise 1
(def my-second (fn [list]
              (first (rest list))))

;;exercise 2
(def third1 (fn [list]
              (first (rest (rest list)))))

(def third2 (fn [list]
              (-> list
                  rest
                  rest
                  first)))

;;exercise 3
(defn add-squares [& nums]
  (let [squared-nums (map (fn [x] (* x x)) nums)]
    (apply + squared-nums)))

;;exercise 4
(defn factorial-with-range [num]
  (let [seq (range 1 (inc num))]
    (apply * seq)))

;;exercise 6
(def all-true? (partial every? true?))
(defn prefix-of? [seq1 seq2]
  (if (and (all-true? (map sequential? [seq1 seq2]))
           (<= (count seq1) (count seq2)))
    (all-true? (map = seq1 seq2))
    false))

;;exercise 7
(defn tails [list]
  (let [drops (range (inc (count list)))]
    (map (fn [counter] (drop counter list)) drops)))
