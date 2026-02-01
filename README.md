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

**Exemple d'utilisation dans le projet :**

**1. Sealed Classes pour le domain modeling**
```java
// Alternative moderne aux enums
public sealed interface TaskStatus permits Todo, InProgress, Done {
    String getLabel();
}

public record Todo() implements TaskStatus {
    @Override
    public String getLabel() { return "TODO"; }
}

public record InProgress() implements TaskStatus {
    @Override
    public String getLabel() { return "IN_PROGRESS"; }
}

public record Done() implements TaskStatus {
    @Override
    public String getLabel() { return "DONE"; }
}
```

**2. Records pour les DTOs**
```java
// Record pour les DTOs (alternative aux classes classiques)
public record TaskRequest(
    @NotBlank String title,
    String description,
    @NotNull TaskPriority priority
) {}
```

**3. Pattern matching for switch avec sealed classes**
```java
public String getTaskStatusMessage(TaskStatus status) {
    return switch (status) {
        case Todo t -> "Tâche à faire";
        case InProgress ip -> "En cours de réalisation";
        case Done d -> "Tâche terminée ✓";
    };
}

// Le compilateur garantit l'exhaustivité !
// Si vous ajoutez un nouveau type à TaskStatus, le code ne compilera pas
// tant que vous n'aurez pas géré ce cas dans le switch
```

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

