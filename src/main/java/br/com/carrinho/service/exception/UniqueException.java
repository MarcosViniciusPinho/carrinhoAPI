package br.com.carrinho.service.exception;

public class UniqueException extends RuntimeException {

    private String mensagemClient;

    public UniqueException(String mensagemClient, String mensagemException) {
        super(mensagemException);
        this.mensagemClient = mensagemClient;
    }

    public String getMensagemClient() {
        return mensagemClient;
    }

}
