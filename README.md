# 🔙 프로젝트 개요 🌈
여행 준비 과정에서 발생하는 의사결정의 어려움과 이를 해결하기 위한 양방향 소통 플랫폼 **"링크"**🔗

---

## 주요 기능 ✨

1. **카테고리별 분류 및 리스트 기능**  
   - 여행 정보를 카테고리(숙소, 교통, 장소 등)별로 정리하고 핵심 정보(URL, 이름, 추가 사항 등)를 입력할 수 있는 기능을 제공합니다.  
   - 사용자들이 분산된 정보를 한 플랫폼에서 통합적으로 관리할 수 있습니다.

2. **커뮤니케이션 기능**  
   - 댓글, 선호, 비선호, 링크(이해했어요) 기능을 통해 참여자들이 의견을 비동기적으로 교환할 수 있습니다.  
   - 주최자는 참여자들의 의견을 시각적으로 쉽게 확인할 수 있습니다.

3. **우선순위 기능**  
   - 선호 수에 따라 정보의 순서가 자동으로 조정되며, 선호와 비선호 수를 종합적으로 고려하여 순위를 계산합니다.  
   - 이를 통해 효율적인 의사결정이 가능해집니다.

---

## 🛠️ 백엔드 기술 스택

- **프레임워크**: Spring Boot  
- **데이터베이스 관리**: JPA (Hibernate)  
- **API 설계**: RESTful 방식  
- **인증 및 보안**: JWT(JSON Web Token) 기반 인증  
- **파일 관리**: AWS S3를 사용한 파일 업로드 및 저장  
- **서버 배포**: AWS EC2를 사용한 애플리케이션 배포  
- **문서화 도구**: Swagger(OpenAPI 3.0)  

<p align="center">
  <img src="https://img.shields.io/badge/AWS-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white" />
  <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" />
  <img src="https://img.shields.io/badge/JPA-59666C?style=for-the-badge&logo=hibernate&logoColor=white" />
  <img src="https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white" />
</p>

---

## 🔑 주요 백엔드 기능 구현

1. **RESTful API 설계**  
   - 클라이언트와 서버 간 통신을 위한 직관적이고 일관된 REST API를 설계.  
   - HTTP 메서드(GET, POST, PUT, DELETE)를 활용하여 리소스 기반의 API 구현.

2. **JWT 인증 및 보안**  
   - 사용자 로그인 시 JWT를 발급하고 클라이언트는 이후 요청 시 토큰을 이용해 인증 수행.  
   - JWT에는 사용자 정보와 만료시간이 포함되어 있어 효율적인 인증 및 권한 관리 가능.  

3. **파일 업로드 및 관리**  
   - AWS S3를 이용하여 파일(이미지, 문서 등)을 안전하게 저장하고 URL을 반환.  
   - 클라이언트는 해당 URL을 통해 파일에 접근 가능.  

4. **데이터베이스 설계 및 관리**  
   - JPA와 Hibernate를 활용하여 데이터베이스와 객체 간의 매핑을 처리.  
   - 관계형 데이터베이스(MySQL) 사용, 필요한 경우 캐싱 도구(Redis 등)를 적용하여 성능 최적화.

5. **의견 및 우선순위 기능 구현**  
   - 선호/비선호 데이터를 실시간으로 집계하여 순위 계산 로직 구현.  
   - 주최자가 참여자의 의견을 한눈에 볼 수 있도록 데이터를 가공 및 제공.  

---

## ⚙️ 설치 및 실행 방법

### **1. 프로젝트 클론**
```sh
git clone https://github.com/your-repo/link-backend.git
cd link-backend
```

### **2. 환경 변수 설정**
- `application.yml` 또는 `.env` 파일을 작성하여 아래 값을 설정:
```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:mysql://<DB_HOST>:<DB_PORT>/<DB_NAME>
    username: <DB_USERNAME>
    password: <DB_PASSWORD>
  jpa:
    hibernate:
      ddl-auto: update

aws:
  s3:
    bucket-name: <BUCKET_NAME>
    region: <REGION>
  access-key: <AWS_ACCESS_KEY>
  secret-key: <AWS_SECRET_KEY>
jwt:
  secret: <JWT_SECRET>
```

### **3. 종속성 설치 및 빌드**
```sh
./gradlew build
```

### **4. 실행**
```sh
java -jar build/libs/link-backend-0.0.1-SNAPSHOT.jar
```

---

## 📚 API 명세

| 메서드 | 엔드포인트               | 설명                     |
|--------|--------------------------|--------------------------|
| POST   | `/auth/register`         | 사용자 회원가입          |
| POST   | `/auth/login`            | 사용자 로그인 및 JWT 발급 |
| GET    | `/categories`            | 카테고리별 정보 조회     |
| POST   | `/categories/{id}/items` | 카테고리에 아이템 추가   |
| GET    | `/items/{id}`            | 특정 아이템 상세 조회    |
| PUT    | `/items/{id}`            | 아이템 정보 수정         |
| DELETE | `/items/{id}`            | 아이템 삭제              |

---

## 배포 정보 🌐

- **배포 환경**: AWS EC2
- **빌드 도구**: Gradle
- **도커 컨테이너**: (필요 시 Dockerfile 추가)  

---

## 개발자 소개 🧑‍💻
| 이름  | 구현 기능 |
|-------|-----------|
| **김하진 🐿️** | JWT 인증, API 설계, 데이터베이스 연동 |
| **이수인 🐾** | 파일 업로드 기능, AWS S3 연동, 우선순위 로직 |

---
