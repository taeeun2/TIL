# SQL

## 관계형 데이터 모델

- 릴레이션의 특징
  1. 튜플의 유일성
  2. 튜플의 무순서성
  3. 속성의 원자성
  4. 속성의 무순서성

- 관계 대수 연산자의 분류

  1. 일반 집합 연산자
     - 합집합
     - 교집합
     - 차집합
     - 카티션 프로덕트

  2. 순수관계 연산자
     - 셀렉트 
     - 프로젝트 : 선택 조건을 만족하는 릴레이션의 수직적 부분집합
     - 조인

- 키(Key)
  1. 키의 개념 : 하나의 테이블 내에서 각 튜플을 유일하게 식별할 수 있는 속성들의 집합
  2. 후보키 : 유일성과 최소성 만족
  3. 수퍼키 : 유일성은 만족하지만 최소성은 만족하지 않음
  4. 기본키 : 후보키들 중 DBA가 지정한 하나의 키
  5. 대체키 : 후보키들 중 기본키를 제외한 나머지 후보키
  6. 외래키 : 한 릴레이션 R1의 튜플과 다른 릴레이션 R2의 하나의 튜플관의 연관 관계를 표시하기 위하여 사용함.
  7. 무결성
     - 개체 무결성 : 기본 키는 NULL값일 수 없다.
     - 참조 무결성 : 외래키 값은 피참조 릴레이션의 기본키 값이거나 NULL값이다.
     - 도메인 무결성 : 속성값은 해당 속성 도메인에 속한 값들 중 하나여야 한다.

## SQL의 개념

### SQL 기본 구문

- DDL : 데이터 정의문

  1. 테이블 생성 : CREATE문

  2. 테이블 삭제 : DROP문

  3. 테이블 구조 변경 : ALTER문

     ALTER TABLE 테이블명{

     {ADD|ALTER|DROP}[COLUMN]속성명[타입]

     }

     - 속성 추가 

       ALTER TABLE 테이블명 ADD 속성명 속성타입

     - 속성 제거

       ALTER TABLE 테이블명 DROP 속성명

     - 속성 타입 변경

       ALTER TABLE 테이블명 ALTER 속성명 속성타입

- DML문 : 데이터 조작문

  1. 튜플 삽입 : INSERT문

     INSERT INTO 테이블명(속성명, 속성명 ,  . . .)

     VALUES(속성값, 속성값,  . . .)

  2. 튜플 변경 : UPDATE문

     UPDATE 테이블명

     SET 속성명 = 수식

     [WHERE 조건]

  3. 튜플 삭제 : DELETE문

     DELETE FROM 테이블명

     [WHERE 조건]

  4. 트랜잭션 관련

     SELECT 속성명, 속성명, ...

     FROM 테이블

     [WHERE 조건]

- DCL문 : 데이터 제어문

  1. 트랜잭션 관련 : COMMIT, ROLLBACK
  2. 사용자 권한 제어 관련 : GRANT, REVOKE, DENY



## 제약조건

1. 제약 조건 

   1. 데이터 무결성

      - NOT NULL
      - UNIQUE
      - PRIMARY KEY
      - FOREIGN KEY
      - CHECK

   2.  제약조건 설정

      - 테이블을 만들 때 속성에 제약조건 지정

        CREATE TABLE 테이블명

        (

        속성명 속성 타입 [ [제약조건명] 제약조건],

        속성명 속성 타입,

        . . .

        ​	)

      -  참조 무결성 제약조건

        속성명[CONSTRAINTS 제약조건명]

        REFERENCE 참조테이블명(속성명)

      - 테이블 수준 제약조건 

        속성 단위로 제약조건 설정은 표현에 있어서 제약이 따름 

        CREATE TABLE 테이블명( 

        속성명 속성타입 ...

         [CONSTRAINT 제약조건명] 제약조건(속성명) 

        )

2.  제약조건 변경

   - 추가

     ALTER TABLE 테이블명

     ADD [ CONSTRAINT 제약조건명] 제약조건(속성명)

   - 제거

     ALTER TABLE 테이블명

     DROP CONSTRAINT 제약조건명

