create table if not exists t_user
(
    id bigint not null primary key auto_increment,
    username varchar(32) not null ,
    password varchar(16) not null ,
    phone varchar(16) not null ,
    level varchar(8) not null default '普通会员',
    profileURL varchar(128) not null,
    approvalNum int not null default 3
);

create table if not exists t_movie
(
    id bigint not null primary key auto_increment,
    name varchar(32) not null ,
    classification varchar(32) not null ,
    durationM int not null ,
    introduction varchar(128) not null ,
    imageDirectory varchar(128) not null ,
    movieURL varchar(128) not null ,
    level varchar(8) not null default '普通电影',
    score double default 10,
    scoreNum int default 0
);

create table if not exists t_comment
(
    id bigint not null primary key auto_increment,
    userId bigint not null ,
    movieId bigint not null ,
    score int not null default 10,
    content varchar(256) not null default '该用户无评价内容',
    createdAt date not null ,
    approvalNum int default 0
);

alter table t_comment
    add foreign key (userId) references t_user(id);

alter table t_comment
    add foreign key (movieId) references t_movie(id);