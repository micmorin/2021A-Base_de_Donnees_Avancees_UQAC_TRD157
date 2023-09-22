Travail Pratique #3: 
Conception du Serveur d’Application et du Client


Michael Morin
Université du Québec à Chicoutimi
Département d'Informatique et de Mathématiques

TRD157 : Bases de Données Avancées
Jimmy G. Nault

29 Novembre 2021

	
Cas 2 : connexion d’un utilisateur au système
Pour la connection, un email client et un mot de passe doivent être utilisé. Si les identifiants ne fonctionnent pas, aucune autre fonctions ne sera disponible et un message d'erreur apparaitra. Les identifiants ont la forme client#@domain.com pour le email et Client123# pour le mot de passe ou # est un chiffre entre 1 et 5 inclusivement.

Cas 3 : consultation interactive des films
Après une connection réussis, les critères de recherche sont disponibles. Un ou plusieurs critères peuvent être utilisé, mais ils soint utilisé par la recherche indépendament, sauf pour les années. On pourrait donc chercher pour un film et un réalisateur et avoir un film répondant au critère du titre et un autre répondant au critère du réalisateur. Pour ce cas, les critères prennent les formes suivantes, ou # est dans {1,2,3,4,5} :
    - Titre : Film# 
    - Annee Min ou Max : 190#
    - Pays : {Canada, France, Mongolie}
    - Langue : {Francais, Anglais, Espagnol, Arabe, Japonnais, Russe}
    - Genre : {Action, Romance, Horreur, Comedy, Drame}
    - Realisateur : PTP# pour le prenom et  PTN# pour le nom
    - Acteur :  {ActP1, ActP2, ActP3} pour le prenom et  {ActN1, ActN2, ActN3} pour le nom
Tous les crières sauf les années sont sensible à la case et cherche le critère dans le mots, ce qui veut dire que le critère n'a pas besoin d'être écris au long pour fonctionner.

Cas 4 : location de films 
	Lorsque la fiche d'un film est sélectionnée, cliquer sur le bouton Louer permet d'effectuer la location du film. Appuyer sur ce boutton vérifie la disponibilité du film et la capacité du client à le louer selon son forfait.
