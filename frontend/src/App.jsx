import { useState, useEffect } from 'react'
import BookDropdown from './components/BookDropdown'
import './App.css'

const top3Books = [
  {
    bookName: "Hi",
    author: "Markiplier"
  },{
    bookName: "Hello World",
    author: "C++"
  },{
    bookName: "Tale of Kittens",
    author: "yes"
  }
]

function App() {
  const backendUrl = "http://localhost:8080"
  const [message, setMessage] = useState("HELLO EVERYONE")

  return (
    <div className="App">
      <div id="container" className="bookContainer">
        {
          top3Books.map((book, index) => <BookDropdown key={index} id={index + 1} bookName={book.bookName} author={book.author} />)
        }
      </div>
    </div>
  )
}

export default App
