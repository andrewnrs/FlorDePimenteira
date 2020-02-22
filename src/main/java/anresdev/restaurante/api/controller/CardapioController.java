package anresdev.restaurante.api.controller;

import anresdev.restaurante.api.model.ItemCardapio;
import anresdev.restaurante.api.service.ItemCardapioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/cardapio")
public class CardapioController {

        private final ItemCardapioService itemCardapioService;

        @Autowired
        public CardapioController(ItemCardapioService itemCardapioService) {
            this.itemCardapioService = itemCardapioService;
        }

        @CrossOrigin
        @GetMapping
        public List<ItemCardapio> BuscaTodosItens(){
            return itemCardapioService.BuscaTodos();
        }

        @CrossOrigin
        @GetMapping("/disponiveis")
        public List<ItemCardapio> BuscaItensDisponiveis(){
            return itemCardapioService.BuscaDisponiveis();
        }

        @CrossOrigin
        @DeleteMapping("{id}")
        public HttpStatus RemoveItemDoCardapio(@PathVariable Integer id){
            itemCardapioService.RemoveItemDoCardapio(id);
            Boolean deletado = false;
            if(itemCardapioService.BuscaItemDoCardapioPorId(id) == null){
                deletado = true;
            }
            HttpStatus responseStatus = deletado ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
            return responseStatus;
        }

        @CrossOrigin
        @GetMapping("{id}")
        public Optional<ItemCardapio> getPedidoPorId(@PathVariable Integer id){

            Optional<ItemCardapio> item = itemCardapioService.BuscaItemDoCardapioPorId(id);

            if(item.isPresent()) {
                return item;
            }
            else {
                return null;
            }
        }

        @PostMapping
        public ResponseEntity<?> adiciona(@Validated @RequestBody ItemCardapio item) {

            ItemCardapio itemSalvo = itemCardapioService.CadastraItemNoCardapio(item );

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(itemSalvo );
        }

        @PutMapping("/{id}")
        public ResponseEntity<?> atualiza(@PathVariable Integer id, @Validated @RequestBody ItemCardapio item ) {
            ItemCardapio itemManager = itemCardapioService.atualiza(id, item );
            return ResponseEntity.ok(itemManager );
        }
}
