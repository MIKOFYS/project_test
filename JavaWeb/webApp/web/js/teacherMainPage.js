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
            transScore:function (){
                window.location.href="scoreController.do";
            }
        }
    });
}