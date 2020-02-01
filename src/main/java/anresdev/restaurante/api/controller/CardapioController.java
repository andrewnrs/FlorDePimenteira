package anresdev.restaurante.api.controller;

import anresdev.restaurante.api.model.ItemCardapio;
import anresdev.restaurante.api.service.CardapioService;
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
//@ExposesResourceFor(ItemCardapio.class)
@RequestMapping(value="/cardapio", produces="application/json")
public class CardapioController {

        private final CardapioService cardapioService;

        @Autowired
        public CardapioController(CardapioService cardapioService) {
            this.cardapioService = cardapioService;
        }

        @CrossOrigin
        @GetMapping
        public List<ItemCardapio> BuscaTodosItens(){
            return cardapioService.BuscaTodos();
        }

        @CrossOrigin
        @GetMapping("/disponiveis")
        public List<ItemCardapio> BuscaItensDisponiveis(){
            return cardapioService.BuscaDisponiveis();
        }

        @CrossOrigin
        @DeleteMapping(value="{id}")
        public HttpStatus RemoveItemDoCardapio(@PathVariable Integer id){
            cardapioService.RemoveItemDoCardapio(id);
            Boolean deletado = false;
            if(cardapioService.BuscaItemDoCardapioPorId(id) == null){
                deletado = true;
            }
            HttpStatus responseStatus = deletado ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
            return responseStatus;
        }

        @CrossOrigin
        @GetMapping(value="{id}")
        public Optional<ItemCardapio> getPedidoPorId(@PathVariable Integer id){

            Optional<ItemCardapio> item = cardapioService.BuscaItemDoCardapioPorId(id);

            if(item.isPresent()) {
                return item;
            }
            else {
                return null;
            }
        }

        @PostMapping
        public ResponseEntity<?> adiciona(@Validated @RequestBody ItemCardapio item, HttpServletResponse response) {

            ItemCardapio itemSalvo = cardapioService.CadastraItemNoCardapio(item );

            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequestUri()
                    .path("/{id}")
                    .buildAndExpand(itemSalvo.getId())
                    .toUri();

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(itemSalvo );
        }


}
