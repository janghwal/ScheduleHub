# ScheduleHub
## API
|기능|Method| URL                      | request                                                                                                 |response|상태 코드|
|:------|:---|:-------------------------|:--------------------------------------------------------------------------------------------------------|:---|:---|
|회원 가입|POST|/user| 요청 body<br>{<br>“userName” : “이름”<br>“email” : “이메일”<br>“password” : “비밀번호”<br>}       |등록 정보 {<br>    "userId": 식별자,<br>    "userName": "이름",<br>    "email": "이메일"<br>}|201: CREATE|
|모든 유저 조회|GET|/user|-| 유저 다건 조회 |200: OK|
|유저 조회(식별자)|GET| /user/find/{userId}|{userId}|{<br>"userId": User 식별자,<br>"userName": "이름",<br>"email": "이메일"<br>} |200: OK|
|유저 조회(이메일)|GET| /user/find|요청 param <br> email = | {<br>"userId": User 식별자,<br>"userName": "이름",<br>"email": "이메일"<br>} |200: OK|
|유저 수정|PATCH| /user | JSESSIONID<br>요청 body<br>{<br>“userName” : “이름”<br>“email” : “이메일”<br>“password” : “비밀번호”<br>} |{<br>"userId": User 식별자,<br>"userName": "이름",<br>"email": "이메일"<br>}|200: OK|
|회원 탈퇴|DELETE| /user | JSESSIONID |-|200: OK|
|일정 생성|POST| /schedule | JSESSIONID<br>요청 body<br>{<br>“title” : “제목”<br>“contents” : “내용”<br>} |{<br>"userName":"이름",<br>"title": "제목",<br>"contents": "내용",<br>"id": schedule 식별자 <br>}|201: CREATE|
|모든 일정 조회|GET| /schedule | - |일정 다건 조회|200: OK|
|일정 조회|GET| /schedule/{scheduleId} | {scheduleId} |{<br>"userName":"이름",<br>"title": "제목",<br>"contents": "내용",<br>"id": schedule 식별자 <br>}|200: OK|
|일정 수정|PATCH| /schedule/{scheduleId} | {scheduleId} <br> JSESSIONID <br> 요청 body<br>{<br>“title” : “제목”<br>“contents” : “내용”<br>} |{<br>"userName":"이름",<br>"title": "제목",<br>"contents": "내용",<br>"id": schedule 식별자 <br>}|200: OK|
|일정 삭제|DELETE| /schedule/{scheduleId} | {scheduleId} <br> JSESSIONID |-|200: OK|
|로그인|POST| /login | 요청 body<br>{<br>“email” : “이메일”<br>“password” : “비밀번호”<br>} |-|200: OK|
|로그 아웃|POST| /login/logout | JSESSIONID |-|200: OK|

