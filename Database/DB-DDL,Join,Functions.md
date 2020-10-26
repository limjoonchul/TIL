# DB - DDL, Function, 외래키, JOIN, UNION
* Encoding - 문자나 코드를 컴퓨터가 알아들을 수 있는 것으로 변환하는것
* Decoding -컴퓨터의 0,1을 문자, 코드로 바꾸는 것

* ASCII - 영문 특수 문자 만 쓸 수 있음 한국어 못쓴다. 8byte
* EUCKR - ASCII 코드 에서 한글만 추가한 것 16byte
* UTF8  - 전 세계 문자를 추가한 것 32byte
   * 하드웨어 때문에 나눴었는데 지금은 성능이 좋으니 UTF8 쓰면 된다,
   * 테이블 단위로 인코딩 방식을 바꿀 수 있는데 기본적으로 UTF8을 쓰면 된다.
* utf8 - md4 는 이모티콘까지 표현 가능
* 엑셀은 인코딩 방식이 UTF8이 아니다 사용할 때 조심!


* DDL 은 바로 적용이 되고 DML은 트랜잭션에 담겨져 있다가 커밋하면 적용된다.
* ORM - 데이터 정의어와 데이터 조작어의 적용되는게 다르다
* 트랜잭션 -실행 절차를 담고 있는 것, ORM안에 있는 기능이다.

* 관계형 데이터베이스에서 조인이 핵심이다, 두 개의 테이블을 한 테이블로 병합할 때 사용한다.
* 4가지 조인을 할 수 있다
   * inner Join - 교집합
   * outer Join - 합집합 없는 컬럼의 데이터는 null로 채워짐.
   * left Join  - 왼쪽 테이블을 기준으로 조인 
   * right Join - 오른쪽 테이블을 기준으로 조인
   
# CRUD : CREATE, READ(SELECT) UPDATE DELETE
## CREATE USE ALTER DROP
```roomsql
create database test;
show databases;
use test;
select database(); // 어떤 데이터베이스를 사용하고 있는지 보여준다.
```

### ALRTER 설정 수정
```roomsql
SHOW variables like "character_set_database"; // 기본 caterset은 라틴을 되어있다. mysql 에서
alter database test CHARACTER SET = UTF8; // CHARCTER SET 변경
SHOW variables like "character_set_database";
alter database world CHARACTER SET = UTF8; 
```

### ALTER : ADD, MODIFIY, DROP 특정 컬림에 대해서 수정가능
```roomsql
use jbs2;
desc user2;
alter table user2 add tmp TEXT; # 새로운 column을 추가한다
alter table user2 modify column tmp int; # column의 타입을 바꾼다.
alter table user2 drop tmp; # column을 삭제한다.
```

### Drop
```roomsql
drop database test;
use jbs2;
show tables;
drop table user1;
```


## UPDATE 
* 데이터의 값들을 변경할 때 사용한다. 조심히 사용해야 한다, 백업해서 작업하는게 중요하다.
```roomsql
update user2
set email = "jin1gmail.com" // 이렇게만했을 때 에러남, 워크벤치에서 에러를 잡아준다. 이렇게만하면 모든 이메일이 다 바뀜
where name = "jin"
limit 1; // limit을 항상 써야 한다. 몇개 바꿀지 설정해야 함

update user2
set email = "jin1gmail.com" , age =22 // 여러 개 바 꿀 수있다.
where name = "jin"
limit 1;  
```

## DELETE 데이터, 테이블을 삭제할 때 3가지 방법.
### DELETE 
* 데이터 조작어로 데이터를 삭제한다.
```roomsql
select * from user2;
delete from user2
where rdate > "2020-10-25"
limit 5;
```

### TRUNCATE 
* 구조를 남기고 데이터 삭제 DDL
```roomsql
truncate user2;
select * from user2;
```
### DROP
*  테이블을 삭제 DDL
```roomsql
drop table user2; 
```
## foreign key(외래키)
* 사용이유 : 데이터 무결성을 지키기 위해서
  *  데이터 무결성 - 데이터들을 같은 형태로 유지하도록 하는 것이라고 생각하면 된다. 유니크를 걸어주면 똑같은 데이터가 들어가지 않게 된다.
  *  foreign key의 데이터 무결성은 테이블과 테이블 사이의 데이터들을 같게 해주는 무결성을 말한다.
  *  foreign key 설정 하려면 컬럼이 unique, primary 제약 조건이 필요하다.
  *  제약조건이 없으면 foregin  key로 연결 할 수 없다.
  *  여러 곳에서 사용하려면 unique로 설정해두면 된다.

