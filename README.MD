[![Build Status](https://travis-ci.org/VadimShein/carwash.svg?branch=master)](https://travis-ci.org/VadimShein/carwash)

# <p align="center">Сервис онлайн записи</p>

## Описание:
Приложение производит онлайн запись на автомойку. Используется Rest архитектура.  

Пользователи регистрируются, затем авторизируются с использованием JWT token.
Пользователь выбирает услугу и записывается на нужное время.
Если время необходимое для услуги пересекается с уже забронированными услугами, то приложение оповещает об этом.
Также пользователь может посмотреть оставшееся время, в формате ХХ days and xx:yy:zz.
Администратор может добавлять, удалять предоставляемые услуги и удалять пользователей.

Пользователь по умолчанию: username: user, password: 123  
Администратор по умолчанию: username: admin, password: 123  
JWT токен имеет формат: Bearer your_token  

## Используемые технологии:
* Java 13, Rest API
* Spring Boot (Data, Security, JWT authorization)
* PostgreSQL, Flyway
* Maven, Swagger, GraphQL

## Просмотр документаци по Rest API через браузер
```
http://localhost:8080/swagger-ui/index.html
```

## Использование GraphQL
Просмотр документации и отладка в клиенте GraphiQL через браузер
```
http://localhost:8080/graphiql?path=/graphql
```

1. Регистрация пользователя 
```
mutation($user: UserInput) {  
    registerUser(user: $user) {  
        username  
        enabled  
    }  
}  
{  
    "user": {  
        "username": "user_1",  
        "password": "123"  
    }  
}  
```
 
2. Поиск пользователя по имени
```
query {
    findUserByName(username: "user") {
        username 
        password 
        enabled 
        roles {
            name
        }
    }
}
```

3. Получение списка услуг
```
query {
    getAllEmployments{
        id 
        name 
        duration
    }
}
```
  
4. Запись на услугу
```
mutation($task:TaskInput) {
    addTask(task: $task) {
        id
        startTime
        finishTime
        status
        userId
        employmentId
    } 
}
{
    "task": {
        "startTime": "2023-05-20T16:00:00+03:00",
        "user": {"id": 1}, 
        "employment":{"id": 1}
    }
}
```
  
5. Просмотр оставшегося времени до предоставления услуги
```
query {
    getTimeLeft(taskId: 8) {
        taskId 
        userId 
        employmentId 
        timeLeft 
        status
    }
}
```
 
6. Регистрация администратора
```
mutation($user: UserInput) {
    registerAdmin(user: $user) {
        username
        enabled 
    } 
}
{
    "user": {
    "username": "admin_1",
    "password": "123"
    }
}
```

7. Удаление пользователя
```
mutation($id: ID) {
    deleteUser(id: $id)
}
{
    "id": 6
}
```

8. Добавление услуги в список предоставляемых услуг
```
mutation($employment: EmploymentInput) {
    addEmployment(employment: $employment) {
        name
        duration
    } 
}
{
    "employment": {
        "name": "Wash a car",
        "duration": "00:10:10"
    }
}
```

9. Удаление услуги из списка предоставляемых услуг
```
mutation($id: ID) {
    deleteEmployment(id: $id)
}
{
    "id": "3"
}
```

## Использование REST API пользователем

1. Регистрация пользователя  
```
curl -i --header "Content-Type: application/json" \
--request POST 'http://localhost:8080/user/registration' \
-d '{"username":"user_1", "password":"123"}'
```

2. Получение JWT токена  
```
curl -i --header "Content-Type: application/json" \
--request POST 'http://localhost:8080/login' \
-d '{"username":"user_1", "password":"123"}'
```

3. Получение списка услуг  
```
curl -i -H "Authorization: Bearer your_token" \
'http://localhost:8080/employment'
```

4. Запись на услугу  
```
curl -i --header "Content-Type: application/json" \
-H "Authorization: Bearer your_token" \
--request POST 'http://localhost:8080/task' \
-d '{"startTime":"2023-02-20T16:00:00", "user": {"id": 1}, "employment":{"id": 1}}'
```

5. Просмотр оставшегося времени до предоставления услуги  
```
curl -i -H "Authorization: Bearer your_token" \
'http://localhost:8080/task/1'
```

## Использование REST API администратором

1. Регистрация администратора  
```
curl -i --header "Content-Type: application/json" \
--request POST 'http://localhost:8080/admin/registration' \
-d '{"username":"user_1", "password":"123"}'
```

2. Удаление пользователя (только с правами администратора)  
```
curl -i --header "Content-Type: application/json" \
-H "Authorization: Bearer your_token" \
--request DELETE 'http://localhost:8080/admin/1'
```

3. Добавление услуги в список предоставляемых услуг (только с правами администратора)
```  
curl -i --header "Content-Type: application/json" \
-H "Authorization: Bearer your_token" \
--request POST 'http://localhost:8080/employment' \
-d '{"name": "Engine wash", "duration": "00:15:00"}'
```

4. Удаление услуги из списка предоставляемых услуг (только с правами администратора)
```  
curl -i --header "Content-Type: application/json" \
-H "Authorization: Bearer your_token" \
--request DELETE 'http://localhost:8080/employment/1'
```

## Запуск проекта:
1. Скопировать проект 
```
git clone https://github.com/VadimShein/carwash
```

2. Создать базу данных carwash 

3. Запустить создание таблиц с помощью flyway
``` 
mvn clean flyway:migrate
```

4. Собрать проект
```
mvn clean package -DskipTests
```

4. Запустить приложение, адрес по умолчанию  [http://localhost:8080/](http://localhost:8080/)
```
mvn spring-boot:run
```

## Контакты:
[![alt-text](https://img.shields.io/badge/-telegram-grey?style=flat&logo=telegram&logoColor=white)](https://t.me/SheinVadim)
[![alt-text](https://img.shields.io/badge/@%20email-005FED?style=flat&logo=mail&logoColor=white)](mailto:shein.v94@mail.ru)
