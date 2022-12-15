BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user1','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Eli','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Kirk','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Luke','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Daniel','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Drew','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('TonyHawk','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('string','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
--INSERT INTO users (username,password_hash,role) VALUES ('user3','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
--INSERT INTO users (username,password_hash,role) VALUES ('user4','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
--INSERT INTO users (username,password_hash,role) VALUES ('user5','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO groups (group_owner, group_name, group_code, date_created) values (1, 'test', '111', '10-10-2020');
INSERT INTO groups (group_owner, group_name, group_code, date_created) values (3, 'Fridggr', '222', '10-10-2020');
INSERT INTO groups (group_owner, group_name, group_code, date_created) values (4, 'No Kirks', '333', '10-10-2020');
INSERT INTO groups (group_owner, group_name, group_code, date_created) values (7, 'Tony Hawks', '444', '10-10-2020');
INSERT INTO list (group_id, list_title, description, date_modified, list_completed, claimed) VALUES (1001, 'testList', 'test description', 'date', false, 0);
INSERT INTO list (group_id, list_title, description, date_modified, list_completed, claimed) VALUES (1002, 'Birthday Cake', 'Cake for Daniels bday', 'date', false, 0);
INSERT INTO list (group_id, list_title, description, date_modified, list_completed, claimed) VALUES (1002, 'Misc', 'Just some other groceries', 'date', false, 0);
INSERT INTO list_item (date_modified, quantity, last_modifier, status, list_id, group_id, item_name) VALUES ('CHANGE date', 10, 1, false, 2001, 1001, 'cool item');
INSERT INTO list_item (date_modified, quantity, last_modifier, status, list_id, group_id, item_name) VALUES ('CHANGE date', 2, 2, false, 2002, 1002, 'Half dozen eggs');
INSERT INTO list_item (date_modified, quantity, last_modifier, status, list_id, group_id, item_name) VALUES ('CHANGE date', 1, 4, false, 2002, 1002, 'Flour');
INSERT INTO list_item (date_modified, quantity, last_modifier, status, list_id, group_id, item_name) VALUES ('CHANGE date', 1, 4, false, 2002, 1002, 'Milk');
INSERT INTO list_item (date_modified, quantity, last_modifier, status, list_id, group_id, item_name) VALUES ('CHANGE date', 1, 5, false, 2002, 1002, 'Sugar');
INSERT INTO list_item (date_modified, quantity, last_modifier, status, list_id, group_id, item_name) VALUES ('CHANGE date', 21, 4, false, 2002, 1002, 'Birthday Candles');
INSERT INTO list_item (date_modified, quantity, last_modifier, status, list_id, group_id, item_name) VALUES ('CHANGE date', 1, 3, false, 2003, 1002, 'Hydrofluoric Acid');
INSERT INTO list_item (date_modified, quantity, last_modifier, status, list_id, group_id, item_name) VALUES ('CHANGE date', 1, 3, false, 2003, 1002, 'Tube furnace');
INSERT INTO list_item (date_modified, quantity, last_modifier, status, list_id, group_id, item_name) VALUES ('CHANGE date', 1, 3, false, 2003, 1002, 'Barkeepers Friend');
INSERT INTO list_item (date_modified, quantity, last_modifier, status, list_id, group_id, item_name) VALUES ('CHANGE date', 6, 3, false, 2003, 1002, 'Hydrous methylamine');
INSERT INTO group_member (user_id, group_id, date_joined) VALUES (1, 1001, 'date');
INSERT INTO group_member (user_id, group_id, date_joined) VALUES (2, 1002, 'date');
INSERT INTO group_member (user_id, group_id, date_joined) VALUES (3, 1002, 'date');
INSERT INTO group_member (user_id, group_id, date_joined) VALUES (4, 1002, 'date');
INSERT INTO group_member (user_id, group_id, date_joined) VALUES (5, 1002, 'date');
INSERT INTO group_member (user_id, group_id, date_joined) VALUES (6, 1002, 'date');
INSERT INTO group_member (user_id, group_id, date_joined) VALUES (2, 1003, 'date');
INSERT INTO group_member (user_id, group_id, date_joined) VALUES (4, 1003, 'date');
INSERT INTO group_member (user_id, group_id, date_joined) VALUES (5, 1003, 'date');
INSERT INTO group_member (user_id, group_id, date_joined) VALUES (6, 1003, 'date');
INSERT INTO group_member (user_id, group_id, date_joined) VALUES (7, 1003, 'date');
INSERT INTO group_member (user_id, group_id, date_joined) VALUES (7, 1004, 'date');
COMMIT TRANSACTION;
