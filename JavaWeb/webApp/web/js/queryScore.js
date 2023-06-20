window.onload=function(){
    let vue=new Vue({
        "el":"#form",
        data:{
            studentCourseScoreInfoList:null,
            studentCourseScoreInfoColName:["课程编号","课程名","教学班编号","授课老师","成绩"],
        },
        methods:{
            queryStudentScore:function (){
                axios({
                    method: "POST",
                    url:"queryScoreController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"queryStudentScore"
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        let jsonList=JSON.parse(JSON.stringify(data));
                        vue.studentCourseScoreInfoList = jsonList;
                        //alert(jsonList)
                    })
                    .catch(function (value){

                    });
            },
        }
    });
    vue.queryStudentScore();
}