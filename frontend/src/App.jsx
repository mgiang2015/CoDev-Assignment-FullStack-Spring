import { useState, useEffect } from 'react'
import BookDropdown from './components/BookDropdown'
import './App.css'

function App() {
  const backendUrl = "http://localhost:8080"
  const [top3Books, setTop3Books] = useState([]);
  const [hasErr, setHasErr] = useState(false);
  const [countryCode, setCountryCode] = useState({name: "SG", code: "00"})

  useEffect(() => {
    // TODO: call API with countryCode.code
    fetch(backendUrl + "/getTop3ReadBooks?country_code=" + countryCode.code)
    .then((response) => response.json())
    .then((data) => {
      if (data.message && (data.message === "no results" || data.message === "invalid parameter")) {
        console.log("No data unfortunately");
        setHasErr(true);
        return;
      }
      let nextTop3Books = [];
      for (let i = 0; i < data.length; i++) {
        nextTop3Books.push({...data[i], isOpen: false});
      }
      setTop3Books(nextTop3Books);
      setHasErr(false);
    })
    .catch((error) => setHasErr(true))
  }, [countryCode]);

  const getNextCountry = () => {
    // call API instead
    fetch(backendUrl + "/getRandomCountry")
    .then((response) => response.json())
    .then((data) => {
      var nextCountry = {name: data.country.full_name, code: data.country.country_code};
      setCountryCode(nextCountry);
    }).catch((error) => {
      setHasErr(true)
    });
  }

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
    <div>
      <div id='action-btn' className='actionBtn' onClick={getNextCountry}>
        <p>{"Get country: " + countryCode.name}</p>
      </div>
      {
        hasErr
        ?
        <div id="error-message" className="errorMessage">
          <p>No data found</p>
        </div>
        :
        <div id="container" className="bookContainer">
          {
            top3Books.map((book, index) => <BookDropdown key={index} id={index + 1} bookName={book.name} author={book.author.name} borrowers={book.borrower.map(borrower => borrower.name)} isOpen={book.isOpen} toggle={() => toggle(index)} />)
          }
        </div>
      }
    </div>
  )
}

export default App

/*
const sampleData = [
  {
    name: "Hi",
    author: "Markiplier",
    isOpen: false,
    borrower: [
      "Jesse Pinkman",
      "Gustavo Fring",
      "Walter White"
    ]
  },{
    name: "Hello World",
    author: "C++",
    isOpen: false,
    borrower: [
      "Jesse Pinkman",
      "Gustavo Fring",
      "Walter White"
    ]
  },{
    name: "Tale of Kittens",
    author: "yes",
    isOpen: false,
    borrower: [
      "Jesse Pinkman",
      "Gustavo Fring",
      "Walter White"
    ]
  }
])
*/