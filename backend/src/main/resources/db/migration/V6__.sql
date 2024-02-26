CREATE TABLE doctor_rating
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    comment   VARCHAR(255)          NULL,
    rating    DOUBLE                NULL,
    doctor_id BIGINT                NULL,
    user_id   BIGINT                NULL,
    CONSTRAINT pk_doctorrating PRIMARY KEY (id)
);

ALTER TABLE doctor_rating
    ADD CONSTRAINT FK_DOCTORRATING_ON_DOCTOR FOREIGN KEY (doctor_id) REFERENCES doctor (id);

ALTER TABLE doctor_rating
    ADD CONSTRAINT FK_DOCTORRATING_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);