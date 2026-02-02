# TaskFlow - TP Backend Java Spring Boot

## 📋 Description du projet

**TaskFlow** est une application de gestion de tâches (To-Do List) développée dans le cadre d'une révision approfondie du développement backend Java avec Spring Boot. Ce projet servira de base pour une future application frontend Angular.

L'objectif est de créer une API REST complète, sécurisée et professionnelle en suivant les bonnes pratiques de développement backend.

---

## 🎯 Objectifs pédagogiques

Ce TP vous permettra de réviser et de maîtriser :

- ✅ Architecture en couches (Controller, Service, Repository)
- ✅ Spring Boot et son écosystème
- ✅ Spring Data JPA et Hibernate
- ✅ API REST et principes RESTful
- ✅ Validation des données
- ✅ Gestion des exceptions
- ✅ Sécurité avec Spring Security et JWT
- ✅ Relations entre entités (OneToMany, ManyToOne, ManyToMany)
- ✅ Configuration et propriétés
- ✅ Tests unitaires et d'intégration

---

## 🛠️ Stack technique

- **Java** : 21
- **Spring Boot** : 3.5.10
- **Spring Data JPA** : Gestion de la persistance
- **Spring Security** : Authentification et autorisation
- **Spring Validation** : Validation des données
- **PostgreSQL** : Base de données (ou MySQL/H2 pour les tests)
- **Maven** : Gestionnaire de dépendances
- **JWT** : Authentification par token

### 🆕 Nouveautés de Java 21 à exploiter

Java 21 est une version LTS (Long Term Support) qui apporte de nombreuses fonctionnalités modernes :

- **Virtual Threads** : Pour améliorer la concurrence
- **Record Patterns** : Simplification du pattern matching
- **Pattern Matching for switch** : Switch expressions améliorées
- **Sealed Classes** : Hiérarchies de types fermées et exhaustives
- **String Templates** (Preview) : Interpolation de chaînes
- **Sequenced Collections** : Nouvelles interfaces pour les collections ordonnées

**Fonctionnalités à utiliser dans le projet :**

**1. Sealed Classes pour le domain modeling**
- Alternative moderne aux enums
- Pattern matching exhaustif vérifié à la compilation
- Plus flexible pour modéliser des domaines métiers complexes

**2. Records pour les DTOs**
- Syntaxe concise pour les objets de transfert de données
- Immutabilité automatique
- Génération automatique des méthodes equals(), hashCode(), toString()

**3. Pattern matching for switch**
- Switch expressions avec vérification d'exhaustivité
- Le compilateur garantit que tous les cas sont gérés
- Parfait avec les sealed classes

**Avantages des Sealed Classes sur les Enums :**
- Chaque variante peut avoir ses propres propriétés et méthodes
- Pattern matching exhaustif vérifié à la compilation
- Plus flexible pour modéliser des domaines métiers complexes
- Meilleure intégration avec les Records

---

## 📁 Structure du projet

```
src/main/java/com/taskflow/
│
├── config/                  # Configurations
│   ├── SecurityConfig.java
│   ├── CorsConfig.java
│   └── JwtConfig.java
│
├── controller/              # Endpoints REST
│   ├── AuthController.java
│   ├── TaskController.java
│   ├── ProjectController.java
│   └── UserController.java
│
├── dto/                     # Data Transfer Objects
│   ├── request/
│   │   ├── LoginRequest.java
│   │   ├── RegisterRequest.java
│   │   ├── TaskRequest.java
│   │   └── ProjectRequest.java
│   └── response/
│       ├── AuthResponse.java
│       ├── TaskResponse.java
│       └── ProjectResponse.java
│
├── entity/                  # Entités JPA
│   ├── User.java
│   ├── Task.java
│   ├── Project.java
│   ├── Category.java
│   ├── role/
│   │   ├── Role.java (sealed interface)
│   │   ├── UserRole.java (record)
│   │   └── AdminRole.java (record)
│   ├── status/
│   │   ├── TaskStatus.java (sealed interface)
│   │   ├── Todo.java (record)
│   │   ├── InProgress.java (record)
│   │   └── Done.java (record)
│   └── priority/
│       ├── TaskPriority.java (sealed interface)
│       ├── Low.java (record)
│       ├── Medium.java (record)
│       ├── High.java (record)
│       └── Urgent.java (record)
│
├── repository/              # Repositories Spring Data
│   ├── UserRepository.java
│   ├── TaskRepository.java
│   ├── ProjectRepository.java
│   └── CategoryRepository.java
│
├── service/                 # Logique métier
│   ├── AuthService.java
│   ├── TaskService.java
│   ├── ProjectService.java
│   ├── UserService.java
│   └── JwtService.java
│
├── exception/               # Gestion des exceptions
│   ├── GlobalExceptionHandler.java
│   ├── ResourceNotFoundException.java
│   ├── BadRequestException.java
│   └── UnauthorizedException.java
│
└── security/                # Sécurité
    ├── JwtAuthenticationFilter.java
    ├── UserDetailsServiceImpl.java
    └── JwtTokenProvider.java
```

