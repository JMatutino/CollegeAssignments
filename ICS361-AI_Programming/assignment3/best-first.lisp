;;; This is one of the example programs from the textbook:
;;;
;;; Artificial Intelligence: 
;;; Structures and strategies for complex problem solving
;;;
;;; by George F. Luger and William A. Stubblefield
;;;
;;; Corrections by Christopher E. Davis (chris2d@cs.unm.edu)
;;; 
;;;
;;; These programs are copyrighted by Benjamin/Cummings Publishers.
;;;
;;; We offer them for use, free of charge, for educational purposes only.
;;;
;;; Disclaimer: These programs are provided with no warranty whatsoever as to
;;; their correctness, reliability, or any other property.  We have written 
;;; them for specific educational purposes, and have made no effort
;;; to produce commercial quality computer programs.  Please do not expect 
;;; more of them then we have intended.
;;;
;;; This code has been tested with CMU Common Lisp CVS release-19a
;;; 19a-release-20040728 and appears to function as intended.

;;; this file contains the best-first search algorithm from chapter 7.

;;; for a simple example of its use with the farmer wolf goat and cabbage rules, 
;;; evaluate the move rule definitions in farmer_wolf_etc_rules_only.lisp,
;;; and bind them to the gloabl variable, *moves*:

; (setq *moves* 
;      '(farmer-takes-self farmer-takes-wolf 
;       farmer-takes-goat farmer-takes-cabbage))

;;; Also, the algorithm requires that a simple heuristic be used to evaluate
;;; states.  For the farmer, wolf, goat and cabbage rules, a simple heuristic 
;;; counts the number of players not in their goal positions:

; (defun heuristic (state)
;  (declare (special *goal*))
;  (heuristic-eval state *goal*))

; (defun heuristic-eval (state goal)
;  (cond ((null state) 0)
;        ((equal (car state) (car goal)) 
;        (heuristic-eval (cdr state) (cdr goal)))
;        (t (1+ (heuristic-eval (cdr state) (cdr goal))))))
;
;;; Once these have been defined, evaluate:
;;;
;;;  (run-best '(e e e e) '(w w w w))


;;; Default heuristic used for the farmer and jug problems
(defun heuristic (state)
  (declare (special *goal*))
  (heuristic-eval state *goal*))

(defun heuristic-eval (state goal)
  (cond ((null state) 0)
        ((equal (car state) (car goal)) 
        (heuristic-eval (cdr state) (cdr goal)))
        (t (1+ (heuristic-eval (cdr state) (cdr goal))))))

;;; insert-by-weight will add new child states to an ordered list of 
;;; states-to-try.  
(defun insert-by-weight (children sorted-list)
  (cond ((null children) sorted-list)
        (t (insert (car children) 
           (insert-by-weight (cdr children) sorted-list)))))

(defun insert (item sorted-list)
  (cond ((null sorted-list) (list item))
        ((< (get-weight item) (get-weight (car sorted-list)))
         (cons item sorted-list))
        (t (cons (car sorted-list) (insert item (cdr sorted-list))))))


;;; run-best is a simple top-level "calling" function to run best-first-search
;;; Used for famrer and water jug puzzles
(defun run-best (start goal)
  (declare (special *goal*)
           (special *open*)
           (special *closed*))
  (setq *goal* goal)
  (setq *open* (list (build-record start nil 0 (heuristic start))))
  (setq *closed* nil)
  (best-first))
  
 
;;; Tile game needs different heuristics so need to call different heuristic functions
;;; Uses Manhattan distance heuristic (heuristic-eval-1)

