# xy-inc
XY Inc - POI - GPS

Serviços associados a Ponto de Interesse (POI)

---
## Stack / Dependências / Ferramentas 

- Java 11 (https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html)
- Gradle (https://docs.gradle.org/current/userguide/building_java_projects.html)
- Kotlin (https://kotlinlang.org/docs/kotlin-pdf.html)
- H2 (https://www.h2database.com/html/main.html e https://www.baeldung.com/spring-boot-h2-database)
- Flyway (https://flywaydb.org/)
- Swagger (https://swagger.io/)

---

## Onboarding

### Inicializar APP Local (automaticamente via ShellScript)
    ./startup.sh
OBS.:<br/>
- conceder permissão de execução no script via comando:
  `chmod +x startup.sh`
- este comando realiza o build da aplicação, executa as implantações dos scripts de banco de dados (criação da tabela e carga inicial de dados via Flyway) e inicializa a aplicação
- a porta 8080 deve estar livre (para identificar se existe algum processo na porta :8080, pode ser usado o comando: `lsof -i :8080`) 

#### Inicializar APP Local - manualmente (opção alternativa)

###### Build da aplicação:<br/>
    ./gradlew clean build

###### Implantação de scripts de banco de dados local (via Flyway):<br/>
    ./gradlew -Dflyway.configFiles=src/main/resources/flyway.conf flywayRepair flywayMigrate flywayInfo

###### Inicializar a aplicacao (via linha de comando):<br/>
    ./gradlew bootRun -Dspring.profiles.active=local

---

###### Inicializar a aplicação (via Intellij):<br/>
    Configuration > Application
    Name: PoiApplicationKt
    Main Class: com.xyinc.poi.PoiApplicationKt
    Module: poi.main
    JRE: Java 11

OBS.:<br/>
- o processo de implantação de scripts via flyway deve ser previamente executado

---

#### Acessar APP Local (via Swagger)
 
##### URL Swagger (Local):
http://localhost:8080/swagger-ui.html

---

#### Exemplos de requisição via CURL

###### Consulta de POIs (exemplo)
    curl -i -X GET 'http://localhost:8080/pois' && echo

###### Cadastro de POI (exemplo)
    curl -i -d '{"name":"Academia", "x":100, "y":100}' -H "Content-Type: application/json" -X POST 'http://localhost:8080/pois' && echo

###### Consulta de POIs baseado em um ponto de referencia e distância (exemplo)
    curl -i -X GET 'http://localhost:8080/pois/references?xCoordinateReference=20&yCoordinateReference=10&distance=10' && echo

---

## HealthCheck API (String Actuator)
http://localhost:8080/actuator/health

---

## Interface Client para acesso ao Banco de dados (H2 Console)
http://localhost:8080/h2-console

Dados para acesso (a app deve estar rodando):
  
    Driver Class: org.h2.Driver 
    JDBC URL: jdbc:h2:./database/poidb
    Username: poi
    Password: poi

---

## Calculo àrea da circunferência - Racional

![Calculo àrea da circunferência](docs/circunferencia.png?raw=true "Calculo àrea da circunferência")

O ponto "O(a,b)" representa as coordenadas de referência a partir de onde se deseja consultar os POIs; o raio "r" representa a distância máxima até onde se deseja que os POIs retornados estejam posicionados; e o ponto "P(x,y)" representa as coordenadas máximas do perímetro de consulta de POIS a partir do ponto de referência - O(a,b) - e o raio - r - especificados  

Referência: https://brasilescola.uol.com.br/matematica/circunferencia.htm [Acessado em 13/01/2021]

---

## Manuais de Instalação e  TroubleShooting

Instalação Docker Compose <br/>
https://docs.docker.com/compose/install/

Configuração para rodar docker compose com usuário não root <br/>
https://docs.docker.com/engine/install/linux-postinstall/