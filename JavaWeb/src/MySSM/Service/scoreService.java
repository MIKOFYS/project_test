package MySSM.Service;

import MySSM.DAO.*;
import MySSM.DATA.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class scoreService {
    private studentDAO StudentDAO;
    private teach_classDAO Teach_classDAO;
    private courseDAO CourseDAO;
    private student_courseDAO Student_courseDAO;
    private paper_in_teach_classDAO Paper_in_teach_classDAO;
    private student_teach_classDAO Student_teach_classDAO;
    private fileUploadAndDownloadService FileUploadAndDownloadService;
    private final String studentUpPaperPath="D:\\IDEA_Code_Store_Position\\JavaWeb\\webApp\\WEB-INF\\studentUpPaperPath\\";
    public List<teach_class_course_info> queryTeach_class(String teacher_id){
        List<teach_class> teach_classList=this.Teach_classDAO.queryMultiRow("select * from teach_class where teacher_id=?",teacher_id);
        List<teach_class_course_info> teach_class_courseList=new ArrayList<>(teach_classList.size());
        for (int i = 0; i < teach_classList.size(); i++) {
            teach_class teachClass=teach_classList.get(i);
            String courseName=(String) this.CourseDAO.queryOneValue("select name from course where course_id=?",teachClass.getCourse_id());
            teach_class_course_info teach_class_courseTemp = new teach_class_course_info(teachClass.getTeach_class_id(),teachClass.getCourse_id(),courseName);
            teach_class_courseList.add(teach_class_courseTemp);
        }
        return teach_class_courseList;
    }

    public List<paper_in_teach_class_info> queryPaper_in_teach_class(String teach_class_id){
        List<paper_in_teach_class> paper_in_teach_classList=this.Paper_in_teach_classDAO.queryMultiRow("select * from paper_in_teach_class where teach_class_id=?",teach_class_id);
        List<paper_in_teach_class_info> paper_in_teach_class_infoList=new ArrayList<>(paper_in_teach_classList.size());
        for (int i = 0; i < paper_in_teach_classList.size(); i++) {
            paper_in_teach_class paper_in_teach_classTemp=paper_in_teach_classList.get(i);
            String studentName=(String) this.StudentDAO.queryOneValue("select name from student where student_id=?",paper_in_teach_classTemp.getStudent_id());
            paper_in_teach_class_info paper_in_teach_class_infoTemp = new paper_in_teach_class_info(paper_in_teach_classTemp.getTeach_class_id(),paper_in_teach_classTemp.getStudent_id(),studentName,paper_in_teach_classTemp.getPaper_id(),paper_in_teach_classTemp.getScore(),paper_in_teach_classTemp.getTime());
            paper_in_teach_class_infoList.add(paper_in_teach_class_infoTemp);
        }
        return paper_in_teach_class_infoList;
    }

    public List<student_course_info> queryStudent_course(String teach_class_id){
        String course_id=(String) this.Teach_classDAO.queryOneValue("select course_id from teach_class where teach_class_id=?",teach_class_id);
        List<Object> student_idList=this.Student_teach_classDAO.queryOneColumnList("select student_id from student_teach_class where teach_class_id=?",1,teach_class_id);
        List<student_course_info> student_courseList=new ArrayList<>(student_idList.size());
        for (int i = 0; i < student_idList.size(); i++) {
            String student_id=(String) student_idList.get(i);
            String student_name=(String) this.StudentDAO.queryOneValue("select name from student where student_id=?",student_id);
            Double score=(Double) this.Student_courseDAO.queryOneValue("select score from student_course where student_id=? and course_id=?",student_id,course_id);
            student_course_info student_course_infoTemp=new student_course_info(student_id,student_name,course_id,score);
            student_courseList.add(student_course_infoTemp);
        }
        return student_courseList;
    }

    public boolean checkPaper(String teach_class_id,String student_id,String paper_id,double score){
        if(score<0||score>100){
            return false;
        }
        this.Paper_in_teach_classDAO.update("update paper_in_teach_class set score =? where teach_class_id=? and student_id=? and paper_id=?",score,teach_class_id,student_id,paper_id);
        return true;
    }

    public String downloadPaper(String teach_class_id, String student_id,String paper_id){
        String directory = (String) this.Paper_in_teach_classDAO.queryOneValue("select directory from paper_in_teach_class where teach_class_id=? and student_id=? and paper_id=?",teach_class_id, student_id,paper_id);
        return this.FileUploadAndDownloadService.fileToBase64(this.studentUpPaperPath,directory);
    }


    public boolean checkStudentCourseScore(String student_id,String course_id,double score){
        if(score<0||score>100){
            return false;
        }
        this.Student_courseDAO.update("update student_course set score=? where student_id=? and course_id=?",score,student_id,course_id);
        return true;
    }

//    public static void main(String[] args) {
//        scoreService ScoreService = new scoreService();
//        ScoreService.StudentDAO = new studentDAO();
//        ScoreService.Teach_classDAO = new teach_classDAO();
//        ScoreService.CourseDAO = new courseDAO();
//        ScoreService.Student_courseDAO = new student_courseDAO();
//        ScoreService.Paper_in_teach_classDAO = new paper_in_teach_classDAO();
//        ScoreService.Student_teach_classDAO = new student_teach_classDAO();
//        ScoreService.FileUploadAndDownloadService = new fileUploadAndDownloadService();
//    }
}
