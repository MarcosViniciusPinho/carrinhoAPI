package br.com.carrinho.handler.domain;

public class ResponseError {

    private String erro;
    private String exception;

    public ResponseError(String erro, String exception) {
        this.erro = erro;
        this.exception = exception;
    }

    public String getException() {
        return exception;
    }

    public String getErro() {
        return erro;
    }
}
