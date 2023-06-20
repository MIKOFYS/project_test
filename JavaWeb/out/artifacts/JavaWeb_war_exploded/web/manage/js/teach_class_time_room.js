window.onload=function(){
    let vue=new Vue({
        "el":"#form",
        data:{
            pageNumber:1,
            teach_class_time_roomList:null,
            teach_class_time_roomColName:["教学班ID","上课时间","上课教室","选项"],
        },
        methods:{
            checkQueryTeach_class_time_room:function (){
                vue.pageNumber=1;
                vue.teach_class_time_roomList=null;
                vue.queryTeach_class_time_room();
            },
            getJSONLen:function (JSONList){
                let len=0;
                for (let item in JSONList) {
                    len++;
                }
                return len;
            },
            pageNumberSUB:function (){
                if(vue.pageNumber<=1){
                    ;
                }else {
                    vue.pageNumber--;
                }
                vue.queryTeach_class_time_room();
            },
            pageNumberADD:function (){
                if(this.getJSONLen(vue.teach_class_time_roomList)<20){
                    ;
                }else{
                    vue.pageNumber++;
                }
                vue.queryTeach_class_time_room();
            },
            queryTeach_class_time_room:function (){
                vue.teach_class_time_roomList=null;
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"queryTeach_class_time_room",
                        pageNumber:vue.pageNumber.toString()
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        let jsonList=JSON.parse(JSON.stringify(data));

                        if(vue.getJSONLen(jsonList) == 0 && vue.pageNumber > 1){
                            vue.pageNumber--;
                            vue.query();
                            return ;
                        }

                        vue.teach_class_time_roomList=jsonList;
                    })
                    .catch(function (value){

                    });
            },
            deleteTeach_class_time_room:function (teach_class_id,time,room){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"deleteTeach_class_time_room",
                        teach_class_id:teach_class_id,
                        time:time,
                        room:room,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryTeach_class_time_room();
                    })
                    .catch(function (value){

                    });
            },
            addTeach_class_time_room:function (teach_class_id,time,room){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"addTeach_class_time_room",
                        teach_class_id:teach_class_id,
                        time:time,
                        room:room,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryTeach_class_time_room();
                    })
                    .catch(function (value){

                    });
            },
            checkAddTeach_class_time_room:function (){
                let teach_class_id = document.getElementById("teach_class_id").value;
                let time = document.getElementById("time").value;
                let room = document.getElementById("room").value;
                vue.addTeach_class_time_room(teach_class_id,time,room);
            }
        }
    });
    vue.checkQueryTeach_class_time_room();
}