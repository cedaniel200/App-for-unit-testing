# AGENTS.md — App-for-unit-testing

## Comandos esenciales
```bash
./gradlew unitTest                           # solo pruebas unitarias (para estudiantes)
./gradlew e2eTest                             # solo pruebas end-to-end de consola (mantenimiento)
./gradlew integrationTest                     # solo pruebas de integracion con servicios externos (mantenimiento)
./gradlew test                                # todas las pruebas (mantenimiento)
./gradlew clean test jacocoTestReport         # todas + cobertura (mantenimiento)
./gradlew unitTest jacocoUnitTestReport       # unitarias + cobertura (estudiantes, reporte: build/reports/jacoco/jacocoUnitTestReport/html/index.html)
./gradlew bootJar                             # build
java -jar build/libs/unit-testing-app-1.0.0.jar   # ejecutar app
```

## Stack
- Java 17 (`sourceCompatibility = 17`), Gradle 9.5.1 wrapper
- Spring Boot 3.5.14, Spring Shell 3.4.2, Retrofit 2 + Gson
- **Tests**: JUnit 4.12, Mockito 2.19.0, PowerMock 2.0.0, JUnitParams 1.1.1 (JUnit Vintage Engine para compatibilidad con Gradle 9)
- **E2E / Integration**: JUnit 5 (Jupiter), Spring Shell Test (ShellTestClient), Spring Boot Test (@SpringBootTest, @TestConfiguration)
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

## Testing — para estudiantes
- Solo existe `CalculatorDefaultTest.java` (stub con TODO) — el repo existe para que los estudiantes escriban los tests
- Cada test debe aislar la capa `domain` mockeando dependencias
- JUnit 4 únicamente, no JUnit 5 (JUnit 5 es solo para E2E / Integration)
- `@RunWith(MockitoJUnitRunner.class)` o `MockitoAnnotations.initMocks(this)` (Mockito 2.x)
- PowerMock 2.0.0 disponible para métodos estáticos o constructores (usar con moderación)
- Naming de clases: `*Test.java`
- JUnitParams disponible para tests parametrizados
- Ejecutar con: `./gradlew unitTest`

## E2E / Integration Testing (mantenimiento de la app)
- **`e2eTest`**: Pruebas que interactuan con la consola Spring Shell via `ShellTestClient`. Garantizan que los comandos (`add`, `subtract`, `greet`, `list`, etc.) funcionan correctamente tras cualquier cambio. Mockean dependencias externas (HTTP, email) para ser auto-contenidas.
- **`integrationTest`**: Pruebas que conectan con servicios reales (JSONPlaceholder via Retrofit). Verifican que `UserRepository` se comunica correctamente con la API externa.
- **IMPORTANTE**: Estas pruebas son **exclusivamente para mantenimiento de la app**. Los estudiantes NO deben enfocarse en ellas. El objetivo de aprendizaje son las **pruebas unitarias** (`./gradlew unitTest`), que ejecutan los tests `*Test.java` (sin sufijo `E2E` ni `Integration`). Alli el estudiante debe implementar los casos de prueba para cada clase de dominio.

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

## Memoria entre sesiones (Engram + Context7)
- **Ciclo automático**:
  - `engram_mem_context` al iniciar sesión → `engram_mem_save` tras cada tarea completada → `engram_mem_session_summary` al cerrar
- **Antes de decisiones de arquitectura**, buscar memoria previa con `engram_mem_search` para evitar decisiones contradictorias
- **Documentación técnica**: usar `context7` para consultar APIs de librerías del stack
- **Guardar con `engram_mem_save`**: decisiones, bugs corregidos, configuraciones, patrones nuevos (usar formato `**What**/**Why**/**Where**/**Learned**`)

## Skill Registry

When working on this project, load the relevant skill(s) BEFORE writing any code. Follow ALL patterns and rules from the loaded skill. Multiple skills can apply simultaneously.

| Skill Name | Path | Description / When to Use |
| :--- | :--- | :--- |
| `readme-writer` | `.opencode/skills/readme-writer/SKILL.md` | Úsala cuando necesites crear o mejorar un README.md con documentación moderna de GitHub. Aplica estructura, formato GFM, badges y principios Diátaxis.

