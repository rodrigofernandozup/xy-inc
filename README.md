# xy-inc
XY Inc - POI - GPS

Serviços associados a Ponto de Interesse (POI)

---
## Stack

- Java 11
- Gradle
- Kotlin
- MySQL
- Docker / Docker Compose

---

## Onboarding

#### Inicializar APP Local - manualmente

###### Inicializar banco de dados local:<br/>
    docker-compose up -d

###### Implantação de scripts de banco de dados local:<br/>
    ./gradlew -Dflyway.configFiles=src/main/resources/flyway.conf flywayRepair flywayMigrate flywayInfo

###### Inicializar a aplicacao (via linha de comando):<br/>
    ./gradlew bootRun -Dspring.profiles.active=local

---

###### Inicializar a aplicacao (via Intellij):<br/>
    Configuration > Application<br/>
    Name: PoiApplicationKt <br/>
    Main Class: com.xyinc.poi.PoiApplicationKt<br/>
    Module: poi.main<br/>
    JRE: Java 11

---

###### Inicializar APP Local -  via SH
    ./startup.sh <br/>
OBS.:<br/>
- conceder permissão de execução no script via comando: 'chmod +x startup.sh'
---

#### Acessar APP Local (via Swagger)
 
##### URL Swagger (Local):
http://localhost:8080/swagger-ui.html

---

#### Exemplos de requisição via CURL

###### Consulta de POIs (exemplo)
    curl -i -X GET 'http://localhost:8080/pois' && echo

###### Cadastro de POI
    curl -i -d '{"name":"Academia", "xCoordinates":20, "yCoordinates":10}' -H "Content-Type: application/json" -X POST 'http://localhost:8080/pois' && echo

###### Consulta de POIs baseado em um ponto de referencia e distancia
    curl -i -X GET 'http://localhost:8080/pois/references?xCoordinateReference=20&yCoordinateReference=10&distance=10' && echo

---

## HealthCheck API (String Actuator)
http://localhost:8080/actuator/health

---

## Manuais de Instalação e  TroubleShooting

Instalação Docker Compose <br/>
https://docs.docker.com/compose/install/

Configuração para rodar docker compose com usuário não root <br/>
https://docs.docker.com/engine/install/linux-postinstall/