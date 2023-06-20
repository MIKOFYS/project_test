package MySSM.DispatcherServlet;

import MySSM.IOC.BeanElementNodeObjectFactory;
import MySSM.ViewBaseServlet.ViewBaseServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;


//@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {
    private BeanElementNodeObjectFactory BeanFactory;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext application=this.getServletContext();
        Object beanFactoryObj=(BeanElementNodeObjectFactory)application.getAttribute("BeanFactory");
        if(beanFactoryObj==null){
            System.out.println("IOC容器获取失败");
        }else{
            this.BeanFactory=(BeanElementNodeObjectFactory)beanFactoryObj;
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //例子：
        //url=http://localhost:8080/project/fruit.do?operate=edit&fruitId=2
        //首先获得servletPath,然后找到其对应Controller         servletPath->Controller     一个servletPath对应一个Controller
        //然后获取操作方法参数,得知执行相关方法
        //然后获取方法带传参数,将相关参数传入对应方法


        //获取Controller组件和operateParameter方法参数
        String servletPath=request.getServletPath();
        System.out.println("servletPath:"+servletPath);
        servletPath=servletPath.substring(1);
        servletPath=servletPath.substring(0,servletPath.lastIndexOf(".do"));

        Object BeanControllerObject=this.BeanFactory.getBeanElementObjectById(servletPath);
        if(BeanControllerObject==null){
            BeanControllerObject=this.BeanFactory.getBeanElementObjectById("loginController");
        }

        String operateParameter=request.getParameter("operateParameter");
        System.out.println("operateParameter:"+operateParameter);
        if(operateParameter==null||operateParameter==""){
            operateParameter="index";//当前组件的首页
        }

        try{
            //根据operateParameter方法参数获得对应组件的方法,通过反射可根据方法名获得方法
            //Method method=BeanControllerObject.getClass().getDeclaredMethod(operateParameter);

            //获取所有方法,根据方法名找到对应方法
            Method[] methods=BeanControllerObject.getClass().getDeclaredMethods();
            Method trueMethod=null;
            for (Method method:methods) {
                //System.out.println("method.getName():"+method.getName());
                if(method.getName().equals(operateParameter)){
                    trueMethod=method;
                    break;
                }
            }

            if(trueMethod==null){
                System.out.println("DispatcherServlet_trueMethod found error");
            }

            //获取method方法参数数组
            Parameter[] parameters=trueMethod.getParameters();
            int parametersLen=parameters.length;
            //用来存放参数的值
            Object[] parameterValues=new Object[parametersLen];
            for (int i = 0; i < parametersLen; i++) {
                Parameter parameter=parameters[i];
                String parameterName=parameter.getName();
                if(parameterName.equals("request")){
                    parameterValues[i]=request;
                } else if (parameterName.equals("response")) {
                    parameterValues[i]=response;
                } else if(parameterName.equals("session")) {
                    parameterValues[i]=request.getSession();
                } else{
                    //获取前端页面单个参数的值
                    String parameterValue=request.getParameter(parameterName);
                    //System.out.println("parameterName:"+parameterName);
                    //System.out.println("parameterValue:"+parameterValue);
                    //获取前端页面复选框的值,需要用request.getParameterValues(parameter.getName());
                    parameterValues[i]=parameterValue;
                }
            }


            //Controller方法调用
            //System.out.println(trueMethod.getName());
            //System.out.println(BeanControllerObject);
            trueMethod.setAccessible(true);
            Object methodReturnObject=trueMethod.invoke(BeanControllerObject,parameterValues);


            //视图处理
            String methodReturnString=(String) methodReturnObject;
            //System.out.println(methodReturnString);
            if(methodReturnString==null||methodReturnString.equals("")){
                ;
            }
            else if(methodReturnString.startsWith("redirect:")){
                String redirectString=methodReturnString.substring("redirect:".length());
                response.sendRedirect(redirectString);
            }else if(methodReturnString.startsWith("JSON:")) {
                String JSONString=methodReturnString.substring("JSON:".length());
                response.getWriter().write(JSONString);
                //System.out.println(JSONString);
            }else if(methodReturnString.startsWith("result:")){
                String resultString=methodReturnString.substring("result:".length());
                response.getWriter().write(resultString);
                //System.out.println(resultString);
            }else{
                super.processTemplate(methodReturnString,request,response);
            }
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
