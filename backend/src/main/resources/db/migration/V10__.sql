CREATE TABLE product_tag
(
    product_id BIGINT NOT NULL,
    tag_id     BIGINT NOT NULL
);

ALTER TABLE product_tag
    ADD CONSTRAINT fk_protag_on_product FOREIGN KEY (product_id) REFERENCES product (id);

ALTER TABLE product_tag
    ADD CONSTRAINT fk_protag_on_tag FOREIGN KEY (tag_id) REFERENCES tag (id);
