CREATE TABLE doctor
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    name         VARCHAR(255)          NULL,
    phone_number VARCHAR(255)          NULL,
    email        VARCHAR(255)          NULL,
    hospital     VARCHAR(255)          NULL,
    image        VARCHAR(255)          NULL,
    CONSTRAINT pk_doctor PRIMARY KEY (id)
);