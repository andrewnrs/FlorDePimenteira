package anresdev.restaurante.api.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
public class Pedido {
    private Integer id;
    private BigDecimal total;
    private Integer mesa;
    //private EstadoPedido estado;
    private String estado;
    private Timestamp dataPedido;
    private String obs;

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
    @Column(name = "total", nullable = true, precision = 2, columnDefinition = "DECIMAL(10,2) DEFAULT '0.00'")
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    //@Basic
    @Column(name = "mesa", nullable = true, columnDefinition = "INT DEFAULT '0'")
    public Integer getMesa() {
        return mesa;
    }

    public void setMesa(Integer mesa) {
        this.mesa = mesa;
    }

    //@Basic
    @Column(name = "estado", nullable = true, length = 15, columnDefinition = "ENUM('ABERTO', 'AGUARDANDO', 'FECHADO', 'CANCELADO') DEFAULT 'ABERTO'")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    //@Basic
    @Column(name = "data_pedido", nullable = true, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    public Timestamp getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Timestamp dataPedido) {
        this.dataPedido = dataPedido;
    }

    //@Basic
    @Column(name = "obs", nullable = true, length = 80, columnDefinition = "VARCHAR(80) NULL DEFAULT NULL")
    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pedido pedido = (Pedido) o;

        if (id != null ? !id.equals(pedido.id) : pedido.id != null) return false;
        if (total != null ? !total.equals(pedido.total) : pedido.total != null) return false;
        if (mesa != null ? !mesa.equals(pedido.mesa) : pedido.mesa != null) return false;
        if (estado != null ? !estado.equals(pedido.estado) : pedido.estado != null) return false;
        if (dataPedido != null ? !dataPedido.equals(pedido.dataPedido) : pedido.dataPedido != null) return false;
        if (obs != null ? !obs.equals(pedido.obs) : pedido.obs != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (mesa != null ? mesa.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (dataPedido != null ? dataPedido.hashCode() : 0);
        result = 31 * result + (obs != null ? obs.hashCode() : 0);
        return result;
    }
}
