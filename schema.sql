create table users
(
    user_id       serial
        constraint users_pkey
            primary key,
    first_name    varchar(30)  not null,
    last_name     varchar(30)  not null,
    email         varchar(100) not null
        constraint users_email_key
            unique,
    password_hash integer      not null,
    role          varchar(30)  not null,
    avatar_id     integer
        constraint users_avatar_id_fkey
            references avatar_file
);

create table avatar_file
(
    avatar_file_id     serial
        constraint avatar_file_pkey
            primary key,
    original_file_name varchar(100),
    storage_file_name  varchar(100) not null,
    size               bigint       not null,
    type               varchar(100)
);

create table lessons
(
    lesson_id  integer default nextval('posts_post_id_seq'::regclass) not null
        constraint posts_pkey
            primary key,
    author_id  integer                                                not null
        constraint posts_author_id_fkey
            references users,
    created_at timestamp                                              not null,
    content    varchar(1000)                                          not null,
    subject    varchar(30),
    for_whom   varchar(30)
);