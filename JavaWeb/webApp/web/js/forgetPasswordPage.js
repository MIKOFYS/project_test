window.onload=function(){
    let vue=new Vue({
        "el":"#form",
        data:{
            operator:null,
        },
        methods: {
            queryIdentity: function () {
                let identityElement = document.getElementsByName("identity");
                let identity = null;
                for(let i = 0;i < identityElement.length; i++){
                    if(identityElement[i].checked == true){
                        identity = identityElement[i].value;
                    }
                }

                if(identity == "teacher"){
                    vue.operator = "teacherForgetPassword";
                }else if(identity == "student") {
                    vue.operator = "studentForgetPassword";
                }

                let id = document.getElementById("id").value;
                if(id == null || id == ""){
                    alert("账号为空,请重新输入");
                    return ;
                }
                vue.forgetPassword(id);
            },
            forgetPassword: function (id) {
                axios({
                    method: "POST",
                    url: "loginController.do",
                    contentType: "charset=utf-8",
                    params: {
                        operateParameter: vue.operator,
                        id: id,
                    }
                })
                    .then(function (value) {
                        let data = value.data;
                        alert(data)
                    })
                    .catch(function (value) {

                    });
            }
        }
    });
}