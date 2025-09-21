package com.example.calcTmb.service;

import java.util.List;

import com.example.calcTmb.dto.CalculoTmbRequest;
import com.example.calcTmb.model.entity.Usuarios;

public interface TmbService {

    Usuarios calcularTmb(CalculoTmbRequest request);

    List<Usuarios> obterHistorico();

}
