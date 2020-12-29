package knu.ialab.sponsor.exception;

import knu.ialab.sponsor.common.ApiMsg;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @RequestMapping(value = "/access-denied")
    public Object accessDenied(){
        return ApiMsg.builder().code(403).msg("접근 권한이 없습니다").build();
    }

    @RequestMapping(value = "/invalid-session")
    public Object inValidSession(){
        return ApiMsg.builder().code(403).msg("존재하지 않는 세션입니다").build();
    }

}

