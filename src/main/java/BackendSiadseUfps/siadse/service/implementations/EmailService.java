package BackendSiadseUfps.siadse.service.implementations;

import BackendSiadseUfps.siadse.dto.PQRSDTO;
import BackendSiadseUfps.siadse.entity.PQRS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String email;

    /**
     * Envio de correo electronico al email proporcionado
     * @param pqrs Mensaje que se envia adjunto al correo electronico.
     * @throws RuntimeException si ocurre un error al enviar el correo
     */
    @Async
    public void sendListEmail(PQRSDTO pqrs, int tipe, String respuesta) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(email);
            helper.setTo(pqrs.getCorreo());
            helper.setSubject("SEGUIMIENTO PQRS - RADICADO "+pqrs.getCodigoRadicado());
            if(tipe == 1) {
                helper.setText(getHtml(pqrs), true);
            }else{
                helper.setText(getHtml2(pqrs, respuesta), true);
            }

            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getHtml(PQRSDTO pqrs){
        String nombre = "";
        if(pqrs.getAnonimo()){
            nombre = "Anónimo";
        }else{
            nombre = pqrs.getNombre() +" "+ pqrs.getApellido();
        }
        String codeRadicado = pqrs.getCodigoRadicado();
        String descripcion = pqrs.getDescripcion();
        String tipoPQRS = pqrs.getTipoPqrs().getTipo();
        Date fechaRadicado = pqrs.getFechaRadicado();
        String emailHtml = "<!DOCTYPE html>\n" +
                "<html lang=\"es\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Seguimiento de Solicitud PQRS</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: #f4f4f4;\n" +
                "            color: #333;\n" +
                "        }\n" +
                "        .container {\n" +
                "            width: 80%;\n" +
                "            margin: 0 auto;\n" +
                "            background-color: #fff;\n" +
                "            padding: 20px;\n" +
                "            border-radius: 5px;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "        }\n" +
                "        .header {\n" +
                "            text-align: center;\n" +
                "            border-bottom: 1px solid #e0e0e0;\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "        .header img {\n" +
                "            width: 150px;\n" +
                "        }\n" +
                "        .content {\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "        .footer {\n" +
                "            text-align: center;\n" +
                "            font-size: 12px;\n" +
                "            color: #777;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header\">\n" +
                "            <img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/a/ad/Logo_de_UFPS.svg/640px-Logo_de_UFPS.svg.png\" alt=\"Logo de la Empresa\">\n" +
                "            <h1>Seguimiento de Solicitud PQRS</h1>\n" +
                "        </div>\n" +
                "        <div class=\"content\">\n" +
                "            <p>Estimado/a "+ nombre+",</p>\n" +
                "            <p>Hemos recibido su solicitud PQRS con el número <strong>"+codeRadicado+"</strong> el <strong>"+fechaRadicado+"</strong>. Agradecemos que nos haya contactado y queremos asegurarle que estamos trabajando para atender su solicitud lo más pronto posible.</p>\n" +
                "            <p><strong>Tipo de Solicitud:</strong> "+tipoPQRS+"</p>\n" +
                "           <p><strong>Descripción:</strong> "+descripcion+"</p>\n" +
                "            <p>Estamos trabajando para resolver su solicitud a la mayor brevedad posible, las actualizaciones serán enviadas a este mismo correo.</p>\n" +
                "            <p>Si tiene alguna pregunta adicional, no dude en responder a este correo o contactarnos a través de nuestros canales de atención al cliente.</p>\n" +
                "            <p>Gracias por su paciencia y comprensión.</p>\n" +
                "            <p>Saludos cordiales,</p>\n" +
                "            <p><strong>Semillero SIREDSE</strong></p>\n" +
                "        </div>\n" +
                "        <div class=\"footer\">\n" +
                "            <p>&copy; 2024 Semillero SIREDSE. Todos los derechos reservados.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";

        return emailHtml;
    }

    public String getHtml2(PQRSDTO pqrs, String respuesta){
        String nombre = "";
        if(pqrs.getAnonimo()){
            nombre = "Anónimo";
        }else{
            nombre = pqrs.getNombre() +" "+ pqrs.getApellido();
        }
        String codeRadicado = pqrs.getCodigoRadicado();
        String descripcion = pqrs.getDescripcion();
        String tipoPQRS = pqrs.getTipoPqrs().getTipo();
        Date fechaRadicado = pqrs.getFechaRadicado();
        String emailHtml = "<!DOCTYPE html>\n" +
                "<html lang=\"es\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Seguimiento de Solicitud PQRS</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: #f4f4f4;\n" +
                "            color: #333;\n" +
                "        }\n" +
                "        .container {\n" +
                "            width: 80%;\n" +
                "            margin: 0 auto;\n" +
                "            background-color: #fff;\n" +
                "            padding: 20px;\n" +
                "            border-radius: 5px;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "        }\n" +
                "        .header {\n" +
                "            text-align: center;\n" +
                "            border-bottom: 1px solid #e0e0e0;\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "        .header img {\n" +
                "            width: 150px;\n" +
                "        }\n" +
                "        .content {\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "        .footer {\n" +
                "            text-align: center;\n" +
                "            font-size: 12px;\n" +
                "            color: #777;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header\">\n" +
                "            <img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/a/ad/Logo_de_UFPS.svg/640px-Logo_de_UFPS.svg.png\" alt=\"Logo de la Empresa\">\n" +
                "            <h1>Seguimiento de Solicitud PQRS</h1>\n" +
                "        </div>\n" +
                "        <div class=\"content\">\n" +
                "            <p>Estimado/a "+ nombre+",</p>\n" +
                "           <p>Su PQRS con el número "+codeRadicado+" fue procesado y la respuesta es la siguiente:</p>\n" +
                "           <p><strong>" + respuesta + "</strong></p>\n" +
                "            <p>Si tiene alguna pregunta adicional, no dude en responder a este correo o contactarnos a través de nuestros canales de atención al cliente.</p>\n" +
                "            <p>Gracias por su paciencia y comprensión.</p>\n" +
                "            <p>Saludos cordiales,</p>\n" +
                "            <p><strong>Semillero SIREDSE</strong></p>\n" +
                "        </div>\n" +
                "        <div class=\"footer\">\n" +
                "            <p>&copy; 2024 Semillero SIREDSE. Todos los derechos reservados.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";

        return emailHtml;
    }

}
