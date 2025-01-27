CREATE TABLE IF NOT EXISTS "userEntity" (
    id BIGINT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    role VARCHAR(50) DEFAULT 'USER' CHECK (role IN ('ROLE_USER', 'ROLE_ADMIN')),
    telegram_link VARCHAR(50) NOT NULL,
    profile_picture VARCHAR(255),
    status VARCHAR(50) NOT NULL CHECK ( status IN ('ACTIVE', 'INACTIVE', 'BLOCKED') ),
    number_of_violations INT CHECK (number_of_violations >= 0),
    last_action TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS settings (
    notification BOOLEAN,
    theme VARCHAR(50) CHECK ( theme IN ('DARK', 'LIGHT', 'SYSTEM') ),
    language VARCHAR(10) CHECK ( language IN ('EN', 'RU')),
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "userEntity" (id) ON DELETE CASCADE
);