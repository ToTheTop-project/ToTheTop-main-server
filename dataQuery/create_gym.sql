show databases;


# tothetop 데이터베이스 생성
create database tothetop;

use tothetop;

show tables;

drop table gyms;

create table gyms(
    id BIGINT primary key KEY AUTO_INCREMENT,
    naver_id BIGINT unique,
    name varchar(20) not null,
    thumb_url varchar(1000) null,
    address varchar(300) not null,
    road_address varchar(300),
    metro_String varchar(100),
    is_possible_parking boolean not null ,
    latitude decimal not null ,
    longitude decimal not null ,
    kt_call_md varchar(500)
);

SHOW VARIABLES LIKE 'character_set_database';


select count(*)
from gyms;

select count(distinct naver_id)
from gyms;

describe gyms;

drop table gyms;

ALTER DATABASE tothetop CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
ALTER TABLE gyms CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

TRUNCATE table gyms;

ALTER TABLE gymDetails DROP FOREIGN KEY gymsDetails_ibfk_1;
TRUNCATE TABLE gyms;
ALTER TABLE gymDetails ADD CONSTRAINT gymsDetails_ibfk_1 FOREIGN KEY (gym_id) REFERENCES gyms (id);


CREATE TABLE gymDetails (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    gym_id BIGINT NOT NULL,
    tel VARCHAR(20) NULL,
    price VARCHAR(2000) NULL,
    naver_booking_url VARCHAR(1000) NULL,
    homepage_url VARCHAR(1000) NULL,
    gym_info VARCHAR(5000) NULL,
    metro_distance VARCHAR(300) NULL,
    parking_type VARCHAR(20) NULL,
    parking_description VARCHAR(1000) NULL,
    is_possible_wifi BOOLEAN DEFAULT FALSE,
    is_possible_MW_toilet BOOLEAN DEFAULT FALSE,
    is_possible_booking BOOLEAN DEFAULT FALSE,
    is_possible_group BOOLEAN DEFAULT FALSE,
    is_possible_animal BOOLEAN DEFAULT FALSE,
    is_possible_kids_zone BOOLEAN DEFAULT FALSE,
    is_possible_disabled_parking BOOLEAN DEFAULT FALSE,
    is_possible_waiting_zone BOOLEAN DEFAULT FALSE,
    blog VARCHAR(500) NULL,
    homepage VARCHAR(500) NULL,
    instagram VARCHAR(500) NULL,
    facebook VARCHAR(500) NULL,
    FOREIGN KEY (gym_id) REFERENCES gyms(id) ON DELETE CASCADE
);

ALTER TABLE gymDetails
MODIFY COLUMN gym_info VARCHAR(5000) NULL;

ALTER TABLE gymDetails
MODIFY COLUMN gym_info TEXT NULL;

ALTER TABLE gymDetails
MODIFY COLUMN gym_info MEDIUMTEXT NULL;


select *
from gymDetails;

drop table gymDetails;

ALTER TABLE gymDetails DROP FOREIGN KEY gymDetails_ibfk_1;

ALTER TABLE gymDetails
ADD CONSTRAINT gymDetails_ibfk_1
FOREIGN KEY (gym_id)
REFERENCES gyms(id)
ON DELETE CASCADE;
