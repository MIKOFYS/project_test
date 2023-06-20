package MySSM.DATA;

import com.alibaba.fastjson.JSONObject;

public class teach_class {
    String teach_class_id;
    String course_id;
    String teacher_id;

    private static JSONObject jsonObject=new JSONObject();
    public teach_class(){

    }
    public teach_class(String teach_class_id, String course_id, String teacher_id) {
        this.teach_class_id = teach_class_id;
        this.course_id = course_id;
        this.teacher_id = teacher_id;
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

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String toJSONString() {
        jsonObject.clear();
        jsonObject.put("teach_class_id",this.teach_class_id);
        jsonObject.put("course_id",this.course_id);
        jsonObject.put("teacher_id",this.teacher_id);
        return  jsonObject.toJSONString();
    }
}
