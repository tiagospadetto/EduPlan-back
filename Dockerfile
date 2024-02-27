# Use a imagem base do OpenJDK para o Java 17
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo build/libs/*.jar
COPY build/libs/eduplan-0.0.1-SNAPSHOT.jar app.jar

# Comando para executar a aplicação quando o contêiner iniciar
CMD ["java", "-jar", "app.jar"]