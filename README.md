# CRUNCH-backend
하이선 프로젝트 - backend


### 문제 정의
-----

최근 몇 년 사이, 온라인상에는 조회수를 목적으로 하는 질이 떨어지는 컨텐츠들이 주로 생산되었다. 이에 따라 무료 컨텐츠를 찾아 헤맬 시간을 줄이고, 질이 보장된 유료 컨텐츠 구매를 선호하는 트렌드와 코로나 19와 함께 온라인 유료 컨텐츠 구매추이는 더욱 증가하였다.

CRUNCH는 이러한 트렌드에 맞춘 글쓰기 플랫폼이다. CRUNCH는 타 글쓰기 플랫폼들과 다르게 글을 쓴 작가에게 사전 펀딩, 유료 컨텐츠 구매, 온라인 광고 시청을 통해 수익을 보장한다. 또한 관심분야의 작가들과 협업하며 글을 쓸 수 있는 툴들을 제공한다. 

### 주요 타겟
-----

<center><img src = "https://user-images.githubusercontent.com/52434154/112952317-5188fd00-9177-11eb-8b66-0e31b42aea02.png" width = "400" height = "200"></center>


### 주요 기술
-----

<center><img src = "https://user-images.githubusercontent.com/52434154/112952386-636aa000-9177-11eb-9ac3-35b142a5b0c8.png" width = "400" height = "200"></center>


#### 이외의 기술

1. I'm port - 포인트 충전을 할 수 있는 결제 모듈
2. Stomp - 채팅 서비스를 위한 스트리밍 메시지 프로토콜
3. Diff Algorithms - diff알고리즘이란, 두 개의 파일 간 차이에 대한 정보를 출력하는 파일 비교 유틸리티다. 본 프로젝트는 최적으로 두개의 글을 비교하기 위해서 Myers' Diff알고리즘과 Histogram Diff알고리즘을 이용한다. Myers' Diff는 최단거리를 구하는 알고리즘, histogram diff은 속도를 최적화하는 알고리즘이다. 공동 프로젝트에서 다수의 작가들이 수정한 내역을 기록하고 비교하여 보여주기 위해 활용하였다.

### er_diagram 
-----
![er_diagram_pictureversion](https://user-images.githubusercontent.com/52434154/112955365-4edbd700-917a-11eb-92bc-7f5fde21684d.png)


### 개발 내용
-----

**공동 글쓰기 페이지** - 여러 작가들이 함께 글을 쓸 수 있는 글쓰기 환경. 글을 목차별로 편집할 수 있고, 수정 내역을 작가별로 저장할 수 있다.

<center><img src = "https://user-images.githubusercontent.com/52434154/112961179-16d79280-9180-11eb-9cab-74ba47fec016.png"></center>


**히스토리 확인 페이지** - 저장된 수정내역을 확인하고 되돌리기를 할 수 있다. 공동 작업물 관리를 편리하게 한다.

<center><img src = "https://user-images.githubusercontent.com/52434154/112961324-3b336f00-9180-11eb-93e9-9ef1536fd8fd.png"></center>

**공동 프로젝트 커뮤니티** - 프로젝트에 참여하는 작가들이 소통할 수 있는 채팅 서비스. 목차별로 나눠진 대화방에서 프로젝트에 관한 대화를 나눌 수 있다. 

<center><img src = "https://user-images.githubusercontent.com/52434154/112957143-1d640b00-917c-11eb-90e9-f4a5b56d68f5.png"></center>

**컨텐츠 열람 페이지** - 독자가 컨텐츠를 열람할 수 있는 페이지. 유료 컨텐츠 열람시에는 포인트가 차감된다. 구독, 댓글, 좋아요 등이 있다.

<center><img src = "https://user-images.githubusercontent.com/52434154/112961895-c3b20f80-9180-11eb-98c4-342282cd272f.png"></center>

**포인트 결제 페이지** - Crunch 플랫폼에서 유료 컨텐츠 구매 혹은 펀딩을 위해 사용하는 포인트를 충전할 수 있는 결제 페이지. I’mport를 활용하여 구현하였다. 

![f4](https://user-images.githubusercontent.com/52434154/112962282-2c998780-9181-11eb-937d-103964eed490.png)

**수익 정산 내역 확인** - 자신이 참여한 프로젝트의 목차별 포스트들로 발생한 유료 수익을 확인할 수 있는 페이지. 

![image](https://user-images.githubusercontent.com/52434154/112957420-60be7980-917c-11eb-94f9-3f54b7e70f2e.png)
![image](https://user-images.githubusercontent.com/52434154/112957447-687e1e00-917c-11eb-8aeb-8ddb24ae04b6.png)

**이외의 기능** 

프로젝트 모집하기, 지원/채택하기, 프로젝트 사전 펀딩하기, eBook 다운받기


### 결과 및 분석
-----

**문제 해결 - 작가**

작가들은 개인으로 작업을 하거나 작가들을 모집하여 프로젝트를 시작할 수 있다. 작가는 모집된 작가들과 집필 내용을 검수할 수 있고, 컨텐츠 제작 분량에 대한 부담도 덜 수 있다. 
집필 과정에서는 수정 내역이 기록되는 협업 툴과 커뮤니티 서비스를 통해 원활한 글쓰기 프로젝트를 진행할 수 있다. 
이후에는 컨텐츠 유료화를 통해 소수만 관심을 가지는 전문 분야라 하더라도 수익창출이 가능하고, 컨텐츠 제작 사전 펀딩을 통해 수익보장과 글쓰기에 동기부여를 해줄 수 있다. 

**문제 해결 - 독자**

독자들은 퀄리티가 높고 완결된 컨텐츠를 열람할 수 있고, 쉽게 제공되지 않는 전문 분야의 지식을 소정의 금액 지불로 열람할 수 있으며, eBook 다운로드를 통해 오프라인에서도 컨텐츠 열람이 가능하다


### 테스트 설계
-----

**사용기술** : Junit4, Mockito, Hamcrest

TDD 는 크게 controller 에 대한 test 와, service 에 대한 test 로 진행하였다. 이때 controller 는 spring boot test 로 진행, service 는 mock test 로 진행하여 database 를 직접 이용하지 않고 mockito 관련 기능을 이용한다

basic test code format 형식을 따름
1. Setup Fixture
2. Verify Outcome
3. Cleaning up Fixture


#### 사용한 오픈소스
https://github.com/java-diff-utils/java-diff-utils



