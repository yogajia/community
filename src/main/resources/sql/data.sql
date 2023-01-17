use accountbook;

insert into user(id, username, password, enabled, accountNonExpired, accountNonLocked, credentialsNonExpired)
VALUES(1,'zcy','{noop}123',1,1,1,1),
       (2,'root','{noop}123',1,1,1,1);
insert into roles(id, name)
VALUES (1,'ROLE_user'),
       (2,'ROLE_admin');
insert into user_role(id, uid, rid) VALUES
(1,1,1),(2,2,1);
