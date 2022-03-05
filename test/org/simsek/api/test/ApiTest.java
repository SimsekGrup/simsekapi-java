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
		 * API anahtar'� almak i�in: https://api.simsek.biz.tr
		 */
		SimsekAPI api = new API("API KEY").connect();

		try {
			
			JSONObject rss = api.rss("https://forum.truckersmp.com/index.php?/forum/5-news.xml");
			JSONArray icerikler = rss.getJSONArray("icerikler"); //Array
			 for(int i = 0; i < icerikler.length(); i++) {
			 	JSONObject icerik = icerikler.getJSONObject(i);
			 
			 }
			log.info("N�bet�i Eczane �rnek 'API' bilgisi �ekme.");
			log.info("");
			JSONArray ec = api.eczane("istanbul");
			/*
			 *  T�m n�bet�i eczaneleri �ekmek i�in foreach d�ng�s�ne al�n�z. 
			 *  
			 *  
			   for(int i = 0; i < ec.length(); i++) {
			 		JSONObject nobetcieczane = ec.getJSONObject(i);
			 		log.info("�l�e: " + nobetcieczane.getString("ilce"));
			 		log.info("�sim: " + nobetcieczane.getString("isim"));
			 		log.info("Telefon: " + nobetcieczane.getString("telefon"));
			 		log.info("Adres: " + nobetcieczane.getString("adres"));
			 		log.info("Konum: " + nobetcieczane.getString("konum"));		
			 	}
			 */

			JSONObject nobetcieczane = ec.getJSONObject(0); 
			log.info("�l�e: " + nobetcieczane.getString("ilce"));
			log.info("�sim: " + nobetcieczane.getString("isim"));
			log.info("Telefon: " + nobetcieczane.getString("telefon"));
			log.info("Adres: " + nobetcieczane.getString("adres"));
			log.info("Konum: " + nobetcieczane.getString("konum"));
			log.info("");
			log.info("Kekoca Yaz� Yazma");
			String ea = api.keko("�im�ek Api m�kemmeldir.").getString("yazi");
			log.info("");
			log.info(ea);
			log.info("");
			log.info("Rastgele S�z Apisi");
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
			log.error("Sunucuyla ba�lant� kurulamad� veya beklenmedik bir hata. " + e.getLocalizedMessage());
		} catch (JSONException e) {
			log.error("Web site ile ilgili bir sorun olu�tu. " + e.getLocalizedMessage());
		}
		System.exit(0);
	}
	
	/*
	 * Uygulaman�zda api request ba�ar�yla att��� zaman belirtin.
	 */
    @EventTarget(value = EventPriority.HIGHEST)
    public void on(RequestEvent event) {
    	log.info(event.getApiType().getName() + " adl� request ba�ar�l�.");
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
