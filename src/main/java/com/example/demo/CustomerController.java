package com.example.demo;

import javax.management.relation.RelationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Controlador para gestionar las operaciones relacionadas con los clientes.
 */
@Controller
@RequestMapping("/Clientes")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * @param model 
     * @param page  
     * @return 
     */
    @GetMapping
    public String listCustomers(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Customer> customers = customerService.getCustomers(page, 10);
        model.addAttribute("customers", customers.getContent());
        model.addAttribute("totalPages", customers.getTotalPages());
        model.addAttribute("currentPage", page);
        return "index";
    }

    /**
     * @return 
     */
    @GetMapping("/crearCliente")
    public String create() {
        return "create";
    }

    /**
     * @param customer            
     * @param redirectAttributes  
     * @return 
     */
    @PostMapping("/crearCliente/crear")
    public String createCustomer(@ModelAttribute Customer customer, RedirectAttributes redirectAttributes) {
        try {
            customerService.saveCustomer(customer);
            redirectAttributes.addFlashAttribute("successMessage", "Se creó con exito el cliente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Hubo un error al crear el cliente.");
        }
        return "redirect:/Clientes";
    }


    /**
     * Elimina un cliente y redirige a la lista de clientes.
     *
     * @param id                  ID del cliente a eliminar.
     * @param redirectAttributes   Atributos para redireccionar.
     * @return Redirección a la lista de clientes.
     */
    @PostMapping("/eleminar/{id}")
    public String deleteCustomer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            customerService.deleteCustomer(id);
            redirectAttributes.addFlashAttribute("successMessage", "Se eliminó con exito al cliente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Hubo un error al eliminar al cliente.");
        }
        return "redirect:/Clientes";
    }

    /**
     * @param id                 
     * @param newCustomerData     
     * @param redirectAttributes   
     * @return 
     */
    @PostMapping("/actualizar/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute Customer newCustomerData, RedirectAttributes redirectAttributes) {
        newCustomerData.setId(id);
        try {
            customerService.updateCustomer(id, newCustomerData);
            redirectAttributes.addFlashAttribute("successMessage", "Se actualizó con exito al cliente.");
        } catch (RelationNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "No se ha encontrado al cliente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Hubo un error al actualizar al cliente.");
        }
        return "redirect:/Clientes";
    }
    
}



