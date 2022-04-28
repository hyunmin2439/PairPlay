## 김현민- 아이디어 추가

### 소모임 카카오 오픈 채팅방으로 바로 연계 불가

1. 카카오 Rest api로 오픈 채팅방 개설 불가
   
2. KakaoTalk 오픈채팅 정보 검색
- 이 API는 카카오톡 오픈채팅방 정보 검색을 할수 있는 API입니다
- [https://docs.develope.kr/ko/apis/room_info.html](https://docs.develope.kr/ko/apis/room_info.html)

1. **결제 시스템 구현**
- [https://www.iamport.kr/](https://www.iamport.kr/)
- 아임 포트
    - PG(Payment gateway)
    - 온라인 쇼핑몰의 경우,***PG사***와의 계약을 통해 전자결제서비스를 구축해야만 **거래**가 가능하다. **PG사**는 신용카드사와 직접 계약하기 어려운 온라인 쇼핑몰을 대신해 결제와 정산 업무를 대행해 주는 업체를 뜻한다.**PG사**와 계약을 하면, *카드결제, 휴대폰 결제, 계좌이체, 무통장입금 등* 다양한 결제 수단을 쇼핑몰 방문 고객에게 제공할 수 있다.
    - 국내 PG결제 연동을 쉽게해주는 결제 API서비스로 PG사 결제모듈에 대한 연동 개발을 진행할 때, 다양한 개발환경에서 보다 쉽고 빠르게 개발할 수 있게 도와준다.
- 테스트 모드로 하면 사업자 등록 안 해도 가능
- [https://1-7171771.tistory.com/83](https://1-7171771.tistory.com/83)
- [https://velog.io/@poseassome/개인프로젝트-아임포트import-결제-기능-구현하기](https://velog.io/@poseassome/%EA%B0%9C%EC%9D%B8%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%95%84%EC%9E%84%ED%8F%AC%ED%8A%B8import-%EA%B2%B0%EC%A0%9C-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0)
- [https://smujihoon.tistory.com/103](https://smujihoon.tistory.com/103)
- 결제 시스템 구조 이해
    - [https://skyseven73.tistory.com/17](https://skyseven73.tistory.com/17)
    - [https://zarawebstudy.tistory.com/11](https://zarawebstudy.tistory.com/11)
- 카카오 페이보다 훨씬 더 있어 보일 듯


## 1.운동 달력/잔디 뱃지 기능 논의

1. `**뱃지를 전부 다 빼버림.**`
2. 뱃지를 따로 모아서 볼 수 있게 만드는 것도 하나의 의견
3. 깃허브처럼 잔디 누르면 하단에 자세한 정보 떳으면 좋겠다.
4. 잔디 1년치 VS 잔디 달별 ~ 2달
5. 월별 + 잔디 통합 프론트에서 해보고 결정

### Activity 테이블

1. member - activity(메이트, 날짜) - mate
2. member - activity(팀운동, 날짜) - team
3. activity 테이블로 통일( team / mate )

JWT에 MemberId를 담는 것으로 확정

- MemberId

체육시설 -> 리뷰 -> 리뷰 댓글 필요?
Quartz 라이브러리 -> 트리거
memberId / voteId / flag
1 / 0

### 정우영/김문희 코치님 조언
- 팀 기능 (소모임) 제외
- 메이트 조금 더 우선 시 하기


지도 크롤링

## 투표기능

### 1. 어려움

- 날짜 시간 : 투표 종료 -> 참가하는 사람 목록 -> 팀 메이트 맺어줌
- Quartz 라이브러리 -> 트리거 ( 어려운 기술, 안해본 기술 )
- 프론트 -> 백엔드 ( X )

### 2. 조금 복잡하지만 간단
- 투표 동의 -> 팀 메이트 맺어짐
- 투표 비동 -> 팀 메이트 끊어짐
- 투표 API ->  날짜 비교해서()
- 1번 + 2번 통합해서 하는 건 어떤가

### 3. 가장 간단하지만 볼품 없음
- 투표 수동 종료 -> 동의한 사람들 팀 메이트 맺어짐