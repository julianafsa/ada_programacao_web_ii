# ada_programacao_web_ii
Projeto de Programação Web II - Curso Ada + F1rst

Foram criadas três entidades principais: livro, categoria e editora. 

A entidade editora tem os seguintes atributos:
id - Long
nome - String - Máx 255 caracteres
descricao - String - Não obrigatório

A entidade categoria tem os seguintes atributos:
id - Long
nome - String - Máx 100 caracteres

A entidade livro tem os seguintes atributos:
id - Long
editora_id - Long - relacionamento com a entidade editora
categoria_id - Long - relacionamento com a entidade categoria
nome - String
isbn - String - Máx 13 caracteres

Com base nas entidades acima, foram realizadas as seguintes tarefas:
1 - Criação do CRUD de cada entidade, possuindo os controllers, services, repositories, entities e DTOs.
2 - Criação de um endpoint específico para buscar os livros por categoria.
3 - Criação de um endpoint específico para buscar os livros por editora.
4 - Criação de um endpoint para buscar o livro pelo nome ou pelo número isbn ou pelos dois, utilizando query dsl.
Bônus
Criação da parte de autenticação de usuário e criação de endpoints para salvar e buscar os livros favoritos do usuário logado, bem como a criação de um endpoint para excluir um livro favoritado.
