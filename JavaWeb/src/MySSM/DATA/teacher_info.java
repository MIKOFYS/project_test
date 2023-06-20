package MySSM.DATA;

import com.alibaba.fastjson.JSONObject;

/**
 * @author mikofys
 * @create 2022-06-17-12:49
 */
public class teacher_info {
    private String name;
    private String sex;
    private String teacher_id;
    private String dept_name;
    private static JSONObject jsonObject=new JSONObject();
    public teacher_info() {
    }

    public teacher_info(String name, String sex, String teacher_id, String dept_name) {
        this.name = name;
        this.sex = sex;
        this.teacher_id = teacher_id;
        this.dept_name = dept_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String toJSONString() {
        jsonObject.clear();
        jsonObject.put("name",this.name);
        jsonObject.put("sex",this.sex);
        jsonObject.put("teacher_id",this.teacher_id);
        jsonObject.put("dept_name",this.dept_name);
        return  jsonObject.toJSONString();
    }
}
