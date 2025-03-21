-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 21, 2025 at 11:59 AM
-- Server version: 11.4.2-MariaDB
-- PHP Version: 8.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spursdata2`
--

-- --------------------------------------------------------

--
-- Table structure for table `joueurs`
--

CREATE TABLE `joueurs` (
  `Nom` varchar(25) NOT NULL,
  `Prenom` varchar(25) NOT NULL,
  `Age` int(11) NOT NULL,
  `Taille` int(11) NOT NULL,
  `Poid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `joueurs`
--

INSERT INTO `joueurs` (`Nom`, `Prenom`, `Age`, `Taille`, `Poid`) VALUES
('Collins', 'Zach', 25, 211, 113),
('Johnson', 'Keldon', 24, 196, 100),
('Sochan', 'Jeremy', 20, 206, 107),
('Vassell', 'Devin', 23, 196, 91),
('Wembanyama', 'Victor', 19, 224, 95);

-- --------------------------------------------------------

--
-- Table structure for table `matchs`
--

CREATE TABLE `matchs` (
  `MatchId` int(11) NOT NULL,
  `Adversaire` varchar(25) NOT NULL,
  `Date` date NOT NULL,
  `Lieu` varchar(25) NOT NULL,
  `Resultat` varchar(25) NOT NULL,
  `DifScore` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `matchs`
--

INSERT INTO `matchs` (`MatchId`, `Adversaire`, `Date`, `Lieu`, `Resultat`, `DifScore`) VALUES
(1, 'Thunder', '2023-10-10', 'Extérieur', 'Défaite', -1),
(2, 'Heat', '2023-10-14', 'Extérieur', 'Victoire', 16),
(3, 'Rockets', '2023-10-19', 'Domicile', 'Victoire', 14);

-- --------------------------------------------------------

--
-- Table structure for table `statcard`
--

CREATE TABLE `statcard` (
  `tdJeu` varchar(25) NOT NULL,
  `Points` int(11) NOT NULL,
  `Rebonds` int(11) NOT NULL,
  `Passe_D` int(11) NOT NULL,
  `Nom` varchar(25) NOT NULL,
  `MatchID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `statcard`
--

INSERT INTO `statcard` (`tdJeu`, `Points`, `Rebonds`, `Passe_D`, `Nom`, `MatchID`) VALUES
('22:44', 15, 5, 5, 'Collins', 1),
('9:46', 0, 1, 1, 'Johnson', 1),
('', 0, 0, 0, 'Sochan', 1),
('21:18', 6, 2, 1, 'Vassell', 1),
('19:22', 20, 5, 1, 'Wembanyama', 1),
('0:0', 0, 0, 0, 'Collins', 2),
('0:0', 0, 0, 0, 'Johnson', 2),
('25:02', 10, 6, 3, 'Sochan', 2),
('21:31', 21, 3, 1, 'Vassell', 2),
('22:51', 23, 4, 4, 'Wembanyama', 2),
('22:28', 11, 9, 5, 'Collins', 3),
('22:48', 4, 2, 4, 'Johnson', 3),
('21:10', 6, 6, 4, 'Sochan', 3),
('25:14', 25, 3, 1, 'Vassell', 3),
('20:37', 15, 6, 1, 'Wembanyama', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `joueurs`
--
ALTER TABLE `joueurs`
  ADD UNIQUE KEY `Nom` (`Nom`);

--
-- Indexes for table `matchs`
--
ALTER TABLE `matchs`
  ADD PRIMARY KEY (`MatchId`);

--
-- Indexes for table `statcard`
--
ALTER TABLE `statcard`
  ADD PRIMARY KEY (`MatchID`,`Nom`),
  ADD KEY `Nom` (`Nom`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `statcard`
--
ALTER TABLE `statcard`
  ADD CONSTRAINT `Nom` FOREIGN KEY (`Nom`) REFERENCES `joueurs` (`Nom`),
  ADD CONSTRAINT `idMatch` FOREIGN KEY (`MatchID`) REFERENCES `matchs` (`MatchId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
