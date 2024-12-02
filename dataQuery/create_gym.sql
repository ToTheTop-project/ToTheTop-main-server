show databases ;

# 클라이밍장 스키마 생성
create database gyms;

use gyms;

show tables;

create table gyms(
    id BIGINT primary key KEY AUTO_INCREMENT,
    gymId BIGINT unique not null ,
    gymName varchar(20),
    thumUrl varchar(1000) null,
    address varchar(300),
    roadAddress varchar(300),
    metro varchar(100),
    isParking boolean not null ,
    latitude decimal not null ,
    longitude decimal not null ,
    ktCallMd varchar(500)
);

select *
from gyms;
