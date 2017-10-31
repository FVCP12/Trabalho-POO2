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

@Controller
public class PorteiroController {

    @Autowired
    LaboratorioDao laboratorioDao;

    @Autowired
    FuncionariosDao funcionariosDao;

    @GetMapping("Portaria/port_verLabDisponivel")
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
        return "/Portaria/port_verLabDisponivel";
    }

    @GetMapping("Portaria/port_verLabAgendado")
    public String ListLabAgendado(
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
        return "/Portaria/port_verLabAgendado";
    }

    @GetMapping("Portaria/port_verLabEmUso")
    public String ListLabEmUso(
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
        return "Portaria/port_verLabEmUso";
    }

    @GetMapping("Portaria/port_vertTodos")
    public String ListTodosLab(
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
        return "Portaria/port_vertTodos";
    }
}
