package ReservaLab.TrabalhoPOO2.Model;

import javax.persistence.*;

@Entity
@Table(name = "Laboratorio")
public class Laboratorio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long cod_labor;
    
    @Column
    String nomeLab;
    
    @Column
    String andar;
    
    
       
}
