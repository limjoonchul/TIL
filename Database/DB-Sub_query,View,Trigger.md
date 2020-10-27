# DB 수업 내용 정리

## sub query : select, from, where 절에 들어갈 수 있다. 쿼리안에 쿼리를 쓰는 것
### Select에서 사용
####  전체 나라수, 도시 수, 언어 수를 출력하세요.
```roomsql
use world;
select ( select  count(*) from country ) as total_country,
( select  count(*) from city ) as total_city,
(select  count(distinct(language))from countrylanguage) as total_language
from dual; # from 절에 들어가는게 없을 때 dual 사용
```
### from에서 사용
####  800만 이상이 되는 도시의 국가코드, 국가이름, 도시인구수를 출력
```roomsql
select city.countrycode, country.name, city.population
from city # 4079
join country # 239
on city.countrycode = country.code
having city.population >= 8000000;
```
#### Sub-query 사용
* 원래 4079 * 239 가 조인이 되는데 from절에 조건을 줘서 걸러진다음에 조인을 하게 한다.
* 많은 데이터를 가지고 하면 큰 차이가 난다. 이건 10 * 239
```roomsql
select city.countrycode, country.name, city.population
from (select * from city where population >= 8000000) as city # 10
join country # 239
on city.countrycode = country.code;
```
### where에서 사용
#### 900만 이상 도시를 가진 국가의 국가 코드, 국가 이름, 대통령 이름을 출력하세요.
```roomsql
select code, name, headofstate 
from country
where code in( # BRA, IDN, IND ...
select distinct(countrycode )from city where population >=9000000);
```


## view : 가상 테이블 테이블처럼 사용할 수 있다. 
* 실제로 데이터를 가지고 있진 않다 실제데이터는 테이블에있고 참조해서 사용하는 것이다.
* 실제 데이터를 저장하지 않습니다. 수정 및 인덱스 설정이 불가능.
* 쓰는 이유 : 쿼리를 단순하게 만들어주기 위해서 사용됩니다.

### 국가 코드와 국가 이름이 있는 뷰 생성
```roomsql
create view code_name_k  as 
select code, name
from country
where code like "k%";

select * from code_name_k;
```

* 뷰를 사용하지 않았을 때 원래는 이렇게 해야한다
```roomsql
select * 
from city
join (select code, name
from country
where code like "k%")
on city.countrycode =code_name_k.code;
```

* 뷰 사용
```roomsql
select * from city
join code_name_k
on city.countrycode =code_name_k.code;
```

## 연습 문제
### 1. 멕시코(mexico)보다 인구가 많은 나라의 이름과 인구수를 조회하고 인구순으로 정렬
* 멕시코 인구수는 업데이트 될 수 있다. 인구수를 넣어서 쿼리를 만들면안되도 테이블에서 쿼리를 생성해서 넣으라는말 select로
*숫자를 쓰면 데이터가 바뀌었을 때 잘 못 될수 있어서 사용하지말라고 한것임
```roomsql
select name, population
from country
where population >= (select population from country where name ="mexico")
order by population desc;
```

### 2. 국가별 몇개의 도시가 있는지 출력하고 10위까지 내림차순(도시순)으로 정렬
* 국가명, 도시수, join, group by
```roomsql
select country.name , count(city.name) as count
from city
join country
on city.countrycode = country.code
group by country.name
order by count desc
limit 10;
```

### 3. 언어별 사용 인구수를 출력하고 사용인구순으로 상위 10개의 언어를 출력
* 언어, 사용인구(country 테이블의 population을 이용)
* percentage * 인구수 * 0.01
* sub query, join, group by
* 국가별 언어별 사용인구 데이터 만들기 언어별 그룹핑 (sum)

#### 순서 1.국가별 언어별 사용인구 데이터 만들기
```roomsql
select ct.code, cl.language , round(ct.population * cl.percentage * 0.01) as count
from country as ct
join countrylanguage as cl
on ct.code = cl.countrycode;
```
#### 순서 2.언어별 그룹핑 추가
* 순서1.로 먼저 데이터를 축소시켜서 from에 넣으면 그 테이터들안에서 select 할 수 있으니깐~ 
* 뷰를 만들어서 내용을 줄여서 사용할 때 from에 넣으면 될 듯
```roomsql
select sub.language, sum(sub.count) as population
from(
  select ct.code, cl.language , round(ct.population * cl.percentage * 0.01) as count
from country as ct
join countrylanguage as cl
on ct.code = cl.countrycode
) as sub
group by sub.language
order by population desc
limit 10;
```

### 4. 국가 인구의 10% 이상의 인구가 있는 도시에서 도시 인구가 500만 이상인 도시를 출력
* 국가코드, 국가이름, 도시이름, 도시인구수, 도시 인구 비율
* join, sub-query

##### join 후에 having절에서 필터링하는 방법 비효율적인 방법
```roomsql
select  country.code , country.name , city.name 
, country.population as country_p
, city.population as city_p
, round(country.population / city.population * 100,2) as percentage
from country,city
where country.code =city.countrycode
having city_p >= 5000000 and percentage >= 10
order by percentage desc;
```

#### sub query에서 필터링 후 join하는 방법
````roomsql
select  country.code , country.name , city.name 
     , country.population as country_p
     , city.population as city_p
     , round(country.population / city.population * 100,2) as percentage
from country, (select * from city where population >= 5000000) as city
where country.code =city.countrycode
having percentage >= 10
order by percentage desc;
````

