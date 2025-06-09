package br.upf.projetobarber.facade;

import br.upf.projetobarber.entity.BarbeiroEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class BarbeiroFacade extends AbstractFacade<BarbeiroEntity> {

    @PersistenceContext(unitName = "ProjetojfprimefacesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BarbeiroFacade() {
        super(BarbeiroEntity.class);
    }

    
     

  public List<BarbeiroEntity> buscarTodos() {
      List<BarbeiroEntity> entityList = new ArrayList<>();
      try {
          Query query = getEntityManager().createQuery("SELECT b FROM BarbeiroEntity b ORDER BY b.nome");
          entityList = query.getResultList();} catch (Exception e) {
          System.out.println("Erro: " + e);}
      return entityList;}

   
     

  public BarbeiroEntity buscarPorEmail(String email, String senha) {
      try {
          Query query = getEntityManager().createQuery("SELECT b FROM BarbeiroEntity b " +"WHERE LOWER(TRIM(b.email)) = LOWER(TRIM(:email)) AND TRIM(b.senha) = TRIM(:senha)");
          query.setParameter("email", email);
          query.setParameter("senha", senha);
          List<BarbeiroEntity> resultado = query.getResultList();
          if (!resultado.isEmpty()) {
              return resultado.get(0);}} catch (Exception e) {
          System.out.println("Erro ao buscar barbeiro: " + e);}
      return null;}
}