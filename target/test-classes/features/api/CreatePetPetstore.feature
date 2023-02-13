#language:ru
@test

Функционал: Тестирование сервиса PetStore
  - Создание нового питомца POST запросом с телом из Data table
  - После создания нового питомца, GET запросом запрашиваем данного питомца и проверяем, что его данные соответствует данными из тела запроса

  Сценарий: Создание питомца

     Если создать питомца
       | id        | 4356                   |
       | category  | homePet                |
       | name      | snickers               |
       | photoUrls | https://clck.ru/33Nfqa |
       | status    | empty                  |
       | tags      | cat                    |
    * создать запрос
      | method | path | body    |
      | POST   | /pet | ${name} |
    * добавить header
      | Content-Type | application/json |
    * отправить запрос
    * статус код 200
    * извлечь данные
      | pet_id | id |
    * сравнить значения
      | ${pet_id} | != | null |

    # Вторая часть теста - запрос пета и проверка его данных
    * создать запрос
      | method | path      |
      | GET    | /pet/{id} |
    * добавить header
      | accept | application/json |

    * отправить запрос
    * статус код 200
    * извлечь данные
      | resp_name   | name  |
      | resp_pet_id | id |

    * сравнить значения
      | ${pet_id} | == | ${resp_pet_id} |
      | ${name}   | == | ${resp_name}   |