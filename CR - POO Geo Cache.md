# Compte rendu - Projet POO Geo Cache - Guillaume Carlier et Romain Rousseaux

## Sommaire

1. Contexte
2. Diagramme UML
3. Schéma de base de données
4. Application

## Contexte

Dans le cadre de la matière de programmation orientée objet, nous devons réaliser une application CLI de manipulation de données dans le cadre de [Geo Caches](https://www.geocaching.com/play).

Cette application doit permettre de manipuler et de tester l'accès aux données d'une part sous une base de données MYSQL mais aussi sous une base de données MongoDB. Le changement doit pouvoir s'effectuer rapidement et simplement.

## Diagramme UML

A la lecture du [sujet](https://docs.google.com/document/d/1R0VKU8B_MBwd9IO3zw2xZxOqlmGzq44UGww0qH3eRUc/edit), on peut faire ressortir 4 grandes entités pour notre modèle :

- Les **lieux**, comprenant des données géographiques sur le lieu de la cache
- Les **caches**, comprenant toutes les informations sur les caches en elles-mêmes (description, type, nature, propriétaire ...)
- Les **utilisateurs**, comprenant les informations propres aux personnes telles que leur pseudo, une description ou encore une photo
- Les **visites**, comprenant une photo, une date, un commentaire ainsi qu'une information sur la découverte possible de la cache

Afin de compléter notre modèle, nous allons créer une entité pour représenter l'ensemble des **types** de nos caches pour des soucis de scalabilité.

On obtient alors un diagramme UML comme suit :

<img src="D:\Images\Capture.PNG" alt="Capture" style="zoom:80%;" />

## Schéma de base de données

Ainsi, nous avons pu créer notre base de données MYSQL comme suit : <img src="C:\Users\cyroc\AppData\Roaming\Typora\typora-user-images\image-20210204101945273.png" alt="image-20210204101945273" style="zoom:80%;" />



