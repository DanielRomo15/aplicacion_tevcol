package com.utc.aplicacion_tevcol.controller;

import com.utc.aplicacion_tevcol.repository.ActaBrigadaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final ActaBrigadaRepository actaRepo;

    public DashboardController(ActaBrigadaRepository actaRepo) {
        this.actaRepo = actaRepo;
    }

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {

        long total = actaRepo.count();

        
        long activos = actaRepo.countByEstadoActaBrigada("ACTIVO");
        long inactivos = actaRepo.countByEstadoActaBrigada("INACTIVO");

        model.addAttribute("totalActas", total);
        model.addAttribute("activos", activos);
        model.addAttribute("inactivos", inactivos);

        return "dashboard/index";
    }
}