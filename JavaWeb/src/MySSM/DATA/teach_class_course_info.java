package MySSM.DATA;

import com.alibaba.fastjson.JSONObject;

public class teach_class_course_info {
    private String teach_class_id;
    private String course_id;
    private String courseName;
    private static JSONObject jsonObject=new JSONObject();
    public teach_class_course_info(){

    }
    public teach_class_course_info(String teach_class_id, String course_id, String courseName) {
        this.teach_class_id = teach_class_id;
        this.course_id = course_id;
        this.courseName = courseName;
    }

    public String getTeach_class_id() {
        return teach_class_id;
    }

    public void setTeach_class_id(String teach_class_id) {
        this.teach_class_id = teach_class_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String toJSONString() {
        jsonObject.clear();
        jsonObject.put("teach_class_id",this.teach_class_id);
        jsonObject.put("course_id",this.course_id);
        jsonObject.put("courseName",this.courseName);
        return  jsonObject.toJSONString();
    }
}
