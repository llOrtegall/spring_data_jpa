# User Entity - Clean Architecture + DDD Example

Este es un ejemplo completo de cómo implementar una entidad **User** siguiendo los principios de **Clean Architecture** y **Domain-Driven Design (DDD)** en Spring Boot.

## 📁 Estructura del Proyecto

```
src/main/java/com/ortega/marktplace/
│
├── domain/                                    # Capa de Dominio (Lógica de Negocio)
│   ├── model/
│   │   ├── entities/
│   │   │   └── User.java                     # Entidad de dominio con lógica de negocio
│   │   └── valueobjects/
│   │       ├── Email.java                    # Value Object para Email (validación)
│   │       └── UserId.java                   # Value Object para ID (tipo seguro)
│   ├── repository/
│   │   └── UserRepository.java               # Interface del repositorio (Puerto)
│   └── exception/
│       ├── UserNotFoundException.java
│       └── DuplicateEmailException.java
│
├── application/                               # Capa de Aplicación (Casos de Uso)
│   ├── dto/
│   │   ├── request/
│   │   │   ├── CreateUserRequest.java
│   │   │   └── UpdateUserRequest.java
│   │   └── response/
│   │       └── UserResponse.java
│   ├── mapper/
│   │   └── UserMapper.java                   # Mapea Domain → DTO
│   ├── port/input/                           # Interfaces de casos de uso
│   │   ├── CreateUserUseCase.java
│   │   ├── GetUserUseCase.java
│   │   ├── UpdateUserUseCase.java
│   │   └── DeleteUserUseCase.java
│   └── usecase/
│       ├── command/                          # Operaciones de escritura
│       │   ├── CreateUserUseCaseImpl.java
│       │   ├── UpdateUserUseCaseImpl.java
│       │   └── DeleteUserUseCaseImpl.java
│       └── query/                            # Operaciones de lectura
│           └── GetUserUseCaseImpl.java
│
├── infrastructure/                            # Capa de Infraestructura (Detalles Técnicos)
│   └── persistence/
│       ├── entity/
│       │   └── UserJpaEntity.java            # Entidad JPA (tabla DB)
│       ├── repository/
│       │   ├── UserJpaRepository.java        # Spring Data JPA Repository
│       │   └── UserRepositoryImpl.java       # Implementación del puerto
│       └── mapper/
│           └── UserPersistenceMapper.java    # Mapea Domain ↔ JPA Entity
│
└── presentation/                              # Capa de Presentación (API REST)
    ├── rest/
    │   ├── controller/
    │   │   └── UserController.java           # REST Controller
    │   ├── request/
    │   │   ├── CreateUserRestRequest.java    # Request con validaciones
    │   │   └── UpdateUserRestRequest.java
    │   └── mapper/
    │       └── UserRestMapper.java           # Mapea REST Request → Application DTO
    └── exception/
        └── GlobalExceptionHandler.java       # Manejo global de excepciones
```

## 🎯 Principios Aplicados

### 1. **Clean Architecture**
- **Regla de Dependencia**: Las dependencias apuntan hacia adentro
  - `Domain` ← `Application` ← `Infrastructure/Presentation`
  - El dominio NO depende de nada externo
  - La infraestructura depende del dominio

### 2. **Domain-Driven Design (DDD)**
- **Entities**: `User` con identidad única y lógica de negocio
- **Value Objects**: `Email`, `UserId` (inmutables, con validación)
- **Repository Pattern**: Interface en dominio, implementación en infraestructura
- **Domain Events**: Preparado para eventos de dominio
- **Aggregate Roots**: User puede ser un aggregate root

### 3. **Separation of Concerns**
- **3 tipos de mappers**:
  - `UserPersistenceMapper`: Domain ↔ JPA Entity
  - `UserMapper`: Domain → Application DTO
  - `UserRestMapper`: REST Request → Application DTO

### 4. **CQRS (Command Query Responsibility Segregation)**
- **Commands**: Operaciones de escritura (`CreateUserUseCase`, `UpdateUserUseCase`)
- **Queries**: Operaciones de lectura (`GetUserUseCase`)

## 🚀 Endpoints REST API

### Base URL: `http://localhost:8080/api/v1/users`

### 1. **Crear Usuario**
```bash
POST /api/v1/users
Content-Type: application/json

{
  "firstName": "Juan",
  "lastName": "Pérez",
  "email": "juan.perez@example.com"
}
```

**Respuesta (201 Created):**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "firstName": "Juan",
  "lastName": "Pérez",
  "email": "juan.perez@example.com",
  "fullName": "Juan Pérez",
  "active": true,
  "createdAt": "2026-02-20T23:20:00",
  "updatedAt": "2026-02-20T23:20:00"
}
```

### 2. **Obtener Usuario por ID**
```bash
GET /api/v1/users/{id}
```

### 3. **Obtener Usuario por Email**
```bash
GET /api/v1/users/email/{email}
```

### 4. **Obtener Todos los Usuarios**
```bash
GET /api/v1/users
```

### 5. **Obtener Usuarios Activos**
```bash
GET /api/v1/users/active
```

### 6. **Actualizar Usuario**
```bash
PUT /api/v1/users/{id}
Content-Type: application/json

{
  "firstName": "Juan Carlos",
  "lastName": "Pérez García"
}
```

### 7. **Activar Usuario**
```bash
PATCH /api/v1/users/{id}/activate
```

### 8. **Desactivar Usuario**
```bash
PATCH /api/v1/users/{id}/deactivate
```

### 9. **Eliminar Usuario**
```bash
DELETE /api/v1/users/{id}
```

## 🔍 Mejores Prácticas Implementadas

### **1. Value Objects (Email, UserId)**
```java
// Email con validación automática
Email email = new Email("user@example.com"); // ✅ Válido
Email invalid = new Email("invalid-email");   // ❌ Lanza IllegalArgumentException

