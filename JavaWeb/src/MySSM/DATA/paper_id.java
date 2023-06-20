package MySSM.DATA;

import com.alibaba.fastjson.JSONObject;

public class paper_id {
    String paper_id;
    private static JSONObject jsonObject=new JSONObject();
    public paper_id(){

    }
    public paper_id(String paper_id) {
        this.paper_id = paper_id;
    }

    public String getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(String paper_id) {
        this.paper_id = paper_id;
    }

    public String toJSONString() {
        jsonObject.clear();
        jsonObject.put("paper_id",this.paper_id);
        return  jsonObject.toJSONString();
    }
}
