DROP TABLE IF EXISTS podopieczny;
DROP TABLE IF EXISTS trener;

CREATE TABLE trainer (
                         id_trainer INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(250) NOT NULL,
                         surname VARCHAR(250) NOT NULL,
                         code_trainer VARCHAR(200) NOT NULL
);

CREATE TABLE ward (
                           id_ward INT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(250) NOT NULL,
                           surname VARCHAR(250) NOT NULL,
                           id_trainer INT NOT NULL
);

INSERT INTO trainer (name, surname, code_trainer) VALUES
                                                      ('Adam', 'Zakrzewski', 'AZ123'),
                                                      ('Szymon', 'Kot', 'SK123'),
                                                      ('Jan', 'Lotek', 'JL123');

INSERT INTO ward (name, surname, id_trainer) VALUES
                                                      ('Adam', 'Majutek', 1),
                                                      ('Zbyszek', 'Sowa', 2),
                                                      ('Jan', 'Grycan', 3),
                                                      ('Szymon', 'Brzoza', 1),
                                                      ('Bartosz', 'Lot', 2),
                                                      ('Kuba', 'Zima', 3);
