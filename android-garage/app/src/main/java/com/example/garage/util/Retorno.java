package com.example.garage.util;

public class Retorno {

    private boolean retorno;
    private String txtRetorno;

    public Retorno() {
    }

    public Retorno(boolean retorno, String txtRetorno) {
        this.retorno = retorno;
        this.txtRetorno = txtRetorno;
    }

    public boolean isRetorno() {
        return retorno;
    }

    public void setRetorno(boolean retorno) {
        this.retorno = retorno;
    }

    public String getTxtRetorno() {
        return txtRetorno;
    }

    public void setTxtRetorno(String txtRetorno) {
        this.txtRetorno = txtRetorno;
    }
}
