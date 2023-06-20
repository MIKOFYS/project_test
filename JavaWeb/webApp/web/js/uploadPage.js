window.onload=function(){
    let vue=new Vue({
        "el":"#DIV",
        data:{
            student_id:null,
        },
        methods:{
            getParames:function (){
                let parames=window.localStorage.getItem("parames");
                parames=JSON.parse(parames)
                return parames
            },
            queryStudent_id: function () {
                axios({
                    method: "POST",
                    url: "paperController.do",
                    contentType: "charset=utf-8",
                    params: {
                        operateParameter: "queryStudent_id",
                    }
                })
                    .then(function (value) {
                        let data = value.data;
                        vue.student_id = data;
                    })
                    .catch(function (value) {

                    });
            },
            checkUpload:function (){
                let student_id = vue.student_id;
                let parames=vue.getParames();
                let operateParameter=parames[0].operateParameter;
                let teach_class_id=parames[1].teach_class_id;
                let paper_id=parames[2].paper_id;

                let directory = document.getElementById("directory");//注意！！！！！！！！！！！！！！！！！！！！！！！
                let filePath = directory.value;//文件路径
                let fileName = directory.files[0].name;//上传的文件名称
                let file = directory.files[0];//上传的文件
                let extn = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();//文件后缀

                if(file == null || (extn != "doc" && extn != "docx" && extn != "pdf")){
                    alert("请上传doc,docx,pdf文件");
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
                        vue.upload(teach_class_id,student_id,paper_id,base64String);
                    };
                }
            },
            upload:function (teach_class_id,student_id,paper_id,directory){
                axios({
                    method: "POST",
                    url: "paperController.do",
                    contentType: "charset=utf-8",
                    params: {
                        operateParameter: "uploadPaper",
                        teach_class_id:teach_class_id,
                        student_id:student_id,
                        paper_id:paper_id,
                        directory:directory,
                    }
                })
                    .then(function (value) {
                        let data = value.data;
                        alert(data);
                    })
                    .catch(function (value) {

                    });
            },
        }
    });
    vue.queryStudent_id();
}