package ReservaLab.TrabalhoPOO2.Model;

import javax.persistence.*;

@Entity
@Table(name = "Funcionarios")
public class Funcionarios {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long Cod_funcio;
    
    @Column
    String nomeFuncionario;
    
    @Column
    String ra;
    
    @Column
    String senha;
    
    @OneToOne
    Funcoes funcao;
    
}
