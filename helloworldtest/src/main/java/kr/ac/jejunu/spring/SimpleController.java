package kr.ac.jejunu.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@org.springframework.stereotype.Controller
@RequestMapping("helloworld")
@Slf4j
public class SimpleController  {

    @RequestMapping("/hi")
    public ModelAndView hello() {
        log.info( "*********** handler*******************" );
        ModelAndView modelAndView = new ModelAndView( "hello" );
//        ModelAndView modelAndView = null;
        modelAndView.addObject( "hello", "Hello world!!!" );
        return modelAndView;
    }

    @ExceptionHandler(NullPointerException.class)
    public String error(NullPointerException e){
        return "error";
    }

    @RequestMapping(path="/upload",method = RequestMethod.GET)
    public String upload(){
        return "upload";
    }

    @RequestMapping(path = "/upload",method = RequestMethod.POST)
    public ModelAndView upload(@RequestParam("file")MultipartFile file , HttpServletRequest request) throws IOException {
        File path = new File(request.getServletContext().getRealPath( "/" ) +"/WEB-INF/static/"+file.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream( path );
        BufferedOutputStream bufferedInputStream = new BufferedOutputStream( fileOutputStream );
        bufferedInputStream.write( file.getBytes() );
        ModelAndView modelAndView = new ModelAndView( "upload" );
        modelAndView.addObject( "url","/images/"+file.getOriginalFilename() );
        return modelAndView;
    }









}