---

## 📚 Progression du TP

### **Phase 1 : Setup et configuration (Niveau débutant)**

#### Étape 1.1 : Configuration initiale
- [ ] Configurer `application.properties` ou `application.yml`
- [ ] Configurer la connexion à PostgreSQL
- [ ] Ajouter les dépendances Maven nécessaires

**Dépendances requises :**
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Validation
- PostgreSQL Driver
- Spring Boot Starter Security
- JWT (io.jsonwebtoken)
- Spring Boot Starter Test

**Ressources à consulter :**
- [Spring Boot Application Properties](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html)
- [Configuration PostgreSQL avec Spring Boot](https://www.baeldung.com/spring-boot-postgresql-docker)
- [Maven Dependencies Management](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html)
- [Spring Boot Starters Guide](https://www.baeldung.com/spring-boot-starters)

#### Étape 1.2 : Création des types avec Sealed Classes (Java 21)
- [ ] Créer la sealed class `Role` avec les implémentations : UserRole, AdminRole
- [ ] Créer la sealed class `TaskStatus` avec les implémentations : Todo, InProgress, Done
- [ ] Créer la sealed class `TaskPriority` avec les implémentations : Low, Medium, High, Urgent

**Pourquoi les Sealed Classes plutôt que les Enums ?**
- Plus de flexibilité : chaque variante peut avoir ses propres propriétés et méthodes
- Pattern matching exhaustif garanti par le compilateur
- Meilleure expressivité du domaine métier
- Possibilité d'ajouter des comportements spécifiques

**Options d'implémentation :**
1. **Sealed Class simple** : Interface scellée avec records simples
2. **Sealed Class avec données** : Records avec propriétés et méthodes métier
3. **Enum classique** : Solution plus simple pour débuter

**Recommandation :** Commencez avec les enums pour la simplicité, puis refactorez vers les sealed classes une fois à l'aise avec les concepts de base.

**Ressources à consulter :**
- [Java Sealed Classes Official Guide](https://docs.oracle.com/en/java/javase/21/language/sealed-classes-and-interfaces.html)
- [Sealed Classes Tutorial - Baeldung](https://www.baeldung.com/java-sealed-classes-interfaces)
- [Pattern Matching with Sealed Classes](https://www.baeldung.com/java-pattern-matching-sealed-classes)
- [Java Enums - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html)
- [Records vs Classes - When to Use What](https://www.baeldung.com/java-record-vs-final-class)

#### Étape 1.3 : Création des entités de base
- [ ] Créer l'entité `User` avec les champs : id, username, email, password, role, createdAt
- [ ] Créer l'entité `Task` avec les champs : id, title, description, status, priority, dueDate, createdAt, updatedAt
- [ ] Ajouter les annotations JPA appropriées (@Entity, @Id, @GeneratedValue, etc.)

**Annotations JPA essentielles à utiliser :**
- @Entity
- @Table
- @Id
- @GeneratedValue
- @Column
- @Enumerated (si vous utilisez des enums)
- @CreationTimestamp (pour createdAt)
- @UpdateTimestamp (pour updatedAt)

**Alternatives pour les timestamps :**
- **Option 1 (Recommandée)** : `@CreationTimestamp` et `@UpdateTimestamp` (annotations Hibernate)
- **Option 2** : `@PrePersist` et `@PreUpdate` (callbacks JPA)
- **Option 3** : Spring Data JPA Auditing avec `@CreatedDate` et `@LastModifiedDate`

**Options pour stocker les sealed classes en base :**
- Stockage du label sous forme de String
- Utilisation de convertisseurs JPA personnalisés
- Utilisation d'enums avec @Enumerated

**Points à réviser :**
- Annotations JPA de base (@Entity, @Table, @Column)
- Types de génération d'ID (@GeneratedValue)
- Gestion des timestamps (Hibernate vs JPA vs Spring Data)
- Sealed classes vs Enums : avantages et inconvénients
- Pattern matching avec sealed classes
- Persistance JPA : stockage de types personnalisés

**Ressources à consulter :**
- [JPA Entity Annotations - Baeldung](https://www.baeldung.com/jpa-entities)
- [JPA @GeneratedValue Strategies](https://www.baeldung.com/jpa-strategies-when-id-null)
- [Hibernate @CreationTimestamp and @UpdateTimestamp](https://www.baeldung.com/hibernate-creationtimestamp-updatetimestamp)
- [JPA Lifecycle Callbacks (@PreUpdate, @PrePersist)](https://www.baeldung.com/jpa-entity-lifecycle-events)
- [Spring Data JPA Auditing](https://www.baeldung.com/database-auditing-jpa)
- [JPA @Enumerated Annotation](https://www.baeldung.com/jpa-persisting-enums-in-jpa)
- [Mapping Custom Types in JPA](https://thorben-janssen.com/jpa-21-type-converter-better-way-to/)
- [Hibernate Official Documentation](https://hibernate.org/orm/documentation/6.0/)

---

### 📘 Annexe Phase 1 : Gestion des Timestamps - @CreationTimestamp vs @PrePersist

#### Comprendre les différences

Il existe plusieurs approches pour gérer automatiquement les dates de création et de mise à jour dans vos entités JPA. Voici les principales options :

**Option 1 : @CreationTimestamp et @UpdateTimestamp (Hibernate)**

- Annotations spécifiques à **Hibernate** (pas JPA standard)
- Gestion **automatique** des timestamps par Hibernate
- **Zéro code** : Hibernate gère tout automatiquement
- **Simple et déclaratif** : Une seule annotation suffit
- **Recommandé** pour des cas simples de timestamps

**Avantages :**
- Très simple à utiliser
- Aucun risque d'oubli
- Pas de logique métier dans l'entité
- Code minimal

**Inconvénients :**
- Dépendance à Hibernate (ne marche pas avec d'autres implémentations JPA)
- Moins de contrôle sur la logique

---

**Option 2 : @PrePersist et @PreUpdate (JPA standard)**

- Annotations **JPA standard** (partie de la spécification JPA)
- **Callbacks** de lifecycle : méthodes appelées automatiquement avant insert/update
- **Flexibilité totale** : Vous pouvez ajouter n'importe quelle logique
- **Portable** : Fonctionne avec toutes les implémentations JPA

**Avantages :**
- JPA standard (portable entre Hibernate, EclipseLink, etc.)
- Contrôle total sur la logique
- Possibilité d'ajouter de la validation ou des calculs
- Flexibilité pour des besoins complexes

**Inconvénients :**
- Plus verbeux (nécessite d'écrire des méthodes)
- Code boilerplate
- Risque d'erreur si mal implémenté

---

**Option 3 : Spring Data JPA Auditing**

- Utilisation de `@CreatedDate` et `@LastModifiedDate`
- Nécessite l'activation de l'auditing avec `@EnableJpaAuditing`
- Peut aussi tracker l'utilisateur qui a créé/modifié (`@CreatedBy`, `@LastModifiedBy`)

---

#### Tableau comparatif

| Critère | @CreationTimestamp / @UpdateTimestamp | @PrePersist / @PreUpdate | Spring Data Auditing |
|---------|--------------------------------------|--------------------------|---------------------|
| **Standard** | Hibernate uniquement | JPA standard | Spring Data |
| **Complexité** | Très simple | Plus verbeux | Moyenne |
| **Flexibilité** | Limitée aux timestamps | Totale | Timestamps + audit utilisateur |
| **Portabilité** | Hibernate seulement | Tous les fournisseurs JPA | Spring uniquement |
| **Configuration** | Aucune | Aucune | Requires @EnableJpaAuditing |

---

#### Quand utiliser quoi ?

**Utilisez @CreationTimestamp / @UpdateTimestamp si :**
- Vous utilisez Hibernate (cas de Spring Boot par défaut)
- Vous voulez juste gérer des timestamps simples
- Vous préférez la simplicité et le code minimal
- **→ C'EST LE CAS POUR TASKFLOW**

**Utilisez @PrePersist / @PreUpdate si :**
- Vous devez être indépendant de Hibernate
- Vous avez besoin de logique métier avant la sauvegarde (ex: générer un slug, calculer une valeur, valider des règles)
- Vous voulez faire plus que juste des timestamps

**Utilisez Spring Data Auditing si :**
- Vous voulez tracker qui a créé/modifié les entités
- Vous avez besoin d'un système d'audit complet
- Vous utilisez Spring Data JPA

---

#### Exemples de cas d'usage pour @PrePersist/@PreUpdate

Voici des situations où les callbacks JPA sont plus appropriés :

**Cas 1 : Génération automatique de slug**
```
Avant sauvegarde : générer un slug URL-friendly depuis le titre
Exemple : "Ma Tâche Importante" → "ma-tache-importante"
```

**Cas 2 : Validation métier**
```
Vérifier des règles métier avant l'insertion
Exemple : S'assurer qu'une tâche urgente a toujours une date d'échéance
```

**Cas 3 : Calculs automatiques**
```
Calculer des valeurs dérivées
Exemple : Calculer le nombre de jours restants avant l'échéance
```

**Cas 4 : Initialisation de valeurs par défaut**
```
Définir des valeurs par défaut complexes
Exemple : Assigner automatiquement un projet par défaut si aucun n'est spécifié
```

---

#### Recommandation pour TaskFlow

Pour votre projet **TaskFlow**, utilisez **@CreationTimestamp** et **@UpdateTimestamp** car :

1. ✅ Vous utilisez Spring Boot avec Hibernate
2. ✅ Vous avez juste besoin de timestamps simples
3. ✅ C'est plus propre et nécessite moins de code
4. ✅ Vous n'avez pas besoin de portabilité JPA pour ce TP

**Gardez en tête** que si plus tard vous avez besoin de logique métier plus complexe (comme les cas d'usage mentionnés ci-dessus), vous pourrez toujours migrer vers @PrePersist/@PreUpdate ou les combiner avec les annotations Hibernate.

---

### **Phase 2 : CRUD de base (Niveau débutant)**

#### Étape 2.1 : Repository Layer
- [ ] Créer `TaskRepository` extends `JpaRepository<Task, Long>`
- [ ] Créer `UserRepository` extends `JpaRepository<User, Long>`
- [ ] Ajouter des méthodes de recherche personnalisées

**Méthodes de recherche à implémenter :**
- Recherche par status
- Recherche par userId
- Recherche par email (pour User)

**Ressources à consulter :**
- [Spring Data JPA - Official Guide](https://spring.io/projects/spring-data-jpa)
- [Query Methods - Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods)
- [Derived Query Methods](https://www.baeldung.com/spring-data-derived-queries)
- [JpaRepository Methods Explained](https://www.baeldung.com/spring-data-repositories)
- [Custom Queries with @Query](https://www.baeldung.com/spring-data-jpa-query)

#### Étape 2.2 : Service Layer
- [ ] Créer `TaskService` avec les méthodes CRUD
- [ ] Implémenter la logique métier
- [ ] Gérer les exceptions (ResourceNotFoundException)

**Méthodes à implémenter :**
- `createTask(TaskRequest request)`
- `getAllTasks()`
- `getTaskById(Long id)`
- `updateTask(Long id, TaskRequest request)`
- `deleteTask(Long id)`

**Ressources à consulter :**
- [Spring Service Layer - Best Practices](https://www.baeldung.com/spring-service-layer-validation)
- [Business Logic in Spring](https://www.baeldung.com/spring-boot-business-logic)
- [Exception Handling in Spring](https://www.baeldung.com/exception-handling-for-rest-with-spring)
- [Transaction Management with @Transactional](https://www.baeldung.com/transaction-configuration-with-jpa-and-spring)
- [DTO Pattern Explained](https://www.baeldung.com/java-dto-pattern)

#### Étape 2.3 : Controller Layer
- [ ] Créer `TaskController` avec les endpoints REST
- [ ] Implémenter les méthodes HTTP (GET, POST, PUT, DELETE)
- [ ] Utiliser les bonnes annotations (@RestController, @RequestMapping, @GetMapping, etc.)

**Endpoints à créer :**
```
GET    /api/tasks           - Liste toutes les tâches
GET    /api/tasks/{id}      - Récupère une tâche par ID
POST   /api/tasks           - Crée une nouvelle tâche
PUT    /api/tasks/{id}      - Met à jour une tâche
DELETE /api/tasks/{id}      - Supprime une tâche
```

**Ressources à consulter :**
- [Building REST APIs with Spring Boot](https://spring.io/guides/tutorials/rest/)
- [Spring @RestController vs @Controller](https://www.baeldung.com/spring-controller-vs-restcontroller)
- [REST API Design Best Practices](https://www.baeldung.com/rest-api-best-practices-design)
- [HTTP Status Codes Explained](https://www.baeldung.com/spring-response-status)
- [Request Mapping Annotations](https://www.baeldung.com/spring-requestmapping)
- [ResponseEntity in Spring](https://www.baeldung.com/spring-response-entity)

---

### **Phase 3 : Validation et gestion d'erreurs (Niveau intermédiaire)**

#### Étape 3.1 : DTOs (Data Transfer Objects)
- [ ] Créer `TaskRequest` avec validation
- [ ] Créer `TaskResponse` pour les réponses
- [ ] Mapper les entités vers les DTOs (avec classes classiques ou Records Java 21)

**Options d'implémentation :**
1. **Classe classique** : Avec getters/setters manuels (sans Lombok)
2. **Record Java 21** : Recommandé pour les DTOs immuables

**Annotations de validation à utiliser :**
- @NotBlank
- @NotNull
- @Size
- @Email
- @Pattern

**Note :** Les Records sont parfaits pour les DTOs car ils sont immuables et génèrent automatiquement les getters, equals(), hashCode() et toString().

**Ressources à consulter :**
- [Bean Validation (JSR 303/380)](https://www.baeldung.com/javax-validation)
- [Validation Annotations Reference](https://www.baeldung.com/javax-validation-method-constraints)
- [Custom Validators in Spring](https://www.baeldung.com/spring-mvc-custom-validator)
- [Java Records Tutorial](https://www.baeldung.com/java-record-keyword)
- [Records with Bean Validation](https://www.baeldung.com/java-bean-validation-not-null-empty-blank)
- [DTO to Entity Mapping - ModelMapper](https://www.baeldung.com/java-modelmapper)

#### Étape 3.2 : Gestion globale des exceptions
- [ ] Créer `GlobalExceptionHandler` avec @ControllerAdvice
- [ ] Créer des exceptions personnalisées
- [ ] Retourner des réponses d'erreur structurées

**Exceptions à créer :**
- `ResourceNotFoundException`
- `BadRequestException`
- `UnauthorizedException`

**Ressources à consulter :**
- [@ControllerAdvice Guide](https://www.baeldung.com/exception-handling-for-rest-with-spring)
- [Global Exception Handling](https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc)
- [Custom Exceptions in Spring Boot](https://www.baeldung.com/spring-boot-custom-error-page)
- [Problem Details for HTTP APIs (RFC 7807)](https://www.baeldung.com/problem-spring-web)
- [ResponseEntityExceptionHandler](https://www.baeldung.com/exception-handling-for-rest-with-spring#responseentityexceptionhandler)

---

### **Phase 4 : Relations entre entités (Niveau intermédiaire)**

#### Étape 4.1 : Relation User - Task
- [ ] Ajouter la relation @ManyToOne dans Task
- [ ] Ajouter la relation @OneToMany dans User
- [ ] Configurer le cascade et le fetch type

**Ressources à consulter :**
- [JPA Relationships - Complete Guide](https://www.baeldung.com/jpa-relationships)
- [@ManyToOne and @OneToMany](https://www.baeldung.com/hibernate-one-to-many)
- [Cascade Types Explained](https://www.baeldung.com/jpa-cascade-types)
- [FetchType LAZY vs EAGER](https://www.baeldung.com/hibernate-lazy-eager-loading)
- [Bidirectional Relationships](https://www.baeldung.com/jpa-joincolumn-vs-mappedby)

#### Étape 4.2 : Ajout de Project
- [ ] Créer l'entité `Project`
- [ ] Relation User - Project (@ManyToOne)
- [ ] Relation Project - Task (@OneToMany)

**Ressources à consulter :**
- [Multiple @OneToMany Relationships](https://www.baeldung.com/jpa-one-to-many)
- [Modeling Real-World Relationships](https://thorben-janssen.com/entity-mappings-introduction-jpa-fetchtypes/)

#### Étape 4.3 : Ajout de Category
- [ ] Créer l'entité `Category`
- [ ] Relation Task - Category (@ManyToMany)
- [ ] Créer la table de jointure

**Ressources à consulter :**
- [@ManyToMany Relationships](https://www.baeldung.com/jpa-many-to-many)
- [Join Tables and @JoinTable](https://www.baeldung.com/jpa-join-types)
- [ManyToMany Best Practices](https://thorben-janssen.com/best-practices-for-many-to-many-associations-with-hibernate-and-jpa/)
- [N+1 Query Problem and Solutions](https://www.baeldung.com/hibernate-n-plus-one-problem)

**Schéma des relations :**
```
User (1) ----< (*) Task (*) >---- (*) Category
  |
  |
  v
Project (1) ----< (*) Task
```

---

### **Phase 5 : Fonctionnalités avancées (Niveau intermédiaire)**

#### Étape 5.1 : Pagination et tri
- [ ] Utiliser `Pageable` dans les repositories
- [ ] Implémenter la pagination dans les services
- [ ] Retourner des `Page<TaskResponse>`

**Exemple d'endpoint :**
```
GET /api/tasks?page=0&size=10&sort=dueDate,desc
```

**Ressources à consulter :**
- [Pagination and Sorting with Spring Data JPA](https://www.baeldung.com/spring-data-jpa-pagination-sorting)
- [Pageable and Page Explained](https://www.baeldung.com/spring-data-web-support)
- [Custom Pagination Responses](https://www.baeldung.com/rest-api-pagination-in-spring)
- [Sorting with Multiple Criteria](https://www.baeldung.com/spring-data-sorting)

#### Étape 5.2 : Filtres et recherche
- [ ] Créer des méthodes de recherche avancée
- [ ] Filtrer par status, priorité, date
- [ ] Recherche textuelle (title, description)

**Types de méthodes à implémenter :**
- Recherche par mots-clés (ignorer la casse)
- Filtres combinés (status + priorité)
- Recherche par plage de dates

**Ressources à consulter :**
- [Query Methods Keywords](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords)
- [JPA Specifications](https://www.baeldung.com/rest-api-search-language-spring-data-specifications)
- [Dynamic Queries with Criteria API](https://www.baeldung.com/hibernate-criteria-queries)
- [Search and Filtering REST APIs](https://www.baeldung.com/rest-api-query-search-language-tutorial)
- [QueryDSL with Spring Data JPA](https://www.baeldung.com/intro-to-querydsl)

#### Étape 5.3 : Statistiques et pattern matching
- [ ] Créer un endpoint pour les statistiques utilisateur
- [ ] Compter les tâches par status
- [ ] Calculer le taux de complétion
- [ ] Utiliser le pattern matching avec les sealed classes

**Statistiques à calculer :**
- Nombre total de tâches
- Nombre de tâches par status (Todo, InProgress, Done)
- Nombre de tâches par priorité
- Taux de complétion en pourcentage

**Approches possibles :**
- Utilisation de Java Streams API
- Pattern matching avec sealed classes
- Agrégation avec Spring Data JPA

**Ressources à consulter :**
- [Java Streams API Guide](https://www.baeldung.com/java-8-streams)
- [Collectors in Java](https://www.baeldung.com/java-8-collectors)
- [Grouping and Aggregation](https://www.baeldung.com/java-groupingby-collector)
- [Pattern Matching with instanceof](https://www.baeldung.com/java-pattern-matching-instanceof)
- [Custom Aggregations in Spring Data](https://www.baeldung.com/spring-data-jpa-projections)

---

### **Phase 6 : Sécurité et authentification (Niveau avancé)**

#### Étape 6.1 : Configuration Spring Security
- [ ] Créer `SecurityConfig`
- [ ] Configurer les endpoints publics/privés
- [ ] Désactiver CSRF pour l'API REST
- [ ] Configurer CORS

**Ressources à consulter :**
- [Spring Security Architecture](https://spring.io/guides/topicals/spring-security-architecture/)
- [Spring Security with Spring Boot 3](https://www.baeldung.com/spring-boot-security-autoconfiguration)
- [SecurityFilterChain Configuration](https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter)
- [CORS Configuration](https://www.baeldung.com/spring-cors)
- [CSRF Protection Explained](https://www.baeldung.com/spring-security-csrf)
- [Method Security with @PreAuthorize](https://www.baeldung.com/spring-security-method-security)

#### Étape 6.2 : Implémentation JWT
- [ ] Créer `JwtTokenProvider` pour générer/valider les tokens
- [ ] Créer `JwtAuthenticationFilter`
- [ ] Implémenter `UserDetailsService`

**Ressources à consulter :**
- [JWT Introduction and Overview](https://jwt.io/introduction)
- [Spring Security with JWT](https://www.baeldung.com/spring-security-oauth-jwt)
- [JWT Authentication in Spring Boot](https://www.bezkoder.com/spring-boot-jwt-authentication/)
- [OncePerRequestFilter Explained](https://www.baeldung.com/spring-onceperrequestfilter)
- [UserDetailsService Custom Implementation](https://www.baeldung.com/spring-security-authentication-with-a-database)
- [JWT Best Practices](https://auth0.com/blog/a-look-at-the-latest-draft-for-jwt-bcp/)

#### Étape 6.3 : Endpoints d'authentification
- [ ] Créer `AuthController`
- [ ] Implémenter `/api/auth/register`
- [ ] Implémenter `/api/auth/login`
- [ ] Encoder les mots de passe avec BCrypt

**Fonctionnalités à implémenter :**
- Enregistrement d'un nouvel utilisateur
- Connexion avec génération de token JWT
- Validation des credentials
- Encodage sécurisé des mots de passe

**Structure recommandée du token JWT :**
- Subject (email de l'utilisateur)
- User ID
- Role
- Date de création (iat)
- Date d'expiration (exp)

**Ressources à consulter :**
- [Password Encoding with BCrypt](https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt)
- [Login and Registration REST API](https://www.baeldung.com/registration-with-spring-mvc-and-spring-security)
- [PasswordEncoder Interface](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/crypto/password/PasswordEncoder.html)
- [Authentication REST API Best Practices](https://www.baeldung.com/rest-api-authentication)

#### Étape 6.4 : Sécurisation des endpoints
- [ ] Protéger tous les endpoints `/api/tasks/**`
- [ ] Vérifier que l'utilisateur ne peut voir que ses propres tâches
- [ ] Implémenter les rôles (USER, ADMIN)

**Ressources à consulter :**
- [Role-Based Access Control (RBAC)](https://www.baeldung.com/role-and-privilege-for-spring-security-registration)
- [Method-Level Security](https://www.baeldung.com/spring-security-method-security)
- [@PreAuthorize and @PostAuthorize](https://www.baeldung.com/spring-security-expressions)
- [Securing REST APIs](https://www.baeldung.com/securing-a-restful-web-service-with-spring-security)
- [Testing Secured Endpoints](https://www.baeldung.com/spring-security-integration-tests)

---

### **Phase 7 : Tests (Niveau avancé)**

#### Étape 7.1 : Tests unitaires
- [ ] Tester les services avec Mockito
- [ ] Tester les repositories
- [ ] Atteindre une couverture de code >70%

**Ressources à consulter :**
- [Unit Testing with JUnit 5](https://www.baeldung.com/junit-5)
- [Mockito Tutorial](https://www.baeldung.com/mockito-series)
- [Testing Spring Boot Applications](https://www.baeldung.com/spring-boot-testing)
- [@Mock, @InjectMocks, @Spy Explained](https://www.baeldung.com/mockito-annotations)
- [JaCoCo Code Coverage](https://www.baeldung.com/jacoco)
- [AssertJ for Better Assertions](https://www.baeldung.com/introduction-to-assertj)

#### Étape 7.2 : Tests d'intégration
- [ ] Tester les controllers avec MockMvc
- [ ] Tester les endpoints avec @SpringBootTest
- [ ] Utiliser une base H2 en mémoire pour les tests

**Types de tests à implémenter :**
- Tests des endpoints REST avec MockMvc
- Tests d'intégration complets avec @SpringBootTest
- Tests de la sécurité et de l'authentification
- Tests des repositories avec @DataJpaTest

**Ressources à consulter :**
- [Integration Testing with @SpringBootTest](https://www.baeldung.com/spring-boot-testing)
- [MockMvc for REST Controllers](https://www.baeldung.com/integration-testing-in-spring)
- [Testing REST APIs with RestAssured](https://www.baeldung.com/rest-assured-tutorial)
- [H2 In-Memory Database for Tests](https://www.baeldung.com/spring-boot-h2-database)
- [Testcontainers for Database Testing](https://www.baeldung.com/spring-boot-testcontainers-integration-test)
- [@DataJpaTest for Repository Tests](https://www.baeldung.com/spring-boot-testing#unit-testing-with-datajpatest)
- [Testing Security with @WithMockUser](https://www.baeldung.com/spring-security-integration-tests)

---

## 🔧 Configuration

### Fichier de configuration requis

Vous devez créer un fichier `application.yml` (ou `application.properties`) contenant :

**Configuration requise :**
- Nom de l'application
- Configuration de la base de données (URL, username, password, driver)
- Configuration JPA/Hibernate (ddl-auto, show-sql, dialect)
- Configuration JWT (secret-key, expiration)
- Port du serveur

**Points importants :**
- La clé secrète JWT doit être suffisamment longue et sécurisée
- L'expiration du token est généralement de 24 heures (86400000 ms)
- Le mode `ddl-auto` peut être `update` en développement, `validate` en production

---

## 📝 Modèle de données

### Entité User
```
id: Long (PK)
username: String (unique, not null)
email: String (unique, not null)
password: String (not null, encrypted)
role: Role (enum: USER, ADMIN)
createdAt: LocalDateTime
tasks: List<Task>
projects: List<Project>
```

### Entité Task
```
id: Long (PK)
title: String (not null)
description: String
status: String (stockage du label, converti vers TaskStatus sealed class) - not null
priority: String (stockage du label, converti vers TaskPriority sealed class) - not null
dueDate: LocalDateTime
createdAt: LocalDateTime
updatedAt: LocalDateTime
user: User (FK)
project: Project (FK)
categories: Set<Category>
```

### Types du domaine (Sealed Classes)

**TaskStatus (sealed interface)**

Implémentations :
- Todo : "À faire"
- InProgress : "En cours"
- Done : "Terminée"

**TaskPriority (sealed interface)**

Implémentations :
- Low (niveau 1) : Priorité basse
- Medium (niveau 2) : Priorité moyenne
- High (niveau 3) : Priorité haute
- Urgent (niveau 4) : Urgent

**Role (sealed interface ou enum)**

Implémentations :
- UserRole : Utilisateur standard
- AdminRole : Administrateur

**Note :** Vous pouvez aussi utiliser des enums classiques si vous préférez la simplicité :
- TaskStatus : TODO, IN_PROGRESS, DONE
- TaskPriority : LOW, MEDIUM, HIGH, URGENT
- Role : USER, ADMIN

### Entité Project
```
id: Long (PK)
name: String (not null)
description: String
createdAt: LocalDateTime
user: User (FK)
tasks: List<Task>
```

### Entité Category
```
id: Long (PK)
name: String (unique, not null)
color: String
tasks: Set<Task>
```

---

## 🎨 API Endpoints

### Authentification
```
POST   /api/auth/register    - Inscription
POST   /api/auth/login       - Connexion (retourne un JWT)
GET    /api/auth/me          - Profil utilisateur authentifié
```

### Tâches
```
GET    /api/tasks                     - Liste toutes les tâches (avec pagination)
GET    /api/tasks/{id}                - Détails d'une tâche
POST   /api/tasks                     - Créer une tâche
PUT    /api/tasks/{id}                - Mettre à jour une tâche
DELETE /api/tasks/{id}                - Supprimer une tâche
GET    /api/tasks/search?keyword=...  - Recherche
GET    /api/tasks/stats               - Statistiques
```

### Projets
```
GET    /api/projects          - Liste tous les projets
GET    /api/projects/{id}     - Détails d'un projet
POST   /api/projects          - Créer un projet
PUT    /api/projects/{id}     - Mettre à jour un projet
DELETE /api/projects/{id}     - Supprimer un projet
```

### Catégories
```
GET    /api/categories        - Liste toutes les catégories
POST   /api/categories        - Créer une catégorie
DELETE /api/categories/{id}   - Supprimer une catégorie
```

---

## 🚀 Installation et lancement

### Prérequis
- Java 21
- PostgreSQL installé et en cours d'exécution
- Maven 3.6+

### Étapes

1. **Cloner le repository**
```bash
git clone https://github.com/loft972/taskFlow.git
cd taskFlow
```

2. **Créer la base de données**
Créer une base de données PostgreSQL nommée `taskflow`

3. **Configurer `application.yml`**
Modifier les informations de connexion à la base de données

4. **Compiler le projet**
```bash
mvn clean install
```

5. **Lancer l'application**
```bash
mvn spring-boot:run
```

L'API sera accessible sur : `http://localhost:8080`

---

## 🧪 Tests

### Lancer tous les tests
```bash
mvn test
```

### Lancer les tests avec rapport de couverture
```bash
mvn clean test jacoco:report
```

---

## 📖 Ressources utiles

### Documentation officielle
- [Documentation Spring Boot](https://spring.io/projects/spring-boot)
- [Documentation Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Documentation Spring Security](https://spring.io/projects/spring-security)
- [Hibernate ORM Documentation](https://hibernate.org/orm/documentation/6.0/)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)

### Guides et tutoriels
- [Spring Guides](https://spring.io/guides) - Guides officiels par Spring
- [Baeldung Spring Tutorials](https://www.baeldung.com/spring-tutorial) - Tutoriels complets
- [Java 21 Features](https://openjdk.org/projects/jdk/21/) - Nouvelles fonctionnalités Java 21

### Outils et librairies
- [JWT.io](https://jwt.io/) - Décodeur et informations sur JWT
- [Postman](https://www.postman.com/) - Test d'API REST
- [Swagger/OpenAPI](https://swagger.io/) - Documentation d'API
- [DBeaver](https://dbeaver.io/) - Client de base de données universel

### Chaînes YouTube recommandées
- [Spring Developer](https://www.youtube.com/@SpringSourceDev)
- [Amigoscode](https://www.youtube.com/@amigoscode)
- [Dan Vega](https://www.youtube.com/@DanVega)
- [Java Brains](https://www.youtube.com/@Java.Brains)

### Blogs techniques
- [Baeldung](https://www.baeldung.com/) - Tutoriels Java et Spring
- [Vlad Mihalcea](https://vladmihalcea.com/) - Expert Hibernate/JPA
- [Thorben Janssen](https://thorben-janssen.com/) - Expert JPA/Hibernate
- [Spring Blog](https://spring.io/blog) - Blog officiel Spring

### Livres recommandés
- "Spring in Action" par Craig Walls
- "Java Persistence with Hibernate" par Christian Bauer
- "Spring Security in Action" par Laurentiu Spilca
- "Effective Java" par Joshua Bloch

---

## ✅ Checklist de validation

Avant de passer au frontend Angular, assurez-vous d'avoir :

- [ ] Toutes les entités créées avec les bonnes relations
- [ ] CRUD complet pour Task, Project, User
- [ ] Authentification JWT fonctionnelle
- [ ] Validation des données côté backend
- [ ] Gestion des erreurs avec des messages clairs
- [ ] CORS configuré pour autoriser les requêtes depuis Angular
- [ ] Au moins 60% de couverture de tests
- [ ] Documentation de l'API (Postman ou Swagger)
- [ ] Code bien structuré et commenté

---

## 🎯 Prochaine étape : Angular Frontend

Une fois ce backend terminé, vous pourrez créer une application Angular qui consommera cette API REST pour :
- Afficher la liste des tâches
- Créer/modifier/supprimer des tâches
- Gérer l'authentification avec les tokens JWT
- Filtrer et rechercher des tâches
- Afficher des statistiques

---

## 🚀 Bonus : Optimisations avec Java 21

### Virtual Threads (Project Loom)

Java 21 introduit les Virtual Threads qui permettent d'améliorer considérablement les performances pour les applications I/O-bound comme les APIs REST.

**Méthodes d'activation :**
1. Via la configuration YAML dans `application.yml`
2. Via une classe de configuration Java avec un Bean personnalisé

**Avantages :**
- Meilleure scalabilité
- Moins de consommation mémoire
- Gestion simplifiée de la concurrence

### Records pour les DTOs

Utilisez les Records Java pour vos DTOs car ils sont :
- Immuables par défaut
- Plus concis
- Thread-safe
- Performants

**Pattern recommandé :**
- Créer une méthode statique `from()` pour mapper depuis l'entité
- Utiliser le constructeur compact pour la validation
- Profiter de l'immutabilité automatique

---

## 👨‍💻 Auteur

**Loïc Christophe** - Révision Backend Java & Spring Boot

---

## 📄 Licence

Ce projet est à but éducatif.