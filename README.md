# Application Search Repositories on GitHub

## Présentation

Cette application affiche la liste des repositories GitHub filtrés par une query rentrée par l'utilisateur.
La query permet d'effectuer la recherche dans le nom, la description ou le README.

## Prérequis

* Installation d'Android Studio
* Récupération de l'api

```
https://api.github.com/search/repositories
```
## Consignes respectées

* Langage Kotlin
* Architecture MVVM
* Clean Architecture
* Utlisation d'une BDD
* Appel WebService à une API Rest
* Affichage d'une liste

## Fonctionnalités

### Premier écran

* Menu affichant l'écran de login avec le logo, possibilité de se connecter ou de se créer un compte
* Si le compte exite alors un message de confirmation s'affiche, en cliquant sur ok on est redirigé vers le troisième écran
* Si le compte n'exite pas, un message d'erreur s'affiche


<img src = "images/first_layout.png" width="300"> <img src = "images/connect_valid.png" width="300"> <img src = "images/account_unknown.png" width="300">


### Deuxième écran

* Écran de création de compte
* Si le compte est correctement créé, un message de confirmation de la création du compte s'affiche et en cliquant sur ok on est redirigé vers le premier écran
* Si l'email ou le mot de passe n'est pas renseigné un message d'erreur s'affiche

<img src = "images/create_account.png" width="300"> <img src = "images/create_account_valid.png" width="300"> 

<img src = "images/email_empty.png" width="300"> <img src = "images/password_empty.png" width="300">

### Troisième écran

* Affiche la liste des répertoires GitHub en fonction de la query rentré par l'utilisateur. Par exemple s'il l'on rentre food, on recherche alors tous les repositories avec le mot "food" dans le nom, la description ou le README.

<img src = "images/app_exemple1.png" width="300"> <img src = "images/app_exemple2.png" width="300">


