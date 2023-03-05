import { useState, useEffect } from 'react'
import BookDropdown from './components/BookDropdown'
import './App.css'

function App() {
  const backendUrl = "http://localhost:8080"
  const [top3Books, setTop3Books] = useState([]);

  useEffect(() => {
    setTop3Books([
      {
        name: "Hi",
        author: "Markiplier",
        isOpen: false,
        borrowers: [
          "Jesse Pinkman",
          "Gustavo Fring",
          "Walter White"
        ]
      },{
        name: "Hello World",
        author: "C++",
        isOpen: false,
        borrowers: [
          "Jesse Pinkman",
          "Gustavo Fring",
          "Walter White"
        ]
      },{
        name: "Tale of Kittens",
        author: "yes",
        isOpen: false,
        borrowers: [
          "Jesse Pinkman",
          "Gustavo Fring",
          "Walter White"
        ]
      }
    ])
  }, []);

  const toggle = (bookIndex) => {
    const newTop3 = top3Books.map((book, index) => {
      if (index ===  bookIndex) {
        return {...book, isOpen: !book.isOpen}
      }

      return {...book, isOpen: false};
    })

    setTop3Books(newTop3);
  }

  return (
    <div className="App">
      <div id="container" className="bookContainer">
        {
          top3Books.map((book, index) => <BookDropdown key={index} id={index + 1} bookName={book.name} author={book.author} borrowers={book.borrowers} isOpen={book.isOpen} toggle={() => toggle(index)} />)
        }
      </div>
    </div>
  )
}

export default App
