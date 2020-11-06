CREATE TABLE IF NOT EXISTS feed(
  id serial PRIMARY KEY,
  title TEXT NOT NULL,
  description TEXT NOT NULL,
  pubdate TEXT NOT NULL,
  image TEXT NOT NULL);