package knu.ialab.sponsor.ocr;

import knu.ialab.sponsor.account.Account;
import knu.ialab.sponsor.authentication.Authentication;
import knu.ialab.sponsor.bill.BillData;
import knu.ialab.sponsor.bill.BillSeparator;
import knu.ialab.sponsor.bill.BillService;
import knu.ialab.sponsor.common.ApiMsg;
import knu.ialab.sponsor.common.CurrentUser;
import knu.ialab.sponsor.common.JsonUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
public class OcrController {

    private FileOutputStream fos;

    @Autowired
    BillService billService;

    @PostMapping("/app/main/ocr/upload")
    @Secured("ROLE_USER")
    @ResponseBody
    public Object uploadImage(@CurrentUser Account user, @RequestParam("file") MultipartFile file) throws IOException, ParseException {
        JsonUtils jsonUtils = new JsonUtils();

        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss"));    // 2020-01-01 00:13:12
        String fileName = user.getUserSeq()+"_"+now+".jpg";
        String filePath = "../ocr/bill/";

        System.out.println("[TIME : "+ now + " ~> 학번/교직원 번호 :" + user.getUserSeq() + "]" + user.getUserName() + "님이 영수증을 제출합니다");

        writeFile(file, filePath , fileName);

        String apiResponse = OcrApiSender.send(filePath+fileName, fileName);

        BillData billData = jsonUtils.parse(apiResponse);
        System.out.println("[BILL DATA] : " + billData);

        Map<String, Object> result = new HashMap<String, Object>();

        if(billService.isDuplicate(billData)){
            result.put("code","999");
            result.put("msg", "이미 제출한 영수증입니다");
            result.put("detail","거부되었습니다");
            return result;
        }

        billService.insertBill(user, billData);

        System.out.println("[BILL DATA] : INSERT QUERY Success");


        if((billData.authNo == null) || (billData.authNo.length() != 8)) {
            result.put("code","999");
            result.put("msg", "승인번호가 인식되지 않습니다");
            result.put("detail","재시도를 해주세요");
        }
        else if(billData.mallNo == null || (billData.mallNo.length() != 8)) {
            result.put("code","999");
            result.put("msg", "가맹점 번호가 인식되지 않습니다");
            result.put("detail","재시도를 해주세요");
        }
        else if(billData.totalPrice == null) {
            result.put("code","999");
            result.put("msg", "가격 총액이 인식되지 않습니다");
            result.put("detail","재시도를 해주세요");
        }
        else {
            billData.setNow(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));

            result.put("code", "200");
            result.put("authNo", billData.authNo);
            result.put("mallNo", billData.mallNo);
            result.put("totalPrice", billData.totalPrice);
            result.put("now", billData.now);
        }
        System.out.println(result);
        return result;
    }


    public void writeFile(MultipartFile file, String path, String fileName) {
        try {
            byte fileData[] = file.getBytes();
            fos = new FileOutputStream(path +  fileName);
            fos.write(fileData);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                }
            }
        }
    }

}
