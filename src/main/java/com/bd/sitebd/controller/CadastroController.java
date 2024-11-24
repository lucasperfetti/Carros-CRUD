package com.bd.sitebd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bd.sitebd.model.Carro;
import com.bd.sitebd.model.CarroService;
import com.bd.sitebd.model.Tool;

@Controller
public class CadastroController {
    @Autowired
    private ApplicationContext context;

    @GetMapping("/") 
    public String Principal(){
        return "principal"; 
    }

    @GetMapping("/atualizar/{id}")
    public String atualizar(Model model, @PathVariable int id){
        CarroService cs = context.getBean(CarroService.class);
        Carro car = cs.obterCarro(id);
        model.addAttribute("id", id);
        model.addAttribute("carro", car);
        return "atualizar";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable int id, @ModelAttribute Carro car){
        CarroService cs = context.getBean(CarroService.class);
        cs.atualizarCarro(id, car);
        return "redirect:/listagem";
    }

    @GetMapping("/cadastro") 
    public String cadastro(Model model){
        model.addAttribute("carro", new Carro("","","",null));
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrar(Model model, @ModelAttribute Carro car){
        CarroService cs = context.getBean(CarroService.class);
        cs.inserir(car);
        return "sucesso";
    }

    @GetMapping("/listagem")
    public String listagem(Model model){
        CarroService cs = context.getBean(CarroService.class);
        List<Map<String,Object>> lista = cs.obterTodosCarros();
        List<Carro> listaCarros = new ArrayList<Carro>();
        for(Map<String,Object> registro : lista){
            listaCarros.add(Tool.converterCarro(registro));
        }
        model.addAttribute("carros", listaCarros);
        return "listagem";
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable int id){
        CarroService cs = context.getBean(CarroService.class);
        cs.deletarCarro(id);
        return "redirect:/listagem";
    }

}