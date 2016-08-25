;;;; Jacob Matutino
;;;  ICS361
;;;  Assignment 2 9/24/14
;;; Code taken from Artificial Intelligence
;;; By: George Luger

(defun make-state (f w g c) (list f w g c))

;;; Farmer is first in state
(defun farmer-side (state)
  (nth 0 state))

;;; Wolf is second in state
(defun wolf-side (state)
  (nth 1 state))

;;; Goat is third in state
(defun goat-side (state)
  (nth 2 state))

;;; Cabbage is fourth in state
(defun cabbage-side (state)
  (nth 3 state))

;;; Will replace with opposite side
(defun opposite (side)
  (cond ((equal side 'e) 'w)
        ((equal side 'w) 'e)))

;;; Farmer moves to the opposite side
(defun farmer-takes-self (state)
  (safe
   (make-state (opposite (farmer-side state))
               (wolf-side state)
               (goat-side state)
               (cabbage-side state))))

;;; Farmer takes wolf to other side
(defun farmer-takes-wolf (state)
  ;;If farmer and wolf are on teh same side
  (cond ((equal (farmer-side state)
                (wolf-side state))
         ;;Has to be safe after farmer takes wolf
         (safe (make-state
                (opposite (farmer-side state))
                (opposite (wolf-side state))
                (goat-side state)
                (cabbage-side state))))
        ;; return true, otherwise nil
        (t nil)))

;;; Farmer take goat to other side
(defun farmer-takes-goat(state)
  ;; Farmer has to be on same side of goat
  (cond ((equal (farmer-side state)
                (goat-side state))
         ;;Has to be safe after farmer takes goat
         (safe (make-state
                (opposite (farmer-side state))
                (wolf-side state)
                (opposite (goat-side state))
                (cabbage-side state))))
        (t nil)))

;; Farmer take cabbage to other side
(defun farmer-takes-cabbage (state)
  ;; Farmer has to be on same side of cabbage
  (cond ((equal (farmer-side state)
                (cabbage-side state))
         ;; Need to be safe after farmer takes cabbage over
         (safe (make-state
                (opposite (farmer-side state))
                (wolf-side state)
                (goat-side state)
                (opposite
                 (cabbage-side state)))))
        (t nil)))

;;; Makes sure everything is in a safe state
(defun safe (state)
  ;; Goat and Wolf cannot be together without farmer
  (cond ((and (equal (goat-side state)
                     (wolf-side state))
              (not (equal (farmer-side state)
                          (wolf-side state))))
         nil)
        ;; Goat and cabbage cannot be together without farmer
        ((and (equal (goat-side state)
                    (cabbage-side state))
              (not (equal (farmer-side state)
                         (goat-side state))))
         nil)
        (t state)))

;;; Solves the scenario while remembering what already has been tried
(defun path (state goal been-list)
  (cond ((null state) nil)
        ((equal state goal)
         (reverse (cons state been-list)))
        ((not (member state been-list
                      :test #'equal))
         (or (path (farmer-takes-self state) goal
                   (cons state been-list))
             (path (farmer-takes-wolf state) goal
                   (cons state been-list))
             (path (farmer-takes-goat state) goal
                   (cons state been-list))
             (path (farmer-takes-cabbage state) goal
                   (cons state been-list))))))

;;; Function that user will call with been-list hidden
(defun solve-fwgc (state goal)
  (path state goal nil))
  
(setq *moves* 
  ;;Farmer
  '(farmer-takes-self farmer-takes-wolf 
  farmer-takes-goat farmer-takes-cabbage))
  
(defvar *farmer-start* '(e e e e))
(defvar *farmer-goal* '(w w w w))
(defvar *farmer-goal* '(w w w w))
  
  
