package br.upf.projetobarber.controller;

import br.upf.projetobarber.entity.BarbeiroEntity;
import br.upf.projetobarber.facade.BarbeiroFacade;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named(value = "barbeiroController")
@SessionScoped
public class BarbeiroController implements Serializable {

    @EJB
    private BarbeiroFacade ejbFacade;

    private BarbeiroEntity barbeiro = new BarbeiroEntity(); // usado para adição
    private List<BarbeiroEntity> barbeiroList = new ArrayList<>();
    private BarbeiroEntity selected; // usado para edição/exclusão

    // Getters e setters
    public BarbeiroEntity getSelected() {
        return selected;
    }

    public void setSelected(BarbeiroEntity selected) {
        this.selected = selected;
    }

    public BarbeiroEntity getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(BarbeiroEntity barbeiro) {
        this.barbeiro = barbeiro;
    }

    public List<BarbeiroEntity> getBarbeiroList() {
        barbeiroList = ejbFacade.buscarTodos();
        return barbeiroList;
    }

    public void setBarbeiroList(List<BarbeiroEntity> barbeiroList) {
        this.barbeiroList = barbeiroList;
    }

    // Preparar novo barbeiro para adicionar
    public BarbeiroEntity prepareAdicionar() {
        barbeiro = new BarbeiroEntity();
        return barbeiro;
    }

    // Adicionar novo barbeiro
    public void adicionarBarbeiro() {
        barbeiro.setDatahorareg(new Timestamp(System.currentTimeMillis()));
        persist(PersistAction.CREATE, "Registro incluído com sucesso!");
        barbeiro = new BarbeiroEntity(); // limpa o formulário após adicionar
    }

    // Editar barbeiro selecionado
    public void editarBarbeiro() {
        if (selected != null) {
            persist(PersistAction.UPDATE, "Registro alterado com sucesso!");
        } else {
            addErrorMessage("Nenhum barbeiro selecionado para edição.");
        }
    }

    // Deletar barbeiro selecionado
    public void deletarBarbeiro() {
        if (selected != null) {
            persist(PersistAction.DELETE, "Registro excluído com sucesso!");
        } else {
            addErrorMessage("Nenhum barbeiro selecionado para exclusão.");
        }
    }

    // Mensagens de erro e sucesso
    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    // Enum para ações de persistência
    public enum PersistAction {
        CREATE,
        DELETE,
        UPDATE
    }

    // Persistência genérica
    private void persist(PersistAction persistAction, String successMessage) {
        try {
            if (persistAction != null) {
                switch (persistAction) {
                    case CREATE:
                        ejbFacade.createReturn(barbeiro);
                        break;
                    case UPDATE:
                        ejbFacade.edit(selected);
                        selected = null;
                        break;
                    case DELETE:
                        ejbFacade.remove(selected);
                        selected = null;
                        break;
                }
            }
            addSuccessMessage(successMessage);
        } catch (EJBException ex) {
            String msg = "";
            Throwable cause = ex.getCause();
            if (cause != null) {
                msg = cause.getLocalizedMessage();
            }
            if (msg != null && !msg.isEmpty()) {
                addErrorMessage(msg);
            } else {
                addErrorMessage(ex.getLocalizedMessage());
            }
        } catch (Exception ex) {
            addErrorMessage(ex.getLocalizedMessage());
        }
    }
}
