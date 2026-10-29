# Берем официальный образ Java (JDK) на базе Alpine Linux (он легкий)
FROM eclipse-temurin:17-jdk-alpine

# Создаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем наш исходный код в контейнер
COPY Main.java .

# Компилируем Java-код
RUN javac Main.java

# Открываем порт 8080
EXPOSE 8080

# Команда, которая выполнится при запуске контейнера
CMD ["java", "Main"]