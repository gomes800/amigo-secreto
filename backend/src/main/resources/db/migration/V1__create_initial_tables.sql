CREATE TABLE users (
    user_id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_date DATE,
    gender VARCHAR(20),
    register_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    wish_list TEXT,
    preferences TEXT
);

CREATE TABLE groups (
    group_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    size INTEGER,
    create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    admin_id BIGINT,
    description TEXT,
    event_date TIMESTAMP,
    price_limit DECIMAL(10, 2),
    draw_completed BOOLEAN DEFAULT FALSE,
    rules TEXT,
    CONSTRAINT fk_admin FOREIGN KEY (admin_id) REFERENCES users(user_id)
);

CREATE TABLE group_participants (
    group_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    PRIMARY KEY (group_id, user_id),
    CONSTRAINT fk_group FOREIGN KEY (group_id) REFERENCES groups(group_id) ON DELETE CASCADE,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE draws (
    draw_id BIGSERIAL PRIMARY KEY,
    group_id BIGINT NOT NULL,
    giver_id BIGINT NOT NULL,
    receiver_id BIGINT NOT NULL,
    draw_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    revealed BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_draw_group FOREIGN KEY (group_id) REFERENCES groups(group_id) ON DELETE CASCADE,
    CONSTRAINT fk_giver FOREIGN KEY (giver_id) REFERENCES users(user_id),
    CONSTRAINT fk_receiver FOREIGN KEY (receiver_id) REFERENCES users(user_id),
    CONSTRAINT check_different_users CHECK (giver_id != receiver_id)
);


CREATE INDEX idx_group_admin ON groups(admin_id);
CREATE INDEX idx_draw_group ON draws(group_id);
CREATE INDEX idx_draw_giver ON draws(giver_id);
CREATE INDEX idx_user_register_date ON users(register_date);