package knu.ialab.sponsor.common;

import knu.ialab.sponsor.bill.BillData;
import knu.ialab.sponsor.bill.BillSeparator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;


public class JsonUtils {

    // 1 : mallNo
    // 2 : authNo
    // 3 : totalPrice
    private static int code = 0;
    private String mallNo = "";
    private String authNo = "";
    private String totalPrice = "";

    public BillData parse(String json) {
        try {
            JSONObject jsonObj = new JSONObject(json);
            parse("", "", jsonObj, 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return BillData.builder().mallNo(mallNo).authNo(authNo).totalPrice(totalPrice).build();
    }


    public void parse(String grandParentKey, String parentKey, JSONObject json, int count) throws JSONException {
        @SuppressWarnings("unchecked")
        Iterator<String> keys = json.keys();

        while (keys.hasNext()) {
            String key = keys.next();
            Object val = null;

            val = json.get(key);

            if (val.getClass().getTypeName().contains("JSONArray")) {
                JSONArray jArr = (JSONArray) val;
                for (int i = 0; i < jArr.length(); i++) {
                    count++;
                    JSONObject childJSONObject = jArr.getJSONObject(i);
                    parse(parentKey, key, childJSONObject, count);
                }

            } else if (val.getClass().getTypeName().equals("org.json.JSONObject")) {
                parse(parentKey, key, (JSONObject) val, count);

            } else {
                if (val.getClass().getTypeName().toString().equals("org.json.JSONObject$Null")) {
                    val = "null";
                }
                String s1 = "";
                if (!grandParentKey.isEmpty()) {
                    s1 = grandParentKey + count + "." + parentKey + "." + key;
                } else if (!parentKey.isEmpty()) {
                    s1 = parentKey + count + "." + key;
                } else {
                    s1 = key;
                }
                String nowKey = s1;
                String value = val.toString();

                if (nowKey.contains("name")) {
                    if (BillSeparator.isMallNo(value)) {
                        code = 1;
                    } else if (BillSeparator.isAuthNo(value)) {
                        code = 2;
                    } else if (BillSeparator.isPrice(value)) {
                        code = 3;
                    }
                }

                if (nowKey.contains("inferText")) {
                    if (code == 1) {
                        mallNo = value;
                        code = 0;
                    } else if (code == 2) {
                        authNo = value;
                        code = 0;
                    } else if (code == 3) {
                        totalPrice = value;
                        code = 0;
                    }
                }

                System.out.println("KEY: " + s1);
                System.out.println("VALUE: " + val.toString());
                System.out.println("-----");
            }
        }
    }//End Method

}

    /*public BillData getValue(JSONObject jsonObject) {
        String fields = jsonObject.getString(BillSeparator.separator);

        JSONObject jsonFields = new JSONObject(fields);

        Map<String, Object> map = jsonFields.toMap();

        BillData billData = new BillData();
        billData.setAuthNo((String) map.get(BillSeparator.authSepar));
        billData.setAuthNo((String) map.get(BillSeparator.mallSepar));
        billData.setAuthNo((String) map.get(BillSeparator.priceSepar));

        return billData;
    }*/

