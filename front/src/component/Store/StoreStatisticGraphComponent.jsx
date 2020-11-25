import React from "react";
import {
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  LineChart,
  Line,
} from "recharts";

function StoreStatisticGraphComponent() {
  const data = [
    {
      name: "1월",
      매출: 2400,
      amt: 2400,
    },
    {
      name: "2월",
      매출: 1398,
      amt: 2210,
    },
    {
      name: "3월",
      매출: 9800,
      amt: 2290,
    },
    {
      name: "4월",
      매출: 3908,
      amt: 2000,
    },
    {
      name: "5월",
      매출: 4800,
      amt: 2181,
    },
    {
      name: "6월",
      매출: 3800,
      amt: 2500,
    },
    {
      name: "7월",
      매출: 4300,
      amt: 2100,
    },
    {
      name: "8월",
      매출: 9800,
      amt: 2290,
    },
    {
      name: "9월",
      매출: 3908,
      amt: 2000,
    },
    {
      name: "10월",
      매출: 4800,
      amt: 2181,
    },
    {
      name: "11월",
      매출: 3800,
      amt: 2500,
    },
    {
      name: "12월",
      매출: 4300,
      amt: 2100,
    },
  ];

  return (
    <LineChart
      width={700}
      height={600}
      data={data}
      margin={{ top: 5, right: 30, left: 20, bottom: 5 }}
    >
      <CartesianGrid strokeDasharray="3 3" />
      <XAxis dataKey="name" />
      <YAxis />
      <Tooltip />
      <Legend />
      <Line type="monotone" dataKey="매출" stroke="#8884d8" />
    </LineChart>
  );
}

export default StoreStatisticGraphComponent;
