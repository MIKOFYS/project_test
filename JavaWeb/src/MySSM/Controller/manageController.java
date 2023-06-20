package MySSM.Controller;

import MySSM.DATA.*;
import MySSM.Service.manageService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class manageController {
    private manageService ManageService=null;

    private String queryStudent(String pageNumber){
        List<student> studentList=this.ManageService.queryStudent();

        int start=(Integer.parseInt(pageNumber)-1)*20;
        if(start<0||start>studentList.size()){
            start=0;
        }

        int end=start+20;
        if(end>studentList.size()){
            end=studentList.size();
        }

        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = start; i < end; i++) {
            if(flag){
                flag=false;
                listJSONString+=studentList.get(i).toJSONString();
            }else{
                listJSONString+=","+studentList.get(i).toJSONString();
            }
        }
        listJSONString+="]";

        return listJSONString;
    }

    private String deleteStudent(String student_id,String name,String sex,String phone,String email,String picture,String class_id,String password,String hashcode,String forget_password_flag){
        if(this.ManageService.deleteStudent(student_id,name,sex,phone,email,picture,class_id,password,hashcode,Integer.parseInt(forget_password_flag))){
            return "result:deleteStudent success";
        }
        return "result:deleteStudent fail";
    }

    private String addStudent1(String student_id1,String name1,String sex1,String phone1,String email1,String picture1,String class_id1,String password1,String hashcode1,String forget_password_flag1){
        if(this.ManageService.addStudent1(student_id1,name1,sex1,phone1,email1,picture1,class_id1,password1,hashcode1,Integer.parseInt(forget_password_flag1))){
            return "result:addStudent success";
        }
        return "result:addStudent fail";
    }

    private String addStudent2(String student_id2,String name2,String sex2,String phone2,String email2,String picture2,String class_id2,String password2,String hashcode2,String forget_password_flag2){
        if(this.ManageService.addStudent2(student_id2,name2,sex2,phone2,email2,picture2,class_id2,password2,hashcode2,Integer.parseInt(forget_password_flag2))){
            return "result:addStudent success";
        }
        return "result:addStudent fail";
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    private String queryTeacher(String pageNumber){
        List<teacher> teacherList=this.ManageService.queryTeacher();

        int start=(Integer.parseInt(pageNumber)-1)*20;
        if(start<0||start>teacherList.size()){
            start=0;
        }

        int end=start+20;
        if(end>teacherList.size()){
            end=teacherList.size();
        }

        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = start; i < end; i++) {
            if(flag){
                flag=false;
                listJSONString+=teacherList.get(i).toJSONString();
            }else{
                listJSONString+=","+teacherList.get(i).toJSONString();
            }
        }
        listJSONString+="]";

        return listJSONString;
    }

    private String deleteTeacher(String teacher_id,String name,String sex,String phone,String email,String picture,String dept_id,String password,String hashcode,String forget_password_flag){
        if(this.ManageService.deleteTeacher(teacher_id,name,sex,phone,email,picture,dept_id,password,hashcode,Integer.parseInt(forget_password_flag))){
            return "result:deleteTeacher success";
        }
        return "result:deleteTeacher fail";
    }

    private String addTeacher1(String teacher_id1,String name1,String sex1,String phone1,String email1,String picture1,String dept_id1,String password1,String hashcode1,String forget_password_flag1){
        if(this.ManageService.addTeacher1(teacher_id1,name1,sex1,phone1,email1,picture1,dept_id1,password1,hashcode1,Integer.parseInt(forget_password_flag1))){
            return "result:addTeacher success";
        }
        return "result:addTeacher fail";
    }

    private String addTeacher2(String teacher_id2,String name2,String sex2,String phone2,String email2,String picture2,String dept_id2,String password2,String hashcode2,String forget_password_flag2){
        if(this.ManageService.addTeacher2(teacher_id2,name2,sex2,phone2,email2,picture2,dept_id2,password2,hashcode2,Integer.parseInt(forget_password_flag2))){
            return "result:addTeacher success";
        }
        return "result:addTeacher fail";
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    private String queryClass(String pageNumber){
        List<class_reality> classList=this.ManageService.queryClass();

        int start=(Integer.parseInt(pageNumber)-1)*20;
        if(start<0||start>classList.size()){
            start=0;
        }

        int end=start+20;
        if(end>classList.size()){
            end=classList.size();
        }

        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = start; i < end; i++) {
            if(flag){
                flag=false;
                listJSONString+=classList.get(i).toJSONString();
            }else{
                listJSONString+=","+classList.get(i).toJSONString();
            }
        }
        listJSONString+="]";

        return listJSONString;
    }

    private String deleteClass(String class_id,String grade_id,String major_id){
        if(this.ManageService.deleteClass(class_id,grade_id,major_id)){
            return "result:deleteClass success";
        }
        return "result:deleteClass fail";
    }

    private String addClass(String class_id,String grade_id,String major_id){
        if(this.ManageService.addClass(class_id,grade_id,major_id)){
            return "result:addClass success";
        }
        return "result:addClass fail";
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    private String queryCourse(String pageNumber){
        List<course> courseList=this.ManageService.queryCourse();

        int start=(Integer.parseInt(pageNumber)-1)*20;
        if(start<0||start>courseList.size()){
            start=0;
        }

        int end=start+20;
        if(end>courseList.size()){
            end=courseList.size();
        }

        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = start; i < end; i++) {
            if(flag){
                flag=false;
                listJSONString+=courseList.get(i).toJSONString();
            }else{
                listJSONString+=","+courseList.get(i).toJSONString();
            }
        }
        listJSONString+="]";

        return listJSONString;
    }

    private String deleteCourse(String course_id,String name,String credit,String period){
        if(this.ManageService.deleteCourse(course_id,name,Integer.parseInt(credit),Integer.parseInt(period))){
            return "result:deleteCourse success";
        }
        return "result:deleteCourse fail";
    }

    private String addCourse(String course_id,String name,String credit,String period){
        if(this.ManageService.addCourse(course_id,name,Integer.parseInt(credit),Integer.parseInt(period))){
            return "result:addCourse success";
        }
        return "result:addCourse fail";
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    private String queryCourse_question_bank(String pageNumber){
        List<course_question_bank> course_question_bankList=this.ManageService.queryCourse_question_bank();

        int start=(Integer.parseInt(pageNumber)-1)*20;
        if(start<0||start>course_question_bankList.size()){
            start=0;
        }

        int end=start+20;
        if(end>course_question_bankList.size()){
            end=course_question_bankList.size();
        }

        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = start; i < end; i++) {
            if(flag){
                flag=false;
                listJSONString+=course_question_bankList.get(i).toJSONString();
            }else{
                listJSONString+=","+course_question_bankList.get(i).toJSONString();
            }
        }
        listJSONString+="]";

        return listJSONString;
    }

    private String deleteCourse_question_bank(String course_id,String paper_id){
        if(this.ManageService.deleteCourse_question_bank(course_id,paper_id)){
            return "result:deleteCourse_question_bank success";
        }
        return "result:deleteCourse_question_bank fail";
    }

    private String addCourse_question_bank(String course_id,String paper_id){
        if(this.ManageService.addCourse_question_bank(course_id,paper_id)){
            return "result:addCourse_question_bank success";
        }
        return "result:addCourse_question_bank fail";
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    private String queryDept(String pageNumber){
        List<dept> deptList=this.ManageService.queryDept();

        int start=(Integer.parseInt(pageNumber)-1)*20;
        if(start<0||start>deptList.size()){
            start=0;
        }

        int end=start+20;
        if(end>deptList.size()){
            end=deptList.size();
        }

        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = start; i < end; i++) {
            if(flag){
                flag=false;
                listJSONString+=deptList.get(i).toJSONString();
            }else{
                listJSONString+=","+deptList.get(i).toJSONString();
            }
        }
        listJSONString+="]";

        return listJSONString;
    }

    private String deleteDept(String dept_id,String dept_name){
        if(this.ManageService.deleteDept(dept_id,dept_name)){
            return "result:deleteDept success";
        }
        return "result:deleteDept fail";
    }

    private String addDept(String dept_id,String dept_name){
        if(this.ManageService.addDept(dept_id,dept_name)){
            return "result:addDept success";
        }
        return "result:addDept fail";
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    private String queryGrade(String pageNumber){
        List<grade> gradeList=this.ManageService.queryGrade();

        int start=(Integer.parseInt(pageNumber)-1)*20;
        if(start<0||start>gradeList.size()){
            start=0;
        }

        int end=start+20;
        if(end>gradeList.size()){
            end=gradeList.size();
        }

        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = start; i < end; i++) {
            if(flag){
                flag=false;
                listJSONString+=gradeList.get(i).toJSONString();
            }else{
                listJSONString+=","+gradeList.get(i).toJSONString();
            }
        }
        listJSONString+="]";

        return listJSONString;
    }

    private String deleteGrade(String grade_id){
        if(this.ManageService.deleteGrade(grade_id)){
            return "result:deleteGrade success";
        }
        return "result:deleteGrade fail";
    }

    private String addGrade(String grade_id){
        if(this.ManageService.addGrade(grade_id)){
            return "result:addGrade success";
        }
        return "result:addGrade fail";
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    private String queryMajor(String pageNumber){
        List<major> majorList=this.ManageService.queryMajor();

        int start=(Integer.parseInt(pageNumber)-1)*20;
        if(start<0||start>majorList.size()){
            start=0;
        }

        int end=start+20;
        if(end>majorList.size()){
            end=majorList.size();
        }

        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = start; i < end; i++) {
            if(flag){
                flag=false;
                listJSONString+=majorList.get(i).toJSONString();
            }else{
                listJSONString+=","+majorList.get(i).toJSONString();
            }
        }
        listJSONString+="]";

        return listJSONString;
    }

    private String deleteMajor(String major_id,String major_name,String dept_id){
        if(this.ManageService.deleteMajor(major_id,major_name,dept_id)){
            return "result:deleteMajor success";
        }
        return "result:deleteMajor fail";
    }

    private String addMajor(String major_id,String major_name,String dept_id){
        if(this.ManageService.addMajor(major_id,major_name,dept_id)){
            return "result:addMajor success";
        }
        return "result:addMajor fail";
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    private String queryPaper_bank(String pageNumber){
        List<paper_bank> paper_bankList=this.ManageService.queryPaper_bank();

        int start=(Integer.parseInt(pageNumber)-1)*20;
        if(start<0||start>paper_bankList.size()){
            start=0;
        }

        int end=start+20;
        if(end>paper_bankList.size()){
            end=paper_bankList.size();
        }

        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = start; i < end; i++) {
            if(flag){
                flag=false;
                listJSONString+=paper_bankList.get(i).toJSONString();
            }else{
                listJSONString+=","+paper_bankList.get(i).toJSONString();
            }
        }
        listJSONString+="]";

        return listJSONString;
    }

    private String deletePaper_bank(String paper_id,String directory){
        if(this.ManageService.deletePaper_bank(paper_id,directory)){
            return "result:deletePaper_bank success";
        }
        return "result:deletePaper_bank fail";
    }

    private String addPaper_bank1(String paper_id1,String directory1){
        if(this.ManageService.addPaper_bank1(paper_id1,directory1)){
            return "result:addPaper_bank success";
        }
        return "result:addPaper_bank fail";
    }

    private String addPaper_bank2(String paper_id2,String directory2){
        if(this.ManageService.addPaper_bank2(paper_id2,directory2)){
            return "result:addPaper_bank success";
        }
        return "result:addPaper_bank fail";
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    private String queryPaper_in_teach_class(String pageNumber){
        List<paper_in_teach_class> paper_in_teach_classList=this.ManageService.queryPaper_in_teach_class();

        int start=(Integer.parseInt(pageNumber)-1)*20;
        if(start<0||start>paper_in_teach_classList.size()){
            start=0;
        }

        int end=start+20;
        if(end>paper_in_teach_classList.size()){
            end=paper_in_teach_classList.size();
        }

        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = start; i < end; i++) {
            if(flag){
                flag=false;
                listJSONString+=paper_in_teach_classList.get(i).toJSONString();
            }else{
                listJSONString+=","+paper_in_teach_classList.get(i).toJSONString();
            }
        }
        listJSONString+="]";

        return listJSONString;
    }

    private String deletePaper_in_teach_class(String teach_class_id,String student_id,String paper_id,String score,String directory,String time){
        if(this.ManageService.deletePaper_in_teach_class(teach_class_id,student_id,paper_id,Double.parseDouble(score),directory,time)){
            return "result:deletePaper_in_teach_class success";
        }
        return "result:deletePaper_in_teach_class fail";
    }

    private String addPaper_in_teach_class1(String teach_class_id1,String student_id1,String paper_id1,String score1,String directory1,String time1){
        if(this.ManageService.addPaper_in_teach_class1(teach_class_id1,student_id1,paper_id1,Double.parseDouble(score1),directory1,time1)){
            return "result:addPaper_in_teach_class success";
        }
        return "result:addPaper_in_teach_class fail";
    }

    private String addPaper_in_teach_class2(String teach_class_id2,String student_id2,String paper_id2,String score2,String directory2,String time2){
        if(this.ManageService.addPaper_in_teach_class2(teach_class_id2,student_id2,paper_id2,Double.parseDouble(score2),directory2,time2)){
            return "result:addPaper_in_teach_class success";
        }
        return "result:addPaper_in_teach_class fail";
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    private String queryStudent_course(String pageNumber){
        List<student_course> student_courseList=this.ManageService.queryStudent_course();

        int start=(Integer.parseInt(pageNumber)-1)*20;
        if(start<0||start>student_courseList.size()){
            start=0;
        }

        int end=start+20;
        if(end>student_courseList.size()){
            end=student_courseList.size();
        }

        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = start; i < end; i++) {
            if(flag){
                flag=false;
                listJSONString+=student_courseList.get(i).toJSONString();
            }else{
                listJSONString+=","+student_courseList.get(i).toJSONString();
            }
        }
        listJSONString+="]";

        return listJSONString;
    }

    private String deleteStudent_course(String student_id,String course_id,String score){
        if(this.ManageService.deleteStudent_course(student_id,course_id,Double.parseDouble(score))){
            return "result:deleteStudent_course success";
        }
        return "result:deleteStudent_course fail";
    }

    private String addStudent_course(String student_id,String course_id,String score){
        if(this.ManageService.addStudent_course(student_id,course_id,Double.parseDouble(score))){
            return "result:addStudent_course success";
        }
        return "result:addStudent_course fail";
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    private String queryStudent_have_taken_course(String pageNumber){
        List<student_have_taken_course> student_have_taken_courseList=this.ManageService.queryStudent_have_taken_course();

        int start=(Integer.parseInt(pageNumber)-1)*20;
        if(start<0||start>student_have_taken_courseList.size()){
            start=0;
        }

        int end=start+20;
        if(end>student_have_taken_courseList.size()){
            end=student_have_taken_courseList.size();
        }

        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = start; i < end; i++) {
            if(flag){
                flag=false;
                listJSONString+=student_have_taken_courseList.get(i).toJSONString();
            }else{
                listJSONString+=","+student_have_taken_courseList.get(i).toJSONString();
            }
        }
        listJSONString+="]";

        return listJSONString;
    }

    private String deleteStudent_have_taken_course(String student_id,String course_id){
        if(this.ManageService.deleteStudent_have_taken_course(student_id,course_id)){
            return "result:deleteStudent_have_taken_course success";
        }
        return "result:deleteStudent_have_taken_course fail";
    }

    private String addStudent_have_taken_course(String student_id,String course_id){
        if(this.ManageService.addStudent_have_taken_course(student_id,course_id)){
            return "result:addStudent_have_taken_course success";
        }
        return "result:addStudent_have_taken_course fail";
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    private String queryStudent_selected_course(String pageNumber){
        List<student_selected_course> student_selected_courseList=this.ManageService.queryStudent_selected_course();

        int start=(Integer.parseInt(pageNumber)-1)*20;
        if(start<0||start>student_selected_courseList.size()){
            start=0;
        }

        int end=start+20;
        if(end>student_selected_courseList.size()){
            end=student_selected_courseList.size();
        }

        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = start; i < end; i++) {
            if(flag){
                flag=false;
                listJSONString+=student_selected_courseList.get(i).toJSONString();
            }else{
                listJSONString+=","+student_selected_courseList.get(i).toJSONString();
            }
        }
        listJSONString+="]";

        return listJSONString;
    }

    private String deleteStudent_selected_course(String student_id,String course_id){
        if(this.ManageService.deleteStudent_selected_course(student_id,course_id)){
            return "result:deleteStudent_selected_course success";
        }
        return "result:deleteStudent_selected_course fail";
    }

    private String addStudent_selected_course(String student_id,String course_id){
        if(this.ManageService.addStudent_selected_course(student_id,course_id)){
            return "result:addStudent_selected_course success";
        }
        return "result:addStudent_selected_course fail";
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    private String queryStudent_teach_class(String pageNumber){
        List<student_teach_class> student_teach_classList=this.ManageService.queryStudent_teach_class();

        int start=(Integer.parseInt(pageNumber)-1)*20;
        if(start<0||start>student_teach_classList.size()){
            start=0;
        }

        int end=start+20;
        if(end>student_teach_classList.size()){
            end=student_teach_classList.size();
        }

        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = start; i < end; i++) {
            if(flag){
                flag=false;
                listJSONString+=student_teach_classList.get(i).toJSONString();
            }else{
                listJSONString+=","+student_teach_classList.get(i).toJSONString();
            }
        }
        listJSONString+="]";

        return listJSONString;
    }

    private String deleteStudent_teach_class(String student_id,String teach_class_id){
        if(this.ManageService.deleteStudent_teach_class(student_id,teach_class_id)){
            return "result:deleteStudent_teach_class success";
        }
        return "result:deleteStudent_teach_class fail";
    }

    private String addStudent_teach_class(String student_id,String teach_class_id){
        if(this.ManageService.addStudent_teach_class(student_id,teach_class_id)){
            return "result:addStudent_teach_class success";
        }
        return "result:addStudent_teach_class fail";
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    private String queryTeach_class_time_room(String pageNumber){
        List<teach_class_time_room> teach_class_time_roomList=this.ManageService.queryTeach_class_time_room();

        int start=(Integer.parseInt(pageNumber)-1)*20;
        if(start<0||start>teach_class_time_roomList.size()){
            start=0;
        }

        int end=start+20;
        if(end>teach_class_time_roomList.size()){
            end=teach_class_time_roomList.size();
        }

        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = start; i < end; i++) {
            if(flag){
                flag=false;
                listJSONString+=teach_class_time_roomList.get(i).toJSONString();
            }else{
                listJSONString+=","+teach_class_time_roomList.get(i).toJSONString();
            }
        }
        listJSONString+="]";

        return listJSONString;
    }

    private String deleteTeach_class_time_room(String teach_class_id,String time,String room){
        if(this.ManageService.deleteTeach_class_time_room(teach_class_id,time,room)){
            return "result:deleteTeach_class_time_room success";
        }
        return "result:deleteTeach_class_time_room fail";
    }

    private String addTeach_class_time_room(String teach_class_id,String time,String room){
        if(this.ManageService.addTeach_class_time_room(teach_class_id,time,room)){
            return "result:addTeach_class_time_room success";
        }
        return "result:addTeach_class_time_room fail";
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    private String queryTeach_class(String pageNumber){
        List<teach_class> teach_classList=this.ManageService.queryTeach_class();

        int start=(Integer.parseInt(pageNumber)-1)*20;
        if(start<0||start>teach_classList.size()){
            start=0;
        }

        int end=start+20;
        if(end>teach_classList.size()){
            end=teach_classList.size();
        }

        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = start; i < end; i++) {
            if(flag){
                flag=false;
                listJSONString+=teach_classList.get(i).toJSONString();
            }else{
                listJSONString+=","+teach_classList.get(i).toJSONString();
            }
        }
        listJSONString+="]";

        return listJSONString;
    }

    private String deleteTeach_class(String teach_class_id,String course_id,String teacher_id){
        if(this.ManageService.deleteTeach_class(teach_class_id,course_id,teacher_id)){
            return "result:deleteTeach_class success";
        }
        return "result:deleteTeach_class fail";
    }

    private String addTeach_class(String teach_class_id,String course_id,String teacher_id){
        if(this.ManageService.addTeach_class(teach_class_id,course_id,teacher_id)){
            return "result:addTeach_class success";
        }
        return "result:addTeach_class fail";
    }

    //    <---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    public String administratorLogin(HttpSession session, String id, String password){
        if(this.ManageService.administratorLogin(id,password)){
            session.setAttribute("isAdminLogin",true);
            session.setAttribute("administrator_id",id);
            session.setAttribute("identity","administrator");
            return "manage\\html\\manage";
        }else {
            return "redirect:manageController.do";
        }
    }

    public String student(){
        return "manage\\html\\student";
    }
    public String teacher(){
        return "manage\\html\\teacher";
    }
    public String classPage(){
        return "manage\\html\\classPage";
    }
    public String course(){
        return "manage\\html\\course";
    }
    public String course_question_bank(){
        return "manage\\html\\course_question_bank";
    }
    public String dept(){
        return "manage\\html\\dept";
    }
    public String grade(){
        return "manage\\html\\grade";
    }
    public String major(){
        return "manage\\html\\major";
    }
    public String paper_bank(){
        return "manage\\html\\paper_bank";
    }
    public String paper_in_teach_class(){
        return "manage\\html\\paper_in_teach_class";
    }
    public String student_course(){
        return "manage\\html\\student_course";
    }
    public String student_have_taken_course(){
        return "manage\\html\\student_have_taken_course";
    }
    public String student_selected_course(){
        return "manage\\html\\student_selected_course";
    }
    public String student_teach_class(){
        return "manage\\html\\student_teach_class";
    }
    public String teach_class(){
        return "manage\\html\\teach_class";
    }
    public String teach_class_time_room(){
        return "manage\\html\\teach_class_time_room";
    }
    public String manage(){
        return "manage\\html\\manage";
    }
    public String index(){
        return "manage\\html\\login";
    }
}