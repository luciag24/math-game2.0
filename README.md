# Math Game 2.0

Matematická hra pre žiakov základných škôl, ktorá pomáha precvičovať matematické príklady a jednotkové prevody.

## Funkcie

- Matematické príklady pre 1. až 4. ročník
- Jednotkové prevody
- Zvukové efekty pre správne/nesprávne odpovede
- Sledovanie progresu užívateľa
- Moderné užívateľské rozhranie

## Technológie

- Java
- Spring Boot
- HTML/CSS/JavaScript
- Maven
- Docker (voliteľné)

## Spustenie aplikácie

### Možnosť 1: Bez Dockera

#### Predpoklady
- Java 23
- Maven

#### Kroky
1. Naklonujte repozitár:
```bash
git clone https://github.com/luciag24/math-game2.0.git
cd math-game2.0
```

2. Zostavte projekt:
```bash
mvn clean package
```

3. Spustite aplikáciu:
```bash
java -jar target/DetskaApp-1.0-SNAPSHOT.jar
```

### Možnosť 2: S Dockerom

#### Predpoklady
- Docker
- Docker Compose

#### Kroky
1. Naklonujte repozitár:
```bash
git clone https://github.com/luciag24/math-game2.0.git
cd math-game2.0
```

2. Spustite aplikáciu pomocou Docker Compose:
```bash
docker-compose up --build
```

## Prístup k aplikácii

Po spustení je aplikácia dostupná na adrese: http://localhost:8080

## Vývoj

### Štruktúra projektu
- `src/main/java/org/example/` - hlavný kód aplikácie
  - `controllers/` - REST kontroléry
  - `services/` - biznis logika
  - `models/` - dátové modely
  - `repositories/` - prístup k dátam
- `src/main/resources/` - resources súbory
  - `static/` - statické súbory (CSS, JavaScript, obrázky)
  - `templates/` - HTML šablóny 