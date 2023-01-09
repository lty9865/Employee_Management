package common.openApi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.management.modelmbean.XMLParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class OpenApiWeather {
	private WeatherVo weather;

	enum WeatherValue {
		PTY, REH, RN1, T1H, UUU, VEC, VVV, WSD
	}

	public OpenApiWeather() throws Exception {

		// 입력받을 weather 값
		weather = new WeatherVo();

		// 변수
		String apiKey = "SjVE%2B9i59xL2Cf9ggEM87d6ne64vcYR8EQmmYekfqu3u%2Bc2pWafHwzUkYFFwHEfXVlXgfWE1wObwzNvAG6%2BX%2Fg%3D%3D";

		// 구하고자 하는 시간, 좌표 설정
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String strDate = sdf.format(date);

		LocalTime now = LocalTime.now().minusHours(1);
		DateTimeFormatter df = DateTimeFormatter.ofPattern("HH");
		String strNow = now.format(df) + "30";
		System.out.println(strNow);

		String nx = "62";
		String ny = "123";
		String baseDate = strDate;
		String baseTime = strNow;
//		String baseTime = "2030";

//    	String dataType = "JSON"; 

		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + apiKey); /* Service Key */
		urlBuilder
				.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("1000", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "="
				+ URLEncoder.encode("XML", "UTF-8")); /* 요청자료형식(XML/JSON) Default: XML */
		urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "="
				+ URLEncoder.encode(baseDate, "UTF-8")); /* ‘21년 6월 28일 발표 */
		urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "="
				+ URLEncoder.encode(baseTime, "UTF-8")); /* 06시 발표(정시단위) */
		urlBuilder.append(
				"&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); /* 예보지점의 X 좌표값 */
		urlBuilder.append(
				"&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); /* 예보지점의 Y 좌표값 */
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
		String result = sb.toString();

		// 문자열 Document 로 변경 후 List 형태로 가져와 파싱
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(result));
		Document document = db.parse(is);

		try {
			document.getDocumentElement().normalize();
			System.out.println("Root Element :" + document.getDocumentElement().getNodeName());
			NodeList nList = document.getElementsByTagName("item");
			System.out.println("-------------------------------------");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					String category = eElement.getElementsByTagName("category").item(0).getTextContent();
					double value = Double
							.parseDouble(eElement.getElementsByTagName("obsrValue").item(0).getTextContent());

					WeatherValue weatherValue = WeatherValue.valueOf(category);

					switch (weatherValue) {
					case PTY:
						weather.setPTY(value);
						break;
					case REH:
						weather.setREH(value);
						break;
					case RN1:
						weather.setRN1(value);
						break;
					case T1H:
						weather.setT1H(value);
						break;
					case UUU:
						weather.setUUU(value);
						break;
					case VEC:
						weather.setVEC(value);
						break;
					case VVV:
						weather.setVVV(value);
						break;
					case WSD:
						weather.setWSD(value);
						break;
					default:
						throw new XMLParseException();
					}
				}
			}
			System.out.println(weather.toString());
		} catch (XMLParseException e) {
			e.printStackTrace();
		}
	}

	public WeatherVo weatherVo() {
		return weather;
	}
}