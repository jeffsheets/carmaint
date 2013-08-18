package com.sheetsj.test;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessagePreparator;

/**
 * Mock out the MailSender so our unit tests don't send email.
 * 
 * See: http://fahdshariff.blogspot.com/2012/09/testing-email-with-mock-mailsender.html
 * 
 * Message is saved in mailContent property
 */
public class MockMailSender extends JavaMailSenderImpl
{
    @Override
    public void send(final MimeMessagePreparator mimeMessagePreparator) throws MailException
    {
        final MimeMessage mimeMessage = createMimeMessage();
        try
        {
            mimeMessagePreparator.prepare(mimeMessage);
            final String content = (String) mimeMessage.getContent();
            final Properties javaMailProperties = getJavaMailProperties();
            javaMailProperties.setProperty("mailContent", content);
        }
        catch (final Exception e)
        {
            throw new MailPreparationException(e);
        }
    }

    @Override
    public void send(SimpleMailMessage simpleMessage) throws MailException
    {
        MimeMailMessage message = new MimeMailMessage(createMimeMessage());
        simpleMessage.copyTo(message);

        final Properties javaMailProperties = getJavaMailProperties();
        javaMailProperties.setProperty("mailContent", simpleMessage.getText());
    }
}
