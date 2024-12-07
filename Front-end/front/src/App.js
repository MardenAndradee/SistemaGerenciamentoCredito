//import logo from './logo.svg';
import './App.css';
import MyForm from './Components/MyForm';
import MyTable from './Components/MyTable';
import FormDespFixas from './Components/FormDespFixas'
import { useState } from 'react';
import FormPesquisa from './Components/FormPesquisa';

function App() {
  const [despesas, setDespesas] = useState([]);

  const adicionarDespesa = (novaDespesa) => {
    setDespesas((prevDespesas) => [...prevDespesas, novaDespesa]);
  };

  return (
    <div className="App">

      <h2>Cadastros</h2>
      <div class='Cadastros'>
      
      <MyForm adicionarDespesa={adicionarDespesa}/>
      <FormDespFixas/>
      </div>

      <h2>Pesquisa</h2>
      <div className='Pesquisa'>
        <FormPesquisa/>
      </div>

      <h2>Relatório Despesas</h2>
      <MyTable despesas={despesas}/>

    </div>
  );
}

export default App;
