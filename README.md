# Call Center

## Описание

```
  Тестовое задание. Часть описания переформулировано.
```

Сервис для добавления, обновления и получения задач call-центра. Формат обмена данными - JSON.<br>
Все ответы имею следующую структуру:

- timestamp - временная метка
- statusCode - код статуса ответа
- status - текст статуса ответа
- message - тело ответа, может быть значением, JSON-объектом или массивом. Описывается в выходных параметрах

```json
{
    "timestamp": 1632656398216,
    "statusCode": 200,
    "status": "OK",
    "message": "Success"
}
```

```json
{
    "timestamp": 1632665596284,
    "statusCode": 200,
    "status": "OK",
    "message": [
        {
            "taskId": 26,
            "number": 2
        },
        {
            "taskId": 27,
            "number": 3
        }
    ]
}
```

## Используемые технологии:

1. Java 11
2. Apache Maven 3.6.3
3. Spring Framework Boot 2.5.4
   1. Starter
   2. Starter Web
   3. Starter Data Jpa
   4. Starter Test
4. Spring Doc 1.5.10 - Документация
   1. OpenAPI UI
5. Slf4j 1.7.32 - Логирование
6. PostgreSQL 42.2.23
7. Lombok 1.18.20
8. FlywayDB 7.15.0 - Миграция БД
9. H2Database 1.4.200 - БД для тестирования
10. JUnit 4.13.2

## Методы и параметры находящиеся в /api/v1/task:

```
  Документаци в формате JSON доступна по эндпоинту /v3/api-docs, UI - /api-docs
```

- ___Добавление нового задания (/add)___
  - **Входные параметры:**
    - number (целое число, обязательный) - номер задачи
  - **Выходные параметры:**
    - строка - статус добавленния
- ___Получение списка задач (/get)___
  - **Входные параметры:**
    - startDate (дата и время, обязательный) - с каких даты и времени будет поиск
    - finishDate (дата и время, обязательный) - по какие дату и время будет поиск
    - number (целое число) - номер задачи
    - status (COMPLETED/NOT_COMPLETED) - статус задачи
  - **Выходные параметры:**
    - массив объектов - найденные задачи
      - taskId (целое число) - id задачи в системе
      - number (целое число) - номер задачи
      - createDate (дата и время) - дата и время создания задачи
      - updateDate (дата и время) - дата и время последнего обновления
      - status (COMPLETED/NOT_COMPLETED) - статус задачи
      - comment (строка) - комментарий к задаче
- ___Обновление задачи (/update)___
  - **Входные параметры:**
    - taskId (целое число) - id задачи в системе
    - status (COMPLETED/NOT_COMPLETED) - статус задачи
    - comment (строка) - комментарий к задаче
  - **Выходные параметры:**
    - строка - статус обновления
