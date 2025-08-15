// src/App.tsx
import { useEffect, useState } from "react";
const API = import.meta.env.VITE_API_URL || "http://localhost:8080";
export default function App() {
  const [msg,setMsg]=useState("...");
  useEffect(()=>{ fetch(`${API}/api/hello`).then(r=>r.json()).then(d=>setMsg(d.msg)); },[]);
  return <div style={{padding:24}}>
    <h1>React + Spring + Mongo</h1>
    <p>{msg}</p>
  </div>;
}
