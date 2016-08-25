/*
  Counts the number of items in a given list
*/
listlength([],0).
listlength([_|List],Num) :- listlength(List,Num1) , Num is Num1+1.

/*
  Recursively finds factorial
*/
myfac(0,Result) :- Result is 1.
myfac(Num,Result) :- Num > 0,
	Num1 is Num-1,
	myfac(Num1,Result1),
	Result is Result1*Num.

/*
  Solutions
*/
% select an element for use in permutation test
%
% If the element is the head of the list, then it is in the list, and the tail is left
selectE(Element, [Element|Tail], Tail).
% If the two lists have the same head, check for more elements in the rest of the lists
selectE(Element, [Head|Tail1], [Head|Tail2]) :-
        selectE(Element, Tail1, Tail2).

% generate permutations
%
% The empty list is a permutation of itself
permutationQ([],[]).
% List1 is a permutation of List2 if each element occurs in both lists
%    the same number of times
permutationQ(List, [Head|Tail]) :- selectE(Head, List, Rest),
                                  permutationQ(Rest, Tail).



/* Situation 1*/
sit1same :- permutationQ(
	  [blue,blue,green,green,yellow,yellow],
	  [A,B,C,D,E,F]),
((A==blue->B\==blue);
(B==blue->C\==blue);
(C==blue->D\==blue);
(D==blue->E\==blue);
(E==blue->F\==blue)),

((A==green->B\==green);
(B==green->C\==green);
(C==green->D\==green);
(D==green->E\==green);
(E==green->F\==green)),

((A==yellow->B\==yellow);
(B==yellow->C\==yellow);
(C==yellow->D\==yellow);
(D==yellow->E\==yellow);
(E==yellow->F\==yellow)),
/* No consecutive balls are same color */

printout6([A,B,C,D,E,F]).


sit1dif :- permutationQ(
	      [green1,green2,blue1,blue2,yellow1,yellow2],
	      [A,B,C,D,E,F]),
	 (A==green1 -> B\==green2;
	  A==green2 -> B\==green1;
	  B==green1 -> C\==green2;
	  B==green2 -> C\==green1;
	  C==green1 -> D\==green2;
	  C==green2 -> D\==green1;
	  D==green1 -> E\==green2;
	  D==green2 -> E\==green1;
	  E==green1 -> F\==green2;
	  E==green2 -> F\==green1),

	 (A==blue1 -> B\==blue2;
	  A==blue2 -> B\==blue1;
	  B==blue1 -> C\==blue2;
	  B==blue2 -> C\==blue1;
	  D==blue1 -> E\==blue2;
	  D==blue2 -> E\==blue1;
	  E==blue1 -> F\==blue2;
	  E==blue2 -> F\==blue1;
	  C==blue1 -> D\==blue2;
	  C==blue2 -> D\==blue1),

	 (A==yellow1 -> B\==yellow2;
	  A==yellow2 -> B\==yellow1;
	  B==yellow1 -> C\==yellow2;
	  B==yellow2 -> C\==yellow1;
	  C==yellow1 -> D\==yellow2;
	  C==yellow2 -> D\==yellow1;
	  D==yellow1 -> E\==yellow2;
	  D==yellow2 -> E\==yellow1;
	  E==yellow1 -> F\==yellow2;
	  E==yellow2 -> F\==yellow1),

	 printout6([A,B,C,D,E,F]).


sit2same :- permutationQ(
	[black,black,black,black,red,blue],
		[A,B,C,D,E,F]),

	((A==black,B==black -> C\==black);
	(B==black,C==black -> D\==black);
	(C==black,D==black -> E\==black);
	(D==black,E==black -> F\==black)),

	printout6([A,B,C,D,E,F]).


sit2dif :- permutationQ(
	[black1,black2,black3,black4,red1,blue1],
		[A,B,C,D,E,F]),

	((A==black1,B==black2 -> C\==black3,C\==black4);
	(A==black1,B==black3 -> C\==black4,C\==black2);
	(A==black1,B==black4 -> C\==black3,C\==black2);

	(A==black2,B==black1 -> C\==black3,C\==black4);
	(A==black2,B==black3 -> C\==black4,C\==black1);
	(A==black2,B==black4 -> C\==black3,C\==black1);

	(A==black3,B==black2 -> C\==black1,C\==black4);
	(A==black3,B==black1 -> C\==black4,C\==black2);
	(A==black3,B==black4 -> C\==black1,C\==black2);

	(A==black4,B==black2 -> C\==black3,C\==black1);
	(A==black4,B==black3 -> C\==black1,C\==black2);
	(A==black4,B==black1 -> C\==black3,C\==black2);

	%-----------------------------------
	(B==black1,C==black2 -> D\==black3,D\==black4);
	(B==black1,C==black3 -> D\==black4,D\==black2);
	(B==black1,C==black4 -> D\==black3,D\==black2);

	(B==black2,C==black1 -> D\==black3,D\==black4);
	(B==black2,C==black3 -> D\==black4,D\==black1);
	(B==black2,C==black4 -> D\==black3,D\==black1);

	(B==black3,C==black2 -> D\==black1,D\==black4);
	(B==black3,C==black1 -> D\==black4,D\==black2);
	(B==black3,C==black4 -> D\==black1,D\==black2);

	(B==black4,C==black2 -> D\==black3,D\==black1);
	(B==black4,C==black3 -> D\==black1,D\==black2);
	(B==black4,C==black1 -> D\==black3,D\==black2);

	%-----------------------------------
	(C==black1,D==black2 -> E\==black3,E\==black4);
	(C==black1,D==black3 -> E\==black4,E\==black2);
	(C==black1,D==black4 -> E\==black3,E\==black2);

	(C==black2,D==black1 -> E\==black3,E\==black4);
	(C==black2,C==black3 -> E\==black4,E\==black1);
	(C==black2,D==black4 -> E\==black3,E\==black1);

	(C==black3,D==black2 -> E\==black1,E\==black4);
	(C==black3,D==black1 -> E\==black4,E\==black2);
	(C==black3,D==black4 -> E\==black1,E\==black2);

	(C==black4,D==black2 -> E\==black3,E\==black1);
	(C==black4,D==black3 -> E\==black1,E\==black2);
	(C==black4,D==black1 -> E\==black3,E\==black2);

	%-----------------------------------
	(D==black1,E==black2 -> F\==black3,F\==black4);
	(D==black1,E==black3 -> F\==black4,F\==black2);
	(D==black1,E==black4 -> F\==black3,F\==black2);

	(D==black2,E==black1 -> F\==black3,F\==black4);
	(D==black2,E==black3 -> F\==black4,F\==black1);
	(D==black2,E==black4 -> F\==black3,F\==black1);

	(D==black3,E==black2 -> F\==black1,F\==black4);
	(D==black3,E==black1 -> F\==black4,F\==black2);
	(D==black3,E==black4 -> F\==black1,F\==black2);

	(D==black4,E==black2 -> F\==black3,F\==black1);
	(D==black4,E==black3 -> F\==black1,F\==black2);
	(D==black4,E==black1 -> F\==black3,F\==black2)),

	 printout6([A,B,C,D,E,F]).

