package ReservaLab.TrabalhoPOO2.Model;

import javax.persistence.*;

@Entity
@Table (name = "Funcoes")
public class Funcoes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long Cod_funcoes;
    
    @Column
    String nomeFuncao;
    
}
