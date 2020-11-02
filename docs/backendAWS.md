# backend-ec2-rds

rds 인스턴스 생성, ec2인스턴스 생성 springboot 연동
* * *
## ec2 연결 & rds 연결

1. 펨키가 잇는 위치에서 cmd창을 키고 다음을 입력한다
```
ssh -i "crunch.pem" ubuntu@ec2-3-35-136-163.ap-northeast-2.compute.amazonaws.com
```
1. mysql을 다음을 입력하여 킨다
```
mysql -u admin -p -h db-crunch.cbyzchmu8dpf.ap-northeast-2.rds.amazonaws.com
```
2. 비밀번호를 친다

* * *
### ec2구성

home
 ubuntu
  usr
   git
    CRUNCH-backend
     backend
      crunch_server
       gradlew
       build
       nohup.out
      crunch_server*.jar
    deploy.sh
     docs
     package-lock.json
     README.md
   git-frontend
    HAISHEN-project
    deploy.sh
   tmp(임시파일)
   
* * *
### rds instance << local: mysql database 'crunch' import

1. mysql dump file 생성
```
mysqldump -u root crunch > crunch_20201027.sql
```
1. 로컬--> 원격(pem key있는 곳에 이동할 파일 복사)
```
scp -i "crunch.pem" "crunch_20201027.sql" ubuntu@ec2-3-35-136-163.ap-northeast-2.compute.amazonaws.com:/home/ubuntu/tmp
```
2. 원격--> 원격 dump sql 파일 이동(sql 이 있는 자리에서)
```
mysql -h db-crunch.cbyzchmu8dpf.ap-northeast-2.rds.amazonaws.com -u admin -p crunch < crunch_20201027.sql
```

* 권한 수정
$ sudo chown root:root /home
$ sudo chmod 777 /home/tmp

### rds mysql tables 확인

```
mysql -u admin -p -h db-crunch.cbyzchmu8dpf.ap-northeast-2.rds.amazonaws.com

show databases;
use crunch;

show tables;
```
* * *
## springboot(gradle)-ec2배포

### 환경 조성

1. jdk 설치
```
cd ~/usr
sudo apt-get update
sudo apt-get install openjdk-8-jdk
java -version
javac -version
```
2. java 환경변수 확인
```
echo $JAVA_HOME
which javac
##/usr/bin/javac
readlink -f /usr/bin/javac
##/usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java
 
##copy 
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64

sudo vim /etc/profile
i
...
  unset i
  unset -f pathmunge

  #JAVA PATH
  export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
done(fi?)

:wq
source /etc/profile
```
3. 확인
```
echo $JAVA_HOME
$JAVA_HOME/bin/javac -version
```
### git clone && git remote add(언제든 pull할 수 있는 환경 조성)

```
cd ~/usr/git
git clone https://github.com/HAISHEN-CapstonDesign/CRUNCH-backend.git

cd HAISHEN-project
git init
git remote add origin https://github.com/HAISHEN-CapstonDesign/CRUNCH-backend.git
git pull

ls -al
```
### project test,build,run

#### deploy script 생성

vim ~/usr/git/deploy.sh
1. REPOSITORY 에 build directory 주소 저장
2. 이동->git pull
3. gradlew에 권한 부여후 gradlew build(프로젝트 빌드)
4. build 파일을 REPOSITORY로 복사
5. 기존 실행중인 스프링부트 process id 가져옴
6. 실행중인 스프링부트가 있다면 종료(kill)시키고 5초 대기
7. 복사했던 build 파일명 저장
8. 복사했던 build 파일 실행

#### deploy script 실행

```
chomod 755 ./deploy.sh
./deploy.sh
ps -ef|grep crunch_server
```
#### 실행로그 확인
```
tail -f /home/ubuntu/usr/git/CRUNCH-backend/backend/crunch_server/nohup.out
```
* * *
## 외부 서비스로 배포
3.35.136.163:3000

* 인바운드 편집 필요 -> 완료
* pm2 예정
<https://artiiicy.tistory.com/12>
<https://javaexpert.tistory.com/957>
* hibernate 적용 필요 -> 완료
<https://osc131.tistory.com/117>

* 참고자료
<https://jojoldu.tistory.com/263>
<https://yhmane.tistory.com/23?category=773048>
<https://pu1et-panggg.tistory.com/69?category=884280>







