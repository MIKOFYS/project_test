package MySSM.Controller;

import MySSM.DATA.course_teach_class_info;
import MySSM.DATA.paper_id;
import MySSM.Service.paperService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
public class paperController {
    private paperService PaperService;

    private String queryCourse(HttpSession session){
        List<course_teach_class_info> course_teach_classList=this.PaperService.queryCourse((String) session.getAttribute("student_id"));

        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = 0; i < course_teach_classList.size(); i++) {
            if(flag){
                flag=false;
                listJSONString+=course_teach_classList.get(i).toJSONString();
            }else{
                listJSONString+=","+course_teach_classList.get(i).toJSONString();
            }
        }
        listJSONString+="]";

        return listJSONString;
    }

    public String queryPaper(HttpSession session,String course_id){
        List<paper_id> paper_idList=this.PaperService.queryPaper(course_id);

        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = 0; i < paper_idList.size(); i++) {
            if(flag){
                flag=false;
                listJSONString+=paper_idList.get(i).toJSONString();
            }else{
                listJSONString+=","+paper_idList.get(i).toJSONString();
            }
        }
        listJSONString+="]";

        return listJSONString;
    }

    public String uploadPaper(String teach_class_id,String student_id,String paper_id,String directory){
        if(this.PaperService.uploadPaper(teach_class_id,student_id,paper_id,directory)){
            return "result:fileUpload success";
        }
        return "result:fileUpload fail";
    }

    public String downloadPaper(String paper_id){
        String res = this.PaperService.downloadPaper(paper_id);
        if(res.startsWith("result:")) return res;
        return "result:" + res;
    }

    private String queryStudent_id(HttpSession session) {
        return "JSON:"+session.getAttribute("student_id");
    }

    public String index(){
        return "html\\paper";
    }

    public String uploadPage(){
      return "html\\uploadPage";
    }
}
