package anresdev.restaurante.api.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item_cardapio", schema = "restaurante")
public class ItemCardapio {
    private Integer id;
    private BigDecimal valorVigente;
    private String nome;
    private String descricao;
    private Boolean disponivel;
    private Categoria categoria;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "valor_vigente", nullable = false, precision = 2)
    public BigDecimal getValorVigente() {
        return valorVigente;
    }

    public void setValorVigente(BigDecimal valorVigente) {
        this.valorVigente = valorVigente;
    }

    @Basic
    @Column(name = "nome", nullable = false, length = 145)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Basic
    @Column(name = "descricao", nullable = true, length = 145)
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Basic
    @Column(name = "disponivel", nullable = true)
    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemCardapio that = (ItemCardapio) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (valorVigente != null ? !valorVigente.equals(that.valorVigente) : that.valorVigente != null) return false;
        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (descricao != null ? !descricao.equals(that.descricao) : that.descricao != null) return false;
        if (disponivel != null ? !disponivel.equals(that.disponivel) : that.disponivel != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (valorVigente != null ? valorVigente.hashCode() : 0);
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (disponivel != null ? disponivel.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", nullable = false)
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
