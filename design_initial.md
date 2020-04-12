 # Description sytème initial
 
 MarsRover est un jeu faisant affronter deux joueurs. Afin de ne pas rencontrer de problème de concurence entre les joueurs, via le fait de se déplacer en même temps, nous instaurons in système de tour par tour.
 
 L'état du jeu sera sotcké de la manière suivante:
   - la position des joueurs dans le jeu représenter par une liste avec des coordonnées et une direction
   - la position des obstacles présents sur la map représenté par une liste avec des coordonnées
   - l'état des joueurs si ils sont toujours en jeu représenté part une liste de booléen
 
 Afin de sécurisé l'accès au jeu, nous mettons en place un système d'authentification username(qui sera un email) et mot de passe. ce dernier sera hashé.
 
 Plusieurs parties seront en cours en même temps. Afin de ne pas rencontrer de problème, une copie de map sera généré à chaque partie via l'API REST, l'utilisateur pourra spécifier la dimension souhaité qui sera ajouté en paramètre de la requète.
 
 Les joueurs s'authentifieront sur une interface web leur demandant de renseigner leur Email et leur mot de passe. le joueur pourra alors consulté ses parties en cours.
 Afin de les afficher, nous devrons associé l'ID de la partie au joueur concerné.
 
 Si le serveur plante, les données des joueurs seront sauvegardé au dernier état.
 
 La sauvegarde de la progression de joueur se fera de manière automatique. Après chaque requète une mis e à jour de l'état est effectuée. Cela permet de pallier certains problèmes comme une fermeture de fenêtre inopinée ou autre disfonctionnement de la machine ou du serveur.
 Le joueur aura le choix de reprendre la dernière partie joué lors de la connexion.
 
 Afin de gérer les charges inprévu nous pouvons nous tourner vers un Application Load Balancer qui permet d'équilibrer les charges du trafic HTTP et HTTPS.
