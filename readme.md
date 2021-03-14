# 카카오페이 웹(풀스택) 개발 과제
## 목차
- [개발 환경](#개발-환경)
- [빌드 및 실행하기](#빌드-및-실행하기)
- [기능 요구사항](#기능-요구사항)
- [개발 제약사항](#개발-제약사항)
- [주요기능](#주요기능)

---

## 개발 환경
- 기본 환경
    - IDE: IntelliJ IDEA Ultimate
    - OS: Windows 10
    - GIT
- Back-end
    - Java8
    - Spring Boot 2.2.2
    - JPA
    - H2
    - Gradle
    - Junit5
- Front-end
    - Vuejs
    - Nodejs
    - Webpack
    - Jest


## 빌드 및 실행하기
### 터미널 환경
- Git, Java, Nodejs는 설치 되어있다고 가정한다.

```
$ git clone https://github.com/hsolemio-lee/kakaopay-test-project.git
$ cd solapp
$ npm install 
$ npm run build //UI 번들링
$ cd ../
$ ./gradlew bootjar //jar 빌드
$ java -jar ./build/libs/SolApp-0.0.1-SNAPSHOT.jar //jar 실행
```

- 접속 Base URI: `http://localhost:8080`

## 기능 요구사항
### 필수사항
- 대량 파일(CSV 10만건 이상) 업로드를 통한 Data 저장 기능 구현
  - Drag & Drop 으로 10만건 이상 CSV 파일 업로드.
  - 업로드된 파일을 데이터베이서에 insert하고 진행상태 progress bar로 상태 표시

## 개발 제약사항
### 필수사항
- Spring boot rest api(json) 기반으로 개발
- 유닛 테스트 코드 필수 작성
- RDBMS를 사용하여 개발
- Frontend Framework을 사용하여 개발

### 선택사항
- core ui, dropzone 등의 UI Component 사용은 선택사항
   
## 주요기능
### API Spec
1.[POST] CSV 파일 업로드 저장 
#### URL: 
http://localhost:8080/rest/v1/users/upload
#### Parameter
file (formData)
#### Response Body
```json
{
    "insertedCount": 99563,
    "updatedCount": 0,
    "failedCount": 437,
    "totalCount": 100000
}
```

1.[GET] User 페이징 조회
#### URL: 
http://localhost:8080/rest/v1/users
#### Parameter
size (integer)
page (integer)
#### Response Body
```json
{
    "content": [
        {
            "id": 110,
            "firstName": "Jobi",
            "lastName": "Bartlett",
            "email": "Jobi.Bartlett@yopmail.com"
        },
        {
            "id": 111,
            "firstName": "Delilah",
            "lastName": "Sikorski",
            "email": "Delilah.Sikorski@yopmail.com"
        },
        {
            "id": 112,
            "firstName": "Brooks",
            "lastName": "Poppy",
            "email": "Brooks.Poppy@yopmail.com"
        },
        {
            "id": 113,
            "firstName": "Lory",
            "lastName": "Schwejda",
            "email": "Lory.Schwejda@yopmail.com"
        },
        {
            "id": 114,
            "firstName": "Blake",
            "lastName": "Alwin",
            "email": "Blake.Alwin@yopmail.com"
        },
        {
            "id": 115,
            "firstName": "Ericka",
            "lastName": "Blake",
            "email": "Ericka.Blake@yopmail.com"
        },
        {
            "id": 116,
            "firstName": "Florie",
            "lastName": "Linehan",
            "email": "Florie.Linehan@yopmail.com"
        },
        {
            "id": 117,
            "firstName": "Michaelina",
            "lastName": "Llovera",
            "email": "Michaelina.Llovera@yopmail.com"
        },
        {
            "id": 118,
            "firstName": "Fred",
            "lastName": "Pauly",
            "email": "Fred.Pauly@yopmail.com"
        },
        {
            "id": 119,
            "firstName": "Corry",
            "lastName": "Holbrook",
            "email": "Corry.Holbrook@yopmail.com"
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "offset": 10,
        "pageNumber": 1,
        "pageSize": 10,
        "unpaged": false,
        "paged": true
    },
    "totalElements": 99563,
    "last": false,
    "totalPages": 9957,
    "number": 1,
    "size": 10,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "numberOfElements": 10,
    "first": false,
    "empty": false
}
```



