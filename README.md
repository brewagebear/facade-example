## 만든 배경 

누군가, 멀티 모듈을 사용할 때 멤버-서비스와 로그인-서비스간의 의존관계 및 해결방법을 물어봐서 이에 작성함.

## 주의사항 

귀찮아서 로그인-서비스에 아래와 같은 의존관계를 넣어뒀는데

```kt
dependencies {
    implementation(project(":member"))
    implementation("org.springframework.boot:spring-boot-starter-security")
}
```

이 경우에는 모듈간의 디비 결합이 발생할 수 있으니 모듈별 `export`할 클래스만 따로 빼서 처리할 수 있게끔 설계해야됩니다.


## 사용법 

`SecurityRequest.http` 를 돌리면 됩니다.

### 1. 회원가입
POST http://localhost:8080/api/v1/signup
Content-Type: application/json

{
  "name" : "hello",
  "email" : "test@gmail.com",
  "password": "1234"
}

### 2. 로그인
POST http://localhost:8080/api/v1/login
Content-Type: application/json

{
  "email" : "test@gmail.com",
  "password": "1234"
}
