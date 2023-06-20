window.onload=function(){
    let vue=new Vue({
        "el":"#form",
        data:{
            couldBeSelectedCourseList:null,
            haveSelectedCourseList:null,
            couldBeSelectedCourseColName:["course_id","课程名","学分","学时","选择"],
            haveSelectedCourseColName:["course_id","课程名","学分","学时","撤销"]
        },
        methods:{
            main:function (){
                vue.queryCouldBeSelectedCourse();
                //alert(vue.couldBeSelectedCourseList);
                vue.queryHaveSelectedCourse();
            },
            queryCouldBeSelectedCourse:function (){
                axios({
                    method: "POST",
                    url:"selectCourseController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"couldBeSelectedCourse"
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        let jsonList=JSON.parse(JSON.stringify(data));
                        vue.couldBeSelectedCourseList=jsonList;
                        //alert(vue.couldBeSelectedCourseList);
                    })
                    .catch(function (value){

                    });
            },
            queryHaveSelectedCourse:function (){
                axios({
                    method: "POST",
                    url:"selectCourseController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"haveSelectedCourse"
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        let jsonList=JSON.parse(JSON.stringify(data));
                        vue.haveSelectedCourseList=jsonList;
                    })
                    .catch(function (value){

                    });
            },
            selectCourse:function (course_id){
                axios({
                    method: "POST",
                    url:"selectCourseController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"selectCourse",
                        course_id:course_id
                    }
                })
                    .then(function (value) {
                        let data=value.data;
                        alert(data.toString())
                        vue.queryCouldBeSelectedCourse();
                        vue.queryHaveSelectedCourse();
                    })
                    .catch(function (value){

                    });
            },
            deleteSelectedCourse:function (course_id){
                axios({
                    method: "POST",
                    url:"selectCourseController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"deleteSelectedCourse",
                        course_id:course_id
                    }
                })
                    .then(function (value) {
                        let data=value.data;
                        alert(data.toString())
                        vue.queryCouldBeSelectedCourse();
                        vue.queryHaveSelectedCourse();
                    })
                    .catch(function (value){

                    });
            }
        }
    });
    vue.main();
}