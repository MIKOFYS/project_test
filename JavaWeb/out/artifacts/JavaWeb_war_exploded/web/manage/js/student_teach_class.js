window.onload=function(){
    let vue=new Vue({
        "el":"#form",
        data:{
            pageNumber:1,
            student_teach_classList:null,
            student_teach_classColName:["学生ID","教学班ID","选项"],
        },
        methods:{
            checkQueryStudent_teach_class:function (){
                vue.pageNumber=1;
                vue.student_teach_classList=null;
                vue.queryStudent_teach_class();
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
                vue.queryStudent_teach_class();
            },
            pageNumberADD:function (){
                if(this.getJSONLen(vue.student_teach_classList)<20){
                    ;
                }else{
                    vue.pageNumber++;
                }
                vue.queryStudent_teach_class();
            },
            queryStudent_teach_class:function (){
                vue.student_teach_classList=null;
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"queryStudent_teach_class",
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

                        vue.student_teach_classList=jsonList;
                    })
                    .catch(function (value){

                    });
            },
            deleteStudent_teach_class:function (student_id,teach_class_id){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"deleteStudent_teach_class",
                        student_id:student_id,
                        teach_class_id:teach_class_id,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryStudent_teach_class();
                    })
                    .catch(function (value){

                    });
            },
            addStudent_teach_class:function (student_id,teach_class_id){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"addStudent_teach_class",
                        student_id:student_id,
                        teach_class_id:teach_class_id,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryStudent_teach_class();
                    })
                    .catch(function (value){

                    });
            },
            checkAddStudent_teach_class:function (){
                let student_id = document.getElementById("student_id").value;
                let teach_class_id = document.getElementById("teach_class_id").value;
                vue.addStudent_teach_class(student_id,teach_class_id);
            }
        }
    });
    vue.checkQueryStudent_teach_class();
}