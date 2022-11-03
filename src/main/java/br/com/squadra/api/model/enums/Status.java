package br.com.squadra.api.model.enums;

public enum Status {

    UM(1),
    DOIS(2),
    TRES(3);

    Status(int valor) {
        this.valor = valor;
    }

    private final int valor;

    public int getValor() {
        return valor;
    }
}