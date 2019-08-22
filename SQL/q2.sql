SELECT DISTINCT member.name FROM member, book, movie, checkout_item WHERE member.id NOT IN (SELECT DISTINCT checkout_item.member_id FROM checkout_item);
