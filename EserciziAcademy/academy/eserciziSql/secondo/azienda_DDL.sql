DROP TABLE IF EXISTS clienti;
DROP TABLE IF EXISTS fatture;
DROP TABLE IF EXISTS prodotti;
DROP TABLE IF EXISTS fornitori;

CREATE TABLE clienti (
numero_cliente serial PRIMARY KEY,
nome varchar (50),
cognome varchar(50),
data_nascita char(10),
regione_residenza varchar(50)
);

CREATE TYPE enum_fattura AS ENUM ('A','Servizio','Vendita');
CREATE TABLE fatture(
numero_fattura serial PRIMARY KEY,
tipo_fattura enum_fattura,
-- int o integer sono uguali in postgres
importo integer,
iva float,
id_cliente integer,
data_fattura char(10),
numero_fornitore integer
);


CREATE TABLE prodotti(
id_prodotto serial PRIMARY KEY,
descrizione varchar (255),
in_produzione boolean,
in_commercio boolean,
data_attivazione char(10),
data_disattivazione char(10)
);

CREATE TABLE fornitori( 
numero_fornitore serial primary key,
denominazione varchar (255),
regione_residenza varchar(30)
);