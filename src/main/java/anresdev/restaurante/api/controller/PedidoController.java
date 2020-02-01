package anresdev.restaurante.api.controller;

import anresdev.restaurante.api.model.Pedido;
import anresdev.restaurante.api.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin
@RestController
//@ExposesResourceFor(Pedido.class)
@RequestMapping(value="/pedido", produces="application/json")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @CrossOrigin
    @GetMapping
    public List<Pedido> TodosOsPedidos(){
        return pedidoService.BuscaTodos();
    }

    @CrossOrigin
    @GetMapping("/disponiveis")
    public List<Pedido> Pedidos(){
        return pedidoService.BuscaTodos();
    }
}
