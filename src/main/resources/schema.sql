CREATE TABLE kunde(
  kid INT AUTO_INCREMENT,
  fornavn VARCHAR(50),
  etternavn VARCHAR(50),
    adresse VARCHAR(50),
    postnr VARCHAR(4),
    telefonnr VARCHAR(8),
    epost VARCHAR(50),
    PRIMARY KEY (kid)
);
CREATE TABLE pakke(
    pid INT AUTO_INCREMENT,
    kid INT,
    volum DECIMAL(10,0),
    vekt DECIMAL(10,0),
    PRIMARY KEY (pid),
    FOREIGN KEY (kid) REFERENCES Kunde(kid)
);

CREATE TABLE poststed(
  Postnr VARCHAR(4),
  Poststed VARCHAR(50)
);