### 5. 국가 인구밀도가 200 이상인 국가중에 사용하는 언어의 갯수가 2개인 국가들을 출력하세요.
#### (1) 인구밀도(density) surface(country) 인구/면적
```roomsql
 select code, name , (population / surfacearea) as density
  from country
  having density>=200;
```

#### (2)사용하는 언어가 2개인 국가를 출력 : 1을 서브쿼리로 하고 countrylanguage를 join
````roomsql
select ct.name, count(cl.language) as language_count
from (
  select code, name , (population / surfacearea) as density
  from country
  having density>=200
) as ct
join countrylanguage as cl
on ct.code = cl.countrycode;
````

#### (3) 국가를 기준으로 group by, count() >> having = 2
```roomsql
select ct.name, count(cl.language) as language_count
from (
  select code, name , (population / surfacearea) as density
  from country
  having density>=200
) as ct
join countrylanguage as cl
on ct.code = cl.countrycode
group by city.name
having language_count = 2;
```
#### (4) 인구밀도 출력
* group_concat() 숫자를 문자열로 나열하고 싶을 대 이걸 사용
* language의 숫자 2개가놨는데 이게 2개 코리아랑 차이나 언어가 두개가 있을 수 있다고했을 때
* 이 언어들을 출력해줄 때 사용한다. 
```roomsql
select ct.name, max(ct.desity), count(cl.language) as language_count
     , group_concat(cl.language) as language_list 
from (
  select code, name , (population / surfacearea) as density
  from country
  having density>=200
) as ct
join countrylanguage as cl
on ct.code = cl.countrycode
group by city.name
having language_count = 2;

create view density as
select code, name , round(population / surfacearea,2) as density
from country
having density>=200;

select * from density;
```

#### 뷰로 만듬 
```roomsql
select ct.name, max(ct.desity), count(cl.language) as language_count
     , group_concat(cl.language) as language_list
from density as ct
join countrylanguage as cl
on ct.code = cl.countrycode
group by city.name
having language_count = 2;
```

### 6. 한국과 미국의 인구와 gnp를 세로로 출력하세요.
* if , sub-query, union

| category  | KOR |  USA |
|----------| ----------- | ------ |
| population | 46800000    | 27800000|
| gnp        | 320000      | 8500000|

```roomsql
select "population"as category, sum(ct.kor)as kor, sum(ct.usa) as usa
from(
select 
     if(code = "kor", population, 0) as kor,
     if(code = "usa", population, 0) as usa,   
     1 as tmp
from country
) as ct
group by tmp
union
select "gnp"as category, sum(ct.kor)as kor, sum(ct.usa) as usa
from(
select 
     if(code = "kor", gnp, 0) as kor,
     if(code = "usa", gnp, 0) as usa,   
     1 as tmp
from country
) as ct
group by tmp;
```


## index : 테이블에서 데이터를 빠르게 검색할수 있도록 도와주는 기능
* 장점 : 검색속도가 빨라짐(select)
* 단점 : 테이블의 저장공간을 더 많이 차지하고, insert, update, delete 속도가 느려진다.
* 이유는 인덱스 공간을 따로 만들어 둬야 해서 
*  사용방법 : where절에서 조건으로 사용하는 컬럼을 index로 설정하면 좋음.where절에서 자주사용하는 것을 index로 설정
*  작동원리 : B-Tree 알고리즘으로 작동
* 인덱스는 컬럼단위로 설정 가능 

# 0.047sec
````roomsql
select *
from salaries
where from_date < "1986-01-01";
````

### 인덱스를 사용하는지 확인하는 explain 명령어
* possible_key, extra를 확인하면 된다.
*  인덱스도 이름을 설정할 수 있는데 이것을 나타내는게 possible_key
````roomsql

explain
select *
from salaries
where from_date < "1986-01-01";

explain
select *
from salaries
where to_date < "1986-01-01";

show index from salaries;

create index fdate
on salaries (from_date);

create index tdate
on salaries (to_date);

create index ftdate
on salaries (from_date,to_date); # 하나씩맞찾을 땐 따로하는게 좋은데 항상같이사용할 땐 이렇게 묶는게 좋음

select *
from salaries
where to_date < "1986-01-01";

````


### 인덱스 삭제
```roomsql
drop index fdate on salaries;
drop index tdate on salaries;
```


## TRIGGER
* 특정 테이블을 감시하고 있다가 설정된 조건의 쿼리가 실행되면 미리 지정된 쿼리가 자동으로 실행되도록 하는 방법
* syntax
* create trigger {trigger name}
* {before | after} {insert | update | delete}
* 쿼리가 실행되기 전인지 후인지 설정하는 것 , 예약어 이런동작을 할 때 트리거를 걸 수  있다.
* on <table name> for each row
* begine
*    <excute query>
* end

```roomsql
use jbs2;
create table data1(
     id varchar(20),
     msg varchar(50) not null
);

create table data_backup(
     id varchar(20),
     msg varchar(50) not null
);
```

* 바뀌기전에 데이터 delete 데이터 전의 데이터이다 old after을 써야 new를 사용 가능
* 하나의 문장이라고 정의해주는 것 delimiter 하나의 쿼리문이라고 묶어줌
```roomsql
delimiter |  
  create trigger backup
  before delete on data1
  for each row
   begin 
	insert into data_backup(id,msg) values(old.id, old.msg);
  end |
  
  show triggers;
  
  insert into data1(id, msg)
  values("dss","good!"),("jbs","nice"),("dss","bad");

  select * from data1;
  delete from data1
  where id ="dss"
  limit 10;
  
  select * from data_backup;
```