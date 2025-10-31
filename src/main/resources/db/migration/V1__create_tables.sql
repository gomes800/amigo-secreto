CREATE TABLE names (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    footwear_number INTEGER,
    taken BOOLEAN DEFAULT FALSE
);

CREATE TABLE PERSON (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    chosen_name_id BIGINT,
    footwear_number INTEGER,
    CONSTRAINT fk_chosen_name FOREIGN KEY (chosen_name_id) REFERENCES names(id)
);

INSERT INTO names (name, footwear_number, taken) VALUES
('Gabrielle', 38, false),
('João', 44, false),
('Lucas', 40, false),
('Lohan', 44, false);