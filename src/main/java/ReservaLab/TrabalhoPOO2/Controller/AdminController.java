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
import ReservaLab.TrabalhoPOO2.DAO.LaboratorioDao;
import ReservaLab.TrabalhoPOO2.Model.Funcionarios;
import ReservaLab.TrabalhoPOO2.Model.Laboratorio;

@Controller
public class AdminController {
    
    @Autowired
    FuncionariosDao funcionariosDao;
    
    @Autowired
    LaboratorioDao laboratorioDao;
 
////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/Administrador/admin_add_lab")
    public String AddLab(
        @RequestParam(value = "fun") long idFun,    
            Model model
    ){
        
        Funcionarios f=funcionariosDao.buscaId(Funcionarios.class, idFun);
        
        model.addAttribute("user", f);
        
        model.addAttribute("lab", new Laboratorio());
        
        return "Administrador/admin_add_lab";
    }
///////////////////////////////////////////////////////////////////////////////////////////////    
    @PostMapping("/AdicionandoLab")
     public String AdicionandoLab(
       @RequestParam(value = "fun") long idFun, 
       @ModelAttribute Laboratorio laboratorio,
       Model model        
     ){
         
         model.addAttribute("user",funcionariosDao.buscaId(Funcionarios.class, idFun));
         model.addAttribute("lab", laboratorio);
         
         try{ 
            laboratorioDao.criar(laboratorio);
         
            model.addAttribute("menssagem","Laboratorio criado com sucesso");
            
         }catch(Exception e){
            model.addAttribute("menssagem","Ocorreu um erro e não foi possivel criar o laboratorio !!!!!");  
         }
         
         
         return "Administrador/adminIni";
     }
//////////////////////////////////////////////////////////////////////////////////////////////////////////    
   @GetMapping("/Administrador/admin_lista_lab")
    public String ListLab(
        @RequestParam(value = "fun") long idFun,    
            Model model
    ){
        
        Funcionarios f=funcionariosDao.buscaId(Funcionarios.class, idFun);
        
        model.addAttribute("user", f);
        
        List<Laboratorio> labs = laboratorioDao.PegarTodos(Laboratorio.class);
         
        model.addAttribute("labs", labs);
        
        if(labs.isEmpty()){
            model.addAttribute("menssagem","Não existe nenhum laboratorio!");
        }
        
        return "/Administrador/admin_lista_lab";
    }
///////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/Administrador/admin_lab_apargar")
    public String ListLab_apagar(
        @RequestParam(value = "fun") long idFun,
        @RequestParam(value = "lab") long idLab,
            Model model
    ){
        
        Funcionarios f=funcionariosDao.buscaId(Funcionarios.class, idFun);
        Laboratorio l=laboratorioDao.buscaId(Laboratorio.class, idLab);
        
        model.addAttribute("user", f);
   
        try{
            laboratorioDao.deletar(l);
            model.addAttribute("menssagem","Laboratorio apagado!");
        }catch(Exception e){
             model.addAttribute("menssagem","Não foi possivel apagar o laboratorio!");
        }
        
        return "/Administrador/admin_lista_lab";
    }
}
