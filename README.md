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

Étape 7 — Tests paramétrés — codes de réduction
- classe PanierReductionTest créée
- @ParameterizedTest + @CsvSource : sans code, REDUC10, REDUC20 testés

Étape 8 — Suite de tests
- dépendance junit-platform-suite ajoutée au pom.xml
- classe SuiteBoutique créée avec @Suite et @SelectClasses
- regroupe ArticleTest, PanierTest, PanierReductionTest, ServiceCommandeTest

Tests exécutés (mvn test)

ArticleTest
- creerArticleValideDoitFonctionner
- modifierPrixDoitMettreAJourLePrix
- referenceNulleDoitLeverException
- nomVideDoitLeverException
- prixNegatifALaCreationDoitLeverException
- setPrixNegatifDoitLeverException

PanierTest
- ajouterArticleDoitAugmenterLeNombreDeArticles
- calculerTotalDoitRetournerLaSommeDesSousTotaux
- panierVideDoitRetournerEstVideEgalTrue
- panierAvecArticlesNeDoitPasEtreVide
- articleNulDoitLeverException
- quantiteNulleDoitLeverException
- quantiteNegativeDoitLeverException
- codeReductionVideDoitLeverException
- codeReductionNulDoitLeverException
- quantiteUneDoitEtreAcceptee
- articleGratuitDoitEtreAccepte
- prixEleveDoitFonctionner
- panierAvecUnSeulArticleDoitFonctionner
- plusieursArticlesDifferentsDansPanier

PanierReductionTest
- calculerTotalAvecCodeReductionDoitAppliquerLaBonneRemise [sans code → 100.0]
- calculerTotalAvecCodeReductionDoitAppliquerLaBonneRemise [REDUC10 → 90.0]
- calculerTotalAvecCodeReductionDoitAppliquerLaBonneRemise [REDUC20 → 80.0]
- calculerTotalAvecPlusieursArticlesDoitAppliquerLaBonneRemise [sans code → 120.0]
- calculerTotalAvecPlusieursArticlesDoitAppliquerLaBonneRemise [REDUC10 → 108.0]
- calculerTotalAvecPlusieursArticlesDoitAppliquerLaBonneRemise [REDUC20 → 96.0]

ServiceCommandeTest
- commandeValideDoitRetournerUneCommande
- panierVideDoitLeverIllegalStateException
- identifiantClientNulDoitLeverException
- identifiantClientVideDoitLeverException
- stockInsuffisantDoitLeverStockInsuffisantException
- totalCommandeDoitCorrespondreAuTotalDuPanier

il y a aussi : 

docker images | findstr boutique

docker run --rm -v "${PWD}:/project" -w /project maven:3.9-eclipse-temurin-17 mvn test

mvn test

## 8 Récapitulatif — Checklist finale

Vérifiez que chaque étape est validée avant de rendre le TP.

### Dockerfile
- [x] Dockerfile créé à la racine avec build multi-stage
- [x] .dockerignore créé (target/, .git, .env…)
- [x] docker build -t boutique:local . → BUILD SUCCESS
- [x] docker run -d -p 8080:8080 boutique:local → conteneur démarre

### pom.xml
- [x] junit-jupiter présent dans les dépendances
- [x] maven-surefire-plugin présent dans les plugins
- [x] mvn test en local → BUILD SUCCESS, tous les tests verts

### GitHub Actions — ci.yml
- [x] Dossier .github/workflows/ créé
- [x] Fichier ci.yml présent avec jobs test et build-push
- [x] git push → pipeline déclenché automatiquement
- [x] Job test → Tests run: N, Failures: 0
- [x] Job build-push → image poussée sur ghcr.io
- [x] GitHub → Packages → image boutique visible

### Bonus — release.yml
- [x] Fichier release.yml créé
- [x] git tag v1.0.0 + git push origin v1.0.0
- [x] Image ghcr.io/user/boutique:v1.0.0 publiée

## Commandes

```bash
mvn test
mvn -Dtest=PanierTest test
mvn -Dtest=ArticleTest test

docker build -t boutique:local .
docker run -d -p 8080:8080 boutique:local
docker images | findstr boutique
docker run --rm -v "${PWD}:/project" -w /project maven:3.9-eclipse-temurin-17 mvn test

git push
git tag v1.0.0
git push origin v1.0.0
```

## TP — Kubernetes avec Minikube

### Étape 0 — Structure du projet après le TP

Vous ajoutez un dossier `k8s/` à la racine du projet Boutique.
Tous les manifests Kubernetes vivent dans ce dossier et sont versionnés avec le code.

Structure cible :

```text
boutique/
├── .github/workflows/
├── src/                     (inchangé)
├── Dockerfile              (inchangé)
├── pom.xml                 (inchangé)
├── k8s/                    (NOUVEAU)
│   ├── deployment.yml      (NOUVEAU)
│   ├── service.yml         (NOUVEAU)
│   ├── configmap.yml       (NOUVEAU)
│   └── secret.yml          (NOUVEAU)
└── pom.xml
```

Règle de l'étape 0 :
- Les fichiers en vert dans l'énoncé sont ceux à créer.
- Les fichiers existants du projet ne sont pas modifiés.

