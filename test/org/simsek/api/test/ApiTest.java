package org.simsek.api.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.simsek.api.API;
import org.simsek.api.data.SimsekAPI;
import org.simsek.api.event.EventPriority;
import org.simsek.api.event.EventTarget;
import org.simsek.api.event.impl.RequestEvent;
import org.simsek.json.JSONArray;
import org.simsek.json.JSONException;
import org.simsek.json.JSONObject;

public class ApiTest {

	
	public static void main(String[] args) {
		
		
		/**
		 * API anahtar'ý almak için: https://api.simsek.biz.tr
		 */
		SimsekAPI api = new API("API KEY").connect();

		try {
			
			JSONObject rss = api.rss("https://forum.truckersmp.com/index.php?/forum/5-news.xml");
			JSONArray icerikler = rss.getJSONArray("icerikler"); //Array
			 for(int i = 0; i < icerikler.length(); i++) {
			 	JSONObject icerik = icerikler.getJSONObject(i);
			 
			 }
			log.info("Nöbetçi Eczane örnek 'API' bilgisi çekme.");
			log.info("");
			JSONArray ec = api.eczane("istanbul");
			/*
			 *  Tüm nöbetçi eczaneleri çekmek için foreach döngüsüne alýnýz. 
			 *  
			 *  
			   for(int i = 0; i < ec.length(); i++) {
			 		JSONObject nobetcieczane = ec.getJSONObject(i);
			 		log.info("Ýlçe: " + nobetcieczane.getString("ilce"));
			 		log.info("Ýsim: " + nobetcieczane.getString("isim"));
			 		log.info("Telefon: " + nobetcieczane.getString("telefon"));
			 		log.info("Adres: " + nobetcieczane.getString("adres"));
			 		log.info("Konum: " + nobetcieczane.getString("konum"));		
			 	}
			 */

			JSONObject nobetcieczane = ec.getJSONObject(0); 
			log.info("Ýlçe: " + nobetcieczane.getString("ilce"));
			log.info("Ýsim: " + nobetcieczane.getString("isim"));
			log.info("Telefon: " + nobetcieczane.getString("telefon"));
			log.info("Adres: " + nobetcieczane.getString("adres"));
			log.info("Konum: " + nobetcieczane.getString("konum"));
			log.info("");
			log.info("Kekoca Yazý Yazma");
			String ea = api.keko("Þimþek Api mükemmeldir.").getString("yazi");
			log.info("");
			log.info(ea);
			log.info("");
			log.info("Rastgele Söz Apisi");
			log.info("");
			JSONObject soz = api.soz();
			log.info(soz.getString("soz"));
			log.info("");
			log.info("Rastgele Espiri");
			String as = api.espiri().getString("espri");
			log.info("");
			log.info(as);
			log.info("");
		} catch (IOException e) {
			log.error("Sunucuyla baðlantý kurulamadý veya beklenmedik bir hata. " + e.getLocalizedMessage());
		} catch (JSONException e) {
			log.error("Web site ile ilgili bir sorun oluþtu. " + e.getLocalizedMessage());
		}
		System.exit(0);
	}
	
	/*
	 * Uygulamanýzda api request baþarýyla attýðý zaman belirtin.
	 */
    @EventTarget(value = EventPriority.HIGHEST)
    public void on(RequestEvent event) {
    	log.info(event.getApiType().getName() + " adlý request baþarýlý.");
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
