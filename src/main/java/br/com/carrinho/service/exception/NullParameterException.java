package br.com.carrinho.service.exception;

public class NullParameterException extends NullPointerException {

    public NullParameterException(String mensagem) {
        super(mensagem);
    }

    public NullParameterException() {
        super();
    }

}
