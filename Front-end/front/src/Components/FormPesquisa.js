import { useState, useEffect } from 'react'
import './FormPesquisa.css'


const FormPesquisa = (adicionarDespesa) => {

        const anoAtual = new Date().getFullYear();

        const mesAtual = new Date().getMonth() + 1;

        
    

    const [desc, setDesc] = useState()
    const[categoria, setCategoria] = useState("")
    const[mes, setMes] = useState(mesAtual)
    const[ano, setAno] = useState(anoAtual)
    const [despesas, setDespesas] = useState([]);

    

    const handleSubmit = (event) => {
        event.preventDefault();


        const novaDespesa = {
            descricao: desc,
            categoria: categoria,
            mes: mes,
            ano: ano
          };

        setDesc("");
        setCategoria("");
        setMes(mesAtual);
        setAno(anoAtual);
    }



    return (

        <div>
            <form class='form-pesquisa' onSubmit={handleSubmit}>
                    <h2>Pesquisa de Despesas</h2>

                <div class='item-form div-pesquisa'>
                    <label htmlFor='desc'>Descrição:</label>
                    <input type='text'
                     name='desc'
                     placeholder='Descrição' 
                     class='desc'
                     onChange={(e) => setDesc(e.target.value)} value={desc}>
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
                <div class='item-form data'>
                    <label>
                    <span >Mês:</span>
                        <select  name='mes' onChange={(e) => setMes(parseInt(e.target.value))} value = {mes}>
                            <option value='0'>janeiro</option>
                            <option value='1'>fevereiro</option>
                            <option value='2'>março</option>
                            <option value='3'>abril</option>
                            <option value='4'>maio</option>
                            <option value='5'>junho</option>
                            <option value='6'>julho</option>
                            <option value='7'>agosto</option>
                            <option value='8'>setembro</option>
                            <option value='9'>outubro</option>
                            <option value='10'>novembro</option>
                            <option value='11'>dezembro</option>
                        </select>
                        </label>
                </div>

                <div class='item-form data'>
                <label htmlFor='ano'>Ano:</label>
                    <input type='number'
                     name='ano'
                     placeholder='Ano' 
                     onChange={(e) => setAno(e.target.value)} value={ano}>
                     </input>
                </div>
               

                <input type='submit' value='pesquisar' class='btnn-pesquisa'></input>
                
            </form>
        </div>
    )
}

export default FormPesquisa