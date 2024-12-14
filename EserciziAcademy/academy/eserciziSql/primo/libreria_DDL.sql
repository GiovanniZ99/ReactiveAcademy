DROP TABLE IF EXISTS librerie CASCADE;
DROP TABLE IF EXISTS libri CASCADE;
DROP TABLE IF EXISTS case_editrici CASCADE;
DROP TABLE IF EXISTS libri_librerie CASCADE;
DROP TABLE IF EXISTS autori CASCADE;
DROP TABLE IF EXISTS generi CASCADE;


CREATE TABLE librerie (
nome VARCHAR(50),
citta VARCHAR(50),
PRIMARY KEY (nome, citta)
);

-- tipi delle date meglio varchar e poi in java cambiarle per la questione del fuso oraio
CREATE TABLE libri (
id SERIAL PRIMARY KEY,
titolo varchar(50),
id_genere int NOT NULL,
id_autore int NOT NULL,
date varchar(20),
id_casa_editrice int
);

CREATE TABLE case_editrici(
id serial,
nome_casa varchar(50)
);

-- i costraint meglio metterli fuori dalla tabella
CREATE TABLE libri_librerie(
nome_libreria varchar (50),
citta_libreria varchar(50),
id_libro int,
PRIMARY KEY (nome_libreria, citta_libreria, id_libro),
FOREIGN KEY (id_libro) REFERENCES libri(id),
FOREIGN KEY (nome_libreria, citta_libreria) REFERENCES Librerie(nome, citta)
);

CREATE TABLE autori (
id serial PRIMARY KEY,
nome varchar(100),
cognome varchar(100)
);
 
 
ALTER TABLE autori ADD CONSTRAINT nome_cognome unique (nome,cognome);

CREATE TABLE generi(
id serial primary key,
genere varchar(40)
);


alter table libri ADD CONSTRAINT FK_Libri_Autore foreign key (id_autore)
references autori(id);
 
ALTER TABLE libri ADD CONSTRAINT FK_Libri_Genere FOREIGN KEY (id_genere)
REFERENCES generi (id);

create view librerie_libri_torino as
select nome_libreria, libri.*
from libri_librerie
right join libri on libri_librerie.id_libro = libri.id
where libri_librerie.citta_libreria = 'torino';
