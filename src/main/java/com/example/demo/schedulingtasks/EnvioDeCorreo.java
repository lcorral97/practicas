package com.example.demo.schedulingtasks;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.SpringbootApplication;
import com.example.demo.exception.CustomException;

@Component
public class EnvioDeCorreo {

	private JavaMailSender jms;
	
	@Scheduled(cron="0 0 8 * * *")
	private void enviarEmail() throws CustomException {
		/*MimeMessage msg = jms.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			helper.setTo("javier.azaola@entelgy-ibai.com");
			helper.setSubject("Tiempo metereológico");
			helper.addAttachment("fichero.xls", new File("fichero.xls"));
			jms.send(msg);
		} catch (MessagingException e) {
			CustomException ce = new CustomException("Error al crear el mensaje: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class)
				.warn(ce.getMessage());
			throw ce;
		}*/
	}
}
