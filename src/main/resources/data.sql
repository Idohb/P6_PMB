insert into person(first_name,last_name,login_id,bank_id) values('Bodivann','KHEK',1,1);
insert into person(first_name,last_name,login_id,bank_id) values('tata','toto',2,2);
insert into person(first_name,last_name,login_id,bank_id) values('tutu','titi',3,3);
commit;


insert into login(email,password,person_id) values('bodivann@mail.com','letmeinbodi',1);
insert into login(email,password,person_id) values('tata@mail.com','letmeintata',2);
insert into login(email,password,person_id) values('tutu@mail.com','letmeintutu',3);
commit;

insert into social_networks(this_person,is_related_to) values(2,1);
commit;

insert into bank(iban,amount,person_id) values(1,3000,1);
insert into bank(iban,amount,person_id) values(2,2500,2);
insert into bank(iban,amount,person_id) values(3,2000,3);
commit;