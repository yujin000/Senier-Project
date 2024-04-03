# 🖥We Pos
Pos 사용자(사업자)가 고객의 주문메뉴를 받아 결제하는 시스템

# [📚PDF 보고서 보기](https://docs.google.com/viewer?url=https://github.com/yujin000/Senier-Project/blob/master/%EC%BA%A1%EC%8A%A4%ED%86%A4%EB%94%94%EC%9E%90%EC%9D%B8_%EB%B3%B4%EA%B3%A0%EC%84%9C_%EA%B9%80%EC%B1%84%EC%98%81%2C%EC%8B%A0%EC%9C%A0%EC%A7%84.pdf?raw=T)
# [📄PDF 발표자료 보기](https://drive.google.com/file/d/10QZPkJ0MzffHP4btcJSaFYftKSse3WNa/view?usp=share_link)
# [💬코드리뷰 보기](https://yudangdang.tistory.com/36)
<br>
# 목차

1. [프로젝트 소개](#1-프로젝트-소개)

2. [기획 & 관리](#2-기획--관리)
-------------
# 1. 프로젝트 소개
* 개발기간 : 2021.03.15 ~ 2021.06.13
* 개발인원 : 2명
* 구현기능 : 회원관리(회원가입, 로그인, 비밀번호 찾기) / 메뉴관리(메뉴 변경, 추가, 삭제) / 판매관리 / 
매출관리(당일 & 메뉴별 판매리스트, & 월 일 판매리스트)

시중의 Pos 프로그램은 일반적으로 오래전에 만들어놓은 UI 화면으로 되어있고, Pos의 목적에 해당하는 기능들이 정리되어있지 않은 형태로 되어있어 사용자(사업자)들이 쉽게 이 기능들을 다 사용하지 않는다. 그러므로 사용자들이 처음부터 쉽게 Pos 프로그램을 사용하는 것을 목표

|Freedom|Convenient|Easy|
|:---:|:---:|:---:|
|자유|편리|쉬움|
|자유로운 메뉴 / 재고관리|편리한 기능 사용|쉬운 조작|

### 프로젝트를 수행하면서 얻은 지식과 다시 수행한다면 어떻게 할지를 통한 과정 검토
① 이클립스와 orcle 연동

이클립스와 데이터베이스인 orcle을 연동하며 많은 오류, 오류해결을 경험하면서 프로그램을 만들며 오류에 대한 해결을 어떻게 대응할 지에 대한 지식을 얻음

② 구조 설계

프로그램을 구조적으로 만들지 않아 스파게티 코드가 되어버려서 다시 수행한다면 처음부터 구조설계를 체계적으로 할 것

### 향후 확장 연구
① 네트워크 연결

네트워크를 연결 해 카드결제 기능 추가

② 재고 관리

메뉴 별 재고관리 기능을 추가하여, 결제 시 재고가 없을 경우 결제가 안되고, 재고가 있을 경우, 결제 시 해당 메뉴 재고 수량 감소 / 재고 추가 주문 기능을 만들어서 재고 제공업체와 연동하여 자동 주문 기능

### 당부
구조 설계 : DB 설계, E-R 다이어그램, 프로그램 순서도 등 체계적으로 설계하고 프로그램을 만들 것

### **사용 언어 및 개발환경**
* **Web** : Java, Java Swing
* **DataBase** : Oracle 11
* **Tool** : Eclipse

# 2. 기획 & 관리
* **프로그램 순서도**

① 로그인 화면 순서도

<img src="https://user-images.githubusercontent.com/101914200/220865326-7595a8cd-55e2-43c2-a804-779d7598815e.png" height="30%" width="30%">
② 메뉴&재고관리 화면 순서도

<img src="https://user-images.githubusercontent.com/101914200/220865989-2f2afd72-8bf1-460e-a77d-3cc4600ecfe6.png" height="30%" width="30%">
③ 판매 화면 순서도

<img src="https://user-images.githubusercontent.com/101914200/220866050-96072999-43de-4bf4-9285-acce546b6858.png" height="30%" width="30%">
④ 매출관리 화면 순서도

<img src="https://user-images.githubusercontent.com/101914200/220866077-5da6e47e-2869-4e08-b752-a635917ce256.png" height="40%" width="40%">

* **ERD**
<img src="https://github.com/yujin000/Senier-Project/assets/101914200/cfb2642c-976b-4464-b6a5-c869742c16cb" height="95%" width="95%">

-------------


### **[메인 화면]**
![image](https://user-images.githubusercontent.com/101914200/217021170-b11a24fa-2b55-469d-93ae-debbf530b61a.png)
### **[메뉴 추가 화면]**
![image](https://user-images.githubusercontent.com/101914200/217021465-936f3de8-6968-4692-a758-cc125671e333.png)
![image](https://user-images.githubusercontent.com/101914200/217021604-0b42eb6e-02d1-458e-9f89-ad1ffc3de42b.png)
### **[메뉴 수정 화면]**
![image](https://user-images.githubusercontent.com/101914200/217021788-9d58fe9c-519c-40dc-8846-5c3252504d2e.png)
### **[판매 화면]**
![image](https://user-images.githubusercontent.com/101914200/217022030-7dcf99bd-6d48-4af0-94be-76c529c79992.png)
### **[매출 관리 화면]**
![image](https://user-images.githubusercontent.com/101914200/217022665-5007b661-8cd9-41d5-a6f1-8e4a42d50d0f.png)
