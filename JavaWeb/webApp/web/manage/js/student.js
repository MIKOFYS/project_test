window.onload=function(){
    let vue=new Vue({
        "el":"#form",
        data:{
            pageNumber:1,
            studentList:null,
            studentColName:["学号","姓名","性别","电话","邮箱","图片路径","班级","密码","hashcode","forget_password_flag","选项"],
        },
        methods:{
            checkQueryStudent:function (){
                vue.pageNumber=1;
                vue.studentList=null;
                vue.queryStudent();
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
                vue.queryStudent();
            },
            pageNumberADD:function (){
                if(this.getJSONLen(vue.studentList)<20){
                    ;
                }else{
                    vue.pageNumber++;
                }
                vue.queryStudent();
            },
            queryStudent:function (){
                vue.studentList=null;
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"queryStudent",
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

                        vue.studentList=jsonList;
                    })
                    .catch(function (value){

                    });
            },
            deleteStudent:function (student_id,name,sex,phone,email,picture,class_id,password,hashcode,forget_password_flag){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"deleteStudent",
                        student_id:student_id,
                        name:name,
                        sex:sex,
                        phone:phone,
                        email:email,
                        picture:picture,
                        class_id:class_id,
                        password:password,
                        hashcode:hashcode,
                        forget_password_flag:forget_password_flag,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryStudent();
                    })
                    .catch(function (value){

                    });
            },
            addStudent1:function (student_id1,name1,sex1,phone1,email1,picture1,class_id1,password1,hashcode1,forget_password_flag1){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"addStudent1",
                        student_id1:student_id1,
                        name1:name1,
                        sex1:sex1,
                        phone1:phone1,
                        email1:email1,
                        picture1:picture1,
                        class_id1:class_id1,
                        password1:password1,
                        hashcode1:hashcode1,
                        forget_password_flag1:forget_password_flag1,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryStudent();
                    })
                    .catch(function (value){

                    });
            },
            checkAddStudent1:function (){
                let student_id1 = document.getElementById("student_id1").value;
                let name1 = document.getElementById("name1").value;
                let sex1 = document.getElementById("sex1").value;
                let phone1 = document.getElementById("phone1").value;
                let email1 = document.getElementById("email1").value;
                let picture1 = document.getElementById("picture1").value;
                let class_id1 = document.getElementById("class_id1").value;
                let password1 = document.getElementById("password1").value;
                let hashcode1 = document.getElementById("hashcode1").value;
                let forget_password_flag1 = document.getElementById("forget_password_flag1").value;
                vue.addStudent1(student_id1,name1,sex1,phone1,email1,picture1,class_id1,password1,hashcode1,forget_password_flag1);
            },
            addStudent2:function (student_id2,name2,sex2,phone2,email2,picture2,class_id2,password2,hashcode2,forget_password_flag2){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"addStudent2",
                        student_id2:student_id2,
                        name2:name2,
                        sex2:sex2,
                        phone2:phone2,
                        email2:email2,
                        picture2:picture2,
                        class_id2:class_id2,
                        password2:password2,
                        hashcode2:hashcode2,
                        forget_password_flag2:forget_password_flag2,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryStudent();
                    })
                    .catch(function (value){

                    });
            },
            checkAddStudent2:function (){
                let student_id2 = document.getElementById("student_id2").value;
                let name2 = document.getElementById("name2").value;
                let sex2 = document.getElementById("sex2").value;
                let phone2 = document.getElementById("phone2").value;
                let email2 = document.getElementById("email2").value;
                let picture2 = document.getElementById("picture2");//注意！！！！！！！！！！！！！！！！！！！！！！！
                let class_id2 = document.getElementById("class_id2").value;
                let password2 = document.getElementById("password2").value;
                let hashcode2 = document.getElementById("hashcode2").value;
                let forget_password_flag2 = document.getElementById("forget_password_flag2").value;

                let filePath = picture2.value;//文件路径
                let fileName = picture2.files[0].name;//上传的文件名称
                let file = picture2.files[0];//上传的文件
                let extn = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();//文件后缀

                if(file == null || (extn != "png" && extn != "jpg" && extn != "gif")){
                    alert("请上传png,jpg,gif图片");
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
                        vue.addStudent2(student_id2,name2,sex2,phone2,email2,base64String,class_id2,password2,hashcode2,forget_password_flag2);
                        //alert(base64String);
                    };
                }
            }
        }
    });
    vue.checkQueryStudent();
}