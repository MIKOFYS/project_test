window.onload=function(){
    let vue=new Vue({
        "el":"#form",
        data:{
            pageNumber:1,
            paper_in_teach_classList:null,
            paper_in_teach_classColName:["教学班ID","学生ID","试卷ID","成绩","试卷路径","时间","选项"],
        },
        methods:{
            checkQueryPaper_in_teach_class:function (){
                vue.pageNumber=1;
                vue.paper_in_teach_classList=null;
                vue.queryPaper_in_teach_class();
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
                vue.queryPaper_in_teach_class();
            },
            pageNumberADD:function (){
                if(this.getJSONLen(vue.paper_in_teach_classList)<20){
                    ;
                }else{
                    vue.pageNumber++;
                }
                vue.queryPaper_in_teach_class();
            },
            queryPaper_in_teach_class:function (){
                vue.paper_in_teach_classList=null;
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"queryPaper_in_teach_class",
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

                        vue.paper_in_teach_classList=jsonList;
                    })
                    .catch(function (value){

                    });
            },
            deletePaper_in_teach_class:function (teach_class_id,student_id,paper_id,score,directory,time){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"deletePaper_in_teach_class",
                        teach_class_id:teach_class_id,
                        student_id:student_id,
                        paper_id:paper_id,
                        score:score,
                        directory:directory,
                        time:time,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryPaper_in_teach_class();
                    })
                    .catch(function (value){

                    });
            },
            addPaper_in_teach_class1:function (teach_class_id1,student_id1,paper_id1,score1,directory1,time1){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"addPaper_in_teach_class1",
                        teach_class_id1:teach_class_id1,
                        student_id1:student_id1,
                        paper_id1:paper_id1,
                        score1:score1,
                        directory1:directory1,
                        time1:time1,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryPaper_in_teach_class();
                    })
                    .catch(function (value){

                    });
            },
            checkAddPaper_in_teach_class1:function (){
                let teach_class_id1 = document.getElementById("teach_class_id1").value;
                let student_id1 = document.getElementById("student_id1").value;
                let paper_id1 = document.getElementById("paper_id1").value;
                let score1 = document.getElementById("score1").value;
                let directory1 = document.getElementById("directory1").value;
                let time1 = document.getElementById("time1").value;
                vue.addPaper_in_teach_class1(teach_class_id1,student_id1,paper_id1,score1,directory1,time1);
            },
            addPaper_in_teach_class2:function (teach_class_id2,student_id2,paper_id2,score2,directory2,time2){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"addPaper_in_teach_class2",
                        teach_class_id2:teach_class_id2,
                        student_id2:student_id2,
                        paper_id2:paper_id2,
                        score2:score2,
                        directory2:directory2,
                        time2:time2,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryPaper_in_teach_class();
                    })
                    .catch(function (value){

                    });
            },
            checkAddPaper_in_teach_class2:function (){
                let teach_class_id2 = document.getElementById("teach_class_id2").value;
                let student_id2 = document.getElementById("student_id2").value;
                let paper_id2 = document.getElementById("paper_id2").value;
                let score2 = document.getElementById("score2").value;
                let directory2 = document.getElementById("directory2");//注意！！！！！！！！！！！！！！！！！！！！！！！
                let time2 = document.getElementById("time2").value;

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
                        vue.addPaper_in_teach_class2(teach_class_id2,student_id2,paper_id2,score2,base64String,time2);
                        //alert(base64String);
                    };
                }
            }
        }
    });
    vue.checkQueryPaper_in_teach_class();
}