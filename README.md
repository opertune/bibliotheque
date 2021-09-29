# bibliotheque
javafx / sql bibliotheque + ddb 

Database : 

DROP TABLE IF EXISTS `book`;
CREATE TABLE IF NOT EXISTS `book` (
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `author` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `year` int(4) DEFAULT NULL,
  `pages` int(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `book` (`title`, `author`, `year`, `pages`, `id`) VALUES
('Titre1', 'Auteur1', 1234, 123, 1),
('Titre2', 'Auteur2', 5678, 456, 2),
('Titre3', 'Auteur3', 9999, 789, 3),
('Titre4', 'Auteur4', 4444, 444, 5),
('Titre5', 'Auteur5', 5555, 555, 6),
('titre6', 'auteur6', 6666, 666, 7),
('Titre7', 'Auteur7', 7777, 777, 8),
COMMIT;
