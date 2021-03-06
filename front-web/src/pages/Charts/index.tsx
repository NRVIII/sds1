import React, { useEffect, useState } from 'react';
import Chart from 'react-apexcharts';
import axios from 'axios';
import Filters from '../../components/Filters/index';
import './styles.css';
import { pieOptions, barOptions } from './chart-options';
import { buildBarSeries, getGenderChartData, getPlatformChartData } from './helpers';

type PieChartData = {
  labels: string[];
  series: number[];
}

type BarChartData = {
  labels: string[];
  series: number[];
}

const initialPieData = {
  labels: [],
  series: [],
};

const BASE_URL = 'http://localhost:8880';

const Charts = () => {
  // @ts-ignore
  const [barChartData, setBarChartData] = useState<BarChartData>([]);
  const [platformData, setPlataformData] = useState<PieChartData>(initialPieData);
  const [genderData, setGenderData] = useState<PieChartData>(initialPieData);

  useEffect(() => {
    async function getData() {
      const recordsResponse = await axios.get(`${BASE_URL}/records?linesPerPage=0`);
      const gamesResponse = await axios.get(`${BASE_URL}/games`);

      const barData = buildBarSeries(gamesResponse.data, recordsResponse.data.content);
      setBarChartData(barData);

      const platformChartData = getPlatformChartData(recordsResponse.data.content);
      setPlataformData(platformChartData);

      const genderChartData = getGenderChartData(recordsResponse.data.content);
      setGenderData(genderChartData);
    }

    getData().then(() => {});
  }, []);

  return (
    <div className="page-container">
      <Filters link="/records" linkText="VER TABELA" />

      <div className="chart-container">
        <div className="top-related">
          <h1 className="top-related-title">
            Jogos mais votados
          </h1>

          <div className="games-container">
            <Chart
              options={barOptions}
              type="bar"
              width="900"
              height="650"
              series={[{ data: barChartData }]}
            />
          </div>
        </div>

        <div className="charts">
          <div className="platform-chart">
            <h2 className="chart-title">Plataformas</h2>
            <Chart
              options={{ ...pieOptions, labels: platformData?.labels }}
              type="donut"
              series={platformData?.series}
              width="350"
            />
          </div>

          <div className="gender-chart">
            <h2 className="chart-title">Gêneros</h2>
            <Chart
              options={{ ...pieOptions, labels: genderData?.labels }}
              type="donut"
              series={genderData?.series}
              width="350"
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Charts;
