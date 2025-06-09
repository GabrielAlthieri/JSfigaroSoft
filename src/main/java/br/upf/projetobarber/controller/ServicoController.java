package br.upf.projetobarber.controller;

import br.upf.projetobarber.entity.ServicoEntity;
import br.upf.projetobarber.facade.ServicoFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ServicoController implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private ServicoFacade servicoFacade;

    private ServicoEntity servico;
    private List<ServicoEntity> servicos;

    @PostConstruct
    public void init() {
        servico = null; // ← começa sem seleção
        servicos = servicoFacade.findAll();
    }

    // Método chamado ao clicar em "Incluir Veículo"
    public void novo() {
        this.servico = new ServicoEntity();
    }

    // Salvar novo ou editar existente
    public void salvar() {
        if (servico != null) {
            if (servico.getId() == null) {
                servicoFacade.create(servico);
            } else {
                servicoFacade.edit(servico);
            }
            servico = null; // ← limpa seleção após salvar
            servicos = servicoFacade.findAll(); // Atualiza a tabela
        }
    }

    // Excluir veículo
    public void excluir(ServicoEntity v) {
        if (v != null && v.getId() != null) {
            servicoFacade.remove(v);
            servicos = servicoFacade.findAll();
            servico = null; // ← limpa seleção após excluir
        }
    }

    // Getters e Setters
    public ServicoEntity getServico() {
        return servico; // ← NÃO cria novo objeto aqui
    }

    public void setServico(ServicoEntity servico) {
        this.servico = servico;
    }

    public List<ServicoEntity> getServicos() {
        return servicos;
    }

    public void setServicos(List<ServicoEntity> servicos) {
        this.servicos = servicos;
    }

    public void atualizarDetalhes() {
        // opcional
    }
}