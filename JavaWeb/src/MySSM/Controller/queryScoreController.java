package MySSM.Controller;

import MySSM.DATA.studentCourseScoreInfo;
import MySSM.Service.queryScoreService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class queryScoreController {

    private queryScoreService QueryScoreService;

    public String queryStudentScore(HttpSession session){
        List<studentCourseScoreInfo> studentCourseScoreInfoList = this.QueryScoreService.queryStudentScore((String) session.getAttribute("student_id"));
        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = 0; i < studentCourseScoreInfoList.size(); i++) {
            if(flag){
                flag=false;
                listJSONString+=studentCourseScoreInfoList.get(i).toJSONString();
            }else{
                listJSONString+=","+studentCourseScoreInfoList.get(i).toJSONString();
            }
        }
        listJSONString+="]";
        return listJSONString;
    }

    private String index(){
        return "html\\queryScore";
    }
}
