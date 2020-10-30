CREATE TABLE `events` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `hash_id` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
    `field_name` varchar(25) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
    `old_value` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
    `new_value` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
    `event_time` timestamp COLLATE utf8_unicode_ci NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`hash_id`, `event_time`, `field_name`)
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;