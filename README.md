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

### Étape 5 — Observer le self-healing

Objectif : vérifier que Kubernetes recrée automatiquement un Pod supprimé pour conserver l'état désiré (`replicas: 3`).

Processus observé en direct :

```bash
# Terminal 1
kubectl get pods -l app=boutique -w

# Terminal 2
kubectl get pods -l app=boutique
kubectl delete pod <nom-du-pod>
```

Suppression effectuée :
- Pod supprimé : `boutique-87fcc88b8-5fd8m`

Transitions vues avec `kubectl get pods -w` :
- `Terminating` pour le Pod supprimé
- apparition d'un nouveau Pod `boutique-87fcc88b8-hzgvl`
- passage `Pending` -> `ContainerCreating` -> `Running`

Continuité de service pendant la panne :
- URL testée : `http://127.0.0.1:52270`
- 6 requêtes consécutives pendant la reconstruction :
    - `STATUS=200`
    - `BODY=TP-JUNIT running`

État final :
- 3 Pods à nouveau en `Running` (dont un Pod récemment créé, plus jeune)

Checklist étape 5 :
- [x] Lancer `kubectl get pods -w` dans un terminal
- [x] Supprimer un Pod avec `kubectl delete pod <nom>`
- [x] Observer le nouveau Pod créé automatiquement en < 10 secondes
- [x] Confirmer que l'application répond toujours pendant la panne

### Étape 6 — Scaler le Deployment

Objectif : ajuster rapidement le nombre de réplicas, puis conserver un état durable via le manifest.

#### 6.1 — Scaling manuel

Commandes exécutées :

```bash
kubectl scale deployment boutique --replicas=5
kubectl get pods -l app=boutique
kubectl get deployment boutique

kubectl scale deployment boutique --replicas=2
kubectl get pods -l app=boutique
kubectl get deployment boutique
```

Résultat observé :
- Scale up vers 5 : `READY 5/5`, 5 Pods en `Running`
- Scale down vers 2 : `READY 2/2`, puis Pods en `Terminating` avant stabilisation

#### 6.2 — Scaling via le manifest (bonne pratique)

Action effectuée :
- modification de `k8s/deployment.yml` : `replicas: 3` -> `replicas: 4`
- application du manifest :

```bash
kubectl apply -f k8s/deployment.yml
kubectl get pods -l app=boutique
kubectl get deployment boutique
```

Résultat observé :
- convergence vers `READY 4/4`
- 4 Pods en `Running`

Checklist étape 6 :
- [x] `kubectl scale deployment boutique --replicas=5` -> 5 Pods Running
- [x] `kubectl scale deployment boutique --replicas=2` -> 2 Pods Running
- [x] Modifier `replicas` dans `deployment.yml` -> `kubectl apply` -> 4 Pods Running

### Étape 7 — Rolling update et rollback

Objectif : mettre à jour l'image sans coupure, puis revenir rapidement à la version précédente en cas de problème.

#### 7.1 — Simulation d'une nouvelle version

Actions effectuées :
- build local d'une image `ghcr.io/sofiane-cheriette/tp-junit:v2.0.0` dans Minikube
- mise à jour temporaire de l'image dans le Deployment
- application du manifest et suivi du rollout

Commandes exécutées :

```bash
kubectl apply -f k8s/deployment.yml
kubectl rollout status deployment/boutique
kubectl get pods -l app=boutique -w
```

Observation du rolling update :
- anciens Pods en `Terminating`
- nouveaux Pods en `Pending` puis `ContainerCreating`
- nouveaux Pods en `Running`
- déploiement terminé avec succès (`successfully rolled out`)

#### 7.2 — Historique des déploiements

Commandes exécutées :

```bash
kubectl rollout history deployment/boutique
kubectl rollout history deployment/boutique --revision=5
```

Résultat observé :
- historique avec plusieurs révisions (au moins 2, condition du TP validée)
- détail d'une révision consulté avec succès

#### 7.3 — Rollback

Commandes exécutées :

```bash
kubectl rollout undo deployment/boutique
kubectl rollout status deployment/boutique
kubectl describe deployment boutique | findstr /I "Image:"
```

