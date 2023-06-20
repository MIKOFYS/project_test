window.onload=function(){
    let vue=new Vue({
        "el":"#form",
        data:{
            pageNumber:1,
            classPageList:null,
            classPageColName:["班级","年级","专业ID","选项"],
        },
        methods:{
            checkQueryClass:function (){
                vue.pageNumber=1;
                vue.classPageList=null;
                vue.queryClass();
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
                vue.queryClass();
            },
            pageNumberADD:function (){
                if(this.getJSONLen(vue.classPageList)<20){
                    ;
                }else{
                    vue.pageNumber++;
                }
                vue.queryClass();
            },
            queryClass:function (){
                vue.classPageList=null;
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"queryClass",
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

                        vue.classPageList=jsonList;
                    })
                    .catch(function (value){

                    });
            },
            deleteClass:function (class_id,grade_id,major_id){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"deleteClass",
                        class_id:class_id,
                        grade_id:grade_id,
                        major_id:major_id,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryClass();
                    })
                    .catch(function (value){

                    });
            },
            addClass:function (class_id,grade_id,major_id){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"addClass",
                        class_id:class_id,
                        grade_id:grade_id,
                        major_id:major_id,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryClass();
                    })
                    .catch(function (value){

                    });
            },
            checkAddClass:function (){
                let class_id = document.getElementById("class_id").value;
                let grade_id = document.getElementById("grade_id").value;
                let major_id = document.getElementById("major_id").value;
                vue.addClass(class_id,grade_id,major_id);
            }
        }
    });
    vue.checkQueryClass();
}