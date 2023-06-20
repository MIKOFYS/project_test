package MySSM.DATA;

import com.alibaba.fastjson.JSONObject;

public class teach_class_time_room {
    String teach_class_id;
    String time;
    String room;

    private static JSONObject jsonObject=new JSONObject();

    public teach_class_time_room(){

    }

    public teach_class_time_room(String teach_class_id, String time, String room) {
        this.teach_class_id = teach_class_id;
        this.time = time;
        this.room = room;
    }

    public String getTeach_class_id() {
        return teach_class_id;
    }

    public void setTeach_class_id(String teach_class_id) {
        this.teach_class_id = teach_class_id;
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
        jsonObject.put("time",this.time);
        jsonObject.put("room",this.room);
        return  jsonObject.toJSONString();
    }
}
