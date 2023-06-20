window.onload=function(){
    let vue=new Vue({
        "el":"#form",
        data:{
            pageNumber:1,
            courseList:null,
            courseColName:["课程ID","课程名","学分","学时","选项"],
        },
        methods:{
            checkQueryCourse:function (){
                vue.pageNumber=1;
                vue.courseList=null;
                vue.queryCourse();
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
                vue.queryCourse();
            },
            pageNumberADD:function (){
                if(this.getJSONLen(vue.courseList)<20){
                    ;
                }else{
                    vue.pageNumber++;
                }
                vue.queryCourse();
            },
            queryCourse:function (){
                vue.courseList=null;
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"queryCourse",
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

                        vue.courseList=jsonList;
                    })
                    .catch(function (value){

                    });
            },
            deleteCourse:function (course_id,name,credit,period){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"deleteCourse",
                        course_id:course_id,
                        name:name,
                        credit:credit,
                        period:period,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryCourse();
                    })
                    .catch(function (value){

                    });
            },
            addCourse:function (course_id,name,credit,period){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"addCourse",
                        course_id:course_id,
                        name:name,
                        credit:credit,
                        period:period,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryCourse();
                    })
                    .catch(function (value){

                    });
            },
            checkAddCourse:function (){
                let course_id = document.getElementById("course_id").value;
                let name = document.getElementById("name").value;
                let credit = document.getElementById("credit").value;
                let period = document.getElementById("period").value;
                vue.addCourse(course_id,name,credit,period);
            }
        }
    });
    vue.checkQueryCourse();
}