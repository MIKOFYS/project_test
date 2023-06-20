package MySSM.DATA;

import com.alibaba.fastjson.JSONObject;

public class course_question_bank {
    String course_id;
    String paper_id;

    private static JSONObject jsonObject=new JSONObject();

    public course_question_bank(){

    }
    public course_question_bank(String course_id, String paper_id) {
        this.course_id = course_id;
        this.paper_id = paper_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(String paper_id) {
        this.paper_id = paper_id;
    }

    public String toJSONString() {
        jsonObject.clear();
        jsonObject.put("course_id",this.course_id);
        jsonObject.put("paper_id",this.paper_id);
        return  jsonObject.toJSONString();
    }
}
