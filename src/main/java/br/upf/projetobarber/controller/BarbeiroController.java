/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.upf.projetobarber.controller;

import br.upf.projetobarber.entity.BarbeiroEntity;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Named(value = "barbeiroController")
@SessionScoped
public class BarbeiroController implements Serializable {

  
    public BarbeiroController() {
    }
    
    
    private BarbeiroEntity barbeiro = new BarbeiroEntity();
    
    private List<BarbeiroEntity> barbeiroList = new ArrayList<>();

    public BarbeiroEntity getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(BarbeiroEntity barbeiro) {
        this.barbeiro = barbeiro;
    }

    public List<BarbeiroEntity> getBarbeiroList() {
        return barbeiroList;
    }

    public void setBarbeiroList(List<BarbeiroEntity> barbeiroList) {
        this.barbeiroList = barbeiroList;
    }
    
    private int gerarID() {
        int id = 1;
        if (!barbeiroList.isEmpty()) {
            id = barbeiroList.size() + 1;
        }
        return id;
    }
    
    private void exibirMensagem() {
        String msg = "barbeiro adicionado" + barbeiro.getNome();
        
        FacesMessage fm = new FacesMessage(msg);
        FacesContext.getCurrentInstance().addMessage(msg, fm);
    }
}

    
