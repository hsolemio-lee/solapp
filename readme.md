# 서버 기동 매뉴얼
### UI 번들링
/solapp 경로에서 아래 커맨드 실행
```sh
$ npm run build
```

### jar 생성
최상위 경로에서 아래 커맨드 실행
```sh
$ gradle bootjar
```

### 생성된 jar 실행
jar 파일 경로에서 아래 커맨드 실행
```sh
$ java -jar SolApp-0.0.1-SNAPSHOT.jar
```

