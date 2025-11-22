# 🎓 LXP Project — 온라인 강좌 서비스

## 📘 개요
Java, Spring Boot, JPA, MySQL 기반의 온라인 학습 플랫폼.  
강좌, 섹션, 강의 구조로 학습 콘텐츠를 관리.  
사용자는 강좌를 수강하고, 관리자는 강좌를 개설.  
파일 업로드 및 썸네일 관리 기능 포함.  
객체지향적 설계를 기반으로, Spring MVC를 포함한 
계층형 아키텍처(Layered Architecture)를 적용하여 관심사 분리.
---

## ⚙️ 기술 스택

| 구분 | 사용 기술 | 설명 |
|------|------------|------|
| 🧠 **Language** | Java 17 | 객체지향적 구조와 안정성을 기반으로 한 주력 언어 |
| 🧩 **Framework** | Spring Boot 3.x | 애플리케이션 전반의 구조 및 의존성 관리 |
| 💾 **Database** | MySQL 8.x | 주요 비즈니스 데이터 저장 및 관리 |
| 🗃️ **ORM** | Spring Data JPA (Hibernate) | 객체지향적 데이터 매핑 및 트랜잭션 처리 |
| 🎨 **View / Template** | Thymeleaf | 서버 사이드 템플릿 기반 뷰 렌더링 |
| ☁️ **Storage** | Google Cloud Storage (GCS) | 이미지 및 파일 업로드 관리 |

---

## 🧩 주요 모듈

| 모듈 | 역할 |
|------|------|
| 👤 **user** | 사용자 및 권한 관리 |
| 💳 **payment** | 결제 및 포인트 관리 |
| 🗂️ **category** | 강좌 주제 및 분류 관리 |
| 🎓 **learning** | 강좌(Course), 섹션(Section), 강의(Lecture) 관리 |
| 📝 **enrollment** | 수강 등록, 학습 진도 및 수료 상태 관리 |


---

## 🎯 목표
Java와 Spring Boot 아키텍처 이해.  
JPA 기반 도메인 모델 설계 학습.  
객체지향적 코드 구조 설계 연습.  
관심사 분리를 통한 유지보수성 강화.  
Layered Architecture 기반의 MVC 설계 패턴 실습.

---

## 🔗 Reference
- 🧭 **Notion:** [프로젝트 문서 페이지](https://www.notion.so/ohgiraffers/F5-2a1649136c11801fadb0f99b62f5ebb5?source=copy_link)
- 🎨 **Figma:** [Service Design & Modeling](https://www.figma.com/board/pfid0izluDLfEUyBsMHLNl/FigJam-basics?node-id=2862-39&t=99R4zqsKHKCEVvIR-1)

## Refactoring
-🪏포크 출처: https://github.com/F4andJandi/growthmate 