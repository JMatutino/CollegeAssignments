 ;;; -----------------
 ;;; --- Tile Game ---
 ;;; -----------------
 ;;; Jacob Matutino
 ;;; ICS361
 ;;; Assignment 2 - 9/24/14 
 
 (defun make-state (a b c d e f g h i)
  (list a b c d e f g h i))

;;Returns number in top-left position
(defun top-left (state)
  (nth 0 state))

;; Returns number in top middle position
(defun top-mid (state)
  (nth 1 state))

;; Return number in top right position
(defun top-right (state)
  (nth 2 state))

;; Returns number in middle left position
(defun mid-left (state)
  (nth 3 state))

;; Returns number in middle position
(defun mid (state)
  (nth 4 state))

;; Returns number in middle right position
(defun mid-right (state)
  (nth 5 state))

;; Returns number in bottom left position
(defun bot-left (state)
  (nth 6 state))

;; returns number in bottom mid position
(defun bot-mid (state)
  (nth 7 state))

;; Returns number in bottom right position
(defun bot-right (state)
  (nth 8 state))

;; moves space up
(defun move-up (state)
  ;;  Checks if space is in top row first, if in top row, return nil
  (cond ((or (equal (top-left state) 0)
             (equal (top-mid state) 0)   
             (equal (top-right state) 0)) nil)
        ((equal (mid-left state) 0) 
         (make-state 0 (top-mid state) (top-right state) 
                     (top-left state) (mid state) (mid-right state)
                     (bot-left state) (bot-mid state) (bot-right state)))
        ((equal (mid state) 0)
         (make-state (top-left state) 0 (top-right state)
                     (mid-left state) (top-mid state) (mid-right state)
                     (bot-left state) (bot-mid state) (bot-right state)))
        ((equal (mid-right state) 0)
         (make-state (top-left state) (top-mid state) 0
                     (mid-left state) (mid state) (top-right state)
                     (bot-left state) (bot-mid state) (bot-right state)))
        ((equal (bot-left state) 0)
         (make-state (top-left state) (top-mid state) (top-right state)
                     0 (mid state) (mid-right state)
                     (mid-left state) (bot-mid state) (bot-right state)))
       ((equal (bot-mid state) 0)
         (make-state (top-left state) (top-mid state) (top-right state)
                     (mid-left state) 0 (mid-right state)
                     (bot-left state) (mid state) (bot-right state)))
       ((equal (bot-right state) 0)
         (make-state (top-left state) (top-mid state) (top-right state)
                     (mid-left state) (mid state) 0
                     (bot-left state) (bot-mid state) (mid-right state)))))

;; Moves space down
(defun move-down (state)
  ;; Checks to see if space is in bottom row
  (cond ((or (equal (bot-left state) 0)
             (equal (bot-mid state) 0)
             (equal (bot-right state) 0)) nil)
        ((equal (top-left state) 0)
         (make-state (mid-left state) (top-mid state) (top-right state)
                     0 (mid state) (mid-right state)
                     (bot-left state) (bot-mid state) (bot-right state)))
        ((equal (top-mid state) 0)
         (make-state (top-left state) (mid state) (top-right state)
                     (mid-left state) 0 (mid-right state)
                     (bot-left state) (bot-mid state) (bot-right state)))
        ((equal (top-right state) 0)
         (make-state (top-left state) (top-mid state) (mid-right state)
                     (mid-left state) (mid state) 0
                     (bot-left state) (bot-mid state) (bot-right state)))
        ((equal (mid-left state) 0)
         (make-state (top-left state) (top-mid state) (top-right state)
                     (bot-left state) (mid state) (mid-right state)
                     0 (bot-mid state) (bot-right state)))
        ((equal (mid state) 0)
         (make-state (top-left state) (top-mid state) (top-right state)
                     (mid-left state) (bot-mid state) (mid-right state)
                     (bot-left state) 0 (bot-right state)))
       ((equal (mid-right state) 0)
         (make-state (top-left state) (top-mid state) (top-right state)
                     (mid-left state) (mid state) (bot-right state)
                     (bot-left state) (bot-mid state) 0))))

