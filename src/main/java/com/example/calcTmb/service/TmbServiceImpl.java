package com.example.calcTmb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.calcTmb.dto.CalculoTmbRequest;
import com.example.calcTmb.model.entity.Usuarios;
import com.example.calcTmb.repositories.UsuariosRespository;

@Service
public class TmbServiceImpl implements TmbService {

    @Autowired
    private UsuariosRespository usuariosRespository;

    /*
     * @Override
     * public Usuarios calcularTmb(CalculoTmbRequest requestDTO) {
     * 
     * double tmb = calcularTmb(requestDTO.getSexo(), requestDTO.getPeso(),
     * requestDTO.getAltura(),
     * requestDTO.getIdade());
     * 
     * double caloriasManter = calcularCaloriasManterPeso(tmb,
     * requestDTO.getNivelAtividade());
     * 
     * double caloriasPerder = calcularCaloriasPerderPeso(caloriasManter);
     * 
     * Usuarios usuario = new Usuarios(requestDTO.getNome(),
     * requestDTO.getIdade(),
     * requestDTO.getSexo(),
     * requestDTO.getPeso(),
     * requestDTO.getAltura(),
     * requestDTO.getNivelAtividade());
     * 
     * usuario.setTmb(tmb);
     * usuario.setPerderPeso(caloriasPerder);
     * usuario.setManterPeso(caloriasManter);
     * 
     * return usuariosRespository.save(usuario);
     * }
     * 
     */

    @Override
    public Usuarios calcularTmb(CalculoTmbRequest requestDTO) {

        // calcula TMB original
        double tmbOriginal = calcularTmb(requestDTO.getSexo(), requestDTO.getPeso(), requestDTO.getAltura(),
                requestDTO.getIdade());

        // calcula calorias usando TMB original
        double caloriasManter = calcularCaloriasManterPeso(tmbOriginal, requestDTO.getNivelAtividade());
        double caloriasPerder = calcularCaloriasPerderPeso(caloriasManter);

        // arredonda os valores finais
        double tmbArredondado = Math.round(tmbOriginal * 100.0) / 100.0;
        caloriasManter = Math.round(caloriasManter * 100.0) / 100.0;
        caloriasPerder = Math.round(caloriasPerder * 100.0) / 100.0;

        Usuarios usuario = new Usuarios(requestDTO.getNome(),
                requestDTO.getIdade(),
                requestDTO.getSexo(),
                requestDTO.getPeso(),
                requestDTO.getAltura(),
                requestDTO.getNivelAtividade());

        usuario.setTmb(tmbArredondado);
        usuario.setPerderPeso(caloriasPerder);
        usuario.setManterPeso(caloriasManter);

        return usuariosRespository.save(usuario);
    }

    private double calcularCaloriasManterPeso(double tmb, String nivelAtividade) {

        switch (nivelAtividade) {
            case "sedentario":
                return tmb * 1.2;

            case "leve":
                return tmb * 1.375;

            case "moderado":
                return tmb * 1.55;

            case "ativo":
                return tmb * 1.725;

            case "muitoAtivo":
                return tmb * 1.9;

            default:
                return tmb;
        }

    }

    private double calcularCaloriasPerderPeso(double caloriasManter) {
        return caloriasManter - 500;
    }

    private double calcularTmb(String sexo, double peso, double altura, int idade) {
        /*
         * Fórmula de Harris-Benedict (rev. 1984)
         * if (sexo.equals("M")) {
         * System.out.println("Chegou aqui");
         * return 66 + (13.7 * peso) + (5 * altura) - (6.8 * idade);
         * } else {
         * return 655 + (9.6 * peso) + (1.8 * altura) - (4.7 * idade);
         * }
         */

        // Fórmula de Mifflin-St Jeor
        if (sexo.equals("M")) {

            return (9.99 * peso) + (6.25 * altura) - (4.92 * idade) + 5;

        } else {
            return (9.99 * peso) + (6.25 * altura) - (4.92 * idade) - 161;
        }

    }

    @Override
    public List<Usuarios> obterHistorico() {
        return usuariosRespository.findByOrderByDataCalculoDesc();
    }

}
