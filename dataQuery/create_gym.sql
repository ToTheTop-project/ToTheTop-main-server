show databases ;

# 클라이밍장 스키마 생성
create database gyms;

use gyms;

show tables;

drop table gyms;

create table gyms(
    id BIGINT primary key KEY AUTO_INCREMENT,
    naverId BIGINT unique not null ,
    name varchar(20) not null,
    thumbUrl varchar(1000) null,
    address varchar(300) not null,
    roadAddress varchar(300) not null,
    metroString varchar(100),
    isPossibleParking boolean not null ,
    latitude decimal not null ,
    longitude decimal not null ,
    ktCallMd varchar(500)
);

select *
from gyms;
