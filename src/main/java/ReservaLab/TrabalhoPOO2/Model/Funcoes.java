package ReservaLab.TrabalhoPOO2.Model;

import javax.persistence.*;

@Entity
@Table (name = "Funcoes")
public class Funcoes {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Cod_funcoes;
    
    @Column
    private String nomeFuncao;
 
    
    /**
     * @return the Cod_funcoes
     */
    public long getCod_funcoes() {
        return Cod_funcoes;
    }

    /**
     * @param Cod_funcoes the Cod_funcoes to set
     */
    public void setCod_funcoes(long Cod_funcoes) {
        this.Cod_funcoes = Cod_funcoes;
    }

    /**
     * @return the nomeFuncao
     */
    public String getNomeFuncao() {
        return nomeFuncao;
    }

    /**
     * @param nomeFuncao the nomeFuncao to set
     */
    public void setNomeFuncao(String nomeFuncao) {
        this.nomeFuncao = nomeFuncao;
    }
    
}
