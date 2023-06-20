package MySSM.Controller;

import MySSM.DAO.studentDAO;
import MySSM.DAO.teacherDAO;
import MySSM.DATA.studentPersonInfo;
import MySSM.DATA.student_info;
import MySSM.DATA.teacherPersonInfo;
import MySSM.Service.queryPersonInfoService;
import MySSM.Service.queryService;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Base64;
import java.util.List;

public class queryPersonInfoController {
    private queryPersonInfoService QueryPersonInfoService=null;

    private final String studentPicturePath="D:\\IDEA_Code_Store_Position\\JavaWeb\\webApp\\WEB-INF\\studentPicturePath\\";
    private final String teacherPicturePath="D:\\IDEA_Code_Store_Position\\JavaWeb\\webApp\\WEB-INF\\teacherPicturePath\\";

    private String queryStudentPersonInfo(HttpSession session) throws IOException {
        //获取学生信息
        studentPersonInfo studentPersonInfoTemp = this.QueryPersonInfoService.queryStudentPersonInfo((String) session.getAttribute("student_id"));

        //获取学生图片路径，并判断文件是否存在
        String fileName = this.studentPicturePath+studentPersonInfoTemp.getPicture();
        File file = new File(fileName);
        if(!file.exists()) return studentPersonInfoTemp.toJSONString();

        //获取图片类型
        String mediaType = fileName.substring(fileName.lastIndexOf(".") + 1);

        //获取输入输出流
        FileInputStream fileInputStream = new FileInputStream(fileName);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        //输入输出过程
        int len;
        byte[] buffer = new byte[1024];
        while((len = fileInputStream.read(buffer))!=-1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        byteArrayOutputStream.flush();
        byte[] data = byteArrayOutputStream.toByteArray();
        String encodeStr = "data:image/"+mediaType+";base64,"+Base64.getEncoder().encodeToString(data);

        //返回JSON数组
        String JSON = "JSON:"+studentPersonInfoTemp.toJSONString(encodeStr);
        //System.out.println(JSON);
        return JSON;
    }

    private String queryTeacherPersonInfo(HttpSession session) throws IOException {
        //获取老师信息
        teacherPersonInfo teacherPersonInfoTemp = this.QueryPersonInfoService.queryTeacherPersonInfo((String) session.getAttribute("teacher_id"));

        //获取老师图片路径，并判断文件是否存在
        String fileName = this.teacherPicturePath+teacherPersonInfoTemp.getPicture();
        File file = new File(fileName);
        if(!file.exists()) return teacherPersonInfoTemp.toJSONString();

        //获取图片类型
        String mediaType = fileName.substring(fileName.lastIndexOf(".") + 1);

        //获取输入输出流
        FileInputStream fileInputStream = new FileInputStream(fileName);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        //输入输出过程
        int len;
        byte[] buffer = new byte[1024];
        while((len = fileInputStream.read(buffer))!=-1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        byteArrayOutputStream.flush();
        byte[] data = byteArrayOutputStream.toByteArray();
        String encodeStr = "data:image/"+mediaType+";base64,"+Base64.getEncoder().encodeToString(data);

        //返回JSON数组
        String JSON = "JSON:"+teacherPersonInfoTemp.toJSONString(encodeStr);
        //System.out.println(JSON);
        return JSON;
    }

    public String checkStudentPhone(String student_id,String phone){
        if(this.QueryPersonInfoService.checkStudentPhone(student_id,phone)){
            return "result:checkStudentPhone success";
        }
        return "result:checkStudentPhone fail";
    }

    public String checkStudentEmail(String student_id,String email){
        if(this.QueryPersonInfoService.checkStudentEmail(student_id,email)){
            return "result:checkStudentEmail success";
        }
        return "result:checkStudentEmail fail";
    }

    public String checkStudentPicture(String student_id,String picture){
        if(this.QueryPersonInfoService.checkStudentPicture(student_id,picture,this.studentPicturePath,student_id)){
            return "result:checkStudentPicture success";
        }
        return "result:checkStudentPicture fail";
    }

    public String checkTeacherPhone(String teacher_id,String phone){
        if(this.QueryPersonInfoService.checkTeacherPhone(teacher_id,phone)){
            return "result:checkTeacherPhone success";
        }
        return "result:checkTeacherPhone fail";
    }

    public String checkTeacherEmail(String teacher_id,String email){
        if(this.QueryPersonInfoService.checkTeacherEmail(teacher_id,email)){
            return "result:checkTeacherEmail success";
        }
        return "result:checkTeacherEmail fail";
    }

    public String checkTeacherPicture(String teacher_id,String picture){
        if(this.QueryPersonInfoService.checkTeacherPicture(teacher_id,picture,this.teacherPicturePath,teacher_id)){
            return "result:checkTeacherPicture success";
        }
        return "result:checkTeacherPicture fail";
    }

    private String queryIdentity(HttpSession session) {
        return "JSON:"+session.getAttribute("identity");
    }

    public String index() {
        return "html\\queryPersonInfo";
    }
}
