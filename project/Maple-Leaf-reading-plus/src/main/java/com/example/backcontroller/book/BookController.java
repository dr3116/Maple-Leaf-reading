package com.example.controller.book;

import com.example.entity.myself_style.StrStrBean;
import com.example.mapper.ClassificationMapper;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/**
 * @author 异侠 2021-05-07
 */

@Controller
@RequestMapping("/Book")
public class BookController {


    @Autowired
    private BookService bookService;
    @Autowired
    private ClassificationMapper classificationMapper;


    /**
     * 开始动态查询图书信息
     */
    @GetMapping(value = "/toSearchDynamicBook")
    public String toSearchDynamicBook() {
//        return "member-search";
        return "redirect:/Book/searchDynamicBook";
    }


    /**
     * 开始动态查询图书信息
     */
    @GetMapping(value = "/searchDynamicBook")
    public String searchDynamicBook(@RequestParam(value = "bookName", defaultValue = "") String bookName,
                                    @RequestParam(value = "classification", defaultValue = "") String classification,
                                    @RequestParam(value = "author", defaultValue = "") String author,
                                    HttpSession session, Model model,
                                    @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {


        model.addAttribute("DynamicBookInfo", bookService.getAllBooksByPagesAndDynamic(pageNum, 5, bookName, author, classification));
        model.addAttribute("msg", "开始展示所有图书");

        return "member-search";
    }


    /**
     * 开始查询全部图书信息
     */
    @GetMapping(value = "/getAllBooks")
    public String getAllBooks(Model model, HttpSession session,
                              @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {


        List<StrStrBean> lists = classificationMapper.getClassificationNumberAndName();


        for (StrStrBean strStrBean : lists) {
            String str2 = strStrBean.getStr2();
            String str1 = strStrBean.getStr1();
            if (str1.equals("小程序")) {
                str1 = "xiaochengxu";
            }
            if (str1.equals("机器学习")) {
                str1 = "jiqixuexi";
            }
            if (str1.equals("深度学习")) {
                str1 = "shenduxuexi";
            }
            if (str1.equals("测试")) {
                str1 = "ceshi";
            }
            session.setAttribute(str1, str2 + "00");
        }


        model.addAttribute("pageInfo", bookService.getAllBooksByPages(pageNum, 5));
        model.addAttribute("msg", "开始展示所有图书");
        return "member-list";
    }


    /**
     * 开始精确查询图书信息
     */
    @GetMapping(value = "/searchBookByName")
    public String searchBookByName(Model model,
                                   @RequestParam(value = "bookName", defaultValue = "") String bookName,
                                   @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {

//        System.out.println("输入的精确搜索的书名为" + bookName);

        if (bookName != null && !"".equals(bookName)) {
            model.addAttribute("msg", "根据书名 ' " + bookName + " ' 查询的结果为:");
            model.addAttribute("bookName", bookName);
            model.addAttribute("pageInfo", bookService.getAllBooksByPagesAndBookName(pageNum, 5, bookName));
        } else {
            model.addAttribute("pageInfo", bookService.getAllBooksByPagesAndBookName(pageNum, 5, "&(^*&*%&^%*(&(&"));
            model.addAttribute("msg", "请输入书名开始查询");
        }
        return "member-list1";
    }


    @GetMapping(value = "/searchBookByNameShow")
    public String searchBookByNameShow(Model model,
                                       @RequestParam(value = "bookName", defaultValue = "") String bookName,
                                       @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        model.addAttribute("bookName", bookName);
        model.addAttribute("pageInfo", bookService.getAllBooksByPagesAndBookName(pageNum, 5, bookName));
        model.addAttribute("msg", "开始展示所有图书");
        return "member-list1";
    }


    /**
     * 删除图书
     */
    @GetMapping("/deleteBook")
    public String deleteBook(@RequestParam(value = "deleteBookName") String bookName,
                             HttpSession session) {
        bookService.deleteBook(bookName);
        return "redirect:/Book/getAllBooks";
    }


//


    /**
     * 添加图书
     */
    @GetMapping("/toAddBook")
    public String addBook() {
        //跳转到图书插入界面
        return "member-add";
    }

    @PostMapping("/addBook")
    @ResponseBody
    public String addBook(@RequestParam("bookName") String bookName,
                          @RequestParam("classification") String classification,
                          @RequestParam("numberOfChapters") String numberOfChapters,
                          @RequestParam("author") String author,
                          @RequestParam("briefIntroduction") String briefIntroduction,
                          @RequestParam("imageFile") MultipartFile imageFile,
                          @RequestParam("bookFile") MultipartFile bookFile) {


//        String pathImage = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/images/book";
        //F:\WorkSpace\IDEA\PP\Maple-leaf-reading\target\classes\static
        String pathBook = "F:\\WorkSpace\\IDEA\\PP\\Maple-leaf-reading\\target\\classes\\static\\pdfs";
        String pathImage = "F:\\WorkSpace\\IDEA\\PP\\Maple-leaf-reading\\target\\classes\\static\\images\\book";


        File uploadImage = new File(pathImage);
        if (!uploadImage.exists()) uploadImage.mkdirs();
        File uploadBook = new File(pathBook);
        if (!uploadBook.exists()) uploadBook.mkdirs();


        String imageOriginalFilename = System.currentTimeMillis() + "";
        if (!imageFile.isEmpty()) {
//            System.out.println("imageOriginalFilename="+imageOriginalFilename);
            try {
                imageFile.transferTo(new File(pathImage + "/" + imageOriginalFilename + ".jpg"));
//                imageFile.transferTo(new File("F:\\WorkSpace\\IDEA\\PP\\Maple-leaf-reading\\src\\main\\resources\\static\\pdfs"+"/"+imageOriginalFilename+".jpg"));
                System.out.println("最终上传图片地址=" + pathImage + "/" + imageOriginalFilename + ".jpg");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if (!bookFile.isEmpty()) {
            try {
                bookFile.transferTo(new File(pathBook + "/" + imageOriginalFilename + ".pdf"));
//                bookFile.transferTo(new File("F:\\WorkSpace\\IDEA\\PP\\Maple-leaf-reading\\src\\main\\resources\\static\\pdfs"+"/"+imageOriginalFilename+".pdf"));
                System.out.println("最终上传图书地址=" + pathBook + "/" + imageOriginalFilename + ".pdf");
                //开始创建图书压缩文件夹
                System.out.println("增加图书，开始创建图书压缩包："+pathBook + "/" + imageOriginalFilename + ".pdf");
                doCompressFile(pathBook + "/" + imageOriginalFilename + ".pdf");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date releaseTime = new Date(System.currentTimeMillis());

        int num = bookService.insertBook(bookName, classification, 0, Integer.parseInt(numberOfChapters), releaseTime, imageOriginalFilename + ".jpg", 0, author, 0, briefIntroduction);
        if (num == 1) {
            //插入数据成功
            System.out.println("图书数据插入成功");
        }


        //重定向
        return "图书增加成功";
    }


    //图书压缩

    /**
     * 将文件进行压缩
     *
     * @param fileName
     */
    public static void doCompressFile(String fileName) {//这里传入解压前的文件全路径名
        //先将出去的文件名字设定好
        String outFileName = removeHouZuiMing(fileName) + ".gz";
        //创建读写刘，不需要GZIP
        try {
            //创建压缩GZIP流
            GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(outFileName));
            FileInputStream in = new FileInputStream(fileName);
            byte[] b = new byte[1024];
            int len;
            //开始读文件
            while ((len = in.read(b)) > 0) {
                out.write(b, 0, len);
            }
            in.close();
            out.finish();
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    public static String removeHouZuiMing(String filePath) {
        String qianZui = "";
        String temp[] = filePath.split("\\.");
        int len = temp.length;
        for (int i = 0; i < len-1; i++) {
            qianZui += temp[i];
        }

        return qianZui;
    }


    /**
     * 修改图书
     */
    @GetMapping("/toEditBook")
    public String toEditBook(Model model,
                             @RequestParam(value = "bookName", defaultValue = "") String bookName,
                             @RequestParam(value = "classification", defaultValue = "") String classification,
                             @RequestParam(value = "author", defaultValue = "") String author,
                             @RequestParam(value = "numberOfChapters", defaultValue = "") String numberOfChapters,
                             @RequestParam(value = "briefIntroduction", defaultValue = "") String briefIntroduction) {

        //判断是不是默认参数（有参数的话就是从书签列表来的，不是从页面来的）
        model.addAttribute("briefIntroduction", briefIntroduction);
        model.addAttribute("bookName", bookName);
        model.addAttribute("author", author);
        model.addAttribute("numberOfChapters", numberOfChapters);
        model.addAttribute("classification", classification);

        return "member-edit";
    }


    @PostMapping("/editBook")
    public String editBook(@RequestParam("bookName") String bookName,
                           @RequestParam("classification") String classification,
                           @RequestParam("numberOfChapters") String numberOfChapters,
                           @RequestParam("author") String author,
                           @RequestParam("briefIntroduction") String briefIntroduction,
                           @RequestParam("imageFile") MultipartFile imageFile,
                           @RequestParam("bookFile") MultipartFile bookFile) {


        //ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/images/book";

        String pathBook = "F:\\WorkSpace\\IDEA\\PP\\Maple-leaf-reading\\target\\classes\\static\\pdfs";
        String pathImage = "F:\\WorkSpace\\IDEA\\PP\\Maple-leaf-reading\\target\\classes\\static\\images\\book";


        String imageOriginalFilename = System.currentTimeMillis() + "";
        if (!imageFile.isEmpty()) {
//            System.out.println("imageOriginalFilename="+imageOriginalFilename);
            try {
                imageFile.transferTo(new File(pathImage + "/" + imageOriginalFilename + ".jpg"));
                System.out.println("最终编辑图片地址=" + pathImage + "/" + imageOriginalFilename + ".jpg");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!bookFile.isEmpty()) {
            try {
                bookFile.transferTo(new File(pathBook + "/" + imageOriginalFilename + ".pdf"));
                System.out.println("最终编辑图书地址=" + pathBook + "/" + imageOriginalFilename + ".pdf");
                System.out.println("编辑图书，开始创建图书压缩包："+pathBook + "/" + imageOriginalFilename + ".pdf");
                doCompressFile(pathBook + "/" + imageOriginalFilename + ".pdf");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date releaseTime = new Date(System.currentTimeMillis());
        System.out.println("numberOfChapters" + numberOfChapters);

        int num = bookService.updateBook(bookName, classification, 0, Integer.parseInt(numberOfChapters), releaseTime, imageOriginalFilename + ".jpg", 0, author, 0, briefIntroduction);
        if (num == 1) {
            //插入数据成功
            System.out.println("图书数据插入成功");
        }

        return "redirect:/Book/getAllBooks";
    }


//    @GetMapping("/toReadPDF")
//    public void toReadPDF(HttpServletResponse response,
//                          @RequestParam("readBookName") String readBookName) {
//        String pdfs = "F:\\WorkSpace\\IDEA\\PP\\Maple-leaf-reading\\target\\classes\\static\\pdfs";
//        readBookName = StringUtils.substringBefore(readBookName, ".") + ".pdf";
//
//        //readBookName
//        File file = new File(pdfs + "/" + readBookName);
//
//
//
//        if (file.exists()) {
//            byte[] data = null;
//            FileInputStream input=null;
//            try {
//                input= new FileInputStream(file);
//                data = new byte[input.available()];
//                input.read(data);
//                response.getOutputStream().write(data);
//            } catch (Exception e) {
////                System.out.println("pdf文件处理异常：" + e);
//            }finally{
//                try {
//                    if(input!=null){
//                        input.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }









}
