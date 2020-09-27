--create database alcohol;
--create user alcoholuser with encrypted password 'wasted';
grant all privileges on database alcohol to alcoholuser;

CREATE EXTENSION pgcrypto;

SET TIME ZONE 'UTC';


create table if not exists appUser
(
	id serial not null,
	userName varchar(256),
	firstName varchar(256),
	lastName varchar(256),
	email varchar(320),
	role varchar(100),
	globalAdmin boolean,
	salt text,
	hashpass text
);
alter table appUser owner to alcoholuser;
--create admin    password:letmein
insert into appUser ( userName, firstName,lastName,role,email,globalAdmin,salt,hashpass) VALUES ( 'Admin','alcohol', 'corona', 'ADMIN',  'app@gmail.com', 'True','$2a$06$2yPYDtOx9ymihhmBF0tcF.','$2a$06$2yPYDtOx9ymihhmBF0tcF.kPO0fLVLpp0wKQr82DaXm8ZheJOa2PC');

create table if not exists fhirPatient
(
	id integer,
	fhirId integer
);
alter table fhirPatient owner to alcoholuser;

commit;