;; moves space left
(defun move-left (state)
  ;; Checks to see if space is in left column
  (cond ((or (equal (top-left state) 0)
             (equal (mid-left state) 0)
             (equal (bot-left state) 0)) nil)
        ((equal (top-mid state) 0)
         (make-state 0 (top-left state) (top-right state)
                     (mid-left state) (mid state) (mid-right state)
                     (bot-left state) (bot-mid state) (bot-right state)))
        ((equal (top-right state) 0)
         (make-state (top-left state) 0 (top-mid state)
                     (mid-left state) (mid state) (mid-right state)
                     (bot-left state) (bot-mid state) (bot-right state)))
        ((equal (mid state) 0)
         (make-state (top-left state) (top-mid state) (top-right state)
                     0 (mid-left state) (mid-right state)
                     (bot-left state) (bot-mid state) (bot-right state)))
        ((equal (mid-right state) 0)
         (make-state (top-left state) (top-mid state) (top-right state)
                     (mid-left state) 0 (mid state)
                     (bot-left state) (bot-mid state) (bot-right state)))
        ((equal (bot-mid state) 0)
         (make-state (top-left state) (top-mid state) (top-right state)
                     (mid-left state) (mid state) (mid-right state)
                     0 (bot-left state) (bot-right state)))
       ((equal (bot-right state) 0)
         (make-state (top-left state) (top-mid state) (top-right state)
                     (mid-left state) (mid state) (mid-right state)
                     (bot-left state) 0 (bot-mid state)))))

;; Move space right
(defun move-right (state)
  ;; Checks to see if space is in right column
  (cond ((or (equal (top-right state) 0)
             (equal (mid-right state) 0)
             (equal (bot-right state) 0)) nil)
        ((equal (top-left state) 0)
         (make-state (top-mid state) 0 (top-right state)
                     (mid-left state) (mid state) (mid-right state)
                     (bot-left state) (bot-mid state) (bot-right state)))
        ((equal (top-mid state) 0)
         (make-state (top-left state) (top-right state) 0
                     (mid-left state) (mid state) (mid-right state)
                     (bot-left state) (bot-mid state) (bot-right state)))
        ((equal (mid-left state) 0)
         (make-state (top-left state) (top-mid state) (top-right state)
                     (mid state) 0 (mid-right state)
                     (bot-left state) (bot-mid state) (bot-right state)))
        ((equal (mid state) 0)
         (make-state (top-left state) (top-mid state) (top-right state)
                     (mid-left state) (mid-right state) 0
                     (bot-left state) (bot-mid state) (bot-right state)))
        ((equal (bot-left state) 0)
         (make-state (top-left state) (top-mid state) (top-right state)
                     (mid-left state) (mid state) (mid-right state)
                     (bot-mid state) 0 (bot-right state)))
       ((equal (bot-mid state) 0)
         (make-state (top-left state) (top-mid state) (top-right state)
                     (mid-left state) (mid state) (mid-right state)
                     (bot-left state) (bot-right state) 0))))