**Ressources à consulter :**
- [Spring Boot Application Properties](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html)
- [Configuration PostgreSQL avec Spring Boot](https://www.baeldung.com/spring-boot-postgresql-docker)
- [Maven Dependencies Management](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html)
- [Spring Boot Starters Guide](https://www.baeldung.com/spring-boot-starters)

**Dépendances à ajouter dans `pom.xml` :**
```xml
<!-- Parent Spring Boot 3.5.10 -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.5.10</version>
    <relativePath/>
</parent>

<properties>
    <java.version>21</java.version>
</properties>

<!-- Spring Boot Starter Web -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- Spring Boot Starter Data JPA -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- Spring Boot Starter Validation -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>

<!-- PostgreSQL Driver -->
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>

<!-- Spring Boot Starter Security -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<!-- JWT -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>

<!-- Spring Boot Starter Test -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

#### Étape 1.2 : Création des types avec Sealed Classes (Java 21)
- [ ] Créer la sealed class `Role` avec les implémentations : UserRole, AdminRole
- [ ] Créer la sealed class `TaskStatus` avec les implémentations : Todo, InProgress, Done
- [ ] Créer la sealed class `TaskPriority` avec les implémentations : Low, Medium, High, Urgent

**Pourquoi les Sealed Classes plutôt que les Enums ?**
- Plus de flexibilité : chaque variante peut avoir ses propres propriétés et méthodes
- Pattern matching exhaustif garanti par le compilateur
- Meilleure expressivité du domaine métier
- Possibilité d'ajouter des comportements spécifiques

**Option 1 : Sealed Class simple (type algébrique)**
```java
public sealed interface TaskStatus permits Todo, InProgress, Done {
    String getLabel();
    String getColor();
}

public record Todo() implements TaskStatus {
    @Override
    public String getLabel() {
        return "À faire";
    }
    
    @Override
    public String getColor() {
        return "#808080";
    }
}

public record InProgress() implements TaskStatus {
    @Override
    public String getLabel() {
        return "En cours";
    }
    
    @Override
    public String getColor() {
        return "#FFA500";
    }
}

public record Done() implements TaskStatus {
    @Override
    public String getLabel() {
        return "Terminée";
    }
    
    @Override
    public String getColor() {
        return "#008000";
    }
}
```

**Option 2 : Sealed Class avec données (plus avancé)**
```java
public sealed interface TaskPriority permits Low, Medium, High, Urgent {
    int getLevel();
    String getLabel();
    
    // Pattern matching helper
    default String getDescription() {
        return switch (this) {
            case Low l -> "Priorité basse";
            case Medium m -> "Priorité moyenne";
            case High h -> "Priorité haute";
            case Urgent u -> "Urgent";
        };
    }
}

public record Low() implements TaskPriority {
    @Override
    public int getLevel() { return 1; }
    
    @Override
    public String getLabel() { return "LOW"; }
}

public record Medium() implements TaskPriority {
    @Override
    public int getLevel() { return 2; }
    
    @Override
    public String getLabel() { return "MEDIUM"; }
}

public record High() implements TaskPriority {
    @Override
    public int getLevel() { return 3; }
    
    @Override
    public String getLabel() { return "HIGH"; }
}

public record Urgent() implements TaskPriority {
    @Override
    public int getLevel() { return 4; }
    
    @Override
    public String getLabel() { return "URGENT"; }
}
```

**Option 3 : Enum classique (plus simple pour débuter)**
Si vous préférez commencer par quelque chose de plus simple, vous pouvez toujours utiliser les enums traditionnels :

```java
public enum TaskStatus {
    TODO,
    IN_PROGRESS,
    DONE
}

public enum TaskPriority {
    LOW,
    MEDIUM,
    HIGH,
    URGENT
}

public enum Role {
    USER,
    ADMIN
}
```

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

**Exemple d'entité Task avec Sealed Classes :**
```java
@Entity
@Table(name = "tasks")
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String title;
    
    @Column(length = 500)
    private String description;
    
    // Stockage en base de données : on sauvegarde le label
    @Column(nullable = false)
    private String status;
    
    @Column(nullable = false)
    private String priority;
    
    private LocalDateTime dueDate;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    // Constructeurs
    public Task() {
        this.createdAt = LocalDateTime.now();
        this.status = new Todo().getLabel();
    }
    
    // Méthodes utilitaires pour convertir String <-> Sealed Class
    public TaskStatus getStatusEnum() {
        return switch (this.status) {
            case "TODO" -> new Todo();
            case "IN_PROGRESS" -> new InProgress();
            case "DONE" -> new Done();
            default -> new Todo();
        };
    }
    
    public void setStatusEnum(TaskStatus status) {
        this.status = status.getLabel();
    }
    
    public TaskPriority getPriorityEnum() {
        return switch (this.priority) {
            case "LOW" -> new Low();
            case "MEDIUM" -> new Medium();
            case "HIGH" -> new High();
            case "URGENT" -> new Urgent();
            default -> new Low();
        };
    }
    
    public void setPriorityEnum(TaskPriority priority) {
        this.priority = priority.getLabel();
    }
    
    // Getters et Setters classiques
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    // ... autres getters/setters
    
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
```

**Alternative plus simple avec Enum :**
```java
@Entity
@Table(name = "tasks")
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String title;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskPriority priority;
    
    // ... reste du code
}
```

**Points à réviser :**
- Annotations JPA de base (@Entity, @Table, @Column)
- Types de génération d'ID (@GeneratedValue)
- Annotations temporelles (@PreUpdate, @PrePersist)
- Sealed classes vs Enums : avantages et inconvénients
- Pattern matching avec sealed classes
- Persistance JPA : stockage de types personnalisés

**Ressources à consulter :**
- [JPA Entity Annotations - Baeldung](https://www.baeldung.com/jpa-entities)
- [JPA @GeneratedValue Strategies](https://www.baeldung.com/jpa-strategies-when-id-null)
- [JPA Lifecycle Callbacks (@PreUpdate, @PrePersist)](https://www.baeldung.com/jpa-entity-lifecycle-events)
- [JPA @Enumerated Annotation](https://www.baeldung.com/jpa-persisting-enums-in-jpa)
- [Mapping Custom Types in JPA](https://thorben-janssen.com/jpa-21-type-converter-better-way-to/)
- [Hibernate Official Documentation](https://hibernate.org/orm/documentation/6.0/)

---

### **Phase 2 : CRUD de base (Niveau débutant)**

#### Étape 2.1 : Repository Layer
- [ ] Créer `TaskRepository` extends `JpaRepository<Task, Long>`
- [ ] Créer `UserRepository` extends `JpaRepository<User, Long>`
- [ ] Ajouter des méthodes de recherche personnalisées

**Exemple de méthodes à implémenter :**
```java
List<Task> findByStatus(TaskStatus status);
List<Task> findByUserId(Long userId);
Optional<User> findByEmail(String email);
```

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

**Option 1 : Classe classique (sans Lombok)**
```java
public class TaskRequest {
    @NotBlank(message = "Le titre est obligatoire")
    @Size(min = 3, max = 100, message = "Le titre doit contenir entre 3 et 100 caractères")
    private String title;
    
    @Size(max = 500, message = "La description ne peut pas dépasser 500 caractères")
    private String description;
    
    @NotNull(message = "La priorité est obligatoire")
    private TaskPriority priority;
    
    // Constructeurs
    public TaskRequest() {}
    
    public TaskRequest(String title, String description, TaskPriority priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }
    
    // Getters
    public String getTitle() {
        return title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public TaskPriority getPriority() {
        return priority;
    }
    
    // Setters
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }
}
```

**Option 2 : Record Java 21 (recommandé pour les DTOs immuables)**
```java
public record TaskRequest(
    @NotBlank(message = "Le titre est obligatoire")
    @Size(min = 3, max = 100)
    String title,
    
    @Size(max = 500)
    String description,
    
    @NotNull(message = "La priorité est obligatoire")
    TaskPriority priority,
    
    LocalDateTime dueDate
) {
    // Constructeur compact pour validation supplémentaire si nécessaire
    public TaskRequest {
        if (title != null) {
            title = title.trim();
        }
    }
}
```

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

**Exemple :**
```java
List<Task> findByTitleContainingIgnoreCase(String keyword);
List<Task> findByStatusAndPriority(TaskStatus status, TaskPriority priority);
List<Task> findByDueDateBetween(LocalDateTime start, LocalDateTime end);
```

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

**Exemple avec pattern matching (sealed classes) :**
```java
@Service
public class TaskStatisticsService {
    
    public TaskStatistics getUserStatistics(Long userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);
        
        long todoCount = tasks.stream()
            .map(Task::getStatusEnum)
            .filter(status -> status instanceof Todo)
            .count();
        
        long inProgressCount = tasks.stream()
            .map(Task::getStatusEnum)
            .filter(status -> status instanceof InProgress)
            .count();
        
        long doneCount = tasks.stream()
            .map(Task::getStatusEnum)
            .filter(status -> status instanceof Done)
            .count();
        
        // Pattern matching exhaustif
        Map<String, Long> tasksByPriority = tasks.stream()
            .collect(Collectors.groupingBy(task -> 
                switch (task.getPriorityEnum()) {
                    case Low l -> "Basse";
                    case Medium m -> "Moyenne";
                    case High h -> "Haute";
                    case Urgent u -> "Urgente";
                },
                Collectors.counting()
            ));
        
        double completionRate = tasks.isEmpty() ? 0 : 
            (double) doneCount / tasks.size() * 100;
        
        return new TaskStatistics(
            tasks.size(),
            todoCount,
            inProgressCount,
            doneCount,
            completionRate,
            tasksByPriority
        );
    }
}
```

**Avec enums classiques :**
```java
long doneCount = tasks.stream()
    .filter(task -> task.getStatus() == TaskStatus.DONE)
    .count();
