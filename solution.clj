;; solution.clj

;; Function that given a number (end) returns the median of the sequence 0...end.
; For the seq 0...n the median is given by:
; if length n is odd: (n+1)/2 -1
; minus one is to convert from 1...n to 0...n
; if the length is even: (((n/2) -1) + (n/2)) /2
(defn median [end]
	(let [
		length (+ end 1)
		offset (if (even? length) 0 1)
		p (- (quot (+ length offset) 2) 1)] ; p is index of median.
	(if (even? length) (/ (+ p (+ p 1)) 2.0) p)
))

;; Function that finds and prints any possible distances that are longer for the given time frame.
; We find the median of the seq 0...t and multiply element wise the vectors
; [0, 1, 2, ..., median(t)) and [t, t-1, .., median(t))
; We only check up to median(t) because the distances are mirrorred about the median.
; For instance for t=7, distances = 0*7, 1*6, 2*5, 3*4, 4*3, 5*2, 6*1, 7*0.
; The median is 3.5, so we only check up to 3*4.
(defn find_better_distance [t dx]
	(filter #(> % dx) (map * (range (median t)) (range t (median t) -1)))
)

;; Count the number of better distances found for a given race i.e. a given pair of time and distance.
(defn find_num_better_distances [t dx]
	; count the best distances
	;also check whether median*median >> distance when time is even since the previous function does not check it.
	(+ (* (count (find_better_distance t dx)) 2) (if (and (even? t) (> (* (median t) (median t)) dx)) 1 0))
)

; Calculate the number of different ways to beat the record across all races.
(defn find_num_ways_beat_record [times distances]
	(reduce * (map find_num_better_distances times distances))
)

;;; Part 1 calculation ;;;

;; sample
;(def times [7 15 30])
;(def distances [9 40 200])

;; puzzle input
(def times [41 66 72 66])
(def distances [244 1047 1228 1040])


(println "Number of ways to beat record for all races: " (find_num_ways_beat_record times distances))


;;; Part 2 calculation ;;;

;; sample
;(def singleRaceTime [71530])
;(def singleRaceDistance [940200])

;; puzzle input
(def singleRaceTime [41667266])
(def singleRaceDistance [244104712281040])

(println "Number of ways to beat record for single long race: " (find_num_ways_beat_record singleRaceTime singleRaceDistance))
