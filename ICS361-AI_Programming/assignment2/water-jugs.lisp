;;; -----------------------
;;; --- Water Jug Game ----
;;; -----------------------
;;; Jacob Matutino
;;; ICS361
;;; Assignment 6 - 9/24/14

(defun make-state (a b)
  (list a b))

(defun small-jug (state)
  (nth 0 state))

(defun big-jug (state)
  (nth 1 state))

;; Fills small jug to 3 gallons
(defun fill-small (state)
  (cond ((equal (small-jug state) 3) nil)
        ((not (equal (small-jug state) 3)) (make-state 3 (big-jug state)))))

;; Fills big jug to 5 gallons
(defun fill-big (state)
  (cond ((equal (big-jug state) 5) nil)
        ((not (equal (big-jug state) 5)) (make-state (small-jug state) 5))))

;; Empty small jug
(defun empty-small (state)
  (cond ((equal (small-jug state) 0) nil)
        (t (make-state 0 (big-jug state)))))

;; Empty big jug
(defun empty-big (state)
  (cond ((equal (big-jug state) 0) nil)
        (t (make-state (small-jug state) 0))))

;; Pour small jug into big jug
(defun pour-small-to-big (state)
  ;; If Both jugs are full, cannot do anything
  (cond ((and (>= (small-jug state) 3)
              (>= (big-jug state) 5)) nil)
        ;; Considers when pouring to the max and still have water left over
        ((>= (+ (small-jug state) (big-jug state)) 5) (make-state (- (+ (small-jug state) (big-jug state)) 5) 5))
        ;; Otherwise pours everything in
        ((< (+ (small-jug state) (big-jug state)) 5)(make-state 0 (+ (small-jug state) (big-jug state))))))

;; Pour big jug to small
(defun pour-big-to-small (state)
  ;; If both the jugs are full, cannot do anything
  (cond ((and (>= (small-jug state) 3)
              (>= (big-jug state) 5)) nil)
        ;; Considers leftover water
        ((>= (+ (small-jug state) (big-jug state)) 3) (make-state 3 (- (+ (small-jug state) (big-jug state)) 3)))
        ;; Dumps everything in
        ((< (+ (small-jug state) (big-jug state)) 3)(make-state (big-jug state) 0))))

;; Modeled after fwgc
(defun start (state goal been-list)
       (cond ((null state) nil)
             ((equal state goal)
              (reverse (cons state been-list)))
             ((not (member state been-list :test #'equal))
              (or (start (fill-small state) goal (cons state been-list))
                  (start (fill-big state) goal (cons state been-list))
                  (start (empty-small state) goal (cons state been-list))
                  (start (empty-big state) goal (cons state been-list))
                  (start (pour-small-to-big state) goal (cons state been-list))
                  (start (pour-big-to-small state) goal (cons state been-list))))))

(defun solve-water-jug (state goal)
  (start state goal nil))
