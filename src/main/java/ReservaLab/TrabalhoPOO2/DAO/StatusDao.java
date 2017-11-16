package ReservaLab.TrabalhoPOO2.DAO;

import ReservaLab.TrabalhoPOO2.Model.StatusLab;
import java.util.*;
import ReservaLab.TrabalhoPOO2.Model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class StatusDao extends Generica<StatusLab> {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    @SuppressWarnings("unchecked")
    public List<StatusLab> StatusPorFuncionario(Funcionarios f ){
        
        List<StatusLab> status = getSession().createQuery(" from StatusLab where professor = :professor").
                setParameter("professor", f).list();
        
                
        return status;        
    }
    
}
