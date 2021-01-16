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

#### Inicializar APP Local -  via SH


#### Inicializar APP Local - manualmente

Inicializar banco de dados local:<br/>
docker-compose up -d

Inicializar a aplicacao (via linha de comando):<br/>
./gradlew bootRun -Dspring.profiles.active=local

Inicializar a aplicacao (via Intellij):<br/>
Configuration > Application<br/>
Name: GpsApplicationKt <br/>
Main Class: com.xyinc.poi.GpsApplicationKt<br/>
Module: gps.main<br/>
JRE: Java 11

---

#### Acessar APP Local (via Swagger)

URL Swagger (Local):<br/>
http://localhost:8080/swagger-ui.html

---

#### Exemplos de requisição via CURL

##### Consulta de POIs (exemplo)
curl -X GET 'http://localhost:8080/pois'

##### Cadastro de POI
curl -d '{"name":"Academia", "xCoordinates":20, "yCoordinates":10}' -H "Content-Type: application/json" -X POST 'http://localhost:8080/pois'

##### Consulta de POIs baseado em um ponto de referencia e distancia
curl -X GET 'http://localhost:8080/pois/references?xCoordinateReference=20&yCoordinateReference=10&distance=10'

---

## HealthCheck API (String Actuator)
http://localhost:8080/actuator/health

---

## TroubleShooting

