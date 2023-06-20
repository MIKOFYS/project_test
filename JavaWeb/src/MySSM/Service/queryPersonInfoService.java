package MySSM.Service;

import MySSM.DAO.*;
import MySSM.DATA.student;
import MySSM.DATA.studentPersonInfo;
import MySSM.DATA.teacher;
import MySSM.DATA.teacherPersonInfo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class queryPersonInfoService {
    private studentDAO StudentDAO = null;
    private teacherDAO TeacherDAO = null;
    private class_realityDAO Class_realityDAO= null;
    private deptDAO DeptDAO = null;
    private majorDAO MajorDAO = null;

    static String base64 = "data:image/png;base64,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NNjPCKMIo6KP8aqk0hNJQAUUUUAFFFFABRRRQAUUUUAFFFFABShiKSigDV07WZbNwC25O6tyK6SHWLa6jwG2Mf4T3rh+KckjRnKmgDr5kyeOtIkLHH90Vz9tq8sWA3K+hrorHUbe6QAMFb0JoAt28J4xxV6NCDj1pscZx8uDkckVJGGz/KgC7bnZgVdSVe9UIicirUgGzIPagCc3I42/jThcDHWsWW42HGcHvSpdccHmgDXe4x3pEvW9ayvtJOBU0WXPtQBrx33NWPtYrLCADinfNQB5BThTaBTAkBp4qMU8GgB9IXApHNR80AKSTUbuE6/lSyvtHuelVjk8mkA5pC3sPQU2iigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAXPqKckrRtlSR9KZRQBs2PiK5tMDduUdj3rpLHxJaXWPMHkv+YNcFTlcocg0Aep+auwMrAg8gg5zVd78gkMfpXGaZrslqwSQloj1Hp9K2JJlmxJG2VIyCO9AGoZFkJb8qjLkH5TVW2mY8HpUhl2EbefT2oAuQkl8k/gavxuwI9Pas+23O3p61ojIQcYI70AXEkBH86l3L71Utjk+1XfLX1oA8cdGRyjDDKcEelAoopgPFO6UUUAN60cDJ7DmiigCq7Fjk0lFFIAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigApcUUUAJiiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACtXS7tlzCckHke1FFAG3byMSAO/WtKOHgNiiigC3b/ALrtU7yk44oooAmt368dKueY3pRRQB//2Q==";

    public String base64ToFile(String base64, String savePath, String prefixFileName) {
        String extn = null;
        String noPrefixBase64 = null;
        if(base64.startsWith("data:image/png;base64,")) {
            extn = "png";
            noPrefixBase64 = base64.substring("data:image/png;base64,".length());
        } else if(base64.startsWith("data:image/jpeg;base64,") ) {
            extn = "jpg";
            noPrefixBase64 = base64.substring("data:image/jpeg;base64,".length());
        } else if(base64.startsWith("data:image/gif;base64,") ) {
            extn = "gif";
            noPrefixBase64 = base64.substring("data:image/gif;base64,".length());
        } else {
            System.out.println("文件格式不正确");
            return "result:false";
        }

        String fileName = prefixFileName + "." + extn;
        File file = null;
        //创建文件目录
        String filePath = savePath;
        File dir = new File(filePath);
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        BufferedOutputStream bos = null;
        java.io.FileOutputStream fos = null;
        try {
            byte[] bytes = Base64.getDecoder().decode(noPrefixBase64);
            file = new File(filePath + fileName);
            fos = new java.io.FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileName;
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

    public boolean matchPhoneFormat(String phoneStr){
        if(phoneStr == null || phoneStr == ""){
            return false;
        }
        Pattern compile = Pattern.compile("^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$",Pattern.CASE_INSENSITIVE);
        Matcher matcher = compile.matcher( phoneStr);
        boolean matches = matcher.matches();
        return matches;
    }

//    public static void main(String[] args) {
//        queryPersonInfoService.base64ToFile(base64,"D:\\IDEA_Code_Store_Position\\JavaWeb\\webApp\\WEB-INF\\picture\\","2020211853");
//    }

    public studentPersonInfo queryStudentPersonInfo(String student_id){
        student studentTemp = this.StudentDAO.querySingleRow("select * from student where student_id = ?", student_id);
        String grade_id = (String) this.Class_realityDAO.queryOneValue("select grade_id from class where class_id = ?",studentTemp.getClass_id());
        String major_id = (String) this.Class_realityDAO.queryOneValue("select major_id from class where class_id = ?",studentTemp.getClass_id());
        String dept_id = (String) this.MajorDAO.queryOneValue("select dept_id from major where major_id = ?",major_id);
        String major_name = (String) this.MajorDAO.queryOneValue("select major_name from major where major_id = ?",major_id);
        String dept_name = (String) this.DeptDAO.queryOneValue("select dept_name from dept where dept_id = ?",dept_id);
        studentPersonInfo studentPersonInfoTemp = new studentPersonInfo(studentTemp.getStudent_id(),studentTemp.getClass_id(),grade_id,major_name,dept_name,studentTemp.getName(),studentTemp.getSex(),studentTemp.getPhone(),studentTemp.getEmail(),studentTemp.getPicture());
        return studentPersonInfoTemp;
    }

    public teacherPersonInfo queryTeacherPersonInfo(String teacher_id){
        teacher teacherTemp = this.TeacherDAO.querySingleRow("select * from teacher where teacher_id = ?", teacher_id);
        String dept_name = (String) this.DeptDAO.queryOneValue("select dept_name from dept where dept_id = ?",teacherTemp.getDept_id());
        teacherPersonInfo teacherPersonInfoTemp = new teacherPersonInfo(teacherTemp.getTeacher_id(),dept_name,teacherTemp.getName(),teacherTemp.getSex(),teacherTemp.getPhone(),teacherTemp.getEmail(),teacherTemp.getPicture());
        return teacherPersonInfoTemp;
    }

    public Boolean checkStudentPhone(String student_id,String phone){
        if(!this.matchPhoneFormat(phone)) return false;
        this.StudentDAO.update("update student set phone =? where student_id=?",phone,student_id);
        return true;
    }

    public Boolean checkStudentEmail(String student_id,String email){
        if(!this.matchEmailFormat(email)) return false;
        this.StudentDAO.update("update student set email =? where student_id=?",email,student_id);
        return true;
    }

    public Boolean checkStudentPicture(String student_id,String base64, String savePath,String prefixFileName){
        String fileName = this.base64ToFile(base64, savePath, prefixFileName);
        if(fileName.startsWith("result:")) return false;
        this.StudentDAO.update("update student set picture =? where student_id=?",fileName,student_id);
        return true;
    }

    public Boolean checkTeacherPhone(String teacher_id,String phone){
        if(!this.matchPhoneFormat(phone)) return false;
        this.TeacherDAO.update("update teacher set phone =? where teacher_id=?",phone,teacher_id);
        return true;
    }

    public Boolean checkTeacherEmail(String teacher_id,String email){
        if(!this.matchEmailFormat(email)) return false;
        this.TeacherDAO.update("update teacher set email =? where teacher_id=?",email,teacher_id);
        return true;
    }

    public Boolean checkTeacherPicture(String teacher_id,String base64, String savePath,String prefixFileName){
        String fileName = this.base64ToFile(base64, savePath, prefixFileName);
        if(fileName.startsWith("result:")) return false;
        this.StudentDAO.update("update teacher set picture =? where teacher_id=?",fileName,teacher_id);
        return true;
    }

//    public static void main(String[] args) {
//        queryPersonInfoService QueryPersonInfoService = new queryPersonInfoService();
//        QueryPersonInfoService.StudentDAO = new studentDAO();
//        QueryPersonInfoService.TeacherDAO = new teacherDAO();
//        QueryPersonInfoService.Class_realityDAO = new class_realityDAO();
//        QueryPersonInfoService.DeptDAO = new deptDAO();
//        QueryPersonInfoService.MajorDAO = new majorDAO();
//        QueryPersonInfoService.queryStudentPersonInfo("2020211853");
//        QueryPersonInfoService.queryTeacherPersonInfo("2020211853");
//        QueryPersonInfoService.checkStudentPicture("2020211853",queryPersonInfoService.base64,"D:\\IDEA_Code_Store_Position\\JavaWeb\\webApp\\WEB-INF\\picture\\","2020211853");
//    }
}
