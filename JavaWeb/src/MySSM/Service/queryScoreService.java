package MySSM.Service;

import MySSM.DAO.*;
import MySSM.DATA.*;

import java.util.ArrayList;
import java.util.List;

public class queryScoreService {

    private student_courseDAO Student_courseDAO;
    private courseDAO CourseDAO;
    private student_teach_classDAO Student_teach_classDAO;
    private teach_classDAO Teach_classDAO;
    private teacherDAO TeacherDAO;

    public List<studentCourseScoreInfo> queryStudentScore(String student_id){
        List<student_course> student_courseList = this.Student_courseDAO.queryMultiRow("select * from student_course where student_id=?",student_id);
        List<student_teach_class> student_teach_classList = this.Student_teach_classDAO.queryMultiRow("select * from student_teach_class where student_id=?",student_id);

        List<studentCourseScoreInfo> studentCourseScoreInfoList = new ArrayList<>(student_courseList.size());
        for (int i = 0; i < student_courseList.size(); i++) {
            for (int j = 0; j < student_teach_classList.size(); j++) {
                String course_id = student_courseList.get(i).getCourse_id();
                String teach_class_id = student_teach_classList.get(j).getTeach_class_id();
                String teacher_id = (String) this.Teach_classDAO.queryOneValue("select teacher_id from teach_class where course_id=? and teach_class_id=?",course_id,teach_class_id);
                if(teacher_id == null) continue;

                String course_name = (String) this.CourseDAO.queryOneValue("select name from course where course_id=?",course_id);
                String teacher_name = (String) this.TeacherDAO.queryOneValue("select name from teacher where teacher_id=?",teacher_id);
                Double score = student_courseList.get(i).getScore();
                studentCourseScoreInfo studentCourseScoreInfoTemp = new studentCourseScoreInfo(course_id,course_name,teach_class_id,teacher_name,score);
                studentCourseScoreInfoList.add(studentCourseScoreInfoTemp);
            }
        }

        return  studentCourseScoreInfoList;
    }

//    public static void main(String[] args) {
//        queryScoreService QueryScoreService = new queryScoreService();
//        QueryScoreService.Student_courseDAO = new student_courseDAO();
//        QueryScoreService.CourseDAO = new courseDAO();
//        QueryScoreService.Student_teach_classDAO = new student_teach_classDAO();
//        QueryScoreService.Teach_classDAO = new teach_classDAO();
//        QueryScoreService.TeacherDAO = new teacherDAO();
//        QueryScoreService.queryStudentScore("STU_ID00");
//    }
}
