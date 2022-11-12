<img width="1029" alt="스크린샷_2022-10-06_오후_6 05 32" src="https://user-images.githubusercontent.com/57346428/201485460-2d53fb85-37bc-450f-881c-5979020e4b7a.png">


## 목차
1. [서비스 소개](https://lab.ssafy.com/s07-bigdata-dist-sub2/S07P22D110#1-%EC%84%9C%EB%B9%84%EC%8A%A4-%EC%86%8C%EA%B0%9C)
2. [주요 기능](https://lab.ssafy.com/s07-bigdata-dist-sub2/S07P22D110#2-%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5)
3. [기술 스택](https://lab.ssafy.com/s07-bigdata-dist-sub2/S07P22D110#3-%EA%B8%B0%EC%88%A0-%EC%8A%A4%ED%83%9D)
4. [시스템 구성도](https://lab.ssafy.com/s07-bigdata-dist-sub2/S07P22D110#4-%EC%8B%9C%EC%8A%A4%ED%85%9C-%EA%B5%AC%EC%84%B1%EB%8F%84)
5. [실행 방법]($#5-실행-방법)
6. [팀원 소개](https://lab.ssafy.com/s07-bigdata-dist-sub2/S07P22D110#6-%ED%8C%80%EC%9B%90-%EC%86%8C%EA%B0%9C)


# 1. 서비스 소개
**살만할지도**는 신혼부부를 위한 주거 지역 추천 서비스 입니다.  
신혼부부가 주거 지역을 고를 때 해당 지역을 직접 가보기에는 시간과 정보가 부족하기 때문에 인터넷에 의존적이라는 문제점에 착안하여 쉽고 편하게 이용할 수 있는 웹 서비스를 개발하였습니다.  
좋은 주거 환경을 찾고 있거나 기호에 맞는 주거 지역을 구하는 신혼부부들이 사용할 수 있습니다.

🔗 [노션 바로가기](https://www.notion.so/bedc7f705856408e9b5bb121a7af4389)

# 2. 주요 기능
### ✅ 카테고리 추천 기능
사용자 유형을 선택하면 비슷한 환경의 사람들이 선택한 카테고리를 추천해주는 기능 입니다.  
기혼, 자가용, 반려동물, 자녀 유무를 선택하면 검색 횟수 기준과 평점 기준 중 원하는 것을 골라 추천받을 수 있습니다.

### ✅ 주거 지역 추천 기능
지역, 선호하는 환경, 평균 전세/매매 가격을 설정하면 그에 맞춰 주거 지역을 추천해주는 기능 입니다.  
선호하는 환경은 카테고리 중요도를 선택함으로써 설정할 수 있습니다. 교통, 재난, 안전, 의료, 반려동물, 교육, 문화, 생활의 8가지 카테고리가 있으며 각각의 세부 카테고리에 대한 중요도를 상, 중, 하로 설정할 수 있습니다.  
최대 8개의 주거 지역을 시군구 단위로 추천해주며, 이에 대한 순위를 볼 수 있습니다. 각 지역의 8가지 카테고리에 대한 점수와 상세 데이터 또한 확인할 수 있습니다.

### ✅ 게시판 기능
임신/출산, 육아/교육, 생활/건강, 사회/정책, 부동산 관련 뉴스를 조회할 수 있는 기능 입니다.  
각 카테고리에서 신혼부부에게 유용한 정보를 확인할 수 있으며, 검색을 통해 원하는 뉴스만을 조회할 수 있습니다.

# 3. 기술 스택
## backend
### local
|intellij|jdk 11|spring boot|docker|
|:---:|:---:|:---:|:---:|
|<img src="https://user-images.githubusercontent.com/57346428/201485479-1afe6c3b-f4c6-445e-9571-dfa69fe05ed5.png" height="70px" />|<img src="https://user-images.githubusercontent.com/57346428/201485473-df4c3051-d002-4ebe-bb76-9f40877354a6.png" height="70px" />|<img src="https://user-images.githubusercontent.com/57346428/201485489-c736460b-4972-4a25-ae6d-3ffe1fc03364.png" height="70px" />|<img src="https://user-images.githubusercontent.com/57346428/201485465-fd9a1093-624f-4ec1-b90d-041b53db4abf.png" height="70px" />|
### server (ec2)
|docker|jenkins|nginx|mariaDB|apache spark|apache hadoop|
|:---:|:---:|:---:|:---:|:---:|:---:|
|<img src="https://user-images.githubusercontent.com/57346428/201485465-fd9a1093-624f-4ec1-b90d-041b53db4abf.png" height="70px" />|<img src="https://user-images.githubusercontent.com/57346428/201485481-a64eef1c-5b2c-477b-928b-07167c7ceddb.png" height="70px" />|<img src="https://user-images.githubusercontent.com/57346428/201485478-c010bb71-e543-4d70-88f8-b54029e9db94.png" height="70px" />|<img src="https://user-images.githubusercontent.com/57346428/201485474-a6525625-bb89-4353-90e1-605d935941a4.png" height="70px" />|<img src="https://user-images.githubusercontent.com/57346428/201485488-fcec88de-ccc8-4c09-b94b-d65a828266fd.png" height="70px" />|<img src="https://user-images.githubusercontent.com/57346428/201485472-0dd9121e-2995-452e-bc48-0309cee0df4d.png" width="70px" />|

## frontend
|HTML|CSS|JavaScript|
|:---:|:---:|:---:|
|<img src="https://user-images.githubusercontent.com/57346428/201485466-acdca075-fda0-4270-a624-13f65b68eb73.svg" height="70px" />|<img src="https://user-images.githubusercontent.com/57346428/201485464-d9acf4e1-bc99-4c53-9ee1-e0ea5208151f.svg" height="70px" />|<img src="https://user-images.githubusercontent.com/57346428/201485471-b2af26e8-a01f-4fca-99df-ba55197e7221.svg" height="70px" />|

|React|Redux|Sass|Mui|Mapbox|
|:---:|:---:|:---:|:---:|:---:|
|<img src="https://user-images.githubusercontent.com/57346428/201485482-4271f58e-eff9-4210-a501-226c37fe63cd.svg" height="70px" />|<img src="https://user-images.githubusercontent.com/57346428/201485485-39e73f7a-0b45-4745-9992-1a4b2cc20c22.svg" height="70px" />|<img src="https://user-images.githubusercontent.com/57346428/201485486-bc1ff015-0878-4b91-a669-26474ef15d46.svg" height="70px" />|<img src="https://user-images.githubusercontent.com/57346428/201485476-067081ef-b33d-4897-95f5-4698cae36c9d.png" height="70px" />|<img src="https://user-images.githubusercontent.com/57346428/201485459-05d15236-7c3b-495a-b1c6-a0b8232f8fc3.png" height="70px" />|

# 4. 시스템 구성도

![시스템_구성도](https://user-images.githubusercontent.com/57346428/201485462-46483276-6adb-4e4b-996e-373ab7b70094.png)

# 5. 실행 방법
## backend
### CI/CD  
gitlab(back branch) push or merge -> jenkins webhook -> auto build  

### MobaXterm
![MobaXterm](https://user-images.githubusercontent.com/57346428/201485469-470bb75f-b8dc-45bb-80b4-d7b558282723.png)

### docker logs
![docker](https://user-images.githubusercontent.com/57346428/201485468-7687b564-eb41-46c4-8092-89cd740635c1.png)
```
docker logs
ex ) docker logs --tail 300 salmanhaljido_server_bluedocker logs --tail 300 salmanhaljido_server_blue
```

### 에러 대처 방법
path 에러가 발생할 경우 로컬 path로 변경 후 실행

src/main/resources/data -> local path

## frontend
1. 레포지토리를 clone 받는다.
```bash
git clone https://lab.ssafy.com/s07-bigdata-dist-sub2/S07P22D110.git
```
2. `front` 폴더에서 `package.json`에 정의된 모듈을 설치한다.
```shell
npm install
```
3. 환경 변수를 설정한다.
 - 이를 위해 [Mapbox API](https://www.mapbox.com)의 access token을 발급받아야 한다.
 - `front` 폴더에서 `.env` 파일을 생성하고 아래 내용을 기입한다.
 ```plain
 REACT_APP_MAPBOX_ACCESS_TOKEN={발급받은 Mapbox API access token}
 ```
4. 프로그램을 실행시킨다.
```shell
npm start
```


# 6. 팀원 소개
|김주영|박진경|이기종|이상민|이재영|
|:---:|:---:|:---:|:---:|:---:|
|front(팀장)|front|back|back(팀장)|front|
