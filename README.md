# bibliotheque
java, sql + database
library for storing books


DATABASE FOR EXEMPLE

DROP TABLE IF EXISTS `book`;
CREATE TABLE IF NOT EXISTS `book` (
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `author` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `year` int(4) DEFAULT NULL,
  `pages` int(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `img` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `book` (`title`, `author`, `year`, `pages`, `id`, `img`) VALUES
('Titre 2', 'Auteur 2', 2222, 222, 31, 'src/main/java/fr/romain/bibliotheque/images/github.jpg'),
('Titre 4', 'Auteur 4', 4444, 444, 33, 'src/main/java/fr/romain/bibliotheque/images/');
COMMIT;
