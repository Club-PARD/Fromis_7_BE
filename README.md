# 🔙 프로젝트 개요 🚀
/여행 준비 과정에서 발생하는 의사결정의 어려움과 이를 해결하기 위한 양방향 소통 플랫폼 "링크"🔗

## 대표기능
1. 카테고리별 분류 및 리스트 기능: 여행 정보를 카테고리(숙소, 교통, 장소 등)별로 정리하고, 핵심 정보(URL, 이름, 추가 사항 등)를 입력할 수 있는 기능입니다. 이를 통해 사용자들이 분산된 정보를 한 플랫폼에서 통합적으로 관리할 수 있습니다.

2. 커뮤니케이션 기능: 댓글, 선호, 비선호, 링크(이해했어요) 기능을 통해 참여자들이 의견을 비동기적으로 교환하고, 선호도를 시각적으로 확인할 수 있습니다. 주최자는 참여자들의 의견을 쉽게 확인할 수 있습니다.

3. 우선순위 기능: 선호 수에 따라 정보의 순서가 자동으로 조정되며, 선호와 비선호 수를 종합적으로 고려하여 순위를 계산합니다. 이를 통해 효율적인 의사결정이 가능해집니다.

이 프로젝트는 Intelij를 사용해서 spring boot 프레임워크를 사용해서 개발되었다.

## 개발자 소개 & 개인별 구현 기능 
김하진 🐿️
이수인 🐾

# 프로젝트 설치 및 실행

## IDE Setup

[VSCode](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar) (and disable Vetur) + [TypeScript Vue Plugin (Volar)](https://marketplace.visualstudio.com/items?itemName=Vue.vscode-typescript-vue-plugin).

## Customize configuration

See [Vite Configuration Reference](https://vitejs.dev/config/).

## Project directory
```sh
cd shopping_admin_front
```

## Project Setup

```sh
npm install
```

### Compile and Hot-Reload for Development

```sh
npm run dev
```

### Compile and Minify for Production

```sh
npm run build
```

### Lint with [ESLint](https://eslint.org/)

```sh
npm run lint
```

# 📚 기술 스택

* 프레임워크: Vue.js 3.x
* 라우팅: Vue Router
* 요청 라이브러리: Axios
* UI 라이브러리: Vuetify

# 프로젝트 구조

```plaintext
src
├── assets          # 이미지 
├── components      # 재사용 가능한 UI 컴포넌트
├── controllers     # 뷰와 서비스 사이의 로직을 관리하는 컨트롤러
├── services        # API 호출 및 데이터 처리를 담당하는 서비스
├── models          # 데이터 모델 정의
├── views           # 각 페이지별 Vue 컴포넌트
├── router          # Vue Router 설정 파일
└── App.vue         # 최상위 Vue 컴포넌트 파일
```

# Route

### 관리자 웹 페이지
* / :  상품관리
* /product/:productId :  상품상세
* /itemcreate :  상품 추가
* /ordermanage :  주문 관리
* /order/:orderId :   주문상세

### 고객 모바일 페이지
* /productview/:productId : 제품상세
* /purchaseview/:productId :  구매정보 (결제통화, 결제방법, 요청사항 입력)
* /purchaseaddress/:productId :  배송지 주소 입력
* /orderlist :  주문목록
* /orderdetail :  주문목록 상세
* /productlist :  제품목록
* /login : 로그인
* /phoneverification :  회원가입
* /shoppingcart :  장바구니
* /shippingdetail :  배송상세
* /mypage :  마이페이지
* /squre/:productId : 카드결제
* /ordercomplete/productId : 결제완료



