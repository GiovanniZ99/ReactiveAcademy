INSERT INTO clienti (numero_cliente, nome, cognome, data_nascita, regione_residenza)
VALUES
    (1, 'Marco', 'Rossi', '1990-05-15', 'Lombardia'),
    (2, 'Anna', 'Bianchi', '1980-09-20', 'Toscana'),
    (3, 'Luca', 'Verdi', '1995-03-10', 'Lazio'),
    (4, 'Maria', 'Russo', '1982-11-02', 'Sicilia'),
    (5, 'Giuseppe', 'Ferrari', '1992-07-25', 'Veneto'),
    (6, 'Laura', 'Gallo', '1982-12-18', 'Piemonte'),
    (7, 'Andrea', 'Conti', '1994-02-14', 'Campania'),
    (8, 'Simona', 'Barbieri', '1980-06-30', 'Emilia-Romagna'),
    (9, 'Davide', 'Santoro', '1980-04-05', 'Calabria'),
    (10, 'Elena', 'Marini', '1982-08-12', 'Abruzzo'),
    (14, 'Sara', 'Ferrari', '1982-03-05', 'Veneto'),
    (15, 'Antonio', 'Marini', '1980-08-18', 'Campania'),
    (16, 'Aldo', 'Franzi', '1982-08-18', 'Campania'),
    (17, 'Mario', 'Pintone', '1980-08-18', 'Campania'),
    (18, 'Luisa', 'Gallo', '1982-11-22', 'Piemonte');
 
INSERT INTO fornitori (numero_fornitore, denominazione, regione_residenza)
VALUES
    (201, 'Fornitore A', 'Lombardia'),
    (202, 'Fornitore B', 'Toscana'),
    (203, 'Fornitore C', 'Lazio');
INSERT INTO fatture (numero_fattura, tipo_fattura, importo, iva, id_cliente, data_fattura, numero_fornitore)
VALUES
    (101, 'A', 150.00, 22, 1, '2017-09-01', 201),
    (102, 'Servizio', 80.50, 22, 2, '2023-09-02', 202),
    (103, 'A', 200.75, 10, 3, '2021-09-03', 203),
    (104, 'Servizio', 50.25, 22, 4, '2017-09-04', 201),
    (105, 'A', 120.00, 10, 5, '2013-09-05', 202),
    (106, 'Servizio', 90.75, 22, 6, '2012-09-06', 203),
    (107, 'Vendita', 175.50, 10, 7, '2019-09-07', 201),
    (108, 'A', 60.25, 22, 8, '2020-09-08', 202),
    (109, 'Vendita', 95.00, 22, 9, '2011-09-09', 203),
    (110, 'Vendita', 95.00, 22, 10, '2011-09-02', 201),
    (111, 'Vendita', 55.00, 22, 17, '2010-09-07', 202),
    (112, 'A', 25.30, 22, 17, '2015-10-10', 203),
    (113, 'Vendita', 21.80, 22, 17, '2012-01-02', 201),
    (114, 'Vendita', 14.00, 10, 10, '2013-12-12', 202),
    (115, 'Vendita', 76.15, 22, 14, '2011-01-01', 203),
    (116, 'A', 85.00, 10, 16, '2018-02-01', 203),
    (117, 'Vendita', 195.00, 22, 18, '2013-01-01', 201),
    (118, 'A', 45.75, 22, 10, '2005-09-10', 201);
 
INSERT INTO prodotti (id_prodotto, descrizione, in_produzione, in_commercio, data_attivazione, data_disattivazione)
VALUES
    (1001, 'Prodotto A', true, true, '2021-01-15', NULL),
    (1002, 'Prodotto B', false, true, '2023-02-20', NULL),
    (1003, 'Prodotto C', false, false, '2023-03-10', '2023-08-31'),
    (1004, 'Prodotto D', true, true, '2023-04-05', NULL),
    (1005, 'Prodotto E', false, true, '2023-05-12', NULL),
    (1011, 'Prodotto K', true, true, '2017-02-15', NULL),
    (1012, 'Prodotto L', false, true, '2017-06-20', '2023-08-31'),
    (1013, 'Prodotto M', true, true, '2017-09-10', NULL),
    (1006, 'Prodotto F', true, false, '2023-06-20', '2023-09-05'),
    (1007, 'Prodotto G', false, false, '2023-07-25', NULL),
    (1008, 'Prodotto H', false, false, '2023-08-10', NULL),
    (1009, 'Prodotto I', true, true, '2023-09-01', NULL),
    (1010, 'Prodotto J', true, false, '2023-09-05', '2023-09-30');