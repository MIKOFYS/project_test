window.onload=function(){
    let vue=new Vue({
        "el":"#form",
        data:{
            operator:"queryStudent_teach_class_time",
            id:null,
            list:null,
            week:["时间","周一","周二","周三","周四","周五","周六","周天"],
            weekFlag:0
        },
        methods:{
            changeWeekFlag:function (num){
                vue.weekFlag=num;
                vue.displayClear();
                if(vue.operator=="queryStudent_teach_class_time"){
                    vue.displayStudentList();
                }else{
                    vue.displayTeacherList();
                }
            },
            queryStudent:function (){
                vue.operator="queryStudent_teach_class_time";
                vue.displayClear();
            },
            queryTeacher:function (){
                vue.operator="queryTeacher_teach_class_time";
                vue.displayClear();
            },
            query:function (){
                vue.displayClear();
                if(vue.operator=="queryStudent_teach_class_time"){
                    vue.queryStudent_teach_class_time_roomList();
                }else{
                    vue.queryTeacher_teach_class_time_roomList();
                }
            },
            queryStudent_teach_class_time_roomList:function (){
                axios({
                    method: "POST",
                    url:"queryController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:vue.operator,
                        student_id:vue.id
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        vue.list=JSON.parse(JSON.stringify(data));
                        vue.displayStudentList();
                    })
                    .catch(function (value){

                    });
            },
            queryTeacher_teach_class_time_roomList:function (){
                axios({
                    method: "POST",
                    url:"queryController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:vue.operator,
                        teacher_id:vue.id
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        vue.list=JSON.parse(JSON.stringify(data));
                        vue.displayTeacherList();
                    })
                    .catch(function (value){

                    });
            },
            displayStudentList:function (){
                for(let item of vue.list){
                    let teach_class_id=item.teach_class_id;
                    let teacher_name=item.teacher_name;
                    let course_name=item.course_name;
                    let room=item.room;
                    console.log(item);
                    console.log(teach_class_id);
                    console.log(teacher_name);
                    let time=JSON.parse(JSON.stringify(item.time));
                    let timeList;

                    let str="";
                    str+="教学班:"+teach_class_id+"\n";
                    str+="老师:"+teacher_name+"\n";
                    str+="课程:"+course_name+"\n";
                    str+="地点:"+room;
                    if(vue.weekFlag%2!=0){
                        timeList=time.oddWeek;
                    }else{
                        timeList=time.evenWeek;
                    }
                    for(let timeItem of timeList){
                        let prefix=timeItem.substring(0,2);
                        let suffix=timeItem.substring(2);
                        let domId="";

                        if(suffix=="12"){
                            domId+="1";
                        }else if(suffix=="34"){
                            domId+="2";
                        }else if(suffix=="56"){
                            domId+="3";
                        }else if(suffix=="78"){
                            domId+="4";
                        }else if(suffix=="910"){
                            domId+="5";
                        }else if(suffix=="1112"){
                            domId+="6";
                        }

                        if(prefix=="周一"){
                            domId+="1"
                        }else if(prefix=="周二"){
                            domId+="2";
                        }else if(prefix=="周三"){
                            domId+="3";
                        }else if(prefix=="周四"){
                            domId+="4";
                        }else if(prefix=="周五"){
                            domId+="5";
                        }else if(prefix=="周六"){
                            domId+="6";
                        }else if(prefix=="周天"){
                            domId+="7";
                        }
                        document.getElementById(domId).innerText=str;
                    }
                }
            },
            displayTeacherList:function (){
                for(let item of vue.list){
                    let teach_class_id=item.teach_class_id;
                    let course_name=item.course_name;
                    let room=item.room;
                    let time=JSON.parse(JSON.stringify(item.time));
                    let timeList;

                    let str="";
                    str+="教学班:"+teach_class_id+"\n";
                    str+="课程:"+course_name+"\n";
                    str+="地点:"+room;
                    if(vue.weekFlag%2!=0){
                        timeList=time.oddWeek;
                    }else{
                        timeList=time.evenWeek;
                    }
                    for(let timeItem of timeList){
                        let prefix=timeItem.substring(0,2);
                        let suffix=timeItem.substring(2);
                        let domId="";

                        if(suffix=="12"){
                            domId+="1";
                        }else if(suffix=="34"){
                            domId+="2";
                        }else if(suffix=="56"){
                            domId+="3";
                        }else if(suffix=="78"){
                            domId+="4";
                        }else if(suffix=="910"){
                            domId+="5";
                        }else if(suffix=="1112"){
                            domId+="6";
                        }

                        if(prefix=="周一"){
                            domId+="1"
                        }else if(prefix=="周二"){
                            domId+="2";
                        }else if(prefix=="周三"){
                            domId+="3";
                        }else if(prefix=="周四"){
                            domId+="4";
                        }else if(prefix=="周五"){
                            domId+="5";
                        }else if(prefix=="周六"){
                            domId+="6";
                        }else if(prefix=="周天"){
                            domId+="7";
                        }
                        document.getElementById(domId).innerText=str;
                    }
                }
            },
            displayClear:function (){
                let nullStr="";
                for(let i =1;i<=6;i++){
                    for(let j=1;j<=7;j++){
                        let domId=nullStr+i+j;
                        document.getElementById(domId).innerText = "";
                    }
                }
            }
        }
    });
}