### 데이터 검색

- DISTINCT : 중복된 것 제거

- BETWEEN a AND b : 검색 조건의 상한과 하한 지정

- IN(a, b, c, . . .) : 속성값이 a, b, c, . . . 중 하나라도 일치하면 참

- IS NULL : NULL값은 어떤 비교를 하든 거짓

- LIKE : 컬럼에 저장된 문자열 중 LIKE연산자에서 지정한 문자 패턴과 부분적으로 일치하면 참이 되는 연산자

  1. % : 임의의 길이의 문자열
  2. _ : 글자 한자

- ORDER BY 절

  ORDER BY {column_name} {ASC |DESC}

## 트랜잭션

트랜잭션 제어문(TCL)

- COMMIT : 트랜잭션의 마지막 명령어가 수행되었음을 나타냄

- ROLLBACK : 트랜잭션의 변경을 취소하고 트랜잭션 종료

- SAVEPOINT : 현재 트랜잭션에서 ROLLBACK시킬 위치 지정

  ex)

  ```sql
   SAVE TRAN svpoint1 
  insert into table vales(50, 'product', 'Seoul');
  -- 삽입
   ROLLBACK TRAN svpoint1
   -> 삽입이 되지 않는다.
  ```

## 조인

하나의 SQL질의문에 의해서 여러 테이블에 저장된 데이터를 한번에 조회할 수 있는 기능

- 다양한 조인들
  1. 카티션 프로덕트 : 두 테이블에 속한 튜플들의 모든 가능한 쌍 생성
  2. 동등 조인 : 조인 조건이 '='
  3. 자연 조인 : 두 테이블에 공통으로 나타나는 속성을 동등조인으로 생각
  4. 쎄타 조인 : 조인 조건으로 <, >, <= 등을 쓸 수 있음
  5. 셀프 조인 : 하나의 테이블 내에 있는 컬럼끼리 연관시켜 조인
  6. 다중 조인 : 조인에 참여하는 테이블이 2개

- 표현

  FROM 테이블명 INNER JOIN 테이블명 ON 조인조건

  FROM 테이블명 JOIN 테이블명 ON 조인조건

  FROM 테이블명 LEFT OUTER JOIN 테이블명 ON 조인조건

  FROM 테이블명 RIGTH OUTER JOIN JOIN 테이블명 ON 조인조건



## 중첩 질의문

- 중첩 질의문의 필요성 : 조인의 필요성과 동일

- 단일행 서브 쿼리 : 서브 쿼리의 결과로 하나의 튜플만이 반환

  ex)

  ```sql
  SELECT ENO, DNO
  FROM EMPLOYEE
  WHERER EMPLOYEE.DNO = (SELECT DNO FROM EMPLOYEE WHERE
  					   EMPLOYEE.ENO = 110)
  ```

- 다중행 서브 쿼리 : 서브 쿼리의 결과로 여러 개의 튜플들이 반환

  ex)

  ```sql
  SELECT ENO, DNO
  FROM EMPLOYEE
  WHERER EMPLOYEE.DNO = (SELECT DNO FROM EMPLOYEE WHERE
                         EMPLOYEE.ENO >= 110)
  ```

