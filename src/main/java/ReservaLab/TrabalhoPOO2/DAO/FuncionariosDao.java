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
public class FuncionariosDao extends Generica<Funcionarios>{
    
        @Autowired
    private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	} 
    
}
