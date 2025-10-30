CREATE TABLE person (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    chosen_person_id BIGINT,
    footwear_number INTEGER,
    CONSTRAINT fk_chosen_person FOREIGN KEY (chosen_person_id) REFERENCES person(id)
);