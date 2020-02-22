//package anresdev.restaurante.api.controller;
//
//import anresdev.restaurante.api.model.Categoria;
//import anresdev.restaurante.api.model.ItemCardapio;
//import anresdev.restaurante.api.model.ItemPedido;
//import anresdev.restaurante.api.model.Pedido;
//import anresdev.restaurante.api.repository.CategoriaRepository;
//import anresdev.restaurante.api.repository.ItemCardapioRepository;
//import anresdev.restaurante.api.repository.ItemPedidoRepository;
//import anresdev.restaurante.api.repository.PedidoRepository;
//import anresdev.restaurante.api.service.ItemCardapioService;
//import anresdev.restaurante.api.service.ItemPedidoService;
//import anresdev.restaurante.api.service.PedidoService;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.tomcat.util.http.fileupload.FileItemStream;
//import org.junit.BeforeClass;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.sql.Timestamp;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class PedidoControllerTest {
//
//    @Autowired
//    private TestRestTemplate testRestTemplate;
//    @Autowired
//    private ItemPedidoRepository itemPedidoRepository;
//    @Autowired
//    private PedidoRepository pedidoRepository;
//    @Autowired
//    private PedidoService pedidoService;
//    @Autowired
//    private ItemPedidoService itemPedidoService;
//
//    ObjectMapper objectMapper = new ObjectMapper();
//
//    ItemPedido itemPedido, itemPedido2;
//
//    Pedido pedido, pedido2;
//
//    @BeforeAll
//    public void inicio(){
//        itemPedidoRepository.deleteAll();
//        pedidoRepository.deleteAll();
//        pedido = salvaPedido();
//    }
//
//    @AfterAll
//    public void fim(){
//        itemPedidoRepository.deleteAll();
//        pedidoRepository.deleteAll();
//    }
//
//    @BeforeEach
//    public void start(){
//        itemPedido = itemExemplo();
//        itemPedido2 = itemExemplo2();
//    }
//
//    @AfterEach
//    public void end() {
//        itemPedidoRepository.deleteAll();
//    }
//
//    @Test
//    public void contextLoads(){
//        Assertions.assertTrue(true);
//    }
//
//    @Test
//    public void deveRecuperarItensSalvos() throws IOException {
//
//        itemPedidoService.CadastraItem(itemPedido);
//        itemPedidoService.CadastraItem(itemPedido2);
//
//        List<ItemPedido> itensCadastrados = new ArrayList<>();
//
//        itensCadastrados.add(itemPedido);
//        itensCadastrados.add(itemPedido2);
//
//        ResponseEntity<String> resposta = testRestTemplate.exchange("/pedido", HttpMethod.GET, null, String.class);
//        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
//
//    System.out.println(resposta);
//        String json = resposta.getBody();
//        List<ItemPedido> Itens = objectMapper.readValue(json, new TypeReference<List<ItemPedido>>(){});
//
//        Assertions.assertEquals(itensCadastrados.size(), Itens.size());
//        itensCadastrados.forEach( i -> {
//            Assertions.assertEquals(i.getEstado(), "Teste");
//        });
//    }
//
//    @Test
//    public void deveSalvarPedido() {
//
//        itemPedido = itemExemplo();
//
//        HttpEntity<ItemPedido> httpEntity = new HttpEntity<>(itemPedido);
//
//
//        ResponseEntity<ItemPedido> resposta =
//                testRestTemplate.exchange("/pedido",HttpMethod.POST, httpEntity, ItemPedido.class);
//
//        Assertions.assertEquals(HttpStatus.CREATED,resposta.getStatusCode());
//
//        ItemPedido resultado = resposta.getBody();
//
//        Assertions.assertNotNull(resultado.getId());
//        Assertions.assertEquals(itemPedido.getValorUnit() , resultado.getValorUnit());
//        Assertions.assertEquals(itemPedido.getEstado(), resultado.getEstado());
//
//    }
//
//    private Pedido salvaPedido(){
//        Pedido pedido = new Pedido();
//        pedido.setMesa(1);
//        pedido.setEstado("Teste");
//        pedido.setTotal(BigDecimal.valueOf(0));
//    return pedido;
//    }
//
//    private Pedido pedido2(){
//        Pedido pedido = new Pedido();
//        pedido.setMesa(2);
//        pedido.setEstado("Teste");
//        pedido.setTotal(BigDecimal.valueOf(0));
//        return pedido;
//    }
//
//    private ItemPedido itemExemplo(){
//        ItemPedido item = new ItemPedido();
//        item.setValorUnit(BigDecimal.valueOf(1));
//        item.setEstado("AGUARDANDO");
//        item.setPedido(pedido);
//        item.setQtd(1);
//        return item;
//    }
//
//    private ItemPedido itemExemplo2(){
//        ItemPedido item = new ItemPedido();
//        item.setValorUnit(BigDecimal.valueOf(1));
//        item.setEstado("AGUARDANDO");
//        item.setPedido(pedido);
//        item.setQtd(1);
//        return item;
//    }
//}
