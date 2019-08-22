SELECT member.name FROM member, checkout_item, book WHERE checkout_item.book_id = book.id AND book.title = "The Hobbit" AND checkout_item.member_id = member.id;

