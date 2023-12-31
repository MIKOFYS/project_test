package Email;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class MailDemo03 {
    public static void main(String[] args) throws Exception {
        Properties prop=new Properties();
        prop.setProperty("mail.host","smtp.qq.com");///设置QQ邮件服务器
        prop.setProperty("mail.transport.protocol","smtp");///邮件发送协议
        prop.setProperty("mail.smtp.auth","true");//需要验证用户密码

//        //经过实测，QQ邮箱好像不需要
//        //QQ邮箱需要设置SSL加密，其他邮箱不需要
//        MailSSLSocketFactory sf=new MailSSLSocketFactory();
//        sf.setTrustAllHosts(true);
//        prop.put("mail.smtp.ssl.enable","false");
//        prop.put("mail.smtp.ssl.socketFactory",sf);

        //使用javaMail发送邮件的5个步骤
        //1.创建定义整个应用程序所需要的环境信息的session对象
        Session session=Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("480604852@qq.com","ufhshmipkgiybhce");
            }
        });
        //开启session的debug模式，这样可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2.通过session得到transport对象
        Transport ts=session.getTransport();
        //3.使用邮箱的用户名和授权码连上邮件服务器
        ts.connect("smtp.qq.com","480604852@qq.com","ufhshmipkgiybhce");
        //4.创建邮件：写文件
        //注意需要传递session
        MimeMessage message=new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress("480604852@qq.com"));
        //指明邮件的收件人
        message.setRecipient(Message.RecipientType.TO,new InternetAddress("983427278@qq.com"));
        //邮件标题
        message.setSubject("java发出");

        //邮件的文本内容
        //=================================准备图片数据
        MimeBodyPart image=new MimeBodyPart();
        //图片需要经过数据化的处理
        DataHandler dh=new DataHandler(new FileDataSource("D:\\IDEA_Code_Store_Position\\JavaWeb\\webApp\\WEB-INF\\picture\\main_picture.png"));
        //在part中放入这个处理过图片的数据
        image.setDataHandler(dh);
        //给这个part设置一个ID名字
        image.setContentID("bz.jpg");

        //=================================准备正文数据
        MimeBodyPart text=new MimeBodyPart();
        text.setContent("这是一张正文<img src='cid:bz.jpg'>","text/html;charset=UTF-8");

        //=================================准备附件数据
        MimeBodyPart body1= new MimeBodyPart();
        body1.setDataHandler(new DataHandler(new FileDataSource("D:\\IDEA_Code_Store_Position\\JavaWeb\\webApp\\WEB-INF\\picture\\main_picture.png")));
        body1.setFileName("1.txt");

        //描述数据关系
        MimeMultipart mm=new MimeMultipart();
        mm.addBodyPart(body1);
        mm.addBodyPart(text);
        mm.addBodyPart(image);
        mm.setSubType("mixed");

        //设置到消息中，保存修改
        message.setContent(mm);
        message.saveChanges();
        //5.发送邮件
        ts.sendMessage(message,message.getAllRecipients());

        //6.关闭连接
        ts.close();

    }
}