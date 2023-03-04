import "./BookDropdown.css"

export default function BookDropdown({id, bookName, author}) {
  return (
  <div id={"book-item-" + id} className="BookItem">
    <span className="id">{id}</span>
    <span className="name">{bookName}</span>
    <div className="toggleContainer">
      <div className="book-toggle" />
    </div>
    <span className="author">{"by " + author}</span>
  </div>
  )
}
