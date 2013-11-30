# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table animal (
  chip_number               varchar(255) not null,
  birth_date                varchar(255),
  specie                    varchar(255),
  name                      varchar(255),
  constraint pk_animal primary key (chip_number))
;

create table employee (
  contact                   varchar(255) not null,
  name                      varchar(255),
  last_name                 varchar(255),
  birth_date                varchar(255),
  constraint pk_employee primary key (contact))
;

create table feed (
  name                      varchar(255) not null,
  amount                    integer,
  minimum                   integer,
  constraint pk_feed primary key (name))
;


create table animal_employee (
  animal_chip_number             varchar(255) not null,
  employee_contact               varchar(255) not null,
  constraint pk_animal_employee primary key (animal_chip_number, employee_contact))
;

create table animal_feed (
  animal_chip_number             varchar(255) not null,
  feed_name                      varchar(255) not null,
  constraint pk_animal_feed primary key (animal_chip_number, feed_name))
;

create table employee_animal (
  employee_contact               varchar(255) not null,
  animal_chip_number             varchar(255) not null,
  constraint pk_employee_animal primary key (employee_contact, animal_chip_number))
;
create sequence animal_seq;

create sequence employee_seq;

create sequence feed_seq;




alter table animal_employee add constraint fk_animal_employee_animal_01 foreign key (animal_chip_number) references animal (chip_number) on delete restrict on update restrict;

alter table animal_employee add constraint fk_animal_employee_employee_02 foreign key (employee_contact) references employee (contact) on delete restrict on update restrict;

alter table animal_feed add constraint fk_animal_feed_animal_01 foreign key (animal_chip_number) references animal (chip_number) on delete restrict on update restrict;

alter table animal_feed add constraint fk_animal_feed_feed_02 foreign key (feed_name) references feed (name) on delete restrict on update restrict;

alter table employee_animal add constraint fk_employee_animal_employee_01 foreign key (employee_contact) references employee (contact) on delete restrict on update restrict;

alter table employee_animal add constraint fk_employee_animal_animal_02 foreign key (animal_chip_number) references animal (chip_number) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists animal;

drop table if exists animal_employee;

drop table if exists animal_feed;

drop table if exists employee;

drop table if exists employee_animal;

drop table if exists feed;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists animal_seq;

drop sequence if exists employee_seq;

drop sequence if exists feed_seq;

