window.onload=function(){
    let vue=new Vue({
        "el":"#form",
        data:{
            pageNumber:1,
            majorList:null,
            majorColName:["专业ID","专业名","学院ID","选项"],
        },
        methods:{
            checkQueryMajor:function (){
                vue.pageNumber=1;
                vue.majorList=null;
                vue.queryMajor();
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
                vue.queryMajor();
            },
            pageNumberADD:function (){
                if(this.getJSONLen(vue.majorList)<20){
                    ;
                }else{
                    vue.pageNumber++;
                }
                vue.queryMajor();
            },
            queryMajor:function (){
                vue.majorList=null;
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"queryMajor",
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

                        vue.majorList=jsonList;
                    })
                    .catch(function (value){

                    });
            },
            deleteMajor:function (major_id,major_name,dept_id){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"deleteMajor",
                        major_id:major_id,
                        major_name:major_name,
                        dept_id:dept_id,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryMajor();
                    })
                    .catch(function (value){

                    });
            },
            addMajor:function (major_id,major_name,dept_id){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"addMajor",
                        major_id:major_id,
                        major_name:major_name,
                        dept_id:dept_id,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryMajor();
                    })
                    .catch(function (value){

                    });
            },
            checkAddMajor:function (){
                let major_id = document.getElementById("major_id").value;
                let major_name = document.getElementById("major_name").value;
                let dept_id = document.getElementById("dept_id").value;
                vue.addMajor(major_id,major_name,dept_id);
            }
        }
    });
    vue.checkQueryMajor();
}