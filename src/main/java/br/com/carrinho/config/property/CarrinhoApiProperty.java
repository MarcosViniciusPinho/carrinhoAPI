package br.com.carrinho.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("carrinho")
public class CarrinhoApiProperty {

    private String originPermitida = "http://localhost:4200";
    private boolean habilitarHttps;

    public String getOriginPermitida() {
        return originPermitida;
    }

    public void setOriginPermitida(String originPermitida) {
        this.originPermitida = originPermitida;
    }

    public boolean isHabilitarHttps() {
        return habilitarHttps;
    }

    public void setHabilitarHttps(boolean habilitarHttps) {
        this.habilitarHttps = habilitarHttps;
    }

}