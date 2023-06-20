package MySSM.DATA;

import com.alibaba.fastjson.JSONObject;

public class student_course {
    String student_id;
    String course_id;
    Double score;

    private static JSONObject jsonObject=new JSONObject();
    public student_course(){

    }
    public student_course(String student_id, String course_id, Double score) {
        this.student_id = student_id;
        this.course_id = course_id;
        this.score = score;
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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String toJSONString() {
        jsonObject.clear();
        jsonObject.put("student_id",this.student_id);
        jsonObject.put("course_id",this.course_id);
        jsonObject.put("score",this.score);
        return  jsonObject.toJSONString();
    }
}
