# 카카오페이 웹(풀스택) 개발 과제
## 목차
- [개발 환경](#개발-환경)
- [빌드 및 실행하기](#빌드-및-실행하기)
- [기능 요구사항](#기능-요구사항)
- [개발 제약사항](#개발-제약사항)
- [해결방법](#해결방법)

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
   
## 해결방법
### 1. 데이터 파일(`.csv`)에서 각 레코드를 데이터베이스에 저장하는 API 개발



