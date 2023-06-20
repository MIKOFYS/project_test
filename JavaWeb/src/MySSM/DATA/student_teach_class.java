package MySSM.DATA;

import com.alibaba.fastjson.JSONObject;

public class student_teach_class {
    String student_id;
    String teach_class_id;

    private static JSONObject jsonObject=new JSONObject();

    public student_teach_class(){

    }
    public student_teach_class(String student_id, String teach_class_id) {
        this.student_id = student_id;
        this.teach_class_id = teach_class_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getTeach_class_id() {
        return teach_class_id;
    }

    public void setTeach_class_id(String teach_class_id) {
        this.teach_class_id = teach_class_id;
    }

    public String toJSONString() {
        jsonObject.clear();
        jsonObject.put("student_id",this.student_id);
        jsonObject.put("teach_class_id",this.teach_class_id);
        return  jsonObject.toJSONString();
    }
}
