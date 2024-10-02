package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import javax.management.relation.RelationNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class DemoApplicationTests {

    @Mock
    private CustomerService customerService; // Mock del servicio que maneja la lógica de negocio

    @Mock
    private RedirectAttributes redirectAttributes; // Mock para las redirecciones y atributos flash

    @InjectMocks
    private CustomerController customerController; // Controlador que será probado

    @BeforeEach
    void setUp() {
        // Inicializa los mocks antes de cada prueba
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCustomerSuccess() {
        // Crea un cliente de prueba
        Customer customer = new Customer("John", "Doe", "123 Main St", 100, 1, "Test customer");

        // Simula el comportamiento del servicio al guardar un cliente
        when(customerService.saveCustomer(any(Customer.class))).thenReturn(customer);

        // Llama al método del controlador para crear el cliente
        String result = customerController.createCustomer(customer, redirectAttributes);

        // Verifica que se haya añadido el mensaje de éxito al redireccionar
        verify(redirectAttributes).addFlashAttribute("successMessage", "Se creó con exito el cliente.");
        // Verifica que el resultado de la redirección sea el esperado
        assertEquals("redirect:/Clientes", result);
    }

    @Test
    void testDeleteCustomer() {
        Long customerId = 1L; // ID del cliente a eliminar

        // Simula el comportamiento del servicio al eliminar un cliente
        doNothing().when(customerService).deleteCustomer(customerId);

        // Llama al método del controlador para eliminar el cliente
        String result = customerController.deleteCustomer(customerId, redirectAttributes);

        // Verifica que se haya añadido el mensaje de éxito al redireccionar
        verify(redirectAttributes).addFlashAttribute("successMessage", "Se eliminó con exito al cliente.");
        // Verifica que el resultado de la redirección sea el esperado
        assertEquals("redirect:/Clientes", result);
    }

    @Test
    void testUpdateCustomer() throws RelationNotFoundException {
        Long customerId = 1L; // ID del cliente a actualizar
        Customer newCustomerData = new Customer("Jane", "Doe", "456 Another St", 200, 2, "Updated customer"); // Datos actualizados del cliente

        // Simula el comportamiento del servicio al actualizar un cliente
        when(customerService.updateCustomer(any(Long.class), any(Customer.class))).thenReturn(newCustomerData);

        // Llama al método del controlador para actualizar el cliente
        String result = customerController.updateCustomer(customerId, newCustomerData, redirectAttributes);

        // Verifica que se haya añadido el mensaje de éxito al redireccionar
        verify(redirectAttributes).addFlashAttribute("successMessage", "Se actualizó con exito al cliente.");
        // Verifica que el resultado de la redirección sea el esperado
        assertEquals("redirect:/Clientes", result);
    }
}

