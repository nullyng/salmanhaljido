![스크린샷_2022-10-06_오후_6.05.32](/uploads/7764f3f943325ff626ae3159f73a97f3/스크린샷_2022-10-06_오후_6.05.32.png)

## 목차
1. [서비스 소개](https://lab.ssafy.com/s07-bigdata-dist-sub2/S07P22D110#1-%EC%84%9C%EB%B9%84%EC%8A%A4-%EC%86%8C%EA%B0%9C)
2. [주요 기능](https://lab.ssafy.com/s07-bigdata-dist-sub2/S07P22D110#2-%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5)
3. [기술 스택](https://lab.ssafy.com/s07-bigdata-dist-sub2/S07P22D110#3-%EA%B8%B0%EC%88%A0-%EC%8A%A4%ED%83%9D)
4. [시스템 구성도](https://lab.ssafy.com/s07-bigdata-dist-sub2/S07P22D110#4-%EC%8B%9C%EC%8A%A4%ED%85%9C-%EA%B5%AC%EC%84%B1%EB%8F%84)
5. [실행 방법]($5-실행-방법)
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
|<img src="/uploads/5264754477afa122f35fbc7d30306e92/pngegg.png" height="70px" />|<img src="/uploads/f464ee0fac9080b03a9aeab8e72ab87e/kisspng-java-runtime-environment-computer-icons-java-platf-java-5ade3063f31610.0948145615245108199957.png" height="70px" />|<img src="/uploads/7feea17f3e573e81b6043338653d856e/Untitled.png" height="70px" />|<img src="/uploads/c3b03b4af346b3c54f0b482681cf8da3/docker.png" height="70px" />|
### server (ec2)
|docker|jenkins|nginx|mariaDB|apache spark|apache hadoop|
|:---:|:---:|:---:|:---:|:---:|:---:|
|<img src="/uploads/c3b03b4af346b3c54f0b482681cf8da3/docker.png" height="70px" />|<img src="/uploads/80340d3581149ccee01b28f4ea111e7d/pngwing.com.png" height="70px" />|<img src="/uploads/8829eb1f5a9bb634daf2a53ae0c84479/nginx.png" height="70px" />|<img src="/uploads/df655512edbbb974a3115773db7c2e73/maria.png" height="70px" />|<img src="/uploads/205059c7ba8d5cab4d45613e6f5de9b2/spark.png" height="70px" />|<img src="/uploads/250b9abce267d902b2bf07c460a13263/kisspng-apache-hadoop-logo-big-data-data-analysis-hadoop-d-services-monitored-signalfx-5b62f0523cf851.4266811115332107062497.png" width="70px" />|

## frontend
|HTML|CSS|JavaScript|
|:---:|:---:|:---:|
|<img src="/uploads/0c985158779d08b1f1d5f729af7bc30b/html5-original-wordmark.svg" height="70px" />|<img src="/uploads/84b4982f1eb2240c1c71934029d4104e/css3-original-wordmark.svg" height="70px" />|<img src="/uploads/4021cf836dfe3bfd866241b20ca9809b/javascript-original.svg" height="70px" />|

|React|Redux|Sass|Mui|Mapbox|
|:---:|:---:|:---:|:---:|:---:|
|<img src="/uploads/ebe5513250f8eb169decc23f18dbb251/react-original-wordmark.svg" height="70px" />|<img src="/uploads/469db57f9e05e78f1b4f76d0001ce623/redux-original.svg" height="70px" />|<img src="/uploads/80026622b2db85b630368d1030122f51/sass-original.svg" height="70px" />|<img src="/uploads/d1a05ff49a4799818302c944290d0a7a/mui.png" height="70px" />|<img src="/uploads/108151bedeb2a533d2df744ddcb26337/1280px-Mapbox_logo_2019.svg.png" height="70px" />|

# 4. 시스템 구성도

![시스템_구성도](/uploads/4fb67c3169e6c9fa70f703382b1779f1/시스템_구성도.png)

# 5. 실행 방법
## backend
### CI/CD  
gitlab(back branch) push or merge -> jenkins webhook -> auto build  

### MobaXterm
![MobaXterm](/uploads/8f06a1193c71f59c3bd7e9f1c0ebbdd9/image.png)

### docker logs
![docker](/uploads/c9c5d41eb149d6a1b81b6ff24bf09bf6/image.png)
```
docker logs
ex ) docker logs --tail 300 salmanhaljido_server_bluedocker logs --tail 300 salmanhaljido_server_blue
```


# 6. 팀원 소개
|김주영|박진경|이기종|이상민|이재영|
|:---:|:---:|:---:|:---:|:---:|
|front(팀장)|front|back|back(팀장)|front|