### user, money
* 테이블을 만들어 준다.
```roomsql
create table user(
   user_id int primary key auto_increment,
   name varchar(20),
   addr varchar(20)
);


create table money(
   money_id int primary key auto_increment,
   income int,
   user_id int
);
```

### 외래키 지정안한 상태에서 값 삽입
* 외래키를 지정하지 않고 값을 삽입했을 때 아무 에러없이 값들이 잘 들어간다.
```roomsql
insert into user(name, addr)
values ("jin","seoul") , ("andy","pusan");

select * from user;

insert into money(income, user_id)
values (5000,1) , (6000,2);

select * from money;

insert into money(income, user_id)
values (7000,3);
```

### 외래키를 저정해서 값 삽입

#### 1. 테이블 수정으로  FK 설정
```roomsql
truncate money;

alter table money
add constraint fk_user // fk의 이름,식별자이다. 중복되서 사용하지 않으면 된다 제약조건을 추가해준다 이름이 fk_user이다.
foreign key (user_id)  // money의 user_id
references user (user_id); // user의 user_id

desc money;

insert into money(income, user_id)
values (5000,1) , (6000,2);
select * from money;

insert into money(income, user_id)
values (7000,3); // error
```

#### 2. 테이블 생성으로 FK설정
```roomsql
drop table money;

create table money(
   money_id int primary key auto_increment,
   income int,
   user_id int,
   foreign key (user_id) references user(user_id)
);
desc money;

insert into money(income, user_id)
values (5000,1) , (6000,2);
select * from money;

insert into money(income, user_id)
values (7000,3); // error
```


### foreign key 데이터 삭제 
* 다른 테이블에서 데이터를 참조하고 있으면 삭제하지 못합니다.
```roomsql
delete from money
where user_id = 2
limit 1;

delete from user
where user_id =1; // 1은 참조하고 있어서 삭제 안됨

delete from user
where user_id =2; // 위에서 user_id = 2를 삭제해서 2는 참조 하고 있지 않아서 삭제 된다.

drop table  user; // 참조를 받고있어서 삭제도 안된다. 참조를 지우고 삭제해야함
```

## ON DELETE, ON UPDATE 설정
* 참조를 받는 데이터가 수정하거나 삭제될 때 참조하는 데이터를 설정(수정, 삭제등등) 가능
* CASCADE : 참조받는 데이터가 수정, 삭제 하면 참조하는 데이터도 수정 삭제 된다.
* SET NULL : 참조받는 데이터가 수정, 삭제 하면 참조하는 데이터는 NULL로 수정된다.

### 업데이트 되면 업데이트(CASCADE), 삭제되면 NULL값으로 수정(SET NULL)
```roomsql
select * from user;
insert into user(name, addr) values ("peter", "incheon");

drop table money;

create table money(
   money_id int primary key auto_increment,
   income int,
   user_id int,
   foreign key (user_id) references user(user_id)
   on update cascade on delete set null
);

insert into money(income, user_id)
values (5000,1) , (6000,3);

select * from money;
select * from user;
```

#### user 업데이트 
* 동기화되서 money, user의 데이터가 같이 없데이트 된다 
```roomsql
update user
set user_id = 4
where user_id =1;

```
#### user 데이터 삭제 
* 참조하고 있는 money의 user_id가 null로 바뀌게된다.
```roomsql
 delete from user
 where user_id = 3;
```

## functions

###  ceil , round , truncate
```roomsql
select ceil(12.345);
select ceil(12.345*100)/100; // 올림

select round(12.345);
select round(12.345,2); // 반올림
 
select truncate(12.345,2); // 버림 소수점을 몇번째 해줄것이지 꼭 써야함
```

### 국가별 언어 사용 비율 올림, 반올림, 버림
```roomsql
use world;
select countrycode, language, percentage
    , ceil(percentage)
    , round(percentage)
    , truncate(percentage,0)
from countrylanguage; 
```

## DATE FORMAT
```roomsql
use sakila;

select sum(amount), date_format(payment_date, "%Y-%m") as monthly
from payment
group by monthly;

select sum(amount) as income, date_format(payment_date, "%H") as monthly
from payment
group by monthly
order by  income desc;
```

## 조건문 : IF, IFNULL, CASE

