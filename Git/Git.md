# Git

**분산 버전 관리 시스템**

- 코드의 히스토리(버전)을 관리하는 도구
- 개발되어온 과정 파악 가능
- 이전 버전과의 변경 사항 비교 및 분석

- **변경사항**만을 저장 -> 속도가 빠름
- Git과 GitHub는 같지 않다.
- **GitHub**는 Git기반의 저장소 서비스
- Git은 **명령어**를 통해서 사용(Command Line Interface)
- Teminal -> git bash

**간단한 Unix/Linux 명령어**

- cd com + tap : 자동완성

- ls : 현재 위치의 폴더, 파일 목록 보기

- cd <path> : 현재 위치 이동하기

- cd .. : 상위 폴더로 이동

- mkdir<name> : 폴더 생성하기

- touch<name> : 파일 생성하기

- rm <name> : 파일 삭제하기

- rm -r <name> : 폴더 삭제하기

- clear 

- C:\Users\김태은 = ~(틸다)  : 사용자의 홈

- cd ~ : 홈 디렉토리로 가기

  

**Git 기본기**

김태은@LAPTOP-027J7LR2 MINGW64 ~/Desktop/command
$ cd ../RacingGround/ : 상대경로

- command->RacingGround로 이동

김태은@LAPTOP-027J7LR2 MINGW64 ~/Desktop/command/folder
$ cd ../../RacingGround/

- 상위 폴더로 두번 이동

**Racing car**

- README.md : 소개문서, 설명서를 나타냄

- Repository : 특정 디렉토리를 버전 관리하는 저장소

  -> git init 명령어로 로컬 저장소를 생성한다.

- 숨김처리 되어있음 : 보기->숨김항목 체크

- .git 디렉토리에 버전관리에 필요한 모든 것이 들어있음

- **특정 버전**으로 남긴다 = "**커밋(Commit)**한다."

- 커밋 : 3가지 영역을 바탕으로 동작한다.

  1.  Working Directory : 내가 작업하고 있는 실제 디렉토리(.git이 있는 디렉토리)

     ​									ex) Racing Car 폴더

  2.  Staging Area : 커밋(Commit)으로 남기고 싶은, 특정 버전으로 관리하고 싶은 파일이 있는 곳

  3.  Repository : 커밋(Commit)들이 저장되는 곳

**git init**

- a : untracked (버전 관리 x) -> git add -> b : staged -> git commit -> c : committed(1주차)

- ->a : modified ( tracked) -> git add -> b : staged-> git commit -> c: committed(2주차)

- git add .  : 추적되지 않은 모든 파일과 추적하고 있는 파일 중 수정된 파일을 모두 Staging Area에 올림

- git config --global user.email "rlaxodmscjsw@naver.com"

- git config --global user.name "taeeun2"

- git commit -m(message약자) "init files(메세지 입력)"

- git commit 완료 : git status->nothing to commit, working tree clean

- git log : 지금까지 commit한것에 대한 history 보여줌

- git status : git 상태를 보여줌

- git diff ( 커밋 아이디: A) (커밋 아이디 : B) : 두 커밋 간 차이 보기 (앞 4자리만 입력)

  ex) git diff 323c 3ed8 (예전 커밋)(최신 커밋)

  --- a/README.md
  +++ b/README.md
  @@ -0,0 +1,2 @@
  +# RacingGround  ( ++)
  +---

  - A를 기준으로 B가 어떻게 바뀌었는지 알려준다.

    

  

**정리**

1. git init

2. git add . 또는 파일 이름

3. git commit -> who you are?

4. git config --global user.email "rlaxodmscjsw@naver.com"

5. git config --global user.name "taeeun2" (global로 해줬기 때문에 한번만 설정하면 됨)

6. git commit -m "버전 이름"

7.  수정 -> 저장(ctr1 +S ) -> git add .

8. git commit 반복

   

git bash 에서 code . -> 바로 vs쓸 수 있음

git commmit 만 입력시 : esc 누르기+ : + q 



# Github

## Github Repository 생성

1. new Repository -> 이름 입력

2. public 과 private 중 선택

3. Local Repository와 연결

4. git init

5. git remote add origin https://github.com/taeeun2/remote_repo.git

6. git push -u origin master ( 최초 push 시 -u 작성)

7. 로그인

8. git hub로 가서 새로고침

9. Repository 지우기 :

   settings -> 제일 밑으로 가서 delete this Repository 클릭

10. 수정 -> git push 

* origin : 원격 레포지토리(git hub에 저장되어 있음)

## clone하는 방법

1. git clone https://github.com/taeeun2/clone_repo.git
2. cd clone_repo/
3. 파일들 commit
4. git push 

해당 파일을 working page로 : git clone https://github.com/taeeun2/taeeun2.git .









