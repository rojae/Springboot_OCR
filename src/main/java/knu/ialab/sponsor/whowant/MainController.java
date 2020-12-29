package knu.ialab.sponsor.whowant;

import knu.ialab.sponsor.account.Account;
import knu.ialab.sponsor.account.AccountService;
import knu.ialab.sponsor.common.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    AccountService accountService;

    @RequestMapping("/admin/main")
    @Secured("ROLE_ADMIN")
    public Object adminMain(@CurrentUser Account account) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("users", accountService.getAllUserData());
        data.put("myData", Account.builder().userName(account.getUserName()).userSeq(account.getUserSeq())
                .userType(account.getUserType()).role(account.getRole())
                .email(account.getEmail()).createdDate(account.getCreatedDate()).build()) ;
        return data;
    }

    @RequestMapping("/app/main")
    @Secured("ROLE_USER")
    public Object userMain(@CurrentUser Account account) {
        Account user = Account.builder().userName(account.getUserName()).userSeq(account.getUserSeq())
                .userType(account.getUserType()).role(account.getRole())
                .email(account.getEmail()).createdDate(account.getCreatedDate()).build();
        return user;
    }
}
