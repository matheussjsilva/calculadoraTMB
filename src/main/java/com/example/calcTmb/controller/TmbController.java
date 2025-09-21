package com.example.calcTmb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.calcTmb.dto.CalculoTmbRequest;
import com.example.calcTmb.model.entity.Usuarios;
import com.example.calcTmb.repositories.UsuariosRespository;
import com.example.calcTmb.service.TmbService;

@RestController
@RequestMapping(path = "/tmb")
public class TmbController {

    @Autowired
    private UsuariosRespository usuariosRespository;

    @Autowired
    private TmbService tmbService;

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("calculoTmbRequest", new CalculoTmbRequest());
        return "index";
    }

    @PostMapping(path = "/api/calcular")
    @ResponseBody
    public ResponseEntity<Usuarios> calcularTmbApi(@RequestBody CalculoTmbRequest request) {
        Usuarios resultado = tmbService.calcularTmb(request);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/api/historico")
    @ResponseBody
    public ResponseEntity<List<Usuarios>> obterHistoricoApi() {
        List<Usuarios> historico = tmbService.obterHistorico();
        return ResponseEntity.ok(historico);
    }

    @GetMapping(path = "/test")
    public String testApi() {
        return "Funcionando";
    }

    @PostMapping(path = "/cadastrarUsuario")
    public @ResponseBody Usuarios novoUsuario(Usuarios usuario) {

        usuariosRespository.save(usuario);
        return usuario;
    }

    @PostMapping(path = "/calcular")
    public String calcularTmb(@ModelAttribute CalculoTmbRequest request, Model model) {
        Usuarios resultado = tmbService.calcularTmb(request);
        model.addAttribute("resultado", resultado);

        return "resultado";
    }

    @GetMapping(path = "/historico")
    public String mostrarHistorico(Model model) {
        List<Usuarios> historico = tmbService.obterHistorico();
        model.addAttribute("historico", historico);

        return "historico";

    }

}
