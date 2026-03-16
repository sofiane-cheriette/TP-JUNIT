TP — Tests unitaires avec JUnit 5
Application Boutique en ligne · Panier & ServiceCommande · JUnit 5

Objectifs du TP
À la fin du TP, vous devez être capables de :
    • écrire des tests unitaires avec JUnit 5
    • structurer un test avec AAA (Arranger · Agir · Affirmer)
    • identifier des cas normaux, limites et invalides
    • utiliser des tests paramétrés avec @TestParametre
    • regrouper des tests dans une suite de tests

Contexte
Vous travaillez sur une plateforme de boutique en ligne contenant trois classes métier :
    • Panier — panier d'achat avec articles et codes de réduction
    • Article — article du catalogue avec référence, nom et prix
    • ServiceCommande — service de validation et création de commandes


    Pour lancer tout les test, on peut faire :

    mvn test

    ou bien on peut lancer qu'une classe à la fois comme par exemple :

    mvn -Dtest=PanierTest test          ou          mvn -Dtest=ArticleTest test

Avancement
Étape 0 — Initialisation GitHub
- Dépôt local relié au dépôt distant
- Premier commit et push effectués

Étape 1 — Structure Maven + classes métier
- pom.xml créé avec JUnit 5
- classes métier ajoutées selon l'énoncé (sans tests)

Étape 1 bis — Alignement avec l'organisation attendue
- classes déplacées dans src/main/java/fr/boutique 
- package fr.boutique ajouté

Étape 2 — Premiers tests unitaires sur Panier
- classe PanierTest créée
- 4 tests valides de base ajoutés

Étape 3 — Tests sur la classe Article
- classe ArticleTest créée
- cas valides et invalides de base ajoutés

Étape 4 — Cas invalides sur Panier
- exceptions sur article null et quantités invalides ajoutées
- exceptions sur codes de réduction invalides ajoutées

Étape 5 — Cas limites sur Panier
- quantité minimale acceptée testée
- article gratuit et prix élevé testés
- panier avec un seul article et plusieurs articles différents testés

Étape 6 — Tests sur ServiceCommande
- classe ServiceCommandeTest créée
- commande valide, panier vide, client invalide testés
- stock insuffisant et total de commande testés