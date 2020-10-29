# Crontab
* 유닉스 OS계열에서 특정 시간에 특정 작업을 해야하는 경우 사용하는 스케줄러입니다.

## Crontab basic
1. 스케줄 선정
* 아래의 커멘드를 입력하면 스케줄을 설정할 수 있는 vi 에디터 페이지가 생성된다. 여기에 어떤 주기로
어떤 파일을 실행할지에 대한 리스트를 작성해주면 된다.
```roomsql
$ crontab -e
```
2. 스케줄 리스트 확인
* 현재 crontab의 스케줄을 확인할 수 있다.
```roomsql
$ crontab -l
```
## 주기 설정
* time.py를 아래 코드로 설정한다.
```text
import datetime
today = datetime.datetime.now()
print(str(today))
```
```text
*        *         *        *       *
분(0-59) 시간(0-23) 일(1-31) 월(1-12) 요일(0-7)
요일에서 0과 7은 일요일이다.

// 2분 간격으로 실행
*/2 * * * * python3 /home/ubuntu/time.py >> time.txt

// 매시 10분에 실행
10 * * * * python3 /home/ubuntu/time.py >> time.txt

// 매시 10분과 20분에 실행
10,20 * * * * python3 /home/ubuntu/time.py >> time.txt

// 매일 5시 10분과 20분에 실행
10,20 5 * * * python3 /home/ubuntu/time.py >> time.txt

// 일요일 5시 10분과 20분에 실행
10,20 5 * * 0 python3 /home/ubuntu/time.py >> time.txt

// 5시에서 10시까지 매시에 5분마다 time.py를 실행하고 결과를 time.txt에 저장
*/5 5-10 * * 0 python3 /home/ubuntu/time.py >> time.txt
```

## time zone 변경
```text
// 현재 사용 시간 확인
$ timedatectl

// 사용하는 파일 심볼릭 링크확인
$ ls -l /etc/localtime

// 사용 할수 있는 타임존 확인
$ timedatectl list-timezones | grep Asia

// 타임존 변경
$ sudo timedatectl set-timezone Asia/Seoul

// 심볼릭 링크 수정
$ sudo unlink /etc/localtime
$ sudo ln -s /usr/share/zoneinfo/Asia/Seoul /etc/localtime
```

## crontab 로그 확인
```text
// 아래의 명령으로 crontab의 시스템 로그를 확인 할수 있습니다.
$ grep CRON /var/log/syslog

// crontab 에러 확인 및 해결
// 에러가 발생하면 아래와 같은 에러 로그를 확인할수 있습니다.
------------------------------------------------------------------------------------------------
* Mar 4 07:42:01 ip-172-31-3-64 CRON[7494]: (CRON) info (No MTA installed, discarding output)
------------------------------------------------------------------------------------------------


// MTA : Mail Transfer Agent

// 에러 메시지 확인하는 방법 1
- $ sudo apt-get install postfix
- $ cat /var/mail/ubuntu

// 에러 메시지 확인하는 방법 2
- $ sudo apt install mailutils
- $ mail

// crontab에서 실행되는 python 환경
// crontab 에서는 .bash_profile이 실행되지 않기때문에 pyenv 환경이 적용되지 않습니다.

* version.py 안에 밑에 코드 설정
------------------------------------------------------------------------------------------------
 import sys
 print(sys.version.split(" ")[0])
------------------------------------------------------------------------------------------------

* crontab -e 안에 밑에 처러 설정한다.
------------------------------------------------------------------------------------------------
 * * * * * python /home/ubuntu/version.py >> version.txt
------------------------------------------------------------------------------------------------
// pyenv 환경에 있는 python으로 실행

* 직접 python 경로 입력
------------------------------------------------------------------------------------------------
* * * * * /home/ubuntu/.pyenv/versions/python3/bin/python /home/ubuntu/version.py
>> version.txt
------------------------------------------------------------------------------------------------

PATH 설정
------------------------------------------------------------------------------------------------
PATH=/usr/local/bin/:/sbin:/bin:/usr/sbin:/home/ubuntu/.pyenv/versions/python3/bin
* * * * * python /home/ubuntu/version.py >> version.txt
------------------------------------------------------------------------------------------------
```

## 백업
### Backup의 종류

#### Hot Backup
* 데이터 베이스를 중지하지 않은 상태로 데이터 백업
* 백업하는 동안 서비스가 실행
* 백업하는 동안 데이터가 변경되어 완전한 백업이 안될수 있음

#### Cold Backup
* 데이터 베이스를 중지한 상태로 데이터 백업
* 안정적으로 백업이 가능
*  백업하는 동안 서비스가 중단되어야 함


#### Logical Backup
* SQL 문으로 백업
* 느린 속도의 백업과 복원
*  디스크 용량을 적게 사용
*  작업시 시스템 자원을 많이 사용
*  문제 발생에 대한 파악이 쉬움
*  서버 OS 호환이 잘됨

