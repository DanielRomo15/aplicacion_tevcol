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
        long estado0 = actaRepo.countByEstadoActaBrigada(0);
        long estado1 = actaRepo.countByEstadoActaBrigada(1);
        long estado2 = actaRepo.countByEstadoActaBrigada(2);

        model.addAttribute("totalActas", total);
        model.addAttribute("estado0", estado0);
        model.addAttribute("estado1", estado1);
        model.addAttribute("estado2", estado2);
        return "dashboard/index";
    }
}