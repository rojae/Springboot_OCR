package knu.ialab.sponsor.whowant;

import com.sun.media.jfxmedia.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @RequestMapping( value = "/test")
    public @ResponseBody
    List<String> test(){
      ArrayList<String> list = new ArrayList<>();
      list.add("TEST1");
      list.add("TEST2");

      list.forEach(System.out::println);
      return list;
    }

}
