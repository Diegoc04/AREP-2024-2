package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	@MockBean
	private CustomerRepository customerRepository;

	@Test
	void contextLoads() {
		// Esta prueba verifica si el contexto de la aplicación se carga correctamente.
	}

	@Test
	void testGetAllCustomers() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		// Simula una lista de clientes para la prueba
		List<Customer> customers = Arrays.asList(
				new Customer("John", "Doe", "123 Street", 200, 40, "Description1"),
				new Customer("Jane", "Doe", "456 Street", 150, 30, "Description2"));

		// Simula el comportamiento del servicio para que retorne la lista de clientes
		when(customerService.getAllCustomer()).thenReturn(customers);

		// Realiza una solicitud GET a la ruta "/Customers/all" y verifica que la respuesta sea correcta
		mockMvc.perform(get("/Customers/all"))
				.andExpect(status().isOk()) // Verifica que el estado HTTP sea 200 (OK)
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Verifica que el contenido sea JSON
				.andExpect(jsonPath("$.size()").value(customers.size())) // Verifica que la cantidad de clientes en la respuesta sea correcta
				.andExpect(jsonPath("$[0].firstName").value("John")) // Verifica el nombre del primer cliente
				.andExpect(jsonPath("$[1].firstName").value("Jane")); // Verifica el nombre del segundo cliente

		// Verifica que el método getAllCustomer() del servicio se haya llamado una vez
		verify(customerService, times(1)).getAllCustomer();
	}

	@Test
	void testCreateCustomer() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		// Crea un objeto de cliente simulado
		Customer customer = new Customer("John", "Doe", "123 Street", 200, 40, "Description1");

		// Simula el comportamiento del servicio para que retorne el cliente cuando se guarde
		when(customerService.saveCustomer(any(Customer.class))).thenReturn(customer);

		// Realiza una solicitud POST a la ruta "/Customers/createCustomer/create" y verifica que el cliente se cree correctamente
		mockMvc.perform(post("/Customers/createCustomer/create")
				.flashAttr("customer", customer)) // Simula el envío del objeto cliente como atributo flash
				.andExpect(redirectedUrl("/Customers")) // Verifica que redirija a "/Customers"
				.andExpect(flash().attribute("successMessage", "Cliente creado exitosamente.")); // Verifica que el mensaje flash sea correcto

		// Verifica que el método saveCustomer() del servicio se haya llamado una vez
		verify(customerService, times(1)).saveCustomer(any(Customer.class));
	}

	@Test
	void testUpdateCustomer() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		// Crea un objeto de cliente simulado
		Customer customer = new Customer("John", "Doe", "123 Street", 200, 40, "Description1");

		// Simula el comportamiento del servicio para que retorne el cliente actualizado
		when(customerService.updateCustomer(eq(1L), any(Customer.class))).thenReturn(customer);

		// Realiza una solicitud POST a la ruta "/Customers/update/1" y verifica que el cliente se actualice correctamente
		mockMvc.perform(post("/Customers/update/1")
				.flashAttr("customer", customer)) // Simula el envío del objeto cliente como atributo flash
				.andExpect(redirectedUrl("/Customers")) // Verifica que redirija a "/Customers"
				.andExpect(flash().attribute("successMessage", "Cliente actualizado exitosamente.")); // Verifica que el mensaje flash sea correcto

		// Verifica que el método updateCustomer() del servicio se haya llamado una vez con el ID correcto
		verify(customerService, times(1)).updateCustomer(eq(1L), any(Customer.class));
	}

	@Test
	void testDeleteCustomer() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		// Realiza una solicitud POST a la ruta "/Customers/delete/1" y verifica que el cliente se elimine correctamente
		mockMvc.perform(post("/Customers/delete/1"))
				.andExpect(redirectedUrl("/Customers")) // Verifica que redirija a "/Customers"
				.andExpect(flash().attribute("successMessage", "Cliente eliminado exitosamente.")); // Verifica que el mensaje flash sea correcto

		// Verifica que el método deleteCustomer() del servicio se haya llamado una vez con el ID correcto
		verify(customerService, times(1)).deleteCustomer(1L);
	}
}



