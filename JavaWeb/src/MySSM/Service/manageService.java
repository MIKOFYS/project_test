package MySSM.Service;

import MySSM.DAO.*;
import MySSM.DATA.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.io.FileUtils.delete;

public class manageService {
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
    private teach_class_time_roomDAO Teach_class_time_roomDAO;
    private teach_classDAO Teach_classDAO;
    private teacherDAO TeacherDAO;

    private final String studentPicturePath="D:\\IDEA_Code_Store_Position\\JavaWeb\\webApp\\WEB-INF\\studentPicturePath\\";
    private final String teacherPicturePath="D:\\IDEA_Code_Store_Position\\JavaWeb\\webApp\\WEB-INF\\teacherPicturePath\\";
    private final String studentDownloadPaperPath="D:\\IDEA_Code_Store_Position\\JavaWeb\\webApp\\WEB-INF\\studentDownloadPaperPath\\";
    private final String studentUpPaperPath="D:\\IDEA_Code_Store_Position\\JavaWeb\\webApp\\WEB-INF\\studentUpPaperPath\\";
//    private final String teacherDownloadStudentPaperPath="D:\\IDEA_Code_Store_Position\\JavaWeb\\webApp\\WEB-INF\\studentUpPaperPath\\";

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
        } else if(base64.startsWith("data:application/msword;base64,") ) {
            extn = "doc";
            noPrefixBase64 = base64.substring("data:application/msword;base64,".length());
        } else if(base64.startsWith("data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64,") ) {
            extn = "docx";
            noPrefixBase64 = base64.substring("data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64,".length());
        } else if(base64.startsWith("data:application/pdf;base64,") ) {
            extn = "pdf";
            noPrefixBase64 = base64.substring("data:application/pdf;base64,".length());
        }else {
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

    public void deleteFile(String filePath){
        File file = new File(filePath);
        File[] listFiles = file.listFiles();
        if(listFiles != null)
        {
            for(File f: listFiles)
            {
                if(f.isDirectory())
                {
                    try {
                        delete(f);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else
                {
                    f.delete();
                }
            }
        }
        file.delete();
    }

//    public static void main(String[] args) {
//        manageService ManageService = new manageService();
//        ManageService.deleteFile(ManageService.studentPicturePath + "SID99.png");
//    }

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

    public boolean administratorLogin(String administrator_id, String password){
        password=MD5.getMd5(password);
        String sql="select * from administrator where administrator_id=? and password=?";
        student obj=null;
        obj=this.StudentDAO.querySingleRow(sql,administrator_id,password);
        if(obj!=null){
            return true;
        }else{
            return false;
        }
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    public List<student> queryStudent(){
        List<student> studentList = this.StudentDAO.queryMultiRow("select * from student");
        return studentList;
    }

    public boolean deleteStudent(String student_id,String name,String sex,String phone,String email,String picture,String class_id,String password,String hashcode,Integer forget_password_flag){
        this.deleteFile(this.studentPicturePath+picture);
        if(this.StudentDAO.execute("delete from student where student_id=? and name=? and sex=? and phone=? and email=? and picture=? and class_id=? and password=? and hashcode=? and forget_password_flag=?",student_id,name,sex,phone,email,picture,class_id,password,hashcode,forget_password_flag)!=0) return true;
        return false;
    }

    public boolean addStudent1(String student_id1,String name1,String sex1,String phone1,String email1,String picture1,String class_id1,String password1,String hashcode1,Integer forget_password_flag1){
        if(this.StudentDAO.execute("insert into student values(?,?,?,?,?,?,?,?,?,?)",student_id1,name1,sex1,phone1,email1,picture1,class_id1,password1,hashcode1,forget_password_flag1)!=0) return true;
        return false;
    }

    public boolean addStudent2(String student_id2,String name2,String sex2,String phone2,String email2,String picture2,String class_id2,String password2,String hashcode2,Integer forget_password_flag2){
        if(!student_id2.equals(hashcode2)) return false;
        String fileName = this.base64ToFile(picture2,this.studentPicturePath,student_id2);
        if(fileName.startsWith("result:")) return false;
        if(this.StudentDAO.execute("insert into student values(?,?,?,?,?,?,?,?,?,?)",student_id2,name2,sex2,phone2,email2,fileName,class_id2,MD5.getMd5(password2),MD5.getMd5(hashcode2),forget_password_flag2)!=0) return true;
        return false;
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    public List<teacher> queryTeacher(){
        List<teacher> teacherList = this.TeacherDAO.queryMultiRow("select * from teacher");
        return teacherList;
    }

    public boolean deleteTeacher(String teacher_id,String name,String sex,String phone,String email,String picture,String dept_id,String password,String hashcode,Integer forget_password_flag){
        this.deleteFile(this.teacherPicturePath+picture);
        if(this.TeacherDAO.execute("delete from teacher where teacher_id=? and name=? and sex=? and phone=? and email=? and picture=? and dept_id=? and password=? and hashcode=? and forget_password_flag=?",teacher_id,name,sex,phone,email,picture,dept_id,password,hashcode,forget_password_flag)!=0) return true;
        return false;
    }

    public boolean addTeacher1(String teacher_id1,String name1,String sex1,String phone1,String email1,String picture1,String dept_id1,String password1,String hashcode1,Integer forget_password_flag1){
        if(this.TeacherDAO.execute("insert into teacher values(?,?,?,?,?,?,?,?,?,?)",teacher_id1,name1,sex1,phone1,email1,picture1,dept_id1,password1,hashcode1,forget_password_flag1)!=0) return true;
        return false;
    }

    public boolean addTeacher2(String teacher_id2,String name2,String sex2,String phone2,String email2,String picture2,String dept_id2,String password2,String hashcode2,Integer forget_password_flag2){
        if(!teacher_id2.equals(hashcode2)) return false;
        String fileName = this.base64ToFile(picture2,this.teacherPicturePath,teacher_id2);
        if(fileName.startsWith("result:")) return false;
        if(this.TeacherDAO.execute("insert into teacher values(?,?,?,?,?,?,?,?,?,?)",teacher_id2,name2,sex2,phone2,email2,fileName,dept_id2,MD5.getMd5(password2),MD5.getMd5(hashcode2),forget_password_flag2)!=0) return true;
        return false;
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    public List<class_reality> queryClass(){
        List<class_reality> classList = this.Class_realityDAO.queryMultiRow("select * from class");
        return classList;
    }

    public boolean deleteClass(String class_id,String grade_id,String major_id){
        if(this.Class_realityDAO.execute("delete from class where class_id=? and grade_id=? and major_id=?",class_id,grade_id,major_id)!=0) return true;
        return false;
    }

    public boolean addClass(String class_id,String grade_id,String major_id){
        if(this.Class_realityDAO.execute("insert into class values(?,?,?)",class_id,grade_id,major_id)!=0) return true;
        return false;
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    public List<course> queryCourse(){
        List<course> courseList = this.CourseDAO.queryMultiRow("select * from course");
        return courseList;
    }

    public boolean deleteCourse(String course_id,String name,Integer credit,Integer period){
        if(this.CourseDAO.execute("delete from course where course_id=? and name=? and credit=? and period=?",course_id,name,credit,period)!=0) return true;
        return false;
    }

    public boolean addCourse(String course_id,String name,Integer credit,Integer period){
        if(this.CourseDAO.execute("insert into course values(?,?,?,?)",course_id,name,credit,period)!=0) return true;
        return false;
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    public List<course_question_bank> queryCourse_question_bank(){
        List<course_question_bank> course_question_bankList = this.Course_question_bankDAO.queryMultiRow("select * from course_question_bank");
        return course_question_bankList;
    }

    public boolean deleteCourse_question_bank(String course_id,String paper_id){
        if(this.Course_question_bankDAO.execute("delete from course_question_bank where course_id=? and paper_id=?",course_id,paper_id)!=0) return true;
        return false;
    }

    public boolean addCourse_question_bank(String course_id,String paper_id){
        if(this.Course_question_bankDAO.execute("insert into course_question_bank values(?,?)",course_id,paper_id)!=0) return true;
        return false;
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    public List<dept> queryDept(){
        List<dept> deptList = this.DeptDAO.queryMultiRow("select * from dept");
        return deptList;
    }

    public boolean deleteDept(String dept_id,String dept_name){
        if(this.DeptDAO.execute("delete from dept where dept_id=? and dept_name=?",dept_id,dept_name)!=0) return true;
        return false;
    }

    public boolean addDept(String dept_id,String dept_name){
        if(this.DeptDAO.execute("insert into dept values(?,?)",dept_id,dept_name)!=0) return true;
        return false;
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    public List<grade> queryGrade(){
        List<grade> gradeList = this.GradeDAO.queryMultiRow("select * from grade");
        return gradeList;
    }

    public boolean deleteGrade(String grade_id){
        if(this.GradeDAO.execute("delete from grade where grade_id=?",grade_id)!=0) return true;
        return false;
    }

    public boolean addGrade(String grade_id){
        if(this.GradeDAO.execute("insert into grade values(?)",grade_id)!=0) return true;
        return false;
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    public List<major> queryMajor(){
        List<major> majorList = this.MajorDAO.queryMultiRow("select * from major");
        return majorList;
    }

    public boolean deleteMajor(String major_id,String major_name,String dept_id){
        if(this.MajorDAO.execute("delete from major where major_id=? and major_name=? and dept_id=?",major_id,major_name,dept_id)!=0) return true;
        return false;
    }

    public boolean addMajor(String major_id,String major_name,String dept_id){
        if(this.MajorDAO.execute("insert into major values(?,?,?)",major_id,major_name,dept_id)!=0) return true;
        return false;
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    public List<paper_bank> queryPaper_bank(){
        List<paper_bank> paper_bankList = this.Paper_bankDAO.queryMultiRow("select * from paper_bank");
        return paper_bankList;
    }

    public boolean deletePaper_bank(String paper_id,String directory){
        this.deleteFile(this.studentDownloadPaperPath+directory);
        if(this.Paper_bankDAO.execute("delete from paper_bank where paper_id=? and directory=?",paper_id,directory)!=0) return true;
        return false;
    }

    public boolean addPaper_bank1(String paper_id1,String directory1){
        if(this.Paper_bankDAO.execute("insert into paper_bank values(?,?)",paper_id1,directory1)!=0) return true;
        return false;
    }

    public boolean addPaper_bank2(String paper_id2,String directory2){
        String fileName = this.base64ToFile(directory2,this.studentDownloadPaperPath,paper_id2);
        if(fileName.startsWith("result:")) return false;
        if(this.Paper_bankDAO.execute("insert into paper_bank values(?,?)",paper_id2,fileName)!=0)return true;
        return false;
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    public List<paper_in_teach_class> queryPaper_in_teach_class(){
        List<paper_in_teach_class> paper_in_teach_classList = this.Paper_in_teach_classDAO.queryMultiRow("select * from paper_in_teach_class");
        return paper_in_teach_classList;
    }

    public boolean deletePaper_in_teach_class(String teach_class_id,String student_id,String paper_id,Double score,String directory,String time){
        this.deleteFile(this.studentUpPaperPath+directory);
        if(this.Paper_in_teach_classDAO.execute("delete from paper_in_teach_class where teach_class_id=? and student_id=? and paper_id=? and score=? and directory=? and time=?",teach_class_id,student_id,paper_id,score,directory,time)!=0) return true;
        return false;
    }

    public boolean addPaper_in_teach_class1(String teach_class_id1,String student_id1,String paper_id1,Double score1,String directory1,String time1){
        if(this.Paper_in_teach_classDAO.execute("insert into paper_in_teach_class values(?,?,?,?,?,?)",teach_class_id1,student_id1,paper_id1,score1,directory1,time1)!=0) return true;
        return false;
    }

    public boolean addPaper_in_teach_class2(String teach_class_id2,String student_id2,String paper_id2,Double score2,String directory2,String time2){
        String fileName = this.base64ToFile(directory2,this.studentUpPaperPath,teach_class_id2+"_"+student_id2+"_"+paper_id2);
        if(fileName.startsWith("result:")) return false;
        if(this.Paper_in_teach_classDAO.execute("insert into paper_in_teach_class values(?,?,?,?,?,?)",teach_class_id2,student_id2,paper_id2,score2,fileName,time2)!=0)return true;
        return false;
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    public List<student_course> queryStudent_course(){
        List<student_course> student_courseList = this.Student_courseDAO.queryMultiRow("select * from student_course");
        return student_courseList;
    }

    public boolean deleteStudent_course(String student_id,String course_id,Double score){
        if(this.Student_courseDAO.execute("delete from student_course where student_id=? and course_id=? and score=?",student_id,course_id,score)!=0) return true;
        return false;
    }

    public boolean addStudent_course(String student_id,String course_id,Double score){
        if(this.Student_courseDAO.execute("insert into student_course values(?,?,?)",student_id,course_id,score)!=0) return true;
        return false;
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    public List<student_have_taken_course> queryStudent_have_taken_course(){
        List<student_have_taken_course> student_have_taken_courseList = this.Student_have_taken_courseDAO.queryMultiRow("select * from student_have_taken_course");
        return student_have_taken_courseList;
    }

    public boolean deleteStudent_have_taken_course(String student_id,String course_id){
        if(this.Student_have_taken_courseDAO.execute("delete from student_have_taken_course where student_id=? and course_id=?",student_id,course_id)!=0) return true;
        return false;
    }

    public boolean addStudent_have_taken_course(String student_id,String course_id){
        if(this.Student_have_taken_courseDAO.execute("insert into student_have_taken_course values(?,?)",student_id,course_id)!=0) return true;
        return false;
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    public List<student_selected_course> queryStudent_selected_course(){
        List<student_selected_course> student_selected_courseList = this.Student_selected_courseDAO.queryMultiRow("select * from student_selected_course");
        return student_selected_courseList;
    }

    public boolean deleteStudent_selected_course(String student_id,String course_id){
        if(this.Student_selected_courseDAO.execute("delete from student_selected_course where student_id=? and course_id=?",student_id,course_id)!=0) return true;
        return false;
    }

    public boolean addStudent_selected_course(String student_id,String course_id){
        if(this.Student_selected_courseDAO.execute("insert into student_selected_course values(?,?)",student_id,course_id)!=0) return true;
        return false;
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    public List<student_teach_class> queryStudent_teach_class(){
        List<student_teach_class> student_teach_classList = this.Student_teach_classDAO.queryMultiRow("select * from student_teach_class");
        return student_teach_classList;
    }

    public boolean deleteStudent_teach_class(String student_id,String teach_class_id){
        if(this.Student_teach_classDAO.execute("delete from student_teach_class where student_id=? and teach_class_id=?",student_id,teach_class_id)!=0) return true;
        return false;
    }

    public boolean addStudent_teach_class(String student_id,String teach_class_id){
        if(this.Student_teach_classDAO.execute("insert into student_teach_class values(?,?)",student_id,teach_class_id)!=0) return true;
        return false;
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    public List<teach_class_time_room> queryTeach_class_time_room(){
        List<teach_class_time_room> teach_class_time_roomList = this.Teach_class_time_roomDAO.queryMultiRow("select * from teach_class_time_room");
        return teach_class_time_roomList;
    }

    public boolean deleteTeach_class_time_room(String teach_class_id,String time,String room){
        if(this.Teach_class_time_roomDAO.execute("delete from teach_class_time_room where teach_class_id=? and time=? and room=?",teach_class_id,time,room)!=0) return true;
        return false;
    }

    public boolean addTeach_class_time_room(String teach_class_id,String time,String room){
        if(this.Teach_class_time_roomDAO.execute("insert into teach_class_time_room values(?,?,?)",teach_class_id,time,room)!=0) return true;
        return false;
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    public List<teach_class> queryTeach_class(){
        List<teach_class> teach_classList = this.Teach_classDAO.queryMultiRow("select * from teach_class");
        return teach_classList;
    }

    public boolean deleteTeach_class(String teach_class_id,String course_id,String teacher_id){
        if(this.Teach_classDAO.execute("delete from teach_class where teach_class_id=? and course_id=? and teacher_id=?",teach_class_id,course_id,teacher_id)!=0) return true;
        return false;
    }

    public boolean addTeach_class(String teach_class_id,String course_id,String teacher_id){
        if(this.Teach_classDAO.execute("insert into teach_class values(?,?,?)",teach_class_id,course_id,teacher_id)!=0) return true;
        return false;
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    public static void init() {
        manageService ManageService = new manageService();
        ManageService.Class_realityDAO = new class_realityDAO();
        ManageService.Course_question_bankDAO = new course_question_bankDAO();
        ManageService.CourseDAO = new courseDAO();
        ManageService.DeptDAO = new deptDAO();
        ManageService.GradeDAO = new gradeDAO();
        ManageService.MajorDAO = new majorDAO();
        ManageService.Paper_bankDAO = new paper_bankDAO();
        ManageService.Paper_in_teach_classDAO = new paper_in_teach_classDAO();
        ManageService.Student_courseDAO = new student_courseDAO();
        ManageService.Student_have_taken_courseDAO = new student_have_taken_courseDAO();
        ManageService.Student_selected_courseDAO = new student_selected_courseDAO();
        ManageService.Student_teach_classDAO = new student_teach_classDAO();
        ManageService.StudentDAO = new studentDAO();
        ManageService.Teach_class_time_roomDAO = new teach_class_time_roomDAO();
        ManageService.Teach_classDAO = new teach_classDAO();
        ManageService.TeacherDAO = new teacherDAO();

        //初始化student表
        for (int i = 0; i < 25; i++) {
            String student_id = String.format("STU_ID%02d", i);
            String name = String.format("STU_NAME%02d", i);
            String sex = (i % 2 == 1) ?"男":"女";
            String phone = "13668458136";
            String email = "983427278@qq.com";
            String picture = "data:image/png;base64,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NNjPCKMIo6KP8aqk0hNJQAUUUUAFFFFABRRRQAUUUUAFFFFABShiKSigDV07WZbNwC25O6tyK6SHWLa6jwG2Mf4T3rh+KckjRnKmgDr5kyeOtIkLHH90Vz9tq8sWA3K+hrorHUbe6QAMFb0JoAt28J4xxV6NCDj1pscZx8uDkckVJGGz/KgC7bnZgVdSVe9UIicirUgGzIPagCc3I42/jThcDHWsWW42HGcHvSpdccHmgDXe4x3pEvW9ayvtJOBU0WXPtQBrx33NWPtYrLCADinfNQB5BThTaBTAkBp4qMU8GgB9IXApHNR80AKSTUbuE6/lSyvtHuelVjk8mkA5pC3sPQU2iigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAXPqKckrRtlSR9KZRQBs2PiK5tMDduUdj3rpLHxJaXWPMHkv+YNcFTlcocg0Aep+auwMrAg8gg5zVd78gkMfpXGaZrslqwSQloj1Hp9K2JJlmxJG2VIyCO9AGoZFkJb8qjLkH5TVW2mY8HpUhl2EbefT2oAuQkl8k/gavxuwI9Pas+23O3p61ojIQcYI70AXEkBH86l3L71Utjk+1XfLX1oA8cdGRyjDDKcEelAoopgPFO6UUUAN60cDJ7DmiigCq7Fjk0lFFIAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigApcUUUAJiiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACtXS7tlzCckHke1FFAG3byMSAO/WtKOHgNiiigC3b/ALrtU7yk44oooAmt368dKueY3pRRQB//2Q==";
            String class_id = String.format("CLA_ID%02d", (i % 2 == 1) ? 1 : 0);
            String password = "123.com";
            String hashcode = student_id;
            Integer forget_password_flag = 0;
            ManageService.addStudent2(student_id,name,sex,phone,email,picture,class_id,password,hashcode,forget_password_flag);
        }

        //初始化teacher表
        for (int i = 0; i < 25; i++) {
            String teacher_id = String.format("TEA_ID%02d", i);
            String name = String.format("TEA_NAME%02d", i);
            String sex = (i % 2 == 1) ?"男":"女";
            String phone = "13668458136";
            String email = "983427278@qq.com";
            String picture = "data:image/png;base64,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NNjPCKMIo6KP8aqk0hNJQAUUUUAFFFFABRRRQAUUUUAFFFFABShiKSigDV07WZbNwC25O6tyK6SHWLa6jwG2Mf4T3rh+KckjRnKmgDr5kyeOtIkLHH90Vz9tq8sWA3K+hrorHUbe6QAMFb0JoAt28J4xxV6NCDj1pscZx8uDkckVJGGz/KgC7bnZgVdSVe9UIicirUgGzIPagCc3I42/jThcDHWsWW42HGcHvSpdccHmgDXe4x3pEvW9ayvtJOBU0WXPtQBrx33NWPtYrLCADinfNQB5BThTaBTAkBp4qMU8GgB9IXApHNR80AKSTUbuE6/lSyvtHuelVjk8mkA5pC3sPQU2iigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAXPqKckrRtlSR9KZRQBs2PiK5tMDduUdj3rpLHxJaXWPMHkv+YNcFTlcocg0Aep+auwMrAg8gg5zVd78gkMfpXGaZrslqwSQloj1Hp9K2JJlmxJG2VIyCO9AGoZFkJb8qjLkH5TVW2mY8HpUhl2EbefT2oAuQkl8k/gavxuwI9Pas+23O3p61ojIQcYI70AXEkBH86l3L71Utjk+1XfLX1oA8cdGRyjDDKcEelAoopgPFO6UUUAN60cDJ7DmiigCq7Fjk0lFFIAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigApcUUUAJiiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACtXS7tlzCckHke1FFAG3byMSAO/WtKOHgNiiigC3b/ALrtU7yk44oooAmt368dKueY3pRRQB//2Q==";
            String dept_id = String.format("DEP_ID%02d", (i % 2 == 1) ? 1 : 0);
            String password = "123.com";
            String hashcode = teacher_id;
            Integer forget_password_flag = 0;
            ManageService.addTeacher2(teacher_id,name,sex,phone,email,picture,dept_id,password,hashcode,forget_password_flag);
        }

        //初始化class表
        for (int i = 0; i < 5; i++) {
            String class_id = String.format("CLA_ID%02d", i);
            String grade_id = String.format("GRA_ID%02d", i);
            String major_id = String.format("MAJ_ID%02d", i);
            ManageService.addClass(class_id,grade_id,major_id);
        }

//        //初始化course表
//        for (int i = 0; i < 5; i++) {
//            String course_id = String.format("COU_ID%02d", i);
//            String name = String.format("COU_name%02d", i);
//            Integer credit = 4;
//            Integer period = 32;
//            ManageService.addCourse(course_id,name,credit,period);
//        }

        //初始化course表
        String course_names[] = {"高等数学","线性代数","概率与数理统计","大学物理","离散数学","计算机科学导论","大学英语","数据库系统原理","C语言程序设计","计算机组成原理","数据结构","操作系统","计算机网络","Python程序设计","JAVA程序设计","C/C++程序设计","大学体育"};
        for (int i = 0; i < course_names.length; i++) {
            String course_id = String.format("COU_ID%02d", i);
            String name = course_names[i];
            Integer credit = 4;
            Integer period = 32;
            ManageService.addCourse(course_id,name,credit,period);
        }

        //初始化course_question_bank表
        for (int i = 0; i < 5; i++) {
            String course_id = String.format("COU_ID%02d", i);
            String paper_id = String.format("PAP_ID%02d", i);
            ManageService.addCourse_question_bank(course_id,paper_id);
        }

        //初始化dept表
        String dept_names[] = {"计算机学院","通信学院","理学院","传媒学院","经济管理学院","体育学院"};
        for (int i = 0; i < 5; i++) {
            String dept_id = String.format("DEP_ID%02d", i);
            String dept_name = dept_names[i];
            ManageService.addDept(dept_id,dept_name);
        }

        //初始化grade表
        for (int i = 0; i < 5; i++) {
            String grade_id = String.format("GRA_ID%02d", i);
            ManageService.addGrade(grade_id);
        }

        //初始化major表
        for (int i = 0; i < 5; i++) {
            String major_id = String.format("MAJ_ID%02d", i);
            String major_name = String.format("MAJ_name%02d", i);
            String dept_id = String.format("DEP_ID%02d", i);
            ManageService.addMajor(major_id,major_name,dept_id);
        }

        //初始化paper_bank表
        for (int i = 0; i < 5; i++) {
            String paper_id = String.format("PAP_ID%02d", i);
            String directory = "data:application/msword;base64,0M8R4KGxGuEAAAAAAAAAAAAAAAAAAAAAPgADAP7/CQAGAAAAAAAAAAAAAAABAAAAAQAAAAAAAAAAEAAAAgAAAAEAAAD+////AAAAAAAAAAD////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////9////BgAAAP7///8EAAAABQAAAP7////+////CAAAAAkAAAAKAAAACwAAAAwAAAANAAAADgAAAA8AAAAQAAAAEQAAABIAAAATAAAAFAAAABUAAAAWAAAAFwAAABgAAAAZAAAAGgAAABsAAAAcAAAAHQAAAB4AAAAfAAAAIAAAACEAAAAiAAAAIwAAACQAAAAlAAAAJgAAACcAAAAoAAAAKQAAACoAAAArAAAALAAAAC0AAAAuAAAALwAAADAAAAAxAAAAMgAAADMAAAA0AAAANQAAADYAAAA/AAAAOAAAADkAAAA6AAAAOwAAADwAAAA9AAAAPgAAAEEAAABAAAAA/v///0IAAABDAAAA/v///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////1IAbwBvAHQAIABFAG4AdAByAHkAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAWAAUA//////////8BAAAABgkCAAAAAADAAAAAAAAARgAAAAAAAAAAAAAAACCe/2UVFNkBAwAAAIAEAAAAAAAABQBTAHUAbQBtAGEAcgB5AEkAbgBmAG8AcgBtAGEAdABpAG8AbgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACgAAgEEAAAAAgAAAP////8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQAIAAAAAAAAFAEQAbwBjAHUAbQBlAG4AdABTAHUAbQBtAGEAcgB5AEkAbgBmAG8AcgBtAGEAdABpAG8AbgAAAAAAAAAAAAAAOAACAf///////////////wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAkAAADUAQAAAAAAAFcAbwByAGQARABvAGMAdQBtAGUAbgB0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaAAIB/////wYAAAD/////AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABwAAADNiAAAAAAAAAQAAAAIAAAADAAAABAAAAAUAAAAGAAAABwAAAAgAAAD+////CgAAAAsAAAAMAAAADQAAAA4AAAAPAAAAEAAAAP7////+///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////+/wAACgACAAAAAAAAAAAAAAAAAAAAAAABAAAA4IWf8vlPaBCrkQgAKyez2TAAAAAQAgAAEgAAAAEAAACgAAAAAAAAgKgAAAACAAAAsAAAAAMAAADQAAAABAAAANwAAAAFAAAA8AAAAAYAAAD8AAAABwAAAAgBAAAIAAAAKAEAAAkAAAA8AQAACgAAAEgBAAAMAAAAVAEAAA0AAABgAQAADgAAAGwBAAAPAAAAdAEAABAAAAB8AQAAEgAAAIQBAAATAAAACAIAAAAAAAAAAAAAAgAAALAEAAATAAAABAgAAB8AAAAMAAAA1Yt3UxZ/91Ma/zAAMAAwADEAMgAzAAAAHwAAAAEAAAAAAAAAHwAAAAUAAABqdQSDsYLtVgAAAAAfAAAAAQAAAAAAAAAfAAAAAQAAAAAAAAAfAAAADAAAAE4AbwByAG0AYQBsAC4AZABvAHQAbQAAAB8AAAAGAAAAcgB1AG4AbAB1AAAAHwAAAAIAAAAyAAAAQAAAAACAIeiLTk8BQAAAAACOvbre+NQBQAAAAAAi3WUVFNkBAwAAAAYAAAADAAAAaAIAAAMAAADCAgAAHwAAAD0AAABXAFAAUwAgAE8AZgBmAGkAYwBlAF8AMQAxAC4AMQAuADAALgAxADAAMQAzADIAXwBGADEARQAzADIANwBCAEMALQAyADYAOQBDAC0ANAAzADUAZAAtAEEAMQA1ADIALQAwADUAQwA1ADQAMAA4ADAAMAAyAEMAQQAAAAAAAwAAAAAAAAD+/wAACgACAAAAAAAAAAAAAAAAAAAAAAACAAAAAtXN1ZwuGxCTlwgAKyz5rkQAAAAF1c3VnC4bEJOXCAArLPmu2AAAAJQAAAAIAAAA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AABoAgAAfQ0AAFIAAACSDAAAFQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAARxMAAKsAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAUFAAAmgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACOAAAAfgQAAGgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAOYEAAAQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAWxAAAKACAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAgAMAQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADQBrWHp6mJgI/yxnJ1mYmHFRMQA1AA9cmJgM/89rD1yYmDIABlIM/3FRMwAwAAZSCf8NAA0AKFdEAEQAQQCXe9VsLU4M/76L9Ha/frllC3ovZnkAPQBtAHgAKwBiAAz/glmcZ3gAnligUjEADP8ZUnkAnligUiAAIAAgACAAIAAgACAAIAACMA0A3YRygoR2ZYhygi9mIAAgACAAIAAgACAAIAAgAAIwDQCVYnFf2FNiY+9TBlI6TiAAIAAgACAAIAAgACAAIACVYnFfjFScZaROlWJxXwIwDQBzXmKX9Ha/foR2wlNwZWJfD18vZiAAIAAgACAAIAAgACAAIAAgACAAAjANAChXQwBvAGgAZQBuAC0AUwB1AHQAaABlAHIAbABhAG4AZADBiGpSl3vVbC1ODP9/iVdTOlPfV4R2Fn8BeC9mIAAgACAAIAAgACAAIAAgACAAIAACMA0AKFdDAE0AWQCcmHKCIWqLVy1ODP9Sl3KCjFTEnnKC+HagUh91EGKEdpyYcoIvZiAAIAAgACAAIAAgACAAIAACMA0AV1uTXu9TBlI6TrlwNZaLV4xUIAAgACAAIAAgACAAIAAgACAAi1cCMA0AV1sme8GIalLvUwZSOk4JTs15vnymXgz/BlIrUi9mMk6+fKZeDP9XWyZ7vnymXoxUIAAgACAAIAAgACAAIAAgAL58pl4CMA0AiG2Qlpd71WzvU+VOBlI6TmlyU096evSVl3vVbIxUIAAgACAAIAAgACAAIAB6evSVl3vVbAIwDQC+iwZXhHa5ZQt6L2ZGACgAeAAsAHkAKQA9ADAADP8ZUrlwKABhACwAYgApAD2EKFcGV4VRhHZhZ/ZOL2YgACAAIAAgACAAIAAgACAAIAAgACAAAjANAL6LQgBlAHoAaQBlAHIA8ma/fjF1UAAwACwAUAAxACwAUAAyACwAUAAzACwAUAA0ALNRmlsM/xlS8ma/fihXd425cARZhHYHUhFUz5FJe45OIAAgACAAIAAgACAAIAAgACAAAjANADpT31drWEVRl3vVbIR2ZWukmi9mGv9CbKROATAgACAAIAAgACAAIAAgACAAATBNkflbjFRrWHKCAjANAKGLl3s6Z/5WYl9mW0tONnIvZiAAIAAgACAAIAAgACAAIAAgACAAAjANAHBTN1IaTjheKHWEdpyYcoIhaotXL2YgACAAIAAgACAAIAAgACAAIAAgAAIwDQAJTjaWQgBlAHoAaQBlAHIA8ma/foR2bFEPXy9mIAAgACAAIAAgACAAIAAgACAAIAAgACAAIAAgACAAIAAgAAIwDQANAIxOATANVM2L44nKkQj/LGcnWZiYcVE0AA9cmJgM/89rD1yYmDUABlIM/3FRMgAwAAZSCf8NAA0ALU65cDt1v37VbA0ADQANAA0ADQANAA0ADQANACAADQBaAC0AYgB1AGYAZgBlAHIAIACIbZCWl3vVbA0ADQANAA0ADQANAA0ADQANAA0ADQBCAGUAegBpAGUAcgDyZr9+DQANAA0ADQANAA0ADQANAA0ADQANAA0ADQBQnyFrUFcHaA0ADQANAA0ADQANAA0ADQANAA0ADQANAAlOATCAe1R7mJgI/yxnJ1mYmHFRNgAPXJiYDP/Paw9cmJg2AAZSDP9xUTMANgAGUgn/DQANAMBOSE4vZkBc6JBQVwdo+3wBMBZOTHVQVwdo+3yMVMKJ31tQVwdo+3wf/4Nb7E4EVOqBhHZcTyh1L2bATkhOH/8NAA0ADQANAA0ADQANAA0ADQANAA0ADQANAA0ADQANAA0A44nKkVIARwBCAJyYcoIhaotXhHYrVElODP92XvSLDmbliyFqi1eEdhhPOn+5cAIwDQANAA0ADQANAA0ADQANAA0ADQANAA0ADQANAA0ADQDBiw5mGv8yAPR+enr0lS1OAE4qTtV+n1O5cIR2y2Vsj9hTYmOMVABOKk5HVwBTKX8+ZdhTYmMvZu9TpE5iY4R2AjANAA0ADQANAA0ADQANAA0ADQANAA0ADQANAA0ADQANAA0AgHvwjxpZuY9iXzpT31drWEVRhHbbVipOO06BiWVrpJoCMA0ADQANAA0ADQANAA0ADQANAA0ADQANAA0ADQANAA0ADQANADUADv/ATkhOL2aeWM+Rl3vVbB//+04PYT5O+lEkTipOf08odZ5Yz5GXe9VshHaLT1BbAjANAA0ADQANAA0ADQANAA0ADQANAA0ADQANAA0ADQANADYALgBTX01SoYuXezpn/lZiX2ZbKFfRU1Vcx48Lei1OR5AwUoR2EWMYYgln6lSbTigA+04PYT5O+lEJTnmYgHtUeykAH/8NAA0ADQANAA0ADQANAA0ADQANAA0ADQANAA0ADQANAA0ADQANANtWDv/ulVR7mJgI/3FRMgAPXJiYDP/Paw9cmJg3AAZSDP9xUTEANAAGUgn/DQANAMBOSE4vZsJTcGXej+1+J2Af/8BOSE4vZuBRVU/ej+1+J2Af/4Nb7E6EdjpTK1KMVFSA+3wvZsBOSE4f/w0ADQANAA0ADQANAA0ADQANAA0ADQANAA0AmVH6UVAAaABvAG4AZwBclWKXzVMEXCFqi1eEdmxR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";
            ManageService.addPaper_bank2(paper_id,directory);
        }

//        //初始化paper_in_teach_class表
//        for (int i = 0; i < 5; i++) {
//            //无需初始化
//        }

        //初始化student_course表
        for (int i = 0; i < 5; i++) {
            String student_id = String.format("STU_ID%02d", i);
            String course_id = String.format("COU_ID%02d", i);
            Double score = 0.0;
            ManageService.addStudent_course(student_id,course_id,score);
        }

//        //初始化student_course表
//        for (int i = 0; i < 5; i++) {
//            String student_id = String.format("STU_ID%02d", i);
//            String course_id = String.format("COU_ID%02d", i);
//            Double score = 0.0;
//            ManageService.addStudent_course(student_id,course_id,score);
//        }
        //初始化student_course表
        //只初始化关联一个人的
        for (int i = 0; i < 5; i++) {
            if(i == 0) continue;
            String student_id = "STU_ID00";
            String course_id = String.format("COU_ID%02d", i);
            Double score = 0.0;
            ManageService.addStudent_course(student_id,course_id,score);
        }

        //初始化student_have_taken_course表
        //只初始化关联一个人的
        for (int i = course_names.length - 1; i > 15; i--) {
            String student_id = "STU_ID00";
            String course_id = String.format("COU_ID%02d", i);
            ManageService.addStudent_have_taken_course(student_id,course_id);
        }

        //初始化student_teach_class表
        //初始化多个教学班,同一群学生的
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String student_id = String.format("STU_ID%02d", i);
                String teach_class_id = String.format("TC_ID%02d", j);
                ManageService.addStudent_teach_class(student_id,teach_class_id);
            }
        }

        //初始化teach_class表
        //初始化多个教学班
        for (int i = 0; i < 5; i++) {
            String teach_class_id = String.format("TC_ID%02d", i);
            String course_id = String.format("COU_ID%02d", i);
            String teacher_id = String.format("TEA_ID%02d", i);
            ManageService.addTeach_class(teach_class_id,course_id,teacher_id);
        }

        //初始化teach_class_time_room表
        //初始化多个班
        String times[] = {"{\"oddWeek\":[\"周一1112\"],\"evenWeek\":[\"周一1112\"]}","{\"oddWeek\":[\"周五56\"],\"evenWeek\":[]}","{\"oddWeek\":[\"周一910\"],\"evenWeek\":[\"周一910\"]}","{\"oddWeek\":[\"周一34\"],\"evenWeek\":[\"周三34\",\"周五34\"]}","{\"oddWeek\":[\"周一910\",\"周一34\",\"周一78\"],\"evenWeek\":[\"周一910\"]}","{\"oddWeek\":[\"周一56\"],\"evenWeek\":[\"周二56\"]}"};
        String romms[] = {"2115","C405/C406","3204","3205","3505","2310"};
        for (int i = 0; i < 5; i++) {
            String teach_class_id = String.format("TC_ID%02d", i);
            String time = times[i];
            String room = romms[i];
            ManageService.addTeach_class_time_room(teach_class_id,time,room);
        }
    }

    public static void main(String[] args) {
        manageService.init();
    }

//    public static void main(String[] args) {
//        manageService ManageService = new manageService();
//        ManageService.Class_realityDAO = new class_realityDAO();
//        ManageService.Course_question_bankDAO = new course_question_bankDAO();
//        ManageService.CourseDAO = new courseDAO();
//        ManageService.DeptDAO = new deptDAO();
//        ManageService.GradeDAO = new gradeDAO();
//        ManageService.MajorDAO = new majorDAO();
//        ManageService.Paper_bankDAO = new paper_bankDAO();
//        ManageService.Paper_in_teach_classDAO = new paper_in_teach_classDAO();
//        ManageService.Student_courseDAO = new student_courseDAO();
//        ManageService.Student_have_taken_courseDAO = new student_have_taken_courseDAO();
//        ManageService.Student_selected_courseDAO = new student_selected_courseDAO();
//        ManageService.Student_teach_classDAO = new student_teach_classDAO();
//        ManageService.StudentDAO = new studentDAO();
//        ManageService.Teach_class_time_roomDAO = new teach_class_time_roomDAO();
//        ManageService.Teach_classDAO = new teach_classDAO();
//        ManageService.TeacherDAO = new teacherDAO();
//
////        ManageService.addStudent1("STUID01","向恒杰","男","13668458136","983427278@qq.com","2020211853.png","04912001","3eda7aed8bd21c54be87d2ff4980576a","ac7746e09ae18b5cb450a54b8b8e77b3",0);
////        ManageService.addTeacher1("TEAID01","向恒杰","男","13668458136","983427278@qq.com","2020211853.png","04912001","3eda7aed8bd21c54be87d2ff4980576a","ac7746e09ae18b5cb450a54b8b8e77b3",0);
////        ManageService.addClass("CLAID01","GIRAD01","MAJID01");
////        ManageService.addCourse("COUID01","机器学习",4,4);
////        ManageService.addCourse_question_bank("COUID01","PAPID01");
////        ManageService.addDept("DEPID01","计算机学院");
////        ManageService.addGrade("GRAID01");
////        ManageService.addMajor("MAJID01","计算机科学与技术","DEPID01");
////        ManageService.addPaper_bank1("PAPID01","PAPID01.docx");
////        ManageService.deletePaper_in_teach_class("TCID01","STUID01","PAPID01",100.0,"PAPID01_STUID01.docx","123");
////        ManageService.addStudent_course("STUID01","COUID01",100.0);
////        ManageService.addStudent_have_taken_course("STUID01","COUID01");
////        ManageService.addStudent_selected_course("STUID01","COUID01");
////        ManageService.addStudent_teach_class("STUID01","TC01");
////        ManageService.addTeach_class_time_room("TC01","{\"oddWeek\":[\"周一1112\"],\"evenWeek\":[\"周一1112\"]}","2115");
//    }
}
