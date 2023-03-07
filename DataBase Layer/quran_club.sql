-- phpMyAdmin SQL Dump
-- version 5.3.0-dev+20221117.561d9ca705
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 07, 2023 at 03:15 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quran_club`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL,
  `userlogin_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin_id`, `userlogin_id`) VALUES
(18, 42);

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE `class` (
  `class_id` int(11) NOT NULL,
  `class_name` varchar(100) DEFAULT NULL,
  `class_date_created` date DEFAULT NULL,
  `techer_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`class_id`, `class_name`, `class_date_created`, `techer_id`) VALUES
(44, 'class1', '0000-00-00', 20);

-- --------------------------------------------------------

--
-- Table structure for table `image_level`
--

CREATE TABLE `image_level` (
  `image_level_id` int(11) NOT NULL,
  `image_level_path` varchar(2000) DEFAULT NULL,
  `image_level_number` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `lesson`
--

CREATE TABLE `lesson` (
  `lesson_id` int(11) NOT NULL,
  `lesson_name` varchar(500) DEFAULT NULL,
  `lesson_date_created` timestamp NULL DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `lesson`
--

INSERT INTO `lesson` (`lesson_id`, `lesson_name`, `lesson_date_created`, `class_id`) VALUES
(58, 'the new lesson', '2023-02-15 01:38:00', 44),
(59, '', '0000-00-00 00:00:00', 44);

-- --------------------------------------------------------

--
-- Table structure for table `quran_part`
--

CREATE TABLE `quran_part` (
  `quran_part_id` int(11) NOT NULL,
  `quran_part_number` int(11) DEFAULT NULL,
  `quran_part_the_number_of_verses` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `quran_part`
--

INSERT INTO `quran_part` (`quran_part_id`, `quran_part_number`, `quran_part_the_number_of_verses`) VALUES
(1, 1, 184),
(2, 2, 111),
(3, 3, 125),
(4, 4, 132),
(5, 5, 124),
(6, 6, 112),
(7, 7, 147),
(8, 8, 142),
(9, 9, 158),
(10, 10, 128),
(11, 11, 150),
(12, 12, 170),
(13, 13, 154),
(14, 14, 227),
(15, 15, 185),
(16, 16, 270),
(17, 17, 189),
(18, 18, 202),
(19, 19, 337),
(20, 20, 170),
(21, 21, 178),
(22, 22, 169),
(23, 23, 356),
(24, 24, 174),
(25, 25, 245),
(26, 26, 195),
(27, 27, 398),
(28, 28, 136),
(29, 29, 430),
(30, 30, 563);

-- --------------------------------------------------------

--
-- Table structure for table `quran_part_surah`
--

CREATE TABLE `quran_part_surah` (
  `quran_part_surah_id` int(11) NOT NULL,
  `quran_part_id` int(11) DEFAULT NULL,
  `surah_id` int(11) DEFAULT NULL,
  `quran_part_surah_from` int(11) DEFAULT NULL,
  `quran_part_surah_to` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `quran_part_surah`
--

INSERT INTO `quran_part_surah` (`quran_part_surah_id`, `quran_part_id`, `surah_id`, `quran_part_surah_from`, `quran_part_surah_to`) VALUES
(1, 1, 1, 1, 7),
(2, 1, 2, 1, 141),
(3, 2, 2, 142, 252),
(4, 3, 2, 253, 286),
(5, 3, 3, 1, 92),
(6, 4, 3, 93, 200),
(7, 4, 4, 1, 23),
(8, 5, 4, 24, 147),
(9, 6, 4, 148, 176),
(10, 6, 5, 1, 70),
(11, 7, 5, 71, 120),
(12, 7, 6, 1, 110),
(13, 8, 6, 111, 165),
(14, 8, 7, 1, 87),
(15, 9, 7, 88, 206),
(16, 9, 8, 1, 40),
(17, 10, 8, 41, 75),
(18, 10, 9, 1, 93),
(19, 11, 9, 94, 129),
(20, 11, 10, 1, 109),
(21, 11, 11, 1, 5),
(22, 12, 11, 6, 123),
(23, 12, 12, 1, 52),
(24, 13, 12, 53, 111),
(25, 13, 13, 1, 43),
(26, 13, 14, 1, 52),
(27, 14, 15, 1, 99),
(28, 14, 16, 1, 128),
(29, 15, 17, 1, 111),
(30, 15, 18, 1, 74),
(31, 16, 18, 75, 110),
(32, 16, 19, 1, 98),
(33, 16, 20, 1, 135),
(34, 17, 21, 1, 112),
(35, 17, 22, 1, 78),
(36, 18, 23, 1, 118),
(37, 18, 24, 1, 64),
(38, 18, 25, 1, 20),
(39, 19, 25, 21, 77),
(40, 19, 26, 1, 227),
(41, 19, 27, 1, 55),
(42, 20, 27, 56, 93),
(43, 20, 28, 1, 88),
(44, 20, 29, 1, 45),
(45, 21, 29, 46, 69),
(46, 21, 30, 1, 60),
(47, 21, 31, 1, 34),
(48, 21, 32, 1, 30),
(49, 21, 33, 1, 30),
(50, 22, 33, 31, 73),
(51, 22, 34, 1, 54),
(52, 22, 35, 1, 45),
(53, 22, 36, 1, 27),
(54, 23, 36, 28, 83),
(55, 23, 37, 1, 182),
(56, 23, 38, 1, 88),
(57, 23, 39, 1, 31),
(58, 24, 39, 32, 75),
(59, 24, 40, 1, 85),
(60, 24, 41, 1, 46),
(61, 25, 41, 47, 54),
(62, 25, 42, 1, 53),
(63, 25, 43, 1, 89),
(64, 25, 44, 1, 59),
(65, 25, 45, 1, 37),
(66, 26, 46, 1, 35),
(67, 26, 47, 1, 38),
(68, 26, 48, 1, 29),
(69, 26, 49, 1, 18),
(70, 26, 50, 1, 45),
(71, 26, 51, 1, 30),
(72, 27, 51, 31, 60),
(73, 27, 52, 1, 49),
(74, 27, 53, 1, 62),
(75, 27, 54, 1, 55),
(76, 27, 55, 1, 78),
(77, 27, 56, 1, 96),
(78, 27, 57, 1, 29),
(79, 28, 58, 1, 22),
(80, 28, 59, 1, 24),
(81, 28, 60, 1, 13),
(82, 28, 61, 1, 14),
(83, 28, 62, 1, 11),
(84, 28, 63, 1, 11),
(85, 28, 64, 1, 18),
(86, 28, 65, 1, 12),
(87, 28, 66, 1, 12),
(88, 29, 67, 1, 30),
(89, 29, 68, 1, 52),
(90, 29, 69, 1, 52),
(91, 29, 70, 1, 44),
(92, 29, 71, 1, 28),
(93, 29, 72, 1, 28),
(94, 29, 73, 1, 20),
(95, 29, 74, 1, 56),
(96, 29, 75, 1, 40),
(97, 29, 76, 1, 31),
(98, 29, 77, 1, 50),
(99, 30, 78, 1, 40),
(100, 30, 79, 1, 46),
(101, 30, 80, 1, 42),
(102, 30, 81, 31, 29),
(103, 30, 82, 1, 19),
(104, 30, 83, 1, 36),
(105, 30, 84, 1, 25),
(106, 30, 85, 1, 22),
(107, 30, 86, 1, 17),
(108, 30, 87, 1, 19),
(109, 30, 88, 1, 26),
(110, 30, 89, 1, 30),
(111, 30, 90, 1, 20),
(112, 30, 91, 1, 15),
(113, 30, 92, 1, 21),
(114, 30, 93, 1, 11),
(115, 30, 94, 1, 8),
(116, 30, 95, 1, 8),
(117, 30, 96, 1, 19),
(118, 30, 97, 1, 5),
(119, 30, 98, 1, 8),
(120, 30, 99, 1, 8),
(121, 30, 100, 1, 11),
(122, 30, 101, 1, 11),
(123, 30, 102, 1, 8),
(124, 30, 103, 1, 3),
(125, 30, 104, 1, 9),
(126, 30, 105, 1, 5),
(127, 30, 106, 1, 4),
(128, 30, 107, 1, 7),
(129, 30, 108, 1, 3),
(130, 30, 109, 1, 6),
(131, 30, 110, 1, 3),
(132, 30, 111, 1, 5),
(134, 30, 112, 1, 4),
(135, 30, 113, 1, 5),
(136, 30, 114, 1, 6);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role_name`) VALUES
(1, 'asd');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `student_id` int(11) NOT NULL,
  `number_of_dauan` int(11) DEFAULT NULL,
  `userlogin_id` int(11) DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`student_id`, `number_of_dauan`, `userlogin_id`, `class_id`) VALUES
(24, 3, 63, 44);

-- --------------------------------------------------------

--
-- Table structure for table `student_lesson`
--

CREATE TABLE `student_lesson` (
  `student_lesson_id` int(11) NOT NULL,
  `student_lesson_note` varchar(2000) DEFAULT NULL,
  `lesson_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student_lesson`
--

INSERT INTO `student_lesson` (`student_lesson_id`, `student_lesson_note`, `lesson_id`, `student_id`) VALUES
(63, 'حفظ الايات بشكل جيد شكرا على جهودكم برجى مراجعة اادرس ااقادم حتى نكمل اامشوار ', 58, 24);

-- --------------------------------------------------------

--
-- Table structure for table `student_lesson_quran_part_surah`
--

CREATE TABLE `student_lesson_quran_part_surah` (
  `student_lesson_quran_part_surah_id` int(11) NOT NULL,
  `student_lesson_id` int(11) NOT NULL,
  `quran_part_surah_id` int(11) DEFAULT NULL,
  `from_verses` int(11) NOT NULL,
  `to_verses` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student_lesson_quran_part_surah`
--

INSERT INTO `student_lesson_quran_part_surah` (`student_lesson_quran_part_surah_id`, `student_lesson_id`, `quran_part_surah_id`, `from_verses`, `to_verses`) VALUES
(61, 63, 2, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `student_quran_part`
--

CREATE TABLE `student_quran_part` (
  `student_quran_part_id` int(11) NOT NULL,
  `student_id` int(11) DEFAULT NULL,
  `quran_part_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student_quran_part`
--

INSERT INTO `student_quran_part` (`student_quran_part_id`, `student_id`, `quran_part_id`) VALUES
(50, 24, 2),
(53, 24, 3),
(38, 24, 4);

-- --------------------------------------------------------

--
-- Table structure for table `surah`
--

CREATE TABLE `surah` (
  `surah_id` int(11) NOT NULL,
  `surah_name` varchar(100) DEFAULT NULL,
  `surah_quran_part_the_number_of_verses` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `surah`
--

INSERT INTO `surah` (`surah_id`, `surah_name`, `surah_quran_part_the_number_of_verses`) VALUES
(1, 'الفَاتِحَة\r\n', 7),
(2, 'البَقَرَة\r\n', 286),
(3, 'آل عِمرَان\r\n', 200),
(4, 'النِّسَاء\r\n', 176),
(5, 'المَائدة\r\n', 120),
(6, 'الأنعَام\r\n', 165),
(7, 'الأعرَاف\r\n', 206),
(8, 'الأنفَال\r\n', 75),
(9, 'التوبَة\r\n', 129),
(10, 'يُونس\r\n', 109),
(11, 'هُود\r\n', 123),
(12, 'يُوسُف\r\n', 111),
(13, 'الرَّعْد\r\n', 43),
(14, 'إبراهِيم\r\n', 52),
(15, 'الحِجْر\r\n', 99),
(16, 'النَّحْل\r\n', 128),
(17, 'الإسْرَاء\r\n', 111),
(18, 'الكهْف\r\n', 110),
(19, 'مَريَم\r\n', 98),
(20, 'طه\r\n', 135),
(21, 'الأنبيَاء\r\n', 112),
(22, 'الحَج\r\n', 78),
(23, 'المُؤمنون\r\n', 118),
(24, 'النُّور\r\n', 64),
(25, 'الفُرْقان\r\n', 77),
(26, 'الشُّعَرَاء\r\n', 227),
(27, 'النَّمْل\r\n', 93),
(28, 'القَصَص\r\n', 88),
(29, 'العَنكبوت\r\n', 69),
(30, 'الرُّوم\r\n', 60),
(31, 'لقمَان\r\n', 34),
(32, 'السَّجدَة\r\n', 30),
(33, 'الأحزَاب\r\n', 73),
(34, 'سَبَأ\r\n', 54),
(35, 'فَاطِر\r\n', 45),
(36, 'يس\r\n', 83),
(37, 'الصَّافات\r\n', 182),
(38, 'ص\r\n', 88),
(39, 'الزُّمَر\r\n', 75),
(40, 'غَافِر\r\n', 85),
(41, 'فُصِّلَتْ\r\n', 54),
(42, 'الشُّورَى\r\n', 53),
(43, 'الزُّخْرُف\r\n', 89),
(44, 'الدخَان\r\n', 59),
(45, 'الجَاثيَة\r\n', 37),
(46, 'الأحْقاف\r\n', 35),
(47, 'محَمَّد\r\n', 38),
(48, 'الفَتْح\r\n', 29),
(49, 'الحُجرَات\r\n', 18),
(50, 'ق\r\n', 45),
(51, 'الذَّاريَات\r\n', 60),
(52, 'الطُّور\r\n', 49),
(53, 'النَّجْم\r\n', 62),
(54, 'القَمَر\r\n', 55),
(55, 'الرَّحمن\r\n', 78),
(56, 'الوَاقِعَة\r\n', 96),
(57, 'الحَديد\r\n', 29),
(58, 'المجَادلة\r\n', 22),
(59, 'الحَشر\r\n', 24),
(60, 'المُمتَحنَة\r\n', 13),
(61, 'الصَّف\r\n', 14),
(62, 'الجُمُعَة\r\n', 11),
(63, 'المنَافِقون\r\n', 11),
(64, 'التغَابُن\r\n', 18),
(65, 'الطلَاق\r\n', 12),
(66, 'التحْريم\r\n', 12),
(67, 'المُلْك\r\n', 30),
(68, 'القَلَم\r\n', 52),
(69, 'الحَاقَّة\r\n', 52),
(70, 'المعَارج\r\n', 44),
(71, 'نُوح\r\n', 28),
(72, 'الجِن\r\n', 28),
(73, 'المُزَّمِّل\r\n', 20),
(74, 'المُدَّثِّر\r\n', 56),
(75, 'القِيَامَة\r\n', 40),
(76, 'الإنسَان\r\n', 31),
(77, 'المُرسَلات\r\n', 50),
(78, 'النَّبَأ\r\n', 40),
(79, 'النّازعَات\r\n', 46),
(80, 'عَبَس\r\n', 42),
(81, 'التَّكوير\r\n', 29),
(82, 'الانفِطار\r\n', 19),
(83, 'المطفِّفِين\r\n', 36),
(84, 'الانْشِقَاق\r\n', 25),
(85, 'البرُوج\r\n', 22),
(86, 'الطَّارِق\r\n', 17),
(87, 'الأَعْلى\r\n', 19),
(88, 'الغَاشِية\r\n', 26),
(89, 'الفَجْر\r\n', 30),
(90, 'البَلَد\r\n', 20),
(91, 'الشَّمْس\r\n', 15),
(92, 'الليْل\r\n', 21),
(93, 'الضُّحَى\r\n', 11),
(94, 'الشَّرْح\r\n', 8),
(95, 'التِّين\r\n', 8),
(96, 'العَلَق\r\n', 19),
(97, 'القَدْر\r\n', 5),
(98, 'البَينَة\r\n', 8),
(99, 'الزلزَلة\r\n', 8),
(100, 'العَادِيات\r\n', 11),
(101, 'القَارِعة\r\n', 11),
(102, 'التَّكَاثر\r\n', 8),
(103, 'العَصْر\r\n', 3),
(104, 'الهُمَزَة\r\n', 9),
(105, 'الفِيل\r\n', 5),
(106, 'قُرَيْش\r\n', 4),
(107, 'المَاعُون\r\n', 7),
(108, 'الكَوْثَر\r\n', 3),
(109, 'الكَافِرُون\r\n', 6),
(110, 'النَّصر\r\n', 3),
(111, 'المَسَد\r\n', 5),
(112, 'الإخْلَاص\r\n', 4),
(113, 'الفَلَق\r\n', 5),
(114, 'النَّاس', 6);

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `teacher_id` int(11) NOT NULL,
  `userlogin_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`teacher_id`, `userlogin_id`) VALUES
(20, 60),
(22, 78),
(23, 83),
(24, 84),
(25, 85);

-- --------------------------------------------------------

--
-- Table structure for table `userlogin`
--

CREATE TABLE `userlogin` (
  `userlogin_id` int(11) NOT NULL,
  `full_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `phone` varchar(200) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `date_created` date DEFAULT NULL,
  `role_name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `userlogin`
--

INSERT INTO `userlogin` (`userlogin_id`, `full_name`, `email`, `password`, `phone`, `age`, `date_created`, `role_name`) VALUES
(42, 'ioasf', 'admin@gmail.com', '123456', 'asfjoafjwo', 7, NULL, 'Admin'),
(60, 'noor', 'noor@gmail.com', '123456', '+962790898621', 123, NULL, 'Teacher'),
(63, 'ضياء الدين ', 'asd@gmail.com', '123456', '+962790898621', 0, NULL, 'Student'),
(78, '', 'asdasd2@gmail.com', '123', '07894984590', 0, NULL, 'Teacher'),
(83, 'deaa3', 'asdas@gmail.com', '123', '07894984590', 0, NULL, 'Teacher'),
(84, 'deaa3', 'asas@gmail.com', '123', '07894984590', 0, NULL, 'Teacher'),
(85, 'deaa3', 'asasafsdfs@gmail.com', '123', '07894984590', 0, NULL, 'Teacher');

--
-- Triggers `userlogin`
--
DELIMITER $$
CREATE TRIGGER `INSERT_UserLoginId_By_RoleName` AFTER INSERT ON `userlogin` FOR EACH ROW BEGIN
        IF new.role_name = 'Student' THEN
          insert into student(student.student_id,student.userlogin_id)VALUES(DEFAULT,new.userlogin_id);
        ELSEIF new.role_name='Teacher' THEN
            insert into teacher(teacher.teacher_id,teacher.userlogin_id)VALUES(DEFAULT,new.userlogin_id);
        ELSEIF new.role_name='Admin' THEN
            insert into admin(admin.admin_id,admin.userlogin_id)VALUES(DEFAULT,new.userlogin_id);
           END IF;
       END
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_id`),
  ADD UNIQUE KEY `userlogin_id` (`userlogin_id`);

--
-- Indexes for table `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`class_id`),
  ADD KEY `techer_id` (`techer_id`);

--
-- Indexes for table `image_level`
--
ALTER TABLE `image_level`
  ADD PRIMARY KEY (`image_level_id`);

--
-- Indexes for table `lesson`
--
ALTER TABLE `lesson`
  ADD PRIMARY KEY (`lesson_id`),
  ADD KEY `fk_class_id` (`class_id`);

--
-- Indexes for table `quran_part`
--
ALTER TABLE `quran_part`
  ADD PRIMARY KEY (`quran_part_id`);

--
-- Indexes for table `quran_part_surah`
--
ALTER TABLE `quran_part_surah`
  ADD PRIMARY KEY (`quran_part_surah_id`),
  ADD KEY `quran_part_surah_fk2` (`quran_part_id`),
  ADD KEY `quran_part_surah_fk1` (`surah_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`student_id`),
  ADD UNIQUE KEY `userlogin_id` (`userlogin_id`),
  ADD KEY `class_id` (`class_id`);

--
-- Indexes for table `student_lesson`
--
ALTER TABLE `student_lesson`
  ADD PRIMARY KEY (`student_lesson_id`),
  ADD KEY `lesson_id` (`lesson_id`),
  ADD KEY `student_id` (`student_id`);

--
-- Indexes for table `student_lesson_quran_part_surah`
--
ALTER TABLE `student_lesson_quran_part_surah`
  ADD PRIMARY KEY (`student_lesson_quran_part_surah_id`),
  ADD KEY `studentLesson_QuranPartSurah_fk1` (`student_lesson_id`),
  ADD KEY `studentLesson_QuranPartSurah_fk2` (`quran_part_surah_id`);

--
-- Indexes for table `student_quran_part`
--
ALTER TABLE `student_quran_part`
  ADD PRIMARY KEY (`student_quran_part_id`),
  ADD UNIQUE KEY `uq_student_quran_part` (`student_id`,`quran_part_id`),
  ADD KEY `quran_part_id` (`quran_part_id`);

--
-- Indexes for table `surah`
--
ALTER TABLE `surah`
  ADD PRIMARY KEY (`surah_id`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`teacher_id`),
  ADD UNIQUE KEY `userlogin_id` (`userlogin_id`);

--
-- Indexes for table `userlogin`
--
ALTER TABLE `userlogin`
  ADD PRIMARY KEY (`userlogin_id`),
  ADD UNIQUE KEY `reference_unique` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `class`
--
ALTER TABLE `class`
  MODIFY `class_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT for table `image_level`
--
ALTER TABLE `image_level`
  MODIFY `image_level_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `lesson`
--
ALTER TABLE `lesson`
  MODIFY `lesson_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT for table `quran_part`
--
ALTER TABLE `quran_part`
  MODIFY `quran_part_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `quran_part_surah`
--
ALTER TABLE `quran_part_surah`
  MODIFY `quran_part_surah_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=137;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `student_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `student_lesson`
--
ALTER TABLE `student_lesson`
  MODIFY `student_lesson_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT for table `student_lesson_quran_part_surah`
--
ALTER TABLE `student_lesson_quran_part_surah`
  MODIFY `student_lesson_quran_part_surah_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT for table `student_quran_part`
--
ALTER TABLE `student_quran_part`
  MODIFY `student_quran_part_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT for table `surah`
--
ALTER TABLE `surah`
  MODIFY `surah_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=115;

--
-- AUTO_INCREMENT for table `teacher`
--
ALTER TABLE `teacher`
  MODIFY `teacher_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `userlogin`
--
ALTER TABLE `userlogin`
  MODIFY `userlogin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=86;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`userlogin_id`) REFERENCES `userlogin` (`userlogin_id`) ON DELETE CASCADE;

--
-- Constraints for table `class`
--
ALTER TABLE `class`
  ADD CONSTRAINT `class_ibfk_1` FOREIGN KEY (`techer_id`) REFERENCES `teacher` (`teacher_id`) ON DELETE SET NULL;

--
-- Constraints for table `lesson`
--
ALTER TABLE `lesson`
  ADD CONSTRAINT `fk_class_id` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Constraints for table `quran_part_surah`
--
ALTER TABLE `quran_part_surah`
  ADD CONSTRAINT `quran_part_surah_fk1` FOREIGN KEY (`surah_id`) REFERENCES `surah` (`surah_id`) ON DELETE CASCADE ON UPDATE SET NULL,
  ADD CONSTRAINT `quran_part_surah_fk2` FOREIGN KEY (`quran_part_id`) REFERENCES `quran_part` (`quran_part_id`) ON DELETE CASCADE ON UPDATE SET NULL;

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`userlogin_id`) REFERENCES `userlogin` (`userlogin_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `student_ibfk_2` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE SET NULL;

--
-- Constraints for table `student_lesson`
--
ALTER TABLE `student_lesson`
  ADD CONSTRAINT `student_lesson_ibfk_1` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`lesson_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `student_lesson_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE;

--
-- Constraints for table `student_lesson_quran_part_surah`
--
ALTER TABLE `student_lesson_quran_part_surah`
  ADD CONSTRAINT `studentLesson_QuranPartSurah_fk1` FOREIGN KEY (`student_lesson_id`) REFERENCES `student_lesson` (`student_lesson_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `studentLesson_QuranPartSurah_fk2` FOREIGN KEY (`quran_part_surah_id`) REFERENCES `quran_part_surah` (`quran_part_surah_id`) ON DELETE SET NULL;

--
-- Constraints for table `student_quran_part`
--
ALTER TABLE `student_quran_part`
  ADD CONSTRAINT `student_quran_part_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `student_quran_part_ibfk_2` FOREIGN KEY (`quran_part_id`) REFERENCES `quran_part` (`quran_part_id`) ON DELETE SET NULL;

--
-- Constraints for table `teacher`
--
ALTER TABLE `teacher`
  ADD CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`userlogin_id`) REFERENCES `userlogin` (`userlogin_id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
