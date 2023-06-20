package MySSM.Service;

import MySSM.DAO.*;
import MySSM.DATA.*;

import java.util.ArrayList;
import java.util.List;

public class queryService {
    private class_realityDAO Class_realityDAO;
    private course_question_bankDAO Course_question_bankDAO;
    private courseDAO CourseDAO;
    private deptDAO DeptDAO;
    private gradeDAO GradeDAO;
    private majorDAO MajorDAO;
    private paper_bankDAO Paper_bankDAO;
    private paper_in_teach_classDAO Paper_in_teach_classDAO;
    private student_courseDAO Student_courseDAO;
    private student_have_taken_courseDAO Student_have_taken_courseDAO;
    private student_selected_courseDAO Student_selected_courseDAO;
    private student_teach_classDAO Student_teach_classDAO;
    private studentDAO StudentDAO;
    private teach_class_time_roomDAO Teach_class_timeDAO;
    private teach_classDAO Teach_classDAO;
    private teacherDAO TeacherDAO;

    //查学生
    public List<student> queryStudent(String keyWord){
        String sql="select student_id,name,class_id,sex from student where student_id=? or name like ? or class_id=?";
        return this.StudentDAO.queryMultiRow(sql,keyWord,keyWord+"%",keyWord);
    }

    //查老师
    public List<teacher> queryTeacher(String keyWord){
        String sql="select teacher_id,name,dept_id,sex from teacher where teacher_id=? or name like ? or dept_id=?";
        return this.TeacherDAO.queryMultiRow(sql,keyWord,keyWord+"%",keyWord);
    }

    /*
    //查整个班级的学生
    public List<student> queryStudentByClass(String class_id){
        String sql = "select * from student where class_id = ?";
        return this.StudentDAO.queryMultiRow(sql, class_id);
    }

    //查整个学院的老师
    public List<teacher> queryTeacherByDept(String dept_id){
        String sql = "select * from teacher where dept_id = ?";
        return this.TeacherDAO.queryMultiRow(sql, dept_id);
    }
     */

    //根据学生id查找教学班时间
    public List<student_teach_class_time_room_info> queryStudent_teach_class_time(String student_id){
        //学生姓名
        String student_name=(String) this.StudentDAO.queryOneValue("select name from student where student_id=?",student_id);
        //根据学生id查找教学班表
        List<student_teach_class> student_teach_classList=this.Student_teach_classDAO.queryMultiRow("select * from student_teach_class where student_id=?",student_id);
        //根据教学班数量初始化返回值数组大小
        List<student_teach_class_time_room_info> student_teach_class_timeList=new ArrayList<>(student_teach_classList.size());
        for (int i = 0; i < student_teach_classList.size(); i++) {
            //教学班id
            String teach_class_id=student_teach_classList.get(i).getTeach_class_id();
            //查找对应教学班id的教学班表
            teach_class teachClass=this.Teach_classDAO.querySingleRow("select * from teach_class where teach_class_id=?",teach_class_id);//根据教学班id查找教学班相关信息
            //查找对应教学班的课程，老师，时间信息
            String course_name=(String) this.CourseDAO.queryOneValue("select name from course where course_id=?",teachClass.getCourse_id());
            String teacher_name=(String) this.TeacherDAO.queryOneValue("select name from teacher where teacher_id=?",teachClass.getTeacher_id());;
            String time=(String) this.Teach_class_timeDAO.queryOneValue("select time from teach_class_time_room where teach_class_id=?",teachClass.getTeach_class_id());
            String room=(String) this.Teach_class_timeDAO.queryOneValue("select room from teach_class_time_room where teach_class_id=?",teachClass.getTeach_class_id());

            //设置单个student_teach_class_time信息
            student_teach_class_time_room_info student_teach_class_time_roomTemp = new student_teach_class_time_room_info();
            student_teach_class_time_roomTemp.setTeach_class_id(teach_class_id);
            student_teach_class_time_roomTemp.setCourse_name(course_name);
            student_teach_class_time_roomTemp.setTeacher_name(teacher_name);
            student_teach_class_time_roomTemp.setTime(time);
            student_teach_class_time_roomTemp.setRoom(room);

            student_teach_class_timeList.add(student_teach_class_time_roomTemp);
        }

        return student_teach_class_timeList;
    }

