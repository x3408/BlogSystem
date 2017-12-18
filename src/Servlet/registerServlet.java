package Servlet;

import Bean.User;
import Service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        创建工厂类
        DiskFileItemFactory factory = new DiskFileItemFactory();
//        创建map集合用来保存jsp端数据,方便后面的注册
        Map<String, String> map = new HashMap<>();
//        创建文件上传核心类
        ServletFileUpload upload = new ServletFileUpload(factory);
        User user = new User();
        try {
//            解析request,所有表单数据保存在list中
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
//                非文件类型
                if (item.isFormField()) {
//                    将字段名和值保存到map中
                   map.put(item.getFieldName(),new String(item.getString().getBytes("iso-8859-1"),"UTF-8"));
                } else {
//                    文件类型
//                    获取文件名
                    String name = item.getName();
                    InputStream is = item.getInputStream();
//                    文件保存位置
                    String path = getServletContext().getRealPath("/img");       //输出的文件
//                    将文件写入到之前的路径中
                    OutputStream os = new FileOutputStream(new File(path+"/"+name));
                    int len = 0;
                    byte byt[] = new byte[1024];
                    while((len = is.read(byt))!=-1){
                        os.write(byt,0,len);
                    }
                    os.close();
                    is.close();

//                    将文件名保存到map中,因为图片路径是固定的所以最后获取只需要文件名即可
                    map.put("path", name);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        try {
//            注册
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
//        添加到数据库中
        UserService us = new UserService();
        boolean flag = us.addUser(user);


//        返回信息
        if (flag) {
            request.setAttribute("msg", "注册成功");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", "注册失败");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }



      /*  Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UserService us = new UserService();
        boolean flag = us.addUser(user);
//        boolean flag = us.addUser(username, password);

        if (flag) {
            request.setAttribute("msg", "注册成功");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", "注册失败");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }*/
    }
}
