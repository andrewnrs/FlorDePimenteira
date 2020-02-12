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
//@ExposesResourceFor(Pedido.class)
@RequestMapping(value="/pedido")
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
    public List<Pedido> Todos(){
        return pedidoService.BuscaTodos();
    }

//    @CrossOrigin
//    @GetMapping("/abertos")
//    public List<Pedido> TodosOsPedidosEmAberto(){
//        return pedidoService.BuscaTodosAbertos();
//    }

    @CrossOrigin
    @GetMapping(value="/{idPedido}/itens")
    public List<ItemPedido> TodosItensDoPedido(@PathVariable Integer idPedido){
        return itemPedidoService.BuscaItensDoPedido(idPedido);
    }

    @CrossOrigin
    @DeleteMapping(value="/{idPedido}")
    public HttpStatus RemoverPedido(@PathVariable Integer idPedido){
        pedidoService.ExcluiPedido(idPedido);
        Boolean deletado = false;
        if(pedidoService.buscaPor(idPedido) == null){
            deletado = true;
        }
        HttpStatus responseStatus = deletado ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return responseStatus;
    }

    @CrossOrigin
    @DeleteMapping(value="/{idPedido}/itens/{idItem}")
    public List<ItemPedido> RemoverItemDoPedido(@PathVariable Integer idPedido, @PathVariable Integer idItem){

        itemPedidoService.ExcluiItemPedido(idItem);

        return itemPedidoService.BuscaItensDoPedido(idPedido);
    }

    @CrossOrigin
    @GetMapping("/{idPedido}")
    public Optional<Pedido> getPedidoPorId(@PathVariable Integer idPedido){

        Optional<Pedido> item = pedidoService.BuscaPedidoPorId(idPedido);

        if(item.isPresent()) {
            return item;
        }
        else {
            return null;
        }
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> novoPedido(@Validated @RequestBody Pedido pedido, HttpServletResponse response) {

        Pedido pedidoSalvo = pedidoService.CadastraPedido(pedido );

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(pedidoSalvo.getId())
                .toUri();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoSalvo );
    }

    @CrossOrigin
    @PostMapping("/itens")
    public ResponseEntity<?> adicionaItem(@Validated @RequestBody ItemPedido item, HttpServletResponse response) {

        ItemPedido itemSalvo = itemPedidoService.CadastraItem(item );

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(itemSalvo.getId())
                .toUri();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(itemSalvo );
    }

    @PutMapping("/{idPedido}")
    public ResponseEntity<?> atualizaPedido(@PathVariable Integer idPedido, @Validated @RequestBody Pedido pedido ) {
        Pedido pedidoManager = pedidoService.atualiza(idPedido, pedido );
        return ResponseEntity.ok(pedidoManager );
    }

    @PutMapping("/itens/{idItem}")
    public ResponseEntity<?> atualizaItem(@PathVariable Integer idItem, @Validated @RequestBody ItemPedido itemPedido ) {
        ItemPedido itemManager = itemPedidoService.atualiza(idItem, itemPedido );
        return ResponseEntity.ok(itemManager );
    }
}
