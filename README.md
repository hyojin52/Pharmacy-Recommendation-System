### Pharmacy-Recommendation

[외부 API(카카오 주소 검색 API)](https://developers.kakao.com/docs/latest/ko/local/dev-guide)와 [공공 데이터(약국 현황 정보)](https://www.data.go.kr/data/15065023/fileData.do)를 활용함으로써 혼자 개발하고 마무리 되는 프로젝트가 아닌 실제 서비스 가능한 수준의 프로젝트를 경험 해본다.

추천된 약국의 길 안내는 [카카오 지도 및 로드뷰 바로가기 URL](https://apis.map.kakao.com/web/guide/#routeurl)로 제공 된다.

### 요구사항 분석
- 약국 찾기 서비스 요구사항
  - 약국 현황 데이터(공공 데이터)를 관리하고 있다 라고 가정하고, 약국 현황 데이터는 위도 경도의 위치 정보 데이터를 가지고 있다.
  - 해당 서비스로 주소 정보를 입력하여 요청하면 위치 기준에서 가까운 약국 3 곳을 추출 한다.
  - 주소는 도로명 주소 또는 지번을 입력하여 요청 받는다.
    - 정확한 주소를 입력 받기 위해 [Kakao 우편번호 서비스](https://postcode.map.daum.net/guide) 사용
  - 주소는 정확한 상세 주소(동, 호수)를 제외한 주소 정보를 이용하여 추천 한다.
    - ex) 서울 성북구 종암로 10길
  - 입력 받은 주소를 위도, 경도로 변환 하여 기존 약국 데이터와 비교 및 가까운 약국을 찾는다.
    - 지구는 평면이 아니기 때문에, 구면에서 두 점 사이의 최단 거리 구하는 공식이 필요
    - 두 위 경도 좌표 사이의 거리를 haversine formula로 계산
    - 지구가 완전한 구형이 아니 므로 아주 조금의 오차가 있다.
  - 입력한 주소 정보에서 정해진 반경(10km) 내에 있는 약국만 추천 한다.
  - 추출한 약국 데이터는 길안내 URL 및 로드뷰 URL로 제공 한다.
    - ex)
    
      길안내 URL : https://map.kakao.com/link/map/우리회사,37.402056,127.108212
    
      로드뷰 URL : https://map.kakao.com/link/roadview/37.402056,127.108212
  - 길안내 URL은 고객에게 제공 되기 때문에 가독성을 위해 shorten url로 제공 한다.
  - shorten url에 사용 되는 key값은 인코딩하여 제공 한다.
    - ex) http://localhost:8080/dir/nqxtX
    
      base62를 통한 인코딩
  - shorten url의 유효 기간은 30일로 제한 한다.

### Pharmacy Recommendation Process
![image](https://github.com/user-attachments/assets/8b19f737-63bf-45ce-bc3d-996e17bddf5c)


### Direction Shorten Url Process
![image](https://github.com/user-attachments/assets/deaa8fb1-3d66-4a5d-adfc-06c976427491)


### Tech Stack
JDK 11
Spring Boot 2.6.7
Spring Data JPA
Gradle
Handlebars
Lombok
Github
Docker
AWS EC2
Redis
MariaDB
Spock
Testcontainers


### Result
<img width="1464" alt="스크린샷 2024-07-23 오후 1 54 33" src="https://github.com/user-attachments/assets/045b4e1b-19fa-4d83-8969-3ea79fce67de">

<img width="1465" alt="스크린샷 2024-07-23 오후 1 54 50" src="https://github.com/user-attachments/assets/dd3c0e76-768a-4d0c-b8a8-d47a5c39bb9f">

<img width="1465" alt="스크린샷 2024-07-23 오후 1 55 03" src="https://github.com/user-attachments/assets/8e567d71-738f-42d9-9046-0fdf3b209851">


