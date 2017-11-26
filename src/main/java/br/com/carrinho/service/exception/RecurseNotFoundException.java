package br.com.carrinho.service.exception;

public class RecurseNotFoundException extends RuntimeException {

    public RecurseNotFoundException(String mensagem) {
        super(mensagem);
    }

}
