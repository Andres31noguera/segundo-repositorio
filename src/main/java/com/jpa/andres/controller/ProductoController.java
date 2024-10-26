package com.jpa.andres.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jpa.andres.service.ProductoService;
import com.jpa.andres.model.Producto;

import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.getAllProductos());
        model.addAttribute("count", productoService.countProductos());
        return "lista-productos"; // Carga la plantilla Thymeleaf lista-productos.html
    }

    @GetMapping("/crear")
    public String crearProductoForm(Model model) {
        model.addAttribute("producto", new Producto());
        return "crear-producto"; // Carga la vista crear-producto.html
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute("producto") Producto producto) {
        productoService.saveProducto(producto);
        return "redirect:/productos"; // Redirige a lista-productos.html
    }

    @GetMapping("/editar/{id}")
    public String editarProductoForm(@PathVariable Long id, Model model) {
        Optional<Producto> producto = productoService.getProductoById(id);
        producto.ifPresent(value -> model.addAttribute("producto", value));
        return "editar-producto"; // Carga la vista editar-producto.html
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarProducto(@PathVariable Long id, @ModelAttribute("producto") Producto producto) {
        productoService.saveProducto(producto);
        return "redirect:/productos"; // Redirige a lista-productos.html
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Producto> productoOpt = productoService.getProductoById(id);
        productoService.deleteProducto(id);
        redirectAttributes.addFlashAttribute("mensaje", "Producto eliminado exitosamente.");
        return "redirect:/productos";
    }
}
