window.onload=function(){
    let vue=new Vue({
        "el":"#form",
        data:{
            pageNumber:1,
            deptList:null,
            deptColName:["学院ID","学院名","选项"],
        },
        methods:{
            checkQueryDept:function (){
                vue.pageNumber=1;
                vue.deptList=null;
                vue.queryDept();
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
                vue.queryDept();
            },
            pageNumberADD:function (){
                if(this.getJSONLen(vue.deptList)<20){
                    ;
                }else{
                    vue.pageNumber++;
                }
                vue.queryDept();
            },
            queryDept:function (){
                vue.deptList=null;
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"queryDept",
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

                        vue.deptList=jsonList;
                    })
                    .catch(function (value){

                    });
            },
            deleteDept:function (dept_id,dept_name){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"deleteDept",
                        dept_id:dept_id,
                        dept_name:dept_name,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryDept();
                    })
                    .catch(function (value){

                    });
            },
            addDept:function (dept_id,dept_name){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"addDept",
                        dept_id:dept_id,
                        dept_name:dept_name,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryDept();
                    })
                    .catch(function (value){

                    });
            },
            checkAddDept:function (){
                let dept_id = document.getElementById("dept_id").value;
                let dept_name = document.getElementById("dept_name").value;
                vue.addDept(dept_id,dept_name);
            }
        }
    });
    vue.checkQueryDept();
}