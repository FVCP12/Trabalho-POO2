package ReservaLab.TrabalhoPOO2.Controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ReservaLab.TrabalhoPOO2.DAO.*;
import ReservaLab.TrabalhoPOO2.Model.*;
import javax.annotation.PostConstruct;

@Controller
public class InicioController {
    
    @Autowired
    FuncaoDao funcaoDao;
    
    @Autowired
    ChavesDao chavesDao;
    
    /*
    @PostConstruct
    public void init(){
        
        List<Funcoes> fun = funcaoDao.PegarTodos(Funcoes.class);
        List<Chaves> cha = chavesDao.PegarTodos(Chaves.class);
        
        
        if(fun.isEmpty()){
            
            Funcoes fAdmin = new Funcoes();
            Funcoes fProf = new Funcoes();
            Funcoes fPort = new Funcoes();
            
            fAdmin.setCod_funcoes(1);
            fAdmin.setNomeFuncao("Administrador");
           
            fProf.setCod_funcoes(2);
            fProf.setNomeFuncao("Professor");
            
            fPort.setCod_funcoes(3);
            fPort.setNomeFuncao("Portaria");
            
            funcaoDao.criar(fAdmin);
            funcaoDao.criar(fProf);
            funcaoDao.criar(fPort);
            
        }
        if(cha.isEmpty()){
            
           Chaves chaPor = new Chaves();
           Chaves chaPro = new Chaves();
           
           chaPor.setCod_chaves(1);
           chaPor.setDescriChave("Chave na portaria");
           
           chaPro.setCod_chaves(2);
           chaPro.setDescriChave("Chave com o professor");
            
        }
        
    }
    */
    
}
