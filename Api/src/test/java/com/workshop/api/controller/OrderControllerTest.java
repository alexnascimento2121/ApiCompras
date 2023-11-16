package com.workshop.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workshop.api.ApiApplication;
import com.workshop.api.DadosMok;
import com.workshop.api.model.Order;
import com.workshop.api.service.OrderService;
import com.workshop.api.service.exception.EntityNotFoundException;
import com.workshop.api.service.rabbitmq.Producer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ApiApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class OrderControllerTest {
	/*teste de integracao*/
	
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ObjectMapper mapper;
    
    @MockBean
	private Producer producer;


    private static final String ROTA_ORDER = "/order";

    private DadosMok dadosMok = new DadosMok();

    @DisplayName("POST - Deve cadastrar um novo pedido com sucesso no banco de dados")
    @Test
    void deveCadastrarPedidoComSucesso() throws Exception {
        var pedidoBody = dadosMok.getOrder();
        var id = 3L;
        
        Mockito.doNothing().when(producer).sendOrder(Mockito.any(Order.class));

        mockMvc.perform(post(ROTA_ORDER)
                .content(mapper.writeValueAsString(pedidoBody))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        
        Order orderSalvo = orderService.searchOrFailById(id);
        assertEquals(orderSalvo.getId(), id);
        assertNotNull(orderSalvo);

    }
    @DisplayName("GET - Deve buscar o pedido com sucesso na base de dados")
    @Test
    void deveBuscarPedidoComSucesso() throws Exception {
        var id = 1L;

        mockMvc.perform(get(ROTA_ORDER.concat("/" + id)))
                .andDo(print())
                .andExpect(status().isOk());
    }
    
    @DisplayName("GET - Deve falhar ao buscar pedido que nao existe")
    @Test
    void deveFalharAoBuscarPedidoQueNaoExiste() throws Exception {
        var id = 2L;

        mockMvc.perform(get(ROTA_ORDER.concat("/" + id)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @DisplayName("DELETE - Deve excluir um pedido com sucesso")
    @Test
    void deveExcluirUmPedidoComSucesso() throws Exception {
        var id = 1L;

        mockMvc.perform(delete(ROTA_ORDER.concat("/" + id)))
                .andDo(print())
                .andExpect(status().isNoContent());

        Throwable exception = assertThrows(EntityNotFoundException.class, () -> orderService.delete(id));

        assertEquals("O pedido de id: " + id + " nao existe na base de dados!", exception.getMessage());
    }
}
