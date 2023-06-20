package MySSM.Init;

import MySSM.DAO.*;
import MySSM.Service.MD5;

public class init {
    public class_realityDAO Class_realityDAO;
    public course_question_bankDAO Course_question_bankDAO;
    public courseDAO CourseDAO;
    public deptDAO DeptDAO;
    public gradeDAO GradeDAO;
    public majorDAO MajorDAO;
    public paper_bankDAO Paper_bankDAO;
    public student_courseDAO Student_courseDAO;
    public student_have_taken_courseDAO Student_have_taken_courseDAO;
    public studentDAO StudentDAO;
    public student_teach_classDAO Student_teach_classDAO;
    public teach_class_time_roomDAO Teach_class_timeDAO;
    public teach_classDAO Teach_classDAO;
    public teacherDAO TeacherDAO;
    public init() {
        this.Class_realityDAO=new class_realityDAO();
        this.Course_question_bankDAO=new course_question_bankDAO();
        this.CourseDAO=new courseDAO();
        this.DeptDAO=new deptDAO();
        this.GradeDAO=new gradeDAO();
        this.MajorDAO=new majorDAO();
        this.Paper_bankDAO=new paper_bankDAO();
        this.Student_courseDAO=new student_courseDAO();
        this.Student_have_taken_courseDAO=new student_have_taken_courseDAO();
        this.StudentDAO=new studentDAO();
        this.Student_teach_classDAO=new student_teach_classDAO ();
        this.Teach_class_timeDAO=new teach_class_time_roomDAO();
        this.Teach_classDAO=new teach_classDAO();
        this.Teach_classDAO=new teach_classDAO();
        this.TeacherDAO=new teacherDAO();
    }

    public void initClass_reality(){
        String class_id="CLASS100";
        String grade_id="GRADE100";
        String major_id="MAJOR100";
        for(int i=1;i<=9;i++){
            this.Class_realityDAO.update("insert into class values(?,?,?)",class_id+i,grade_id+i,major_id+i);
        }
    }
    public void initCourse(){
        String course_id="COURSE100";
        String name[]={"高等数学","数据结构","算法设计与分析","计算机组成原理","操作系统","大学物理","计算机导论","体育","数字电路","数据库原理"};
        Integer credit=4;
        Integer period=24;
        for(int i=1;i<=9;i++){
            this.CourseDAO.update("insert into course values(?,?,?,?)",course_id+i,name[i],credit,period);
        }
    }

    public void initStudent(){
        String student_id="STUDENT100";
        String name="ST_";
        String sex="男";
        String age="20";
        String class_id="CLASS100";
        String password="123.com";
        password= MD5.getMd5(password);
        for(int i=1;i<=9;i++){
            this.StudentDAO.update("insert into student values(?,?,?,?,?,?)",student_id+i,name+i,sex,age,class_id+i,password);
        }
    }

    public void initTeacher(){
        String teacher_id="TEACHER100";
        String name="TE_";
        String sex="男";
        String age="20";
        String dept_id="DEPT";
        String password="123.com";
        password= MD5.getMd5(password);
        for(int i=1;i<=5;i++){
            this.TeacherDAO.update("insert into teacher values(?,?,?,?,?,?)",teacher_id+i,name+i,sex,age,dept_id+i,password);
        }
    }

    public void initCourse_question_bank(){
        String course_id="COURSE100";
        String paper_id="";
        for (int i=1;i<=9;i++){
            for (int j = 1; j <=3; j++) {
                this.Course_question_bankDAO.update("insert into course_question_bank values(?,?)",course_id+i,course_id+i+"_"+j);
            }
        }
    }

    public void initDept(){
        String dept_id="DEPT";
        String dept_name[]={"计算机学院","光电学院","通信学院","自动化学院","传媒学院"};
        for (int i = 0; i < 5; i++) {
            this.DeptDAO.update("insert into dept values(?,?)",dept_id+(i+1),dept_name[i]);
        }
    }

    public void initGrade(){
        String grade_id="GRADE";
        for (int i = 1; i <=9; i++) {
            this.GradeDAO.update("insert into grade values(?)",grade_id+i);
        }
    }

    public void initMajor(){
        String dept_id[]={"DEPT1","DEPT2","DEPT3","DEPT4","DEPT5"};
        String major_name[][]={{"计算机科学与技术","大数据与技术","人工智能","网络工程"},{"光电工程"},{"通信技术专业","通信工程设计与管理专业"},{"自动化制造"},{"摄影"}};
        String major_id[][]={{"MAJOR1001","MAJOR1002","MAJOR1003","MAJOR1004"},{"MAJOR1005"},{"MAJOR1006","MAJOR1007"},{"MAJOR1008"},{"MAJOR1009"}};
        for (int i=0;i<5;i++){
            for (int j = 0; j < major_name[i].length; j++) {
                this.MajorDAO.update("insert into major values(?,?,?)",major_id[i][j],major_name[i][j],dept_id[i]);
            }
        }
    }

    public void initStudent_course(){
        String student_id="STUDENT100";
        String course_id="COURSE100";
        for (int i = 1; i <=9; i++) {
            for (int j = 1; j <=9 ; j++) {
                this.Student_courseDAO.update("insert into student_course values(?,?,?)",student_id+i,course_id+j,null);
            }
        }
    }

    public void initStudent_teach_class(){
        String student_id="STUDENT100";
        String teach_class_id="TEACH_CLASS100";
        for (int i = 1; i <=9; i++) {
            for (int j = 1; j <=5 ; j++) {
                this.Student_teach_classDAO.update("insert into student_teach_class values(?,?)",student_id+i,teach_class_id+j);
            }
        }
    }

    public void initTeach_class(){
        String teach_class_id="TEACH_CLASS100";
        String course_id="COURSE100";
        String teacher_id="TEACHER100";
        for (int i = 1; i <= 5; i++) {
            this.Teach_classDAO.update("insert into teach_class values(?,?,?)",teach_class_id+i,course_id+i,teacher_id+i);
        }
    }
    public static void main(String[] args) {

        init INIT = new init();
        //INIT.initTeacher();
        //INIT.initStudent();
        //INIT.initCourse();
        //INIT.initClass_reality();
        //INIT.initCourse_question_bank();
        //INIT.initDept();
        //INIT.initGrade();
        //INIT.initMajor();
        //INIT.initStudent_course();
        //INIT.initStudent_teach_class();
        //INIT.initTeach_class();
    }
}
