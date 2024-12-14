SELECT concat(c.nome,' ', c.cognome)
FROM clienti c
WHERE c.data_nascita LIKE '1982%';

SELECT concat(c.nome, '-', c.cognome) AS Denominazione 
FROM clienti c
WHERE c.regione_residenza ='Lombardia';

SELECT count(f.*) AS numero_fatture_22
FROM fatture f 
WHERE f.iva =22;

SELECT substring (f.data_fattura,1, 4), count(f.*) AS conteggio_fatture, sum(f.importo) AS somma_importi
FROM fatture f 
GROUP BY substring(f.data_fattura,1, 4);

SELECT *
FROM prodotti p 
WHERE p.data_attivazione LIKE '2017%'
AND p.in_commercio IS TRUE 
OR p.in_produzione IS TRUE;

SELECT substring(f.data_fattura,1, 4), COUNT(*) AS conteggio_fatture
FROM fatture f
WHERE f.iva = 22
GROUP BY substring(f.data_fattura,1, 4);

SELECT substring(f.data_fattura,1, 4) AS anno, count(*) AS conteggio_fatture
FROM fatture f 
WHERE f.tipo_fattura = 'A'
GROUP BY substring(f.data_fattura,1, 4)
HAVING count(*) >2;

SELECT fo.denominazione, f.numero_fattura, f.importo, f.iva, f.data_fattura
FROM fatture f 
JOIN fornitori fo ON f.numero_fornitore = fo.numero_fornitore;

SELECT sum(f.importo), c.regione_residenza 
FROM fatture f 
JOIN clienti c ON f.id_cliente = c.numero_cliente
GROUP BY c.regione_residenza;

-- il numero_cliente o la somma?
SELECT  c.numero_cliente
FROM clienti c 
JOIN fatture f ON c.numero_cliente = f.id_cliente 
WHERE f.importo >50 AND c.data_nascita LIKE '1982%';
