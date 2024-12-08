//import logo from './logo.svg';
import './App.css';
import MyForm from './Components/MyForm';
import MyTable from './Components/MyTable';
import { useState } from 'react';
import FormPesquisa from './Components/FormPesquisa';

function App() {
  const [despesas, setDespesas] = useState([]);

  const adicionarDespesa = (novaDespesa) => {
    setDespesas((prevDespesas) => [...prevDespesas, novaDespesa]);
  };

  return (
    <div className="App">
      <header>
        {/* fazer header */}
      </header>

      <div class='Cadastros'>
      
      <MyForm adicionarDespesa={adicionarDespesa}/>
      </div>

      <div className='Pesquisa'>
        <FormPesquisa/>
      </div>

      <MyTable despesas={despesas}/>

    </div>
  );
}

export default App;
