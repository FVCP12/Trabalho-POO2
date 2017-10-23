package ReservaLab.TrabalhoPOO2.Model;

import javax.persistence.*;

@Entity
@Table(name = "Funcionarios")
public class Funcionarios {

  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Cod_funcio;
    
    @Column
    private String nomeFuncionario;
    
    @Column
    private String ra;
    
    @Column
    private String senha;
    
    @OneToOne
    private Funcoes funcao;
    
      /**
     * @return the Cod_funcio
     */
    public long getCod_funcio() {
        return Cod_funcio;
    }

    /**
     * @param Cod_funcio the Cod_funcio to set
     */
    public void setCod_funcio(long Cod_funcio) {
        this.Cod_funcio = Cod_funcio;
    }

    /**
     * @return the nomeFuncionario
     */
    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    /**
     * @param nomeFuncionario the nomeFuncionario to set
     */
    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    /**
     * @return the ra
     */
    public String getRa() {
        return ra;
    }

    /**
     * @param ra the ra to set
     */
    public void setRa(String ra) {
        this.ra = ra;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the funcao
     */
    public Funcoes getFuncao() {
        return funcao;
    }

    /**
     * @param funcao the funcao to set
     */
    public void setFuncao(Funcoes funcao) {
        this.funcao = funcao;
    }
    
    
}
