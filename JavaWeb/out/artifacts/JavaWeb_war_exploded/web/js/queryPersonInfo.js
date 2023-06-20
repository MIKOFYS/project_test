window.onload=function() {
    let vue = new Vue({
        "el": "#DIV",
        data: {
            operator: null,
            identity: null,
            studentJSON: null,
            teacherJSON: null,
            studentPicture:null,
            teacherPicture:null,
            phone:null,
            email:null
            //studentRow: ["头像", "学号", "班级", "年级", "专业", "学院", "姓名", "性别", "电话", "邮箱"]
            // teacherRowname:["头像","老师认证码","学院","姓名","性别","电话","邮箱"]
        },
        methods: {
            queryStudentPersonInfo: function () {
                vue.operator = "queryStudentPersonInfo";
                vue.studentJSON = null;
                vue.teacherJSON = null;
            },
            queryTeacherPersonInfo: function () {
                vue.operator = "queryTeacherPersonInfo";
                vue.studentJSON = null;
                vue.teacherJSON = null;
            },
            queryIdentity: function () {
                vue.operator = "queryIdentity";
                vue.studentJSON = null;
                vue.teacherJSON = null;

                axios({
                    method: "POST",
                    url: "queryPersonInfoController.do",
                    contentType: "charset=utf-8",
                    params: {
                        operateParameter: vue.operator,
                    }
                })
                    .then(function (value) {
                        let data = value.data;
                        vue.identity = JSON.parse(JSON.stringify(data));

                        if (vue.identity == "student") {
                            vue.queryStudentPersonInfo();
                            vue.query();
                        } else {
                            vue.queryTeacherPersonInfo();
                            vue.query();
                        }
                    })
                    .catch(function (value) {

                    });
            },
            query: function () {
                vue.studentJSON = null;
                axios({
                    method: "POST",
                    url: "queryPersonInfoController.do",
                    contentType: "charset=utf-8",
                    params: {
                        operateParameter: vue.operator,
                    }
                })
                    .then(function (value) {
                        let data = value.data;
                        let json = JSON.parse(JSON.stringify(data));
                        let picture = json.picture;

                        if (vue.identity == "student") {
                            vue.studentJSON = json;
                            vue.studentPicture = json.picture;
                            //document.getElementById("studentPicture").setAttribute('src', json.picture);
                        } else {
                            vue.teacherJSON = json;
                            vue.teacherPicture = json.picture;
                            //document.getElementById("teacherPicture").setAttribute('src', picture);
                        }
                    })
                    .catch(function (value) {

                    });
            },
            matchEmail:function (emailStr){
                let regExp = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
                return regExp.test(emailStr)
            },
            matchPhone:function (phoneStr){
                let regExp = /^[1][3,4,5,6.7,8,9][0-9]{9}$/;
                return regExp.test(phoneStr)
            },
            checkStudentPhone:function (student_id,phone){
                axios({
                    method: "POST",
                    url:"queryPersonInfoController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"checkStudentPhone",
                        student_id:student_id,
                        phone:phone,
                    }
                })
                    .then(function (value) {
                        let data=value.data;
                        alert(data.toString())
                        vue.query();
                    })
                    .catch(function (value){

                    });
            },
            checkStudentPhoneValue:function (student_id,phone){
                if(!vue.matchPhone(phone)){
                    alert("手机格式出错");
                    return ;
                }
                vue.checkStudentPhone(student_id,phone);
            },
            checkStudentEmail:function (student_id,email){
                axios({
                    method: "POST",
                    url:"queryPersonInfoController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"checkStudentEmail",
                        student_id:student_id,
                        email:email,
                    }
                })
                    .then(function (value) {
                        let data=value.data;
                        alert(data.toString())
                        vue.query();
                    })
                    .catch(function (value){

                    });
            },
            checkStudentEmailValue:function (student_id,email){
                if(!vue.matchEmail(email)){
                    alert("邮箱格式出错");
                    return ;
                }
                vue.checkStudentEmail(student_id,email);
            },
            checkStudentPictureValue:function (student_id){
                let studentImg = document.getElementById("studentImg");
                let filePath = studentImg.value;//文件路径
                let fileName = studentImg.files[0].name;//上传的文件名称
                let file = studentImg.files[0];//上传的文件
                let extn = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();//文件后缀

                if(file == null || (extn != "png" && extn != "jpg" && extn != "gif")){
                    alert("请上传图片");
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
                        vue.checkStudentPicture(student_id, base64String);
                        //alert(base64String);
                    };
                }
            },
            checkStudentPicture:function (student_id, base64String){
                axios({
                    method: "POST",
                    url:"queryPersonInfoController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"checkStudentPicture",
                        student_id:student_id,
                        picture:base64String,
                    }
                })
                    .then(function (value) {
                        let data=value.data;
                        alert(data.toString())
                        vue.query();
                    })
                    .catch(function (value){

                    });
            },
            checkTeacherPhone:function (teacher_id,phone){
                axios({
                    method: "POST",
                    url:"queryPersonInfoController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"checkTeacherPhone",
                        teacher_id:teacher_id,
                        phone:phone,
                    }
                })
                    .then(function (value) {
                        let data=value.data;
                        alert(data.toString())
                        vue.query();
                    })
                    .catch(function (value){

                    });
            },
            checkTeacherPhoneValue:function (teacher_id,phone){
                if(!vue.matchPhone(phone)){
                    alert("手机格式出错");
                    return ;
                }
                vue.checkTeacherPhone(teacher_id,phone);
            },
            checkTeacherEmail:function (teacher_id,email){
                axios({
                    method: "POST",
                    url:"queryPersonInfoController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"checkTeacherEmail",
                        teacher_id:teacher_id,
                        email:email,
                    }
                })
                    .then(function (value) {
                        let data=value.data;
                        alert(data.toString())
                        vue.query();
                    })
                    .catch(function (value){

                    });
            },
            checkTeacherEmailValue:function (teacher_id,email){
                if(!vue.matchEmail(email)){
                    alert("邮箱格式出错");
                    return ;
                }
                vue.checkTeacherEmail(teacher_id,email);
            },
            checkTeacherPictureValue:function (teacher_id){
                let teacherImg = document.getElementById("teacherImg");
                let filePath = teacherImg.value;//文件路径
                let fileName = teacherImg.files[0].name;//上传的文件名称
                let file = teacherImg.files[0];//上传的文件
                let extn = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();//文件后缀

                if(file == null || (extn != "png" && extn != "jpg" && extn != "gif")){
                    alert("请上传图片");
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
                        vue.checkTeacherPicture(teacher_id, base64String);
                        //alert(base64String);
                    };
                }
            },
            checkTeacherPicture:function (teacher_id, base64String){
                axios({
                    method: "POST",
                    url:"queryPersonInfoController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"checkTeacherPicture",
                        teacher_id:teacher_id,
                        picture:base64String,
                    }
                })
                    .then(function (value) {
                        let data=value.data;
                        alert(data.toString())
                        vue.query();
                    })
                    .catch(function (value){

                    });
            },
        }
    });
    vue.queryIdentity();
}