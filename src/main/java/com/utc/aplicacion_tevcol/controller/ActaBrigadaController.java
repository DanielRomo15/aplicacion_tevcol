package com.utc.aplicacion_tevcol.controller;

import com.utc.aplicacion_tevcol.entity.ActaBrigada;
import com.utc.aplicacion_tevcol.service.ActaBrigadaService;
import com.utc.aplicacion_tevcol.service.TipoBrigadaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/actas")
public class ActaBrigadaController {

    private final ActaBrigadaService actaService;
    private final TipoBrigadaService tipoService;

    public ActaBrigadaController(ActaBrigadaService actaService, TipoBrigadaService tipoService) {
        this.actaService = actaService;
        this.tipoService = tipoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", actaService.listar());
        return "actas/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("acta", new ActaBrigada());
        model.addAttribute("brigadas", tipoService.listar());
        return "actas/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("acta") ActaBrigada acta,
                          @RequestParam("fkTipoBrigada") Long fkTipoBrigada,
                          Model model) {
        try {
            actaService.guardar(acta, fkTipoBrigada);
            return "redirect:/actas";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("brigadas", tipoService.listar());
            return "actas/form";
        }
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        ActaBrigada acta = actaService.obtener(id);
        model.addAttribute("acta", acta);
        model.addAttribute("brigadas", tipoService.listar());
        return "actas/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        actaService.eliminar(id);
        return "redirect:/actas";
    }
}