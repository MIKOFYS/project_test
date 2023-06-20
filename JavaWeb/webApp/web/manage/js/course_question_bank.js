window.onload=function(){
    let vue=new Vue({
        "el":"#form",
        data:{
            pageNumber:1,
            course_question_bankList:null,
            course_question_bankColName:["课程ID","试卷ID","选项"],
        },
        methods:{
            checkQueryCourse_question_bank:function (){
                vue.pageNumber=1;
                vue.course_question_bankList=null;
                vue.queryCourse_question_bank();
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
                vue.queryCourse_question_bank();
            },
            pageNumberADD:function (){
                if(this.getJSONLen(vue.course_question_bankList)<20){
                    ;
                }else{
                    vue.pageNumber++;
                }
                vue.queryCourse_question_bank();
            },
            queryCourse_question_bank:function (){
                vue.course_question_bankList=null;
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"queryCourse_question_bank",
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

                        vue.course_question_bankList=jsonList;
                    })
                    .catch(function (value){

                    });
            },
            deleteCourse_question_bank:function (course_id,paper_id){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"deleteCourse_question_bank",
                        course_id:course_id,
                        paper_id:paper_id,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryCourse_question_bank();
                    })
                    .catch(function (value){

                    });
            },
            addCourse_question_bank:function (course_id,paper_id){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"addCourse_question_bank",
                        course_id:course_id,
                        paper_id:paper_id,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryCourse_question_bank();
                    })
                    .catch(function (value){

                    });
            },
            checkAddCourse_question_bank:function (){
                let course_id = document.getElementById("course_id").value;
                let paper_id = document.getElementById("paper_id").value;
                vue.addCourse_question_bank(course_id,paper_id);
            }
        }
    });
    vue.checkQueryCourse_question_bank();
}