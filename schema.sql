create table users (
    user_id serial primary key,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    email varchar(100) not null unique,
    password_hash varchar(100) not null,
    role integer not null,
    avatar_id integer,
    foreign key (avatar_id) references avatar_file(avatar_file_id)
);

create table avatar_file (
    avatar_file_id serial primary key,
    original_file_name varchar(100),
    storage_file_name varchar(100) not null,
    size bigint not null,
    type varchar(100)
);

create table posts (
    post_id serial primary key,
    author_id int not null,
    foreign key (author_id) references users(user_id),
    created_at timestamp not null,
    content varchar(1000) not null
);

create table attached_file (
    file_id serial primary key,
    original_file_name varchar(100),
    storage_file_name varchar(100) not null,
    size bigint not null,
    type varchar(100),
    post_id int not null,
    foreign key (post_id) references posts(post_id)
);

alter table users alter column password_hash type integer using password_hash::integer;