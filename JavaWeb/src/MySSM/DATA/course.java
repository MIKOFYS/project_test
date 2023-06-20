package MySSM.DATA;

import com.alibaba.fastjson.JSONObject;

public class course {
    String course_id;
    String name;
    Integer credit;
    Integer period;

    private static JSONObject jsonObject=new JSONObject();

    public course(){

    }
    public course(String course_id, String name, Integer credit, Integer period) {
        this.course_id = course_id;
        this.name = name;
        this.credit = credit;
        this.period = period;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "course{" +
                "course_id='" + course_id + '\'' +
                ", name='" + name + '\'' +
                ", credit=" + credit +
                ", period=" + period +
                '}';
    }

    public String toJSONString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("course_id",this.course_id);
        jsonObject.put("name",this.name);
        jsonObject.put("credit",this.credit);
        jsonObject.put("period",this.period);
        return  jsonObject.toJSONString();
    }
}
