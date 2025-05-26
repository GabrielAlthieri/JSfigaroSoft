/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.upf.projetobarber.controller;


import br.upf.projetobarber.entity.AgendaEntity;
import br.upf.projetobarber.entity.ClienteEntity;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Named(value = "servicoController")
@SessionScoped
public class ServicoController implements Serializable {

  
    public ServicoController() {
    }
    
    
    private AgendaEntity agenda = new AgendaEntity();
    
    private List<AgendaEntity> agendaList = new ArrayList<>();

    public AgendaEntity getAgenda() {
        return agenda;
    }

    public void setAgenda(AgendaEntity agenda) {
        this.agenda = agenda;
    }

    public List<AgendaEntity> getAgendaList() {
        return agendaList;
    }

    public void setAgendaList(List<AgendaEntity> agendaList) {
        this.agendaList = agendaList;
    }

  
  
   
}

    
