window.onload=function(){
    let vue=new Vue({
        "el":"#form",
        data:{
            operator:"queryCourse",
            teach_class_id:null,
            course_id:null,
            courseList:null,
            paperList:null,
            courseColName:["课程名","选择"],
            paperColName:["试卷ID","下载试卷","上传试卷"]
        },
        methods:{
            queryCourse:function (){
                vue.operator="queryCourse";
                axios({
                    method: "POST",
                    url:"paperController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:vue.operator,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        vue.courseList=JSON.parse(JSON.stringify(data));
                    })
                    .catch(function (value){

                    });
            },
            queryPaper:function (course_id,teach_class_id){
                vue.operator="queryPaper";
                vue.teach_class_id=teach_class_id;
                vue.course_id=course_id;
                axios({
                    method: "POST",
                    url:"paperController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:vue.operator,
                        course_id:course_id
                        //teach_class_id:vue.teach_class_id
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        vue.paperList=JSON.parse(JSON.stringify(data));
                    })
                    .catch(function (value){

                    });
            },
            base64ToBlob:function (urlData, type){
                const arr = urlData.split(',')
                const array = arr[0].match(/:(.*?);/)
                const mime = (array && array.length > 1 ? array[1] : type) || type
                // 去掉url的头，并转化为byte
                const bytes = window.atob(arr[1])
                // 处理异常,将ascii码小于0的转换为大于0
                const ab = new ArrayBuffer(bytes.length)
                // 生成视图（直接针对内存）：8位无符号整数，长度1个字节
                const ia = new Uint8Array(ab)
                for (let i = 0; i < bytes.length; i++) {
                    ia[i] = bytes.charCodeAt(i)
                }
                return new Blob([ab], {
                    type: mime
                })
            },
            downloadExportFile:function (blob, fileName, fileType){
                const downloadElement = document.createElement('a')
                let href = blob
                if (typeof blob === 'string') {
                    downloadElement.target = '_blank'
                } else {
                    href = window.URL.createObjectURL(blob) // 创建下载的链接
                }
                downloadElement.href = href
                downloadElement.download = fileName + '.' + fileType // 下载后文件名
                document.body.appendChild(downloadElement)
                downloadElement.click() // 触发点击下载
                document.body.removeChild(downloadElement) // 下载完成移除元素
                if (typeof blob !== 'string') {
                    window.URL.revokeObjectURL(href) // 释放掉blob对象
                }
            },
            downloadFile:function (base64WithPrefix,fileType,filePrefixName){
                const blob = vue.base64ToBlob(base64WithPrefix, fileType) // 转成blob对象
                vue.downloadExportFile(blob, filePrefixName, fileType) // 下载文件
            },
            downloadPaper:function (paper_id){
                axios({
                    method: "POST",
                    url:"paperController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"downloadPaper",
                        paper_id: paper_id,
                    }
                })
                    .then(function (value) {
                        let data = value.data;
                        if(data.startsWith("result:")) {
                            alert(data);
                            return;
                        }

                        let fileType = null;
                        if(data.startsWith("data:application/msword;base64,")){
                            fileType = "doc";
                        }else if(data.startsWith("data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64,")){
                            fileType = "docx";
                        }else if(data.startsWith("data:application/pdf;base64,")){
                            fileType = "pdf";
                        }else if(data.startsWith("data:image/png;base64,")){
                            fileType = "png";
                        }else if(data.startsWith("data:image/jpeg;base64,")){
                            fileType = "jpg";
                        }else if(data.startsWith("data:image/gif;base64,")){
                            fileType = "gif";
                        }
                        vue.downloadFile(data,fileType,paper_id);
                    })
                    .catch(function (value){

                    });
            },
            uploadPaper:function (paper_id){
                let parames = new Array();
                parames.push({  "operateParameter" : "uploadPaper"});
                parames.push({  "teach_class_id": vue.teach_class_id});
                parames.push({  "paper_id": paper_id});
                window.localStorage.setItem("parames",JSON.stringify(parames))
                window.location.href="paperController.do?operateParameter=uploadPage";
            }
        }
    });
    vue.queryCourse();
}