window.onload=function(){
    let vue=new Vue({
        "el":"#form",
        data:{
            operator:"queryStudent",
            keyword:null,
            pageNumber:1,
            studentList:null,
            teacherList:null,
            titleInfo:"学生信息",
            studentColName:["姓名","性别","学生ID","班级","年级","专业","学院"],
            teacherColName:["姓名","性别","老师ID","学院"]
        },
        methods:{
            queryStudent:function (){
                vue.titleInfo="学生信息";
                vue.operator="queryStudent";
                vue.pageNumber=1;
                vue.studentList=null;
                vue.teacherList=null;
            },
            queryTeacher:function (){
                vue.titleInfo="老师信息";
                vue.operator="queryTeacher";
                vue.pageNumber=1;
                vue.studentList=null;
                vue.teacherList=null;
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
                vue.query();
            },
            pageNumberADD:function (){
                if(vue.operator=="queryStudent"){
                    if(this.getJSONLen(vue.studentList)<10){
                        ;
                    }else{
                        vue.pageNumber++;
                    }
                }else {
                    if(this.getJSONLen(vue.teacherList)<10){
                        ;
                    }else{
                        vue.pageNumber++;
                    }
                }
                this.query();
            },
            query:function (){
                vue.studentList=null;
                vue.teacherList=null;
                axios({
                    method: "POST",
                    url:"queryController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:vue.operator,
                        keyword: vue.keyword,
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

                        if(vue.operator=="queryStudent"){
                            vue.studentList=jsonList;
                        }else{
                            vue.teacherList=jsonList;
                        }
                    })
                    .catch(function (value){

                    });
            }
        }
    });
}