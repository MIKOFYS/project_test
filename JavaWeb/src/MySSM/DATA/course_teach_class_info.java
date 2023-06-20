package MySSM.DATA;

import com.alibaba.fastjson.JSONObject;

public class course_teach_class_info {
    String course_id;
    String courseName;
    String teach_class_id;
    private static JSONObject jsonObject=new JSONObject();

    public course_teach_class_info(){

    }

    public course_teach_class_info(String course_id, String courseName, String teach_class_id) {
        this.course_id = course_id;
        this.courseName = courseName;
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

    public String getTeach_class_id() {
        return teach_class_id;
    }

    public void setTeach_class_id(String teach_class_id) {
        this.teach_class_id = teach_class_id;
    }

    public String toJSONString() {
        jsonObject.clear();
        jsonObject.put("course_id",this.course_id);
        jsonObject.put("courseName",this.courseName);
        jsonObject.put("teach_class_id",this.teach_class_id);
        return  jsonObject.toJSONString();
    }
}
