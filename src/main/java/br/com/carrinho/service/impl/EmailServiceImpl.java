package br.com.carrinho.service.impl;

import br.com.carrinho.entity.Carrinho;
import br.com.carrinho.service.EmailService;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.tools.generic.NumberTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VelocityEngine velocityEngine;


    /**
     * {@inheritDoc}
     */
    @Override
    public void send(Carrinho carrinho) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        MimeMessage message = sender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setText(this.createTemplate(carrinho), true);
            helper.setTo(carrinho.getUsuario().getEmail());
            helper.setFrom(carrinho.getUsuario().getEmail());
            helper.setSubject(String.format("Prezado %s, sua compra foi realizada com sucesso! Código da compra SVB%d", carrinho.getUsuario().getNome(), carrinho.getId()));

            this.mailSender.send(message);

        } catch (MailException | MessagingException | ResourceNotFoundException | ParseErrorException | MethodInvocationException e) {
            throw new RuntimeException("Erro ao enviar o e-mail", e);
        }
    }

    private String createTemplate(Carrinho carrinho) throws ResourceNotFoundException, ParseErrorException, MethodInvocationException{
        VelocityContext context = new VelocityContext();
        Template template = this.velocityEngine.getTemplate("template.vm", "utf-8");
        context.put("produtos", carrinho.getProdutoCarrinhoList());
        context.put("number", new NumberTool());
        context.put("total", carrinho.getValorDaCompra());
        context.put("usuario", carrinho.getUsuario());
        StringWriter conteudoHtml = new StringWriter(1024);
        template.merge(context, conteudoHtml);
        return conteudoHtml.toString();
    }
}