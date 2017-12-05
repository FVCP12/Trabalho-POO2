/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReservaLab.TrabalhoPOO2.Controller;

import ReservaLab.TrabalhoPOO2.DAO.ChavesDao;
import ReservaLab.TrabalhoPOO2.DAO.FuncionariosDao;
import ReservaLab.TrabalhoPOO2.DAO.LaboratorioDao;
import ReservaLab.TrabalhoPOO2.DAO.ReservasDao;
import ReservaLab.TrabalhoPOO2.DAO.StatusDao;
import ReservaLab.TrabalhoPOO2.Model.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PorteiroController {

    @Autowired
    ReservasDao reservasDao;

    @Autowired
    LaboratorioDao laboratorioDao;

    @Autowired
    FuncionariosDao funcionariosDao;

    @Autowired
    ChavesDao chavesDao;

    @Autowired
    StatusDao statusDao;

    @GetMapping("Portaria/port_verLabAgendado")
    public String labAgendado(
            @RequestParam(value = "fun") long idFun,
            Model model
    ) {

        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));

        Date d1 = new Date();

        List<Reservas> reservas = reservasDao.buscaPorDia(d1);

        model.addAttribute("reservas", reservas);

        long n = 0;
        n += reservas.stream().filter(x -> x.getStatus()[0].isSituacao() == true).count();
        n += reservas.stream().filter(x -> x.getStatus()[1].isSituacao() == true).count();
        n += reservas.stream().filter(x -> x.getStatus()[2].isSituacao() == true).count();
        n += reservas.stream().filter(x -> x.getStatus()[3].isSituacao() == true).count();
        n += reservas.stream().filter(x -> x.getStatus()[4].isSituacao() == true).count();
        n += reservas.stream().filter(x -> x.getStatus()[5].isSituacao() == true).count();

        if (n == 0) {
            model.addAttribute("messagem", "Não existe nenhum regitro com esta data!!");
        }

        return "/Portaria/port_verLabAgendado";

    }


    @GetMapping("Portaria/port_vertTodos")
    public String ExibirReservaspordiaSolicitar(
            @RequestParam(value = "fun") long idFun,
            Model model
    ) {

        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));

        Date d1 = new Date();

        List<Reservas> reservas = reservasDao.buscaPorDia(d1);
        
        
        
        List<Reservas> r0 = reservas.stream().filter(x -> x.getStatus()[0].getDescricaoChave().getCod_chaves() == 2).collect(Collectors.toList());
        List<Reservas> r1 = reservas.stream().filter(x -> x.getStatus()[1].getDescricaoChave().getCod_chaves() == 2).collect(Collectors.toList());
        List<Reservas> r2 = reservas.stream().filter(x -> x.getStatus()[2].getDescricaoChave().getCod_chaves() == 2).collect(Collectors.toList());
        List<Reservas> r3 = reservas.stream().filter(x -> x.getStatus()[3].getDescricaoChave().getCod_chaves() == 2).collect(Collectors.toList());
        List<Reservas> r4 = reservas.stream().filter(x -> x.getStatus()[4].getDescricaoChave().getCod_chaves() == 2).collect(Collectors.toList());
        List<Reservas> r5 = reservas.stream().filter(x -> x.getStatus()[5].getDescricaoChave().getCod_chaves() == 2).collect(Collectors.toList());

        
        
        model.addAttribute("r0", r0);
        model.addAttribute("r1", r1);
        model.addAttribute("r2", r2);
        model.addAttribute("r3", r3);
        model.addAttribute("r4", r4);
        model.addAttribute("r5", r5);
        
        long n = 0;
        n += r0.size();
        n += r1.size();
        n += r2.size();
        n += r3.size();
        n += r4.size();
        n += r5.size();

        if (n == 0) {
            model.addAttribute("messagem", "Não existe nenhum regitro com esta data!!");
        }
        
        

        return "Portaria/port_vertTodos";

    }

    @GetMapping("/alterandoChave")
    public String alterarChave(
            @RequestParam(value = "fun") long idFun,
            @RequestParam(value = "idSta") long idStat,
            Model model
    ) {

        System.out.println("Entrei aqui");

        model.addAttribute("user", funcionariosDao.buscaId(Funcionarios.class, idFun));

        StatusLab s = statusDao.buscaId(StatusLab.class, idStat);

        if (s.getDescricaoChave().getCod_chaves() == 1) {
            s.getDescricaoChave().setCod_chaves(2);
        } else {
            s.getDescricaoChave().setCod_chaves(1);
        }

        statusDao.atualizar(s);

        Date d1 = new Date();

        List<Reservas> reservas = reservasDao.buscaPorDia(d1);

        model.addAttribute("reservas", reservas);

        long n = 0;
        n += reservas.stream().filter(x -> x.getStatus()[0].isSituacao() == true).count();
        n += reservas.stream().filter(x -> x.getStatus()[1].isSituacao() == true).count();
        n += reservas.stream().filter(x -> x.getStatus()[2].isSituacao() == true).count();
        n += reservas.stream().filter(x -> x.getStatus()[3].isSituacao() == true).count();
        n += reservas.stream().filter(x -> x.getStatus()[4].isSituacao() == true).count();
        n += reservas.stream().filter(x -> x.getStatus()[5].isSituacao() == true).count();

        if (n == 0) {
            model.addAttribute("messagem", "Não existe nenhum regitro com esta data!!");
        }

        return "Portaria/port_verLabAgendado";
    }

}
