-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Nov 26, 2019 at 09:00 AM
-- Server version: 5.7.26
-- PHP Version: 7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `insuranceMembers`
--
CREATE DATABASE IF NOT EXISTS `insuranceMembers` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `insuranceMembers`;

-- --------------------------------------------------------

--
-- Table structure for table `tblAgent`
--

CREATE TABLE `tblAgent` (
  `agentID` int(11) NOT NULL,
  `first` varchar(50) DEFAULT NULL,
  `last` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tblMember`
--

CREATE TABLE `tblMember` (
  `memberID` int(11) NOT NULL,
  `first` varchar(50) DEFAULT NULL,
  `last` varchar(50) DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `package` varchar(50) DEFAULT NULL,
  `noMember` int(11) DEFAULT NULL,
  `fee` double DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `agentID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tblAgent`
--
ALTER TABLE `tblAgent`
  ADD PRIMARY KEY (`agentID`);

--
-- Indexes for table `tblMember`
--
ALTER TABLE `tblMember`
  ADD PRIMARY KEY (`memberID`),
  ADD KEY `agentID` (`agentID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tblAgent`
--
ALTER TABLE `tblAgent`
  MODIFY `agentID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tblMember`
--
ALTER TABLE `tblMember`
  MODIFY `memberID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tblMember`
--
ALTER TABLE `tblMember`
  ADD CONSTRAINT `tblmember_ibfk_1` FOREIGN KEY (`agentID`) REFERENCES `tblAgent` (`agentID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
