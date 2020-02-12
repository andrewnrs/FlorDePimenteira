package anresdev.restaurante.api.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "item_pedido", schema = "restaurante")
public class ItemPedido {
    private Integer id;
    private Integer qtd;
    private BigDecimal valorUnit;
    //private EstadoItem estado;
    private String estado;
    private Timestamp dataPedido;
    private Timestamp dataEntregue;
    private Pedido pedido;
    private ItemCardapio itemCardapio;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //@Basic
    @Column(name = "qtd", nullable = false, columnDefinition = "INT NOT NULL")
    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    //@Basic
    @Column(name = "valor_unit", nullable = true, precision = 2, columnDefinition = " DECIMAL(10,2) DEFAULT NULL")
    public BigDecimal getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(BigDecimal valorUnit) {
        this.valorUnit = valorUnit;
    }

    //@Basic
    @Column(name = "estado", nullable = true, length = 15, columnDefinition = "ENUM('AGUARDANDO', 'EM_PREPARO', 'PRONTO', 'ENTREGUE', 'CANCELADO') NULL DEFAULT 'AGUARDANDO'")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    //@Basic
    @Column(name = "data_pedido", nullable = true, columnDefinition = "DATETIME NULL DEFAULT CURRENT_TIMESTAMP")
    public Timestamp getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Timestamp dataPedido) {
        this.dataPedido = dataPedido;
    }

    //@Basic
    @Column(name = "data_entregue", nullable = true, columnDefinition = "DATETIME NULL DEFAULT NULL")
    public Timestamp getDataEntregue() {
        return dataEntregue;
    }

    public void setDataEntregue(Timestamp dataEntregue) {
        this.dataEntregue = dataEntregue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemPedido that = (ItemPedido) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (qtd != null ? !qtd.equals(that.qtd) : that.qtd != null) return false;
        if (valorUnit != null ? !valorUnit.equals(that.valorUnit) : that.valorUnit != null) return false;
        if (estado != null ? !estado.equals(that.estado) : that.estado != null) return false;
        if (dataPedido != null ? !dataPedido.equals(that.dataPedido) : that.dataPedido != null) return false;
        if (dataEntregue != null ? !dataEntregue.equals(that.dataEntregue) : that.dataEntregue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (qtd != null ? qtd.hashCode() : 0);
        result = 31 * result + (valorUnit != null ? valorUnit.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (dataPedido != null ? dataPedido.hashCode() : 0);
        result = 31 * result + (dataEntregue != null ? dataEntregue.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "pedido_id", referencedColumnName = "id", nullable = false)
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @ManyToOne
    @JoinColumn(name = "item_cardapio_id", referencedColumnName = "id", nullable = false)
    public ItemCardapio getItemCardapio() {
        return itemCardapio;
    }

    public void setItemCardapio(ItemCardapio itemCardapio) {
        this.itemCardapio = itemCardapio;
    }

}
