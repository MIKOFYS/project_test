package MySSM.Service;

import MySSM.DAO.studentDAO;
import MySSM.DAO.teacherDAO;
import MySSM.DATA.student;
import MySSM.DATA.teacher;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class loginService {
    private studentDAO StudentDAO=null;
    private teacherDAO TeacherDAO=null;
    public boolean studentLogin(String student_id, String password){
        password=MD5.getMd5(password);
        String sql="select * from student where student_id=? and password=?";
        student obj=null;
        obj=this.StudentDAO.querySingleRow(sql,student_id,password);
        if(obj!=null){
            return true;
        }else{
            return false;
        }
    }

    public boolean teacherLogin(String teacher_id, String password){
        password=MD5.getMd5(password);
        String sql="select * from teacher where teacher_id=? and password=?";
        teacher obj=null;
        obj=this.TeacherDAO.querySingleRow(sql,teacher_id,password);
        if(obj!=null){
            return true;
        }else{
            return false;
        }
    }

    public boolean studentChangePassword(String student_id, String oldPassword,String newPassword){
        oldPassword=MD5.getMd5(oldPassword);
        String sql="select * from student where student_id=? and password=?";
        student obj=null;
        obj=this.StudentDAO.querySingleRow(sql,student_id,oldPassword);
        if(obj!=null){
            newPassword=MD5.getMd5(newPassword);
            this.StudentDAO.update("update student set password=? where student_id=?",newPassword,student_id);
            return true;
        }else{
            return false;
        }
    }

    public boolean teacherChangePassword(String teacher_id, String oldPassword,String newPassword){
        oldPassword=MD5.getMd5(oldPassword);
        String sql="select * from teacher where teacher_id=? and password=?";
        student obj=null;
        obj=this.StudentDAO.querySingleRow(sql,teacher_id,oldPassword);
        if(obj!=null){
            newPassword=MD5.getMd5(newPassword);
            this.StudentDAO.update("update student set password=? where student_id=?",newPassword,teacher_id);
            return true;
        }else{
            return false;
        }
    }

    public boolean matchEmailFormat(String emailStr){
            if(emailStr == null || emailStr == ""){
                return false;
            }
            Pattern compile = Pattern.compile("^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))$",Pattern.CASE_INSENSITIVE);
            Matcher matcher = compile.matcher( emailStr);
            boolean matches = matcher.matches();
            return matches;
    }

    public boolean sendEmail(String emailStr,String url) throws Exception {

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

            //QQ才有！其他邮箱就不用
            Session session=Session.getDefaultInstance(prop, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    //发件人邮件用户名、授权码
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
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(emailStr.toString()));
            //邮件标题
            message.setSubject("修改密码");
            //邮件的文本内容
            message.setContent(url,"text/html;charset=UTF-8");

            //5.发送邮件
            ts.sendMessage(message,message.getAllRecipients());

            //6.关闭连接
            ts.close();

            return true;
    }
    public boolean studentForgetPassword(String student_id){
        student studentTemp = this.StudentDAO.querySingleRow("select * from student where student_id=?",student_id);
        if(studentTemp == null || !this.matchEmailFormat(studentTemp.getEmail())) return false;
        this.StudentDAO.update("update student set forget_password_flag=? where student_id=?",1,student_id);
        String hashcodeStr = "hashcode=" + this.StudentDAO.queryOneValue("select hashcode from student where student_id=?",student_id);
        String url = "http://localhost:8080/loginController.do?operateParameter=afterForgetChangePassword" + "&" + hashcodeStr;

        try {
            if(!this.sendEmail(studentTemp.getEmail(), url)) return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean teacherForgetPassword(String teacher_id){
        teacher teacherTemp = this.TeacherDAO.querySingleRow("select * from teacher where teacher_id=?",teacher_id);
        if(teacherTemp == null || !this.matchEmailFormat(teacherTemp.getEmail())) return false;
        this.TeacherDAO.update("update teacher set forget_password_flag=? where teacher_id=?",1,teacher_id);
        String hashcodeStr = "hashcode=" + this.TeacherDAO.queryOneValue("select hashcode from teacher where teacher_id=?",teacher_id);
        String url = "http://localhost:8080/loginController.do?operateParameter=afterForgetChangePassword" + "&" + hashcodeStr;

        try {
            if(!this.sendEmail(teacherTemp.getEmail(), url)) return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean forgetAndChangePassword(String hashcode,String password){
        System.out.println(hashcode + " " + password);
        student studentTemp = this.StudentDAO.querySingleRow("select * from student where hashcode=?",hashcode);
        teacher teacherTemp = this.TeacherDAO.querySingleRow("select * from teacher where hashcode=?",hashcode);

        if(studentTemp != null && studentTemp.getForget_password_flag() != 0){
            this.StudentDAO.update("update student set password =?,forget_password_flag=? where hashcode=?",MD5.getMd5(password),0,hashcode);
            return true;
        }

        if(teacherTemp != null && teacherTemp.getForget_password_flag() != 0){
            this.TeacherDAO.update("update teacher set password =?,forget_password_flag=? where hashcode=?",MD5.getMd5(password),0,hashcode);
            return true;
        }

        return false;
    }

//    public static void main(String[] args) {
//        loginService LoginService = new loginService();
//        LoginService.TeacherDAO = new teacherDAO();
//        LoginService.StudentDAO = new studentDAO();
//        LoginService.StudentDAO.update("update student set password =?,forget_password_flag=? where hashcode=?",MD5.getMd5("123.com"),0,"ac7746e09ae18b5cb450a54b8b8e77b3");
//        teacher teacherTemp = LoginService.TeacherDAO.querySingleRow("select * from teacher where hashcode=?","ac7746e09ae18b5cb450a54b8b8e77b3");
//    }
}
