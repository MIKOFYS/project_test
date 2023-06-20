window.onload=function(){
    let vue=new Vue({
        "el":"#form",
        data:{
            manageColName1:["student","teacher","classPage","course","course_question_bank","dept","grade","major"],
            manageColName2:["paper_bank","paper_in_teach_class","student_course","student_have_taken_course","student_selected_course","student_teach_class","teach_class","teach_class_time_room"],
        },
        methods:{
            queryManageItem:function (tableName){
                window.location.href="manageController.do?operateParameter="+tableName;
            },
        }
    });
}