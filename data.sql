-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mer 15 Avril 2020 à 21:56
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `test`
--

-- --------------------------------------------------------

--
-- Structure de la table `classement`
--

CREATE TABLE IF NOT EXISTS `classement` (
  `idclassement` int(11) NOT NULL,
  `Equipe_idEquipe` int(11) DEFAULT NULL,
  `annee` int(11) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  PRIMARY KEY (`idclassement`),
  KEY `Equipe_idEquipe_idx` (`Equipe_idEquipe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `classement`
--

INSERT INTO `classement` (`idclassement`, `Equipe_idEquipe`, `annee`, `position`) VALUES
(10001, 1, 2018, 1),
(10002, 1, 2019, 2),
(10003, 1, 2020, 3),
(10004, 2, 2018, 2),
(10005, 2, 2019, 1),
(10006, 2, 2020, 1),
(10007, 3, 2018, 3),
(10008, 3, 2019, 3),
(10009, 3, 2020, 2),
(10010, 4, 2018, 4),
(10011, 4, 2019, 4),
(10012, 4, 2020, 4),
(10013, 5, 2018, 5),
(10014, 5, 2019, 5),
(10015, 5, 2020, 5);

-- --------------------------------------------------------

--
-- Structure de la table `equipe`
--

CREATE TABLE IF NOT EXISTS `equipe` (
  `idEquipe` int(11) NOT NULL,
  `nomEquipe` varchar(45) DEFAULT NULL,
  `nbParticipation` int(11) DEFAULT NULL,
  `nbVictoire` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEquipe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `equipe`
--

INSERT INTO `equipe` (`idEquipe`, `nomEquipe`, `nbParticipation`, `nbVictoire`) VALUES
(1, 'lakers', 16, 20),
(2, 'rockets', 16, 18),
(3, 'warriors', 16, 16),
(4, 'magic', 15, 8),
(5, 'heat', 15, 13);

-- --------------------------------------------------------

--
-- Structure de la table `joueur`
--

CREATE TABLE IF NOT EXISTS `joueur` (
  `idjoueur` int(11) NOT NULL,
  `nomJoueur` varchar(45) DEFAULT NULL,
  `but` int(11) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `notePrese` float DEFAULT NULL,
  `Equipe_idEquipe` int(11) DEFAULT NULL,
  PRIMARY KEY (`idjoueur`),
  KEY `equipe_idEquipe_idx` (`Equipe_idEquipe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `joueur`
--

INSERT INTO `joueur` (`idjoueur`, `nomJoueur`, `but`, `role`, `notePrese`, `Equipe_idEquipe`) VALUES
(1001, 'ciro immobile', 27, 'forward', 96, 1),
(1002, 'robert lewandowski', 25, 'forward', 95, 1),
(1003, 'cristiano ronaldo', 21, 'forward', 99.9, 2),
(1004, 'timo werner', 21, 'forward', 95.5, 3),
(1005, 'jamie vardy', 19, 'forward', 94, 4),
(1006, 'lionel andres messi', 19, 'forward', 99.9, 5),
(1007, 'wissam ben yedder', 18, 'forward', 96.5, 4),
(1008, 'kylian mbappe lottin', 18, 'forward', 98, 2),
(1009, 'piere-emerick', 17, 'forward', 94.5, 1),
(1010, 'romelu lukaku', 17, 'forward', 96.5, 2);

-- --------------------------------------------------------

--
-- Structure de la table `joueur_match`
--

CREATE TABLE IF NOT EXISTS `joueur_match` (
  `joueur_idJoueur` int(11) NOT NULL,
  `Match_idMatch` int(11) NOT NULL,
  PRIMARY KEY (`joueur_idJoueur`,`Match_idMatch`),
  KEY `Match_idMatch_idx` (`Match_idMatch`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `joueur_match`
--

INSERT INTO `joueur_match` (`joueur_idJoueur`, `Match_idMatch`) VALUES
(1001, 101),
(1002, 101),
(1005, 101),
(1007, 101),
(1009, 101),
(1001, 102),
(1002, 102),
(1003, 102),
(1008, 102),
(1009, 102),
(1010, 102),
(1003, 103),
(1004, 103),
(1008, 103),
(1010, 103),
(1004, 104),
(1006, 104),
(1005, 105),
(1006, 105),
(1007, 105);

-- --------------------------------------------------------

--
-- Structure de la table `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `idadmin` int(11) NOT NULL,
  `login` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idadmin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `login`
--

INSERT INTO `login` (`idadmin`, `login`, `password`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Structure de la table `matchinfo`
--

CREATE TABLE IF NOT EXISTS `matchinfo` (
  `idmatch` int(11) NOT NULL,
  `nomMatch` varchar(45) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `ville` varchar(45) DEFAULT NULL,
  `stade` varchar(45) DEFAULT NULL,
  `Equipe_idEquipe1` int(11) DEFAULT NULL,
  `Equipe_idEquipe2` int(11) DEFAULT NULL,
  `pointEquipe1` varchar(45) DEFAULT NULL,
  `pointEquipe2` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idmatch`),
  KEY `Equipe_idEquipe1_idx` (`Equipe_idEquipe1`),
  KEY `Equipe_idEquipie2_idx` (`Equipe_idEquipe2`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `matchinfo`
--

INSERT INTO `matchinfo` (`idmatch`, `nomMatch`, `date`, `ville`, `stade`, `Equipe_idEquipe1`, `Equipe_idEquipe2`, `pointEquipe1`, `pointEquipe2`) VALUES
(101, 'regular season1', '2019-06-08', 'paris', 'parc des princes', 4, 1, '2', '1'),
(102, 'regular season2', '2019-06-09', 'roma', 'stadio olimpico di roma', 1, 2, '2', '1'),
(103, 'regular season3', '2019-06-12', 'turin', 'juventus stadium', 2, 3, '3', '0'),
(104, 'regular season4', '2019-06-08', 'paris', 'parc des princes', 3, 5, '2', '1'),
(105, '1/4 finals', '2019-07-01', 'barcelone', 'camp nou', 5, 4, '5', '3');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `classement`
--
ALTER TABLE `classement`
  ADD CONSTRAINT `idEquipe` FOREIGN KEY (`Equipe_idEquipe`) REFERENCES `equipe` (`idEquipe`);

--
-- Contraintes pour la table `joueur`
--
ALTER TABLE `joueur`
  ADD CONSTRAINT `Equipe_idEquipe` FOREIGN KEY (`Equipe_idEquipe`) REFERENCES `equipe` (`idEquipe`);

--
-- Contraintes pour la table `joueur_match`
--
ALTER TABLE `joueur_match`
  ADD CONSTRAINT `Joueur_idJoueur` FOREIGN KEY (`joueur_idJoueur`) REFERENCES `joueur` (`idjoueur`),
  ADD CONSTRAINT `Match_idMatch` FOREIGN KEY (`Match_idMatch`) REFERENCES `matchinfo` (`idmatch`);

--
-- Contraintes pour la table `matchinfo`
--
ALTER TABLE `matchinfo`
  ADD CONSTRAINT `Equipe_idEquipe1` FOREIGN KEY (`Equipe_idEquipe1`) REFERENCES `equipe` (`idEquipe`),
  ADD CONSTRAINT `Equipe_idEquipie2` FOREIGN KEY (`Equipe_idEquipe2`) REFERENCES `equipe` (`idEquipe`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
