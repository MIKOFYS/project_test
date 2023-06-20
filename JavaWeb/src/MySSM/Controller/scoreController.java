package MySSM.Controller;

import MySSM.DATA.paper_in_teach_class_info;
import MySSM.DATA.student_course_info;
import MySSM.DATA.teach_class_course_info;
import MySSM.Service.scoreService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class scoreController {
    private scoreService ScoreService;
    public String queryTeach_class(HttpSession session){
        List<teach_class_course_info> teach_class_course_infoList=this.ScoreService.queryTeach_class((String) session.getAttribute("teacher_id"));
        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = 0; i < teach_class_course_infoList.size(); i++) {
            if(flag){
                flag=false;
                listJSONString+=teach_class_course_infoList.get(i).toJSONString();
            }else{
                listJSONString+=","+teach_class_course_infoList.get(i).toJSONString();
            }
        }
        listJSONString+="]";

        return listJSONString;
    }

    public String queryPaper_in_teach_class_info(String teach_class_id){
        List<paper_in_teach_class_info> paper_in_teach_class_infoList=this.ScoreService.queryPaper_in_teach_class(teach_class_id);
        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = 0; i < paper_in_teach_class_infoList.size(); i++) {
            if(flag){
                flag=false;
                listJSONString+=paper_in_teach_class_infoList.get(i).toJSONString();
            }else{
                listJSONString+=","+paper_in_teach_class_infoList.get(i).toJSONString();
            }
        }
        listJSONString+="]";

        System.out.println(listJSONString);

        return listJSONString;
    }

    public String queryStudent_course(String teach_class_id){
        List<student_course_info> student_courseList=this.ScoreService.queryStudent_course(teach_class_id);
        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = 0; i < student_courseList.size(); i++) {
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

    public String downloadPaper(String teach_class_id, String student_id,String paper_id){
        String res = this.ScoreService.downloadPaper(teach_class_id,student_id,paper_id);
        if(res.startsWith("result:")) return res;
        return "result:"+res;
    }


    public String checkStudentCourseScore(String student_id,String course_id,String score){
        if(this.ScoreService.checkStudentCourseScore(student_id,course_id,Double.parseDouble(score))){
            return "result:checkStudentCourseScore success";
        }
        return "result:checkStudentCourseScore fail";
    }

    public String checkPaper(String teach_class_id,String student_id,String paper_id,String score){
        if(score==null||score.equals("")){
            return "result:check false";
        }
        if(this.ScoreService.checkPaper(teach_class_id,student_id,paper_id,Double.parseDouble(score))){
            return "result:check success";
        }
        return "result:check false";
    }

    public String index(){
        return "html\\score";
    }
}
