## 만든 배경 

누군가, MSA 구조로 설계를 진행할 경우 멤버-서비스와 로그인-서비스간의 의존관계 및 해결방법을 물어봐서 이에 작성함.

## 주의사항 

귀찮아서 로그인-서비스에 아래와 같은 의존관계를 넣어뒀는데

```kt
dependencies {
    implementation(project(":member"))
    implementation("org.springframework.boot:spring-boot-starter-security")
}
```

이 경우에는 모듈간의 의존성 결합도 높아질 수 있으니(`member` 서비스에 대한 의존성을 다 땡겨옴)이 발생할 수 있으니 모듈별 `export`할 클래스만 따로 빼서 처리할 수 있게끔 설계해야됩니다.
자세한건 https://laughcryrepeat.tistory.com/45 참고하시기 바랍니다.

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

### 3. 로그인(패스워드 불일치)
POST http://localhost:8080/api/v1/login
Content-Type: application/json

{
  "email" : "test@gmail.com",
  "password": "12345"
}

불일치의 경우에는 따로 DTO을 안만들어놔서 아래와 같이 익셉션 터지는 것으로 확인가능합니다.
<img width="1662" alt="Screenshot 2023-08-31 at 00 32 31" src="https://github.com/brewagebear/facade-example/assets/22961251/0288625f-2f5a-48da-beeb-ca01ace58728">


별도의 서버로 띄워져있고, 저런식으로 모듈 컴파일해서 사용하지 않는다면 `restTemplate` 든 `webClient` 든으로 작업하시면 됩니다.
그렇게되면 아래의 작업이 변경되겠죠? (`memberService` 에 대한 의존성이 사라짐)

```java
private void validationPassword(MemberCommand.LoginMemberRequest request) {
        MemberInfo.secret secret = memberService.retrieveMemberSecretInfo(request.email(), request.password());

        if(!passwordEncoder.matches(request.password(), secret.password())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
    }
```

여기서 `memberService.retrieveMemberSecretInfo(request.email(), request.password());` 부분만 외부 서버를 통해서 통신하게끔 처리하면됩니다.
