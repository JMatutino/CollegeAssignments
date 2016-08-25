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
                     (mid-right state) (top-mid state) (mid-right state)
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

(defvar *default-tile-start* '(2 8 3 1 6 4 7 0 5))
(defvar *tile-goal* '(1 2 3 8 0 4 7 6 5))
;;; Extra credit:  At least I can get the solutions
(defvar *tile-start-two* '(1 3 4 8 6 2 7 0 5))
(defvar *tile-start-three* '(2 8 1 0 4 3 7 6 5))
 