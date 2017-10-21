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
    private FuncionariosDao funcionarioDao;
    
    
}
