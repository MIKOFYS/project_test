package MySSM.Service;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;

public class fileUploadAndDownloadService {
    public String base64ToFile(String base64, String savePath, String prefixFileName) {
        String extn = null;
        String noPrefixBase64 = null;
        if(base64.startsWith("data:image/png;base64,")) {
            extn = "png";
            noPrefixBase64 = base64.substring("data:image/png;base64,".length());
        } else if(base64.startsWith("data:image/jpeg;base64,") ) {
            extn = "jpg";
            noPrefixBase64 = base64.substring("data:image/jpeg;base64,".length());
        } else if(base64.startsWith("data:image/gif;base64,") ) {
            extn = "gif";
            noPrefixBase64 = base64.substring("data:image/gif;base64,".length());
        } else if(base64.startsWith("data:application/msword;base64,") ) {
            extn = "doc";
            noPrefixBase64 = base64.substring("data:application/msword;base64,".length());
        } else if(base64.startsWith("data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64,") ) {
            extn = "docx";
            noPrefixBase64 = base64.substring("data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64,".length());
        } else if(base64.startsWith("data:application/pdf;base64,") ) {
            extn = "pdf";
            noPrefixBase64 = base64.substring("data:application/pdf;base64,".length());
        }else {
            System.out.println("文件格式不正确");
            return "result:false";
        }

        String fileName = prefixFileName + "." + extn;
        File file = null;
        //创建文件目录
        String filePath = savePath;
        File dir = new File(filePath);
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        BufferedOutputStream bos = null;
        java.io.FileOutputStream fos = null;
        try {
            byte[] bytes = Base64.getDecoder().decode(noPrefixBase64);
            file = new File(filePath + fileName);
            fos = new java.io.FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileName;
    }

    public String fileToBase64(String path, String fileName) {
        String base64Prefix = null;
        if(fileName.endsWith(".doc")){
            base64Prefix = "data:application/msword;base64,";
        }else if(fileName.endsWith(".docx")){
            base64Prefix = "data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64,";
        }else if(fileName.endsWith(".pdf")){
            base64Prefix = "data:application/pdf;base64,";
        }else if(fileName.endsWith(".png")){
            base64Prefix = "data:image/png;base64,";
        }else if(fileName.endsWith(".jpg")){
            base64Prefix = "data:image/jpeg;base64,";
        }else if(fileName.endsWith(".gif")){
            base64Prefix = "data:image/gif;base64,";
        }else{
            System.out.println("文件格式不正确");
            return "result:false";
        }

        try {
            //获取输入输出流
            FileInputStream fileInputStream = new FileInputStream(path + fileName);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            //输入输出过程
            int len;
            byte[] buffer = new byte[1024];
            while((len = fileInputStream.read(buffer))!=-1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            byteArrayOutputStream.flush();
            byte[] data = byteArrayOutputStream.toByteArray();
            String encodeStr = base64Prefix+Base64.getEncoder().encodeToString(data);
            return encodeStr;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(new fileUploadAndDownloadService().fileToBase64("D:\\IDEA_Code_Store_Position\\JavaWeb\\webApp\\WEB-INF\\studentDownloadPaperPath\\","main_paper.doc"));
    }

//    public static void main(String[] args) {
//        fileUploadAndDownloadService FileUploadAndDownloadService = new fileUploadAndDownloadService();
//        System.out.println(FileUploadAndDownloadService.fileToBase64("D:\\IDEA_Code_Store_Position\\JavaWeb\\webApp\\WEB-INF\\studentDownloadPaperPath\\","xhj.docx"));
//    }


//        public boolean fileUpload(HttpServletRequest request, String fileUploadPath,String newFileName){
//        // 创建DiskFileItemFactory文件项工厂对象
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//        // 通过工厂对象获取文件上传请求核心解析类ServletFileUpload
//        ServletFileUpload upload = new ServletFileUpload(factory);
//        boolean flag=false;
//        try {
//            // 使用ServletFileUpload对应Request对象进行解析
//            List<FileItem> items = upload.parseRequest(request);
//            // 遍历每个fileItem
//            for (FileItem fileItem : items) {
//                // 判断fileItem是否是上传项
//                if (fileItem.isFormField()) {
//                    // 返回true:表示不是上传项
//                    String fieldName = fileItem.getFieldName();
//                    String str = fileItem.getString("utf-8");
//                    //System.out.println(fieldName+" : "+str);
//                }else{
//                    // 返回false:表示是上传项
//                    //String name = fileItem.getName();
//                    File directory = new File(fileUploadPath);
//                    if(!directory.exists()) directory.mkdir();
//                    InputStream in = fileItem.getInputStream();
//                    OutputStream out = new FileOutputStream(new File(fileUploadPath, newFileName));
//                    int b;
//                    while ((b = in.read()) != -1) {
//                        out.write(b);
//                    }
//                    out.close();
//                    in.close();
//                }
//            }
//            flag=true;
//        } catch (Exception e) {
//            flag=false;
//        }finally {
//            return flag;
//        }
//    }
//
//    public boolean fileDownload(HttpServletResponse response, String fileDownloadPath, String downloadFileName) {
//        // 判断下载文件是否存在
//        File downloadFile = new File(fileDownloadPath + downloadFileName);
//        //System.out.println(downloadFile.toPath());
//        boolean flag = false;
//        try {
//            if (downloadFile.isFile() && downloadFile.exists()) {
//                // 说明下载文件存在
//                // 为下载设置ContentType和Content-Disposition
//                Path downloadFilePath = downloadFile.toPath();
//                response.setContentType(Files.probeContentType(downloadFilePath));
//                response.setHeader("Content-Disposition", "attachment;filename=" + downloadFileName);
//                // 读取下载文件的内容,写到客户端
//                InputStream in = new BufferedInputStream(new FileInputStream(downloadFile));
//                IOUtils.copy(in, response.getOutputStream());
//                in.close();
//                flag = true;
//            }
//        } catch (IOException e){
//            flag=false;
//        }finally {
//            return flag;
//        }
//    }
}
