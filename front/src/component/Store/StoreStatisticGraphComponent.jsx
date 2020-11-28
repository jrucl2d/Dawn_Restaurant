import React, { useEffect, useState } from "react";
import {
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  LineChart,
  Line,
} from "recharts";

function StoreStatisticGraphComponent({ orders }) {
  const [data, setData] = useState([]);

  useEffect(() => {
    const thisYear = new Date().toString().split(" ")[3];
    const calData = [
      {
        name: "1월",
        매출: 0,
        amt: 0,
      },
      {
        name: "2월",
        매출: 0,
        amt: 0,
      },
      {
        name: "3월",
        매출: 0,
        amt: 0,
      },
      {
        name: "4월",
        매출: 0,
        amt: 0,
      },
      {
        name: "5월",
        매출: 0,
        amt: 0,
      },
      {
        name: "6월",
        매출: 0,
        amt: 0,
      },
      {
        name: "7월",
        매출: 0,
        amt: 0,
      },
      {
        name: "8월",
        매출: 0,
        amt: 0,
      },
      {
        name: "9월",
        매출: 0,
        amt: 0,
      },
      {
        name: "10월",
        매출: 0,
        amt: 0,
      },
      {
        name: "11월",
        매출: 0,
        amt: 0,
      },
      {
        name: "12월",
        매출: 0,
        amt: 0,
      },
    ];
    orders.forEach((order) => {
      if (
        order.timestamp.split("-")[0] === thisYear &&
        (order.orderStatus === "판매 완료" || order.orderStatus === "음식 수령")
      ) {
        calData[+order.timestamp.split("-")[1] - 1]["매출"] += order.total;
      }
    });
    setData(calData);
  }, []);

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