```

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

**Structure du token JWT :**
```json
{
  "sub": "user@email.com",
  "userId": 1,
  "role": "USER",
  "iat": 1234567890,
  "exp": 1234654290
}
```

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

**Exemple de test :**
```java
@Test
void shouldCreateTask() throws Exception {
    TaskRequest request = new TaskRequest();
    request.setTitle("Test Task");
    
    mockMvc.perform(post("/api/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.title").value("Test Task"));
}
```

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

### application.yml (exemple)

```yaml
spring:
  application:
    name: taskflow
  
  datasource:
    url: jdbc:postgresql://localhost:5432/taskflow
    username: postgres
    password: password
    driver-class-name: org.postgresql.Driver
  
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.PostgreSQLDialect
  
  security:
    jwt:
      secret-key: votreClefSecreteTresLongueEtSecurisee
      expiration: 86400000 # 24 heures en millisecondes

server:
  port: 8080
```

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
```java
sealed interface TaskStatus permits Todo, InProgress, Done

Implémentations :
- Todo : "À faire"
- InProgress : "En cours"
- Done : "Terminée"
```

**TaskPriority (sealed interface)**
```java
sealed interface TaskPriority permits Low, Medium, High, Urgent

Implémentations :
- Low (niveau 1) : Priorité basse
- Medium (niveau 2) : Priorité moyenne
- High (niveau 3) : Priorité haute
- Urgent (niveau 4) : Urgent
```

**Role (sealed interface ou enum)**
```java
sealed interface Role permits UserRole, AdminRole

Implémentations :
- UserRole : Utilisateur standard
- AdminRole : Administrateur
```

**Note :** Vous pouvez aussi utiliser des enums classiques si vous préférez la simplicité :
```java
enum TaskStatus { TODO, IN_PROGRESS, DONE }
enum TaskPriority { LOW, MEDIUM, HIGH, URGENT }
enum Role { USER, ADMIN }
```

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
```sql
CREATE DATABASE taskflow;
```

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

**Activation dans Spring Boot 3.5.10 :**

```yaml
# application.yml
spring:
  threads:
    virtual:
      enabled: true
```

Ou par configuration :

```java
@Configuration
public class VirtualThreadConfig {
    
    @Bean
    public TomcatProtocolHandlerCustomizer<?> protocolHandlerVirtualThreadExecutorCustomizer() {
        return protocolHandler -> {
            protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
        };
    }
}
```

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

```java
// Response DTO avec Record
public record TaskResponse(
    Long id,
    String title,
    String description,
    TaskStatus status,
    TaskPriority priority,
    LocalDateTime dueDate,
    LocalDateTime createdAt,
    String username
) {
    // Méthode statique pour mapper depuis l'entité
    public static TaskResponse from(Task task) {
        return new TaskResponse(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getStatus(),
            task.getPriority(),
            task.getDueDate(),
            task.getCreatedAt(),
            task.getUser().getUsername()
        );
    }
}
```

---

## 👨‍💻 Auteur

**Loïc Christophe** - Révision Backend Java & Spring Boot

---

## 📄 Licence

Ce projet est à but éducatif.

---

**Bon courage pour votre révision ! 💪**