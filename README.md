# T4

Работу выполнил: Апасов Александр

Описание эндпоинтов:
## GET http://localhost:8080/products
Входные параметры: 

    *String name - при наличии фильтрует все товары по имени или его части.
    *Integer limit - при наличии выводит с ценой равной limit
    *Boolean isLowLimit - при значении true вместе с переданным limit выводятся товары больше цены (limit). При значении false выводит товары ниже цины (limit)
    *Boolean isInStock - при значении true выводятся товары, которые есть в наличии
    *Boolean sortByName - при значении true выводятся товары отсортированные по имени
    *Boolean sortByPrice - при значении true выводятся товары отсортированные по цене
    *Integer limitElements - при наличии выводятся первые элементы, количество которых равно limitElements
    Все параметры необязательные. Фильры могут работать вместе с сортировками и ограничением количества вывода товаров. Возможность работать фильтрам накладываться друг на друга, как и сортировкам, пока не предусмотрена.

Выводит массив из товаров.

## GET http://localhost:8080/products/{id}
Входные параметры -

Выводит единственный товар по id

## POST http://localhost:8080/products
Входные параметры: товар в формате Json

Cоздает новый товар. Выполняет проверки. Выводит сообщение об ошибке при негативном ответе.

## PUT http://localhost:8080/products/{id}
Входные параметры: -товар в формате Json

Проверяет существует ли товар. Заменяет его переданным при положительном ответе. При негативном ответе выводит сообщение об ошибке.

## DELETE http://localhost:8080/products{id}
Входные параметры: -

Проверяет существует ли товар. Удаляет его при положительном ответе. При негативном ответе выводит сообщение об ошибке.

