window.onload=function(){
    let vue=new Vue({
        "el":"#midArea",
        methods:{
            administratorLogin:function (){
                let id = document.getElementById("id").value;
                let password = document.getElementById("password").value;
                let url = "manageController.do?operateParameter=administratorLogin"+"&id="+id+"&password="+password;
                window.location.href=url;
            }
        }
    });
}