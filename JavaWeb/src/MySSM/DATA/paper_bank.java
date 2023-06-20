package MySSM.DATA;

import com.alibaba.fastjson.JSONObject;

public class paper_bank {
    String paper_id;
    String directory;

    private static JSONObject jsonObject=new JSONObject();

    public paper_bank(){

    }
    public paper_bank(String paper_id, String directory) {
        this.paper_id = paper_id;
        this.directory = directory;
    }

    public String getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(String paper_id) {
        this.paper_id = paper_id;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String toJSONString() {
        jsonObject.clear();
        jsonObject.put("paper_id",this.paper_id);
        jsonObject.put("directory",this.directory);
        return  jsonObject.toJSONString();
    }
}
