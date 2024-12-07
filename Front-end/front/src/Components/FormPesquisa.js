import { useState } from 'react'
import './FormPesquisa.css'

const FormPesquisa = (adicionarDespesa) => {

    const [desc, setDesc] = useState()
    const[categoria, setCategoria] = useState("")
    const[data, setData] = useState()
    const [despesas, setDespesas] = useState([]);


    const handleSubmit = (event) => {
        event.preventDefault();


        const novaDespesa = {
            descricao: desc,
            categoria: categoria,
            data: data
          };
      
          // Chama a função do componente pai para adicionar a despesa
    adicionarDespesa(novaDespesa);
        

        setDesc("");
        setCategoria("");
        setData("");
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
                <div class='item-form'>
                    <label>
                    <span >Mês:</span>
                        <select  name='mes' onChange={(e) => setData(e.target.value)}>
                            <option value='1'>janeiro</option>
                            <option value='2'>fevereiro</option>
                            <option value='3'>março</option>
                            <option value='4'>abril</option>
                            <option value='5'>maio</option>
                            <option value='6'>junho</option>
                            <option value='7'>julho</option>
                            <option value='8'>agosto</option>
                            <option value='9'>setembro</option>
                            <option value='10'>outubro</option>
                            <option value='11'>novembro</option>
                            <option value='12'>dezembro</option>
                        </select>
                        </label>
                </div>
               

                <input type='submit' value='pesquisar' class='btnn-pesquisa'></input>
                
            </form>
        </div>
    )
}

export default FormPesquisa