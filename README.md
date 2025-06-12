# JWT 인증 시스템 프로젝트

## 📌 프로젝트 개요

Spring Boot 기반으로 제작한 JWT 인증 시스템입니다. 회원가입, 로그인, 사용자 권한 관리 기능을 제공합니다. 인증에 성공하면 JWT 토큰을 발급하며, 이를 통해 인증이 필요한 API에 접근할 수 있습니다.

---

## 🧰 기술 스택

- Spring Boot 3.5.0
- Spring Security
- JWT
- JaCoCo

---

## 📂 주요 기능

- 회원가입 (`/signup`)
- 로그인 (`/login`)
- 관리자 권한 부여 (`/admin/users/{userId}/roles`)

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
- 측정 결과
<img width="914" alt="화면 캡처 2025-06-12 122311" src="https://github.com/user-attachments/assets/f33f4bea-be40-458e-859c-83df7d446ce4" />


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
- **Response**: `200 OK`
  ```json
  {
    "username": "user1",
    "nickname": "닉네임",
    "roles": [
        {
            "role": "USER"
        }
    ]
  }
- **Response(Error)**: `400`
  ```json
  {
    "error": {
        "code": "USER_ALREADY_EXISTS",
        "message": "이미 가입된 사용자입니다."
    }
  }

### 2. 로그인

- **URL**: `/login`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "username": "user1",
    "password": "pass1234"
  }
- **Response**: `200 OK`
  ```json
  {
    "bearerToken": "Bearer ..."
  }
- **Response(Error)**: `401`
  ```json
  {
    "error": {
        "code": "INVALID_CREDENTIALS",
        "message": "아이디 또는 비밀번호가 올바르지 않습니다."
    }
  }

### 3. 관리자 권한 부여

- **URL**: `/admin/users/{userId}/roles`
- **Method**: `PATCH`
- **Response**: `200 OK`
  ```json
  {
    "username": "user1",
    "nickname": "닉네임",
    "roles": [
        {
            "role": "ADMIN"
        }
    ]
  }
- **Response(Error)**: `403`
  ```json
  {
    "error": {
        "code": "ACCESS_DENIED",
        "message": "관리자 권한이 필요한 요청입니다. 접근 권한이 없습니다."
    }
  }

---
