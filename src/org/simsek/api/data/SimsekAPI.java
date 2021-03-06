package org.simsek.api.data;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.imageio.ImageIO;

import org.simsek.api.API;
import org.simsek.api.event.impl.RequestEvent;
import org.simsek.api.type.APIType;
import org.simsek.json.JSONArray;
import org.simsek.json.JSONException;
import org.simsek.json.JSONObject;

public class SimsekAPI {
	
	private final API api;
	
	public SimsekAPI(API api) {
		this.api = api;
	}
	
	/**
	 * 8-Ball'a ait rastgele cevaplar? ?ekmenize yarar.
	 * @return 8ball
	 * @throws JSONException 
	 * @throws IOException 
	 * 
	 */
	public JSONObject ball() throws IOException, JSONException {
		return api.readJsonFromUrl(this.api.web_url + "8ball&key=" + this.api.key, APIType.Ball);	
	}
	
	/**
	 * Rastgele Atat?rk resimlerini ?ekmenize yarar.
	 * @return ataturk
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONObject ataturk() throws IOException, JSONException {
		return api.readJsonFromUrl(this.api.web_url + "ataturk&key=" + this.api.key, APIType.Ataturk);	
	}
	
	/**
	 * ?nl? ki?ilere ait rastgele s?zleri ?ekmenize yarar.
	 * @return soz
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONObject soz() throws IOException, JSONException {
		return api.readJsonFromUrl(this.api.web_url + "soz&key=" + this.api.key, APIType.Soz);	
	}
	
	/**
	 * Rastgele esprileri ?ekmenize yarar.
	 * @return espri
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONObject espiri() throws IOException, JSONException {
		return api.readJsonFromUrl(this.api.web_url + "espri&key=" + this.api.key, APIType.Espiri);	
	}
	
	/**
	 * Rastgele olarak belirlenen a?k ?l?er verilerini ?ekmenize yarar.
	 * @return askSeviye, askSeviyeSonuc, askSeviyeDc
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONObject askolcer() throws IOException, JSONException {
		return api.readJsonFromUrl(this.api.web_url + "askolcer&key=" + this.api.key, APIType.AskOlcer);	
	}
	
	
	/**
	 * Yazm?? oldu?unuz yaz?y? kekoca yazd?rman?za yarar.
	 * @return yazi
	 * @throws JSONException
	 * @throws IOException
	 * 
	 */
	public JSONObject keko(String yazi) throws IOException, JSONException {
		return api.readJsonFromUrl(this.api.web_url + "keko&key=" + this.api.key + "&yazi=" + URLEncoder.encode(yazi, StandardCharsets.UTF_8.name()), APIType.Keko);	
	}
	
