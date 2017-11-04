
package ReservaLab.TrabalhoPOO2.Controller;

import ReservaLab.TrabalhoPOO2.DAO.*;
import ReservaLab.TrabalhoPOO2.Model.Funcionarios;
import ReservaLab.TrabalhoPOO2.Model.Laboratorio;
import ReservaLab.TrabalhoPOO2.Model.Reservas;
import ReservaLab.TrabalhoPOO2.Model.StatusLab;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Marioto
 */
@Controller
public class ProfessorController {
  
    @Autowired
    FuncionariosDao funcionariosDao;

    @Autowired
    LaboratorioDao laboratorioDao;

    @Autowired
    ReservasDao reservasDao;

    @Autowired
    ChavesDao chavesDao;

    @Autowired
    StatusDao statusDao;

////////////////////////////////////////////////////////////////////////////////////

    
    
@GetMapping("/Professor/prof_reserva")
    public String ListLab1(
            @RequestParam(value = "fun") long idFun,
            Model model
    ) {

        Funcionarios f = funcionariosDao.buscaId(Funcionarios.class, idFun);

        model.addAttribute("user", f);

        List<Laboratorio> labs = laboratorioDao.PegarTodos(Laboratorio.class);

        model.addAttribute("labs", labs);

        if (labs.isEmpty()) {
            model.addAttribute("menssagem", "Não existe nenhum laboratorio!");
        }

        return "/Professor/prof_reserva";
    }
    
     @GetMapping("/Professor/solicitarlab")
    public String ExibirReservasSolicitar(
           @RequestParam(value = "fun") long idFun,
            Model model
    ){
    
        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
        model.addAttribute("aux", new Funcionarios());
        
        
        return "/Professor/exibirlabteste";
    }
    
    @PostMapping("/BuscaReservas1")
    public String buscando(
          @RequestParam(value = "fun") long idFun,
          @ModelAttribute Funcionarios aux,
          Model model  
    ){
 
         model.addAttribute("aux", aux);
         model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
        
         String dataInicio = aux.getNomeFuncionario()+"-01";
         dataInicio = dataInicio.replaceAll("-","/");
         
         Date d1 = new Date(dataInicio);//inicio
         Calendar c = Calendar.getInstance();
         c.setTime(d1);
         c.add(Calendar.MONTH, 1);
         Date d2 = c.getTime();//fim
          
        List<Reservas> reservas = reservasDao.buscaPorMes(d1, d2);
        
        model.addAttribute("reservas",reservas);
        //apagar abaixo
        model.addAttribute("d",d1);
        
        if(reservas.isEmpty()){
            model.addAttribute("messagem", "Não existe nenhum regitro com esta data!!");
        }
        
        
        
        return "/Professor/exibirlabteste";
    }
    
    
    @GetMapping("/Professor/exibirlabteste")
    public String exibindoReservas(
       //@ModelAttribute Funcionarios aux,
       @ModelAttribute Funcionarios user,
       @ModelAttribute List<Reservas> reservas,
       @ModelAttribute Date d,
       Model model
    ){
      
        
        
       
        model.addAttribute("d",d);
        //model.addAttribute("aux", aux);
        model.addAttribute("aux", new Funcionarios());
        model.addAttribute("user", user);
        model.addAttribute("reservas",reservas);
        
        return "/Professor/exibirlabteste";
    }
    
    
    @PostMapping("/AdicionandoReserva")
    public String AdicionandoReserva(
            @RequestParam(value = "fun") long idFun,
            @ModelAttribute Laboratorio laboratorio,
            @ModelAttribute StatusLab status,
            Model model
    ) {
            model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
            model.addAttribute("status", status);
        try {
            
            statusDao.atualizar(status);

            model.addAttribute("menssagem", "Reserva efetuada com sucesso");

        } catch (Exception e) {
            model.addAttribute("menssagem", "Ocorreu um erro e não foi possivel reservar !!!!!");
        }

        return "Administrador/adminIni";
    }
    
}
