package knu.ialab.sponsor.authentication.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MailController {

    @Autowired
    MailService mailService;

    @GetMapping("/authentication/sign-up")
    public ModelAndView emailAuth(@RequestParam(value = "authCode") String authCode, @RequestParam(value = "userSeq") String userSeq){
        ModelAndView mav = new ModelAndView("sign-up");
        boolean status = mailService.doAuth(userSeq, authCode);
        if(status)
            mav.addObject("msg", "인증이 완료되었습니다");
        else
            mav.addObject("msg", "인증에 실패하였습니다");
        return mav;
    }
}
