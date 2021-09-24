INSERT INTO project.role(role_name) VALUES('ADMIN');
INSERT INTO project.role(role_name) VALUES('USER');
INSERT INTO project.user_roles(user_id, roles_id) VALUES(1, 1);
INSERT INTO project.user(display_name, pwd, user_name) VALUES('Admin', '$2a$12$pL.zkc0yAgdMxZQPXfccuexdkneZJ7nT/qGOeU0rTxtqlPy.QszFi', 'admin');