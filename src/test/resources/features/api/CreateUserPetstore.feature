#language:ru
@test

Функционал: Тестирование сервиса PetStore
  - Создание нового юзера POST запросом с телом из Data table
  - После создания нового питомца, GET запросом запрашиваем данного питомца и проверяем, что его данные соответствует данными из тела запроса

  Сценарий: Создание юзера

    Если создать юзера
      | id        | 4367          |
      | name      | landr         |
      | firstname | rav           |
      | lastname  | lat           |
      | email     | rav@gmail.com |
      | pass      | qwerty        |
      | phone     | 88005553535   |
    * создать запрос
      | method | path  | body    |
      | POST   | /user | ${name} |
    * добавить header
      | Content-Type | application/json |
    * отправить запрос
    * статус код 200
    * извлечь данные
      | user_id | message |
    * сравнить значения
      | ${user_id} | != | null |

    # Вторая часть теста - запрос юзера и проверка его данных
    * создать запрос
      | method | path         |
      | GET    | /user/{name} |
    * добавить header
      | accept | application/json |

    * отправить запрос
    * статус код 200
    * извлечь данные
      | resp_username | username  |
      | resp_user_id  | id  |

    * сравнить значения
      | ${user_id}       | == | ${resp_user_id} |
      | ${resp_username} | == | ${name}         |