Résultat observé :
- rollback effectué avec succès
- retour confirmé sur l'image précédente : `ghcr.io/sofiane-cheriette/tp-junit:latest`
- état final stable : 4 Pods `Running`

Checklist étape 7 :
- [x] Mettre à jour l'image et `kubectl apply`
- [x] `kubectl rollout status` -> déploiement sans coupure observé
- [x] `kubectl rollout history` -> au moins 2 révisions visibles
- [x] `kubectl rollout undo` -> retour à l'ancienne version
- [x] `kubectl describe deployment ...` -> ancienne image confirmée

Bonnes pratiques :
- éviter `:latest` en production
- préférer des tags versionnés (`v1.2.0`) ou SHA Git pour tracer précisément la version déployée

### Étape 8 — Déploiement complet en une commande

Objectif : supprimer puis redéployer toutes les ressources Kubernetes d'un coup à partir du dossier `k8s/`.

Préparation :
- manifest `k8s/deployment.yml` réaligné à `replicas: 3` pour correspondre à la checklist de l'étape

Commandes exécutées :

```bash
kubectl delete -f k8s/
kubectl apply -f k8s/
kubectl rollout status deployment/boutique
kubectl get all
minikube service boutique-svc --url
```

Résultat observé :
- suppression globale réussie : ConfigMap, Secret, Deployment, Service
- recréation globale réussie en une commande :
    - `configmap/boutique-config created`
    - `secret/boutique-secret created`
    - `deployment.apps/boutique created`
    - `service/boutique-svc created`
- `kubectl get all` :
    - 3 Pods `Running`
    - 1 Service `NodePort` (`boutique-svc`)
    - 1 Deployment `3/3 Available`
    - 1 ReplicaSet avec `3 desired`

Vérification finale d'accessibilité (refaite avant finalisation) :
- URL : `http://127.0.0.1:56478`
- HTTP `200`
- réponse : `TP-JUNIT running`

Checklist étape 8 :
- [x] `kubectl delete -f k8s/` -> toutes les ressources supprimées
- [x] `kubectl apply -f k8s/` -> tout recréé en une commande
- [x] `kubectl get all` -> 3 Pods Running + 1 Service + 1 Deployment
- [x] `minikube service boutique-svc --url` -> application accessible

### Étape 9 — Bonus (aller plus loin)

Objectif : explorer des fonctionnalités avancées Kubernetes (santé, autoscaling, CI/CD).

#### 9.1 — Liveness et Readiness probes

Actions effectuées :
- ajout de `livenessProbe` et `readinessProbe` dans `k8s/deployment.yml`
- application du deployment et vérification via `kubectl describe deployment boutique`

Résultat observé :
- rollout réussi
- probes visibles et actives :
    - liveness HTTP sur `/` port `8080`
    - readiness HTTP sur `/` port `8080`

Note : l'énoncé proposait des endpoints `/actuator/...`; l'application TP expose actuellement `/`, donc les probes ont été adaptées pour rester fonctionnelles.

#### 9.2 — Autoscaling horizontal (HPA)

Commande exécutée :

```bash
kubectl autoscale deployment boutique --min=2 --max=8 --cpu-percent=70
kubectl get hpa
```

Résultat observé :
- HPA `boutique` créé
- bornes min/max correctes (`2` / `8`)
- métrique CPU affichée en `<unknown>/70%` (normal si les métriques CPU ne sont pas encore disponibles immédiatement)

#### 9.3 — Intégration CI/CD

Action effectuée :
- ajout d'un job `deploy` dans `.github/workflows/ci.yml` après `build-push`

Le job :
- checkout du repo
- setup `kubectl`
- lecture du secret GitHub `KUBECONFIG`
- `kubectl apply -f k8s/` avec kubeconfig injecté

Checklist étape 9 :
- [x] Probes de santé ajoutées au Deployment
- [x] HPA configuré (`min=2`, `max=8`, `cpu=70%`)
- [x] Job `deploy` ajouté dans le pipeline CI/CD