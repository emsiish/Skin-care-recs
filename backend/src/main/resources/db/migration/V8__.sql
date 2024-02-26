CREATE TABLE user_tag
(
    tag_id  BIGINT NOT NULL,
    user_id BIGINT NOT NULL
);

ALTER TABLE user_tag
    ADD CONSTRAINT fk_user_tag_on_tag FOREIGN KEY (tag_id) REFERENCES tag (id);

ALTER TABLE user_tag
    ADD CONSTRAINT fk_user_tag_on_user FOREIGN KEY (user_id) REFERENCES user (id);