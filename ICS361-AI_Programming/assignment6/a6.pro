
% -*- mode: Prolog  -*-
% (c) J. R. Fisher.
% http://www.csupomona.edu/~jrfisher/www/prolog_tutorial/2_17.html
% Modified by Jacob Matutino
% source: http://wildlifeofhawaii.com/hawaii-mammals.html

% Animal identification rules
% To run, type      go.

go :- hypothesize(Animal),
       write('I guess that the animal is: '),
       write(Animal), nl, undo.

 /* hypotheses to be tested */
/* Mammals of Hawaii */
hypothesize(feralCat) :- feralCat, !.
hypothesize(feralDog) :- feralDog, !.
hypothesize(monkSeal) :- monkSeal, !.
hypothesize(mongoose) :- mongoose, !.
hypothesize(bat) :- bat, !.
hypothesize(deer) :- deer, !.
hypothesize(goat) :- goat, !.
hypothesize(cattle) :- cattle, !.
hypothesize(boar) :- boar, !.
hypothesize(sheep) :- sheep, !.

hypothesize(unknown). /* no diagnosis */

/* Hawaiian Animals */
/* http://wildlifeofhawaii.com/hawaii-mammals.html */
feralCat :- mammal, carnivore,
	verify(has_tawny_color),
	verify(has_stripes),
	verify(has_pointy_ears).
feralDog :- mammal, carnivore,
	verify(has_tawny_color),
	verify(has_floppy_ears).
monkSeal :- mammal, carnivore,
	verify(lives_in_water),
	verify(has_flippers).
mongoose :- mammal, carnivore,
	verify(has_dark_fur),
	verify(is_small_in_size).
bat :- mammal,
	verify(has_wings),
	verify(is_nocturnal).

deer :- ungulate,
	verify(has_tawny_color),
	verify(has_antlers).
goat :- ungulate,
	verify(has_horns),
	verify(lives_in_mountains).
cattle :- ungulate,
	verify(has_horns),
	verify(has_splotches),
	verify(has_utters).
boar :- ungulate,
	verify(has_dark_fur),
	verify(has_tusks).
sheep :- ungulate,
	verify(is_fluffy),
	verify(has_light_fur).


/* classification rules */
mammal :- verify(has_hair), !.
mammal :- verify(gives_milk).
carnivore :- verify(eats_meat), !.
carnivore :- verify(has_pointed_teeth),
                    verify(has_claws),
                    verify(has_forward_eyes).
ungulate :- mammal, verify(has_hooves), !.
ungulate :- mammal, verify(chews_cud).
/* how to ask questions */
ask(Question) :-
        write('Does the animal have the following attribute: '),
        write(Question), write('? '),
         read(Response), nl,
         ( (Response == yes ; Response == y)
         -> assert(yes(Question)) ;
         assert(no(Question)), fail).
:- dynamic yes/1,no/1.
/* How to verify something */
verify(S) :- (yes(S) -> true ; (no(S) -> fail ; ask(S))).
/* undo all yes/no assertions */
undo :- retract(yes(_)),fail.
undo :- retract(no(_)),fail.
undo.
