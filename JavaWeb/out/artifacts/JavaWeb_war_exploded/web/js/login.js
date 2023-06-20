window.onload=function(){
    let vue=new Vue({
        "el":"#midArea",
        methods:{
            changePassword:function (){
                window.location.href="loginController.do?operateParameter=changePassword";
            },
            forgetPassword:function (){
                window.location.href="loginController.do?operateParameter=forgetPasswordPage";
            }
        }
    });
}