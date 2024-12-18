SELECT * FROM libri, autori WHERE libri.id_autore = autori.id

SELECT libri.titolo, generi.genere, autori.nome FROM libri
INNER JOIN generi ON libri.id_genere = generi.id
INNER JOIN autori ON libri.id_autore = autori.id

SELECT concat(a.nome, ' ', a.cognome), l.titolo
FROM autori a 
LEFT JOIN libri l
ON a.id = l.id_autore 

SELECT concat(a.nome,' ', a.cognome), l.titolo
FROM libri l
RIGHT JOIN autori a
ON a.id = l.id_autore 

SELECT *
FROM case_editrici c
LEFT JOIN libri l
ON c.id  = l.id_casa_editrice

SELECT *
FROM libri l 
LEFT JOIN case_editrici c
ON l.id_casa_editrice= c.id

SELECT count(*), g.genere, a.cognome
FROM libri l 
JOIN generi g ON l.id_genere = g.id 
JOIN autori a ON l.id_autore = a.id 
WHERE TO_DATE(l.date, 'YYYY-MM-DD') > '2021-01-01'
GROUP BY g.genere, a.cognome 

SELECT count(*), g.genere, a.cognome
FROM libri l 
JOIN generi g ON l.id_genere = g.id 
JOIN autori a ON l.id_autore = a.id 
GROUP BY g.genere, a.cognome 
HAVING count(*) >2