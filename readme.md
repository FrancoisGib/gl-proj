# WebMagic

Lien du git du projet : https://github.com/code4craft/webmagic

## 1. Présentation globale du projet

### 1.1 Utilité du projet

* Webmagic est un web crawler (robot d'indexation). Il couvre l'ensemble du cycle de vie d'un crawler : téléchargement, gestion des URL, extraction de contenu et persistance. Il peut simplifier le développement d'un crawler spécifique.
  C'est un framework qui fonctionne avec une API pour extraire le contenu des pages html.
* Pour lancer le projet, il faut d'abord configurer "log4j" qui est un journal d'événements fait par Apache.
* Le crawler télécharge les pages que l'utilisateur demande et les places dans un dossier défini par l'utilisateur.

### 1.2 Description du projet

* Il y a bien un readme dans le git du projet, il explique les fonctionnalités du crawler ainsi que les dépendances à importer pour le faire marcher. Cependant, le readme ne spécifie pas réellement comment utliser le crawler, il donne un petit template de code permettant de télécharger un site mais ne fonctionne pas correctement.
Il faut importer soi-même le framework **log4j** et le configurer soi-même, le readme ne fournit aucune indication sur cette configuration.
* On peut trouver une documentation plutôt complète sur le site de WebMagic, même si encore une fois le fonctionnement initial du crawler n'est pas explicite.
* Malgré la documentation complète, il faut d'abord savoir de nombreuses choses avant de pouvoir exploiter les fonctionnalités.

## 2. Historique du logiciel

### 2.1 Analyse du git
* 49 développeurs ont travaillé sur WebMagic mais on trouve principalement **code4craft** qui a développé et coordonné la plupart du projet.
* Le projet a été créé en 2013 et son pic d'activité était en 2014, le projet a été ralenti durant 3 ans et a été beaucoup refactor en 2017, mais la plupart des fonctionnalités ont été implémentées avant 2017. Depuis 2017, le projet a été repris par **Sutra** qui prend la place de code4craft.
* On compte 47 branches dans le projet. Curieusement, la branche principale ne s'appelle pas **main** ou **master** mais **develop**. Seulement 5 des 47 branches sont utilisées régulièrement, les autres sont pour la plupart des saves et des logs.
* La mécanique des pull requests est utilisée. Il y en a 32 en attente et 181 ont été fermées à ce jour.



## 3. Architecture logicielle

### 3.1 Utilisation de bibliothèques extérieures
* Il y a 26 dépendances et 35 plugins importés.
* Les dépendances suivantes ne sont pas utilisées mais sont importées inutilement:
  com.google.guava:guava:jar:32.0.0-jre:compile
  org.slf4j:slf4j-api:jar:2.0.4:compile
  org.apache.commons:commons-lang3:jar:3.12.0:compile
  org.apache.commons:commons-collections4:jar:4.4:compile
  com.fasterxml.jackson.core:jackson-core:jar:2.15.2:compile
  com.fasterxml.jackson.core:jackson-annotations:jar:2.15.2:compile

Il est embêtant de maintenir des dépendances surtout si celles ci ne sont pas utiles donc il faudra les retirer.

### 3.2 Paquetages

* On compte 28 paquetages dans le projet.
* On a des packages qui ne sont pas utilisés


## 4. Analyse approfondie

### 4.1 Tests

Il y a 108 méthodes de tests dont 4 ignorées dans le projet et 143 assertions ce qui est ridicule. Néanmoins les 104 tests passent.

### 4.2 Commentaires

* La javadoc est inégale, certaines classes sont bien documentées mais les autres n'en présentent aucune, de plus certaines documentations de méthodes n'expliquent en rien ce que la méthode fait, ce qui n'aide pas à la compréhension du projet. Si un nouveau développeur était amené à travailler sur le projet, il mettrait certainement très longtemps à comprendre l'utilité qu'a chacune des classes du projet. De plus, la documentation est parfois en chinois et non en anglais, ce qui limite fortement le nombre de développeur pouvant travailler sur le projet.
* Il y a 986 lignes de commentaires (hors javadoc) et 797 lignes de javadoc, soit dix pour cent des méthodes du projet sont documentées et certaines documentations sont très succintes et n'apportent rien à la compréhension du projet et trois pour cent des attributs, ce qui est très peu, mais les classes bien documentées sont souvent propre et n'ont pas de code commenté.
Globalement, on voit vraiment une inégalité dans les différentes classes du projet, soit une classe est propre, testée et documentée, soit elle ne l'est pas du tout.

### 4.3 Dépréciation

* Il y a beaucoup de méthodes dépréciées, la plupart du temps, ***@deprecated*** est indiqué mais ils le laisse. Cela s'explique par le fait que certains projets utilisant ***webmagic*** peuvent encore utiliser ces méthodes, ce qui pourrait corrompre l'éxécution de ces projets,
elles sont donc indiquées comment deprecated le temps que ces projets utilisent les nouvelles méthodes à jour.

* Il y a beaucoup d'appels à du code déprécié, par exemple dans les classes ***AbstractDownloader***, ***Spider***, ***Html*** et beaucoup d'autres, cependant dans la majorité des cas, la nouvelle méthode remplacant cette méthode dépréciée est documentée.
Il arrive cependant que la nouvelle méthode remplacant celle déprécié appelle elle même la méthode dépréciée comme les méthodes ***onError*** et ***onSuccess*** dans ***SpiderListener***, la conséquence de ce genre de modification est que les projets utilisant ces méthodes vont alors utiliser la nouvelle méthode non dépréciée alors qu'elle est équivalente à l'ancienne méthode qui elle est dépréciée.

### 4.4 Duplication de code

* Il y a très peu de duplication de code, mais ces duplications sont principalement dans le package ***webmagic-samples***, c'est un package à part des autres qui contient probablement des modèles d'implémentation et qui n'est pas utilisé par les autres packages.

* On ne peut pas vraiment supprimer ces duplications, car comme expliqué juste avant, ces duplications sont des modèles pour implémenter les autres classes.

### 4.5 God Classes

La classe ***Spider*** du package ***webmagic-core*** peut être considérée comme une God Class, avec plus de 500 lignes de code et compte une trentaine de dépendances et un grand nombre d'attributs, la classe a trop de responsabilités et d'informations sur les autres classes, ces responsabilités devraient être déléguées à d'autres classes.

### 4.6 Analyse des méthodes

La méthode avec la plus haute complexité cyclomatique est de 26, il s'agit de la méthode ***processSingle*** de la classe ***PageModelExtractor***, certaines méthodes sont vides, donc la complexité minimale est de 0. La complexité cyclomatique moyenne est de 1,69 ce qui est relativement acceptable même si certaines méthodes sont extrêmes.

## 5. Nettoyage de Code et Code smells

### 5.1 Règles de nommage

Les noms de méthodes, d'attributs sont globalement bons, chaque première lettre de mots est en majuscule (excepté le premier mot), c'est compréhensible et prononcable et ils n'hésitent pas à avoir des méthodes avec des noms longs pour décrire au mieux la méthode.

### 5.2 Nombre magique

On ne trouve pas de nombres magiques dans le projet, à chaque fois ces nombres sont définis par des constantes ce qui rend leur compréhension facile.

### 5.3 Structure du code

Dans certaines classes les attributs sont bien au début, mais quand on a des constantes, les attributs et les constantes sont mélangés, comme dans la classe ***Spider***, ce qui empêche de trouver les attributs facilement.
Pour ce qui est de l'ordre des méthodes, les méthodes sont souvent mélangées, qu'elles soient public, private ou protected, ce qui est une mauvaise pratique.

### 5.4 Code mort

On compte 262 warnings de code non utilisé ce qui en fait du code mort. Il y a même des classes comme la classe ***TianyaPageProcesser*** qui ne sont jamais utilisées.

On peut potentiellement supprimer certains bout de code mort, mais impossible de savoir avec exactitude si ce code sera utile dans le futur. Cependant, certaines méthodes "mortes" sont sûrement dans le projet afin d'être utilisées par les personnes souhaitant créer leur propre crawler à l'aide de webmagic.