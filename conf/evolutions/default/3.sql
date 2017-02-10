# User schema

# --- !Ups
create table `usercs` (
  `username` VARCHAR NOT NULL  PRIMARY KEY,
  `password` VARCHAR NOT NULL,
  `first_name` VARCHAR NOT NULL,
  `last_name` VARCHAR NOT NULL,
  `status` VARCHAR NOT NULL
)

# --- !Downs
drop table `usercs`