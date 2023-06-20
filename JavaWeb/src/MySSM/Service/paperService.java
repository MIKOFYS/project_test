package MySSM.Service;

import MySSM.DAO.*;
import MySSM.DATA.course_teach_class_info;
import MySSM.DATA.paper_id;
import MySSM.DATA.teach_class;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class paperService {

    private fileUploadAndDownloadService FileUploadAndDownloadService;
    private student_teach_classDAO Student_teach_classDAO;
    private teach_classDAO Teach_classDAO;
    private course_question_bankDAO Course_question_bankDAO;
    private courseDAO CourseDAO;
    private paper_in_teach_classDAO Paper_in_teach_classDAO;
    private paper_bankDAO Paper_bankDAO;

    private final String studentDownloadPaperPath="D:\\IDEA_Code_Store_Position\\JavaWeb\\webApp\\WEB-INF\\studentDownloadPaperPath\\";
    private final String studentUpPaperPath="D:\\IDEA_Code_Store_Position\\JavaWeb\\webApp\\WEB-INF\\studentUpPaperPath\\";

    public List<course_teach_class_info> queryCourse(String student_id){
        //取得一个同学的多个教学班班号
        List<Object> teach_class_idList = this.Student_teach_classDAO.queryOneColumnList("select teach_class_id from student_teach_class where student_id = ?", 1, student_id);

        List<course_teach_class_info> course_teach_classList=new ArrayList<>(teach_class_idList.size());

        for (int i = 0; i < teach_class_idList.size(); i++) {
            teach_class teach_classTemp=this.Teach_classDAO.querySingleRow("select teach_class_id,course_id from teach_class where teach_class_id = ?", (String)teach_class_idList.get(i));
            String courseName=(String) this.CourseDAO.queryOneValue("select name from course where course_id = ?",teach_classTemp.getCourse_id());
            course_teach_class_info course_teach_classTemp=new course_teach_class_info(teach_classTemp.getCourse_id(),courseName,teach_classTemp.getTeach_class_id());
            course_teach_classList.add(course_teach_classTemp);
        }
        return course_teach_classList;
    }

    public List<paper_id> queryPaper(String course_id){
        //取得一个课程号的多个试卷号
        List<Object> course_question_bankTempList = this.Course_question_bankDAO.queryOneColumnList("select paper_id from course_question_bank where course_id = ?", 1, course_id);
        List<paper_id> course_question_bankList=new ArrayList<>(course_question_bankTempList.size());
        for(int i=0;i<course_question_bankTempList.size();i++){
            paper_id paper_idTemp=new paper_id((String) course_question_bankTempList.get(i));
            course_question_bankList.add(paper_idTemp);
        }
        return course_question_bankList;
    }


    public boolean uploadPaper(String teach_class_id,String student_id,String paper_id,String directory){
        String prefixFileName=teach_class_id+"_"+student_id+"_"+paper_id;
        String newFileName = this.FileUploadAndDownloadService.base64ToFile(directory,this.studentUpPaperPath,prefixFileName);
        if(newFileName.startsWith("result:")) return false;
        DateTimeFormatter fmDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String uploadPaperTime = LocalDateTime.now().format(fmDate);
        if(this.Paper_in_teach_classDAO.queryOneValue("select student_id from paper_in_teach_class where student_id=? and paper_id=?",student_id,paper_id)!=null){
            this.Paper_in_teach_classDAO.update("update paper_in_teach_class set score=?,directory=?,time=? where teach_class_id=? and student_id=? and paper_id=?", null,newFileName,uploadPaperTime,teach_class_id,student_id,paper_id);
        }else{
            this.Paper_in_teach_classDAO.update("insert into paper_in_teach_class values(?,?,?,null,?,?)", teach_class_id, student_id, paper_id, newFileName, uploadPaperTime);
        }
        return true;
    }

    public String downloadPaper(String paper_id){
        String directory = (String) this.Paper_bankDAO.queryOneValue("select directory from paper_bank where paper_id=?",paper_id);
//        System.out.println(paper_id + " "+ directory);
        if (directory == null || directory.equals("")) return "result:downloadPaper fail";
        return this.FileUploadAndDownloadService.fileToBase64(this.studentDownloadPaperPath, directory);
    }
}
