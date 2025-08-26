package com.curso.domains.enums;

public enum TipoConta {

    CONTACORRENTE(0,"CONTACORRENTE"), CONTAINVESTIMENTO(1, "CONTAINVESTIMENTO"),
    CARTAOCREDITO(2,"CARTAOCREDITO"), ALIMENTACAO(3, "ALIMENTACAO"),
    POUPANCA(4,"POUPANCA");

    private Integer id;
    private String tipoConta;

    TipoConta(Integer id, String tipoConta) {
        this.id = id;
        this.tipoConta = this.tipoConta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public static TipoConta toEnum(Integer id){
        if(id==null) return null;
        for(TipoConta x : TipoConta.values()){
            if(id.equals(x.getId())){
                return x;
            }
        }
        throw new IllegalArgumentException("TipoConta Invalido");
    }
}
