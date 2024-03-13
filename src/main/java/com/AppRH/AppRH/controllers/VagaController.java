package com.AppRH.AppRH.controllers;

import com.AppRH.AppRH.models.Candidato;
import com.AppRH.AppRH.models.Vaga;
import com.AppRH.AppRH.repository.CandidatoRepository;
import com.AppRH.AppRH.repository.VagaRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
public class VagaController {
    private VagaRepository vr;
    private CandidatoRepository cr;

    @RequestMapping(value = "/cadastrarVaga", method = RequestMethod.GET)
    public String form(){
        return "vaga/form-vaga";
    }

    @RequestMapping(value = "/cadastrarVaga", method = RequestMethod.POST)
    public String form(@Valid Vaga vaga, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique os campos...");
            return "redirect:/cadastrarVaga";
        }

        vr.save(vaga);
        attributes.addFlashAttribute("mensagem", "Vaga cadastrada com sucesso!");
        return "redirect:/cadastrarVaga";
    }

    @RequestMapping(value = "/vagas", method = RequestMethod.GET)
    public ModelAndView listaVagas(){
        ModelAndView mv = new ModelAndView("vaga/listaVaga");
        Iterable<Vaga> vagas = vr.findAll();
        mv.addObject("vagas", vagas);
        return mv;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView detalhesVaga(@PathVariable("id") long id){
        Vaga vaga = vr.findById(id);
        ModelAndView mv = new ModelAndView("vaga/detalhesVaga");
        mv.addObject("vaga", vaga);

        Iterable<Candidato> candidatos = cr.findByVaga(vaga);
        mv.addObject("candidatos", candidatos);

        return mv;
    }

    @RequestMapping("/deletarVaga")
    public String deletarVaga(long id){
        Vaga vaga = vr.findById(id);
        vr.delete(vaga);
        return "redirect:/vagas";
    }

    public String detalhesVagaPost(@PathVariable("id") long id, @Valid Candidato candidato, BindingResult result, RedirectAttributes attributes){

        if (result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique os campos");
            return "redirect:/{id}";
        }

        if(cr.findByRg(candidato.getRg()) != null){
            attributes.addFlashAttribute("mensagem erro", "RG duplicado");
            return "redirect:/{id}";
        }

        Vaga vaga = vr.findById(id);
        candidato.setVaga(vaga);
        cr.save(candidato);
        attributes.addFlashAttribute("mensagem", "Candidato adicionado com sucesso!");
        return "redirect:/{id}";
    }

    @RequestMapping("/deletarCandidato")
    public String deletarCandidato(String rg){
            Candidato candidato = cr.findByRg(rg);
            Vaga vaga = candidato.getVaga();
            String codigo = "" + vaga.getId();

            cr.delete(candidato);
            return "redirect:/" + codigo;
        }

        @RequestMapping(value = "/editar-vaga", method = RequestMethod.GET)
        public ModelAndView editarVaga(long id){
            Vaga vaga = vr.findById(id);
            ModelAndView mv = new ModelAndView("vaga/update-vaga");
            mv.addObject("vaga", vaga);
            return mv;
        }

        @RequestMapping(value = "/editar-vaga", method = RequestMethod.POST)
        public String updateVaga(@Valid Vaga vaga, BindingResult result, RedirectAttributes attributes){
            vr.save(vaga);
            attributes.addFlashAttribute("success", "Vaga alterada com sucesso!");
            long idLong = vaga.getId();
            String codigo = ""+ idLong;
            return "redirect:/" + codigo;
        }

}
