CREATE TABLE IF NOT EXISTS Entreprise (
    id_entreprise COUNTER,
    nom VARCHAR(50),
    adresse VARCHAR(250),
    cp INT,
    ville VARCHAR(100),
    mail VARCHAR(150),
    telephone INT,
    PRIMARY KEY (id_entreprise)
);

CREATE TABLE IF NOT EXISTS Utilisateur (
    id_user COUNTER,
    email VARCHAR(50) NOT NULL,
    nom VARCHAR(50),
    prenom VARCHAR(50),
    telephone INT,
    password VARCHAR(150) NOT NULL,
    PRIMARY KEY (id_user),
    UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS Consultation (
    id_consultation COUNTER,
    debut DATE,
    fin DATE,
    TJM DECIMAL(15, 2),
    description VARCHAR(250),
    id_user INT NOT NULL,
    id_entreprise INT NOT NULL,
    PRIMARY KEY (id_consultation),
    FOREIGN KEY (id_user) REFERENCES Utilisateur (id_user),
    FOREIGN KEY (id_entreprise) REFERENCES Entreprise (id_entreprise)
);

CREATE TABLE IF NOT EXISTS Formation (
    id_formation COUNTER,
    jour DATE,
    debut TIME,
    fin TIME,
    classe VARCHAR(50),
    cours VARCHAR(50),
    id_user INT NOT NULL,
    id_entreprise INT NOT NULL,
    PRIMARY KEY (id_formation),
    FOREIGN KEY (id_user) REFERENCES Utilisateur (id_user),
    FOREIGN KEY (id_entreprise) REFERENCES Entreprise (id_entreprise)
);