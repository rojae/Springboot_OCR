package knu.ialab.sponsor;

import knu.ialab.sponsor.account.Account;
import knu.ialab.sponsor.account.AccountService;
import knu.ialab.sponsor.account.Bill;
import knu.ialab.sponsor.bill.BillData;
import knu.ialab.sponsor.bill.BillSeparator;
import knu.ialab.sponsor.bill.BillService;
import knu.ialab.sponsor.ocr.OcrApiSender;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


@SpringBootTest
class SponsorApplicationTests {

    @Autowired
    AccountService accountService;

    @Autowired
    BillService billService;

    @Test
    void contextLoads() {
    }


}
