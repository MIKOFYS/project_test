package MySSM.DATA;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class teacher_teach_class_time_room_info {
    String teach_class_id;
    String course_name;
    String time;
    String room;
    private static JSONObject jsonObject=new JSONObject();
    public teacher_teach_class_time_room_info(){

    }
    public teacher_teach_class_time_room_info(String teach_class_id, String course_name, String time, String room) {
        this.teach_class_id = teach_class_id;
        this.course_name = course_name;
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
        JSONObject jsonTime =  JSON.parseObject(this.time);
        jsonObject.put("time",jsonTime);
        jsonObject.put("room",this.room);
        return  jsonObject.toJSONString();
    }
}