- 다중행 비교 연산자

  - IN : 속성값이 여러 값들 중 하나이기만 하면 참
  - ANY 또는 SOME : 메인 쿼리 비교 조건에서 서브 쿼리의 결과와 하나라도 일치하면 참, IN과 차이점은 >,<,>=와 같은 범위 쿼리를 쓸 수 있음
  - ALL : 메인 쿼리 비교 조건에서 서브 쿼리의 결과와 모두 일치하면 참
  - ex)

  1. 다중행 서브 쿼리 : IN  

     -  봉급이 500이상인 사원과 같은 부서에 근무하는 사원들의 이름, 봉급,  부서번호 구하기 

     ```sql
     SELECT ENAME, SALARY, DNO
     FROM EMPLOYEE
     WHERE DNO  IN (SELCT DNO FROM EMPLOYEE 
     				WHERE SALARY >= 500)
     ```
  
  2. 다중행 서브 쿼리 : ANY  
  
     - 부서 번호 20에 근무하는 한 직원의 봉급 보다 많은 봉급을 받는 직원들의 이름,  봉급, 부서번호 출력 
  
     ```sql
     SELECT ENAME, SALARY, DNO
     FROM EMPLOYEE
     WHERE SALARY > ANY (SELECT SALARY FROM
                         EMPLOYEE WHERE DNO = 20)
     
     ```
  
  1. 다중행 서브 쿼리 : ALL  
  
     - 부서 번호 10에 근무하는 모든 직원들의 봉급 보다 많은 봉급을 받는 직원들의 이름, 봉급, 부서번호 출력
  
     ```sql
     SELECT ENAME, SALARY, DNO
     FROM EMPLOYEE
     WHERE SALARY > ALL ( SELECT SALARY FROM
                         EMPLOYEE WHERE DNO = 10)
  ```
  
  - EXISTS : 서브 쿼리의 결과가 하나라도 존재하면 참
  
  - NOT EXISTS : EXISTS와 상반되는 연산자
  
    ex) 봉급과 커미션의 합이 500이 넘는 사원이 존재하면 모든 사원의 이름 출력
  
    ```sql
    SELECT ENAME, SALARY, DNO
    FROM EMPLOYEE
    WHERE EXISTS (SELECT * FROM EMPLOYEE 
                  FROM EMPLOYEE
                  WHERE SALARY+COMMISSION > 500)
  ```

## 집합 연산자

- UNION : 합집합

  예) 부서 번호 10인 사원들과 직급이 staff인 사원들 검색

  ```sql
  (select * from employee where dno = 10) 
  union
  (select * from employee where job = "staff")
  ```

  중복 허용 : union all 사용

  

- INTERSECT : 교집합

  예) 부서 번호 10이고 직급이 staff인 사원들 검색

  ```sql
  (select * from employee where dno = 10) 
  intersect
  (select * from employee where job = "staff")
  ```

- EXCEPT : 차집합

  예) 부서 번호 10이고 직급이 staff가 아닌 사원들 검색

  ```sql
  (select * from employee where dno = 10) 
  
  except
  (select * from employee where job = "staff")
  ```

- 외부 합집합

  *두 질의 결과의 속성 수와 타입이 일치되게 만듦

  ```sql
  (select eno, ename, dno, NULL from Empl)
  union
  (select NULL, NULL, dno, dname from department)
  ```

## 집단 함수

- 집단 함수의 종류
  - SUM : 그룹의 합계
  - AVG : 그룹의 평균
  - COUNT : 그룹의 개수
  - MAX : 그룹의 최대값
  - MIN : 그룹의 최소값
  - STDEV : 그룹의 표준편차
  - VAR : 그룹의 분산

- 집단 함수의 예

  Q 사원의 최대 봉급, 최소 봉급 구하기

  ```SQL
  SELECT MAX(SALARY), MIN(SALARY)
  FROM EMPLOYEE
  ```

  Q 사원 테이블의 튜플 수와 COMMISION의 개수 구하기

  ```SQL
  SELECT COUNT(*), COUNT(COMMISION)
  FROM EMPLOYEE
  ```

  Q 사원의 직급의 수와 중복되지 않는 직급의 수 구하기

  ```SQL
  SELECT COUNT(JOB), COUNT(DISTINCT JOB)
  FROM EMPLOYEE
  ```

  Q 부서별 사원들의 평균 봉급과 부서번호 검색

  ```SQL
  SELECT DNO, AVG(SALARY)
  FROM EMPLOYEE
  GROUP BY DNO
  ```

  Q 부서의 최대 봉급이 500초과인 부서에 대해서만 부서별 사원들의 평균 봉급과 부서 번호 출력

  ```SQL
  SELECT DNO, AVG(SALARY) AS DNOAVGSAL
  FROM EMPLOYEE
  GROUP BY DNO
  HAVING MAX(SALARY)>500
  ```

  Q  ROLLUP 연산자를 이용하여 부서별 직급별 사원들의 봉급 합 구하기

  ```SQL
  SELECT DNO, JOB, SUM(SALARY)
  FROM EMPOYEE
  GROUP BY DNO, JOB WITH ROLLUP
  ```

  Q CUBE 연산자를 이용하여 부서별 직급별 사원들의 봉급 합 구하기

  ```SQL
  SELECT DNO, JOB, SUM(SALARY)
  FROM EMPOYEE
  GROUP BY DNO, JOB WITH CUBE
  ```

