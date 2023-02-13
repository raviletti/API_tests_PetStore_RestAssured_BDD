#language:ru
@test

  #TODO: api_key

Функционал: Тестирование сервиса PetStore
  - Создание питомца для последующего изменения его данных
  - После обновления питомца, GET запросом проверяем внесенные изменения

  Сценарий: Обновление данных питомца

    Если создать питомца
      | id        | 7890                   |
      | category  | udpatingPet            |
      | name      | toUpdatePet            |
      | photoUrls | https://clck.ru/33Nfqa |
      | status    | empty                  |
      | tags      | dog                    |
    * создать запрос
      | method | path | body        |
      | POST   | /pet | ${name} |
    * добавить header
      | Content-Type | application/json |
    * отправить запрос
    * статус код 200
    * извлечь данные
      | pet_id        | id |
      | previous_name | name  |
    * сравнить значения
      | ${pet_id} | != | null |

    # Вторая часть теста
    Если создать питомца
      | id       | 7890        |
      | category | udpatingPet |
      | name     | updatedPet  |
    * создать запрос
      | method | path | body       |
      | PUT    | /pet | ${name} |
    * добавить header
      | Content-Type | application/json |
      | accept | application/json |
    * отправить запрос
    * статус код 200

  # Проверка изменений
    * создать запрос
      | method | path      |
      | GET    | /pet/{id} |
    * добавить header
      | accept | application/json |
    * отправить запрос
    * статус код 200
    * извлечь данные
      | resp_name   | name |
      | resp_pet_id | id   |
    * сравнить значения
      | ${pet_id}        | == | ${resp_pet_id} |
      | ${previous_name} | != | ${resp_name}   |
      | ${resp_name}     | == | updatedPet     |