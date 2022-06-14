DROP TABLE IF EXISTS podopieczny;
DROP TABLE IF EXISTS trener;

CREATE TABLE trener (
                         id_trener INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(250) NOT NULL,
                         surname VARCHAR(250) NOT NULL,
                         kod_trenera VARCHAR(200) NOT NULL
);

CREATE TABLE podopieczny (
                           id_podopieczny INT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(250) NOT NULL,
                           surname VARCHAR(250) NOT NULL,
                           id_trener INT NOT NULL
);

INSERT INTO trener (name, surname, kod_trenera) VALUES
                                                      ('Adam', 'Zakrzewski', 'AZ123'),
                                                      ('Szymon', 'Kot', 'SK123'),
                                                      ('Jan', 'Lotek', 'JL123');

INSERT INTO podopieczny (name, surname, id_trener) VALUES
                                                      ('Adam', 'Majutek', 1),
                                                      ('Zbyszek', 'Sowa', 2),
                                                      ('Jan', 'Grycan', 3),
                                                      ('Szymon', 'Brzoza', 1),
                                                      ('Bartosz', 'Lot', 2),
                                                      ('Kuba', 'Zima', 3);
