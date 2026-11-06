-- Script de création de la base de données et de la table pour MySQL
CREATE DATABASE IF NOT EXISTS studentdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE studentdb;

CREATE TABLE IF NOT EXISTS student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    date_naissance DATE
);

-- Insertion de données de test (optionnel)
INSERT INTO student (nom, prenom, date_naissance) VALUES
('LACHGAR', 'Mohamed', '1985-09-01'),
('EL AMRANI', 'Yassine', '1990-05-12'),
('BENJELLOUN', 'Salma', '1985-11-20');

