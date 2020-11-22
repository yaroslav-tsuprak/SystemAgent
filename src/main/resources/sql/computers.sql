CREATE TABLE `computers` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `computer_hash_id` bigint(20) COLLATE utf8_unicode_ci NOT NULL,
    `inventar_number` varchar(25) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
    `computer_state` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT '1',
    `last_active` timestamp default current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY (`computer_hash_id`)
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
