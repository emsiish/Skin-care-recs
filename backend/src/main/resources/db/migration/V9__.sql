CREATE TABLE question_tag
(
    question_id BIGINT NOT NULL,
    tag_id      BIGINT NOT NULL
);

ALTER TABLE question_tag
    ADD CONSTRAINT fk_quetag_on_question FOREIGN KEY (question_id) REFERENCES question (id);

ALTER TABLE question_tag
    ADD CONSTRAINT fk_quetag_on_tag FOREIGN KEY (tag_id) REFERENCES tag (id);