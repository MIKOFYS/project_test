package MySSM.DATA;

import com.alibaba.fastjson.JSONObject;

public class student {
    private String student_id;
    private String name;
    private String sex;
    private String phone;
    private String email;
    private String picture;
    private String password;
    private String class_id;
    private String hashcode;
    private Integer forget_password_flag;

    private static JSONObject jsonObject=new JSONObject();

    public student(){

    }

    public student(String student_id, String name, String sex, String phone, String email, String picture, String password, String class_id, String hashcode, Integer forget_password_flag) {
        this.student_id = student_id;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.picture = picture;
        this.password = password;
        this.class_id = class_id;
        this.hashcode = hashcode;
        this.forget_password_flag = forget_password_flag;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getHashcode() {
        return hashcode;
    }

    public void setHashcode(String hashcode) {
        this.hashcode = hashcode;
    }

    public Integer getForget_password_flag() {
        return forget_password_flag;
    }

    public void setForget_password_flag(Integer forget_password_flag) {
        this.forget_password_flag = forget_password_flag;
    }

    public String toJSONString() {
        jsonObject.clear();
        jsonObject.put("student_id",this.student_id);
        jsonObject.put("name",this.name);
        jsonObject.put("sex",this.sex);
        jsonObject.put("phone",this.phone);
        jsonObject.put("email",this.email);
        jsonObject.put("picture",this.picture);
        jsonObject.put("password",this.password);
        jsonObject.put("class_id",this.class_id);
        jsonObject.put("hashcode",this.hashcode);
        jsonObject.put("forget_password_flag",this.forget_password_flag);
        return  jsonObject.toJSONString();
    }
}
