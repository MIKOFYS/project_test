package MySSM.Filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse)servletResponse;

        //获取Controller组件和operateParameter方法参数
        String servletPath=httpServletRequest.getServletPath();
        //System.out.println("servletPath:"+servletPath);
        servletPath=servletPath.substring(1);
        servletPath=servletPath.substring(0,servletPath.lastIndexOf(".do"));

        String res = this.checkLogin(httpServletRequest,servletPath);
        if(res.startsWith("result:")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            httpServletResponse.sendRedirect(res.substring("redirect:".length()));
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    public String checkLogin(HttpServletRequest request,String servletPath){
        HttpSession session=request.getSession();
        if(session.getAttribute("isAdminLogin") != null) return "result:success";
        if(session.getAttribute("isNotAdminLogin") != null && !servletPath.equals("manageController")) return "result:success";
        if(servletPath.equals("manageController") || servletPath.equals("loginController")) return "result:success";
        return "redirect:loginController.do";
    }
}
