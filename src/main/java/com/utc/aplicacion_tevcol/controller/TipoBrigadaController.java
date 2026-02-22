package com.utc.aplicacion_tevcol.controller;

import com.utc.aplicacion_tevcol.entity.TipoBrigada;
import com.utc.aplicacion_tevcol.service.TipoBrigadaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/brigadas")
public class TipoBrigadaController {

    private final TipoBrigadaService service;

    public TipoBrigadaController(TipoBrigadaService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", service.listar());
        return "brigadas/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("tipo", new TipoBrigada());
        return "brigadas/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("tipo") TipoBrigada tipo, Model model) {
        try {
            service.guardar(tipo);
            return "redirect:/brigadas";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "brigadas/form";
        }
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("tipo", service.obtener(id));
        return "brigadas/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/brigadas";
    }
}