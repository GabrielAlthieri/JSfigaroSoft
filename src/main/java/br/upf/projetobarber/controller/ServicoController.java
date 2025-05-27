/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.upf.projetobarber.controller;



import br.upf.projetobarber.entity.ClienteEntity;
import br.upf.projetobarber.entity.ServicosEntity;
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
    
    
    private ServicosEntity servico = new ServicosEntity();
    
    private List<ServicosEntity> servicoList = new ArrayList<>();

    public ServicosEntity getServico() {
        return servico;
    }

    public void setServico(ServicosEntity servico) {
        this.servico = servico;
    }

    public List<ServicosEntity> getServicoList() {
        return servicoList;
    }

    public void setServicoList(List<ServicosEntity> servicoList) {
        this.servicoList = servicoList;
    }

   

 
   
}

    