    //根据老师id查找时间
    public List<teacher_teach_class_time_room_info> queryTeacher_teach_class_time(String teacher_id){
        //老师姓名
        //String teacher_name=(String) this.TeacherDAO.queryOneValue("select name from teacher where teacher_id=?",teacher_id);
        //根据老师id查找教学班表
        List<teach_class> teach_classList=this.Teach_classDAO.queryMultiRow("select * from teach_class where teacher_id=?",teacher_id);

        //根据教学班数量初始化返回值数组大小
        List<teacher_teach_class_time_room_info> teacher_teach_class_timeList=new ArrayList<>(teach_classList.size());
        for (int i = 0; i < teach_classList.size(); i++) {
            teach_class teachClass=teach_classList.get(i);

            //查找对应教学班的课程,老师,时间信息
            String course_name=(String) this.CourseDAO.queryOneValue("select name from course where course_id=?",teachClass.getCourse_id());
            String time=(String) this.Teach_class_timeDAO.queryOneValue("select time from teach_class_time_room where teach_class_id=?",teachClass.getTeach_class_id());
            String room=(String) this.Teach_class_timeDAO.queryOneValue("select room from teach_class_time_room where teach_class_id=?",teachClass.getTeach_class_id());

            //设置单个teacher_teach_class_time信息
            teacher_teach_class_time_room_info teacher_teach_class_time_roomTemp = new teacher_teach_class_time_room_info();
            teacher_teach_class_time_roomTemp.setTeach_class_id(teachClass.getTeach_class_id());
            teacher_teach_class_time_roomTemp.setCourse_name(course_name);
            teacher_teach_class_time_roomTemp.setTime(time);
            teacher_teach_class_time_roomTemp.setRoom(room);

            teacher_teach_class_timeList.add(teacher_teach_class_time_roomTemp);
        }

        return teacher_teach_class_timeList;
    }

    //查询student_info
    public List<student_info> queryStudent_info(String keyWord){
        List<student_info> student_infoList = new ArrayList<>();

        String class_id, grade_id, major_id, major_name, dept_id, dept_name;

        List<student> studentList = this.queryStudent(keyWord);
        for (int i = 0; i < studentList.size(); i++) {
            student stu = studentList.get(i);
            class_id = stu.getClass_id();
            grade_id = (String) Class_realityDAO.queryOneValue("select grade_id from class where class_id = ?", class_id);
            major_id = (String) Class_realityDAO.queryOneValue("select major_id from class where class_id = ?", class_id);
            major_name = (String) MajorDAO.queryOneValue("select major_name from major where major_id = ?", major_id);
            dept_id = (String) MajorDAO.queryOneValue("select dept_id from major where major_id = ?", major_id);
            dept_name = (String) DeptDAO.queryOneValue("select dept_name from dept where dept_id = ?", dept_id);
            student_info student_infoTemp = new student_info(stu.getName(),stu.getSex(),stu.getStudent_id(),class_id,grade_id,major_name,dept_name);
            student_infoList.add(student_infoTemp);
        }

        return student_infoList;
    }

    //查询teacher_info
    public List<teacher_info> queryTeacher_info(String keyWord){
        List<teacher_info> teacher_infoList = new ArrayList<>();

        String dept_id, dept_name;

        List<teacher> teacherList = queryTeacher(keyWord);
        for (int i = 0; i < teacherList.size(); i++) {
            teacher tea = teacherList.get(i);
            dept_id = tea.getDept_id();
            dept_name = (String) this.DeptDAO.queryOneValue("select dept_name from dept where dept_id = ?", dept_id);
            teacher_info teacher_infoTemp = new teacher_info(tea.getName(),tea.getSex(),tea.getTeacher_id(),dept_name);
            teacher_infoList.add(teacher_infoTemp);
        }

        return teacher_infoList;
    }

//    public static void main(String[] args) {
//        queryService QueryService = new queryService();
//        QueryService.Class_realityDAO = new class_realityDAO();
//        QueryService.Course_question_bankDAO = new course_question_bankDAO();
//        QueryService.CourseDAO = new courseDAO();
//        QueryService.DeptDAO = new deptDAO();
//        QueryService.GradeDAO = new gradeDAO();
//        QueryService.MajorDAO = new majorDAO();
//        QueryService.Paper_bankDAO = new paper_bankDAO();
//        QueryService.Paper_in_teach_classDAO = new paper_in_teach_classDAO();
//        QueryService.Student_courseDAO = new student_courseDAO();
//        QueryService.Student_have_taken_courseDAO = new student_have_taken_courseDAO();
//        QueryService.Student_selected_courseDAO = new student_selected_courseDAO();
//        QueryService.Student_teach_classDAO = new student_teach_classDAO();
//        QueryService.StudentDAO = new studentDAO();
//        QueryService.Teach_class_timeDAO = new teach_class_timeDAO();
//        QueryService.Teach_classDAO = new teach_classDAO();
//        QueryService.TeacherDAO = new teacherDAO();
//        QueryService.queryStudent_info("xhj");
//    }
}
