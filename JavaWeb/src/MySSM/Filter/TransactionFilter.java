package MySSM.Filter;

import MySSM.TransactionManager.TransactionManager;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            TransactionManager.beginTransaction();
            filterChain.doFilter(servletRequest,servletResponse);
            TransactionManager.commit();
            System.out.println("TransactionFilter没有发现异常");
        }catch (Exception e){
            TransactionManager.rollback();
            System.out.println("TransactionFilter发现异常");
        }
    }

    @Override
    public void destroy() {

    }
}
