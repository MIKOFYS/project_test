window.onload=function(){
    let vue=new Vue({
        "el":"#form",
        data:{
            pageNumber:1,
            teach_classList:null,
            teach_classColName:["教学班ID","课程ID","老师ID","选项"],
        },
        methods:{
            checkQueryTeach_class:function (){
                vue.pageNumber=1;
                vue.teach_classList=null;
                vue.queryTeach_class();
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
                vue.queryTeach_class();
            },
            pageNumberADD:function (){
                if(this.getJSONLen(vue.teach_classList)<20){
                    ;
                }else{
                    vue.pageNumber++;
                }
                vue.queryTeach_class();
            },
            queryTeach_class:function (){
                vue.teach_classList=null;
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"queryTeach_class",
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

                        vue.teach_classList=jsonList;
                    })
                    .catch(function (value){

                    });
            },
            deleteTeach_class:function (teach_class_id,course_id,teacher_id){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"deleteTeach_class",
                        teach_class_id:teach_class_id,
                        course_id:course_id,
                        teacher_id:teacher_id,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryTeach_class();
                    })
                    .catch(function (value){

                    });
            },
            addTeach_class:function (teach_class_id,course_id,teacher_id){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"addTeach_class",
                        teach_class_id:teach_class_id,
                        course_id:course_id,
                        teacher_id:teacher_id,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryTeach_class();
                    })
                    .catch(function (value){

                    });
            },
            checkAddTeach_class:function (){
                let teach_class_id = document.getElementById("teach_class_id").value;
                let course_id = document.getElementById("course_id").value;
                let teacher_id = document.getElementById("teacher_id").value;
                vue.addTeach_class(teach_class_id,course_id,teacher_id);
            }
        }
    });
    vue.checkQueryTeach_class();
}