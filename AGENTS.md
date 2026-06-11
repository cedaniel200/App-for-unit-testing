# AGENTS.md — App-for-unit-testing

## Comandos esenciales
```bash
./gradlew bootJar                          # build
./gradlew test                              # tests
./gradlew clean test jacocoTestReport       # tests + cobertura (reporte: build/reports/jacoco/test/html/index.html)
java -jar build/libs/unit-testing-app-1.0.0.jar   # ejecutar app
```

## Stack
- Java 17 (`sourceCompatibility = 17`), Gradle 9.5.1 wrapper
- Spring Boot 3.5.14, Spring Shell 3.4.2, Retrofit 2 + Gson
- **Tests**: JUnit 4.12, Mockito 2.19.0, PowerMock 2.0.0, JUnitParams 1.1.1 (JUnit Vintage Engine para compatibilidad con Gradle 9)
- **Cobertura**: JaCoCo 0.8.15 (excluye `config/`, `console/`, `model/`, `App`)

## Arquitectura (Clean Architecture / DDD)
- Entrypoint: `com.cedaniel200.practice.App` (`@SpringBootApplication`)
- Capas: `console` → `domain` → `repository`/`persistence` → `service` (Retrofit HTTP a reqres.in)
- `domain` es la capa más interna — Java puro, sin dependencias de frameworks
- Cada concepto de dominio vive en su subpaquete: `calculator/`, `email/`, `greeting/`, `phrase/`, `user/`
- Excepciones de dominio transversales: `MalformedDataException`, `ServiceNotAvailableException`

## Patrones de diseño en uso
- **Strategy**: `CalculatorDefault` compone `Adder`, `Subtractor`, `Multiplier`, `Divider`
- **Interface + Impl *Default**: separación interfaz/implementación en cada subdominio
- **Repository**: `UserRepository` + `UserRepositoryDefault`
- **DAO**: `PhraseDao`
- **DI por constructor** via Spring (`@Configuration` en `config/`)

## Testing (TDD)
- Solo existe `CalculatorDefaultTest.java` (stub con TODO) — el repo existe para que escribas los tests
- Cada test debe aislar la capa `domain` mockeando dependencias
- JUnit 4 únicamente, no JUnit 5
- `@RunWith(MockitoJUnitRunner.class)` o `MockitoAnnotations.initMocks(this)` (Mockito 2.x)
- PowerMock 2.0.0 disponible para métodos estáticos o constructores (usar con moderación)
- Naming de clases: `*Test.java`
- JUnitParams disponible para tests parametrizados

## Commits
- Mensajes de commit en español
- Preguntar al usuario antes de ejecutar cualquier commit
- Explicar los cambios de código detalladamente antes de proceder

## Prácticas adicionales
- **Refactoring primero**: Antes de añadir funcionalidades, refactorizar código existente si es complejo
- **Seguridad**: Validar inputs en todas las capas (especialmente comandos de consola)
- **No asumir tecnologías**: No usar librerías, frameworks o herramientas que no estén en el stack definido en `build.gradle`. Si una tecnología no está en el proyecto, preguntar antes de incorporarla
- **No inventar entidades**: No crear nombres de clases, métodos o datos que no existan en el código o no estén definidos en el dominio
- **No alucinar**: No inventar librerías, dependencias o APIs. Solo usar lo que se pueda verificar en el código fuente
- **Context7**: Cuando necesites buscar documentación técnica, usa las herramientas de `context7`
