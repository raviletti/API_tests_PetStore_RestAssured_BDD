#language:ru
@test

  #TODO: api_key

Функционал: Тестирование сервиса PetStore
  - Удаление юзера DELETE запросом
  - После удаления юзера, GET запросом проверяем отсутствие данного юзера в базе

  Сценарий: Удаление юзера

    Если создать юзера
      | id        | 1234             |
      | name      | toDelete         |
      | firstname | test             |
      | lastname  | lastest          |
      | email     | delete@gmail.com |
      | pass      | delety           |
      | phone     | 1282822          |
    * создать запрос
      | method | path  | body    |
      | POST   | /user | ${name} |
    * добавить header
      | Content-Type | application/json |
    * отправить запрос
    * статус код 200

    # Удаляем пользователя
    * создать запрос
      | method | path         |
      | DELETE | /user/{name} |
    * добавить header
      | accept | application/json |
   #   | api_key | special-key      |
    * отправить запрос
    * статус код 200
    * извлечь данные
      | response_name | message |
    * сравнить значения
      | ${name} | == | ${response_name} |

    # Проверяем что юзер отсутствует в базе
    * создать запрос
      | method | path         |
      | GET    | /user/{name} |
    * отправить запрос
    * статус код 404