# Eduplan BackEnd API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![Static Badge](https://img.shields.io/badge/docker-%2523ED8B00.svg?style=for-the-badge&logo=Docker&logoColor=white&color=blue)

O repositório "Eduplan-back" contém o código-fonte da API backend para o projeto **Eduplan**, uma plataforma de gerenciamento de planos de aulas. Esta API é responsável por permitir aos usuários criar, visualizar, atualizar e excluir planos de aulas de forma eficiente e segura.

API utiliza as tecnologias: **Java, Java Spring, Flyway Migrations, PostgresSQL como database,Spring Security, JWT para controle de autenticação e Docker.**

## Tábela de conteúdo:
- [Requisitos](#requisitos)
- [Instalação](#instalação)
- [Configuração do Banco de Dados](#configuração-do-banco-de-dados)


## Requisitos

* Java 17
* PostgreSQL 15.4
* Gradle 8.2


## Instalação

1. Clonar repositório:

```bash
git clone https://github.com/tiagospadetto/EduPlan-back.git
```
2. Checkout na branch develop
```bash
git checkout develop
```

## Configuração do Banco de Dados

Antes de executar o projeto, você precisará configurar o banco de dados PostgreSQL:

1. Instale [PostgresSQL](https://www.postgresql.org/)

2. Crie um banco de dados no PostgreSQL com o nome eduplan.

3. Atualize as configurações do banco de dados no arquivo src/main/resources/application.properties com suas informações de conexão. 
Por exemplo:

```markdown
spring.datasource.url=${db-url:jdbc:postgresql://localhost:5432/eduplan}
spring.datasource.username=${db-user:postgres}
spring.datasource.password=${db-password:8l&6AVDF06W1d%xkHt}
spring.datasource.driver-class-name=org.postgresql.Driver
```




