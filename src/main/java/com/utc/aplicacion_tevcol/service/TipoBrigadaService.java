package com.utc.aplicacion_tevcol.service;

import com.utc.aplicacion_tevcol.entity.TipoBrigada;
import com.utc.aplicacion_tevcol.repository.TipoBrigadaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoBrigadaService {

    private final TipoBrigadaRepository repo;

    public TipoBrigadaService(TipoBrigadaRepository repo) {
        this.repo = repo;
    }

    public List<TipoBrigada> listar() { return repo.findAll(); }

    public TipoBrigada obtener(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Tipo brigada no existe"));
    }

    public TipoBrigada guardar(TipoBrigada t) {
        
        if (t.getDescripcionBrigada() == null || t.getDescripcionBrigada().trim().length() < 3)
            throw new IllegalArgumentException("Descripción inválida (mínimo 3 caracteres)");

        if (t.getLinkBrigada() == null || !t.getLinkBrigada().startsWith("http"))
            throw new IllegalArgumentException("Link inválido (debe iniciar con http/https)");

        if (t.getFkCodEstablecimiento() == null || t.getFkCodEstablecimiento() <= 0)
            throw new IllegalArgumentException("Establecimiento inválido");

        t.setDescripcionBrigada(t.getDescripcionBrigada().trim());
        t.setLinkBrigada(t.getLinkBrigada().trim());

        return repo.save(t);
    }

    public void eliminar(Long id) { repo.deleteById(id); }
}