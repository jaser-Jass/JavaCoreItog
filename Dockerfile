# Первый этап сборки
FROM maven:3.8.6-openjdk-11-slim AS build-stage

WORKDIR /app

# Копируем файлы проекта
COPY pom.xml .
COPY src ./src

# Выполняем сборку проекта
RUN mvn clean package -DskipTests -X


# Второй этап (финальный образ)
FROM openjdk:11-slim AS runtime-stage

WORKDIR /app

# Копируем JAR файл из первого этапа
COPY --from=build-stage /app/target/*.jar app.jar

# Указываем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]



