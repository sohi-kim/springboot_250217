INSERT INTO tbl_user (id,username, password, role, nickname, picture,join_date) 
VALUES (1,'sohi', 's1234', 'ADMIN', '소히', 'sohi_profile.png','2025-05-01 10:30:00');

INSERT INTO tbl_user (id,username, password, role, nickname, picture,join_date)
VALUES (2,'johndoe', 'j1234', 'USER', 'JJ', 'john_picture.jpg','2025-05-01 10:30:00');

INSERT INTO tbl_user (id,username, password, role, nickname, picture,join_date) 
VALUES (3,'janedoe', 'd1234', 'USER', 'Dodo', 'jane_profile.png','2025-05-01 10:30:00');

INSERT INTO tbl_todo (todo_id, title, username, done, created_at, updated_at) 
VALUES (UUID(), '할 일 1', 'sohi', false, '2025-05-01 10:30:00', '2025-05-01 10:30:00');

INSERT INTO tbl_todo (todo_id, title, username, done, created_at, updated_at) 
VALUES (UUID(), '할 일 2', 'johndoe', true, '2025-04-20 14:15:00', '2025-04-22 09:50:00');

INSERT INTO tbl_todo (todo_id, title, username, done, created_at, updated_at) 
VALUES (UUID(), '할 일 3', 'janedoe', false, '2025-03-15 08:45:00', '2025-03-16 11:20:00');
