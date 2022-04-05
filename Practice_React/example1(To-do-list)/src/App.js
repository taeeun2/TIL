import logo from './logo.svg';
import './App.css';
import { useState } from 'react';

function App() {

  const [toDo, setToDo] = useState('');
  const [toDos, setToDos] = useState([]);
  const onChange = (event) => setToDo(event.target.value)
  const onSubmit = (event) => {
    event.preventDefault();
    if (toDo == ''){
      return;
    }
    console.log(toDos)
    setToDos(currentArray=>[toDo,...currentArray])//함수를 보내는 방법
    setToDo('');//toDo = '' 값을 보내는 법
  }
  return (
    <div>
      <h1>My To Dos ({toDos.length})</h1>
      <form onSubmit={onSubmit}>
      <input onChange = {onChange} value = {toDo} placeholder='Write your to do...'/>
      <button>Add To Do</button>
      </form>
      <hr />
      {/* map은 하나의 array에 있는 item을 내가 원한느 무엇이든지로 바꿔주는 역할을 하고
      그건 결국 새로운 array로 return 해준다. */}
      {toDos.map((item,index)=><li key ={index}>{item}</li>)}
    </div>
  );
}

export default App;
