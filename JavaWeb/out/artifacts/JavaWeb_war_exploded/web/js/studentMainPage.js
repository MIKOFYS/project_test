window.onload=function(){
    let vue=new Vue({
        "el":"#DIV",
        methods:{
            transQueryPersonInfo:function (){
                window.location.href="queryPersonInfoController.do";
            },
            transQuery:function (){
                window.location.href="queryController.do";
            },
            transQueryCourse:function (){
                window.location.href="queryController.do?operateParameter=queryCourse";
            },
            transPaper:function (){
                window.location.href="paperController.do";
            },
            transSelectCourse:function (){
                window.location.href="selectCourseController.do";
            },
            transQueryScore:function (){
                window.location.href="queryScoreController.do";
            }
        }
    });
}