# Getting Started

> Exmaple init gradle

### Compile Code, Test Code, Jar Code

```bash
gradle build
```

### Run

- Local

```bash
gradle bootRun
```

- Background

```bash
nohup gradle bootRun &
```

## Windows

### Testing Application

- Abrir navegador: http://localhost:8081/rest/mscovid/test?msg=testing

## Linux

### Testing Application

- curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing'
