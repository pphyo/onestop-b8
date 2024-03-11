insert into ledger (name, type) values ('Awards', 'Credit');
insert into ledger (name, type) values ('Lottery', 'Credit');
insert into ledger (name, type) values ('Salary', 'Credit');
insert into ledger (name, type) values ('Refunds', 'Credit');
insert into ledger (name, type) values ('Grants', 'Credit');

insert into ledger (name, type) values ('Bills', 'Debit');
insert into ledger (name, type) values ('Food', 'Debit');
insert into ledger (name, type) values ('Health', 'Debit');
insert into ledger (name, type) values ('Sport', 'Debit');
insert into ledger (name, type) values ('Transportation', 'Debit');

insert into ledger_tag (ledger_id, tag) values (2, 'Nway Oo Lottery');
insert into ledger_tag (ledger_id, tag) values (3, 'JDC Salary');
insert into ledger_tag (ledger_id, tag) values (1, 'Freelance Development');
insert into ledger_tag (ledger_id, tag) values (6, 'Mobile Phone Bills');
insert into ledger_tag (ledger_id, tag) values (9, 'Soccer Stadium Ticket Fees');