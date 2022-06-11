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