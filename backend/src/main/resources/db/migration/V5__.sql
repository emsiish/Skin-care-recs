CREATE TABLE product
(
    id    BIGINT AUTO_INCREMENT NOT NULL,
    name  VARCHAR(255)          NULL,
    brand VARCHAR(255)          NULL,
    type  VARCHAR(255)          NULL,
    image VARCHAR(255)          NULL,
    price DOUBLE                NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);