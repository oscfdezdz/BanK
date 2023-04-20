package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CONVERSACION", schema = "taw", catalog = "")
public class ConversacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "TERMINADA", nullable = false)
    private Byte terminada;
    @ManyToOne
    @JoinColumn(name = "EMISOR", referencedColumnName = "ID", nullable = false)
    private UsuarioEntity usuarioByEmisor;
    @ManyToOne
    @JoinColumn(name = "RECEPTOR", referencedColumnName = "ID", nullable = false)
    private UsuarioEntity usuarioByReceptor;
    @OneToMany(mappedBy = "conversacionByConversacion")
    private List<MensajeEntity> mensajesById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getTerminada() {
        return terminada;
    }

    public void setTerminada(Byte terminada) {
        this.terminada = terminada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversacionEntity that = (ConversacionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(terminada, that.terminada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, terminada);
    }

    public UsuarioEntity getUsuarioByEmisor() {
        return usuarioByEmisor;
    }

    public void setUsuarioByEmisor(UsuarioEntity usuarioByEmisor) {
        this.usuarioByEmisor = usuarioByEmisor;
    }

    public UsuarioEntity getUsuarioByReceptor() {
        return usuarioByReceptor;
    }

    public void setUsuarioByReceptor(UsuarioEntity usuarioByReceptor) {
        this.usuarioByReceptor = usuarioByReceptor;
    }

    public List<MensajeEntity> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(List<MensajeEntity> mensajesById) {
        this.mensajesById = mensajesById;
    }
}