sit3same :- permutationQ(
	 [black,white,white,red,red,green,green,green],
		[A,B,C,D,E,F,G,H]),
		%1 2 3 4 5 6 7 8

 (B\==green,C\==green), %The balls in positions 2 and 3 are not green.

 ((D==white->H==white);
 (D==red->H==red);
 (D==green->H==green)), %The balls in positions 4 and 8 are the same color

 (A==white->G\==white;
  A==red->G\==red;
  A==green->G\==green), %The balls in positions 1 and 7 are of different colors

 ((A\==red,C\==red,D\==red),
  (B==red->A==green),
  (E==red->D==green)), %There is a green ball to the left of every red ball

 (A\==white,H\==white), %A white ball is neither first nor last

 (F\==red,G\==red),

 printout8([A,B,C,D,E,F,G,H]).







sit3dif :- permutationQ(
	[black1,white1,white2,red1,red2,green1,green2,green3],
		[A,B,C,D,E,F,G,H]),
	        %1 2 3 4 5 6 7 8

	(B\==green1,C\==green1,
	 B\==green2,C\==green2,
	 B\==green3,C\==green3), /* The balls in positions 2 and 3 are not green. */

	 ((D\==black1,H\==black1);

	  (D==white1->H==white2;
	   D==white2->H==white1);

	  (D==red1->H==red2;
	   D==red2->H==red1);

	  (D==green1->H==green2;
	   D==green1->H==green3;
	   D==green2->H==green1;
	   D==green2->H==green3;
	   D==green3->H==green1;
	   D==green3->H==green2)), /* The balls in positions 4 and 8 are the same color. */

	  (A==white1->G\==white2;
	   A==white2->G\==white1;

	   A==red1->G\==red2;
	   A==red2->G\==red1;

	   A==green1->G\==green2;
	   A==green1->G\==green3;
	   A==green2->G\==green1;
	   A==green2->G\==green3;
	   A==green3->G\==green1;
	   A==green3->G\==green2), /* The balls in positions 1 and 7 are of different colors.*/

	   ((A\==red1,A\==red2,
	    C\==red1,D\==red1,
	    C\==red2,D\==red2),

	   ((B==red1->(A==green1;A==green2;A==green3));
	   (B==red2->(A==green1;A==green2;A==green3))),
	   ((E==red1->(D==green1;D==green2;D==green3));
	   (E==red2->(D==green1;D==green2;D==green3)))), /* There is a green ball to the left of every red ball. */

	  (A\==white1,H\==white2,
	   A\==white2,H\==white1), /* A white ball is neither first nor last. */

	  (F\==red1,G\==red2,
	   F\==red2,G\==red1),
	   /* The balls in positions 6 and 7 are not red. */

	  printout8([A,B,C,D,E,F,G,H]).


go :- permutationQ(
[red,black,white],[A,B,C]),
% white is just right of black
((A==black -> B==white); % OR
 (B==black -> C==white)), % AND
% black is not in the center
B\==black, % AND
% print any solution you find
 printout([A,B,C]).


 printout6([A,B,C,D,E,F]) :-
        write(A), write(' '),
        write(B), write(' '),
        write(C), write(' '),
        write(D), write(' '),
		write(E), write(' '),
		write(F),nl.

 printout8([A,B,C,D,E,F,G,H]) :-
        write(A), write(' '),
        write(B), write(' '),
        write(C), write(' '),
        write(D), write(' '),
		write(E), write(' '),
		write(F), write(' '),
		write(G), write(' '),
		write(H),nl.

 printout([A,B,C]) :-
        nl,
        write('The order of balls from left to right is: '), nl,
        write('A  is '), write(A),nl,
        write('B  is '), write(B),nl,
        write('C  is '), write(C),nl.









