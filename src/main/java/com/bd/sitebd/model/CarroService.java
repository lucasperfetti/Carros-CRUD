package com.bd.sitebd.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarroService {
    @Autowired
    CarroDAO cdao;

    public void inserir(Carro car){
        cdao.inserir(car);
    }

    public List<Map<String,Object>> obterTodosCarros(){
        return cdao.obterTodosCarros();
    }

    public void atualizarCarro(int id, Carro car){
        cdao.atualizarCarro(id, car);
    }

    public Carro obterCarro(int id){
        return cdao.obterCarro(id);
    }

    public void deletarCarro(int id){
        cdao.deletarCarro(id);
    }
}
