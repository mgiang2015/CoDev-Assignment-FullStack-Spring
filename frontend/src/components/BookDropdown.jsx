import "./BookDropdown.css"

export default function BookDropdown({id, bookName, author, borrowers, isOpen, toggle}) {
  return (
  <div className="bookAndChildrenContainer">
    <div id={"book-item-" + id} className="BookItem">
      <span className="id">{id}</span>
      <span className="name">{bookName}</span>
      <div className="toggleContainer">
        <div className="book-toggle" onClick={toggle} />
      </div>
      <span className="author">{"by " + author}</span>
    </div>
    {
      isOpen 
      ?
      borrowers.map(name => {
        return <p className="customer">{name}</p>
      })
      : null
    }
  </div>
  )
}
