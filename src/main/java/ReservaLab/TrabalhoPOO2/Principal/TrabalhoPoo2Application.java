package ReservaLab.TrabalhoPOO2.Principal;

import ReservaLab.TrabalhoPOO2.DAO.*;
import ReservaLab.TrabalhoPOO2.Model.*;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"ReservaLab.TrabalhoPOO2.Principal", "ReservaLab.TrabalhoPOO2.Model",
    "ReservaLab.TrabalhoPOO2.DAO", "ReservaLab.TrabalhoPOO2.Controller"})
@EnableAutoConfiguration
public class TrabalhoPoo2Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context
                = SpringApplication.run(TrabalhoPoo2Application.class);
/*
        FuncionariosDao funcionariosDao = context.getBean(FuncionariosDao.class);
        ChavesDao chavesDao = context.getBean(ChavesDao.class);
        FuncaoDao funcaoDao = context.getBean(FuncaoDao.class);

        List<Funcoes> Todofuncao = funcaoDao.PegarTodos(Funcoes.class);
        List<Funcionarios> Todofuncionarios = funcionariosDao.PegarTodos(Funcionarios.class);
        List<Chaves> Todochaves = chavesDao.PegarTodos(Chaves.class);

        if (Todofuncao.isEmpty()) {

            Funcoes f = new Funcoes();

            f.setCod_funcoes(1);
            f.setNomeFuncao("Administrador");
            
            funcaoDao.criar(f);
            
            f = new Funcoes();

            f.setCod_funcoes(2);
            f.setNomeFuncao("Professor");
            
            funcaoDao.criar(f);
            
            f = new Funcoes();

            f.setCod_funcoes(3);
            f.setNomeFuncao("Portaria");
            
            funcaoDao.criar(f);
            
        }
        
        if(Todochaves.isEmpty()){
            
            Chaves ch = new Chaves();
            
            ch.setCod_chaves(1);
            ch.setDescriChave("Chave na portaria");
            
            ch = new Chaves();
            
            ch.setCod_chaves(2);
            ch.setDescriChave("Chave com o professor");
            
           
        }

        if(Todofuncionarios.isEmpty()){
            
            Funcionarios fun = new Funcionarios();
            
            fun.setCod_funcio(1);
            fun.setNomeFuncionario("Felipe Vieira");
            fun.setRa("123");
            fun.setSenha("123");
            fun.setFuncao(funcaoDao.buscaId(Funcoes.class, 1));
            
            funcionariosDao.criar(fun);
        }
        */
        /*
        
insert into Funcionarios
(Cod_funcio,nomeFuncionario,ra,senha,funcao_Cod_funcoes)
values(1,'Felipe Vieira','123','123',1)

insert into Funcionarios
(Cod_funcio,nomeFuncionario,ra,senha,funcao_Cod_funcoes)
values(2,'Sr Professor','111','111',2)

insert into Funcionarios
(Cod_funcio,nomeFuncionario,ra,senha,funcao_Cod_funcoes)
values(3,'Sr Portaria','222','222',3)
   
         */
    }

}
