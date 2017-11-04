package ReservaLab.TrabalhoPOO2.Principal;
import ReservaLab.TrabalhoPOO2.DAO.LaboratorioDao;
import ReservaLab.TrabalhoPOO2.Model.Funcionarios;
import ReservaLab.TrabalhoPOO2.Model.Laboratorio;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"ReservaLab.TrabalhoPOO2.Principal", "ReservaLab.TrabalhoPOO2.Model",
    "ReservaLab.TrabalhoPOO2.DAO", "ReservaLab.TrabalhoPOO2.Controller"})
@EnableAutoConfiguration
public class TrabalhoPoo2Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context
                = SpringApplication.run(TrabalhoPoo2Application.class);
    }
    
}
