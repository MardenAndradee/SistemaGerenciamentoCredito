import { useState } from 'react'
import './MyForm.css'
import React, { useEffect } from 'react';
import FormRange from 'react-bootstrap/esm/FormRange';

const MyForm = ({ adicionarDespesa }) => {

    const [parcelas,setParcelas] = useState("1")
    const [desc, setDesc] = useState()
    const [valor, setValor] = useState()
    const[categoria, setCategoria] = useState("")
    const[data, setData] = useState()
    const[forma, setForma] = useState()
    const [despesas, setDespesas] = useState([])

    const [mostrarDiv, setMostrarDiv] = useState(false);

    const handleChange = (event) => {

        setForma(event.target.value);

        const valorSelecionado = event.target.value;

        setMostrarDiv(event.target.value === "credito");

        if (valorSelecionado === "debito") {
            setParcelas("1"); 
        }
    };


    const handleSubmit = (event) => {
        event.preventDefault();


        const novaDespesa = {
            parcelas : parcelas,
            descricao: desc,
            valor: parseFloat(valor), 
            categoria: categoria,
            data: data,
            forma: forma
          };
      
          // Chama a função do componente pai para adicionar a despesa
    adicionarDespesa(novaDespesa);
        

        setDesc("");
        setValor("");
        setCategoria(0);
        setData("");
        setParcelas("1");
        setForma();
    }



    return (

        <div>
            <form onSubmit={handleSubmit}>
            <h2>Cadastro de despesa</h2>

                <div class='item-form' id='escolhaTipo'>
                    <label>
                        <span >Forma de pagamento: </span>
                        <select  name='forma' onChange={handleChange}>
                            <option value='debito'>DÉBITO</option>
                            <option value='credito'>CRÉDITO</option>
                        </select>
                    </label>
                </div>
                {mostrarDiv && (
                <div class='item-form' id='parcelas'>
                    <label htmlFor='parcelas'>Parcelas:</label>
                    <input type='number'
                     name='parcelas'
                     placeholder='N° Parcelas' 
                     class='parcelas'
                     onChange={(e) => setParcelas(e.target.value)} value={parcelas}>
                     </input>
                </div>
                )}
                <div class='item-form'>
                    <label htmlFor='desc'>Descrição:</label>
                    <input type='text'
                     name='desc'
                     placeholder='Descrição' 
                     class='desc'
                     onChange={(e) => setDesc(e.target.value)} value={desc}>
                     </input>
                </div>
                <div class='item-form'> 
                    <label htmlFor='Valor'>Valor:</label>
                    <input type='text'
                     name='Valor'
                      placeholder='Valor'
                       onChange={(e) => setValor(e.target.value)} value={valor}>
                       </input>
                </div>
                <div class='item-form'>
                    <label>
                        <span >Categoria:</span>
                        <select  name='categoria' onChange={(e) => setCategoria(e.target.value)}>
                            <option value='categ0'>Categ0</option>
                            <option value='categ1'>Categ1</option>
                            <option value='categ2'>Categ2</option>
                            <option value='categ3'>Categ3</option>
                            <option value='categ4'>Categ4</option>
                        </select>
                    </label>
                </div>
                <div class='item-form'>
                    <label htmlFor='data'>Data:</label>
                    <input type='date'
                     name='data'
                      placeholder='data'
                      class='data'
                       onChange={(e) => setData(e.target.value)} value={data}>
                       </input>
                </div>
               

                <input type='submit' value='enviar' class='btnn'></input>
                
            </form>
        </div>
    )
}

export default MyForm