package MySSM.Filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//过滤器过滤顺序默认是按全类名决定的,如果配置则按配置顺序执行
//@WebServlet(urlPatterns = {"/*.do"},initParams = {@WebInitParam(name = "encoding",value = "UTF-8")})
public class CharacterEncodingFilter implements Filter {
    private String encodingString="UTF-8";
    private Map<String,Object> beanElementNodeMap=new HashMap<>();

    public void init(FilterConfig filterConfig) throws ServletException {
        //初始化设置编码
        String encodingStr=filterConfig.getInitParameter("encoding");
        if(encodingStr!=null&&encodingStr!=""){
            this.encodingString=encodingStr;
        }
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        request.setCharacterEncoding(this.encodingString);
        response.setCharacterEncoding(this.encodingString);
        //System.out.println("过滤器1编码设置成功");
        //System.out.println("Filter已经将请求进行过滤了");
        filterChain.doFilter(request, response);
    }
}
