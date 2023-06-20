window.onload=function(){
    let vue=new Vue({
        "el":"#form",
        data:{
            operator:"queryTeach_class",
            teach_classList:null,
            paper_in_teach_class_infoList:null,
            student_courseList:null,
            teach_class_id:null,
            score:null,
            teach_classColName:["教学班","课程名","检查做题记录","评课程总成绩"],
            paper_in_teach_class_infoColName:["学号","学生姓名","试卷ID","分数","提交时间","下载浏览","评分"],
            student_courseColName:["学号","姓名","成绩","评分"]
        },
        methods: {
            queryTeach_class: function () {
                vue.operator = "queryTeach_class";
                axios({
                    method: "POST",
                    url: "scoreController.do",
                    contentType: "charset=utf-8",
                    params: {
                        operateParameter: "queryTeach_class"
                    }
                })
                    .then(function (value) {
                        let data = value.data;
                        let jsonList = JSON.parse(JSON.stringify(data));
                        vue.teach_classList = jsonList;
                    })
                    .catch(function (value) {

                    });
            },
            queryPaper_in_teach_class_info: function (teach_class_id) {
                vue.operator = "queryPaper_in_teach_class_info";
                vue.teach_class_id = teach_class_id;
                axios({
                    method: "POST",
                    url: "scoreController.do",
                    contentType: "charset=utf-8",
                    params: {
                        operateParameter: "queryPaper_in_teach_class_info",
                        teach_class_id: teach_class_id
                    }
                })
                    .then(function (value) {
                        let data = value.data;
                        let jsonList = JSON.parse(JSON.stringify(data));
                        vue.paper_in_teach_class_infoList = jsonList;
                    })
                    .catch(function (value) {

                    });
            },
            queryStudent_course: function (teach_class_id) {
                vue.operator = "queryStudent_course";
                vue.teach_class_id = teach_class_id;
                axios({
                    method: "POST",
                    url: "scoreController.do",
                    contentType: "charset=utf-8",
                    params: {
                        operateParameter: "queryStudent_course",
                        teach_class_id: teach_class_id
                    }
                })
                    .then(function (value) {
                        let data = value.data;
                        let jsonList = JSON.parse(JSON.stringify(data));
                        vue.student_courseList = jsonList;
                    })
                    .catch(function (value) {

                    });
            }, checkStudentCourseScore: function (student_id, course_id) {
                if (vue.score < 0 || vue.score > 100) {
                    vue.score = null;
                    return;
                }
                axios({
                    method: "POST",
                    url: "scoreController.do",
                    contentType: "charset=utf-8",
                    params: {
                        operateParameter: "checkStudentCourseScore",
                        student_id: student_id,
                        course_id: course_id,
                        score: vue.score
                    }
                })
                    .then(function (value) {
                        let data = value.data;
                        alert(data.toString())
                        vue.queryStudent_course(vue.teach_class_id);
                    })
                    .catch(function (value) {

                    });
            },
            checkPaper: function (student_id, paper_id) {
                if (vue.score < 0 || vue.score > 100) {
                    vue.score = null;
                    return;
                }
                axios({
                    method: "POST",
                    url: "scoreController.do",
                    contentType: "charset=utf-8",
                    params: {
                        operateParameter: "checkPaper",
                        teach_class_id: vue.teach_class_id,
                        student_id: student_id,
                        paper_id: paper_id,
                        score: vue.score
                    }
                })
                    .then(function (value) {
                        let data = value.data;
                        alert(data.toString())
                        vue.queryPaper_in_teach_class_info(vue.teach_class_id);
                    })
                    .catch(function (value) {

                    });
            },
            back: function () {
                vue.queryTeach_class();
            },
            base64ToBlob: function (urlData, type) {
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
            downloadExportFile: function (blob, fileName, fileType) {
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
            downloadFile: function (base64WithPrefix, fileType, filePrefixName) {
                const blob = vue.base64ToBlob(base64WithPrefix, fileType) // 转成blob对象
                vue.downloadExportFile(blob, filePrefixName, fileType) // 下载文件
            },
            downloadPaper: function (student_id, paper_id) {
                axios({
                    method: "POST",
                    url: "scoreController.do",
                    contentType: "charset=utf-8",
                    params: {
                        operateParameter: "downloadPaper",
                        teach_class_id: vue.teach_class_id,
                        student_id: student_id,
                        paper_id: paper_id,
                    }
                })
                    .then(function (value) {
                        let data = value.data;
                        if (data.startsWith("result:")) {
                            alert(data);
                            return;
                        }

                        let fileType = null;
                        if (data.startsWith("data:application/msword;base64,")) {
                            fileType = "doc";
                        } else if (data.startsWith("data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64,")) {
                            fileType = "docx";
                        } else if (data.startsWith("data:application/pdf;base64,")) {
                            fileType = "pdf";
                        } else if (data.startsWith("data:image/png;base64,")) {
                            fileType = "png";
                        } else if (data.startsWith("data:image/jpeg;base64,")) {
                            fileType = "jpg";
                        } else if (data.startsWith("data:image/gif;base64,")) {
                            fileType = "gif";
                        }
                        vue.downloadFile(data, fileType, vue.teach_class_id + "_" + student_id + "_" + paper_id);
                    })
                    .catch(function (value) {

                    });
            }
        }
    });
    vue.queryTeach_class();
}