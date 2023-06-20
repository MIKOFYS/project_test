package MySSM.DATA;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class student_teach_class_time_room_info {
    String teach_class_id;
    String course_name;
    String teacher_name;
    String time;//  {"oddWeek":["周一12","周二34"],"evenWeek":["周二12","周三56"]}
                //  {"oddWeek":["周二12","周三34"],"evenWeek":["周三12","周三78"]}
    String room;
    private static JSONObject jsonObject=new JSONObject();
    public student_teach_class_time_room_info(){

    }

    public student_teach_class_time_room_info(String teach_class_id, String course_name, String teacher_name, String time, String room) {
        this.teach_class_id = teach_class_id;
        this.course_name = course_name;
        this.teacher_name = teacher_name;
        this.time = time;
        this.room = room;
    }

    public String getTeach_class_id() {
        return teach_class_id;
    }

    public void setTeach_class_id(String teach_class_id) {
        this.teach_class_id = teach_class_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String toJSONString() {
        jsonObject.clear();
        jsonObject.put("teach_class_id",this.teach_class_id);
        jsonObject.put("course_name",this.course_name);
        jsonObject.put("teacher_name",this.teacher_name);
        JSONObject jsonTime =  JSON.parseObject(this.time);
        jsonObject.put("time",jsonTime);
        jsonObject.put("room",this.room);
        return  jsonObject.toJSONString();
    }
}
