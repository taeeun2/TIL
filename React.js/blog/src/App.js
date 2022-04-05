import React, {useState} from 'react';
import './App.css';
import logo from './logo.svg';

function App() {
  
  // destructuring 문법
  // var [a,b] = [10, 100];

  //[state 데이터, state 데이터 변경 함수]
  let [글제목, 글제목변경] = useState(['남자 코트 추천', '강남 우동 맛집', '파이썬 독학']); 
  let [따봉, 따봉변경] = useState(0);

  //state
  //1. 변수 대신 쓰는 데이터 저장공간
  //2. useState()를 이용해 만들어야함
  //3. 문자, 숫자, array, object 다 저장가능

  //state에 데이터 저장해놓는 이유
  //웹이 App처럼 동작하게 만들고 싶어서
  //state는 변경되면 HTML이 자동으로 재랜더링이 된다.
  // = HTML이 새로고침 없이도 스무스하게 변경됨
  // 자주 바뀌는, 중요한 데이터는 변수 말고 state로 저장해서 쓴다.
   

  let posts = '강남 고기 맛집'
  // let styles =  {color : 'blue', fontSize : '30px'}
  function 함수(){
    return 100
  }

  // 수정된[데이터]를 만드는데 state 복사본을 만들어서 수정한다.
  // state를 deep copy해서 수정
  // <Array, Object state 데이터 수정 방법>
  // 일단 변경함수 써야함
  // 변경함수(대체할 데이터)

  function 제목바꾸기(){
    var newArray = [...글제목];  
    newArray[0] = '여자 코트 추천'
    글제목변경(newArray)
  }



  return (  
    <div className="App">
      <div className='black-nav'>
        {/* <div style = { styles}>개발 Blog</div> */}
        <div>개발 Blog</div>
      </div>    
      {/* 데이터 바인딩이 쉬움 {변수명, 함수, 이미지 등} */}
      {/* <h4>{posts}</h4>
      <h4>{함수()}</h4>
      <img src = {logo}/> */}


      {/* 리액트
      onClick = {클릭될 때 실행할 함수}
      onClick = {()=> {실행할 내용}} */}
      <button onClick={제목바꾸기}>버튼</button>
      <div className='list'>
        <h3>{글제목[0]}<span onClick={ ()=>{따봉변경(따봉+1)}}>👍</span> {따봉}</h3>
        <p>2월 17일 발행</p>
        <hr/>
      </div>  
      <div className='list'>
        <h3>{글제목[1]}</h3>
        <p>2월 18일 발행</p>
        <hr/>
      </div>
      <div className='list'>
        <h3>{글제목[2]}</h3>
        <p>2월 19일 발행</p>
        <hr/>
      </div>


      <Modal/>
    </div>
  );
}

//Component 만드는 법
//1. 함수 만들고 이름짓고
//2. 축약을 원하는 HTML 넣고
//3. 원하는 곳에서 <함수명/>

//유의사항
//1. 이름은 대괄호
//2. return() 안에 있는건 태그 하나로 묶어야 함

// 반복출현하는 HTML 덩어리들
// 자주 변경되는 HTML UI들
// 다른 페이지 만들때도 컴포넌트로 만듦

//Componet 많이 만들었을 때 단점:
//-state 쓸 때 복잡해짐
//
function Modal(){
  return (
    <>
    <div className='modal'>
      <h3>제목</h3>
      <p>날짜</p>
      <p>상세내용</p>
    </div>
    </>
  )
}

export default App;
