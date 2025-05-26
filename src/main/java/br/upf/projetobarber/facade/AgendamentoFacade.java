package br.upf.projetobarber.facade;

import br.upf.projetobarber.entity.AgendamentoEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class AgendamentoFacade extends AbstractFacade<AgendamentoEntity> {

    @PersistenceContext(unitName = "ProjetojfprimefacesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AgendamentoFacade() {
        super(AgendamentoEntity.class);
    }

    private List<AgendamentoEntity> entityList;

    public List<AgendamentoEntity> buscarTodos() {
        entityList = new ArrayList<>();
        try {
            Query query = getEntityManager().createQuery("SELECT a FROM AgendamentoEntity a ORDER BY a.dataHora DESC");
            if (!query.getResultList().isEmpty()) {
                entityList = (List<AgendamentoEntity>) query.getResultList();
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return entityList;
    }

    public List<AgendamentoEntity> buscarPorClienteId(Integer clienteId) {
        List<AgendamentoEntity> resultado = new ArrayList<>();
        try {
            Query query = getEntityManager()
                    .createQuery("SELECT a FROM AgendamentoEntity a WHERE a.cliente.id = :clienteId");
            query.setParameter("clienteId", clienteId);
            if (!query.getResultList().isEmpty()) {
                resultado = (List<AgendamentoEntity>) query.getResultList();
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return resultado;
    }

    public List<AgendamentoEntity> buscarPorBarbeiroId(Integer BarbeiroID) {
        List<AgendamentoEntity> resultado = new ArrayList<>();
        try {
            Query query = getEntityManager()
                    .createQuery("SELECT a FROM AgendamentoEntity a WHERE a.barbeiro.id = :BarbeiroID");
            query.setParameter("BarbeiroID", BarbeiroID);
            if (!query.getResultList().isEmpty()) {
                resultado = (List<AgendamentoEntity>) query.getResultList();
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return resultado;
    }
}
