package com.example.demo;


/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jinbiao
 * @create 2019/5/13 0013
 * @since 1.0.0
 */
public class ReadJsonFile {

    /*public final static HashMap<String, String> constant = new HashMap<String, String>() {{
        put("lanId", "认证地");
//        put("applyStaffName", "申请员工姓名");
//        put("applyStaffPhone", "申请员工电话");
//        put("oldExtPartyId", "原身份证编码");
       put("custName", "客户名称");
//        put("certType", "证件类型");
//        put("certNum", "证件号码");
//        put("orgUscc", "统一社会信用代码");
//        put("certPhoto", "证件照片");
//
//        put("partyCheckLogId", "认证日志唯一ID");
//        put("href", "引用地址");
//        put("ruleCode", "认证结果编码");
        put("ruleMsg", "认证结果");
//        put("jtCustName", "集团的客户名称");
//        put("workOrderNbr", "工单");
//        put("partyId", "集团参与人表id");
//        put("partyName", "身份证名称");
//        put("partyAbbrname", "身份证简称");
//        put("identityType", "身份证类型");
//        put("partyNumber", "身份证编码");
//        put("idEffDate", "身份证生效日期");
//        put("idExpDate", "身份证失效日期");
//        put("registerOrg", "登记机关");
//        put("orgProperty", "组织性质");
//        put("registerAddr", "注册地址");
//        put("industryTypeId", "行业类型标识");
//        put("legalRepr", "法定代表人");
//        put("econType", "经济类型");
//        put("busiScope", "经营范围");
//        put("orgAssets", "组织资产");
//        put("custBulidDate", "注册日期");
//        put("issueDate", "发照日期");
//        put("certOrg", "证件的发证机关");
//        put("orgStatusCd", "经营状态");
//        put("orgAddr", "生产经营地址");
//        put("suspectedCust", "疑似客户");
//
//        put("CREATED", "创建时间");
//        put("CREATED_BY", "创建人");
       put("ACCT_ID", "客户id");
//        put("ACCT_NAME", "客户名称");
//        put("ID_NUM", "证件号码");
//        put("ID_TYPE", "证件类型");
//        put("LOGIN", "登录工号");
//        put("STATUS", "调用状态");
//        put("DBMS_LOB.SUBSTR(A.REQ_STR)", "请求报文");
//        put("DBMS_LOB.SUBSTR(A.RESPONSE)", "返回报文");
//
//        put("ACCT_NAME_IN", "客户核查名称");
//        put("ACCT_NAME_OUT", "工商接口返回客户名称");
//        put("CALL_TYPE", "工商接口类型");
//        put("CHECK_RESULT", "核查结果");
//        put("ERROR_CODE", "状态码");
//        put("IDNUM", "证件号码");
    }};
    LinkedHashMap<String, String> result;

    public void read(String filePath) {
        LinkedHashSet<String> headerSet0 = new LinkedHashSet<>();
        LinkedHashSet<String> headerSet = new LinkedHashSet<>();
        LinkedHashSet<String> headerSet2 = new LinkedHashSet<>();

        ArrayList<String> arr = new ArrayList<>();
        int reqIndex = 0, respIndex = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyMMdd-HHmmss");
        String now = sdf.format(new Date());
        int index = new File(filePath).getAbsolutePath().indexOf(".csv");
        String filePath2 = filePath.substring(0, index) + "_" + now + ".csv";
        try {
            // 创建CSV读对象
            CsvReader csvReader = new CsvReader(filePath, ',', Charset.forName("GBK"));
            CsvReader csvReader2 = new CsvReader(filePath, ',', Charset.forName("GBK"));
            // 创建CSV写对象
            CsvWriter csvWriter = new CsvWriter(filePath2, ',', Charset.forName("GBK"));
            // 读表头,第一行
            csvReader.readHeaders();
            String[] headersAll = csvReader.getHeaders();
            for (int i = 0; i < headersAll.length; i++) {
                if ("DBMS_LOB.SUBSTR(A.REQ_STR)".equals(headersAll[i])) {
                    reqIndex = i;
                    continue;
                }
                if ("DBMS_LOB.SUBSTR(A.RESPONSE)".equals(headersAll[i])) {
                    respIndex = i;
                    continue;
                }
            }
            headerSet0.addAll(Arrays.asList(headersAll));

            while (csvReader.readRecord()) {
                result = new LinkedHashMap<>();
                String req = "";
                if (reqIndex != 0) {
                    req = csvReader.get(reqIndex);
                }
                if (!("".equals(req) || req == null)) {
                    result.clear();
                    json2map(req);
                    for (Map.Entry<String, String> entry : result.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        String head = constant.get(key);
                        headerSet.add(head);
                    }
                }
                String resp = "";
                if (respIndex != 0) {
                    resp = csvReader.get(respIndex);
                }
                if (!("".equals(resp) || resp == null)) {
                    result.clear();
                    json2map(resp);
                    for (Map.Entry<String, String> entry : result.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        String head = constant.get(key);
                        headerSet2.add(head);
                    }
                }
            }
            arr.addAll(headerSet0);
            arr.addAll(headerSet);
            arr.addAll(headerSet2);
            System.out.println("headerSet============" + arr);
            String[] header = arr.toArray(new String[arr.size()]);
            // 写表头
            csvWriter.writeRecord(header);
            csvWriter.flush();

            csvReader2.readHeaders();
            while (csvReader2.readRecord()) {
                String[] content = new String[arr.size()];
                result = new LinkedHashMap<>();
                for (int i = 0; i < headersAll.length; i++) {
                    if ("ID_NUM".equals(headersAll[i])) {
                        content[i] = "A" + csvReader2.get(i);
                    } else {
                        content[i] = csvReader2.get(i);
                    }
                }
                String req = "";
                if (reqIndex != 0) {
                    req = csvReader2.get(reqIndex);
                }
                if (!("".equals(req) || req == null)) {
                    result.clear();
                    json2map(req);
                    for (Map.Entry<String, String> entry : result.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        String head = constant.get(key);
                        for (int i = 0; i < headerSet.size(); i++) {
                            if (head.equals(headerSet.toArray()[i])) {
                                if ("certNum".equals(key) || "orgUscc".equals(key)) {
                                    value = "A" + value;
                                }
                                content[headerSet0.size() + i] = value;
                                break;
                            }
                        }
                    }
                }
                String resp = "";
                if (respIndex != 0) {
                    resp = csvReader2.get(respIndex);
                }
                if (!("".equals(resp) || resp == null)) {
                    result.clear();
                    json2map(resp);
                    for (Map.Entry<String, String> entry : result.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        String head = constant.get(key);
                        for (int i = 0; i < headerSet2.size(); i++) {
                            if (head.equals(headerSet2.toArray()[i])) {
                                if ("certNum".equals(key) || "orgUscc".equals(key)) {
                                    value = "A" + value;
                                }
                                content[i + headerSet0.size() + headerSet.size()] = value;
                                break;
                            }
                        }
                    }
                }
                csvWriter.writeRecord(content);
                csvWriter.flush();
            }
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void json2map(String string) {
        if (!("".equals(string) || string == null)) {
            LinkedHashMap<String, Object> jsonMap = JSON.parseObject(string, new TypeReference<LinkedHashMap<String, Object>>() {
            });
            for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof String) {
                    result.put(key, value.toString());
                } else if (value instanceof JSONArray) {
                    System.out.println(value + "=============" + value.toString());
                    result.put(key, value.toString());
                    *//*if(((JSONArray) value).size()>0) {
                        for (int i = 0; i < ((JSONArray) value).size(); i++) {
                            json2map(((JSONArray) value).get(i).toString());
                        }
                    }*//*
                } else {
                    json2map(value.toString());
                }
            }
        }
    }*/
}
