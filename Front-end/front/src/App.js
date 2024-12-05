//import logo from './logo.svg';
import './App.css';
import MyForm from './Components/MyForm';
import MyTable from './Components/MyTable';
import { useState } from 'react';

function App() {
  const [despesas, setDespesas] = useState([]);

  const adicionarDespesa = (novaDespesa) => {
    setDespesas((prevDespesas) => [...prevDespesas, novaDespesa]);
  };

  return (
    <div className="App">
      <h2>Cadastro de Despesas</h2>
      <MyForm adicionarDespesa={adicionarDespesa}/>

      <h2>Relatório Despesas</h2>
      <MyTable despesas={despesas}/>

    </div>
  );
}

export default App;
