package MySSM.DATA;

import com.alibaba.fastjson.JSONObject;

public class student_selected_course {
    String student_id;
    String course_id;

    private static JSONObject jsonObject=new JSONObject();

    public student_selected_course(){

    }
    public student_selected_course(String student_id, String course_id) {
        this.student_id = student_id;
        this.course_id = course_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String toJSONString() {
        jsonObject.clear();
        jsonObject.put("student_id",this.student_id);
        jsonObject.put("course_id",this.course_id);
        return  jsonObject.toJSONString();
    }
}
