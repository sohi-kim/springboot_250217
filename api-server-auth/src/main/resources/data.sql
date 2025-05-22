/*insert into BOOKABLES_ENTITY (GROUP_NAME,TITLE,NOTES,AVAILABLE_DAYS,SESSION_NUMBERS)
values (1,'Rooms','Meeting Room','The one with the big table and interactive screen. Seats 12. See Colin if you need the tea and coffee trolley.',
        '1,2,3','1,2,3,4,5,0');*/

/*
insert into BOOKING_ENTITY(BOOKER_ID,BOOKABLE_ID,TITLE,SESSION_NAME,BOOKING_DATE,NOTES)
values ();
*/

INSERT INTO BOOKABLE_ENTITY ( GROUP_NAME, TITLE, NOTES, AVAILABLE_DAYS, SESSION_NUMBERS) VALUES
    ('Rooms', 'Meeting Room', 'The one with the big table and interactive screen. Seats 12. See Colin if you need the tea and coffee trolley.', '1,2,3,4,5,0', '1,2,3');

INSERT INTO BOOKABLE_ENTITY ( GROUP_NAME, TITLE, NOTES, AVAILABLE_DAYS, SESSION_NUMBERS) VALUES
    ('Rooms', 'Lecture Hall', 'For more formal ''sage-on-the-stage'' presentations. Seats 100. See Sandra for help with AV setup.', '0,1,2,3,4', '1,3,4');

INSERT INTO BOOKABLE_ENTITY ( GROUP_NAME, TITLE, NOTES, AVAILABLE_DAYS, SESSION_NUMBERS) VALUES
    ( 'Rooms', 'Games Room', 'Table tennis, table football, pinball! There''s also a selection of board games. Please tidy up!', '0,2,3,4,5,6', '0,2,4');

INSERT INTO BOOKABLE_ENTITY ( GROUP_NAME, TITLE, NOTES, AVAILABLE_DAYS, SESSION_NUMBERS) VALUES
    ( 'Rooms', 'Lounge', 'A relaxing place to hang out. Ideal for bean bag wranglers and sofa surfers. Help yourself to a beer after hours.', '0,1,2,3,4,5,6', '0,1,2,3,4');

INSERT INTO BOOKABLE_ENTITY ( GROUP_NAME, TITLE, NOTES, AVAILABLE_DAYS, SESSION_NUMBERS) VALUES
    ( 'Kit', 'Projector', 'Portable but powerful. Keep it with the case. Be careful, it gets quite hot after a while!', '0,2,3,4,5', '1,2,3,4');

INSERT INTO BOOKABLE_ENTITY ( GROUP_NAME, TITLE, NOTES, AVAILABLE_DAYS, SESSION_NUMBERS) VALUES
    ( 'Kit', 'Wireless mics', 'Really handy but don''t forget to switch them off when you pop out of the room.', '0,2,3,4,5,6', '1,3,4');


INSERT INTO BOOKING_ENTITY ( BOOKER_ID, BOOKABLE_ID, TITLE, SESSION_NAME, BOOKING_DATE, NOTES) VALUES
    ( 3, 1, 'Onboarding', 'Lunch', '2024-09-22', NULL);

INSERT INTO BOOKING_ENTITY ( BOOKER_ID, BOOKABLE_ID, TITLE, SESSION_NAME, BOOKING_DATE, NOTES) VALUES
    ( 3, 3, 'Football Challenge', 'Lunch', '2024-09-23', NULL);

INSERT INTO BOOKING_ENTITY ( BOOKER_ID, BOOKABLE_ID, TITLE, SESSION_NAME, BOOKING_DATE, NOTES) VALUES
    ( 2, 1, 'Movie Pitch!', 'Morning', '2024-09-24', NULL);

INSERT INTO BOOKING_ENTITY ( BOOKER_ID, BOOKABLE_ID, TITLE, SESSION_NAME, BOOKING_DATE, NOTES) VALUES
    ( 2, 1, 'Meeting Room', 'Lunch', '2024-10-09', NULL);

INSERT INTO BOOKING_ENTITY ( BOOKER_ID, BOOKABLE_ID, TITLE, SESSION_NAME, BOOKING_DATE, NOTES) VALUES
    ( 2, 3, 'Tiddlywinks', 'Breakfast', '2024-09-26', NULL);

INSERT INTO BOOKING_ENTITY ( BOOKER_ID, BOOKABLE_ID, TITLE, SESSION_NAME, BOOKING_DATE, NOTES) VALUES
    ( 1, 1, 'New Employee Intro', 'Lunch', '2024-09-26', NULL);

INSERT INTO BOOKING_ENTITY ( BOOKER_ID, BOOKABLE_ID, TITLE, SESSION_NAME, BOOKING_DATE, NOTES) VALUES
    ( 1, 1, 'Project Update', 'Afternoon', '2024-09-23', NULL);

INSERT INTO BOOKING_ENTITY ( BOOKER_ID, BOOKABLE_ID, TITLE, SESSION_NAME, BOOKING_DATE, NOTES) VALUES
    ( 1, 2, 'Quiz', 'Afternoon', '2024-09-22', 'hi');

INSERT INTO BOOKING_ENTITY ( BOOKER_ID, BOOKABLE_ID, TITLE, SESSION_NAME, BOOKING_DATE, NOTES) VALUES
    ( 2, 6, '테스트😊', 'Morning', '2024-10-17', '테스트');

INSERT INTO BOOKING_ENTITY ( BOOKER_ID, BOOKABLE_ID, TITLE, SESSION_NAME, BOOKING_DATE, NOTES) VALUES
    ( 3, 3, '베타테스트🎇', 'Breakfast', '2024-10-19', '테스트');

INSERT INTO WORKER_ENTITY ( name, img, title, notes) VALUES
    ( 'Mark', 'user1.jpg', 'Envisioning Sculptor', 'With the company for 15 years, Mark has consistently sculpted innovative and compelling narratives for enforwarding the mutual ethos of all stakeholders.');

INSERT INTO WORKER_ENTITY ( name, img, title, notes) VALUES
    ( 'Simon🎁', 'user2.jpg', 'Outreach Samurai', 'Simon wrangles social networks, elegantly employing bleeding-katana psycho-tools to leverage what he likes to call ''News Technology''.');

INSERT INTO WORKER_ENTITY ( name, img, title, notes) VALUES
    ( 'Clarisse✨', 'user3.jpg', 'Quantum Explorator', 'Surfing a higher plane of understanding, Clarisse value-adds the latest ''beyond fullstack'' platforms, libraries and universes, collapsing realities to find the one truth.');

INSERT INTO WORKER_ENTITY ( name, img, title, notes) VALUES
    ( 'Sanjiv🧧', 'user4.jpg', 'Devil''s Advocate Advocate', 'Sanjiv lives your life to better understand your power-tantrums and architect empathic growth journeys that break the shell and distribute the yoke company-wide.^^!!');
