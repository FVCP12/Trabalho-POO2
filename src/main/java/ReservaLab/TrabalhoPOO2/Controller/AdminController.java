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
                    
                    if (reservasDao.verificarExiste(l, d)) {//quando nao existe no banco e pode inserir

                        do {

                            Status[] s = new Status[6];
                            for (int i = 0; i < 6; i++) {
                                s[i] = new Status();
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
                    
                    if (reservasDao.verificarExiste(l, d)) {//quando nao existe no banco e pode inserir

                        do {

                            Status[] s = new Status[6];
                            for (int i = 0; i < 6; i++) {
                                s[i] = new Status();
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
    ){
    
        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));
        model.addAttribute("aux", new Funcionarios());
        
        
        return "/Administrador/admin_exibirReservas_solicitar";
    }
    
    @PostMapping("/BuscaReservas")
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
        
        
        
        return "/Administrador/admin_exibirReservas_exibindo";
    }
    
    
    @GetMapping("/Administrador/admin_exibirReservas_exibindo")
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
        
        return "/Administrador/admin_exibirReservas_exibindo";
    }
    
}
