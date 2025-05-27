package br.upf.projetobarber.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "agendamento")
public class AgendamentoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private ClienteEntity cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_barbeiro", referencedColumnName = "id")
    private BarbeiroEntity barbeiro;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_servico", referencedColumnName = "id")
    private ServicosEntity servico;

    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data")
    private Date data;

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public BarbeiroEntity getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(BarbeiroEntity barbeiro) {
        this.barbeiro = barbeiro;
    }
    
     public ServicosEntity getServicos() {
        return servico;
    }

    public void setServicos (ServicosEntity servico) {
        this.servico = servico;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AgendamentoEntity other = (AgendamentoEntity) obj;
        return Objects.equals(id, other.id);
    }
}