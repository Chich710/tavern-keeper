CREATE TABLE tavern_keeper_db.logs_info
(
    id          SERIAL PRIMARY KEY,
    action_time TIMESTAMP DEFAULT now(),
    action_type VARCHAR,
    description VARCHAR
);

CREATE OR REPLACE FUNCTION logs_info_function()
    RETURNS TRIGGER AS
$$
BEGIN
    INSERT INTO tavern_keeper_db.logs_info(action_type, description)
    VALUES ('register', 'Добавлен новый пользователь: ' || NEW.name || ' ' || NEW.login);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER log_info_trigger
    AFTER INSERT
    ON users
    FOR EACH ROW
EXECUTE FUNCTION logs_info_function();
