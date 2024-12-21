insert into "user" (id, first_name, last_name, sur_name, status)
values ('1','Иванов','Иван','Иванович','ADMIN'),
       ('2','Петров','Петр','Петрович','USER'),
       ('3','Макаров','Макар','Макарович','USER'),
       ('4','Олегов','Олег','Олегович','SUPPORT');

insert into filter (id, user_id, types_ids)
values ('1','2','{1, 3, 5}'),
       ('2','3','{}');

insert into stats (id, filter_id, author_id, title, statistics, created_at, status)
values ('1','1','1','Покупка','-100 рублей',now(),'UNREAD'),
       ('2','1','2','Онигири','-120 рублей',now(),'UNREAD'),
       ('3','2','3','Квартира','-650 миллионов рублей',now(),'UNREAD'),
       ('5','2','4','Зарплата','+40 тысяч рублей',now(),'UNREAD');
