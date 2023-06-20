window.onload=function(){
    let vue=new Vue({
        "el":"#form",
        data:{
            pageNumber:1,
            student_have_taken_courseList:null,
            student_have_taken_courseColName:["学生ID","课程ID","选项"],
        },
        methods:{
            checkQueryStudent_have_taken_course:function (){
                vue.pageNumber=1;
                vue.student_have_taken_courseList=null;
                vue.queryStudent_have_taken_course();
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
                vue.queryStudent_have_taken_course();
            },
            pageNumberADD:function (){
                if(this.getJSONLen(vue.student_have_taken_courseList)<20){
                    ;
                }else{
                    vue.pageNumber++;
                }
                vue.queryStudent_have_taken_course();
            },
            queryStudent_have_taken_course:function (){
                vue.student_have_taken_courseList=null;
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"queryStudent_have_taken_course",
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

                        vue.student_have_taken_courseList=jsonList;
                    })
                    .catch(function (value){

                    });
            },
            deleteStudent_have_taken_course:function (student_id,course_id){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"deleteStudent_have_taken_course",
                        student_id:student_id,
                        course_id:course_id,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryStudent_have_taken_course();
                    })
                    .catch(function (value){

                    });
            },
            addStudent_have_taken_course:function (student_id,course_id){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"addStudent_have_taken_course",
                        student_id:student_id,
                        course_id:course_id,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryStudent_have_taken_course();
                    })
                    .catch(function (value){

                    });
            },
            checkAddStudent_have_taken_course:function (){
                let student_id = document.getElementById("student_id").value;
                let course_id = document.getElementById("course_id").value;
                vue.addStudent_have_taken_course(student_id,course_id);
            }
        }
    });
    vue.checkQueryStudent_have_taken_course();
}