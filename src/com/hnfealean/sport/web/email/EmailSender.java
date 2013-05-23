package com.hnfealean.sport.web.email;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.hnfealean.sport.model.email.Email;
public class EmailSender implements Runnable {
	private Email email;
	private String[] receivers;
	private String subject;
	private String mailContent; 
	private File[] attachements; 
	private String mimetype;

    public EmailSender(Email email, String[] receivers, String subject,
			String mailContent, File[] attachements, String mimetype) {
		super();
		this.email = email;
		this.receivers = receivers;
		this.subject = subject;
		this.mailContent = mailContent;
		this.attachements = attachements;
		this.mimetype = mimetype;
	}


	private static final String charset = "GBK";
    //private static final String defaultMimetype = "text/plain";
	private static final String defaultMimetype = "text/html";
	
    
/*    public static void main(String[] args) throws Exception {
    	EmailSender.send(new String[]{"lhm@itcast.cn"}, "邮件测试xx", "<b>传智播客</b>",null , "text/html");
    	Email email = new Email();
    	email.setAddress("hothotearn@gmail.com");
    	email.setPassword("hnfealean");
    	email.setSmtpHost("smtp.gmail.com");
    	email.setSmtpPort("465");
    	email.setUsername("hothotearn");
    	send(email, new String[]{"hnfealean@gmail.com"}, "最好测试搜索主ss题测试", "测试ss 阿萨德邮件正文", null, "text/html");
    	System.out.println("成功");
    }*/

	private static void send(String[] sendTo, String subject, String content,
			Object object, String mimetype) {
	}
    public static void send(final Email email,
				    		String[] receivers, 
				    		String subject, 
				    		String mailContent, 
				    		File[] attachements, 
				    		String mimetype) {
        Properties props = new Properties();
        props.put("mail.smtp.host", email.getSmtpHost());//Smtp服务器地址
        props.put("mail.smtp.auth", "true");//需要校验
      //  props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", email.getSmtpPort());
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.port", email.getSmtpPort());
        props.put("mail.smtp.socketFactory.port",email.getSmtpPort());
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
            	
                return new PasswordAuthentication(email.getUsername(),email.getPassword());//登录用户名/密码
            }
        });
        session.setDebug(true);
        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(email.getAddress()));//发件人邮件

            InternetAddress[] toAddress = new InternetAddress[receivers.length];
            for (int i=0; i<receivers.length; i++) {
                toAddress[i] = new InternetAddress(receivers[i]);
            }
            mimeMessage.setRecipients(Message.RecipientType.TO, toAddress);//收件人邮件
            mimeMessage.setSubject(subject, charset);
            
            Multipart multipart = new MimeMultipart();
            //正文
            MimeBodyPart body = new MimeBodyPart();
           // body.setText(message, charset);不支持html
            body.setContent(mailContent, ((mimetype!=null && !"".equals(mimetype)) ? mimetype : defaultMimetype)+ ";charset="+ charset);
            multipart.addBodyPart(body);//发件内容
            //附件
            if(attachements!=null){
	            for (File attachement : attachements) {
	                MimeBodyPart attache = new MimeBodyPart();
	               //ByteArrayDataSource bads = new ByteArrayDataSource(byte[],"application/x-any");
	                attache.setDataHandler(new DataHandler(new FileDataSource(attachement)));
	                String fileName = getLastName(attachement.getName());
	                attache.setFileName(MimeUtility.encodeText(fileName, charset, null));
	                multipart.addBodyPart(attache);
	            }
            }
            mimeMessage.setContent(multipart);
           // SimpleDateFormat formcat = new SimpleDateFormat("yyyy-MM-dd");            
            mimeMessage.setSentDate(new Date());//formcat.parse("2010-5-23")
            Transport.send(mimeMessage);            
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    private static String getLastName(String fileName) {
        int pos = fileName.lastIndexOf("\\");
        if (pos > -1) {
            fileName = fileName.substring(pos + 1);
        }
        pos = fileName.lastIndexOf("/");
        if (pos > -1) {
            fileName = fileName.substring(pos + 1);
        }
        return fileName;
    }

	
	public void run() {
		System.out.println("---runbegin---"+Thread.currentThread().getName());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		send(email, receivers, subject, mailContent, attachements, mimetype);
		System.out.println("---runend---");
		
	}
}

