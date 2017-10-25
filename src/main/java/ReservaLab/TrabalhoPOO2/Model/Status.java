package ReservaLab.TrabalhoPOO2.Model;

import javax.persistence.*;

@Entity
@Table(name = "StatusLab")
public class Status {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long Cod_status;
    
    boolean manhaPrimeiro;//0 para disponivel 1 para reservado

    @OneToOne
    Funcionarios funcionarioManhaP;//quem reserveou manha primeiro horario        
    
    boolean manhaSegundo;

    @OneToOne
    Funcionarios funcionarioManhaS;
    
    boolean tardePrimeiro;
    
    @OneToOne
    Funcionarios funcionarioTardeP;

    boolean tardeSegundo;
    
    @OneToOne
    Funcionarios funcionarioTardeS;

    boolean noitePrimeiro;
    
    @OneToOne
    Funcionarios funcionarioNoiteP;

    boolean noiteSegundo;
    
    @OneToOne
    Funcionarios funcionarioNoiteS;
    
}
