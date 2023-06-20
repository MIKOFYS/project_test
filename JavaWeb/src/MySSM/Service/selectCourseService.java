package MySSM.Service;

import MySSM.DAO.courseDAO;
import MySSM.DAO.student_have_taken_courseDAO;
import MySSM.DAO.student_selected_courseDAO;
import MySSM.DATA.course;

import java.util.ArrayList;
import java.util.List;

public class selectCourseService {
    public courseDAO CourseDAO;
    public student_selected_courseDAO Student_selected_courseDAO;
    public student_have_taken_courseDAO Student_have_taken_courseDAO;

    public boolean deleteCourse(String student_id, String course_id){
        List<course> haveSelectedCourseList=this.haveSelectedCourse(student_id);
        for (int i = 0; i < haveSelectedCourseList.size(); i++) {
            if(haveSelectedCourseList.get(i).getCourse_id().equals(course_id)){
                this.Student_selected_courseDAO.update("delete from student_selected_course where student_id = ? and course_id = ?",student_id,course_id);
                return true;
            }
        }
        return false;
    }

    public boolean selectCourse(String student_id,String course_id){
        List<course> couldSelectedCourseList=this.couldBeSelectedCourse(student_id);
        for (int i = 0; i < couldSelectedCourseList.size(); i++) {
            if(couldSelectedCourseList.get(i).getCourse_id().equals(course_id)){
                this.Student_selected_courseDAO.update("insert into student_selected_course values(?,?)",student_id,course_id);
                return true;
            }
        }
        return false;
    }

    public List<course> notHaveTakenCourse(String student_id){
        List<Object> haveTakenCourseList=this.Student_have_taken_courseDAO.queryOneColumnList("select course_id from student_have_taken_course where student_id=?",1,student_id);
        List<course> course=this.CourseDAO.queryMultiRow("select * from course");

        System.out.println(haveTakenCourseList);
        for (int i = 0; i < course.size(); i++) {
//            boolean courseNotInHaveTakenCourse=true;
            for (int j = 0; j < haveTakenCourseList.size(); j++) {
                if(course.get(i).getCourse_id().equals((String) haveTakenCourseList.get(j))){
//                    courseNotInHaveTakenCourse=false;
                    course.remove(i--);//i--是因为防止下一次循环跳过一个元素
                    break;
                }
            }
        }
        //System.out.println(course);
        return course;
    }

    public List<course> haveSelectedCourse(String student_id){
        //获取该学生已经选过的课程id
        List<Object> selected_course_idList=this.Student_selected_courseDAO.queryOneColumnList("select course_id from student_selected_course where student_id=?",1,student_id);
        List<course> selected_courseList=new ArrayList<>(selected_course_idList.size());
        for (int i = 0; i < selected_course_idList.size(); i++) {
            course courseTemp=this.CourseDAO.querySingleRow("select * from course where course_id=?",(String)selected_course_idList.get(i));
            selected_courseList.add(courseTemp);
        }
        return selected_courseList;
    }

    public List<course> couldBeSelectedCourse(String student_id){
        List<course> couldSelectedCourseList=this.notHaveTakenCourse(student_id);
        List<course> selectedCourseIDList=this.haveSelectedCourse(student_id);
        for (int i = 0; i < couldSelectedCourseList.size(); i++) {
            for (int j = 0; j < selectedCourseIDList.size(); j++) {
                if(couldSelectedCourseList.get(i).getCourse_id().equals(selectedCourseIDList.get(j).getCourse_id())){
                    couldSelectedCourseList.remove(i--);//i--是因为防止下一次循环跳过一个元素
                    break;
                }
            }
        }
        return couldSelectedCourseList;
    }
}
