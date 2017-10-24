package ReservaLab.TrabalhoPOO2.Controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ReservaLab.TrabalhoPOO2.DAO.FuncionariosDao;
import ReservaLab.TrabalhoPOO2.Model.Funcionarios;

@Controller
public class FuncionarioControle {
 
    @Autowired
    private FuncionariosDao funcionarioDao;//usando para acessar o banco
     
    
    @GetMapping("/logar")//mapeando a tela logar 
    public String logar(Model model) {
        
    	model.addAttribute("funcio", new Funcionarios());//cria no html uma variavel do tipo funcionario
        
        return "logar";
    }
    //////////////////////////
   
    ////////////////////////////////
    
    @PostMapping("/logando")//mapeando o formulario logando 
    public String logandoSistema(
    		@ModelAttribute Funcionarios funcionario, 
    		Model model) {
        
        
        try{//encontrando a tela certa para abrir
            Funcionarios f =funcionarioDao.buscarFuncionario(funcionario.getRa(),funcionario.getSenha());
                      
            if(f.getFuncao().getCod_funcoes() == 1){               
                return "adminIni"; 
            }else if(f.getFuncao().getCod_funcoes() == 2){           
                return "professorIni";               
            }else if(f.getFuncao().getCod_funcoes() == 3){              
                return "portariaIni";                
            }        
        }catch(Exception e){
            model.addAttribute("menssagem", 
    			"Erro ao conectar, verifique sua senha ou usuario!");
                 }
     
              return "logar";
    }
    
    ////////////////////////////////////////
    
     @GetMapping("/adminIni")
    public String adminLogin( Model model){
        
        return "adminIni";
    }
    
    @GetMapping("/portariaIni")
    public String portariaLogin( Model model){
        
        return "portariaIni";
    }
    
    @GetMapping("/professorIni")
    public String professorLogin( Model model){
        
        return "professorIni";
    }
    
}
