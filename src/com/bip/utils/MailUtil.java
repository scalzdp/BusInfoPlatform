package com.bip.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {

    static int port = 25;

    static String server = "smtp.sina.com";//邮件服务器mail.cpip.net.cn

    static String from = "欢迎";//发送者,显示的发件人名字

    static String user = "scalzdp@sina.com";//发送者邮箱地址

    static String password = "Zp106817";//密码

   
    public static void sendEmail(String email, String subject, String body) throws UnsupportedEncodingException {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", server);
            props.put("mail.smtp.port", String.valueOf(port));
            props.put("mail.smtp.auth", "true");
            //props.put("mail.smtp.auth", "false");
            Transport transport = null;
            Session session = Session.getDefaultInstance(props, null);
            //
            session.setDebug(true);//设置调试标志,要查看经过邮件服务器邮件命令，可以用该方法
            transport = session.getTransport("smtp");
            transport.connect(server, user, password);
            MimeMessage msg = new MimeMessage(session);
            msg.setSentDate(new Date());
            InternetAddress fromAddress = new InternetAddress(user,from,"UTF-8");
            msg.setFrom(fromAddress);
            InternetAddress[] toAddress = new InternetAddress[1];
            toAddress[0] = new InternetAddress(email);
            msg.setRecipients(Message.RecipientType.TO, toAddress);
            msg.setSubject(subject, "UTF-8");   
            msg.setText(body, "UTF-8");
            msg.saveChanges();
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[]) throws UnsupportedEncodingException
    {

        //sendEmail("??????@sina.com","邮件测试","hello");//收件人
    	//sendEmail("275735763@qq.com","邮件测试","hellos");//收件人
    	 StringBuffer sf=new StringBuffer();  
         /*sf.append("<a href=\"http://192.168.0.84:8088/ompxm/activateEmail.action?email=");  
         sf.append("275735763@qq.com");  
         sf.append("&validateCode=");  
         sf.append("xxxxx");  
         sf.append("\">");  
         sf.append(" <FONT   face=\"MS   UI   Gothic\"   size=\"3\"><b>点击这里</b></FONT>");  
         sf.append("</a>");  
         sf.append("激活账号，24小时生效，否则重新验证，请尽快激活！<br>"); 
         */
    	 /*sf.append("<a>");//含有超链接和ip都会被屏蔽
    	 sf.append(" <FONT   face=\"MS   UI   Gothic\"   size=\"3\"><b>点击这里</b></FONT>");
    	 sf.append("激活账号，24小时生效，否则重新验证，请尽快激活！<br>");*/
    	sf.append("http://jingyan.baidu.com/article/f54ae2fccd998d1e93b8497f.html");
    	 sf.append("hello,this is message");
    	sendEmail("275735763@qq.com","邮件",sf.toString());//收件人
    	//sendEmail("xjbean@qq.com","邮件测试","hello jun");//收件人
        System.out.println("ok");
    }
}