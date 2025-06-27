CREATE TABLE IF NOT EXISTS Entreprise (
    Id_Entreprise COUNTER,
    Nom VARCHAR(50),
    adresse VARCHAR(250),
    cp INT,
    ville VARCHAR(100),
    mail VARCHAR(150),
    telephone INT,
    PRIMARY KEY (Id_Entreprise)
);

CREATE TABLE IF NOT EXISTS Utilisateur (
    Id_User COUNTER,
    email VARCHAR(50) NOT NULL,
    Nom VARCHAR(50),
    Prenom VARCHAR(50),
    telephone INT,
    password VARCHAR(150) NOT NULL,
    PRIMARY KEY (Id_User),
    UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS Consultation (
    Id_Consultation COUNTER,
    debut DATE,
    fin DATE,
    TJM DECIMAL(15, 2),
    description VARCHAR(250),
    Id_User INT NOT NULL,
    Id_Entreprise INT NOT NULL,
    PRIMARY KEY (Id_Consultation),
    FOREIGN KEY (Id_User) REFERENCES Utilisateur (Id_User),
    FOREIGN KEY (Id_Entreprise) REFERENCES Entreprise (Id_Entreprise)
);

CREATE TABLE IF NOT EXISTS Formation (
    Id_Formation COUNTER,
    jour DATE,
    debut TIME,
    fin TIME,
    classe VARCHAR(50),
    cours VARCHAR(50),
    Id_User INT NOT NULL,
    Id_Entreprise INT NOT NULL,
    PRIMARY KEY (Id_Formation),
    FOREIGN KEY (Id_User) REFERENCES Utilisateur (Id_User),
    FOREIGN KEY (Id_Entreprise) REFERENCES Entreprise (Id_Entreprise)
);