window.onload = function () {
    let vue = new Vue({
        "el": "#form",
        data: {
        },
        methods: {
            getQueryString: function (name) {
                let reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
                let r = window.location.search.substr(1).match(reg);
                if (r != null) {
                    return unescape(r[2]);
                }
                return null;
            },
            checkChangePassword: function () {
                let password = document.getElementById("password").value;
                if(password == null || password == "") {
                    alert("请输入密码");
                    return;
                }

                let hashcode = vue.getQueryString("hashcode");
                if(hashcode == null || hashcode == ""){
                    alert("网页错误");
                    return;
                }

                vue.changePassword(hashcode,password);
            },
            changePassword: function (hashcode,password) {
                axios({
                    method: "POST",
                    url: "loginController.do",
                    contentType: "charset=utf-8",
                    params: {
                        operateParameter:"forgetAndChangePassword",
                        hashcode:hashcode,
                        password:password,
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