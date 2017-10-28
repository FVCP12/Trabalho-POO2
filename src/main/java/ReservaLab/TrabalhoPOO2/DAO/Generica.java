package ReservaLab.TrabalhoPOO2.DAO;

import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
@Transactional
public class Generica<T> {
    
    @Autowired
    private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	} 
    
        
        public void criar(T obj){
            getSession().persist(obj);
        }
        
        public void atualizar(T obj){
            getSession().merge(obj);
        }
        
        public void deletar(T obj){
            
            if(getSession().contains(obj)){
                getSession().delete(obj);
            }else{
                getSession().delete(getSession().merge(obj));
            }
        }  
            
        @SuppressWarnings("unchecked")
        public List<T> PegarTodos(Class<T> obj){
            return getSession().createQuery(" from " + obj.getName()).list();
        }
        
        @SuppressWarnings("unchecked")
        public T buscaId(Class<T> obj,long id){
            
            List<T> listaDeUm = getSession().createQuery("from "+obj.getName()+" where id= :id")
                    .setParameter("id", id).list();
            
            return listaDeUm.get(0);
            
        }
}
