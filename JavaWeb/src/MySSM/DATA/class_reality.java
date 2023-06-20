package MySSM.DATA;

import com.alibaba.fastjson.JSONObject;

public class class_reality {
    String class_id;
    String grade_id;
    String major_id;

    private static JSONObject jsonObject=new JSONObject();

    public class_reality(){

    }

    public class_reality(String class_id, String grade_id, String major_id) {
        this.class_id = class_id;
        this.grade_id = grade_id;
        this.major_id = major_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(String grade_id) {
        this.grade_id = grade_id;
    }

    public String getMajor_id() {
        return major_id;
    }

    public void setMajor_id(String major_id) {
        this.major_id = major_id;
    }

    public String toJSONString() {
        jsonObject.clear();
        jsonObject.put("class_id",this.class_id);
        jsonObject.put("grade_id",this.grade_id);
        jsonObject.put("major_id",this.major_id);
        return  jsonObject.toJSONString();
    }
}
