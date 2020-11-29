import React, { useEffect, useState } from "react";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
} from "recharts";
import axios from "axios";

const data = [
  {
    name: "바보 파..",
    uv: 1890,
    pv: 4800,
    tt: 4000,
  },
  {
    name: "멍청이..",
    uv: 2390,
    pv: 3800,
    tt: 3400,
  },
];

function StoreRankGraphComponent() {
  const [theData, setTheData] = useState([]);
  useEffect(() => {
    (async () => {
      try {
        const result = await axios.get("/orderTotal");
        const forShow = result.data.map((v) => {
          return {
            name: v[0],
            매출: v[1],
          };
        });
        setTheData(forShow);
      } catch (err) {
        console.error(err);
      }
    })();
  }, []);
  return (
    <BarChart
      width={600}
      height={600}
      data={theData}
      margin={{
        top: 5,
        right: 30,
        left: 20,
        bottom: 5,
      }}
    >
      <CartesianGrid strokeDasharray="4 4" />
      <XAxis dataKey="name" />
      <YAxis />
      <Tooltip />
      <Legend />
      <Bar dataKey="매출" fill="#82ca9d" />
    </BarChart>
  );
}

export default StoreRankGraphComponent;
