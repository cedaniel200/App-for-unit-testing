# App-for-unit-testing

Aplicación de consola creada con el fin de practicar pruebas unitarias, es decir, 
debes crearle sus pruebas unitarias. 

### Tecnologías de desarrollo 
La aplicación fue construída con las siguientes herramientas y lenguajes:
* Spring Boot
* Spring Shell
* Retrofit 2
* Okhttp 3
* Gradle
* Java 17

### Empaquetado:
EL proyecto se empaqueta en un .jar Para ello, se debe ejecutar el comando:
                 
    ./gradlew bootJar

Ese comando generará el jar con el nombre de acuerdo a la siguiente configuración ubicada en el archivo build.gradle

    bootJar {
        archiveBaseName = 'unit-testing-app'
        version('1.0.0')
    }

Para la consiguración anterior el jar generado tendra el nombre unit-testing-app-1.0.0.jar La versión debe ser actualizada según los cambios realizados,
ya sean mayores o menores.

### Ejecución:
Para ejecutar unit-testing-app en consola interactiva a través del jar generado:

    java -jar unit-testing-app-[version].jar

Si requieres configurar variables de entorno (como credenciales de email),
consulta la sección [Configuración de variables de entorno](#configuración-de-variables-de-entorno).

Para ejecutar desde el IDE, solo ejecuta la clase **App** ubicada en el paquete
**com.cedaniel200.practice.**

### Pruebas

**Para estudiantes — ejecutar únicamente:**

    ./gradlew unitTest

Este comando ejecuta solo las pruebas unitarias (archivos `*Test.java`) y es el único que debes usar mientras estudias.

Además, puedes generar el reporte de cobertura solo para las pruebas unitarias con:

    ./gradlew unitTest jacocoUnitTestReport

El reporte se genera en `build/reports/jacoco/jacocoUnitTestReport/html/index.html`.

La cobertura está configurada con JaCoCo y excluye los paquetes `config/`, `console/`, `model/` y la clase `App`, ya que el objetivo es medir solo la capa de dominio.

**Para mantenimiento de la aplicación:**

| Comando | Qué ejecuta |
|---------|-------------|
| `./gradlew e2eTest` | Pruebas end-to-end de consola (`*E2E*.java`) |
| `./gradlew integrationTest` | Pruebas de integración con servicios externos (`*Integration*.java`) |
| `./gradlew test` | Todas las pruebas |
| `./gradlew clean test jacocoTestReport` | Todas + reporte de cobertura global |

El reporte de cobertura global se genera en `build/reports/jacoco/test/html/index.html`.

> Las pruebas E2E e integrationTest consumen conexiones HTTP externas, tardan más y no son necesarias para el aprendizaje de pruebas unitarias.

### Comandos de la aplicación 
La aplicación cuenta con los siguientes comandos o funcionalidades:
    
**Calculator Commands**

    add [--first-number] int  [--second-number] int
    
    divide [--dividend] int  [--divider] int
    
    multiply [--first-number] int  [--second-number] int
    
    subtract [--first-number] int  [--second-number] int

**Email Commands**
    
    send [--to] string  [--subject] string  [--message] string

**Greeting Commands** (--language soportados: es, en o pt)

    greet [--language] string

**User Commands** (Consumen los servicios en https://jsonplaceholder.typicode.com)

    find-by-id [--id] int
    
    list
    
    
    
Para mayor informacion de cada comando ejecute desde la consola:

    help nombre_comando
    
Ejemplo: **help add**, lo que nos mostraría la siguiente información:

    NAME
            add - Add two whole numbers
    
    SYNOPSYS
            add [--first-number] int  [--second-number] int
    
    OPTIONS
            --first-number  int
    
                    [Mandatory]
    
            --second-number  int
    
                    [Mandatory]

Para comandos adicionales ejecute desde consola el comando 'help' para obtener información acerca de las instrucciones disponibles.



### Diagrama de Arquitectura
![Arquitectura](Arquitectura.png)

    + exception
        Capa trasverla donde se encuentran las lases que controlan las posibles excepciones
        técnicas y de negocios que se presentan durante la ejecución de la aplicación
    + model
        Capa trasverla donde se encuentran las clases relacionadas con el modelo de dominio
    + console
        Capa donde se encuentran las clases que representan cada uno de los comandos permititdos
        es el punto de entrada para cada operación o fución que se quiera realizar
    + domain
        Capa donde se encuentran las clases con la lógica y/o reglas de negocio
    + persistence
        Capa donde se encuentran las clases que implementan el patrón DAO y/o
        aquellas que acceden a datos
    + repository
        Capa donde se encuentran las clases que implementan el patrón Repository, puede 
        a su vez tener comunicación con la capa de persistence, ya que, el patrón DAO y 
        Persistence no son excluyentes
    + service
        Capa donde se encuentran las clases que representan clientes HTTP para comunicación con 
        servicios en la nube
        
### Configuración de variables de entorno

### Email

| Variable | Descripción |
|---|---|
| `EMAIL_USERNAME` | Correo Gmail para enviar |
| `EMAIL_PASSWORD` | App password de Gmail |

Ejemplo:

```bash
export EMAIL_USERNAME=micorreo@gmail.com
export EMAIL_PASSWORD=abcd1234efgh5678
```

Si no se configuran, el comando `send` usará los valores por defecto y fallará con un mensaje de error.

### Si tiene alguna pregunta, puede escribirme a cdanielmg200@gmail.com  