CREATE TABLE `computers` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `hash_id` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
    `serial_number` varchar(25) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
    `computer_state` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`, `hash_id`)
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
