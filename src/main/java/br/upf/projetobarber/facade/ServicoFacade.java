package br.upf.projetobarber.facade;

import br.upf.projetobarber.entity.ServicoEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ServicoFacade extends AbstractFacade<ServicoEntity> {

    @PersistenceContext(unitName = "ProjetojfprimefacesPU")
    private EntityManager em;

    public ServicoFacade() {
        super(ServicoEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    // Se quiser expor explicitamente o método create (opcional, já está herdado)
    @Override
    public void create(ServicoEntity entity) {
        getEntityManager().persist(entity);
    }

    // Caso queira também atualizar (edit)
    @Override
    public void edit(ServicoEntity entity) {
        getEntityManager().merge(entity);
    }

    // E para deletar
    @Override
    public void remove(ServicoEntity entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    // Lista todos
    @Override
    public List<ServicoEntity> findAll() {
        return super.findAll();
    }
}