;;; Sample COBWEB animal data taken from the ML article

(setf *feature-names* '(body-cover heart-chamber body-temp fertilization))

(setf *domains* '((hair feathers cornified-skin moist-skin scales)
		  (two three imperfect-four four)
		  (regulated unregulated)
		  (internal external)))

(setf *raw-examples*
      '(
	(amphibian (moist-skin three unregulated external))
	(fish (scales two unregulated external))
	(mammal (hair four regulated internal))
	(bird   (feathers four regulated internal)) 
	(reptile (cornified-skin imperfect-four unregulated internal))
	(fish2 (scales two unregulated external))
	
	(alien1 (moist-skin imperfect-four unregulated external))
	(alien2 (moist-skin two unregulated external))
	(alien3 (moist-skin three unregulated external))
	(alien4 (moist-skin four unregulated external))
	(alien5 (moist-skin two regulated external))
	(alien6 (moist-skin three regulated external))
	(alien7 (moist-skin four regulated external))
	(alien8 (moist-skin imperfect-four regulated external))
	(alien9 (moist-skin two regulated internal))
	(alien10 (moist-skin three regulated internal))
      ))
