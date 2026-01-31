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
- **String Templates** (Preview) : Interpolation de chaînes
- **Sequenced Collections** : Nouvelles interfaces pour les collections ordonnées

**Exemple d'utilisation dans le projet :**
```java
// Record pour les DTOs (alternative aux classes classiques)
public record TaskRequest(
    @NotBlank String title,
    String description,
    @NotNull TaskPriority priority
) {}

// Pattern matching for switch
public String getTaskStatusMessage(TaskStatus status) {
    return switch (status) {
        case TODO -> "Tâche à faire";
        case IN_PROGRESS -> "En cours";
        case DONE -> "Terminée";
    };
}
```

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
│   └── Role.java (enum)
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

#### Étape 1.2 : Création des entités de base
- [ ] Créer l'entité `User` avec les champs : id, username, email, password, role, createdAt
- [ ] Créer l'entité `Task` avec les champs : id, title, description, status, priority, dueDate, createdAt, updatedAt
- [ ] Ajouter les annotations JPA appropriées (@Entity, @Id, @GeneratedValue, etc.)

**Points à réviser :**
- Annotations JPA de base
- Types de génération d'ID
- Annotations temporelles (@CreatedDate, @LastModifiedDate)

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

#### Étape 3.2 : Gestion globale des exceptions
- [ ] Créer `GlobalExceptionHandler` avec @ControllerAdvice
- [ ] Créer des exceptions personnalisées
- [ ] Retourner des réponses d'erreur structurées

**Exceptions à créer :**
- `ResourceNotFoundException`
- `BadRequestException`
- `UnauthorizedException`

---

### **Phase 4 : Relations entre entités (Niveau intermédiaire)**

#### Étape 4.1 : Relation User - Task
- [ ] Ajouter la relation @ManyToOne dans Task
- [ ] Ajouter la relation @OneToMany dans User
- [ ] Configurer le cascade et le fetch type

#### Étape 4.2 : Ajout de Project
- [ ] Créer l'entité `Project`
- [ ] Relation User - Project (@ManyToOne)
- [ ] Relation Project - Task (@OneToMany)

#### Étape 4.3 : Ajout de Category
- [ ] Créer l'entité `Category`
- [ ] Relation Task - Category (@ManyToMany)
- [ ] Créer la table de jointure

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

#### Étape 5.3 : Statistiques
- [ ] Créer un endpoint pour les statistiques utilisateur
- [ ] Compter les tâches par status
- [ ] Calculer le taux de complétion

---

### **Phase 6 : Sécurité et authentification (Niveau avancé)**

#### Étape 6.1 : Configuration Spring Security
- [ ] Créer `SecurityConfig`
- [ ] Configurer les endpoints publics/privés
- [ ] Désactiver CSRF pour l'API REST
- [ ] Configurer CORS

#### Étape 6.2 : Implémentation JWT
- [ ] Créer `JwtTokenProvider` pour générer/valider les tokens
- [ ] Créer `JwtAuthenticationFilter`
- [ ] Implémenter `UserDetailsService`

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

#### Étape 6.4 : Sécurisation des endpoints
- [ ] Protéger tous les endpoints `/api/tasks/**`
- [ ] Vérifier que l'utilisateur ne peut voir que ses propres tâches
- [ ] Implémenter les rôles (USER, ADMIN)

---

### **Phase 7 : Tests (Niveau avancé)**

#### Étape 7.1 : Tests unitaires
- [ ] Tester les services avec Mockito
- [ ] Tester les repositories
- [ ] Atteindre une couverture de code >70%

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
status: TaskStatus (enum: TODO, IN_PROGRESS, DONE)
priority: TaskPriority (enum: LOW, MEDIUM, HIGH, URGENT)
dueDate: LocalDateTime
createdAt: LocalDateTime
updatedAt: LocalDateTime
user: User (FK)
project: Project (FK)
categories: Set<Category>
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

- [Documentation Spring Boot](https://spring.io/projects/spring-boot)
- [Documentation Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Documentation Spring Security](https://spring.io/projects/spring-security)
- [Guide JWT avec Spring](https://jwt.io/introduction)
- [Hibernate ORM](https://hibernate.org/orm/documentation/)

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



---

## 👨‍💻 Auteur

**Loïc Christophe** - Révision Backend Java & Spring Boot

---

## 📄 Licence

Ce projet est à but éducatif.

