/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReservaLab.TrabalhoPOO2.Controller;

import ReservaLab.TrabalhoPOO2.DAO.FuncionariosDao;
import ReservaLab.TrabalhoPOO2.DAO.LaboratorioDao;
import ReservaLab.TrabalhoPOO2.Model.Funcionarios;
import ReservaLab.TrabalhoPOO2.Model.Laboratorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Marioto
 */
@Controller
public class ProfessorController {
      
    @Autowired
    LaboratorioDao laboratorioDao;
    
    @GetMapping("Professor/prof_reserva")
    public String ListarLab(Model model){
        
        List<Laboratorio> labs = laboratorioDao.PegarTodos(Laboratorio.class);
         
        model.addAttribute("labs", labs);
        
        if(labs.isEmpty()){
            model.addAttribute("menssagem","Não existe nenhum laboratorio!");
        }
        
        return "Professor/prof_reserva";
    }
   
}