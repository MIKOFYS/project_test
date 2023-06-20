window.onload=function(){
    let vue=new Vue({
        "el":"#form",
        data:{
            pageNumber:1,
            teacherList:null,
            teacherColName:["老师ID","姓名","性别","电话","邮箱","图片路径","学院ID","密码","hashcode","forget_password_flag","选项"],
        },
        methods:{
            checkQueryTeacher:function (){
                vue.pageNumber=1;
                vue.teacherList=null;
                vue.queryTeacher();
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
                vue.queryTeacher();
            },
            pageNumberADD:function (){
                if(this.getJSONLen(vue.teacherList)<20){
                    ;
                }else{
                    vue.pageNumber++;
                }
                vue.queryTeacher();
            },
            queryTeacher:function (){
                vue.teacherList=null;
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"queryTeacher",
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

                        vue.teacherList=jsonList;
                    })
                    .catch(function (value){

                    });
            },
            deleteTeacher:function (teacher_id,name,sex,phone,email,picture,dept_id,password,hashcode,forget_password_flag){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"deleteTeacher",
                        teacher_id:teacher_id,
                        name:name,
                        sex:sex,
                        phone:phone,
                        email:email,
                        picture:picture,
                        dept_id:dept_id,
                        password:password,
                        hashcode:hashcode,
                        forget_password_flag:forget_password_flag,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryTeacher();
                    })
                    .catch(function (value){

                    });
            },
            addTeacher1:function (teacher_id1,name1,sex1,phone1,email1,picture1,dept_id1,password1,hashcode1,forget_password_flag1){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"addTeacher1",
                        teacher_id1:teacher_id1,
                        name1:name1,
                        sex1:sex1,
                        phone1:phone1,
                        email1:email1,
                        picture1:picture1,
                        dept_id1:dept_id1,
                        password1:password1,
                        hashcode1:hashcode1,
                        forget_password_flag1:forget_password_flag1,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryTeacher();
                    })
                    .catch(function (value){

                    });
            },
            checkAddTeacher1:function (){
                let teacher_id1 = document.getElementById("teacher_id1").value;
                let name1 = document.getElementById("name1").value;
                let sex1 = document.getElementById("sex1").value;
                let phone1 = document.getElementById("phone1").value;
                let email1 = document.getElementById("email1").value;
                let picture1 = document.getElementById("picture1").value;
                let dept_id1 = document.getElementById("dept_id1").value;
                let password1 = document.getElementById("password1").value;
                let hashcode1 = document.getElementById("hashcode1").value;
                let forget_password_flag1 = document.getElementById("forget_password_flag1").value;
                vue.addTeacher1(teacher_id1,name1,sex1,phone1,email1,picture1,dept_id1,password1,hashcode1,forget_password_flag1);
            },
            addTeacher2:function (teacher_id2,name2,sex2,phone2,email2,picture2,dept_id2,password2,hashcode2,forget_password_flag2){
                axios({
                    method: "POST",
                    url:"manageController.do",
                    contentType:"charset=utf-8",
                    params:{
                        operateParameter:"addTeacher2",
                        teacher_id2:teacher_id2,
                        name2:name2,
                        sex2:sex2,
                        phone2:phone2,
                        email2:email2,
                        picture2:picture2,
                        dept_id2:dept_id2,
                        password2:password2,
                        hashcode2:hashcode2,
                        forget_password_flag2:forget_password_flag2,
                    }
                })
                    .then(function (value) {
                        let data =  value.data;
                        alert(data);
                        vue.checkQueryTeacher();
                    })
                    .catch(function (value){

                    });
            },
            checkAddTeacher2:function (){
                let teacher_id2 = document.getElementById("teacher_id2").value;
                let name2 = document.getElementById("name2").value;
                let sex2 = document.getElementById("sex2").value;
                let phone2 = document.getElementById("phone2").value;
                let email2 = document.getElementById("email2").value;
                let picture2 = document.getElementById("picture2");//注意！！！！！！！！！！！！！！！！！！！！！！！
                let dept_id2 = document.getElementById("dept_id2").value;
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
                        vue.addTeacher2(teacher_id2,name2,sex2,phone2,email2,base64String,dept_id2,password2,hashcode2,forget_password_flag2);
                        //alert(base64String);
                    };
                }
            }
        }
    });
    vue.checkQueryTeacher();
}