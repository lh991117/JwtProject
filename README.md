# JWT 인증 시스템 프로젝트

## 📌 프로젝트 개요

Spring Boot 기반으로 제작한 JWT 인증 시스템입니다. 회원가입, 로그인, 사용자 권한 관리 기능을 제공합니다. 인증에 성공하면 JWT 토큰을 발급하며, 이를 통해 인증이 필요한 API에 접근할 수 있습니다.

---

## 📂 주요 기능

- 회원가입 (`/signup`)
- 로그인 (`/login`)
- 관리자 권한 부여 (`/admin/grant`) - 테스트 용도
- 사용자 권한 조회 (`/admin/users/{username}`)

---

## 🔐 인증 방식

- 로그인 성공 시 Access Token이 발급됩니다.
- `Authorization: Bearer <token>` 형식의 헤더로 인증이 이루어집니다.

---

## 🧪 테스트 커버리지

- 테스트 커버리지 측정 도구: **JaCoCo**
- IntelliJ에서 시각화된 리포트를 통해 확인 가능
- 주요 테스트 항목:
    - 회원가입 성공/실패
    - 로그인 성공/실패
    - 권한 부여 로직

---

## 🔗 API 명세서

### 1. 회원가입

- **URL**: `/signup`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "username": "user1",
    "password": "pass1234",
    "nickname": "닉네임"
  }