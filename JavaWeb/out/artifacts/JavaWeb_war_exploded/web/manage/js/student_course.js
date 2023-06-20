window.onload=function(){
    let vue=new Vue({
        "el":"#form",
        data:{
            pageNumber:1,
            student_courseList:null,
            student_courseColName:["学生ID","课程ID","成绩","选项"],
        },
        methods:{
            checkQueryStudent_course:function (){
                vue.pageNumber=1;
                vue.student_courseList=null;
                vue.queryStudent_course();
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
                vue.queryStudent_course();
            },
            pageNumberADD:function (){
                if(this.getJSONLen(vue.student_courseList)<20){
                    ;
                }else{
                    vue.pageNumber++;
                }
                vue.queryStudent_course();
            },
            queryStudent_course:function (){
                vue.student_courseList=null;
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"queryStudent_course",
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

                        vue.student_courseList=jsonList;
                    })
                    .catch(function (value){

                    });
            },
            deleteStudent_course:function (student_id,course_id,score){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"deleteStudent_course",
                        student_id:student_id,
                        course_id:course_id,
                        score:score,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryStudent_course();
                    })
                    .catch(function (value){

                    });
            },
            addStudent_course:function (student_id,course_id,score){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"addStudent_course",
                        student_id:student_id,
                        course_id:course_id,
                        score:score,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryStudent_course();
                    })
                    .catch(function (value){

                    });
            },
            checkAddStudent_course:function (){
                let student_id = document.getElementById("student_id").value;
                let course_id = document.getElementById("course_id").value;
                let score = document.getElementById("score").value;
                vue.addStudent_course(student_id,course_id,score);
            }
        }
    });
    vue.checkQueryStudent_course();
}