;; Recursively solves
(defun move (state goal been-list)
  (cond ((null state) nil)
        ;; If reached a state-goal, return path solution
        ((equal state goal)
         (reverse (cons state been-list)))
        ;; If didnt try possible state yet, 
        ((not (member state been-list
                      :test #'equal))
         ;; Go through the possible moves
         (or (move (move-up state) goal (cons state been-list))
             (move (move-down state) goal (cons state been-list))
             (move (move-left state) goal (cons state been-list))
             (move (move-right state) goal (cons state been-list))))))

(defun solve-tiles (state goal)
  (move state goal nil))

 ;; Didnt implement this
(defun correct (state)
  (cond ((equal state '(1 2 3 8 0 4 7 6 5)) t)))
  
 (setq *moves* 
	'(move-up move-down move-left move-right))

(defvar *tile-start* '(2 8 3 1 6 4 7 0 5))
(defvar *tile-goal* '(1 2 3 8 0 4 7 6 5))
(defvar *tile-goal2* '(1 2 3 4 5 6 7 8 0))

;; Got these states from the following link:
;; http://www.d.umn.edu/~jrichar4/8puz.html
;; WIll be using these states for #3 of assignment 3
(defvar *tile-easy* '(1 3 4 8 6 2 7 0 5))
(defvar *tile-med* '(2 8 1 0 4 3 7 6 5))
(defvar *tile-hard* '(2 8 1 4 6 3 0 7 5))
(defvar *tile-worst* '(5 6 7 4 0 8 3 2 1))

;; Transforms the state so I can compare the tiles to check for inversions
;; The 'tile swaps' used in here are the same 'swaps' used to convert
;; 	This goal (1 2 3 8 0 4 7 6 5) to this goal (1 2 3 4 5 6 7 8 9 0)
(defun trans (state)
	(make-state 
		(top-left state) (top-mid state) (top-right state) 
		(mid-right state) (bot-right state) (bot-mid state)
		(bot-left state) (mid-left state) (mid state)))

 ;; If it is an inversion, return 1, else return 0
(defun inversions (first second)
	(cond ((equal first 0) 0)
		  ((equal second 0) 0)
		  ((> first second) 1)
		  (t 0)))

;; Sums up all of the inversions found and returns that sum value
;; Hardcoded for 8-puzzle
(defun sum-invs (state)
	(+  (inversions (top-left state) (top-mid state)) (inversions (top-left state) (top-right state))
		(inversions (top-left state) (mid-left state)) (inversions (top-left state) (mid state))
		(inversions (top-left state) (mid-right state)) (inversions (top-left state) (bot-left state))
		(inversions (top-left state) (bot-mid state)) (inversions (top-left state) (bot-right state))
		
		(inversions (top-mid state) (top-right state))
		(inversions (top-mid state) (mid-left state)) (inversions (top-mid state) (mid state))
		(inversions (top-mid state) (mid-right state)) (inversions (top-mid state) (bot-left state))
		(inversions (top-mid state) (bot-mid state)) (inversions (top-mid state) (bot-right state))
		
		(inversions (top-right state) (mid-left state)) (inversions (top-right state) (mid state))
		(inversions (top-right state) (mid-right state)) (inversions (top-right state) (bot-left state))
		(inversions (top-right state) (bot-mid state)) (inversions (top-right state) (bot-right state))
		
		(inversions (mid-left state) (mid state))
		(inversions (mid-left state) (mid-right state)) (inversions (mid-left state) (bot-left state))
		(inversions (mid-left state) (bot-mid state)) (inversions (mid-left state) (bot-right state))
		
		(inversions (mid state) (mid-right state)) (inversions (mid state) (bot-left state))
		(inversions (mid state) (bot-mid state)) (inversions (mid state) (bot-right state))
		
		(inversions (mid-right state) (bot-left state))
		(inversions (mid-right state) (bot-mid state)) (inversions (mid-right state) (bot-right state))
		
		(inversions (bot-left state) (bot-mid state)) (inversions (bot-left state) (bot-right state))
		
		(inversions (bot-mid state) (bot-right state))))
		
;; If even amount of inversions, then it is solvable
;; Otherwise, not solvable
(defun can-solve (state)
	(cond ((equal (rem (sum-invs state) 2) 0) t)
		  (t nil)))

;;; Will check if solvable only when goal is (1 2 3 8 0 4 7 6 5) or (1 2 3 4 5 6 7 8 0)
(defun solvable? (state goal)
(cond ((equal goal *tile-goal*)
			(cond ((can-solve (trans state)) t)
					(t nil)))
	  ((equal goal *tile-goal2*) 
			(cond ((can-solve state) t)
					(t nil)))))