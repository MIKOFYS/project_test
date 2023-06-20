package MySSM.DATA;

import com.alibaba.fastjson.JSONObject;

public class grade {
    String grade_id;

    private static JSONObject jsonObject=new JSONObject();
    public grade(){

    }
    public grade(String grade_id) {
        this.grade_id = grade_id;
    }
    public String getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(String grade_id) {
        this.grade_id = grade_id;
    }

    public String toJSONString() {
        jsonObject.clear();
        jsonObject.put("grade_id",this.grade_id);
        return  jsonObject.toJSONString();
    }
}
