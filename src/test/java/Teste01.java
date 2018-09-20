
import br.org.rfdouro.prjmavenweb01.models.Pessoa;
import br.org.rfdouro.prjmavenweb01.util.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author romulo.douro
 */
public class Teste01 {
 public static void main(String[] args) {
  
  EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
  
  Pessoa p = new Pessoa();
  p.setNome("carla");
  entityManager.persist(p);
  
  entityManager.getTransaction().begin();
  entityManager.getTransaction().commit();
  

  List<Pessoa> l = entityManager.createQuery("select p from Pessoa p", Pessoa.class).getResultList();
  entityManager.clear();
  JPAUtil.shutdown();
  
  for(Pessoa p2 : l){
   System.out.println(""+p2.getNome());
  }
 }
}