	/**
	 * ?stenilen uzunlukta ve ?zellikte ?ifre olu?turman?za yarar.
	 * @return sifre
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONObject sifre(int uzunluk) throws IOException, JSONException {
		return api.readJsonFromUrl(this.api.web_url + "sifre&key=" + this.api.key + "&uzunluk=" + uzunluk, APIType.Sifre);	
	}
	
	/**
	 * ?stenilen uzunlukta ve ?zellikte ?ifre olu?turman?za yarar.
	 * @return sifre
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONObject sifre(int uzunluk, boolean rakam, boolean sembol) throws IOException, JSONException {
		return api.readJsonFromUrl(this.api.web_url + "sifre&key=" + this.api.key + "&uzunluk=" + uzunluk + "&rakam=" + rakam + "&sembol=" + sembol, APIType.Sifre);	
	}
	
	/**
	 * Renk koduna ait bilgileri bulman?za yarar.
	 * @return renkismi, kod, karesiz, rgb, hsl, hsv, cmyk, xyz
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONObject renk(String renk) throws IOException, JSONException {
		return api.readJsonFromUrl(this.api.web_url + "renk&key=" + this.api.key + "&renk=" + URLEncoder.encode(renk, StandardCharsets.UTF_8.name()), APIType.Renk);	
	}
	
	/**
	 * ?stenilen resme istenilen efekti vermenize yarar.
	 * ?uanan aktif olarak ?al??an efektler: turkbayragi, azebayragi, triggered, wasted, erdoganselfie, kediselfie, kopekselfie, rainbow, tbc, karefekti, hapisefekti
	 * @return Buffer (resim)
	 * @throws IOException 
	 * 
	 */
	public BufferedImage resimefekti(String efekt, String resim) throws IOException {
		String url = this.api.web_url + "resimefekti&key=" + this.api.key + "&efekt=" + efekt + "&resim=" + resim;
		new RequestEvent(APIType.ResimEfekti, url).call();
		return ImageIO.read(new URL(url));
	}
	
	
	/**
	 * ?stenilen RSS dosyas?n? JSON format?na ?evirmenize yarar.
	 * @return icerikler (ARRAY)
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONObject rss(String rss_url) throws IOException, JSONException {
		return api.readJsonFromUrl(this.api.web_url + "rss&key=" + this.api.key + "&rss=" + rss_url, APIType.Rss);	
	}
	
	/**
	 * ?stedi?iniz metni/yaz?y? ?evirmenize yarar.
	 * ?evrilecek ?eyin dilini otomatik alg?latmak i?in metindili k?sm?n? "oto" yapman?z gerekiyor.
	 * Desteklenen diller: af, sq, ar, hy, az, eu, be, bn, bs, bg, ca, ceb, ny, zh-cn, zh-tw, co, hr, cs, da, nl, en, eo, et, tl, fi, fr, fy, gl, ka, de, el, gu, ht, ha, haw, iw, hi, hmn, hu, is, ig, id, ga, it, ja, jw, kn, kk, km, ko, ky, lo, la, lv, lt, lb, mk, mg, ms, ml, mt, mi, mr, mn, my, ne, no, ps, fa, pl, pt, pa, ro, ru, sm, gd, sr, st, sn, sd, si, sk, sl, so, es, su, sw, sv, tg, ta, te, th, tr, uk, ur, uz, vi, cy, xh, yi, yo, zu
	 * @return metinDiliKisa, metinDiliUzun, hedefDilKisa, hedefDilUzun, ceviri
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONObject ceviri(String metin, String metin_dili, String hedef_dili) throws IOException, JSONException {
		return api.readJsonFromUrl(this.api.web_url + "ceviri&key=" + this.api.key + "&metin=" + URLEncoder.encode(metin, StandardCharsets.UTF_8.name()) + "&metindili=" + metin_dili + "&hedefdil=" + hedef_dili, APIType.Ceviri);	
	}
	
	/**
	 * ?stedi?iniz ile ait namaz vakitlerini ?ekmenize yarar.
	 * @return il, imsak, gunes, ogle, ikindi, aksam, yatsi, yaklasanVakit, yaklasanVakteKalan, ramazanAyi, ramazanVakit, ramazanKalan
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONObject namaz(String il) throws IOException, JSONException {
		return api.readJsonFromUrl(this.api.web_url + "namaz&key=" + this.api.key + "&il=" + URLEncoder.encode(il, StandardCharsets.UTF_8.name()), APIType.Namaz);	
	}
	
	/**
	 * ?stedi?iniz ile ait hava durumunu ?ekmenize yarar.
	 * @return il, enlem, boylam, sicaklikDerece, sicaklikFahrenheit, enYuksekSicaklikDerece, enYuksekSicaklikFahrenheit, enDusukSicaklikDerece, enDusukSicaklikFahrenheit, havaOlayi, havaOlayiResmi, bulutOrani, nem, basinc, ruzgarYonu, ruzgarHizi, gunDogumu, gunBatimi
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONObject hava(String il) throws IOException, JSONException {
		return api.readJsonFromUrl(this.api.web_url + "hava&key=" + this.api.key + "&il=" + URLEncoder.encode(il, StandardCharsets.UTF_8.name()), APIType.Hava);	
	}
	
	/**
	 * Resimdeki renkleri bulman?za yarar.
	 * @return hex, rgb
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONObject renkbul(String resim) throws IOException, JSONException {
		return api.readJsonFromUrl(this.api.web_url + "renkbul&key=" + this.api.key + "&resim=" + resim, APIType.Renkbul);	
	}
	
	/**
	 * ?stedi?iniz ile ait n?bet?i eczaneleri bulman?za yarar.
	 * T?rkiye s?n?rlar? i?inde bulunan illeri ve K?br?s'? (sadece ?lke olarak) destekler.
	 * @return isim, ilce, telefon, konum, adres (Array)
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONArray eczane(String il) throws IOException, JSONException {
		return api.readJsonArrayFromUrl(this.api.web_url + "eczane&key=" + this.api.key + "&il=" + URLEncoder.encode(il, StandardCharsets.UTF_8.name()), APIType.Eczane);	
	}
	
	
	/**
	 * ?stedi?iniz ?ark?ya ait ?ark? s?z? dahil bir ?ok bilgiyi ?ekmenize yarar.
	 * @return sarkici, sarkiIsmi, tamIsim, albumKapagi, url, soz
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONObject sarkisozu(String sarki) throws IOException, JSONException {
		return api.readJsonFromUrl(this.api.web_url + "sarkisozu&key=" + this.api.key + "&sarki=" + URLEncoder.encode(sarki), APIType.SarkiSozu);	
	}
	
	/**
	 * ?stenilen bur? yorumlar?n? ?ekmenize yarar.
	 * @return burc, burcResmi, burcMottosu, burcGunu, burcunGezegeni, burcunElementi, burcunNiteligi, burcunUgurluSayilari, burcunUgurluGunu, burcunUgurluRengi, burcunOlumluOzellikleri, burcunOlumsuzOzellikeri, gunlukYorum, haftalikYorum, aylikYorum
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONObject burc(String burc) throws IOException, JSONException {
		return api.readJsonFromUrl(this.api.web_url + "burc&key=" + this.api.key + "&burc=" + URLEncoder.encode(burc, StandardCharsets.UTF_8.name()), APIType.Burc);	
	}
	
	/**
	 * Vikipedi'den arad???n?z ?eyin sonu?lar?n? ?ekmenize yarar.
	 * @return baslik, kisaAciklama, uzunAciklama, resim, url
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONObject vikipedi(String ara) throws IOException, JSONException {
		return api.readJsonFromUrl(this.api.web_url + "vikipedi&key=" + this.api.key + "&ara=" + URLEncoder.encode(ara, StandardCharsets.UTF_8.name()), APIType.VikiPedi);	
	}
	
	
	/**
	 * TruckersMP sunucu istatistiklerini ?ekmenize yarar.
	 * @return baslik, kisaAciklama, uzunAciklama, resim, url (Array)
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONArray tmp(String oyun) throws IOException, JSONException {
		return api.readJsonArrayFromUrl(this.api.web_url + "tmp&key=" + this.api.key + "&oyun=" + URLEncoder.encode(oyun, StandardCharsets.UTF_8.name()), APIType.Tmp);	
	}
	
	/**
	 * Tarihte bug?n olan olaylar? ?ekmenize yarar.
	 * @return baslik, kisaAciklama, uzunAciklama, resim, url
	 * @throws JSONException
	 * @throws IOException
	 * 
	 */
	public JSONObject tarihtebugun() throws IOException, JSONException {
		return api.readJsonFromUrl(this.api.web_url + "tarihtebugun&key=" + this.api.key, APIType.TarihteBugun);	
	}
	
