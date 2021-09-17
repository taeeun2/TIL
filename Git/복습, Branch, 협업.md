# 									복습

# 제목, 소제목

- 리스트1
- 리스트2

1. 숫자1
2. 숫자2

```java
System.out.println("abc");
```

출력 : `System.out.println("abc")`

링크 : [링크](https://github.com/taeeun2)

이미지 : ![이미지](C:/Users/%EA%B9%80%ED%83%9C%EC%9D%80/Desktop/TIL/Mark_Down/markdown.assets/cat.jpg)

텍스트 강조1 :  **굵은 글씨** __굵은 글씨__

텍스트 강조2 : *기울임체*

텍스트 강조3 : ~~취소선~~

수평선 ---, ___, ***

---

***

***

> 인용 
>
> > 인용의 인용



| 원소1 | 원소2 |
| ----- | ----- |
|       |       |



표삽입 : CTRL + T

## UNIX/Linux 명령어

ls : 현재 디렉토리 파일에 대한 리스트

cd : change directory / cd .. 상위폴더 / cd . 자기 자신

mkdir : 폴더 생성

touch : 파일 생성

rm : 파일 지우기 , 폴더를 지울 때는 rm -r

C:\Users\김태은 = ~(틸다)  : 사용자의 홈

cd ~ : 홈 디렉토리로 가기

## Git 기본 명령어

git init : 로컬 저장소 생성 -> master 표시

git status : 현재 git의 상태

git add . : staging area로 모든 파일을 올림

git commit -m "~" : commit하기 / commit 메세지는 최대한 자세하게

git log : 지금까지 남긴 commit 확인

## Git Remote 명령어

방법 1

1. git init

2. git remote add origin 레포지터리 주소

3. git push -u(최초 push할 때) origin master

방법 2

1. 빈 폴더

2. git clone 레포지터리 주소 (git init x)

   2-1.git clone              ' '                    .  : 폴더가 따로 안생김

3.  git push

git remote add origin https://github.com/taeeun2/taeeun2.github.io.git

: origin 지우기

바탕화면에 git clone하면 파일이 생긴다.

## Github Pages

- 나만의 웹사이트 만들기 

- 무료 웹 호스팅 서비스 (Github 서버 중 일부분 제공)
- Github 레포지터리랑 직접적으로 연결
- 개인 블로그 or 회사 기술 블로그 or 공식 문서 or 프로젝트 홈페이지 
- [부트스트랩 주소]([Free Bootstrap Themes, Templates, Snippets, and Guides - Start Bootstrap](https://startbootstrap.com/))
- settings -> pages 가서 수정(master로 변환)



## 새로운 Git Remote 명령어

- git **pull** origin master : github : 레포지토리에 저장된 내용 가져오기

- git restore --staged {file} : add 취소하기

- git restore {file} : Working Directory 변경사항 취소하기

  -> 가장 최신 commit의 상태로 만들어줌

- git reset --(soft, mixed, hard) {commit_id} : commit 되돌리기

   -> git pull origin master : 다시 최신 commit으로 돌아옴

  -> hard : 모두 해당 commit의 상태로 되돌림

  -> mixed : sa, rp만 해당 commit의 상태로 되돌림

  -> soft : rp만 해당 commit의 상태로 되돌림

- git rebase



## .gitignore :  원하지 않는 파일 제외하기

- .git 이 있는 위치와 동일해야 한다.

- .gitignore파일 안에 원하지 않는 파일 입력

- ex)   test1.txt : 특정 파일 무시

  ​		data1.csv :          ''		

  ​		data/ : 특정 폴더 자체를 무시

     	*.png : 특정 확장자 무시

  ​	    !profile.png : 모든 png은 빼고, profile.png는 넣고!

- 소스코드가 아닌 파일 제외

- [gitignore.io](https://www.toptal.com/developers/gitignore): 자동으로 필요없는 파일을 제거해주는 .gitignore내용이 들어있음, 복사->.gitignoe파일에 저장

- #by taaeun 이렇게 내가 처리해준 내용 따로 저장

## Shared Repository Model

: 동일한 저장소를 공유하여 활용하는 방식

- 팀장 : repository owner
- 팀원 : collaborator => push를 할 권한이 생김
- Settings -> manage access -> collaborator -> github 유저 이름 or 이메일 주소 -> 초대 메일 수락 ->url 복사->clone

### 팀장

1. GitHub Repository 생성
2. git clone 주소
3. 만든 repository로 가서 코드 열기
4. README.md 생성
5. git add , commit
6. git push
7. manage access -> invite a collaborater

### 팀원

1. GitHub에 가입한 이메일로 오는 초대메일 수락
2. git clone url.git
3. 작업
4. git add, commit
5. git push origin master



## 브랜치(Branch)

= 특정 커밋을 가리키는 '포인터'



**Branch basic commands**

- git branch {breanch_name} : 브랜치 생성
- git check out  {breanch_name}        :  브랜치 이동
- git check out -b  {breanch_name}   : 브랜치 생성 및 이동 (1+2)
- git branch : 브랜치 목록 보여줌
- git branch -d  {breanch_name}   : 브랜치 삭제
- git log --graph : commit 이력 보기
- git log --graph --oneline : commit 이력 더 확실히 보기

**Branch merge**

- 각 branch에서 작업을 한 이후 이력을 합치기 위해서는 일반적으로 merge 명령어를 사용한다.
- 병합을 진행할 때, 만약 서로 다른 이력(commit)에서 동일한 파일을 수정한 경우 충돌이 발생할 수 있다.
- 이 경우에는 반드시 직접 수정을 진행해야 한다. -> merge day
- ~~안나가지면 q누르기~~

1. fast - forward merge 

   - 기존  master 브랜치에 변경사항이 없어 단순히 앞으로 이동
   - feature-a branch로 이동 후 commit
   - master 별도 변경 없음
   - master branch로 병합

2.  일반 Branch merge

   - 기존 master 브랜치에 변경사항이 있어 병합 커밋 발생
   - feature-a branch로 이동 후 commit
   - master branch commit
   - master branch로 병합

   

1. git clone 주소 .git .
2. git branch test
3. git checkout test
4. README 파일 수정
5. add , commit
6. git push origin test
7. test브런치 선택
8. pull requests -> new pull request
9. (base : master <---- compare : test) : master -> test로 바꾸기
10. create pull request 누르기
11. write에 작성하고 create pull request 누르기
12. merge pull request를 누르면 master branch로 바로 merge된다.
13. close pull request 는 거절
14. confirm merge
15. delete branch

정리

1. branch 생성(test)
2. README.md 수정
3. add, commit
4. git push origin test
5. github 가서 PR 발행



## Git Flow

- Master  : 배포 가능한 상태의 코드
- Develop : feature branch로 나뉘어지거나, 발생된 버그 수정 등 개발 진행
- feature branches : 기능별 개발 브랜치
- release branches : 테스트 브랜치
- Hotfixes : 긴급하게 반영해야하는 bug fix

정해진 답이 있는 것은 아니다.

**기본 원칙**

- master branch는 반드시 배포가능한 상태여야 한다.
- feature branch는 각 기능의 의도를 알 수 있도록 작성한다.
- commit message는 매우 중요하며, 명확하게 작성한다.
- Pull Request를 통해 협업을 진행한다.
- 변경사항을 반영하고 싶다면 master branch에 반영한다.



- Shared Repository Model : push 가능

- Fork & Pull Model : 오픈 소스, push권한 x, pr을 통해서만 push 가능

  -> Pull request를 통한 협업이 가능, Github 기반의 오픈소스 참여 과정에서 쓰이는 방식

![fork](C:/Users/%EA%B9%80%ED%83%9C%EC%9D%80/AppData/Roaming/Typora/typora-user-images/image-20210917171422399.png)

A

1. repository 만들기
2. git clone
3. add , commit, push

B

1. fork 누르기
2. 나만의 repository 생성됨
3. git clone
4. git branch fork_branch
5. git checkout fork_branch
6. add, commit
7. git push fork branch
8. fork -> pull request -> new pull request
9. create new request
10. merge권한이 없음

A 

1. merge 수락



[참고1](https://aidenlim-edu.github.io/class-git-advanced/)

[참고2](https://hphk.notion.site/hphk/Git-Github-eb889d145316478fb57d0123b698a982)

aidenlim.dev@gmail.com

임우재

해피해킹INC