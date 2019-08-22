SELECT DISTINCT movie.title 
FROM movie
WHERE NOT EXISTS
(
SELECT NULL
FROM checkout_item
WHERE checkout_item.movie_id = movie.id
);

SELECT DISTINCT book.title
FROM book
WHERE NOT EXISTS
(
SELECT NULL
FROM checkout_item
WHERE checkout_item.book_id = book.id
);
