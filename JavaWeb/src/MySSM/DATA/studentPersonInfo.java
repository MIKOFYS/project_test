package MySSM.DATA;

import com.alibaba.fastjson.JSONObject;

public class studentPersonInfo {
    private String student_id;
    private String class_id;
    private String grade_id;
    private String major_name;
    private String dept_name;
    private String name;
    private String sex;
    private String phone;
    private String email;
    private String picture;
    private static JSONObject jsonObject=new JSONObject();

    public studentPersonInfo(){

    }

    public studentPersonInfo(String student_id, String class_id, String grade_id, String major_name, String dept_name, String name, String sex, String phone, String email, String picture) {
        this.student_id = student_id;
        this.class_id = class_id;
        this.grade_id = grade_id;
        this.major_name = major_name;
        this.dept_name = dept_name;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.picture = picture;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
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

    public String getMajor_name() {
        return major_name;
    }

    public void setMajor_name(String major_name) {
        this.major_name = major_name;
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
        jsonObject.put("student_id",this.student_id);
        jsonObject.put("class_id",this.class_id);
        jsonObject.put("grade_id",this.grade_id);
        jsonObject.put("major_name",this.major_name);
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
        jsonObject.put("student_id",this.student_id);
        jsonObject.put("class_id",this.class_id);
        jsonObject.put("grade_id",this.grade_id);
        jsonObject.put("major_name",this.major_name);
        jsonObject.put("dept_name",this.dept_name);
        jsonObject.put("name",this.name);
        jsonObject.put("sex",this.sex);
        jsonObject.put("phone",this.phone);
        jsonObject.put("email",this.email);
        return jsonObject.toJSONString();
    }
}