// UserId tipo seguro (no se puede confundir con otros IDs)
UserId userId = UserId.generate(); // Genera UUID automáticamente
```

### **2. Entidad de Dominio con Lógica de Negocio**
```java
User user = new User(userId, "Juan", "Pérez", email);
user.updateProfile("Juan Carlos", "Pérez García"); // Actualiza y marca updatedAt
user.deactivate(); // Lógica de negocio en el dominio
```

### **3. Método Reconstitute para Persistencia**
```java
// Constructor público para crear nuevos usuarios
User newUser = new User(id, firstName, lastName, email);

// Método estático para reconstruir desde DB (con todas las propiedades)
User existingUser = User.reconstitute(id, firstName, lastName, email, 
                                     active, createdAt, updatedAt);
```

### **4. Validación en Múltiples Capas**
- **Dominio**: Validaciones de negocio (Email format, nombre mínimo 2 caracteres)
- **Presentación**: Validaciones de entrada con Jakarta Validation (`@NotBlank`, `@Email`, `@Size`)

### **5. Manejo de Excepciones**
```java
// Excepciones de dominio específicas
throw new UserNotFoundException(userId);
throw new DuplicateEmailException(email);

// Manejadas globalmente en GlobalExceptionHandler
// Retornan respuestas HTTP apropiadas (404, 409, 400, etc.)
```

### **6. Transacciones**
```java
@Service
@Transactional  // Operaciones de escritura
public class CreateUserUseCaseImpl { ... }

@Service
@Transactional(readOnly = true)  // Operaciones de lectura (optimización)
public class GetUserUseCaseImpl { ... }
```

### **7. Inyección de Dependencias por Constructor**
```java
// Inmutable, fácil de testear, Spring recomienda esta forma
public UserController(CreateUserUseCase createUserUseCase,
                     GetUserUseCase getUserUseCase, ...) {
    this.createUserUseCase = createUserUseCase;
    // ...
}
```

## 🗄️ Base de Datos

La tabla `users` se creará automáticamente con:
- **ID**: UUID (tipo seguro)
- **Índice único** en email
- **Timestamps**: created_at, updated_at

```sql
CREATE TABLE users (
    id UUID PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE UNIQUE INDEX idx_user_email ON users(email);
```

## 🧪 Cómo Probar

### Opción 1: cURL
```bash
# Crear usuario
curl -X POST http://localhost:8080/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Juan",
    "lastName": "Pérez",
    "email": "juan.perez@example.com"
  }'

# Obtener todos los usuarios
curl http://localhost:8080/api/v1/users
```

### Opción 2: Postman / Insomnia
Importa los endpoints listados arriba.

### Opción 3: Navegador (solo GET)
```
http://localhost:8080/api/v1/users
http://localhost:8080/api/v1/users/active
```

## 📊 Flujo de una Petición

```
1. HTTP Request → UserController (Presentation)
   ↓
2. Valida con @Valid → CreateUserRestRequest
   ↓
3. UserRestMapper → CreateUserRequest (Application DTO)
   ↓
4. CreateUserUseCase.execute() (Application)
   ↓
5. Crea User (Domain Entity) con Email (Value Object)
   ↓
6. UserRepository.save() (Domain Interface)
   ↓
7. UserRepositoryImpl (Infrastructure)
   ↓
8. UserPersistenceMapper → UserJpaEntity
   ↓
9. UserJpaRepository.save() (Spring Data JPA)
   ↓
10. PostgreSQL Database
   ↓
11. UserPersistenceMapper → User (Domain)
   ↓
12. UserMapper → UserResponse (Application DTO)
   ↓
13. HTTP Response JSON
```

## 🎓 Conceptos Clave

### **¿Por qué 3 capas de modelos?**
1. **Domain Entity (User)**: Lógica de negocio pura, independiente de frameworks
2. **JPA Entity (UserJpaEntity)**: Detalles de persistencia (anotaciones JPA)
3. **DTOs (Request/Response)**: Contrato de API, validaciones de entrada

### **¿Por qué Value Objects?**
- **Tipo seguro**: No puedes pasar un String donde se espera un Email
- **Validación centralizada**: Email siempre válido si existe
- **Inmutabilidad**: No se pueden modificar después de crearse
- **Semántica de negocio**: `Email` es más expresivo que `String`

### **¿Por qué separar Use Cases?**
- **Single Responsibility**: Cada caso de uso hace una cosa
- **Fácil de testear**: Puedes testear cada operación independientemente
- **CQRS**: Separación clara entre lecturas y escrituras
- **Escalabilidad**: Puedes optimizar queries vs commands de forma diferente

## 🔄 Próximos Pasos

Para extender este ejemplo, puedes:
1. Agregar **Domain Events** (UserCreatedEvent, UserActivatedEvent)
2. Implementar **Specifications** para queries complejas
3. Agregar **Auditoría** automática (CreatedBy, UpdatedBy)
4. Implementar **Soft Delete** en lugar de delete físico
5. Agregar **Paginación** en los endpoints de listado
6. Crear **Tests Unitarios** para cada capa
7. Implementar **Cache** en la capa de aplicación

## 📝 Notas Importantes

- **Domain** nunca importa clases de Spring, JPA o Jakarta
- **Application** solo conoce el dominio
- **Infrastructure** implementa las interfaces del dominio
- **Presentation** solo conoce Application y Domain (no Infrastructure directamente)
- Cada capa tiene su propio modelo y mappers para traducir entre capas
