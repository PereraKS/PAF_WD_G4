-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2021 at 07:41 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gadgetbadgetdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `membershiplevels`
--

CREATE TABLE `membershiplevels` (
  `id` int(11) NOT NULL,
  `membership_Name` varchar(30) NOT NULL,
  `pricing` double NOT NULL,
  `benefits` varchar(50) NOT NULL,
  `researchID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `membershiplevels`
--

INSERT INTO `membershiplevels` (`id`, `membership_Name`, `pricing`, `benefits`, `researchID`) VALUES
(1, 'Gold', 5000, 'Acces to all previous ', 1),
(2, 'Silver', 2500, 'Acces to previous month', 1),
(3, 'bronze', 0, 'Current Status only ', 1);

-- --------------------------------------------------------

--
-- Table structure for table `research`
--

CREATE TABLE `research` (
  `id` int(11) NOT NULL,
  `title` varchar(30) NOT NULL,
  `category` varchar(30) NOT NULL,
  `description` varchar(200) NOT NULL,
  `progress` int(11) NOT NULL,
  `estimateBudget` double NOT NULL,
  `addedDate` varchar(30) NOT NULL,
  `approvalStatus` varchar(30) NOT NULL,
  `resercherName` varchar(30) NOT NULL,
  `resercherEmail` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `research`
--

INSERT INTO `research` (`id`, `title`, `category`, `description`, `progress`, `estimateBudget`, `addedDate`, `approvalStatus`, `resercherName`, `resercherEmail`) VALUES
(2, 'Science Imporvement', '2', 'science improvement paths', 5, 34000, '2021-04-25', 'local', 'Krishan', 'Krishan@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `researchcategory`
--

CREATE TABLE `researchcategory` (
  `categoryID` int(11) NOT NULL,
  `categoryName` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `researchcategory`
--

INSERT INTO `researchcategory` (`categoryID`, `categoryName`) VALUES
(1, 'science'),
(2, 'social');

-- --------------------------------------------------------

--
-- Table structure for table `researcherprofile`
--

CREATE TABLE `researcherprofile` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `researcherprofile`
--

INSERT INTO `researcherprofile` (`id`, `name`, `email`, `phone`) VALUES
(1, 'Krishan', 'krishan@gmail.com', 767158801);

-- --------------------------------------------------------

--
-- Table structure for table `researchstatus`
--

CREATE TABLE `researchstatus` (
  `approvalid` int(11) NOT NULL,
  `researchID` int(11) NOT NULL,
  `progress` varchar(100) NOT NULL,
  `approval` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `researchstatus`
--

INSERT INTO `researchstatus` (`approvalid`, `researchID`, `progress`, `approval`) VALUES
(2, 2, '3', 'sendAP'),
(9, 23, '2', 'sendAP');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `membershiplevels`
--
ALTER TABLE `membershiplevels`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `research`
--
ALTER TABLE `research`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `researchcategory`
--
ALTER TABLE `researchcategory`
  ADD PRIMARY KEY (`categoryID`);

--
-- Indexes for table `researcherprofile`
--
ALTER TABLE `researcherprofile`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `researchstatus`
--
ALTER TABLE `researchstatus`
  ADD PRIMARY KEY (`approvalid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `membershiplevels`
--
ALTER TABLE `membershiplevels`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `research`
--
ALTER TABLE `research`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `researchcategory`
--
ALTER TABLE `researchcategory`
  MODIFY `categoryID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `researcherprofile`
--
ALTER TABLE `researcherprofile`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `researchstatus`
--
ALTER TABLE `researchstatus`
  MODIFY `approvalid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
