create table users(
    id bigint not null AUTO_INCREMENT,
    login varchar(255) not null unique,
    email varchar(255) not null unique,
    password varchar(255) not null,
    auth varchar(50),
    registered_on datetime default NOW(),
    primary key (id)
);

create table posts(
    postId bigint not null AUTO_INCREMENT,
    title varchar(255) not null,
    content text not null,
    user_id bigint not null,
    primary key(postId),
    foreign key (user_id) reference users(id)
);

create table user_roles (
    user_id bigint not null,
    roles varchar(255) DEFAULT null,
    key (user_id),
    foreign key (user_id) references users(id)
);

create table user_roles (
    user_id bigint not null,
    roles varchar(255) DEFAULT null,
    key (user_id),
    foreign key (user_id) references users(id)
);