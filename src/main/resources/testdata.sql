drop table if exists publicationauthor;
drop table if exists publication;
drop table if exists author;

create table author(
 id uuid primary key,
 firstname varchar(30) not null,
 lastname varchar(30) not null
);

create table publication(
  id varchar(13) primary key,
  publicationtype smallint not null,
  title varchar(100) not null unique,
  description varchar(300) not null,
  price decimal(18,2),
  currency char(5),
  pages smallint,
  url varchar(100),
  publishingDate timestamp not null
);

create table publicationauthor(
  authorID uuid not null,
  publicationID varchar(13) not null,
  primary key (authorID, publicationID),
  foreign key (authorID) references author(id),
  foreign key (publicationID) references publication(id),
);

insert into author (id,firstname,lastname)
values ('e2b3ccc2-dfe4-447b-8996-06a0b3272149', 'Barney', 'Geroellheimer');

insert into author (id,firstname,lastname)
values ('5f2e352e-a391-46fe-9a60-865cd615d6eb', 'Fred', 'Feuerstein');

insert into author (id,firstname,lastname)
values ('6e723a3c-bd25-471c-8d1c-6c019b36d357', 'Sheev', 'Palpatine');

insert into publication (id,publicationtype,title,description,price,currency,pages,url,publishingDate)
values ('9783866801929', 1, 'The Tragedy of Darth Plagueis The Wise', 'Darth Plagueis was a Dark Lord of the Sith, so powerful and so wise he could use the Force to influence the midichlorians to create life',999.99,'GCRDT',999,null,'1970-01-01 00:00:42');

insert into publication (id,publicationtype,title,description,price,currency,pages,url,publishingDate)
values ('7893865801929', 2, 'Beautiful places in Arkanstone', 'Fred and Barney taking a tour to the most beautiful places in Arkanstone',null,null,null,'https://flintstones.fandom.com/wiki/Arkanstone','1994-06-01 17:21:01');

insert into publication (id,publicationtype,title,description,price,currency,pages,url,publishingDate)
values ('4563865801239', 1, 'Rockbottom "Rocky/Stony" Flintstone', 'Biography of an veteran of stone world one',5.0,'STONE',1,null,'2010-12-24 12:12:12');

insert into publicationauthor (authorID, publicationID)
values ('6e723a3c-bd25-471c-8d1c-6c019b36d357','9783866801929');

insert into publicationauthor (authorID, publicationID)
values ('e2b3ccc2-dfe4-447b-8996-06a0b3272149','7893865801929');

insert into publicationauthor (authorID, publicationID)
values ('5f2e352e-a391-46fe-9a60-865cd615d6eb','7893865801929');

insert into publicationauthor (authorID, publicationID)
values ('5f2e352e-a391-46fe-9a60-865cd615d6eb','4563865801239');