CREATE TABLE product_rating
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    comment    VARCHAR(255)          NULL,
    rating     DOUBLE                NULL,
    product_id BIGINT                NOT NULL,
    user_id    BIGINT                NOT NULL,
    CONSTRAINT pk_productrating PRIMARY KEY (id)
);

ALTER TABLE product_rating
    ADD CONSTRAINT FK_PRODUCTRATING_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

ALTER TABLE product_rating
    ADD CONSTRAINT FK_PRODUCTRATING_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);