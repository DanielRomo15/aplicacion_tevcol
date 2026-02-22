package com.utc.aplicacion_tevcol.service;

import com.utc.aplicacion_tevcol.entity.ActaBrigada;
import com.utc.aplicacion_tevcol.entity.TipoBrigada;
import com.utc.aplicacion_tevcol.repository.ActaBrigadaRepository;
import com.utc.aplicacion_tevcol.repository.TipoBrigadaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ActaBrigadaService {

    private final ActaBrigadaRepository actaRepo;
    private final TipoBrigadaRepository tipoRepo;

    public ActaBrigadaService(ActaBrigadaRepository actaRepo, TipoBrigadaRepository tipoRepo) {
        this.actaRepo = actaRepo;
        this.tipoRepo = tipoRepo;
    }

    public List<ActaBrigada> listar() { return actaRepo.findAll(); }

    public ActaBrigada obtener(Long id) {
        return actaRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Acta no existe"));
    }

    public ActaBrigada guardar(ActaBrigada a, Long fkTipoBrigada) {
        // Validaciones negocio
        if (a.getFechaActaBrigada() == null) throw new IllegalArgumentException("Fecha obligatoria");
        if (a.getFechaActaBrigada().isAfter(LocalDate.now())) throw new IllegalArgumentException("No puede ser futura");

        if (a.getEstadoActaBrigada() == null || a.getEstadoActaBrigada() < 0 || a.getEstadoActaBrigada() > 2)
            throw new IllegalArgumentException("Estado inválido (0-2)");

        if (a.getLinkArchivoActaBrigada() == null || !a.getLinkArchivoActaBrigada().startsWith("http"))
            throw new IllegalArgumentException("Link inválido (http/https)");

        if (a.getFkCodEstablecimiento() == null || a.getFkCodEstablecimiento() <= 0)
            throw new IllegalArgumentException("Establecimiento inválido");

        TipoBrigada tipo = tipoRepo.findById(fkTipoBrigada)
                .orElseThrow(() -> new IllegalArgumentException("Brigada inválida"));

        // regla: mismo establecimiento
        if (tipo.getFkCodEstablecimiento() != null && !tipo.getFkCodEstablecimiento().equals(a.getFkCodEstablecimiento())) {
            throw new IllegalArgumentException("El establecimiento del acta debe coincidir con el de la brigada");
        }

        a.setTipoBrigada(tipo);
        a.setLinkArchivoActaBrigada(a.getLinkArchivoActaBrigada().trim());

        return actaRepo.save(a);
    }

    public void eliminar(Long id) { actaRepo.deleteById(id); }
}