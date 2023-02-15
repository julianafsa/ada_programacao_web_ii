INSERT INTO categorias (nome) VALUES ('Biografia');
INSERT INTO categorias (nome) VALUES ('Conto');
INSERT INTO categorias (nome) VALUES ('Crônica');
INSERT INTO categorias (nome) VALUES ('Ensaio');
INSERT INTO categorias (nome) VALUES ('Ficção');
INSERT INTO categorias (nome) VALUES ('Poesia');
INSERT INTO categorias (nome) VALUES ('Terror');

INSERT INTO editoras (nome, descricao) VALUES ('Companhia da Letras', 'Editora Companhia da Letras');
INSERT INTO editoras (nome, descricao) VALUES ('BestSeller', 'BestSeller');
INSERT INTO editoras (nome, descricao) VALUES ('Globo Livros', 'Editora Globo Livros');
INSERT INTO editoras (nome, descricao) VALUES ('Darkside Books', 'Editora Darkside Books');
INSERT INTO editoras (nome, descricao) VALUES ('Editora Rocco', 'Editora Rocco');
INSERT INTO editoras (nome, descricao) VALUES ('Aleph', 'Editora Aleph');

INSERT INTO livros (nome, isbn, categoria_id, editora_id) VALUES ('Meu irmão Alemão', '111111111111', '1', '1');
INSERT INTO livros (nome, isbn, categoria_id, editora_id) VALUES ('Ideias para adiar o fim do mundo', '111111111112', '5', '1');
INSERT INTO livros (nome, isbn, categoria_id, editora_id) VALUES ('Em busca de mim', '111111111113', '1', '2');

INSERT INTO perfis (nome, descricao) VALUES ('ROLE_ADMIN', 'Perfil de administrador');
INSERT INTO perfis (nome, descricao) VALUES ('ROLE_USER', 'Perfil de usuário comum');

INSERT INTO usuarios (username, password, nome, email, perfil_id) VALUES ('admin', '$2a$10$OgLNbKcqUJXp1cWd.YRBgOkIPqygEDNjZR1Wf10VCRW4acJlO4XSW', 'Juliana', 'julianafsa@gmail.com', '1');