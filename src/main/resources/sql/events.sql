CREATE TABLE `events` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `computer_hash_id` bigint(20) NOT NULL,
    `message_id` int(2) NOT NULL,
    `field_name` varchar(255) NOT NULL DEFAULT '',
    `old_value` varchar(255) NOT NULL DEFAULT '',
    `new_value` varchar(255) NOT NULL DEFAULT '',
    `event_time` timestamp default current_timestamp(),
    PRIMARY KEY (`id`),
    UNIQUE KEY (`computer_hash_id`, `event_time`, `field_name`)
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;