CREATE SEQUENCE IF NOT EXISTS knowledge_id_seq START 1 INCREMENT 1;

CREATE TABLE IF NOT EXISTS knowledge
(
    id        BIGINT PRIMARY KEY,
    parent_id BIGINT,
    namespace VARCHAR NOT NULL,
    name      VARCHAR NOT NULL,
    CONSTRAINT fk_knowledge_parent FOREIGN KEY (parent_id) REFERENCES knowledge (id)
);