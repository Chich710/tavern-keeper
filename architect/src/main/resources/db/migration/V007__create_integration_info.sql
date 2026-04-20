CREATE TABLE integration_info
(
    id          SERIAL PRIMARY KEY,
    link        VARCHAR,
    key         VARCHAR,
    code        VARCHAR,
    description VARCHAR
);

INSERT INTO integration_info
VALUES (1,
        'aHR0cHM6Ly9hcGkub3BlbmNhZ2VkYXRhLmNvbS9nZW9jb2RlL3YxL2pzb24/cT0lcyslcyZrZXk9JXMmbGFuZ3VhZ2U9ZW4=',
        'OGNjZjNlOTNiNDMwNGEwMGFhZTA3N2YxNGE2OTBhZjc=',
        'open_cage',
        'Сервис геолокаций');
