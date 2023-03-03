import { useState, useEffect } from 'react'
import reactLogo from './assets/react.svg'
import BookDropdown from './components/BookDropdown'
import './App.css'

const top3Books = [
  {
    bookName: "Markiplier - 3 Scary Games #69"
  },{
    bookName: "Hello World 2.0"
  },{
    bookName: "Snowee and Spotee - Tale of Kittens"
  }
]

function App() {
  const backendUrl = "http://localhost:8080"
  const [message, setMessage] = useState("")

  useEffect(() => {
    fetch(backendUrl + "/greeting?name=Quanya", { mode: 'cors' })
      .then((res) => res.json())
      .then((data) => setMessage(data.content));
  }, []);

  return (
    <div className="App">
      <h1>{message}</h1>
      {
        top3Books.map((book, index) => <BookDropdown key={index} id={index} bookName={book.bookName} />)
      }
    </div>
  )
}

export default App
