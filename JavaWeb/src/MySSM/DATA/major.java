package MySSM.DATA;

import com.alibaba.fastjson.JSONObject;

public class major {
    String major_id;
    String major_name;
    String dept_id;

    private static JSONObject jsonObject=new JSONObject();

    public major(){

    }
    public major(String major_id, String major_name, String dept_id) {
        this.major_id = major_id;
        this.major_name = major_name;
        this.dept_id = dept_id;
    }

    public String getMajor_id() {
        return major_id;
    }

    public void setMajor_id(String major_id) {
        this.major_id = major_id;
    }

    public String getMajor_name() {
        return major_name;
    }

    public void setMajor_name(String major_name) {
        this.major_name = major_name;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String toJSONString() {
        jsonObject.clear();
        jsonObject.put("major_id",this.major_id);
        jsonObject.put("major_name",this.major_name);
        jsonObject.put("dept_id",this.dept_id);
        return  jsonObject.toJSONString();
    }
}
