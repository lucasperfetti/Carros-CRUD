package com.bd.sitebd.model;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class CarroDAO {

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    
    @PostConstruct
    private void initialize(){
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserir(Carro car){
        String sql = "INSERT INTO carro(marca,modelo,cor,ano) VALUES (?,?,?,?);";
        Object[] parametros = new Object[4];
        parametros[0] = car.getMarca();
        parametros[1] = car.getModelo();
        parametros[2] = car.getCor();
        parametros[3] = car.getAno();
        jdbc.update(sql,parametros);
    }

    //[ {id: 1, nome: teste1, cpf: 123456789-00}
    //, {id: 2, nome: teste2, cpf: 323456789-00}
    //]
    public List<Map<String,Object>> obterTodosCarros(){
        String sql = "Select * from carro;";
        return jdbc.queryForList(sql);
    }

    public void atualizarCarro(int id, Carro car){
        String sql = "UPDATE carro SET ";
        sql += "marca = ?, modelo = ?, cor = ?, ano = ? WHERE id = ?";
        jdbc.update(sql, car.getMarca(), car.getModelo(), car.getCor(), car.getAno(), id);
    }

    public Carro obterCarro(int id){
        String sql = "Select * from carro where id = ?";
        return Tool.converterCarro(jdbc.queryForMap(sql,id));
    }

    public void deletarCarro(int id){
        String sql = "DELETE FROM carro where id = ?";
        jdbc.update(sql,id);
    }


}
