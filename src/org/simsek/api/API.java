package org.simsek.api;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import org.simsek.api.data.SimsekAPI;
import org.simsek.api.event.EventManager;
import org.simsek.api.event.EventTarget;
import org.simsek.api.event.impl.RequestEvent;
import org.simsek.api.type.APIType;
import org.simsek.json.JSONArray;
import org.simsek.json.JSONException;
import org.simsek.json.JSONObject;

/**
 * <strong>SIMSEK API</strong><br>
 * Tüm api'ler mevcut ve güncel.<br>
 * Herhangi bir aksilik durumunda <strong>Turkoglu-#0777</strong> ile iletişim'e geçiniz.
 * @author by furkan
 *
 */
public class API {

	private static String VERSION = "1.0.0";
	
	public final String key;
	public String web_url = "https://api.simsek.biz.tr/api?api=";
	
	/**
	 * Simsek api ile bağlantı kurmanızı sağlar.
	 * @apiNote SimsekAPI api = new API(key).connect();
	 * 
	 * @version 1.0.0
	 * @author by furkan
	 * @param key
	 */
	public API(String key) {
		this.key = key;
	}

	
	/**
	 * Simsek api verilerini çekmenizi sağlar.
	 * @return
	 */
	public SimsekAPI connect() {
		checkKey();
		String version = getLatestVersion();
		if(version != null && !version.equals(VERSION)) {
			log.info("Yeni bir güncelleme bulundu lütfen " + VERSION + " sürümünü indirin.");
			log.info("https://github.com/SimsekGrup/simsekapi-java/releases/tag/" + VERSION);
		}else {
			log.info("Güncelleme bulunamadı.");
		}
		log.info("Güncelleme bulunamadı.");
		return new SimsekAPI(this);
	}
	
    private boolean checkKey() {
    	JSONObject str = null;
		try {
			str = readJsonFromUrl(web_url + "askolcer&key=" + this.key, APIType.CheckAPIKey);
			if(str != null) {
				if(str.has("hata")) {
					log.info(str.getString("hata"));
				}else {
					return true;
				}
			}else {
				log.info("API Key kontrol edilirken hata oluştu...");
			}
		} catch (IOException e) {
			log.info("API ile iletişim kurulamadı lütfen tekrar deneyiniz.");
		} catch (JSONException e) {
			log.info("API ile iletişim kurulamadı lütfen tekrar deneyiniz.");
		}
		return false;
	}


    public JSONObject readJsonFromUrl(String url, APIType type) throws IOException, JSONException {
    	HttpURLConnection is = (HttpURLConnection) (new URL(url)).openConnection();
        try {
          BufferedReader rd = new BufferedReader(new InputStreamReader((is).getInputStream(), Charset.forName("UTF-8")));
          String jsonText = readAll(rd);
          JSONObject json = new JSONObject(jsonText);
          new RequestEvent(type, url).call();
          return json;
        } finally {
          is.disconnect();
        }
    }
    
    public JSONArray readJsonArrayFromUrl(String url, APIType type) throws IOException, JSONException {
    	HttpURLConnection is = (HttpURLConnection) (new URL(url)).openConnection();
        try {
          BufferedReader rd = new BufferedReader(new InputStreamReader((is).getInputStream(), Charset.forName("UTF-8")));
          String jsonText = readAll(rd);
          JSONArray json = new JSONArray(jsonText);
          new RequestEvent(type, url).call();
          return json;
        } finally {
          is.disconnect();
        }
    }
    
    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
          sb.append((char) cp);
        }
        return sb.toString();
      }
	
	
    private String getLatestVersion()
    {
    	JSONObject str = null;
		try {
			str = readJsonFromUrl("https://api.github.com/repos/SimsekGrup/simsekapi-java/releases/latest", APIType.VersionControl);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	
    	if(str != null)  {
    		if(str.has("tag_name")) {
    			try {
					return str.getString("tag_name");
				} catch (JSONException e) {
					e.printStackTrace();
				}
    		}
    	}
		return null;
    }
    
    
	static class log {
		private static void info(String str) {
			System.out.println(str);

		}
		
		private static void error(String str) {
			System.out.println(str);

		}
	}
    
	
}
