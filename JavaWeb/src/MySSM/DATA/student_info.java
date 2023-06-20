package MySSM.DATA;

import com.alibaba.fastjson.JSONObject;

/**
 * @author mikofys
 * @create 2022-06-17-12:21
 */
public class student_info {

    private String name;

    private String sex;

    private String student_id;

    private String class_id;

    private String grade_id;

    private String major_name;

    private String dept_name;
    private static JSONObject jsonObject=new JSONObject();
    public student_info() {
    }

    public student_info(String name, String sex, String student_id, String class_id, String grade_id, String major_name, String dept_name) {
        this.name = name;
        this.sex = sex;
        this.student_id = student_id;
        this.class_id = class_id;
        this.grade_id = grade_id;
        this.major_name = major_name;
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
    public String toJSONString() {
        jsonObject.clear();
        jsonObject.put("name",this.name);
        jsonObject.put("sex",this.sex);
        jsonObject.put("student_id",this.student_id);
        jsonObject.put("class_id",this.class_id);
        jsonObject.put("grade_id",this.grade_id);
        jsonObject.put("major_name",this.major_name);
        jsonObject.put("dept_name",this.dept_name);
        return  jsonObject.toJSONString();
    }

    @Override
    public String toString() {
        return "student_info{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", student_id='" + student_id + '\'' +
                ", class_id='" + class_id + '\'' +
                ", grade_id='" + grade_id + '\'' +
                ", major_name='" + major_name + '\'' +
                ", dept_name='" + dept_name + '\'' +
                '}';
    }
}
