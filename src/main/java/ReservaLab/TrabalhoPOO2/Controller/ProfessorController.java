package ReservaLab.TrabalhoPOO2.Controller;

import ReservaLab.TrabalhoPOO2.DAO.*;
import ReservaLab.TrabalhoPOO2.Model.Funcionarios;
import ReservaLab.TrabalhoPOO2.Model.Laboratorio;
import ReservaLab.TrabalhoPOO2.Model.Reservas;
import ReservaLab.TrabalhoPOO2.Model.StatusLab;
import java.time.LocalDateTime;
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
    StatusDao statusDao;

    @Autowired
    ChavesDao chavesDao;

  
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/Professor/solicitarlab")
    public String ExibirReservasSolicitar(
            @RequestParam(value = "fun") long idFun,
            Model model
    ) {

        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
        model.addAttribute("aux", new Funcionarios());

        return "/Professor/exibirlabteste";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/BuscaReservas1")
    public String buscando(
            @RequestParam(value = "fun") long idFun,
            @ModelAttribute Funcionarios aux,
            Model model
    ) {

        model.addAttribute("aux", aux);
        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));

        String dataInicio = aux.getNomeFuncionario() + "-01";
        dataInicio = dataInicio.replaceAll("-", "/");

        Date d1 = new Date(dataInicio);//inicio
        Calendar c = Calendar.getInstance();
        c.setTime(d1);
        c.add(Calendar.MONTH, 1);
        Date d2 = c.getTime();//fim

        List<Reservas> reservas = reservasDao.buscaPorMes(d1, d2);

        model.addAttribute("reservas", reservas);
        //apagar abaixo
        model.addAttribute("d", d1);

        if (reservas.isEmpty()) {
            model.addAttribute("messagem", "Não existe nenhum regitro com esta data!!");
        }

        return "/Professor/exibirlabteste";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/Professor/exibirlabteste")
    public String exibindoReservas(
            //@ModelAttribute Funcionarios aux,
            @ModelAttribute Funcionarios user,
            @ModelAttribute List<Reservas> reservas,
            @ModelAttribute Date d,
            @ModelAttribute StatusLab status,
            Model model
    ) {

        model.addAttribute("d", d);
        model.addAttribute("status", status);
        model.addAttribute("aux", new Funcionarios());
        model.addAttribute("user", user);
        model.addAttribute("reservas", reservas);

        return "/Professor/exibirlabteste";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/Professor/paginadeexibicao")
    public String AdicionandoReserva(
            @RequestParam(value = "fun") long idFun,
            @RequestParam(value = "stat") long idstatus,
            @RequestParam(value = "cod") long codigo,
            @RequestParam(value = "codlab") long codlab,
            @ModelAttribute Laboratorio lab,
            @ModelAttribute Reservas reserva,
            Model model
    ) {

        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
        model.addAttribute("status", statusDao.buscaId(StatusLab.class, idstatus));
        lab = laboratorioDao.buscaId(Laboratorio.class, codlab);
        reserva = reservasDao.buscaId(Reservas.class, codigo);
        model.addAttribute("lab", lab);
        model.addAttribute("codigo", codigo);
        model.addAttribute("reserva", reserva);

        return "/Professor/paginadeexibicao";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/Professor/reservaadicionada")
    public String Reservaadicionada(
            @RequestParam(value = "fun") long idFun,
            @RequestParam(value = "stat") long idstatus,
            @RequestParam(value = "cod") long codigo,
            @ModelAttribute Reservas reserva,
            @ModelAttribute StatusLab status,
            @ModelAttribute Funcionarios user,
            Model model
    ) {

        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
        model.addAttribute("status", statusDao.buscaId(StatusLab.class, idstatus));
        model.addAttribute("codigo", codigo);

        Date data = new Date(System.currentTimeMillis());

        reserva = reservasDao.buscaId(Reservas.class, codigo);
        status = statusDao.buscaId(StatusLab.class, idstatus);
        user = funcionariosDao.buscaId(Funcionarios.class, idFun);
        status.setSituacao(true);
        status.setProfessor(user);
        status.setDataOperacao(data);

        try {
            reservasDao.atualizar(reserva);

            statusDao.atualizar(status);

            model.addAttribute("menssagem", "Laboratorio reservado com sucesso");

        } catch (Exception e) {
            model.addAttribute("menssagem", "Ocorreu um erro e não foi possivel alterar o laboratorio !!!!!");
        }

        return "/Professor/reservaadicionada";
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*   @GetMapping("/Professor/minhasreservas")
    public String Exibirminhasreservas(
            @RequestParam(value = "fun") long idFun,
            Model model
    ) {
    
        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
        model.addAttribute("aux", new Funcionarios());
    
        return "/Professor/minhasreservas";
    }
     */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/BuscaminhasReservas")
    public String buscandominhasreservas(
            @RequestParam(value = "fun") long idFun,
            @ModelAttribute Funcionarios aux,
            Model model
    ) {

        model.addAttribute("aux", aux);
        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));

        String dataInicio = aux.getNomeFuncionario() + "-01";
        dataInicio = dataInicio.replaceAll("-", "/");

        Date d1 = new Date(dataInicio);//inicio
        Calendar c = Calendar.getInstance();
        c.setTime(d1);
        c.add(Calendar.MONTH, 1);
        Date d2 = c.getTime();//fim

        List<Reservas> reservas = reservasDao.buscaPorMes(d1, d2);

        model.addAttribute("reservas", reservas);

        if (reservas.isEmpty()) {
            model.addAttribute("messagem", "Não existe nenhum regitro com esta data!!");
        }
        return "/Professor/minhasreservas";
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/Professor/minhasreservas")
    public String exibindominhasReservas(
            //@ModelAttribute Funcionarios aux,
            @ModelAttribute Funcionarios user,
            @ModelAttribute List<Reservas> reservas,
            @ModelAttribute Date d,
            @ModelAttribute StatusLab status,
            Model model
    ) {

        model.addAttribute("d", d);
        model.addAttribute("status", status);
        model.addAttribute("aux", new Funcionarios());
        model.addAttribute("user", user);
        model.addAttribute("reservas", reservas);

        return "/Professor/minhasreservas";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/Professor/solicitarminhasreservas")
    public String ExibirminhasReservasSolicitar(
            @RequestParam(value = "fun") long idFun,
            Model model
    ) {

        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
        model.addAttribute("aux", new Funcionarios());

        return "/Professor/minhasreservas";
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/Professor/solicitarminhasreservaspordia")
    public String ExibirminhasReservaspordiaSolicitar(
            @RequestParam(value = "fun") long idFun,
            Model model
    ) {

        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
        model.addAttribute("aux", new Funcionarios());

        return "/Professor/solicitarminhasreservaspordia";
    }

    @PostMapping("/BuscaminhasReservaspordia")
    public String buscandominhasreservaspordia(
            @RequestParam(value = "fun") long idFun,
            @ModelAttribute Funcionarios aux,
            Model model
    ) {

        model.addAttribute("aux", aux);
        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));

        String data = aux.getNomeFuncionario();
        data = data.replaceAll("-", "/");

        Date d1 = new Date(data);//inicio

        List<Reservas> reservas = reservasDao.buscaPorDia(d1);

        model.addAttribute("reservas", reservas);

        if (reservas.isEmpty()) {
            model.addAttribute("messagem", "Não existe nenhum regitro com esta data!!");
        }
        return "/Professor/minhasreservaspordia";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/Professor/minhasreservaspordia")
    public String exibindominhasReservaspordia(
            //@ModelAttribute Funcionarios aux,
            @ModelAttribute Funcionarios user,
            @ModelAttribute List<Reservas> reservas,
            @ModelAttribute Date d,
            @ModelAttribute StatusLab status,
            Model model
    ) {

        model.addAttribute("d", d);
        model.addAttribute("status", status);
        model.addAttribute("aux", new Funcionarios());
        model.addAttribute("user", user);
        model.addAttribute("reservas", reservas);

        return "/Professor/minhasreservaspordia";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/Professor/novareservadia")
    public String NovaReservaDiaSolicitar(
            @RequestParam(value = "fun") long idFun,
            Model model
    ) {

        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
        model.addAttribute("aux", new Funcionarios());

        return "/Professor/novareservadia";
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/novareservadia")
    public String buscandoreservaspordia(
            @RequestParam(value = "fun") long idFun,
            @ModelAttribute Funcionarios aux,
            Model model
    ) {

        model.addAttribute("aux", aux);
        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));

        String data = aux.getNomeFuncionario();
        data = data.replaceAll("-", "/");

        Date d1 = new Date(data);//inicio

        List<Reservas> reservas = reservasDao.buscaPorDia(d1);

        model.addAttribute("reservas", reservas);
        //apagar abaixo
        // model.addAttribute("d", d1);

        if (reservas.isEmpty()) {
            model.addAttribute("messagem", "Não existe nenhum regitro com esta data!!");
        }
        return "/Professor/exibirreservasdia";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/alterarsenha")
    public String trocandosenha(
            @RequestParam(value = "fun") long idFun,
            @ModelAttribute Funcionarios funcionario,
            Model model) {

            Funcionarios prof = new Funcionarios();
            prof = funcionariosDao.buscaId(Funcionarios.class, idFun);
            model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));

            if (funcionario.getSenha().equals(prof.getSenha())) {
                model.addAttribute("usuario1", new Funcionarios());
                return "/Professor/novasenha";
            }
        
        model.addAttribute("usuario", new Funcionarios());
        model.addAttribute("menssagem","Senha inválida!");
        return "Professor/AlterarSenha";
    }

    @GetMapping("/Professor/AlterarSenha")
    public String trocarsenha(
            @RequestParam(value = "fun") long idFun,
            @ModelAttribute Funcionarios aux,
            Model model
    ) {
        model.addAttribute("usuario", new Funcionarios());
        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
        model.addAttribute("aux", aux);

        return "/Professor/AlterarSenha";
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/Professor/novasenha")
    public String novasenha(
            @RequestParam(value = "fun") long idFun,
            @ModelAttribute Funcionarios aux,
            Model model
    ) {
        model.addAttribute("usuario1", new Funcionarios());
        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
        model.addAttribute("aux", aux);
        return "/Professor/novasenha";
    }
    
     @PostMapping("/atualizandosenha")
    public String atualizandosenha(
            @RequestParam(value = "fun") long idFun,
            @ModelAttribute Funcionarios funcionario1,
            Model model) {
        
        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
        
        if(funcionario1.getSenha().equals(funcionario1.getRa())){
        String senha = funcionario1.getSenha();
        Funcionarios atualizarsenha = new Funcionarios();
        atualizarsenha= funcionariosDao.buscaId(Funcionarios.class, idFun);
        atualizarsenha.setSenha(senha);
        funcionariosDao.atualizar(atualizarsenha);
        return "Professor/senhaalterada";
        }
        
        model.addAttribute("usuario1", new Funcionarios());
        model.addAttribute("menssagem","As senhas nao coincidem!");
        return "Professor/novasenha";
    }
    
    @GetMapping("/Professor/senhaalterada")
    public String senhaalterada(
            @RequestParam(value = "fun") long idFun,
            
            Model model
    ) {
        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
     
        return "/Professor/senhaalterada";
    }
    /////////////////////////////////////////////////////////////////////////////////////
        @GetMapping("/voltarinicio")
    public String inicio(
            @RequestParam(value = "fun") long idFun,
            
            Model model
    ) {
        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
     
        return "/Professor/professorIni";
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    
    @GetMapping("/deletar")
    public String deletar(
            @RequestParam(value = "fun") long idFun,
            @RequestParam(value = "stat") long stat,
            Model model
    ) {
        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
        model.addAttribute("aux", new Funcionarios());
        
        StatusLab reserva= new StatusLab();
        
        reserva = statusDao.buscaId(StatusLab.class, stat);
        reserva.setSituacao(false);
        
        statusDao.atualizar(reserva);
        
        model.addAttribute("menssagem", "Reserva excluida com sucesso");
        
        
        return "/Professor/minhasreservas";
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    @GetMapping("/deletarpordia")
    public String deletarpordia(
            @RequestParam(value = "fun") long idFun,
            @RequestParam(value = "stat") long stat,
            Model model
    ) {
        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
        model.addAttribute("aux", new Funcionarios());
        
        StatusLab reserva= new StatusLab();
        
        reserva = statusDao.buscaId(StatusLab.class, stat);
        reserva.setSituacao(false);
        
        statusDao.atualizar(reserva);
        
        model.addAttribute("menssagem", "Reserva excluida com sucesso");
        
        
        return "/Professor/minhasreservaspordia";
    }
    
    
}