import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './styles.css';
import { RecordsResponse } from './types';
import { formatDate } from './helpers';
import Pagination from './Pagination';
import Filters from '../../components/Filters';

const BASE_URL = 'http://localhost:8880';

const Records = () => {
  // useState() ->> hook que possuem valores a serem consumidos
  const [recordsResponse, setRecordsResponse] = useState<RecordsResponse>();
  const [activePage, setActivePage] = useState(0);

  // hook executado no início da leitura do componente
  useEffect(() => {
    axios.get(`${BASE_URL}/records?linesPerPage=12&page=${activePage}`)
      .then((res) => setRecordsResponse(res.data));
  }, [activePage]); // toda vez que activePage mudar executa

  const handlePageChange = (index: number) => setActivePage(index);

  return (
    <div className="page-container">

      <Filters link="/charts" linkText="VER GRÁFICO" />

      <table className="records-table" cellPadding="0" cellSpacing="0">
        <thead>
          <tr>
            <th>INSTANTE</th>
            <th>NOME</th>
            <th>IDADE</th>
            <th>PLATAFORMA</th>
            <th>GÊNERO</th>
            <th>TÍTULO</th>
          </tr>
        </thead>

        <tbody>
          {recordsResponse?.content.map((record) => (
            <tr key={record.id}>
              <td>{formatDate(record.moment)}</td>
              <td>{record.name}</td>
              <td>{record.age}</td>
              <td className="text-secondary">{record.platform}</td>
              <td>{record.genreName}</td>
              <td className="text-primary">{record.gameTitle}</td>
            </tr>
          ))}
        </tbody>
      </table>

      <Pagination
        activePage={activePage}
        totalPages={recordsResponse?.totalPages}
        goToPage={handlePageChange}
      />
    </div>
  );
};

export default Records;
