# User schema

# --- !Ups
create table `employee` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `first_name` TEXT NOT NULL,
  `last_name` TEXT NOT NULL,
  `username` TEXT NOT NULL,
  `password` TEXT NOT NULL
)

# --- !Downs
drop table `employee`