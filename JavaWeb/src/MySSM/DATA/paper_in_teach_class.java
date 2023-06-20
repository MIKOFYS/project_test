package MySSM.DATA;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

public class paper_in_teach_class {
    String teach_class_id;
    String student_id;
    String paper_id;
    Double score;
    String directory;
    String time;

    private static JSONObject jsonObject=new JSONObject();

    public paper_in_teach_class(){

    }

    public paper_in_teach_class(String teach_class_id, String student_id, String paper_id, Double score, String directory, String time) {
        this.teach_class_id = teach_class_id;
        this.student_id = student_id;
        this.paper_id = paper_id;
        this.score = score;
        this.directory = directory;
        this.time = time;
    }

    public String getTeach_class_id() {
        return teach_class_id;
    }

    public void setTeach_class_id(String teach_class_id) {
        this.teach_class_id = teach_class_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(String paper_id) {
        this.paper_id = paper_id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String toJSONString() {
        jsonObject.clear();
        jsonObject.put("teach_class_id",this.teach_class_id);
        jsonObject.put("student_id",this.student_id);
        jsonObject.put("paper_id",this.paper_id);
        jsonObject.put("score",this.score);
        jsonObject.put("directory",this.directory);
        jsonObject.put("time",this.time);
        return  jsonObject.toJSONString();
    }
}
