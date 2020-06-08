DROP TABLE if exists animal;

--������� ��������
CREATE TABLE animal
(
    idanimal bigserial NOT NULL PRIMARY KEY,
    idcage integer, -- id ������
    idmed integer,  -- id ���.����������
    idtype integer, -- ��� ���������
    animal varchar(25)
);

INSERT into  Animal (idCage, idMed, idType, animal) VALUES ( 100, 1, 1, '����');
INSERT into  Animal (idCage, idMed, idType, animal) VALUES (101, 2, 1, '���');
INSERT into  Animal (idCage, idMed, idType, animal) VALUES ( 200, 3, 2, '��������');
INSERT into  Animal (idCage, idMed, idType, animal) VALUES ( 110, 4, 1, '�������');
INSERT into  Animal (idCage, idMed, idType, animal) VALUES ( 210, 5, 2, '����');
INSERT into  Animal (idCage, idMed, idType, animal) VALUES ( 220, 6, 2, '�����');
INSERT into  Animal (idCage, idMed, idType, animal) VALUES ( 300, 7, 3, '�������');
INSERT into  Animal (idCage, idMed, idType, animal) VALUES ( 310, 8, 3, '��������');
INSERT into  Animal (idCage, idMed, idType, animal) VALUES ( 230, 9, 2, '�����');
INSERT into  Animal (idCage, idMed, idType, animal) VALUES ( 120, 10, 1, '����');

DROP TABLE if exists Feed;

CREATE TABLE Feed(
	idFeed int NULL,
	idAnimal int NULL,
	hourFeed numeric(4, 2) NULL,  -- ����� ���������
	volume int NULL,              -- ���-�� ���
	typeFood varchar(30) NULL     -- ��� ����
);

INSERT into Feed (idFeed, idAnimal, hourFeed, volume, typeFood) VALUES (1, 1, 7, 5, '����');
INSERT into Feed (idFeed, idAnimal, hourFeed, volume, typeFood) VALUES (2, 1, 20, 5, '����');
INSERT into Feed (idFeed, idAnimal, hourFeed, volume, typeFood) VALUES (3, 2, 7, 5, '����');
INSERT into Feed (idFeed, idAnimal, hourFeed, volume, typeFood) VALUES (4, 2, 20, 5, '����');
INSERT into Feed (idFeed, idAnimal, hourFeed, volume, typeFood) VALUES (5, 3, 8, 1, '����');
INSERT into Feed (idFeed, idAnimal, hourFeed, volume, typeFood) VALUES (6, 3, 19, 1, '������');
INSERT into Feed (idFeed, idAnimal, hourFeed, volume, typeFood) VALUES (7, 4, 7, 5, '����');
INSERT into Feed (idFeed, idAnimal, hourFeed, volume, typeFood) VALUES (8, 4, 20, 5, '����');
INSERT into Feed (idFeed, idAnimal, hourFeed, volume, typeFood) VALUES (9, 5, 7, 100, '������');
INSERT into Feed (idFeed, idAnimal, hourFeed, volume, typeFood) VALUES (10, 5, 21, 50, '������');
INSERT into Feed (idFeed, idAnimal, hourFeed, volume, typeFood) VALUES (11, 6, 8, 20, '������');
INSERT into Feed (idFeed, idAnimal, hourFeed, volume, typeFood) VALUES (12, 6, 20, 30, '������');
INSERT into Feed (idFeed, idAnimal, hourFeed, volume, typeFood) VALUES (13, 7, 7, 10, '������');
INSERT into Feed (idFeed, idAnimal, hourFeed, volume, typeFood) VALUES (14, 7, 21, 10, '������');
INSERT into Feed (idFeed, idAnimal, hourFeed, volume, typeFood) VALUES (15, 8, 7, 7, '������');
INSERT into Feed (idFeed, idAnimal, hourFeed, volume, typeFood) VALUES (16, 8, 21, 7, '������');
INSERT into Feed (idFeed, idAnimal, hourFeed, volume, typeFood) VALUES (17, 9, 8, 20, '������');
INSERT into Feed (idFeed, idAnimal, hourFeed, volume, typeFood) VALUES (18, 9, 20, 20, '����');
INSERT into Feed (idFeed, idAnimal, hourFeed, volume, typeFood) VALUES (19, 10, 7, 5, '����');
INSERT into Feed (idFeed, idAnimal, hourFeed, volume, typeFood) VALUES (20, 10, 20, 5, '����');

DROP TABLE if exists Cage;
CREATE TABLE Cage(
	idCage int NULL,  -- ����� ������
	idStaff int NULL  -- id ���������
);

INSERT into Cage (idCage, idStaff) VALUES (100, 10);
INSERT into Cage (idCage, idStaff) VALUES (101, 10);
INSERT into Cage (idCage, idStaff) VALUES (110, 10);
INSERT into Cage (idCage, idStaff) VALUES (200, 20);
INSERT into Cage (idCage, idStaff) VALUES (210, 20);
INSERT into Cage (idCage, idStaff) VALUES (220, 20);
INSERT into Cage (idCage, idStaff) VALUES (120, 10);
INSERT into Cage (idCage, idStaff) VALUES (300, 30);
INSERT into Cage (idCage, idStaff) VALUES (310, 30);
INSERT into Cage (idCage, idStaff) VALUES (230, 20);
INSERT into Cage (idCage, idStaff) VALUES (350, 30);
INSERT into Cage (idCage, idStaff) VALUES (400, NULL);
INSERT into Cage (idCage, idStaff) VALUES (500, NULL);

DROP TABLE if exists med;
CREATE TABLE Med(
	idMed int NULL,
	weight int NULL,      -- ��� ���������
	checkPriv int NULL    -- ������� ��������
);

insert into med (idMed, weight, checkPriv) VALUES (1, 300, 1);
insert into med (idMed, weight, checkPriv) VALUES (2, 250, 1);
insert into med (idMed, weight, checkPriv) VALUES (3, 50, 1);
insert into med (idMed, weight, checkPriv) VALUES (4, 400, 1);
insert into med (idMed, weight, checkPriv) VALUES (5, 5000, 1);
insert into med (idMed, weight, checkPriv) VALUES (6, 1000, 1);
insert into med (idMed, weight, checkPriv) VALUES (7, 200, 1);
insert into med (idMed, weight, checkPriv) VALUES (8, 70, 1);
insert into med (idMed, weight, checkPriv) VALUES (9, 300, 1);
insert into med (idMed, weight, checkPriv) VALUES (10, 80, 1);

DROP TABLE if exists staff;
CREATE TABLE Staff(
	idStaff int NULL,
	position varchar(30) NULL  -- ��������
);

INSERT into Staff (idStaff, position) VALUES (10, '����������(�������)');
INSERT into Staff (idStaff, position) VALUES (20, '����������(����������)');
INSERT into Staff (idStaff, position) VALUES (30, '����������(�������)');
