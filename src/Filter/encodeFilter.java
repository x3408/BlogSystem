package Filter;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class encodeFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        request.setCharacterEncoding("UTF-8");

        /*HttpServletRequest enhanceRequest = (HttpServletRequest) Proxy.newProxyInstance(
                request.getClass().getClassLoader(),
                request.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //调用方法是否是获取参数
                        if(method.getName().equals("getParameter")){
                            //得到先前的参数
                            String param = (String) method.invoke(request, args);
                            //将参数用utf-8重新编码并返回
                            param = new String(param.getBytes("iso-8859-1"),"UTF-8");
                            return param;
                        }

                        //不是获取方法参数则返回原先类型
                        return method.invoke(request, args);
                    }
                }
        );*/

        servletResponse.setContentType("text/html;charsert=utf-8");
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