## 순위 계산

1. TOP() 함수

   - 질의 결과 튜플 수의 제한

   - Q 사원들 중 급여 기준 5등까지만 결과로 출력하기

     ```SQL
     SELECT TOP(5) *,
     FROM EMPLOYEE
     ORDER BY SALARY DESC
     ```

   - 동률이 있을 때를 모두 보고 싶은 경우 -> WITH TIES

     ```SQL
     SELECT TOP(5) WITH TIES *
     FROM EMPLOYEE
     ORDER BY SALARY DESC
     ```

   - 정렬 기준 특정 비율까지만 보고 싶은 경우

     Q 사원들 중 급여 기준 20%까지만 결과로 출력하기

     ```SQL
     SELECT TOP(20) PERCENT *
     FROM EMPLOYEE
     ORDER BY 속성명 DESC
     ```

2. RANK() 함수

   - 각 튜플에 등수를 표시함

   - RANK() 함수 : 동률에 대하여 동일 등수 배정, 비연속식 등수 배정(1,2,2,4, . . .)

   - Q 사원에 대하여 이름, 급여, 급여에 대한 내림차순 RANK() 값 

     ```SQL
     SELECT ENAME, SALARY, RANK() OVER
     (ORDER BY SALARY DESC) AS RANK
     FROM EMPLOYEE
     ```

   - DENSE_RANK()함수 : 등률에 대하여 동일 등수 배정, 연속식 등수 배정(1, 2, 3, 3, ...)
   - ROW_NUMBER() 함수 : 동률에 대하여 임의 등수 배정, 연속식 등수 배정(1, 2, 3, 4, ...)
   - NTILE(n) 함수 : 전체 튜플을 n개로 균등 분할하여 순위 지정

3. 그룹 별 순위 지정

   - PARTITION BY 속성명

     : 튜플들의 속성값에 따라서 그룹핑함

     : 각 그룹에 대하여 순위 함수를 적용

     Q DNO별로 분류하고 각 분류된 소그룹에서 SALARY 기준 내림차순하고 순위 나타내기

     ```SQL
     SELECT ENAME, SALARY, DNO,
     RANK() OVER(PARTITION BY DNO ORDER BY SALARY DESC) AS RANK_DEPT
     FROM EMPOYEE
     ```

   - 행 순서 함수

     : 정렬된 대상에서 특정 순위의 튜플들을 추출할 필요가 있을 때 사용되는 함수

     - FIRST_VALUE 함수 

       : 정렬 대상에서 첫 번째 데이터 추출

       FIRST_VALUE(SALARY)

     - LAG/LEAD 함수

       : 지정된 순서에서 선행 / 후행 데이터를 참조하는 함수

       LAG(SALARY, 1) : 선행 순위의 급여

       LEAD(SALAY,1) : 후행 순위의 급여

## 인덱스

- 색인 생성

  ```SQL
  CREATE INDEX 색인명
  ON 테이블명(속성명, 속성명, ...)
  ```

- 색인 삭제

  ```SQL
  DROP INDEX 색인명
  ON 테이블명
  ```