;;  Solvable? function is in my tiles-a3.lisp file
(defvar *tile-solveable?* '(solvable?))

;;UPDATE: First checks to see if game is solvable and prints error message if it isnt
;;Will work when goals are (1 2 3 8 0 4 7 6 5) or (1 2 3 4 5 6 7 8 0)
(defun run-best-on-tile (start goal)
(cond ((not (funcall (car *tile-solveable?*) start goal)) (princ "~~Game NOT solvable~~"))
  (t (declare (special *goal*)
           (special *open*)
           (special *closed*))
  (setq *goal* goal)
  (setq *open* (list (build-record start nil 0 (heuristic-tile start))))
  (setq *closed* nil)
  (best-first-tile))))

;;; Heuristic-eval using manhattan and defult heursitics
;;; Used just for n-puzzle
(defun heuristic-tile (state)
  (declare (special *goal*))
  (/ (+ (heuristic-eval state *goal*)(heuristic-eval-1 state *goal*)) 2))
  
 ;; best-first defines the actual best-first search algorithm
;;; it uses "global" open and closed lists.
;;;Used just for n-puzzle
(defun best-first-tile ()
  (declare (special *goal*)
           (special *open*)
           (special *closed*)
           (special *moves*))
  (setf state nil)
  (setf iter-num 1)
  ;; Loop while the goal state has not been found
  (loop while (not (equal (get-state state) *goal*)) 
	do
   ;; Printing the iteration number
  (format t "~%~%=====Iteration=====~%         ~a~%===================~%" iter-num)
  (format t "State examined on *open*: ~a " (car *open*))
  ;; print open and closed lists
  (format t "~%open =") (print *open*)
  (format t "~%closed =") (print *closed*)
  ;; If open is empty, return nil
  (cond ((null *open*) nil)
		;;otherwise get the first element on open to examine it
        (t (let ((state (car *open*)))
			;; add state into closed
             (setq *closed* (cons state *closed*))
			 ;; If found the goal, print out solution path
             (cond ((equal (get-state state) *goal*) (format t "~%Solution: ") (print (reverse (build-solution *goal*))) 
						(format t "~%Length of *open* = ~a~%" (length *open*))
						(format t "Length of *closed* = ~a~%" (length *closed*))(return))
					;; Look at moves depending on heuristic
                   (t (setq *open* 
                            (insert-by-weight 
                                    (generate-descendants-tile (get-state state)
                                                          (1+ (get-depth state))
                                                          *moves*)
                                    (cdr *open*)))
                    ))) 
					(incf iter-num)))))
					
;;; generate-descendants produces all the descendants of a state
;;; Used just for n-puzzle
(defun generate-descendants-tile (state depth moves)
  (declare (special *closed*)
           (special *open*))
  (cond ((null moves) nil)
        (t (let ((child (funcall (car moves) state))
                 (rest (generate-descendants-tile state depth (cdr moves))))
             (cond ((null child) rest)
                   ((retrieve-by-state child rest) rest)
                   ((retrieve-by-state child *open*) rest)
                   ((retrieve-by-state child *closed*) rest)
                   (t (cons (build-record child state depth 
                                          (+ depth (heuristic-tile child))) 
                            rest)))))))

  

;;; These functions handle the creation and access of (state parent) 
;;; pairs.
(defun build-record (state parent depth weight) 
  (list state parent depth weight))

(defun get-state (state-tuple) (nth 0 state-tuple))

(defun get-parent (state-tuple) (nth 1 state-tuple))

(defun get-depth (state-tuple) (nth 2 state-tuple))

(defun get-weight (state-tuple) (nth 3 state-tuple))

(defun retrieve-by-state (state list)
  (cond ((null list) nil)
        ((equal state (get-state (car list))) (car list))
        (t (retrieve-by-state state (cdr list)))))


;; best-first defines the actual best-first search algorithm
;;; it uses "global" open and closed lists.
;;; Used for farmer and water jug puzzles
;;; Comments are the same as best-first-tile function
(defun best-first ()
  (declare (special *goal*)
           (special *open*)
           (special *closed*)
           (special *moves*))
  (setf state nil)
  (setf iter-num 1)
  (loop while (not (equal (get-state state) *goal*)) 
	do
  (format t "~%~%=====Iteration=====~%         ~a~%===================~%" iter-num)
  (format t "State examined on *open*: ~a " (car *open*))
  (format t "~%open =") (print *open*)
  (format t "~%closed =") (print *closed*)
  (cond ((null *open*) nil)
        (t (let ((state (car *open*)))
             (setq *closed* (cons state *closed*))
             (cond ((equal (get-state state) *goal*) (format t "~%Solution: ") (print (reverse (build-solution *goal*))) 
						(format t "~%Length of *open* = ~a~%" (length *open*))
						(format t "Length of *closed* = ~a~%" (length *closed*))(return))
                   (t (setq *open* 
                            (insert-by-weight 
                                    (generate-descendants (get-state state)
                                                          (1+ (get-depth state))
                                                          *moves*)
                                    (cdr *open*)))
                    ))) 
					(incf iter-num)))))


;;; generate-descendants produces all the descendants of a state
;;; Used for farmer and water jug puzzles
(defun generate-descendants (state depth moves)
  (declare (special *closed*)
           (special *open*))
  (cond ((null moves) nil)
        (t (let ((child (funcall (car moves) state))
                 (rest (generate-descendants state depth (cdr moves))))
             (cond ((null child) rest)
                   ((retrieve-by-state child rest) rest)
                   ((retrieve-by-state child *open*) rest)
                   ((retrieve-by-state child *closed*) rest)
                   (t (cons (build-record child state depth 
                                          (+ depth (heuristic child))) 
                            rest)))))))


(defun build-solution (state)
  (declare (special *closed*))
  (cond ((null state) nil)
        (t (cons state (build-solution 
                        (get-parent 
                         (retrieve-by-state state *closed*)))))))
						 
;;;----------------------------------------------
;;; Code from Laulima - Heuristics-n-problem.pdf
;;;----------------------------------------------
; Define N - Dimension of the puzzle 
(defvar *N* 3) ; 8 puzzle 

;;; DISTANCE - calculate the distance a tile is from its goal position. 
(defun distance (tile state goal) 
 "Calculate the Manhattan distance a tile is from its 
 goal position." 
 (+ 
	 (abs (- (row tile state) 
		(row tile goal))) 
	(abs (- (column tile state) 
		(column tile goal)))))

;; Finds column based on the position in state		
(defun column (tile state)
	(setq pos (find-pos tile state 1))
	(cond ((or (equal pos 1)
		   (equal pos 4)
		   (equal pos 7)) 1)
		   
		  ((or (equal pos 2)
		   (equal pos 5)
		   (equal pos 8)) 2)
		   
		  ((or (equal pos 3)
		   (equal pos 6)
		   (equal pos 9)) 3)
		  (t (print "~~Something is wrong in column~~"))))

;; Finds the row based on the position in state		  
(defun row (tile state)
	(setq pos (find-pos tile state 1))
	(cond ((or (equal pos 1)
		   (equal pos 2)
		   (equal pos 3)) 1)
		   
		  ((or (equal pos 4)
		   (equal pos 5)
		   (equal pos 6)) 2)
		   
		  ((or (equal pos 7)
		   (equal pos 8)
		   (equal pos 9)) 3)
		  (t (print "~~Something is wrong in row~~"))))
		  

;; Finds  the position of the given tile in the given state
(defun find-pos (tile state pos-num)
	(cond ((equal tile (car state)) pos-num)
		  (t (find-pos tile (cdr state) (+ pos-num 1)))))
 
 
 
; HEURISTIC-EVAL-1 - Return the sum of the distances tiles are out of place. 
; A better heuristic for the n-puzzle problem than # of tiles out of place. 
 
(defun heuristic-eval-1 (state goal) 
 "Sum the distance for each tile from its goal position." 
 (do 
 ((sum 0) (tile 1 (+ tile 1))) ;loop adding dist for tiles 1-(n^2 - 1) 
 ((equal tile (* *n* *n*)) sum) ; return sum when done 
 (setq sum (+ sum (distance tile state goal)))) ) 
 
;;; HEURISTIC-EVAL-2 - Return the sum of the distances out of place 
;;; plus two times the number of direct tile reversals. 
 
(defun heuristic-eval-2 (state goal) 
 "Sum of distances plus 2 times the number of tile reversals" 
 (+ 
 (heuristic-eval-1 state goal) 
 (* 2 (tile-reversals state goal)) )) 
 
 ;;; TILE-REVERSE - Return 1 if the tiles are reversed in the two states, 
;;; else return 0. The blank tile doesn't increase the count (returns 0). 
 
(defun tile-reverse (pos1 pos2 state1 state2) 
 "Return 1 if the tiles in the two positions are reversed, else 0" 
 (cond 
 ((or 
 ;;; Blanks are 0's
 (equal (nth pos1 state1) 0) ; blanks don't count in reversals 
 (equal (nth pos2 state1) 0)) 
 0) ; return 0 
 ((and ; the tiles are reversed 
 (equal (nth pos1 state1) (nth pos2 state2)) 
 (equal (nth pos2 state1) (nth pos1 state2))) 
 1) ; return 1 
 (t 0)) ) ; else return 0 
 
 
 ; TILE-REVERSALS - Return the number of tiles directly reversed between state and goal. 
(defun tile-reversals (state goal) 
 "Calculate the number of tile reversals between two states" 
 (+ 
	 (do 
	 ((sum 0) (i 0 (1+ i))) ; loop checking row adjacencies 
	 ((equal i (- *n* 1)) sum) ; until i = n-2, return sum 
		 (do 
		 ((j 0 (1+ j))) 
		 ((equal j *n*) ) ; until j = n-1, nothing to return 
		 (setq sum (+ sum 
		 (tile-reverse (+ (* i *n*) j) (+ (* i *n*) j 1) 
		 state goal))))) 
	 (do 
	 ((sum 0) (i 0 (1+ i))) ; loop checking column adjacencies 
	 ((equal i *n* ) sum) ; until i = n-1, return sum 
		 (do 
		 ((j 0 (1+ j))) 
		 ((equal j (- *n* 1)) ) ; until j = n-2, nothing to return 
		 (setq sum (+ sum 
		 (tile-reverse (+ (* i *n*) j) (+ (* i *n*) j 1) 
		 state goal)))) )))
		 
		 

		 
						 
