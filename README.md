<div align="center">
  <img src="https://github.com/user-attachments/assets/61f560fc-edeb-4b33-9f80-053653ffe892" alt="프로젝트 이미지" width="300"/>
</div>


# 🔙 프로젝트 개요 🌈
여행 준비 과정에서 발생하는 의사결정의 어려움과 이를 해결하기 위한 양방향 소통 플랫폼 **"링크"** 🔗

---

## 주요 기능 ✨

1. **카테고리별 리스트 관리 기능**  
   - 여행 정보를 카테고리(숙소, 교통, 장소 등)별로 정리하고 핵심 정보(URL, 이름, 추가 사항 등)를 입력할 수 있는 기능을 제공합니다.  
   - 사용자들이 분산된 정보를 한 플랫폼에서 통합적으로 관리하고, 필요 시 쉽게 확인할 수 있습니다.  

2. **커뮤니케이션 기능**  
   - 댓글, 선호(좋아요), 비선호(싫어요), 링크(이해했어요) 기능을 통해 참여자들이 의견을 비동기적으로 교환할 수 있습니다.  
   - 좋아요와 싫어요는 동시에 선택할 수 없으며, 각자의 선호도를 시각적으로 확인 가능합니다.  
   - 주최자는 참여자들의 의견을 한눈에 확인할 수 있어 의사결정 과정이 간소화됩니다.  

3. **우선순위 갱신 기능**  
   - 사용자들의 선호 수에 따라 정보의 순서가 자동으로 조정되며, 선호와 비선호 수를 종합적으로 고려하여 순위가 계산됩니다.  
   - 이를 통해 가장 많은 지지를 받는 정보를 쉽게 확인할 수 있으며, 더 나은 의사결정을 지원합니다.  

4. **자동 정보 생성 기능**  
   - 링크 첨부 및 메모 입력 시, 관련된 사진과 이름이 자동으로 생성됩니다.  
   - 사용자는 간단한 입력만으로 시각적으로 정리된 정보를 확인할 수 있어 편리합니다.  

---

## 🛠️ 백엔드 기술 스택

- **프레임워크**: Spring Boot  
- **데이터베이스 관리**: JPA (Hibernate)  
- **API 설계**: RESTful 방식  
- **파일 관리**: AWS S3를 사용한 파일 업로드 및 저장  
- **서버 배포**: AWS EC2를 사용한 애플리케이션 배포  
- **문서화 도구**: Swagger(OpenAPI 1.0)  

<p align="center">
  <img src="https://img.shields.io/badge/AWS-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white" />
  <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" />
  <img src="https://img.shields.io/badge/JPA-59666C?style=for-the-badge&logo=hibernate&logoColor=white" />
</p>

---

## 🔑 주요 백엔드 기능 구현

1. **RESTful API 설계**  
   - 클라이언트와 서버 간 통신을 위한 직관적이고 일관된 REST API를 설계.  
   - HTTP 메서드(GET, POST, PUT, DELETE)를 활용하여 리소스 기반의 API 구현.

2. **파일 업로드 및 관리**  
   - AWS S3를 이용하여 파일(이미지, 문서 등)을 안전하게 저장하고 URL을 반환.  
   - 클라이언트는 해당 URL을 통해 파일에 접근 가능.  

3. **데이터베이스 설계 및 관리**  
   - JPA와 Hibernate를 활용하여 데이터베이스와 객체 간의 매핑을 처리.  
   - 관계형 데이터베이스(MySQL) 사용.

4. **의견 및 우선순위 기능 구현**  
   - 선호/비선호 데이터를 실시간으로 집계하여 순위 계산 로직 구현.  
   - 주최자가 참여자의 의견을 한눈에 볼 수 있도록 데이터를 가공 및 제공.  

5. **자동 정보 생성 기능**  
   - 사용자가 입력한 URL을 분석하여, 제목과 이미지를 자동으로 생성.  
   - 효율적으로 정보를 수집하고 정리할 수 있도록 지원.  

---

## 📚 API 명세
[API 명세서](<https://noisy-sunscreen-6be.notion.site/Fromis7_API-165464b1b207805d9600de8d4b7e03ac?pvs=4>)


## ERD
<img width="300" alt="스크린샷 2025-01-02 오전 4 39 52" src="https://github.com/user-attachments/assets/3cd5c972-208d-4811-931c-7689adc0501d" />

---

## 배포 정보 🌐

- **배포 환경**: AWS EC2
- **빌드 도구**: Gradle

---

## 개발자 소개 🧑‍💻

### **백엔드**
| 이름  | 구현 기능 |
|-------|-----------|
| **김하진 🐿️** | - API 설계 및 데이터베이스 연동<br> - 로그인 및 JWT 인증<br> - RESTful API 최적화  |
| **이수인 🐾** | - 우선순위 계산 로직 구현<br> - 댓글 및 선호/비선호 기능<br> - AWS S3 연동 및 파일 업로드 기능 |

---
