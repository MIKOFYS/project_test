package MySSM.Controller;

import MySSM.DATA.*;
import MySSM.Service.queryService;

import java.util.List;

public class queryController {
    private queryService QueryService=null;

    private static int queryNum=10;

    private String queryStudent(String keyword, String pageNumber){
        if(keyword.equals("")){
            keyword=null;
        }
        List<student_info> studentList=this.QueryService.queryStudent_info(keyword);

        int start=(Integer.parseInt(pageNumber)-1)*10;
        if(start<0||start>studentList.size()){
            start=0;
        }

        int end=start+10;
        if(end>studentList.size()){
            end=studentList.size();
        }

        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = start; i < end; i++) {
            if(flag){
                flag=false;
                listJSONString+=studentList.get(i).toJSONString();
            }else{
                listJSONString+=","+studentList.get(i).toJSONString();
            }
        }
        listJSONString+="]";
        //System.out.println(listJSONString);

        return listJSONString;
    }

    private String queryTeacher(String keyword, String pageNumber){
        List<teacher_info> teacherList=this.QueryService.queryTeacher_info(keyword);

        int start=(Integer.parseInt(pageNumber)-1)*10;
        if(start<0||start>teacherList.size()){
            start=0;
        }

        int end=start+10;
        if(end>teacherList.size()){
            end=teacherList.size();
        }

        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = start; i < end; i++) {
            if(flag){
                flag=false;
                listJSONString+=teacherList.get(i).toJSONString();
            }else{
                listJSONString+=","+teacherList.get(i).toJSONString();
            }
        }
        listJSONString+="]";

        return listJSONString;
    }

    private String queryStudent_teach_class_time(String student_id){
        List<student_teach_class_time_room_info> student_teach_class_time_roomList=this.QueryService.queryStudent_teach_class_time(student_id);
        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = 0; i < student_teach_class_time_roomList.size(); i++) {
            if(flag){
                flag=false;
                listJSONString+=student_teach_class_time_roomList.get(i).toJSONString();
            }else{
                listJSONString+=","+student_teach_class_time_roomList.get(i).toJSONString();
            }
        }
        listJSONString+="]";

        return listJSONString;
    }

    private String queryTeacher_teach_class_time(String teacher_id){
        List<teacher_teach_class_time_room_info> teacher_teach_class_time_roomList=this.QueryService.queryTeacher_teach_class_time(teacher_id);
        String listJSONString="JSON:[";
        boolean flag=true;
        for (int i = 0; i < teacher_teach_class_time_roomList.size(); i++) {
            if(flag){
                flag=false;
                listJSONString+=teacher_teach_class_time_roomList.get(i).toJSONString();
            }else{
                listJSONString+=","+teacher_teach_class_time_roomList.get(i).toJSONString();
            }
        }
        listJSONString+="]";

        return listJSONString;
    }

    private String index(){
        return "html\\query";
    }

    private String queryCourse(){
        return "html\\queryCourse";
    }
}