### IF 
* 도시의 인구가 100만이 넘으면 "big city", 아니면 "small city"를 출력 컬럼
```roomsql
use world;
select countrycode, name, population
, if(population >= 5000000, "big city", "small city")
from city
where population >= 800000
order by population desc;
```

### IFNULL
* 테이블의 해당 컬럼에 null이 있으면 0으로 바꾼다.
```roomsql
select code, name, indepyear
, ifnull(indepyear,0) 
from country;
```

### case when then end
* 국가 인구가 10억 이상 "level3" , 1억 이상 "level2",  1억 이하 "level1"
* if문이랑 비슷하게 동작한다.
* IF 대신 WHEN THEN으로 사용하는데, WHEN다음에 조건식, THEN 다음에 들어갈 값을 써주면 된다.
```roomsql
select code, name, population
, case
     when population >= 1000000000 then "level3"
     when population >= 100000000 then "level2"
     else "level1"
  end as scale
from country
where population >= 80000000
order by population desc;
```

### 미국과 한국의 국가 코드와 인구수 gnp 출력, 피봇팅해서 출력
* 피봇팅 - 가로 세로 축을 바꾸는 것(if문이랑 같이 써야 함)
* database는 대문자로 작성하면 안나옴.
```roomsql
select code, name, population, gnp
from country
where code in ("usa","kor");
```

## JOIN(조인)
* 필터링해서 조인하는게 훨씬 성능이 좋다. 이것도 해결이 안되면 index를 써야 한다.
```roomsql
create database test;
use test;
select * from money;
create table user(
   name varchar(20),
   addr varchar(20)
);
create table money(
  name varchar(20),
  income int
);
insert into user(name, addr)
values("A","seoul"), ("B","pusan"), ("C","incheon");
select * from user;
insert into money(name, income)
values("A",100), ("B",200), ("C",300), ("D",400);
select * from money;
```

### inner join
```roomsql
select user.name, user.addr, money.income
from user
join money #아무것도 쓰지 않으면 자동 inner조인
on user.name = money.name; 
// on 대신 where을 써도 동작한다. 테이블을 여러개 조인할 수 있다. 
// 테이블의 row가 많을 때 생각하지 않고 join을 하지 않으면 오래걸리거나 터질 수 있음..
```

### left join
```roomsql
select user.name, user.addr, money.income
from user
left join money // 아무것도 쓰지 않으면 자동 inner조인
on user.name = money.name; 
```

### right join
```roomsql
select money.name, user.addr, money.income
from user
right join money // 아무것도 쓰지 않으면 자동 inner조인
on user.name = money.name;
```

## 연습문제
### city 테이블과 country 테이블을 조인해서  city 인구가 500만 넘는 도시의 국가 코드, 국가 이름, 도시 이름, 인구수를 출력하세요
```roomsql
use world;
select * 
from city;
select *
from country;

select city.countrycode, country.name, city.name, city.population
from city
join country
on city.CountryCode = country.code and city.population >= 5000000;

select city.countrycode, country.name, city.name, city.population
from city , country , countrylanguage # 이런식으로 작성할 수도 있다.
where city.CountryCode = country.code and city.population >= 5000000;
```

### 국가별, 도시별, 언어 사용률을 출력
```roomsql
select country.name as country_name, city.name as city_name,  cl.Language, cl.Percentage
from country,city, countrylanguage  as cl
where country.code = city.countrycode and  country.code = cl.countrycode;

select country.name as country_name, city.name as city_name,  cl.Language, cl.Percentage, (cl.Percentage * city.popluation) / 100 as lpp # 모든 도시에서 비율이 똑같다고할 때 도시에서 각 언어를 사용하는 인구수를 출력
from country,city, countrylanguage  as cl
where country.code = city.countrycode and  country.code = cl.countrycode and country.code in ("usa","kor");
```
## UNION
* union : select 결과를 합쳐서 출력할 때 사용, 자동으로 중복제거
* union all : 중복제거 x
```roomsql
use test;
select name
from user;

select name
from money;

select name
from user
union
select name
from money;

select name
from user
union all
select name
from money;
```
### UNION으로 Outer Join 하는 나타내는 SQL 
* mysql에는 outer 조인이 없다. union을 사용해서 나타내야한다 left조인 + right조인 + union
```roomsql
select user.name, user.addr, money.income
from user
left join money
on user.name =money.name
union
select money.name, user.addr, money.income
from user
right join money
on user.name =money.name;
```
