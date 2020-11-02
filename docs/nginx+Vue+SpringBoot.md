# vue - nginx - Spring Boot

ec2 Instance에서 vue,  nginx 연동
이후로 nginx, Spring Boot 연동

* * *
## vue + nginx 배포

1. 펨키가 잇는 위치에서 cmd창을 키고 다음을 입력한다
```
ssh -i "crunch.pem" ubuntu@ec2-3-35-136-163.ap-northeast-2.compute.amazonaws.com
```
2. HAISHEN-project 나올때까지 이동
```
cd ~/usr/git-frontend
```
3. 미리 짜논 배포 자동화 코드 실행
```
./deploy.sh
```
->(deploy.sh) 최근 forntend소스 pull -> 프로젝트 build시작 (npm install, npm run build) -> Build파일 'dist'복사 -> 
현재 구동중인 nginx 프로세스 확인후 구동중인 nginx 종료 -> 새롭게 기동 -> nginx status확인
*OR 'sudo systemctl restart nginx' 실행

4. 3.35.136.163(:80) 실행

## nginx + spring boot 배포
1. spring boot 실행과 동일
./deploy.sh

2. server log 확인
```
tail -f /home/ubuntu/usr/git/CRUNCH-backend/backend/crunch_server/nohup.out
```
* * *
### 종료시
nginx 종료 - sudo service nginx stop
nohup(back server종료) 
1. ps -ef | grep crunch_server
2. 처음 pid확인(프로세스 아이디)
3. kill -9 pid숫자

* * *
### vue + nginx config file 

```
cd /etc/nginx/sites-available
vim default

...
#root 설정
root /var/www/html/dist;
#server 이름 설정
server_name 3.35.136.163;
#location / 설정
location / {
  try_files $uri $uri/ /index.html
}

```
### nginx + spring boot config file

```
cd /etc/nginx/sites-available
vim default

...
#location /api 설정
location /api {
  proxy_pass http://localhost:3000/api/;
  #header 설정 
}

```

* * *
## 외부 서비스로 배포
3.35.136.163:3000
3.35.136.163

## 다음 기약 - local에서 작업 진행 & cors문제 아직은 안떴지만 문제가 나타나면 & S3

* 참고자료
<https://jay-ji.tistory.com/57?category=870634>
<https://shinjongpark.github.io/2020/02/17/AWS-nginx-vue-spring-ssl.html>







