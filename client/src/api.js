const BASE = "/api/v1/news";

async function request(url, options) {
  const response = await fetch(url, options);

  if (!response.ok) {
    const error = await response.json().catch(() => ({}));
    // backend error message
    throw new Error(Object.values(error).join(", ") || "Request failed");
  }
  return response.json();
}

export function getAllNews() {
  return request(BASE);
}

export function getNews(id) {
  return request(`${BASE}/${id}`);
}

export function getComments(newsId) {
  return request(`${BASE}/${newsId}/comments`);
}

export function addComment(newsId, name, content) {
  return request(`${BASE}/${newsId}/comments`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ name, content }),
  });
}