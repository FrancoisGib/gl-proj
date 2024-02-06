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
49 développeurs ont travaillé sur WebMagic mais on trouve principalement **code4craft** qui a développé et coordonné la plupart du projet.
Le projet a été créé en 2013 et son pic d'activité était en 2014, le projet a été ralenti durant 3 ans et a été beaucoup refactor en 2017, mais la plupart des fonctionnalités ont été implémentées avant 2017. Depuis 2017, le projet a été repris par **Sutra** qui prend la place de code4craft.