#### Physical Backup
* 파일 차체를 백업
* 빠른 속도의 백업과 복원
*  디스크 용량 많이 사용
*  작업시 시스템 자원을 적게 사용
*  문제 발생에 대한 파악과 검토가 어려움
*  서버 OS 호환이 잘안될수 있음

### 실습  : Hot Logical Backup

* Backup : 백업
```text
// test 데이터 베이스를 test_backup.sql 파일로 백업
//  mysqldump -u root -p(패스워드) (데이터베이스) > (저장할 파일 이름)
—————————————————————————————————————
$ mysqldump -u root -prada test > test_backup.sql
—————————————————————————————————————

// backup shell script 작성 (backup.sh)
—————————————————————————————————————
# backup 파일을 저장할 스크립트 작성
$ mkdir backup

$ vi backup.sh
#!/bin/bash

BD=`date +%Y%m%d_%H%M --date=today`
FILE=${BD}.sql

cd backup
# echo "mysqldump -u root -prada test > $FILE"
mysqldump -u root -prada test > $FILE
—————————————————————————————————————

// backup.sh 스크립트 실행
—————————————————————————————————————
$ /bin/bash backup.sh
—————————————————————————————————————

// crontab을 이용하여 주기적으로 명령을 실행 : 5분에 한번씩 백업
—————————————————————————————————————
$ crontab -e
# /usr/bin : mysqldump를 실행
# /bin : /bin/bash를 실행
PATH=/usr/bin:/bin:/home/ubuntu/.pyenv/versions/python3/bin
*/5 * * * * /bin/bash /home/ubuntu/backup.sh
—————————————————————————————————————
```

## Data Replication
```text
1. Setting Instance : Install Mysql Database

$ sudo apt-get update
$ sudo apt-get install -y mysql-server mysql-client
$ sudo mysql

mysql> ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password
BY 'rada';
mysql> FLUSH PRIVILEGES;
mysql> exit

$ sudo vi /etc/mysql/mysql.conf.d/mysqld.cnf
- bind-address = 0.0.0.0

$ sudo mysql -u root -prada
mysql> grant all privileges on *.* to root@'%' identified by 'rada';

$ sudo service mysql restart

2. Setting Master Server

$ sudo vi /etc/mysql/mysql.conf.d/mysqld.cnf
—————————————————————————————————————
server_id = 1
log_bin = /var/log/mysql/mysql-bin.log
binlog_do_db = mydb1
—————————————————————————————————————

$ sudo service mysql restart

$ mysql -u root -p
mysql> GRANT REPLICATION SLAVE ON *.* TO 'root'@'%' IDENTIFIED BY 'rada';
mysql> FLUSH PRIVILEGES;

mysql> CREATE DATABASE mydb1;
mysql> show databases;
mysql> use mydb1

// 데이터 베이스 백업을 위해 테이블에 LOCK을 걸어둔다.
mysql> FLUSH TABLES WITH READ LOCK;

// Master 의 상태를 확인한다.
mysql> SHOW MASTER STATUS;

+---------------------+----------+-----------------+---------------------+
| File | Position | Binlog_Do_DB | Binlog_Ignore_DB |
+---------------------+----------+-----------------+---------------------+
| mysql-bin.000001 | 589 | mydb1 | |
+---------------------+----------+-----------------+---------------------+
1 row in set (0.00 sec)

Posion Number 를 기억해 둬야한다. Slave 설정시에 사용해야 함.

// mydb1 데이터 베이스를 mydb1.sql 파일로 백업한다.
$ mysqldump -u root -p --opt mydb1 > mydb1.sql

// 데이터 베이스의 LOCK을 해제
$ mysql -u root -p
mysql> use mydb1;
mysql> UNLOCK TABLES;

3. Setting Slave Server

// mydb1 데이터 베이스 생성
$ mysql -u root -p
mysql> CREATE DATABASE mydb1;
mysql> exit

// 설정 파일 변경
$ sudo vi /etc/mysql/mysql.conf.d/mysqld.cnf
—————————————————————————————————————
server_id = 2
relay-log = /var/log/mysql/mysql-relay-bin.log
log_bin = /var/log/mysql/mysql-bin.log
binlog_do_db = mydb1
—————————————————————————————————————

// mysql 서버 재시작
$ sudo service mysql restart

// Replication Setup on Slave
mysql> CHANGE MASTER TO MASTER_HOST='Master 퍼블릭 IP주소 ',
MASTER_USER='root', 
MASTER_PASSWORD='password', 
MASTER_LOG_FILE='mysql-bin.000001', // MATER의 파일명
MASTER_LOG_POS=589; // 마스터의 POSTION NUMBER


// 동기화 시작
mysql> START SLAVE;

// 동기화 상태 확인
mysql> SHOW SLAVE STATUS\G

// Slave에 동기화가 깨졌을때 마스터 데이터베이스의 쿼리 스킵
mysql> SET GLOBAL SQL_SLAVE_SKIP_COUNTER=1;

// slave 설정 초기화
mysql> reset slave all;


// 데이터 베이스의 프로세스 리스트를 확인 : master의 업데이트 대기중 확인
mysql> show processlist;
```