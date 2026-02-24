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

        if (a.getFechaActaBrigada() == null) throw new IllegalArgumentException("Fecha obligatoria");
        if (a.getFechaActaBrigada().isAfter(LocalDate.now())) throw new IllegalArgumentException("No puede ser futura");

        if (a.getEstadoActaBrigada() == null || a.getEstadoActaBrigada().isBlank())
            throw new IllegalArgumentException("Estado obligatorio");

        // Ajusta aquí si manejas más estados
        String estado = a.getEstadoActaBrigada().trim().toUpperCase();
        if (!estado.equals("ACTIVO") && !estado.equals("INACTIVO"))
            throw new IllegalArgumentException("Estado inválido (use ACTIVO o INACTIVO)");

        a.setEstadoActaBrigada(estado);

        // link puede ser null según tu tabla
        if (a.getLinkArchivoActaBrigada() != null && !a.getLinkArchivoActaBrigada().isBlank()) {
            String link = a.getLinkArchivoActaBrigada().trim();
            if (!link.startsWith("http"))
                throw new IllegalArgumentException("Link inválido (http/https)");
            a.setLinkArchivoActaBrigada(link);
        } else {
            a.setLinkArchivoActaBrigada(null);
        }

        if (a.getFkCodEstablecimiento() == null || a.getFkCodEstablecimiento() <= 0)
            throw new IllegalArgumentException("Establecimiento inválido");

        TipoBrigada tipo = tipoRepo.findById(fkTipoBrigada)
                .orElseThrow(() -> new IllegalArgumentException("Brigada inválida"));

        if (tipo.getFkCodEstablecimiento() != null && !tipo.getFkCodEstablecimiento().equals(a.getFkCodEstablecimiento())) {
            throw new IllegalArgumentException("El establecimiento del acta debe coincidir con el de la brigada");
        }

        a.setTipoBrigada(tipo);

        return actaRepo.save(a);
    }

    public void eliminar(Long id) { actaRepo.deleteById(id); }
}