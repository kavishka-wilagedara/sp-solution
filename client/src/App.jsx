import { useState } from "react";
import NewsList from "./NewsList";
import NewsDetail from "./NewsDetail";

export default function App() {
  const [selectedId, setSelectedId] = useState(null);

  return (
    <div>
      <h1>News</h1>
      {selectedId === null ? (
        <NewsList onSelect={setSelectedId} />
      ) : (
        <NewsDetail newsId={selectedId} onBack={() => setSelectedId(null)} />
      )}
    </div>
  );
}