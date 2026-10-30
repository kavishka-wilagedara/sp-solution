import { useEffect, useState } from "react";
import { getAllNews } from "./api";

export default function NewsList({ onSelect }) {
  const [newsList, setNewsList] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    getAllNews()
      .then(setNewsList)
      .catch((e) => setError(e.message));
  }, []);

  if (error) return <p>Error: {error}</p>;
  if (newsList.length === 0) return <p>No news found.</p>;

  return (
    <div>
      {newsList.map((news) => (
        <div key={news.id} onClick={() => onSelect(news.id)} style={{ cursor: "pointer" }}>
          <h3>[{news.category}] by {news.author}</h3>
          <p>{news.content.slice(0, 100)}...</p>
          <hr />
        </div>
      ))}
    </div>
  );
}