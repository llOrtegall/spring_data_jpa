src/main/java/com/ortega/marktplace/
│
├── domain/                                    # Domain Layer (Core Business Logic)
│   ├── model/                                # Domain Models (Entities, Value Objects)
│   │   ├── aggregates/                       # Aggregate Roots
│   │   ├── entities/                         # Domain Entities
│   │   └── valueobjects/                     # Value Objects
│   ├── repository/                           # Repository Interfaces (Ports)
│   ├── service/                              # Domain Services
│   ├── event/                                # Domain Events
│   ├── exception/                            # Domain Exceptions
│   └── factory/                              # Domain Factories
│
├── application/                               # Application Layer (Use Cases)
│   ├── usecase/                              # Use Cases / Application Services
│   │   ├── command/                          # Command Use Cases (Write operations)
│   │   └── query/                            # Query Use Cases (Read operations)
│   ├── dto/                                  # Data Transfer Objects
│   │   ├── request/                          # Request DTOs
│   │   └── response/                         # Response DTOs
│   ├── mapper/                               # DTO <-> Domain Mappers
│   ├── port/                                 # Application Ports
│   │   ├── input/                            # Input Ports (Use Case Interfaces)
│   │   └── output/                           # Output Ports (Repository, External Services)
│   └── event/                                # Application Event Handlers
│
├── infrastructure/                            # Infrastructure Layer (Technical Details)
│   ├── persistence/                          # Database Implementation
│   │   ├── entity/                           # JPA Entities
│   │   ├── repository/                       # JPA Repository Implementations
│   │   ├── mapper/                           # JPA Entity <-> Domain Mappers
│   │   └── config/                           # Database Configuration
│   ├── messaging/                            # Message Brokers (Kafka, RabbitMQ)
│   │   ├── publisher/                        # Event Publishers
│   │   └── consumer/                         # Event Consumers
│   ├── external/                             # External Services Integration
│   │   ├── client/                           # REST/SOAP Clients
│   │   └── adapter/                          # External Service Adapters
│   ├── security/                             # Security Implementation
│   │   ├── config/                           # Security Configuration
│   │   └── service/                          # Security Services
│   └── config/                               # General Infrastructure Config
│
├── presentation/                              # Presentation Layer (Interface Adapters)
│   ├── rest/                                 # REST Controllers
│   │   ├── controller/                       # REST Controllers
│   │   ├── request/                          # REST Request Models
│   │   ├── response/                         # REST Response Models
│   │   └── mapper/                           # REST <-> Application DTO Mappers
│   ├── graphql/                              # GraphQL (if needed)
│   │   ├── resolver/                         # GraphQL Resolvers
│   │   └── schema/                           # GraphQL Schemas
│   ├── exception/                            # Global Exception Handlers
│   └── validation/                           # Input Validation
│
└── shared/                                    # Shared Kernel (Cross-cutting concerns)
    ├── util/                                 # Utility Classes
    ├── constant/                             # Constants
    ├── exception/                            # Common Exceptions
    └── validator/                            # Common Validators