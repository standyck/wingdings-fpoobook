(ns fp-oo.c11)

;;; exercise 1
(defn tak [x y z]
;  (println x ":" y ":" z)
  (if (< y x)
    (tak (tak (dec x) y z)
         (tak (dec y) z x)
         (tak (dec z) x y))
    z))

#_(declare tarai)
#_(defn lazy-tarai [x y zx zy zz]
  (if-not (< y x)
    y
    (lazy-tarai (tarai (dec x) y z)
                (tarai (dec y) z x)
                (dec (tarai zx zy zz)) x y)))

#_(defn tarai [x y z]
  (if-not (< y x)
    y
    (lazy-tarai (tarai (dec x) y z)
                (tarai (dec y) z x)
                (dec z) x y)))

;;; exercise 1
(defn seq-zip* [tree] tree)
(defn zdown* [zipper] (first zipper))
(defn znode* [zipper] zipper)

;;; exercise 2
(defn seq-zip [tree]
  {:here tree
   :parents '()})
(defn znode [zipper]
  (:here zipper))
(defn zdown [zipper]
  {:here (first (:here zipper))
   :parents (cons zipper (:parents zipper))})
(defn zup [zipper]
  {:here (first (:parents zipper))
   :parents (rest (:parents zipper))})