- 고유 인덱스 

  : 유일 값을 가지는 속성에 대하여 생성하는 색인

  : 각 키 값은 테이블의 하나의 튜플과 연관됨

  Q  부서 테이블에 부서 이름에 대하여 고유 색인 생성하기

  ```SQL
  CREATE UNIQUE INDEX idx_name_unique
  ON DEPARTMENT(dNname)
  ```

- 비고유 인덱스

  : 중복된 값을 가지는 속성에 생성하는 인덱스

  : 키 값은 여러 개의 튜플들과 연관됨

  Q 부서 테이블에 부서 위치에 대하여 비고유 색인 생성하기

  ```SQL
  CREATE INDEX idx_loc_unique
  ON DEPARTMENT(loc)
  ```

## 뷰

- 뷰의 생성 구문

  ```sql
  CREATE VIEW 뷰이름
  AS SQL문(SELECT문)
  ```
  
- 뷰의 삭제 구문

  ```SQL
  DROP VIEW 뷰이름
  ```

  Q 사원 테이블에 부서번호 30인 사원들의 뷰 생성하기

  ```SQL
  CREATE VIEW EMP30
  AS
  SELECT * FROM EMPLOYEE
  WHERE DNO = 30
  ```

- 뷰의 갱신 연산

  : 뷰가 집단연산의 결과일 경우, 뷰를 통한 갱신 연산 불가능

## 사용자 관리

- GREANT문

  : 자신에게 허용된 권한을 다른 사용자에게 부여하는 구문

  GRANT[권한|ALL] ON 데이터 객체 TO 사용자

- REVOKE문

  : 다른 사용자에게 허용한 권한을 철회하는 구문

  REVOKE 권한 ON 데이터객체 TO 사용자

- DENY문

  : 다른 사용자에게 특정 권한을 불허하는 구문

  DENY 권한 ON 데이터객체 TO 사용자



## 프로시저

- 개념 : 자주 사용되는 질의문들을 하나로 묶어서 저장해두고 필요할 때마다 명령문처럼 실행할 수 있도록 해주는 것

- 생성 구문

  ```SQL
  CREATE[PROCEDURE|PROC] 프로시저 이름
  AS
  BEGIN SQL문 END
  // BEGIN END는 SQL문이 하나만 있다면 생략 가능
  ```

- 프로시저 실행 문법

  ```SQL
  EXEC 프로시저 이름
  ```

- 프로시저 수정 문법

  ```SQL
  ALTER PROCEDURE 프로시저 이름
  AS SQL문
  
  DROP PROCEDURE 프로시저 이름
  ```

- 입력 매개변수의 선언

  ```SQL
  --생성
  CREATE PROCEDURE 프로시저 이름
  @매개변수명 타입, . . .
  AS SQL문
  
  --실행
  EXEC 프로시저이름 매개변수값
  ```

- 출력 매개변수의 선언

  ```SQL
  CREATE PROCEDURE 프로시저 이름
  @매개변수명 타입 OUTPUT, ...
  AS
  SELECT @매개변수명 = 속성명
  FROM WHERE ...
  ```

  - 출력 매개변수 값 받기

    1. 프로시저 실행 전 매개변수 선언(DECLARE 문)
    2. 선언된 매개변수 출력(SELECT문)

    ```SQL
    DECLARE @매개변수명
    EXEC 프로시저명 @매개변수명 OUTPUT
    SELECT @매개변수명
    ```

- 사용자 정의 함수의 선언

  ```SQL
  CREATE FUNCTION 함수명
  (@매개변수명 타입, ...)
  RETURNS 반환타입
  AS
  [BEGIN] SQL 문[END]
  ```

  

## 트리거

- 특정 조건을 만족하면 자동으로 수행되도록 하는 저장 프로시저

```SQL
CREATE TRIGGER 트리거명
ON 테이블명
[FOR/AFTER/INSTEAD OF][INSERT/UPDATE/DELETE]
AS
SQL문
```

- DDL 트리거

```SQL
CREATE TRIGGER 트리거명
ON DATABASE
{FOR|AFTER}{DROP_TABLE|CREATE_TABLE|AFTER_TABLE}
AS SQL문
```

