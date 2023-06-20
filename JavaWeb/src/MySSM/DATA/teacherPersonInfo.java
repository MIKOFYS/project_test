package MySSM.DATA;

import com.alibaba.fastjson.JSONObject;

public class teacherPersonInfo {
    private String teacher_id;
    private String dept_name;
    private String name;
    private String sex;
    private String phone;
    private String email;
    private String picture;
    private static JSONObject jsonObject=new JSONObject();

    public teacherPersonInfo() {
    }

    public teacherPersonInfo(String teacher_id, String dept_name, String name, String sex, String phone, String email, String picture) {
        this.teacher_id = teacher_id;
        this.dept_name = dept_name;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.picture = picture;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String toJSONString(String picture) {
        jsonObject.clear();
        jsonObject.put("teacher_id",this.teacher_id);
        jsonObject.put("dept_name",this.dept_name);
        jsonObject.put("name",this.name);
        jsonObject.put("sex",this.sex);
        jsonObject.put("phone",this.phone);
        jsonObject.put("email",this.email);
        jsonObject.put("picture",picture);
        return jsonObject.toJSONString();
    }

    public String toJSONString() {
        jsonObject.clear();
        jsonObject.put("teacher_id",this.teacher_id);
        jsonObject.put("dept_name",this.dept_name);
        jsonObject.put("name",this.name);
        jsonObject.put("sex",this.sex);
        jsonObject.put("phone",this.phone);
        jsonObject.put("email",this.email);
        return jsonObject.toJSONString();
    }
}
