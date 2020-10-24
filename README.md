François SUZEAU

# TP4Note


## Informations importante : Les horaires affiché par les escales sont faussé.

J'ai eu l'idée d'utiliser la double navigabilité afin de faire correspondre par exemple la première escale avec l'heure du départ du vol (par exemple +2h plus tard) et ensuite faire correspondre l'heure d'arrivé avec le nombre d'escale, le temps resté sur place afin de calculer une durée total de vol en fonction de tout ces paramètres.

Sachant que ce n'était pas la priorité j'ai préférer mettre cela pour plus tard, on a juste une date d'arrivée qui prend en compte le nombre d'escale du vol si il y en a et qui rajoute un jour par escale.

En revanche il n'y a qu'un seul vol par aeroport mais il est évidant que l'on gère les vols par aeroport via la double navigabilité.

 ### Pour lancer :

 J'ai utilisé gradle run. Il n'est pas nécéssaire d'utiliser un argument car j'ai modifier le build pour que gradle prenne les entrées clavier au moment ou le programme le demande.

Ligne 18 build.gradle
 	run {
 	standardInput = System.in
 }

 #### Déroulement du programme : 

 On demande quel compagnie l'utilisateur souhaite prendre puis une liste de ville pour la destination et le départ. Ensuite le programme affiche une liste de vol, son numéro et enfin les escales associé si il y en a avec heure de départ et d'arrivé par vol et par escale.