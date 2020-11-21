import React from "react";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
} from "recharts";

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

function StoreStatisticGraphComponent() {
  return (
    <BarChart
      width={600}
      height={600}
      data={data}
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
      <Bar dataKey="pv" fill="#8884d8" />
      <Bar dataKey="uv" fill="#82ca9d" />
      <Bar dataKey="tt" fill="#12532" />
    </BarChart>
  );
}

export default StoreStatisticGraphComponent;
