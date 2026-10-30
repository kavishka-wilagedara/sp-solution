import { useEffect, useState } from "react";
import { getNews, getComments, addComment } from "./api";

export default function NewsDetail({ newsId, onBack }) {
  const [news, setNews] = useState(null);
  const [comments, setComments] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [name, setName] = useState("");
  const [content, setContent] = useState("");
  const [error, setError] = useState("");

  useEffect(() => {
    getNews(newsId).then(setNews).catch((e) => setError(e.message));
    getComments(newsId).then(setComments).catch((e) => setError(e.message));
  }, [newsId]);

  async function handleSubmit(event) {
    event.preventDefault();
    setError("");
    try {
      await addComment(newsId, name, content);
      setComments(await getComments(newsId)); // reload the list
      setName("");
      setContent("");
      setShowForm(false);
    } catch (e) {
      setError(e.message);
    }
  }

  if (!news) return <p>{error || "Loading..."}</p>;

  return (
    <div>
      <button onClick={onBack}>← Back</button>

      <div>
        <h2>[{news.category}] by {news.author}</h2>
        <p>{news.content}</p>
        <small>Updated: {news.updatedAt}</small>
      </div>

      <hr />
      <h3>Comments ({comments.length})</h3>

      {comments.length === 0 && <p>No comments yet.</p>}
      {comments.map((c) => (
        <p key={c.id}>
          <b>{c.name}:</b> {c.content}
        </p>
      ))}

      {error && <p>Error: {error}</p>}

      {!showForm ? (
        <button onClick={() => setShowForm(true)}>Add comment</button>
      ) : (
        <form onSubmit={handleSubmit}>
          <div>
            <input
              placeholder="Your name"
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
          </div>
          <div>
            <textarea
              placeholder="Your comment"
              value={content}
              onChange={(e) => setContent(e.target.value)}
            />
          </div>
          <button type="submit">Submit</button>
          <button type="button" onClick={() => setShowForm(false)}>Cancel</button>
        </form>
      )}
    </div>
  );
}