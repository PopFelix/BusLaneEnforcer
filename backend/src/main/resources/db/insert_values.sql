INSERT INTO user_entity (id, password, username)
VALUES ( '5aa21472-bea0-44c0-aa02-71f88c895c57', '$2a$10$IDC1DJPoXUB4TWdUBcLs8.cLPqwFr/Y8x2Pxu/wMsgbyv5geMsoeO',
        'user'),
       ( '800361b3-41c1-4729-af3f-ec4c045d0b8a', '$2a$10$nJ6xwG7x2dRNyrGEYT4Xae2jH4xwHzY4OXtp1ls42xGl/0VH8VY3K',
        'admin');

INSERT INTO user_entity_roles (user_entity_id, roles)
VALUES ('5aa21472-bea0-44c0-aa02-71f88c895c57', 'ROLE_USER'),
       ('800361b3-41c1-4729-af3f-ec4c045d0b8a', 'ROLE_ADMIN'),
       ('800361b3-41c1-4729-af3f-ec4c045d0b8a', 'ROLE_USER');
