package MySSM.Listener;

import MySSM.IOC.BeanElementNodeObjectFactory;
import MySSM.IOC.BeanElementNodeObjectFactoryByXML;

import javax.servlet.*;
import javax.servlet.http.*;

//@WebListener
public class BeanFactoryListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public BeanFactoryListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        BeanElementNodeObjectFactory BeanFactory=new BeanElementNodeObjectFactoryByXML();
        ServletContext application= servletContextEvent.getServletContext();
        application.setAttribute("BeanFactory",BeanFactory);
        //System.out.println("ServletContext对象初始化动作被我监听到了");
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //System.out.println("ServletContext对象销毁动作被我监听到了");
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }




    /***********************************************************************/
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    }
}
