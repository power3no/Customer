# Customer
Java Swing GUI를 사용하여 기본적인 회원관리 시스템 구현
------
## 구현 기능
> ### 로그인
>> ip와 Username, Password 중 하나라도 입력되지 않으면 입력하라는 메세지 다이얼로그 창이 출력되고 창을 닫을 시 해당 텍스트 필드로 포커스
>> 로그인 시 각 텍스트 필드의 입력기능을 막고 로그인 버튼이 로그아웃 버튼으로 변경
>> 로그아웃 버튼 클릭 시 Username과 Password가 입력이 다시 가능하게 하고 입력값 모두 초기화
>> 로그인 시에만 회원추가, 회원목록, 회원삭제에 접근 가능
> ### 회원추가
>> 좌측에 있는 회원정보(이름, 아이디, 패스워드, 주민번호)를 입력하고 하단의 회원추가 버튼을 누르면 해당하는 회원정보를 MYSQL 데이터베이스에 저장
>> 회원 번호는 자동으로 지정되므로 회원번호의 입력칸은 입력할수 없도록 구현
> ### 회원목록
>> 하단의 회원목록 버튼 클릭시 MYSQL 데이터베이스를 조회하여 화면에 모든 회원의 정보를 출력
>> 출력되는 회원정보를 클릭시 해당 행이 선택되며 좌측 회원정보 입력창에 해당 행에 해당하는 회원 정보를 출력
> ### 회원삭제
> 하단의 회원삭제 버튼 클릭시 새로운 창이 뜨며 회원번호를 입력하고 확인버튼을 누르면 해당하는 회원정보를 삭제
> 입력값이 숫자가 아닐 때 "번호만 입력 가능!" 이라는 메세지 다이얼로그 출력
> 만약 회원삭제 실패시 다시 같은 창이 열리도록 구현
