package ajw.Controller;

//import java.net.PasswordAuthentication;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailController extends Authenticator {
    private String mAuthenticationCode = "";
    private String ranNum;
    private int createNum;
    private Random randdom = new Random();
    
    //private JButton btnSend;

    public EmailController(){

    }

    public void sendEmail(String toEmail){
        send(toEmail);
    }

    private void send(String toEmail) {
        // 네이버 계정 정보
        String fromEmail = "ajw3351@naver.com";
        String password = "awert50280867";
      
        // SMTP 서버 설정
        String host = "smtp.naver.com";
        int port = 587;

        // 인증을 위한 네이버 계정 설정
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // 인증 정보 객체 생성
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // 인증번호 생성
            for (int i = 0; i < 4; i++) {
                createNum = randdom.nextInt(9);
                ranNum = Integer.toString(createNum);
                mAuthenticationCode += ranNum;
            }
            Controller.getInstance().setAuthenticationCode(mAuthenticationCode);

            // 이메일 메시지 생성
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("[BaseBall] 회원 인증번호");
            message.setText("인증번호 : " + mAuthenticationCode);

            // 이메일 보내기
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}