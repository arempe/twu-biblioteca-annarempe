SELECT member.name,  COUNT(checkout_item.member_id)
FROM  member, checkout_item
WHERE member.id = checkout_item.member_id 
GROUP BY checkout_item.member_id
HAVING COUNT(checkout_item.member_id)>1;
