CREATE TABLE IF NOT EXISTS products
(
    id  INTEGER PRIMARY KEY ,
    name  VARCHAR(255) ,
    description VARCHAR(4096) ,
    price INTEGER(20),
    inStock BOOLEAN
);