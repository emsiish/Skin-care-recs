CREATE TABLE question
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    question VARCHAR(255)          NULL,
    CONSTRAINT pk_question PRIMARY KEY (id)
);