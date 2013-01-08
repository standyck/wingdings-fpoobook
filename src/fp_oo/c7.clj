(ns fp-oo.c7)

(def parameters
  {:instructors 3  :course-count 7 :courses-per-day 2})

(def course-sample
  {:course-name "Zigging"  :morning?    true   :limit 5 :registered 3})

(defn answer-annotations [courses registrants-courses]
  (let [checking-set (set registrants-courses)]
    (map (fn [course]
           (assoc course
             :spaces-left (- (:limit course)
                             (:registered course))
             :already-in? (contains? checking-set
                                     (:course-name course))))
         courses)))

(defn domain-annotations [courses]
  (map (fn [course]
         (assoc course
           :empty? (zero? (:registered course))
           :full?  (zero? (:spaces-left course))))
       courses))

(defn note-unavailability [courses instructor-count]
  (let [out-of-instructors? (= instructor-count
                               (count (filter (fn [course] (not (:empty? course)))
                                              courses)))]
    (map (fn [course]
           (assoc course
             :unavailable? (or (:full? course)
                               (and out-of-instructors?
                                    (:empty? course)))))
         courses)))

(defn annotate [courses registrants-courses instructor-count]
  (-> courses
      (answer-annotations registrants-courses)
      domain-annotations
      (note-unavailability instructor-count)))

;;exercise 1
(def v [1])
(-> v
    first
    inc
    list)

;;exercise 2
(-> v
    first
    inc
    (* 3)
    list)

;;exercise 3
(-> 3
    ((fn [n] (* 2 n)))
    inc)

;;exercise 4
(-> (+ 1 2)
    (* 3)
    (+ 4))

(defn separate [pred seq]
  [(filter pred seq) (remove pred seq)])

(defn visible-courses [courses]
  (let [[guaranteed possibles] (separate :already-in? courses)]
    (concat guaranteed (remove :unavailable? possibles))))

(defn final-shape [courses]
  (let [desired-keys [:course-name :morning? :registered :spaces-left :already-in?]]
    (map (fn [course]
           (select-keys course desired-keys))
         courses)))

(defn half-day-solution [courses registrants-courses instructor-count]
  (-> courses
      (annotate registrants-courses instructor-count)
      visible-courses
      ((fn [courses] (sort-by :course-name courses)))
      final-shape))

(defn solution [courses registrants-courses instructor-count]
  (map (fn [courses]
         (half-day-solution courses registrants-courses instructor-count))
       (separate :morning? courses)))
