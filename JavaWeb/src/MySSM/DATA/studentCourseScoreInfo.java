package MySSM.DATA;

import com.alibaba.fastjson.JSONObject;

public class studentCourseScoreInfo {
    private String course_id;
    private String course_name;
    private String teach_class_id;
    private String teacher_name;
    private Double score;

    private static JSONObject jsonObject=new JSONObject();

    public studentCourseScoreInfo(){

    }

    public studentCourseScoreInfo(String course_id, String course_name, String teach_class_id, String teacher_name, Double score) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.teach_class_id = teach_class_id;
        this.teacher_name = teacher_name;
        this.score = score;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getTeach_class_id() {
        return teach_class_id;
    }

    public void setTeach_class_id(String teach_class_id) {
        this.teach_class_id = teach_class_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String toJSONString() {
        jsonObject.clear();
        jsonObject.put("course_id",this.course_id);
        jsonObject.put("course_name",this.course_name);
        jsonObject.put("teach_class_id",this.teach_class_id);
        jsonObject.put("teacher_name",this.teacher_name);
        jsonObject.put("score",this.score);
        return  jsonObject.toJSONString();
    }
}
