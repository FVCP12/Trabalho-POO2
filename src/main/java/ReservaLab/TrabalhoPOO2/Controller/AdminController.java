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

@Controller
public class AdminController {

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
    
    @Autowired
    FuncaoDao funcaoDao;

////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/Administrador/admin_add_lab")
    public String AddLab(
            @RequestParam(value = "fun") long idFun,
            Model model
    ) {

        Funcionarios f = funcionariosDao.buscaId(Funcionarios.class, idFun);

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
    ) {

        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
        model.addAttribute("lab", laboratorio);

        try {
            laboratorioDao.criar(laboratorio);

            model.addAttribute("menssagem", "Laboratorio criado com sucesso");

        } catch (Exception e) {
            model.addAttribute("menssagem", "Ocorreu um erro e não foi possivel criar o laboratorio !!!!!");
        }

        return "Administrador/adminIni";
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////    

    @GetMapping("/Administrador/admin_lista_lab")
    public String ListLab(
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

        return "/Administrador/admin_lista_lab";
    }
///////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/Administrador/admin_lab_apargar")
    public String ListLab_apagar(
            @RequestParam(value = "fun") long idFun,
            @RequestParam(value = "lab") long idLab,
            Model model
    ) {

        Funcionarios f = funcionariosDao.buscaId(Funcionarios.class, idFun);
        Laboratorio l = laboratorioDao.buscaId(Laboratorio.class, idLab);

        model.addAttribute("user", f);

        List<Reservas> res = reservasDao.buscaPorLaboratorio(l);//buscando as  reservas para apagar

        for (Reservas r : res) {//apagando uma por uma 

            reservasDao.deletar(r);

            statusDao.deletar(r.getStatus()[0]);
            statusDao.deletar(r.getStatus()[1]);
            statusDao.deletar(r.getStatus()[2]);
            statusDao.deletar(r.getStatus()[3]);
            statusDao.deletar(r.getStatus()[4]);
            statusDao.deletar(r.getStatus()[5]);

        }

        try {
            laboratorioDao.deletar(l);
            model.addAttribute("menssagem", "Laboratorio apagado!");
        } catch (Exception e) {
            model.addAttribute("menssagem", "Não foi possivel apagar o laboratorio!");
        }

        List<Laboratorio> labs = laboratorioDao.PegarTodos(Laboratorio.class);

        model.addAttribute("labs", labs);

        return "/Administrador/admin_lista_lab";
    }
/////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/Administrador/admin_disponibilizar_escolhaAno")
    public String Admin_escolha_ano(
            @RequestParam(value = "fun") long idFun,
            Model model
    ) {

        Funcionarios f = funcionariosDao.buscaId(Funcionarios.class, idFun);

        model.addAttribute("user", f);
        model.addAttribute("aux", new Funcionarios());

        return "/Administrador/admin_disponibilizar_escolhaAno";
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("/escolhendoAno")
    public String escolhendoAno(
            @RequestParam(value = "fun") long idFun,
            @ModelAttribute Funcionarios fun,
            Model model
    ) {

        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
        model.addAttribute("aux", fun);

        return "/Administrador/admin_disponibilizar_escolhaSemestre";
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/Administrador/admin_disponibilizar_escolhaSemestre")
    public String Admin_escolha_semestre(
            @ModelAttribute Funcionarios f,
            @ModelAttribute Funcionarios aux,
            Model model
    ) {

        model.addAttribute("user", f);
        model.addAttribute("aux", aux);

        return "/Administrador/admin_disponibilizar_escolhaSemestre";
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("/escolhendoSemestre")
    public String escolhendoSemestre(
            @RequestParam(value = "fun") long idFun,
            @ModelAttribute Funcionarios fun,
            Model model
    ) {

        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
        model.addAttribute("aux", fun);

        // System.out.println("Ano: "+fun.getRa());
        // System.out.println("semestre: "+fun.getSenha());
        List<Laboratorio> labs = laboratorioDao.PegarTodos(Laboratorio.class);
        Chaves cha = chavesDao.buscaId(Chaves.class, 1);
        String ano = fun.getRa();
        String semestre = fun.getSenha();
        String fraseRetorno = "";

        if (!labs.isEmpty()) {

            if (semestre.equals("1")) {

                for (Laboratorio l : labs) {

                    Date d = new Date(ano + "/01/01");
                    Calendar c = Calendar.getInstance();
                    
                     if (d.getDay() == 0) {
                                c.setTime(d);

                                c.add(Calendar.DATE, +1);

                                d = c.getTime();
                    }
                    

                    if (reservasDao.verificarExiste(l, d)) {//quando nao existe no banco e pode inserir

                        do {

                            StatusLab[] s = new StatusLab[6];
                            for (int i = 0; i < 6; i++) {
                                s[i] = new StatusLab();
                                s[i].setDescricaoChave(cha);
                                s[i].setSituacao(false);

                                switch (i) {
                                    case 0:
                                        s[i].setHorario("1º horario manha");
                                        break;
                                    case 1:
                                        s[i].setHorario("2º horario manha");
                                        break;
                                    case 2:
                                        s[i].setHorario("1º horario tarde");
                                        break;
                                    case 3:
                                        s[i].setHorario("2º horario tarde");
                                        break;
                                    case 4:
                                        s[i].setHorario("1º horario noite");
                                        break;
                                    case 5:
                                        s[i].setHorario("2º horario noite");
                                        break;
                                    default:
                                        System.out.println("erro");
                                        break;
                                }

                                statusDao.criar(s[i]);
                            }

                            Reservas r = new Reservas();
                            r.setDataReserva(d);
                            r.setLaboratorio(l);
                            r.setStatus(s);

                            reservasDao.criar(r);
                            //////////////////////////////abaixo datas
                            c.setTime(d);

                            c.add(Calendar.DATE, +1);

                            d = c.getTime();

                            if (d.getDay() == 0) {
                                c.setTime(d);

                                c.add(Calendar.DATE, +1);

                                d = c.getTime();
                            }

                        } while (d.getMonth() + 1 != 7);

                        fraseRetorno += "A data para o " + l.getNomeLab() + " foi criada com sucesso; ";

                    } else {
                        fraseRetorno += "A data para o " + l.getNomeLab() + " ja foi criada; ";

                    }

                }

            } else {

                for (Laboratorio l : labs) {

                    Date d = new Date(ano + "/07/01");
                    Calendar c = Calendar.getInstance();
                    
                     if (d.getDay() == 0) {
                                c.setTime(d);

                                c.add(Calendar.DATE, +1);

                                d = c.getTime();
                    }

                    if (reservasDao.verificarExiste(l, d)) {//quando nao existe no banco e pode inserir

                        do {

                            StatusLab[] s = new StatusLab[6];
                            for (int i = 0; i < 6; i++) {
                                s[i] = new StatusLab();
                                s[i].setDescricaoChave(cha);
                                s[i].setSituacao(false);

                                switch (i) {
                                    case 0:
                                        s[i].setHorario("1º horario manha");
                                        break;
                                    case 1:
                                        s[i].setHorario("2º horario manha");
                                        break;
                                    case 2:
                                        s[i].setHorario("1º horario tarde");
                                        break;
                                    case 3:
                                        s[i].setHorario("2º horario tarde");
                                        break;
                                    case 4:
                                        s[i].setHorario("1º horario noite");
                                        break;
                                    case 5:
                                        s[i].setHorario("2º horario noite");
                                        break;
                                    default:
                                        System.out.println("erro");
                                        break;
                                }

                                statusDao.criar(s[i]);
                            }

                            Reservas r = new Reservas();
                            r.setDataReserva(d);
                            r.setLaboratorio(l);
                            r.setStatus(s);

                            reservasDao.criar(r);
                            //////////////////////////////abaixo datas
                            c.setTime(d);

                            c.add(Calendar.DATE, +1);

                            d = c.getTime();
                            
                            if (d.getDay() == 0) {
                                c.setTime(d);

                                c.add(Calendar.DATE, +1);

                                d = c.getTime();
                            }
                            
                        } while (d.getMonth() + 1 != 1);

                        fraseRetorno += "A data para o " + l.getNomeLab() + " foi criada com sucesso; ";

                    } else {
                        fraseRetorno += "A data para o " + l.getNomeLab() + " ja foi criada; ";

                    }

                }

            }
            model.addAttribute("menssagem", fraseRetorno);
        } else {
            model.addAttribute("menssagem", "Não existe laboratorios para disponibilizar!");
        }

        return "Administrador/adminIni";
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/Administrador/admin_exibirReservas_solicitar")
    public String ExibirReservasSolicitar(
            @RequestParam(value = "fun") long idFun,
            Model model
    ) {

        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
        model.addAttribute("aux", new Funcionarios());

        return "/Administrador/admin_exibirReservas_solicitar";
    }

    @PostMapping("/BuscaReservas")
    public String buscando(
            @RequestParam(value = "fun") long idFun,
            @ModelAttribute Funcionarios aux,
            Model model
    ) {

        model.addAttribute("aux", aux);
        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));

        if(!aux.getNomeFuncionario().equals("")){
        String dataInicio = aux.getNomeFuncionario() + "-01";
        dataInicio = dataInicio.replaceAll("-", "/");

        Date d1 = new Date(dataInicio);//inicio
        Calendar c = Calendar.getInstance();
        c.setTime(d1);
        c.add(Calendar.MONTH, 1);
        Date d2 = c.getTime();//fim

        List<Reservas> reservas = reservasDao.buscaPorMes(d1, d2);

        List<Funcionarios> podeReservar = funcionariosDao.podeReservarLab();
        
        model.addAttribute("reservas", reservas);
        model.addAttribute("funcionarios", podeReservar);
        
        model.addAttribute("d", d1);
        
        
        model.addAttribute("status", new StatusLab());

        if (reservas.isEmpty()) {
            model.addAttribute("messagem", "Não existe nenhum regitro com esta data!!");
        }
        return "/Administrador/admin_exibirReservas_exibindo";
        }else{
            
            model.addAttribute("messagem", "Selecione uma data primeiro!!");
            
        return "/Administrador/admin_exibirReservas_solicitar";
        }

        
    }

//////////////////////////////////////////////////////////////////////////////////////////////    
    @GetMapping("/Administrador/admin_exibirReservas_exibindo")
    public String exibindoReservas(
            
            @ModelAttribute Funcionarios user,
            @ModelAttribute List<Reservas> reservas,
            @ModelAttribute Date d,
            @ModelAttribute List<Funcionarios> podeReservar,
            @ModelAttribute Funcionarios aux,
            Model model
    ) {

        
   
        model.addAttribute("d", d);
        model.addAttribute("aux", aux);
        model.addAttribute("user", user);
        model.addAttribute("reservas", reservas);
        model.addAttribute("funcionarios", podeReservar);
        
       
        
        return "/Administrador/admin_exibirReservas_exibindo";
    }
    ////////////////////////////////////////////////////////////////////////////////
   @PostMapping("/Reservando")
   public String reservandoLab(
            @RequestParam(value = "fun") long idFun,
            @RequestParam(value = "sta") long idStat,
            @RequestParam(value = "data") String dataMes,
            @ModelAttribute Funcionarios aux,
            @ModelAttribute StatusLab status,
            Model model
   ){
       
        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
        model.addAttribute("status", status);
        model.addAttribute("aux", aux);
       
       
        StatusLab atualizar = statusDao.buscaId(StatusLab.class, idStat);
        Funcionarios funReserva = funcionariosDao.buscaId(Funcionarios.class, status.getProfessor().getCod_funcio());
                
        atualizar.setProfessor(funReserva);
        atualizar.setDataOperacao(new Date());
        atualizar.setSituacao(true);
        
        statusDao.atualizar(atualizar);
        
     
        String dataInicio =  dataMes.substring(0, dataMes.length()-2) + "01";
       
       
        dataInicio = dataInicio.replaceAll("-", "/");

        Date d1 = new Date(dataInicio);//inicio
        Calendar c = Calendar.getInstance();
        c.setTime(d1);
        c.add(Calendar.MONTH, 1);
        Date d2 = c.getTime();//fim

        List<Reservas> reservas = reservasDao.buscaPorMes(d1, d2);

        List<Funcionarios> podeReservar = funcionariosDao.podeReservarLab();
        
        model.addAttribute("reservas", reservas);
        model.addAttribute("funcionarios", podeReservar);
        
        model.addAttribute("d", d1);
        
        
        model.addAttribute("status", new StatusLab());

        if (reservas.isEmpty()) {
            model.addAttribute("messagem", "Não existe nenhum regitro com esta data!!");
        }
  
        return "/Administrador/admin_exibirReservas_exibindo";
    
   }
  /////////////////////////////////////////////////////////////////////////////////
   
   @GetMapping("/Excluir")
   public String excluindoReservaLab(
            @RequestParam(value = "fun") long idFun,
            @RequestParam(value = "sta") long idStat,
            @RequestParam(value = "data") String dataMes,
            @ModelAttribute Funcionarios aux,
            @ModelAttribute StatusLab status,
            Model model
   ){
       
        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
        model.addAttribute("status", status);
        model.addAttribute("aux", aux);
       
       
        StatusLab atualizar = statusDao.buscaId(StatusLab.class, idStat);
                
        atualizar.setProfessor(null);
        atualizar.setDataOperacao(null);
        atualizar.setSituacao(false);
        
        statusDao.atualizar(atualizar);
        
         
        String dataInicio =  dataMes.substring(0, dataMes.length()-2) + "01";
       
       
        dataInicio = dataInicio.replaceAll("-", "/");

        Date d1 = new Date(dataInicio);//inicio
        Calendar c = Calendar.getInstance();
        c.setTime(d1);
        c.add(Calendar.MONTH, 1);
        Date d2 = c.getTime();//fim

        List<Reservas> reservas = reservasDao.buscaPorMes(d1, d2);

        List<Funcionarios> podeReservar = funcionariosDao.podeReservarLab();
        
        model.addAttribute("reservas", reservas);
        model.addAttribute("funcionarios", podeReservar);
        
        model.addAttribute("d", d1);
        
        
        model.addAttribute("status", new StatusLab());

        if (reservas.isEmpty()) {
            model.addAttribute("messagem", "Não existe nenhum regitro com esta data!!");
        }
  
        return "/Administrador/admin_exibirReservas_exibindo";
    
   }
   
    ///////////////////////////////////////////////////////////////////////////////
    @GetMapping("/Administrador/admin_altera_lab")
    public String alteraLab(
            @RequestParam(value = "fun") long idFun,
            @RequestParam(value = "ilab") long ilab,
            Model model
    ) {

        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
        model.addAttribute("lab", laboratorioDao.buscaId(Laboratorio.class, ilab));

        return "/Administrador/admin_altera_lab";
    }
    //////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("/AlterandoLab")
    public String AlterandoLab(
            @RequestParam(value = "fun") long idFun,
            @ModelAttribute Laboratorio laboratorio,
            Model model
    ) {

        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
        model.addAttribute("lab", laboratorio);

        try {
            laboratorioDao.atualizar(laboratorio);

            model.addAttribute("menssagem", "Laboratorio alterado com sucesso");

        } catch (Exception e) {
            model.addAttribute("menssagem", "Ocorreu um erro e não foi possivel alterar o laboratorio !!!!!");
        }

        List<Laboratorio> labs = laboratorioDao.PegarTodos(Laboratorio.class);

        model.addAttribute("labs", labs);

        return "Administrador/admin_lista_lab";
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/Administrador/admin_lista_funcionario")
    public String listaProfessor(
            @RequestParam(value = "fun") long idFun,
            Model model
    ) {

        List<Funcionarios> funcionarios = new ArrayList<Funcionarios>();

        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));

        try {

            funcionarios = funcionariosDao.PegarTodos(Funcionarios.class);

            if (funcionarios.isEmpty()) {
                model.addAttribute("menssagem", "Não existem funcionarios cadastrados!");
            }

        } catch (Exception e) {

            model.addAttribute("menssagem", "Erro ao buscar os dados do servidor!!!");
        }

        model.addAttribute("funs", funcionarios);

        return "/Administrador/admin_lista_funcionario";
    }
///////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/Administrador/admin_add_funcionario")
    public String adicFuncio(
       @RequestParam(value = "fun") long idFun,
        Model model     
    ){
        
        Funcionarios funcio = new Funcionarios();
        Funcoes funcao = new Funcoes();
        
        funcio.setFuncao(funcao);
        
        List<Funcoes> funcoes = funcaoDao.PegarTodos(Funcoes.class);
        
        model.addAttribute("user",funcionariosDao.buscaId(Funcionarios.class, idFun));
        
        model.addAttribute("funcio", funcio);
        
        model.addAttribute("funcoes",funcoes);
        
        return "/Administrador/admin_add_funcionario";
    }
///////////////////////////////////////////////////////////////////////////////////    
    @PostMapping("/AdicionandoFunc")
    public String adicionadoFuncio(
        @RequestParam(value = "fun") long idFun,
        @ModelAttribute  Funcionarios funcio,
        Model model 
    ){
        
        model.addAttribute("user",funcionariosDao.buscaId(Funcionarios.class, idFun));
        
        model.addAttribute("funcio", funcio);
        
       
        Funcoes f = funcaoDao.buscaId(Funcoes.class, funcio.getFuncao().getCod_funcoes());
        
        funcio.setFuncao(f);
        
        try{
            
            funcionariosDao.criar(funcio);
            
            model.addAttribute("menssagem", "Usuario criado com sucesso!!");
            
        }catch(Exception e){
         
            model.addAttribute("menssagem", "Erro ao criar o novo usuario!!");
        }
        
  
        
        return "/Administrador/adminIni";
    }
//////////////////////////////////////////////////////////////////////////////////////////////////
  @GetMapping("/Administrador/admin_altera_funcionario")
  public String altarFuncionario(
     @RequestParam(value = "fun") long idFun,
     @RequestParam(value = "idfunAlterar") long idAltera,
     Model model
          
  ){
      
      model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
      model.addAttribute("funcionario", funcionariosDao.buscaId(Funcionarios.class, idAltera));
      List<Funcoes> funcoes = funcaoDao.PegarTodos(Funcoes.class);
       model.addAttribute("funcoes",funcoes);
       
       
      return "/Administrador/admin_altera_funcionario";
  } 
  ////////////////////////////////////////////////////////////////////////////////////////////////////////
  @PostMapping("/alterandoFuncionario")
  public String alterandoFuncio(
    @RequestParam(value = "fun") long idFun,
    @ModelAttribute Funcionarios funcionario,            
    Model model
  ){
      
      
        List<Funcionarios> funcionarios = new ArrayList<Funcionarios>();

        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));

        try {

            funcionarios = funcionariosDao.PegarTodos(Funcionarios.class);

            if (funcionarios.isEmpty()) {
                model.addAttribute("menssagem", "Não existem funcionarios cadastrados!");
            }else{
                funcionariosDao.atualizar(funcionario);
                model.addAttribute("menssagem", "Funcionario alterado com sucesso!");
            }

        } catch (Exception e) {

            model.addAttribute("menssagem", "Erro ao buscar os dados do servidor!!!");
        }

        model.addAttribute("funs", funcionarios);

        return "/Administrador/admin_lista_funcionario";
  }
 //////////////////////////////////////////////////////////////////////////////////////////////////
 @GetMapping("/apagandoFuncionario")
  public String apagandoFuncio(
    @RequestParam(value = "fun") long idFun,
    @RequestParam(value = "excluir") long idExcluir,            
    Model model
  ){
      
      
        List<Funcionarios> funcionarios = new ArrayList<Funcionarios>();
        List<StatusLab> status = new ArrayList<StatusLab>();

        Funcionarios f = funcionariosDao.buscaId(Funcionarios.class, idExcluir);
        
        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));

        
        try {

            
            
            status = statusDao.StatusPorFuncionario(f);
            
            for(StatusLab s : status){
                
                s.setDataOperacao(null);
                s.setSituacao(false);
                s.setProfessor(null);
                statusDao.atualizar(s);
            }
            
            funcionariosDao.deletar(f);
            
            
            model.addAttribute("menssagem", "Funcionario excluido com sucesso!");
            
            
            funcionarios = funcionariosDao.PegarTodos(Funcionarios.class);

            if (funcionarios.isEmpty()) {
                model.addAttribute("menssagem", "Não existem funcionarios cadastrados!");
            }
                

        } catch (Exception e) {

            model.addAttribute("menssagem", "Erro ao buscar os dados do servidor!!!");
        }

        model.addAttribute("funs", funcionarios);

        return "/Administrador/admin_lista_funcionario";
  } 
//////////////////////////////////////////////////////////////////////////////////////////////////////  

  
  
}