	/**
	 * ?stenilen kategorideki haberleri ?ekmenize yarar.<br>
	 * <strong>Kategoriler:</strong> guncel, egitim, dunya, koronavirus, ekonomi, spor, bilim-teknoloji, politika, yasam, saglik
	 * @return baslik, aciklama, url, yayinTarihi
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONArray haberler(String kategori) throws IOException, JSONException {
		return api.readJsonArrayFromUrl(this.api.web_url + "haberler&key=" + this.api.key + "&kategori=" + kategori, APIType.Haberler);	
	}
	
	/**
	 * Instagram kullan?c?s?n?n bilgilerini ?ekmenize yarar.<br>
	 * "islem" parametresine gonderi veya kullanici yazarak arama yapabilirsiniz.<br>
	 * Gonderi de?eri i?in "url", kullanici de?eri i?in "kullanici" parametreleri girilmelidir. <br>
	 * "duzenle" parametresini girmedi?inizde Instagram'?n sa?lad??? b?t?n bilgilere eri?ebilirsiniz, ancak sade bir ??kt? almak istiyorsan?z bu parametreyi aktif etmeniz ?nerilir.
	 * @return *bad
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONObject instagram(String kullanici, String islem, boolean duzenle) throws IOException, JSONException {
		return api.readJsonFromUrl(this.api.web_url + "instagram&key=" + this.api.key + "&kullanici=" + kullanici + "&islem=" + islem + "&duzenle=" + duzenle, APIType.Instagram);	
	}
	
	/**
	 * Sinemada vizyona giren/girecek filmlerin bilgilerini ?ekmenize yarar. "vizyon" parametresine 'suan' veya 'gelecek' yazarak sonu? alabilirsiniz.
	 * @return Array(filmIsmi, filmAfisiKucuk, filmAfisiBuyuk, filmTuru, filmSuresi, filmYonetmeni, filmSenaristi, filmVizyonTarihi, filmOyunculari, filmOzeti)
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONArray sinema(String vizyon) throws IOException, JSONException {
		return api.readJsonArrayFromUrl(this.api.web_url + "sinema&key=" + this.api.key + "&vizyon=" + vizyon, APIType.Sinema);	
	}
	
	/**
	 * TV'de bug?n yay?nlanacak programlar? ?ekmenize yarar.
	 * <strong>Desteklenen TV'ler:</strong> kanal-d, star-tv, fox, show-tv, atv, trt-1, tv8, cartoon-network, teve2, beyaz-tv, a2, fb-tv, disney-channel, tlc, kanal-7, dmax, trt-2, krt-tv, tele-1, cnn-turk, tv-100, bein-movies-turk, bein-sports-haber, disney-junior, 360, halk-tv, trt-cocuk, bein-movies-premiere, bein-movies-action, nick-jr, bein-sports-1, trt-spor, nickelodeon, bein-gurme, bein-movies-stars, bein-iz, bein-movies-family, bein-sports-2, bein-movies-action-2, eurosport-1, gs-tv, tjk-tv, trt-muzik, haberturk, bein-movies-premiere-2, best-fm, bein-sports-4, eurosport-2, a-spor, bein-series-sci-fi
	 * @return tv, programlar: Array(program, programBaslama, programBitis)
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONObject tvdebugun(String tv) throws IOException, JSONException {
		return api.readJsonFromUrl(this.api.web_url + "sinema&key=" + this.api.key + "&tv=" + tv, APIType.TVdeBugun);	
	}
	
	/**
	 * TV'de ?u an yay?nlanan programlar? ?ekmenize yarar.<br>
	 * Desteklenen TV'ler, TV'de bug?n API'siyle ayn?d?r.
	 * @return Array(kanal, kod, suanIsim, suanBaslama, suanBitis, sonrakiIsim, sonrakiBaslama, sonrakiBitis)
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONArray tvdesuan() throws IOException, JSONException {
		return api.readJsonArrayFromUrl(this.api.web_url + "sinema&key=" + this.api.key, APIType.TVdeSuan);	
	}
	
	/**
	 * D?viz bilgilerini TL baz?nda anl?k olarak ?ekmenize yarar.<br>
	 * <strong>Kullan?labilir d?viz k?saltmalar?:</strong> GEL, TND, USD, EUR, GBP, CAD, DKK, SEK, CHF, NOK, JPY, AED, AUD, RUB, KWD, ZAR, BHD, LYD, SAR, IQD, ILS, IRR, INR, MXN, HUF, NZD, BRL, IDR, CSK, PLN, BGN, RON, CNY, ARS, ALL, AZN, BAM, BYR, CLP, COP, CRC, DZD, EGP, HKD, HRK, ISK, JOD, KRW, KZT, LBP, LKR, LTL, LVL, MAD, MDL, MKD, MYR, OMR, PEN, PHP, PKR, QAR, RSD, SGD, SYP, THB, TWD, UAH, UYU
	 * @return kod, isim, alis, enDusuk, enYuksek, degisim, degisimYuzdesi, kapanis, guncellenmeTarihi
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONObject doviz(String doviz) throws IOException, JSONException {
		return api.readJsonFromUrl(this.api.web_url + "doviz&key=" + this.api.key + "&doviz=" + doviz, APIType.Doviz);	
	}
	
	/**
	 * Alt?n bilgilerini TL baz?nda anl?k olarak ?ekmenize yarar.<br>
	 * <strong>Kullan?labilir altin birimleri:</strong> 14, 18, 22, ATA, BSL, C, CMR, GA, GAG, GR, HA, IKB, T, ONS, Y
	 * @return kod, isim, alis, enDusuk, enYuksek, degisim, degisimYuzdesi, kapanis, guncellenmeTarihi
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONObject altin(String altin) throws IOException, JSONException {
		return api.readJsonFromUrl(this.api.web_url + "altin&key=" + this.api.key + "&altin=" + altin, APIType.Altin);
	}
	
	/**
	 * Parite bilgilerini anl?k olarak ?ekmenize yarar. <br>
	 * <strong>Kullan?labilir altin birimleri:</strong> 14, 18, 22, ATA, BSL, C, CMR, GA, GAG, GR, HA, IKB, T, ONS, Y
	 * @return kod, isim, alis, enDusuk, enYuksek, degisim, degisimYuzdesi, kapanis, guncellenmeTarihi
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONObject parite(String parite) throws IOException, JSONException {
		return api.readJsonFromUrl(this.api.web_url + "parite&key=" + this.api.key + "&parite=" + parite, APIType.Parite);	
	}
	
	/**
	 * Kripto para bilgilerini dolar ($) baz?nda anl?k olarak ?ekmenize yarar.<br>
	 * <strong>Kullan?labilir kripto paralar:</strong> BTC, ETH, XRP, BCH, LTC, USDT, EOS, ADA, XMR, VEN, TRX, POE, MANA, MTH, ELF, STRAT, XZC, WTC, ENJ, ZRX, ZEC, ZEN, ZIL, AE, ALGO, ARDR, ARK, BAT, REP, AOA, BNB, BSV, BCD, BTG, BTS, BTT, BTMX, BCN, BTM, XLINK, CRO, DAI, DCR, DENT, DGB, XMX, WAXP, ATOM, DOGE, EGT, ELA, NRG, GNT, GRIN, GXC, HOT, HT, KMD, KCS, LAMB, LEO, LSK, MAID, MKR, ETP, MONA, NANO, NAS, XEM, NEX, NEXO, NULS, OKB, ONT, PAX, NPXS, QTUM, QNT, QKC, RVN, REN, RR, SC, SOLVE, SNT, STEEM, XTZ, THETA, TUSD, USDC, VSYS, XVG, MCO, XAUT, NMR, CKB, DIVI, DX, CEL, STX, BUSD, HBAR, ETN, HUSD, GT, HYN, UBT, LUNA, MATIC, HIVE, FTT, KNC, UMA, CHZ, CETH, LRC, HAV, DOT, CELO, YFI, CHSB, AVAX, UNI, NXM, AMP, CRV, AAVE, CDAI, CAKE, 1INCH, RENBTC, NEAR, RSR, ALPHA, SOL, COMP, WBTC, KSM, FIL, BAL, EGLD, RUNE, CUSDC, SUSHI, GRT, OCEAN, ETHOS, FTM, AMPL, HBTC, MIOTA, WAVES, PPT, XLM, REQ, HSR, IOST, CND, VIBE, LUN, VIB, CMT, ICX, ETC, OMG, NEO, DSH
	 * @return kod, isim, alis, enDusuk, enYuksek, degisim, degisimYuzdesi, kapanis, guncellenmeTarihi
	 * @throws JSONException
	 * @throws IOException 
	 * 
	 */
	public JSONObject kripto() throws IOException, JSONException {
		return api.readJsonFromUrl(this.api.web_url + "kripto&key=" + this.api.key, APIType.Kripto);	
	}
	
