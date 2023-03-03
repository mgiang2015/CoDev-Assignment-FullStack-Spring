import { useState, useEffect } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'

function App() {
  const backendUrl = "http://localhost:8080"
  const [message, setMessage] = useState("")

  useEffect(() => {
    fetch(backendUrl + "/greeting", { mode: 'cors' })
      .then((res) => res.json())
      .then((data) => setMessage(data.content));
  }, []);

  return (
    <div className="App">
      <h1>{message}</h1>
    </div>
  )
}

export default App
