INSERT INTO book (id, title)
VALUES (11, "The Pragmatic Programmer");

INSERT INTO member(id, name)
VALUES (43, "Anna Rempe");

INSERT INTO checkout_item (member_id, book_id)
VALUES (43, 11);

SELECT member.name 
FROM member, checkout_item, book 
WHERE checkout_item.book_id = book.id 
AND book.title = "The Pragmatic Programmer" 
AND checkout_item.member_id = member.id;


