CREATE TABLE users
(
    user_id    BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name  VARCHAR(50),
    email      VARCHAR(70) UNIQUE,
    password   VARCHAR(50)
);

CREATE TABLE orders
(
    order_id    BIGSERIAL UNIQUE,
    user_id     BIGINT REFERENCES users (user_id),
    PRIMARY KEY (order_id, user_id),
    created_at  TIMESTAMP,
    status      VARCHAR(50),
    total_price DECIMAL(12,2)
);

CREATE TABLE order_items
(
    game_id  BIGINT REFERENCES games (game_id),
    order_id BIGINT REFERENCES orders (order_id),
    PRIMARY KEY (game_id, order_id),
    quantity INTEGER
);

CREATE TABLE games
(
    game_id       BIGSERIAL PRIMARY KEY,
    name          VARCHAR(200),
    price         DECIMAL(12,2) NOT NULL CHECK ( price > 0 ),
    poster        VARCHAR(1000),
    description   VARCHAR(1000),
    platforms      VARCHAR(20),
    youtube_link  VARCHAR(1000),
    mc_score      DECIMAL(11, 8) CHECK ( mc_score >= 0 AND mc_score <= 100 ),
    user_score    DECIMAL(11, 8) CHECK ( user_score >= 0 AND user_score <= 100 ),
    overall_score DECIMAL(11, 8) CHECK ( overall_score >= 0 AND user_score <= 100 ),
    developer     VARCHAR(150),
    publisher     VARCHAR(150),
    release_date  DATE,
    setting       VARCHAR(50),
    main_genre    VARCHAR(50),
    side_genre1   VARCHAR(50),
    side_genre2   VARCHAR(50),
    is_indie      BOOLEAN,
    processor     VARCHAR(150),
    graphics_card VARCHAR(150),
    ram           INTEGER,
    free_memory   DOUBLE PRECISION
);


CREATE TABLE comments
(
    comment_id   BIGSERIAL UNIQUE,
    game_id      BIGINT REFERENCES games (game_id),
    user_id      BIGINT REFERENCES users (user_id),
    primary key (comment_id, game_id, user_id),
    published_at TIMESTAMP,
    content      VARCHAR(500)
);
