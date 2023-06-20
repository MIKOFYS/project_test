window.onload=function(){
    let vue=new Vue({
        "el":"#form",
        data:{
            pageNumber:1,
            gradeList:null,
            gradeColName:["年级ID","选项"],
        },
        methods:{
            checkQueryGrade:function (){
                vue.pageNumber=1;
                vue.gradeList=null;
                vue.queryGrade();
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
                vue.queryGrade();
            },
            pageNumberADD:function (){
                if(this.getJSONLen(vue.gradeList)<20){
                    ;
                }else{
                    vue.pageNumber++;
                }
                vue.queryGrade();
            },
            queryGrade:function (){
                vue.gradeList=null;
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"queryGrade",
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

                        vue.gradeList=jsonList;
                    })
                    .catch(function (value){

                    });
            },
            deleteGrade:function (grade_id){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"deleteGrade",
                        grade_id:grade_id,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryGrade();
                    })
                    .catch(function (value){

                    });
            },
            addGrade:function (grade_id){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"addGrade",
                        grade_id:grade_id,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryGrade();
                    })
                    .catch(function (value){

                    });
            },
            checkAddGrade:function (){
                let grade_id = document.getElementById("grade_id").value;
                vue.addGrade(grade_id);
            }
        }
    });
    vue.checkQueryGrade();
}