package ReservaLab.TrabalhoPOO2.DAO;

import java.util.*;
import ReservaLab.TrabalhoPOO2.Model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class FuncionariosDao extends Generica<Funcionarios> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public Funcionarios buscarFuncionario(String ra, String senha) {

        List<Funcionarios> func = getSession().createQuery("from Funcionarios "
                + "where ra = :ra and senha = :senha").setParameter("ra", ra).setParameter("senha", senha).list();

        return func.get(0);
    }

    @SuppressWarnings("unchecked")
    public List<Funcionarios> podeReservarLab(){
        
        List<Funcionarios> funcio = getSession().createQuery("from Funcionarios a "+
                " left join fetch a.funcao f where f.Cod_funcoes = 1 or f.Cod_funcoes = 2").list();
        
        return funcio;
        
    }
    
    @SuppressWarnings("unchecked")
        public List<Funcionarios> FuncionarioPorNome(String nome){
            
            List<Funcionarios> funcios = getSession().createQuery(" from Funcionarios a"
                    + " where a.nomeFuncionario like :nome").setParameter("nome","%"+nome+"%").list();
            
            
            return funcios;
        }
    
}
