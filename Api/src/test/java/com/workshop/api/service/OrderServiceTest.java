package com.workshop.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.workshop.api.DadosMok;
import com.workshop.api.model.Order;
import com.workshop.api.repository.OrderRepository;
import com.workshop.api.service.exception.BusinessException;
import com.workshop.api.service.rabbitmq.Producer;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
	@InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private Producer producer;

    private DadosMok mock = new DadosMok();

    @DisplayName("Salvar pedido com sucesso")
    @Test
    void deveSalvarUmPedidoComSucesso() throws JsonProcessingException {
        var orderMok = mock.getOrder();

        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(orderMok);
        Mockito.doNothing().when(producer).sendOrder(Mockito.any(Order.class));

        var orderSalvo = orderService.saveOrder(orderMok);

        assertEquals(orderMok.getCep(), orderSalvo.getCep());
        assertNotNull(orderSalvo.getCep());
    }
    
    @DisplayName("Deve falhar na busca de pedido que nao existe")
    @Test
    void deveFalharNaBuscaDePedidoNaoExistente() {
        var id = 1L;

        Throwable exception = assertThrows(BusinessException.class, () -> {
            @SuppressWarnings("unused")
			Order orderSalvo = orderService.searchOrFailById(id);
        });

        assertEquals("O pedido de id: " + id + " nao existe na base de dados!", exception.getMessage());
    }
    
    @DisplayName("Deve buscar um pedido com sucesso na base de dados")
    @Test
    void deveBuscarPedidoComSucesso() {
        var orderMok = mock.getOrderSalvo();
        var id = 1L;

        Mockito.when(orderRepository.findById(id)).thenReturn(Optional.of(orderMok));

        var orderSalvo = orderService.searchOrFailById(id);

        assertEquals(orderMok.getId(), orderSalvo.getId());
        assertNotNull(orderSalvo);
        Mockito.verify(orderRepository, Mockito.atLeastOnce()).findById(id);
    }
        
    @DisplayName("Deve excluir o pedido com sucesso")
    @Test
    void deveExcluirPedidoComSucesso() {
        var pedidoMok = mock.getOrderSalvo();
        var id = 1L;

        Mockito.when(orderRepository.findById(id)).thenReturn(Optional.of(pedidoMok));
        Mockito.doNothing().when(orderRepository).deleteById(id);

        orderService.delete(id);
        Mockito.verify(orderRepository, Mockito.atLeastOnce()).deleteById(id);
    }
    
    @DisplayName("Deve falhar ao excluir o pedido que nao existe")
    @Test
    void deveFalharAoEXcluirPedidoNaoExistente() {
        var id = 1L;

        Mockito.when(orderRepository.findById(id)).thenReturn(Optional.empty());

        Throwable exception = assertThrows(BusinessException.class, () -> orderService.delete(id));

        assertEquals("O pedido de id: " + id + " nao existe na base de dados!", exception.getMessage());
    }
    

}
