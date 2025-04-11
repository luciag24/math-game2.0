# DetskaApp

## Spustenie aplikácie pomocou Dockeru

### Predpoklady
- Nainštalovaný Docker
- Nainštalovaný Docker Compose

### Inštrukcie

1. Naklonujte repozitár:
```bash
git clone https://github.com/your-username/math-game2.0.git
cd math-game2.0
```

2. Spustite aplikáciu pomocou Docker Compose:
```bash
docker-compose up --build
```

Aplikácia bude dostupná na adrese: http://localhost:8080

### Zastavenie aplikácie
```bash
docker-compose down
```

### Poznámky
- Aplikácia používa Java 23
- Port 8080 musí byť voľný na vašom systéme
- Pre vývoj môžete upraviť súbory v adresári `src` a zmeny sa automaticky prejavia v kontajneri 