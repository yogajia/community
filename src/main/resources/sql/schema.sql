use accountbook;

#用户表;
set charset utf8mb4;
drop table if exists user;
create table user(
    id int not null auto_increment,
    username varchar(32),
    password varchar(255),
    enabled tinyint(1) default null comment '账户是否可用',
    accountNonExpired tinyint(1) default null comment '账户是否没有过期',
    accountNonLocked tinyint (1) default null comment '账户是否没有被锁定',
    credentialsNonExpired tinyint(1) default null comment '密码是否没有过期',
    primary key (id)
);

#角色表;
drop table if exists roles;
create table roles(
    id int not null auto_increment,
    name varchar(32) ,
    primary key (id)
);

#用户角色关系表;
drop table if exists user_role;
create table user_role(
    id int not null,
    uid int ,
    rid int,
    primary key (id),
    foreign key (uid) references user (id),
    foreign key (rid) references roles (id)
);

#用户收入记录表;
drop table if exists income;
create table income(
    uid int not null,
    id int not null auto_increment,
    type varchar(20) not null comment '收入类型',
    amount decimal(8,2) not null comment '金额',
    text varchar(255) comment '备注',
    create_time TIMESTAMP default CURRENT_TIMESTAMP comment '记录时间',
    primary key (id),
    foreign key (uid) references user(id)
);

#用户支出记录表;
drop table if exists disburse;
create table disburse(
    uid int not null,
    id int not null auto_increment,
    type varchar(20) not null comment '支出类型',
    amount decimal(8,2) not null comment '金额',
    text varchar(255) comment '备注',
    create_time TIMESTAMP default CURRENT_TIMESTAMP comment '记录时间',
    primary key (id),
    foreign key (uid) references user(id)
);