	/**
	 * Kripto para bilgilerini dolar ($) baz?nda anl?k olarak ?ekmenize yarar.<br>
	 * <strong>Kullan?labilir kripto paralar:</strong> BTC, ETH, XRP, BCH, LTC, USDT, EOS, ADA, XMR, VEN, TRX, POE, MANA, MTH, ELF, STRAT, XZC, WTC, ENJ, ZRX, ZEC, ZEN, ZIL, AE, ALGO, ARDR, ARK, BAT, REP, AOA, BNB, BSV, BCD, BTG, BTS, BTT, BTMX, BCN, BTM, XLINK, CRO, DAI, DCR, DENT, DGB, XMX, WAXP, ATOM, DOGE, EGT, ELA, NRG, GNT, GRIN, GXC, HOT, HT, KMD, KCS, LAMB, LEO, LSK, MAID, MKR, ETP, MONA, NANO, NAS, XEM, NEX, NEXO, NULS, OKB, ONT, PAX, NPXS, QTUM, QNT, QKC, RVN, REN, RR, SC, SOLVE, SNT, STEEM, XTZ, THETA, TUSD, USDC, VSYS, XVG, MCO, XAUT, NMR, CKB, DIVI, DX, CEL, STX, BUSD, HBAR, ETN, HUSD, GT, HYN, UBT, LUNA, MATIC, HIVE, FTT, KNC, UMA, CHZ, CETH, LRC, HAV, DOT, CELO, YFI, CHSB, AVAX, UNI, NXM, AMP, CRV, AAVE, CDAI, CAKE, 1INCH, RENBTC, NEAR, RSR, ALPHA, SOL, COMP, WBTC, KSM, FIL, BAL, EGLD, RUNE, CUSDC, SUSHI, GRT, OCEAN, ETHOS, FTM, AMPL, HBTC, MIOTA, WAVES, PPT, XLM, REQ, HSR, IOST, CND, VIBE, LUN, VIB, CMT, ICX, ETC, OMG, NEO, DSH
	 * @return kod, isim, alis, enDusuk, enYuksek, degisim, degisimYuzdesi, kapanis, guncellenmeTarihi
	 * @throws JSONException
	 * @throws IOException
	 * 
	 */
	public JSONObject kripto(String kripto) throws IOException, JSONException {
		return api.readJsonFromUrl(this.api.web_url + "kripto&key=" + this.api.key + "&kripto=" + kripto, APIType.Kripto);	
	}
	
	
}
