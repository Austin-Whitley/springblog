USE springblog_db;

TRUNCATE posts;
INSERT INTO posts (id, title, body, user_id)
VALUES (1, 'Greg likes potato chips', 'He sure does look at him go!', 1),
       (2, 'Hi, my name Borat', 'Woo wow wee wow', 1),
       (3, 'BINGO BANGO', 'My names Funbucket!', 1),
       (4, 'Test post', 'oooooo weeeee', 1),
       (5, 'Im Mr. Meseeks', 'Look at meeee', 1),
       (6, 'Teststst', 'bzzzzzzzt', 1),
       (7, 'Whats up with this weather?!', 'It has been over 90 degrees for like a week now. When will this all end? Im getting pretty sick of the heat.', 1),
       (8, 'Rubber ducks', 'I found this thrift store in town, and ill tell you what if, if youre into rubber ducks at all I would highly recommend checking it out. They have a sale going on right now buy 5 get 5 free! and the ducks are 50c a pop! What a steal!!', 1);
