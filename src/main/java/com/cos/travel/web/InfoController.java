package com.cos.travel.web;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;



@Controller
public class InfoController {
	
    public static int PRETTY_PRINT_INDENT_FACTOR = 4;

	//JSON 파싱용
	@GetMapping("/etc/coronainfo")
	@ResponseBody
	public String coronaInfo() throws IOException {
		
		
		Calendar cal = Calendar.getInstance();
		String format ="yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		cal.add(cal.DATE, -1);
		String yesterday = sdf.format(cal.getTime());
		
//		System.out.println("어제날짜:"+yesterday);
		
		Calendar cal2 = Calendar.getInstance();
		String format2 ="yyyyMMdd";
		SimpleDateFormat sdf2 = new SimpleDateFormat(format2);
		String today = sdf2.format(cal2.getTime());
		
//		System.out.println("오늘날짜:"+today);
		
		
        StringBuilder urlBuilder = new StringBuilder("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=C7TQWZckjR7aJC72Xpst19alIqj9wPXlz2cIacI40%2Fiq64nRSS%2FX%2BlH51HE8XkqctubTUdxGeZd6XltoW84DEA%3D%3D"); /*Service Key*/
        //urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("-", "UTF-8")); /*공공데이터포털에서 받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("startCreateDt","UTF-8") + "=" + URLEncoder.encode(today, "UTF-8")); /*검색할 생성일 범위의 시작*/
        urlBuilder.append("&" + URLEncoder.encode("endCreateDt","UTF-8") + "=" + URLEncoder.encode(today, "UTF-8")); /*검색할 생성일 범위의 종료*/
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*JSON 파싱*/
        URL url = new URL(urlBuilder.toString());
        //System.out.println(url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
		return sb.toString();
	}
	
	@GetMapping("/etc/vcinfo")
	@ResponseBody
	public String vaccineInfo() throws IOException {
		
		Calendar cal = Calendar.getInstance();
		String format ="yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		cal.add(cal.DATE, -1);
		String yesterday = sdf.format(cal.getTime());
		
//		System.out.println("어제날짜:"+yesterday);
		
		Calendar cal2 = Calendar.getInstance();
		String format2 ="yyyyMMdd";
		SimpleDateFormat sdf2 = new SimpleDateFormat(format2);
		String today = sdf2.format(cal2.getTime());
		
//		System.out.println("오늘날짜:"+today);
		
		
        StringBuilder urlBuilder = new StringBuilder("https://nip.kdca.go.kr/irgd/cov19stats.do?list=sido"); /*URL*/
        //urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=C7TQWZckjR7aJC72Xpst19alIqj9wPXlz2cIacI40%2Fiq64nRSS%2FX%2BlH51HE8XkqctubTUdxGeZd6XltoW84DEA%3D%3D"); /*Service Key*/
        //urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("-", "UTF-8")); /*공공데이터포털에서 받은 인증키*/
       // urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        //urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
       // urlBuilder.append("&" + URLEncoder.encode("startCreateDt","UTF-8") + "=" + URLEncoder.encode(today, "UTF-8")); /*검색할 생성일 범위의 시작*/
        //urlBuilder.append("&" + URLEncoder.encode("endCreateDt","UTF-8") + "=" + URLEncoder.encode(today, "UTF-8")); /*검색할 생성일 범위의 종료*/
        //urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*json 파싱*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
//        System.out.println(sb.toString());
        
        String result = sb.toString();
        JSONObject xmlJSONObj = XML.toJSONObject(result);
        String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
//        System.out.println(jsonPrettyPrintString);
        
        
		return jsonPrettyPrintString;
	}
	
	
	//코로나 화면
	@GetMapping("/etc/info")
	public String corona() {
		return "etc/info";
	}
	
	
	//요일 구하기 21.10.12
	
	
}


