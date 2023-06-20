package MySSM.Controller;

import MySSM.Service.loginService;
import javax.servlet.http.HttpSession;

public class loginController {
    private loginService LoginService=null;

    public String studentLogin(HttpSession session,String id,String password){
        if(this.LoginService.studentLogin(id,password)){
            session.setAttribute("isNotAdminLogin",true);
            session.setAttribute("student_id",id);
            session.setAttribute("identity","student");
            return "html\\studentMainPage";
        }else {
            return "redirect:loginController.do";
        }
    }

    public String teacherLogin(HttpSession session,String id,String password){
        if(this.LoginService.teacherLogin(id,password)){
            session.setAttribute("isNotAdminLogin",true);
            session.setAttribute("teacher_id",id);
            session.setAttribute("identity","teacher");
            return "html\\teacherMainPage";
        }else {
            return "redirect:loginController.do";
        }
    }

    public String studentChangePassword(String id, String oldPassword,String newPassword){
        if(this.LoginService.studentChangePassword(id,oldPassword,newPassword)){
            return "result:change password success";
        }
        return "result:change password fail";
    }

    public String teacherChangePassword(String id, String oldPassword,String newPassword){
        if(this.LoginService.teacherChangePassword(id,oldPassword,newPassword)){
            return "result:change password success";
        }
        return "result:change password fail";
    }

    public String studentForgetPassword(String id){
        if(this.LoginService.studentForgetPassword(id)){
            return "result:forget password success";
        }
        return "result:forget password fail";
    }

    public String teacherForgetPassword(String id){
        if(this.LoginService.teacherForgetPassword(id)){
            return "result:forget password success";
        }
        return "result:forget password fail";
    }

    public String forgetAndChangePassword(String hashcode,String password){
        if(this.LoginService.forgetAndChangePassword(hashcode,password)){
            return "result:change password success";
        }
        return "result:change password fail";
    }

    public String index(){
        return "html\\login";
    }
    public String changePassword(){
        return "html\\changePassword";
    }
    public String forgetPasswordPage(){
        return "html\\forgetPasswordPage";
    }
    public String afterForgetChangePassword(){
        return "html\\afterForgetChangePassword";
    }
}
