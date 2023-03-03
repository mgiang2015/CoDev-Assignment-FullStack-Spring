export default function BookDropdown({id, bookName}) {
  return (
  <div id={"book-item-" + id}>
    {bookName}
  </div>
  )
}
