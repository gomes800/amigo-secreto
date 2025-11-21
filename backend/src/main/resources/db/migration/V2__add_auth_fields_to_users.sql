ALTER TABLE users
ADD COLUMN email VARCHAR(255),
ADD COLUMN provider VARCHAR(50),
ADD COLUMN provider_id VARCHAR(255),
ADD COLUMN username VARCHAR(255),
ADD COLUMN avatar_url VARCHAR(500);

UPDATE users SET email = 'temp_user_' || user_id || '@example.com',
                 provider = 'legacy',
                 provider_id = user_id::TEXT
WHERE email IS NULL OR provider IS NULL OR provider_id IS NULL;

ALTER TABLE users
ALTER COLUMN email SET NOT NULL,
ALTER COLUMN provider SET NOT NULL,
ALTER COLUMN provider_id SET NOT NULL;

CREATE UNIQUE INDEX idx_users_email ON users(email);
CREATE UNIQUE INDEX idx_users_provider_id ON users(provider, provider_id);
CREATE UNIQUE INDEX idx_users_username ON users(username);