import { Table } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useState } from 'react';

const BasicExample = ({despesas}) => {

    const total = despesas.reduce((acc, despesa) => acc + (despesa.valor || 0), 0);
   
  return (
    <Table striped bordered hover>
      <thead>
        <tr>
          <th>#</th>
          <th>Descrição</th>
          <th>Categoria</th>
          <th>Valor</th>
          <th>Parcela</th>
          <th>Forma de Pagamento</th>
          <th>Data</th>
        </tr>
      </thead>
      <tbody>
      {despesas.map((despesa, index) => (
          <tr key={index}>
            <td>{index + 1}</td>
            <td>{despesa.descricao}</td>
            <td>{despesa.categoria}</td>
            <td>{despesa.valor}</td>
            <td>{despesa.parcelas}</td> {/* colocar o numero da parcela*/}
            <td>{despesa.forma}</td>
            <td>{despesa.data}</td>
          </tr>
        ))}
      </tbody>
      <tfoot>
          <tr>
            <td colSpan="2" style={{ fontWeight: 'bold' }}>Total</td>
            <td style={{ fontWeight: 'bold' }} colSpan="3">
              {total.toFixed(2)}
            </td>
          </tr>
        </tfoot>
    </Table>


  );
}

export default BasicExample;