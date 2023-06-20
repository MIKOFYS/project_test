package MySSM.DATA;

import com.alibaba.fastjson.JSONObject;

public class dept {
    String dept_id;
    String dept_name;

    private static JSONObject jsonObject=new JSONObject();
    public dept(){

    }
    public dept(String dept_id, String dept_name) {
        this.dept_id = dept_id;
        this.dept_name = dept_name;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String toJSONString() {
        jsonObject.clear();
        jsonObject.put("dept_id",this.dept_id);
        jsonObject.put("dept_name",this.dept_name);
        return  jsonObject.toJSONString();
    }
}
