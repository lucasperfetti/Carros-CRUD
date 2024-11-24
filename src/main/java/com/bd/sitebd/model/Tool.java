package com.bd.sitebd.model;

import java.util.Map;

public class Tool {

    public static Carro converterCarro(Map<String,Object> registro){
        //Como registro.get retorna Object, devemos usar o polimorfismo
        //de subtipos (downcast) para recuperar os tipos originais.
        return new Carro((Integer) registro.get("id")
                          ,(String) registro.get("marca")
                          ,(String) registro.get("modelo")
                          ,(String) registro.get("cor")
                          ,(Integer) registro.get("ano"));
    }

}