État dans ce dépôt :
- [x] Dossier `k8s/` créé
- [x] `k8s/deployment.yml` créé
- [x] `k8s/service.yml` créé
- [x] `k8s/configmap.yml` créé
- [x] `k8s/secret.yml` créé

### Étape 1 — Installer et démarrer Minikube

Objectif : disposer d'un cluster Kubernetes local prêt pour les prochaines étapes du TP.

Commandes exécutées :

```bash
minikube version
kubectl version --client
minikube start --driver=docker
kubectl get nodes
minikube status
```

Résultat obtenu :
- `minikube version` : OK (v1.38.1)
- `kubectl version --client` : OK (v1.28.2)
- `minikube start --driver=docker` : OK, cluster démarré
- `kubectl get nodes` : nœud `minikube` en statut `Ready`
- `minikube status` : `host/kubelet/apiserver` en `Running`, `kubeconfig` configuré

Checklist étape 1 :
- [x] `minikube start` démarre sans erreur bloquante
- [x] `kubectl get nodes` affiche `STATUS = Ready`

### Étape 2 — Créer le ConfigMap et le Secret

Objectif : externaliser la configuration de l'application hors de l'image Docker.

Ressources créées :
- `k8s/configmap.yml` avec des variables non sensibles
- `k8s/secret.yml` avec des valeurs sensibles encodées en base64 (valeurs de test)

Commandes exécutées :

```bash
kubectl apply -f k8s/configmap.yml
kubectl apply -f k8s/secret.yml
kubectl get configmap boutique-config
kubectl get secret boutique-secret
```

Résultat obtenu :
- `configmap/boutique-config created`
- `secret/boutique-secret created`
- `kubectl get configmap boutique-config` : objet présent (`DATA = 3`)
- `kubectl get secret boutique-secret` : objet présent (`TYPE = Opaque`, `DATA = 2`)

Checklist étape 2 :
- [x] Créer `k8s/configmap.yml` avec au moins 3 variables
- [x] Encoder les valeurs sensibles en base64
- [x] Créer `k8s/secret.yml` avec `DB_USER` et `DB_PASSWORD`
- [x] `kubectl get configmap boutique-config` -> l'objet existe
- [x] `kubectl get secret boutique-secret` -> l'objet existe

Note sécurité :
- Le fichier `k8s/secret.yml` contient des valeurs de test uniquement (pas de vrais secrets).

### Étape 3 — Créer le Deployment

Objectif : déployer l'application avec 3 Pods, image GHCR, configuration injectée via ConfigMap/Secret.

Manifest créé :
- `k8s/deployment.yml`
- `replicas: 3`
- image: `ghcr.io/sofiane-cheriette/tp-junit:latest`
- injection de `boutique-config` et `boutique-secret` via `envFrom`
- ressources CPU/RAM (requests + limits)

Commandes exécutées :

```bash
kubectl apply -f k8s/deployment.yml
kubectl rollout status deployment/boutique
kubectl get pods -l app=boutique
kubectl describe pod <nom-du-pod>
kubectl logs <nom-du-pod>
```

Résultat obtenu :
- Déploiement créé puis mis à jour avec succès
- `kubectl rollout status` : `deployment "boutique" successfully rolled out`
- 3 Pods `Running` (1/1)
- logs applicatifs OK : `Serveur demarre sur le port 8080`

Incident rencontré puis corrigé :
- Erreur initiale : `Could not find or load main class fr.boutique.Application`
- Correction appliquée pour la validation locale Minikube :
    - construction de l'image dans Minikube avec le code courant
    - démarrage explicite de la classe principale dans le container

Checklist étape 3 :
- [x] Créer `k8s/deployment.yml` avec `replicas: 3` et l'URL de l'image
- [x] `kubectl apply -f k8s/deployment.yml`
- [x] `kubectl rollout status` -> deployment successfully rolled out
- [x] `kubectl get pods` -> 3/3 pods en Running
- [x] `kubectl logs <pod>` -> application démarrée sans erreur

### Étape 4 — Exposer l'application avec un Service

Objectif : fournir un point d'accès stable aux Pods via un Service Kubernetes.

Manifest créé :
- `k8s/service.yml`
- `type: NodePort`
- `port: 80` vers `targetPort: 8080`
- `selector: app: boutique`

Commandes exécutées :

```bash
kubectl apply -f k8s/service.yml
kubectl get service boutique-svc
minikube service boutique-svc --url
```

Résultat obtenu :
- `service/boutique-svc created`
- `kubectl get service boutique-svc` : Service présent en `NodePort`
- URL Minikube affichée et testée : `http://127.0.0.1:64617`
- Réponse HTTP OK : `TP-JUNIT running` (status 200)

Checklist étape 4 :
- [x] Créer `k8s/service.yml` de type `NodePort`
- [x] `kubectl apply -f k8s/service.yml`
- [x] `kubectl get service boutique-svc` -> le Service existe
- [x] `minikube service boutique-svc --url` -> URL affichée
- [x] Accéder à l'URL -> réponse de l'application