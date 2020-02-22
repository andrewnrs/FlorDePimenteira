package anresdev.restaurante.api.controller;

import anresdev.restaurante.api.model.ItemPedido;
import anresdev.restaurante.api.model.Pedido;
import anresdev.restaurante.api.service.ItemPedidoService;
import anresdev.restaurante.api.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final ItemPedidoService itemPedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService, ItemPedidoService itemPedidoService) {
        this.pedidoService = pedidoService;
        this.itemPedidoService = itemPedidoService;
    }

    @CrossOrigin
    @GetMapping
    public List<Pedido> TodosPedidos(){
        return pedidoService.BuscaTodos();
    }

    @CrossOrigin
    @GetMapping("/abertos")
    public List<Pedido> TodosOsPedidosEmAberto(){

        return pedidoService.BuscaTodosAbertos();
    }

    @CrossOrigin
    @GetMapping("/{idPedido}")
    public Optional<Pedido> BuscaPedidoPorId(@PathVariable Integer idPedido){

        Optional<Pedido> item = pedidoService.BuscaPedidoPorId(idPedido);

        if(item.isPresent()) {
            return item;
        }
        else {
            return null;
        }
    }

    @CrossOrigin
    @GetMapping("/{idPedido}/itens")
    public List<ItemPedido> TodosItensDoPedido(@PathVariable Integer idPedido){
        return itemPedidoService.BuscaItensDoPedido(idPedido);
    }

    @PutMapping("/{idPedido}")
    public ResponseEntity<?> AtualizaPedido(@PathVariable Integer idPedido, @Validated @RequestBody Pedido pedido ) {
        Pedido pedidoManager = pedidoService.atualiza(idPedido, pedido );
        return ResponseEntity.ok(pedidoManager );
    }

    @CrossOrigin
    @DeleteMapping("/{idPedido}")
    public HttpStatus RemoverPedido(@PathVariable Integer idPedido){
        //busacr todos itens
        List<ItemPedido> itens = itemPedidoService.BuscaItensDoPedido(idPedido);

        //deletar todos os itens
        for (ItemPedido item: itens ) {
            itemPedidoService.ExcluiItemPedido(item);
        }
        //deletar o pedido

        pedidoService.ExcluiPedido(idPedido);
        Boolean deletado = false;
        if(pedidoService.buscaPor(idPedido) == null){
            deletado = true;
        }
        HttpStatus responseStatus = deletado ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return responseStatus;
    }

    @CrossOrigin
    @DeleteMapping("/{idPedido}/itens/{idItem}")
    public List<ItemPedido> RemoverItemDoPedido(@PathVariable Integer idPedido, @PathVariable Integer idItem){

        itemPedidoService.ExcluiItemPedido(idItem);

        return itemPedidoService.BuscaItensDoPedido(idPedido);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> CadastraNovoPedido(@Validated @RequestBody Pedido pedido) {

        Pedido pedidoSalvo = pedidoService.CadastraPedido(pedido );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoSalvo );
    }

    @CrossOrigin
    @GetMapping("/itens")
    public List<ItemPedido> BuscaTodosItens(){
        return itemPedidoService.BuscaTodos();
    }

    @CrossOrigin
    @GetMapping("/itens/{idItem}")
    public Optional<ItemPedido> BuscaItemPorId(@PathVariable Integer idItem) {

        Optional<ItemPedido> item = itemPedidoService.buscaItemPedidoPor(idItem );

        if(item.isPresent()) {
            return item;
        }
        else {
            return null;
        }
    }

    @CrossOrigin
    @PostMapping("/itens")
    public ResponseEntity<?> SalvaItem(@Validated @RequestBody ItemPedido item) {

        ItemPedido itemSalvo = itemPedidoService.CadastraItem(item );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(itemSalvo );
    }

    @PutMapping("/itens/{idItem}")
    public ResponseEntity<?> AtualizaItem(@PathVariable Integer idItem, @Validated @RequestBody ItemPedido itemPedido ) {
        ItemPedido itemManager = itemPedidoService.atualiza(idItem, itemPedido );
        return ResponseEntity.ok(itemManager );
    }


}
