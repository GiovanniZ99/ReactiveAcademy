INSERT INTO case_editrici (id, nome_casa) VALUES
(1, 'Mondadori'),
(2, 'Rizzoli'),
(3, 'Feltrinelli');

INSERT INTO autori (nome, cognome) VALUES
('Italo', 'Calvino'),
('Dante', 'Alighieri'),
('Virginia', 'Woolf'),
('Gabriel', 'Garcia Marquez');


INSERT INTO generi (genere) VALUES
('Narrativa'),
('Poesia'),
('Saggistica'),
('Fantasy'),
('Storico');

INSERT INTO librerie (nome, citta) VALUES
('Libreria Feltrinelli', 'Torino'),
('Libreria Mondadori', 'Milano'),
('Libreria Rizzoli', 'Torino'),
('Libreria Universitaria', 'Roma');


INSERT INTO libri (titolo, id_genere, id_autore, date, id_casa_editrice) VALUES
('Le città invisibili', 6, 5, '1972-01-01', 1),
('La Divina Commedia', 7, 6, '1320-01-01', 2),
('Mrs Dalloway', 8, 7, '1925-05-14', 3),
('Centanni di solitudine', 9, 8, '1967-06-05', 3);

INSERT INTO libri_librerie (nome_libreria, citta_libreria, id_libro) VALUES
('Libreria Feltrinelli', 'Torino', 5),
('Libreria Mondadori', 'Milano', 6),
('Libreria Rizzoli', 'Torino', 7),
('Libreria Universitaria', 'Roma', 8),
('Libreria Feltrinelli', 'Torino', 8);