# Trabalho-POO2
reserva laboratorio


insert into Funcoes
(Cod_funcoes,nomeFuncao)
values(1,'Administrador'),(2,'Professor'),(3,'Portaria')


insert into Funcionarios
(Cod_funcio,nomeFuncionario,ra,senha,funcao_Cod_funcoes)
values(1,'Felipe Vieira','123','123',1)

insert into Funcionarios
(Cod_funcio,nomeFuncionario,ra,senha,funcao_Cod_funcoes)
values(2,'Sr Professor','111','111',2)

insert into Funcionarios
(Cod_funcio,nomeFuncionario,ra,senha,funcao_Cod_funcoes)
values(3,'Sr Portaria','222','222',3)

insert into chaves
(cod_chaves,descrichave)
values(1,'Chave na portaria'),(2,'Chave com o professor')
