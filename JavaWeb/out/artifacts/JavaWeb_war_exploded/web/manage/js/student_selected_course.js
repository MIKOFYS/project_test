window.onload=function(){
    let vue=new Vue({
        "el":"#form",
        data:{
            pageNumber:1,
            student_selected_courseList:null,
            student_selected_courseColName:["学生ID","课程ID","选项"],
        },
        methods:{
            checkQueryStudent_selected_course:function (){
                vue.pageNumber=1;
                vue.student_selected_courseList=null;
                vue.queryStudent_selected_course();
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
                vue.queryStudent_selected_course();
            },
            pageNumberADD:function (){
                if(this.getJSONLen(vue.student_selected_courseList)<20){
                    ;
                }else{
                    vue.pageNumber++;
                }
                vue.queryStudent_selected_course();
            },
            queryStudent_selected_course:function (){
                vue.student_selected_courseList=null;
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"queryStudent_selected_course",
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

                        vue.student_selected_courseList=jsonList;
                    })
                    .catch(function (value){

                    });
            },
            deleteStudent_selected_course:function (student_id,course_id){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"deleteStudent_selected_course",
                        student_id:student_id,
                        course_id:course_id,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryStudent_selected_course();
                    })
                    .catch(function (value){

                    });
            },
            addStudent_selected_course:function (student_id,course_id){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"addStudent_selected_course",
                        student_id:student_id,
                        course_id:course_id,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryStudent_selected_course();
                    })
                    .catch(function (value){

                    });
            },
            checkAddStudent_selected_course:function (){
                let student_id = document.getElementById("student_id").value;
                let course_id = document.getElementById("course_id").value;
                vue.addStudent_selected_course(student_id,course_id);
            }
        }
    });
    vue.checkQueryStudent_selected_course();
}