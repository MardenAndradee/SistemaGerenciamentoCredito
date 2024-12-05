import { useState } from 'react'
import './MyForm.css'

const MyForm = ({ adicionarDespesa }) => {

    const [desc, setDesc] = useState()
    const [valor, setValor] = useState()
    const[categoria, setCategoria] = useState("")
    const[data, setData] = useState()
    const [despesas, setDespesas] = useState([]);


    const handleSubmit = (event) => {
        event.preventDefault();


        const novaDespesa = {
            descricao: desc,
            valor: parseFloat(valor), // Certificando-se de que o valor é um número
            categoria: categoria,
            data: data
          };
      
          // Chama a função do componente pai para adicionar a despesa
    adicionarDespesa(novaDespesa);
        

        setDesc("");
        setValor("");
        setCategoria("");
        setData("");
    }

    return (

        <div>
            <form onSubmit={handleSubmit}>
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