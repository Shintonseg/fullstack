# ---- Build stage ----
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# copy wrapper & pom first to leverage cache
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# normalize line endings and make wrapper executable
RUN sed -i 's/\r$//' mvnw && chmod +x mvnw

# download deps
RUN ./mvnw -B -q -DskipTests dependency:go-offline

# now copy sources and build
COPY src ./src
RUN ./mvnw -B -q -DskipTests package

# ---- Run stage ----
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75"
CMD ["sh","-c","java -Dserver.port=${PORT:-8080} -jar app.jar"]
