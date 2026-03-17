# ■■ STAGE 1 : build ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /src
# Copier pom.xml en premier pour profiter du cache Maven
COPY pom.xml .
RUN mvn dependency:go-offline -B # télécharge les dépendances une seule fois
# Copier le code source et compiler
COPY src ./src
RUN mvn package -DskipTests -B # compile + crée le JAR
# ■■ STAGE 2 : image de production ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
FROM eclipse-temurin:17-jre-alpine      
# JRE seulement = ~85 MB
WORKDIR /app
# Récupérer uniquement le JAR produit par le stage 1
COPY --from=build /src/target/tp-junit-*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]