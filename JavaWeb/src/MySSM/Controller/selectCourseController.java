package MySSM.Controller;

import MySSM.DATA.course;
import MySSM.Service.selectCourseService;
import org.apache.catalina.connector.Request;

import javax.servlet.http.HttpSession;
import java.util.List;

public class selectCourseController {

    private selectCourseService SelectCourseService;

    public String couldBeSelectedCourse(HttpSession session){
        List<course> couldSelectedCourseList= this.SelectCourseService.couldBeSelectedCourse((String) session.getAttribute("student_id"));
        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = 0; i < couldSelectedCourseList.size(); i++) {
            if(flag){
                flag=false;
                listJSONString+=couldSelectedCourseList.get(i).toJSONString();
            }else{
                listJSONString+=","+couldSelectedCourseList.get(i).toJSONString();
            }
        }
        listJSONString+="]";
        return listJSONString;
    }

    public String haveSelectedCourse(HttpSession session){
        List<course> haveSelectedCourseList= this.SelectCourseService.haveSelectedCourse((String) session.getAttribute("student_id"));
        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = 0; i < haveSelectedCourseList.size(); i++) {
            if(flag){
                flag=false;
                listJSONString+=haveSelectedCourseList.get(i).toJSONString();
            }else{
                listJSONString+=","+haveSelectedCourseList.get(i).toJSONString();
            }
        }
        listJSONString+="]";
        return listJSONString;
    }

    public String selectCourse(HttpSession session, String course_id){

        String student_id = (String) session.getAttribute("student_id");
        boolean flag = this.SelectCourseService.selectCourse(student_id, course_id);
        if(flag){
            return "result:select success";
        }
        return "result:select false";
    }

    public String deleteSelectedCourse(HttpSession session,String course_id){
        String student_id = (String) session.getAttribute("student_id");
        boolean flag = this.SelectCourseService.deleteCourse(student_id, course_id);
        if(flag){
            return "result:delete success";
        }
        return "result:delete false";
    }

    private String index(){
        return "html\\selectCourse";
    }
}
