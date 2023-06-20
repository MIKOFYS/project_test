window.onload=function(){
    let vue=new Vue({
        "el":"#form",
        data:{
            pageNumber:1,
            paper_bankList:null,
            paper_bankColName:["试卷ID","文件路径","选项"],
        },
        methods:{
            checkQueryPaper_bank:function (){
                vue.pageNumber=1;
                vue.paper_bankList=null;
                vue.queryPaper_bank();
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
                vue.queryPaper_bank();
            },
            pageNumberADD:function (){
                if(this.getJSONLen(vue.paper_bankList)<20){
                    ;
                }else{
                    vue.pageNumber++;
                }
                vue.queryPaper_bank();
            },
            queryPaper_bank:function (){
                vue.paper_bankList=null;
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"queryPaper_bank",
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

                        vue.paper_bankList=jsonList;
                    })
                    .catch(function (value){

                    });
            },
            deletePaper_bank:function (paper_id,directory){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"deletePaper_bank",
                        paper_id:paper_id,
                        directory:directory,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryPaper_bank();
                    })
                    .catch(function (value){

                    });
            },
            addPaper_bank1:function (paper_id1,directory1){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"addPaper_bank1",
                        paper_id1:paper_id1,
                        directory1:directory1,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryPaper_bank();
                    })
                    .catch(function (value){

                    });
            },
            checkAddPaper_bank1:function (){
                let paper_id1 = document.getElementById("paper_id1").value;
                let directory1 = document.getElementById("directory1").value;
                vue.addPaper_bank1(paper_id1,directory1);
            },
            addPaper_bank2:function (paper_id2,directory2){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"addPaper_bank2",
                        paper_id2:paper_id2,
                        directory2:directory2,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryPaper_bank();
                    })
                    .catch(function (value){

                    });
            },
            checkAddPaper_bank2:function (){
                let paper_id2 = document.getElementById("paper_id2").value;
                let directory2 = document.getElementById("directory2");//注意！！！！！！！！！！！！！！！！！！！！！！！

                let filePath = directory2.value;//文件路径
                let fileName = directory2.files[0].name;//上传的文件名称
                let file = directory2.files[0];//上传的文件
                let extn = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();//文件后缀

                if(file == null || (extn != "docx" && extn != "doc" && extn != "pdf")){
                    alert("请上传docx,doc,pdf文件");
                    return ;
                }

                //文件base64string获取
                let base64String = null;
                if (window.FileReader) {
                    let reader = new FileReader();
                    reader.readAsDataURL(file);
                    //监听文件读取结束后事件
                    reader.onloadend = function (e) {
                        base64String = e.target.result;
                        vue.addPaper_bank2(paper_id2,base64String);
                        //alert(base64String);
                    };
                }
            }
        }
    });
    vue.checkQueryPaper_bank();
}