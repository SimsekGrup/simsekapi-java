# **Simsek API Java**

Merhaba, Ben furkan **SimsekAPI** için request'lere gerek kalmadan daha kolay ve basit bir şekilde veri çekebilmeniz için bir java kütüphanesi yaptım iyi kullanımlar. 🥰😋


# API ile bağlantı kurma

```java
/*
 * API anahtar'ı almak için: https://api.simsek.biz.tr
 */
SimsekAPI api = new API("API KEY").connect();
```
# Mevcut API'ler Çekme Biçimleri
```java
//8-Ball'a ait rastgele cevapları çekmenize yarar.
JSONObject ball = api.ball();
ball.getString("8ball"); // Örnek Çıktı: Şimdi söylemesem daha iyi

//Rastgele Atatürk resimlerini çekmenize yarar.
JSONObject ataturk = api.ataturk();
ataturk.getString("ataturk"); //Örnek Çıktı: https://disk.simsek.biz.tr/api/ataturk/1%20%28382%29.jpg

//Ünlü kişilere ait rastgele sözleri çekmenize yarar.
JSONObject soz = api.soz();
soz.getString("soz"); //Örnek Çıktı: Cahil kimsenin yanında kitap gibi sessiz ol.\n- Mevlânâ Celâleddîn-i Rûmî (Şair)

//Rastgele espirileri çekmenize yarar.
JSONObject espri = api.espri();
espiri.getString("espri"); //Örnek Çıktı: Röntgen Filmi çektirdik, yakında sinemalarda.

//Rastgele olarak belirlenen aşk ölçer verilerini çekmenize yarar.
JSONObject askolcer = api.askolcer();
askolcer.getInt("askSeviye"); // 62
askolcer.getString("askSeviyeSonuc"); // ♥♥♥♥♥♥🖤🖤🖤🖤
askolcer.getString("askSeviyeDc"); // :heart::heart::heart::heart::heart::heart::black_heart::black_heart::black_heart::black_heart:

//Yazmış olduğunuz yazıyı kekoca yazdırmanıza yarar.
JSONObject keko = api.keko("Şimşek Api mükemmeldir.");
keko.getString("yazi"); // ŞİmŞEK Api müKEmmelDİR.

//İstenilen uzunlukta ve özellikte şifre oluşturmanıza yarar.
JSONObject sifre = api.sifre(16); //uzunluk
sifre.getString("sifre"); //ChLnSMKsufHvDamx

//İstenilen uzunlukta ve özellikte şifre oluşturmanıza yarar.
JSONObject sifre = api.sifre(16, true, true); //uzunluk - rakam - sembol
sifre.getString("sifre"); //w>IqKt-ZA2uU0wV

//Renk koduna ait bilgileri bulmanıza yarar.
JSONObject renk = api.renk("4C3FED");
renk.getString("renkismi") //Royal Blue
renk.getString("kod") //#4C3FED
JSONArray rgb = renk.getJSONArray("rgb") //Array
rgb.getString("deger"); //76, 63, 237
rgb.getString("tam"); //rgb(76, 63, 237)
rgb.getString("r"); //76
rgb.getString("g"); //63
rgb.getString("b"); //237
JSONArray hsl = renk.getJSONArray("hsl") //Array
hsl.getString("deger"); //244, 83, 59
hsl.getString("tam"); //hsl(244, 83%, 59%)
hsl.getString("h"); //244
hsl.getString("s"); //83%
hsl.getString("l"); //59%
JSONArray hsv = renk.getJSONArray("hsv") //Array
hsv.getString("deger"); //244, 73, 93
hsv.getString("tam"); //hsv(244, 73%, 93%)
hsv.getString("h"); //244
hsv.getString("s"); //73
hsv.getString("v"); //93
JSONArray cmyk = renk.getJSONArray("cmyk") //Array
cmyk.getString("deger"); //68, 73, 0, 7
cmyk.getString("tam"); //cmyk(68, 73, 0, 7)
cmyk.getString("c"); //68
cmyk.getString("m"); //73
cmyk.getString("y"); //0
cmyk.getString("k"); //7
JSONArray xyz = renk.getJSONArray("xyz") //Array
xyz.getString("deger"); //38, 31, 92
xyz.getString("tam"); //XYZ(38, 31, 92)
xyz.getString("x"); //38
xyz.getString("y"); //31
xyz.getString("z"); //92

//İstenilen resme istenilen efekti vermenize yarar.
//turkbayragi, azebayragi, triggered, wasted, erdoganselfie, kediselfie, kopekselfie, rainbow, tbc, karefekti, hapisefekti
BufferedImage resimefekti = api.resimefekti("kediselfie", "https://cdn.discordapp.com/avatars/524552476444196885/7f90be98428006d7ddb79ac9e705abfa.png?size=2048") //efekt, resim url

//Java projenizde resmi nasıl çekmek istiyorsanız orası size kalmış Base-64 yapmak istiyorsanız alttaki kodu'da kullanabilirsiniz.
public static String imgToBase64String(final RenderedImage img, final String formatName) {
    final ByteArrayOutputStream os = new ByteArrayOutputStream();
    try (final OutputStream b64os = Base64.getEncoder().wrap(os)) {
        ImageIO.write(img, formatName, b64os);
    } catch (final IOException ioe) {
        throw new UncheckedIOException(ioe);
    }
    return os.toString();
}
//Örnek: 
String base64 = imgToBase64String(resimefekti, "png");

//İstenilen RSS url'sini JSON formatına çevirmenize yarar.
JSONObject rss = api.rss("https://forum.truckersmp.com/index.php?/forum/5-news.xml");
JSONArray icerikler = rss.getJSONArray("icerikler"); //Array
for(int i = 0; i < icerikler.length(); i++) {
	JSONObject icerik = icerikler.getJSONObject(i);
	icerik.getString("baslik"); //TruckersMP Team
	icerik.getString("yazar"); //...
	icerik.getString("aciklama"); //\n \n \n \n \nYou can find all 			the TruckersMP Team's staff changes in this topic, which is constantly updated by the respective management. \n \n \n
	icerik.getString("icerik"); //...
	icerik.getString("icerikEncoded"); //...
	icerik.getString("resim"); //...
	icerik.getString("url"); //https://forum.truckersmp.com/index.php?/topic/11449-truckersmp-team/
	icerik.getString("yayinTarihi"); //1429034744000				 
}

//İstediğiniz metni/yazıyı çevirmenize yarar.
//Çevrilecek şeyin dilini otomatik algılatmak için metindili kısmını "oto" yapmanız gerekiyor.
//Desteklenen diller: af, sq, ar, hy, az, eu, be, bn, bs, bg, ca, ceb, ny, zh-cn, zh-tw, co, hr, cs, da, nl, en, eo, et, tl, fi, fr, fy, gl, ka, de, el, gu, ht, ha, haw, iw, hi, hmn, hu, is, ig, id, ga, it, ja, jw, kn, kk, km, ko, ky, lo, la, lv, lt, lb, mk, mg, ms, ml, mt, mi, mr, mn, my, ne, no, ps, fa, pl, pt, pa, ro, ru, sm, gd, sr, st, sn, sd, si, sk, sl, so, es, su, sw, sv, tg, ta, te, th, tr, uk, ur, uz, vi, cy, xh, yi, yo, zu
JSONObject ceviri = api.ceviri ("Merhaba dünya!", "tr", "en"); //metin, metin dili, hedef dil
ceviri.getString("metinDiliKisa"); //tr
ceviri.getString("metinDiliUzun"); //Türkçe
ceviri.getString("hedefDilKisa"); //en
ceviri.getString("hedefDilUzun"); //İngilizce
ceviri.getString("ceviri"); //Hello World!

//İstediğiniz ile ait namaz vakitlerini çekmenize yarar.
JSONObject namazvakti = api.namaz("Batman"); //il
namazvakti.getString("il"); //Batman
namazvakti.getString("imsak"); //05:15
namazvakti.getString("gunes"); //06:35
namazvakti.getString("ogle"); //12:32
namazvakti.getString("ikindi"); //15:46
namazvakti.getString("aksam"); //18:19
namazvakti.getString("yatsi"); //19:34
namazvakti.getString("yaklasanVakit"); //Akşam
namazvakti.getString("yaklasanVakteKalan"); //32 dakika 55 saniye
//Ramazan ayi'na girdiğimiz zaman bu veri true olarak döner.
namazvakti.getBoolean("ramazanAyi"); //false
namazvakti.getString("ramazanVakit"); //İftar
namazvakti.getString("ramazanKalan"); //32 dakika 55 saniye

JSONObject havadurumu = api.hava("Batman"); //il
havadurumu.getString("il"); //Batman/TR
havadurumu.getLong("enlem"); //37.8874
havadurumu.getLong("boylam"); //41.1322
havadurumu.getInt("sicaklikDerece"); //10
havadurumu.getInt("sicaklikFahrenheit"); //50
havadurumu.getInt("enYuksekSicaklikDerece"); //10
havadurumu.getInt("enYuksekSicaklikFahrenheit"); //50
havadurumu.getInt("enDusukSicaklikDerece"); //10
havadurumu.getInt("enDusukSicaklikFahrenheit"); //50
havadurumu.getString("havaOlayi"); //Bulutlu
havadurumu.getString("havaOlayiResmi"); //http://openweathermap.org/img/w/04d.png
havadurumu.getInt("bulutOrani"); //75
havadurumu.getInt("nem"); //1017
havadurumu.getString("ruzgarYonu"); //Doğu
havadurumu.getLong("ruzgarHizi"); //3.6
havadurumu.getString("gunDogumu"); //09:42
havadurumu.getString("gunBatimi"); //21:12

//Resimdeki renkleri bulmanıza yarar.
JSONObject renkbul = api.renkbul("https://cdn.discordapp.com/avatars/524552476444196885/7f90be98428006d7ddb79ac9e705abfa.png?size=2048");
renkbul.getString("hex"); //#5c2022, #dcb4b9, #933436, #bd8b94, #bc767d
renkbul.getString("rgb"); //rgb(92,32,34), rgb(220,180,185), rgb(147,52,54), rgb(189,139,148), rgb(188,118,125)

//İstediğiniz ile ait nöbetçi eczaneleri bulmanıza yarar.
//Türkiye sınırları içinde bulunan illeri ve Kıbrıs'ı (sadece ülke olarak) destekler.
JSONArray eczaneler = api.eczane("Batman"); //il
for(int i = 0; i < eczaneler.length(); i++) {
	JSONObject eczane = ec.getJSONObject(i);
	eczane.getString("isim"); //Barış Eczanesi
	eczane.getString("ilce"); //Batman Merkez
	eczane.getString("telefon"); //0 (553) 537-65-45
	eczane.getString("konum"); //https://www.google.com/maps?daddr=37.899836,41.139428
	eczane.getString("adres"); //Belde Mahallesi, 3207 Sokak No:7/A Merkez / Batman
}

//İstediğiniz şarkıya ait şarkı sözü dahil bir çok bilgiyi çekmenize yarar.
JSONObject sarkisozu = api.sarkisozu("Nahide babashli anlasana"); //şarkı
sarkisozu.getString("sarkici"); //Nahide Babashlı
sarkisozu.getString("sarkiIsmi"); //Anlasana
sarkisozu.getString("tamIsim"); //Nahide Babashlı - Anlasana
sarkisozu.getString("albumKapagi"); //https://images.genius.com/4893555d1978aba8d98741b4d1e8adec.939x939x1.jpg
sarkisozu.getString("url"); //https://genius.com/Nahide-babashl-anlasana-annotated
sarkisozu.getString("soz"); //Kendimi esir aldım\nÇalmadı yine telefonlar\nAlışırım sanmıştım\nYüreğimde sancın var\n\nGel etme nazlı güneş ...

//İstenilen burç yorumlarını çekmenize yarar.
JSONObject burc = api.burc("Aslan"); //burç
burc.getString("burc"); //Aslan
burc.getString("burcResmi"); //https://disk.simsek.biz.tr/horoscope/aslan.png
burc.getString("burcMottosu"); //Benim dediklerim sonunda her zaman doğru çıkar!
burc.getString("burcGunu"); //23 Temmuz / 22 Ağustos
burc.getString("burcunGezegeni"); //Güneş
burc.getString("burcunElementi"); //Ateş
burc.getString("burcunNiteligi"); //Sabit
burc.getString("burcunUgurluSayilari"); //5
burc.getString("burcunUgurluGunu"); //Pazar
burc.getString("burcunUgurluRengi"); //Sarı, Turuncu
burc.getString("burcunOlumluOzellikleri"); //yüce gönüllülük, cömertlik, yaratıcılık, babalık, fedakarlık, üstünlük, yaratıcılık, neşe, iyi organizasyon, açık zihin
burc.getString("burcunOlumsuzOzellikeri"); //otorite, diktatörlük, zorbalık, tantana, züppelik, tolerans göstermemek, sabit fikirlilik, kuvvet deliliği, kendini beğenmişlik
burc.getString("gunlukYorum"); //Birisi size muzip mi geliyor, sempatik mi geliyor, çekici mi geliyor çözemiyorsunuz. ...
burc.getString("haftalikYorum"); //İş hayatınızda yanlış anlaşılmalara karşı dikkatli davranmanız ve ani kararlar vermeden her ne ...
burc.getString("aylikYorum"); //Bu ay iyi şans ve zindelik zamanı. Kendine olan güven ve dürüstlüğündeki yüksek artışla, ...

//Vikipedi'den aradığınız şeyin sonuçlarını çekmenize yarar.
JSONObject vikipedi = api.vikipedi("Araba"); //aranan kelime
vikipedi.getString("baslik"); //Araba
vikipedi.getString("kisaAciklama"); //Dört tekerlekli araç (çoğunlukla hayvanlar tarafından çekilir)
vikipedi.getString("uzunAciklama"); //Araba (tekerlekli taşıt), yolcu ve yük taşımaya uygun tekerlekli ...
vikipedi.getString("resim"); //https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Carro_Ribadavia_060115_05.jpg/1024px-Carro_Ribadavia_060115_05.jpg
vikipedi.getString("url"); //https://tr.wikipedia.org/wiki/Araba

//TruckersMP sunucu istatistiklerini çekmenize yarar.
JSONArray tmps = api.tmp("ets2"); //oyun
for(int i = 0; i < tmps.length(); i++) {
	JSONObject tmp = tmps.getJSONObject(i);
	tmp.getBoolean("aktif"); //true
	tmp.getString("sunucu"); //Simulation 1
	tmp.getInt("oyuncuSayisi"); //3106
	tmp.getInt("oyuncuLimiti"); //4800
	tmp.getLong("dolulukOrani"); //64.7
	tmp.getInt("kuyruktakiOyuncu"); //0
	tmp.getBoolean("carpisma"); //true
	tmp.getBoolean("hizLimiti"); //true
	tmp.getBoolean("afkKick"); //true
	tmp.getBoolean("promods"); //false
	tmp.getBoolean("oyuncularaNormalArac"); //true
	tmp.getBoolean("oyuncularaPolisAraci"); //false
	tmp.getBoolean("etkinlikSunucusu"); //false
	tmp.getBoolean("ozelEtkinlikSunucusu"); //false
}

//Tarihte bugün olan olayları çekmenize yarar.
JSONObject tarihtebugun = api.tarihtebugun();
tarihtebugun.getString("tarih"); //5 Mart
JSONArray olaylar = tarihtebugun.getJSONArray("olaylar");
for(int i = 0; i < olaylar.length(); i++) {
	 olaylar.getString(i); //1584 - Karlstad, İsveç'te kent konumuna alındı.
}
JSONArray olumler = tarihtebugun.getJSONArray("olumler");
for(int i = 0; i < olumler.length(); i++) {
	 olumler.getString(i); //1534 - Antonio da Correggio, İtalyan ressam (d. 1489)
}
JSONArray ozelGunler = tarihtebugun.getJSONArray("ozelGunler");
for(int i = 0; i < ozelGunler.length(); i++) {
	 ozelGunler.getString(i); //Püf tüh 5 mart'a ait bir özel gün yok hay Allah.
}

//İstenilen kategorideki haberleri çekmenize yarar.
JSONArray haberler = api.haberler();
for(int i = 0; i < haberler.length(); i++) {
	 JSONObject haber = haberler.getJSONObject(i);
	 haber.getString("baslik"); //Güney Kore'deki orman yangınında 216 bina kullanılamaz hale geldi
	 haber.getString("aciklama"); //Güney Kore'nin doğusundaki sahil bölgesinde dün çıkan orman yangınında 216 binanın kullanılamaz hale geldiği, 6 binden fazla kişinin tahliye edildiği bildirildi.
	 haber.getString("url"); //https://www.aa.com.tr/tr/guncel/guney-koredeki-orman-yangininda-216-bina-kullanilamaz-hale-geldi/2525095
	 haber.getString("yayinTarihi"); //1646499579000
}

//Instagram kullanıcısının bilgilerini çekmenize yarar.
//"islem" parametresine gonderi veya kullanici yazarak arama yapabilirsiniz.
JSONObject instagram = api.instagram("instagram" /* kullanıcı */, "kullanici" /* islem */, true /* bu false ise direk instagram apiden gelen json isteği gösterir. */);
instagram.getInt("hesapID"); //25025320
instagram.getString("kullaniciAdi"); //instagram
instagram.getString("kullaniciIsmi") //Instagram
instagram.getString("kullaniciBiyografisi"); //#YoursToMake
instagram.getString("kullaniciInternetAdresi"); //http://help.instagram.com/
instagram.getString("profilFotografi"); //https://instagram.fist4-1.fna.fbcdn.net/v/t51.2885-19/203019087_3969530746500786_7930596639916235962_n.jpg?stp=dst-jpg_s150x150&_nc_ht=instagram.fist4-1.fna.fbcdn.net&_nc_cat=1&_nc_ohc=Q3c2BZ4oQsgAX_UpJ8q&edm=ABfd0MgBAAAA&ccb=7-4&oh=00_AT9gjnJr-j0rLMOm9R4_xVvaP5mp8tjLk3rLnyt25d1rQA&oe=622B3282&_nc_sid=7bff83
instagram.getString("profilFotografiHD"); //https://instagram.fist4-1.fna.fbcdn.net/v/t51.2885-19/203019087_3969530746500786_7930596639916235962_n.jpg?stp=dst-jpg_s320x320&_nc_ht=instagram.fist4-1.fna.fbcdn.net&_nc_cat=1&_nc_ohc=Q3c2BZ4oQsgAX_UpJ8q&edm=ABfd0MgBAAAA&ccb=7-4&oh=00_AT94FuGv53FwQZ4vEpzqeezkkPKtg9GQI7NHgyCck9h-kQ&oe=622B3282&_nc_sid=7bff83
instagram.getLong("gonderiSayisi"); //7075
instagram.getLong("hikayeSayisi"); //12
instagram.getString("takipciSayisi"); //477445486
instagram.getString("takipEdilenKisiSayisi"); //108
instagram.getBoolean("hesapGizliMi"); //false
instagram.getBoolean("hesapOnayliMi"); //true
JSONArray baslicaGonderiler = instagram.getJSONArray("baslicaGonderiler");
for(int i = 0; i < baslicaGonderiler.length(); i++) {
	JSONObject bG = baslicaGonderiler.getJSONObject(i);
	bG.getString("onizleme"); //https://instagram.fist4-1.fna.fbcdn.net/v/t51.2885-15/275152167_640327813898697_215932940996334228_n.jpg?stp=dst-jpg_e35_p1080x1080&_nc_ht=instagram.fist4-1.fna.fbcdn.net&_nc_cat=1&_nc_ohc=Cms2gzRkLaYAX9tGFJU&edm=ABfd0MgBAAAA&ccb=7-4&oh=00_AT_fx1ev-M6hqwqqz0TzD34s9jKtWOaXzGHD6xryoK9UPA&oe=6229DFA4&_nc_sid=7bff83
	bG.getString("gonderiLinki"); //https://www.instagram.com/p/CasrIPYPbnB
	bG.getString("aciklama"); //Australian beauty creator Brandon Scott (@itsbybrandon) feels most connec ...
	bG.getString("paylasilmaTarihi"); //Sat Mar 05 2022 01:05:10 GMT+0300
	bG.getLong("begeniSayisi"); //240492
	bG.getLong("yorumSayisi"); //8769
	bG.getBoolean("yorumKapaliMi"); //false
}

//Sinemada vizyona giren/girecek filmlerin bilgilerini çekmenize yarar. "vizyon" parametresine 'suan' veya 'gelecek' yazarak sonuç alabilirsiniz.
JSONArray sinema = api.sinema("suan"); //vizyon
for(int i = 0; i < sinema.length(); i++) {
	JSONObject si = sinema.getJSONObject(i);
	si.getString("filmIsmi"); //Bergen
	si.getString("filmAfisiKucuk"); //https://tr.web.img3.acsta.net/c_310_420/pictures/22/02/18/14/06/0405738.jpg
	si.getString("filmAfisiBuyuk"); //https://tr.web.img3.acsta.net/r_1280_720/pictures/22/02/18/14/06/0405738.jpg
	si.getString("filmTuru"); //Dram, Biyografik
	si.getString("filmSuresi"); //2 saat 25 dakika
	si.getString("filmYonetmeni"); //Mehmet Binay, Caner Alper
	si.getString("filmSenaristi"); //Sema Kaygusuz, Yıldız Bayazıt
	si.getString("filmVizyonTarihi"); //4 Mart 2022
	si.getString("filmOyunculari"); //Farah Zeynep Abdullah, Erdal Beşikçioğlu, Tilbe Saran
	si.getString("filmOzeti"); //Arabeskin unutulmayan kadın seslerinden biri olan Bergen'in hayatını ...
}

//TV'de bugün yayınlanacak programları çekmenize yarar.
//Desteklenen TV'ler: kanal-d, star-tv, fox, show-tv, atv, trt-1, tv8, cartoon-network, teve2, beyaz-tv, a2, fb-tv, disney-channel, tlc, kanal-7, dmax, trt-2, krt-tv, tele-1, cnn-turk, tv-100, bein-movies-turk, bein-sports-haber, disney-junior, 360, halk-tv, trt-cocuk, bein-movies-premiere, bein-movies-action, nick-jr, bein-sports-1, trt-spor, nickelodeon, bein-gurme, bein-movies-stars, bein-iz, bein-movies-family, bein-sports-2, bein-movies-action-2, eurosport-1, gs-tv, tjk-tv, trt-muzik, haberturk, bein-movies-premiere-2, best-fm, bein-sports-4, eurosport-2, a-spor, bein-series-sci-fi
JSONObject tvdebugun = api.tvdebugun("kanal-d"); //kanal
tvdebugun.getString("tv"); //Star TV
JSONArray programlar = instagram.getJSONArray("programlar");
for(int i = 0; i < programlar.length(); i++) {
	JSONObject program = programlar.getJSONObject(i);
	program.getString("program"); //Kaderimin Oyunu / 11
	program.getString("programBaslama"); //2022-03-04T20:00:00
	program.getString("programBitis"); //2022-03-05T00:15:00
}

//TV'de şu an yayınlanan programları çekmenize yarar.
//Desteklenen TV'ler, TV'de bugün API'siyle aynıdır.
JSONArray tvdesuan = api.tvdesuan();
for(int i = 0; i < tvdesuan.length(); i++) {
	JSONObject suan = tvdesuan.getJSONObject(i);
	suan.getString("kanal"); //TRT 1
	suan.getString("kod"); //trt-1
	suan.getString("suanIsim"); //Gönül Dağı
	suan.getString("suanBaslama"); //2022-03-05T20:00:00
	suan.getString("suanBitis"); //2022-03-06T00:15:00
	suan.getString("sonrakiIsim"); //Pelin Çift ile Gündem Ötesi
	suan.getString("sonrakiBaslama"); //2022-03-06T00:15:00
	suan.getString("sonrakiBitis"); //2022-03-06T01:30:00
}

//Döviz bilgilerini TL bazında anlık olarak çekmenize yarar.
//Kullanılabilir döviz kısaltmaları: GEL, TND, USD, EUR, GBP, CAD, DKK, SEK, CHF, NOK, JPY, AED, AUD, RUB, KWD, ZAR, BHD, LYD, SAR, IQD, ILS, IRR, INR, MXN, HUF, NZD, BRL, IDR, CSK, PLN, BGN, RON, CNY, ARS, ALL, AZN, BAM, BYR, CLP, COP, CRC, DZD, EGP, HKD, HRK, ISK, JOD, KRW, KZT, LBP, LKR, LTL, LVL, MAD, MDL, MKD, MYR, OMR, PEN, PHP, PKR, QAR, RSD, SGD, SYP, THB, TWD, UAH, UYU
JSONObject doviz = api.doviz("PHP"); //doviz
doviz.getString("kod"); //PHP
doviz.getString("isim"); //Filipinler Pesosu
doviz.geDouble("alis"); //0.2732
doviz.geDouble("satis"); //0.2735
doviz.geDouble("enDusuk"); //0.2735
doviz.geDouble("enYuksek"); //0.2736
doviz.geDouble("degisim"); //-0.0001
doviz.geDouble("degisimYuzdesi"); //0
doviz.geDouble("kapanis"); //0.2736
doviz.getString("guncellenmeTarihi"); //Sat Mar 05 2022 00:00:18 GMT+0300

//Altın bilgilerini TL bazında anlık olarak çekmenize yarar.
//Kullanılabilir altın birimleri: 14, 18, 22, ATA, BSL, C, CMR, GA, GAG, GR, HA, IKB, T, ONS, Y
JSONObject altin = api.altin("14"); //altin
doviz.getString("kod"); //14
doviz.getString("isim"); //14 Ayar Altın
doviz.geDouble("alis"); //524.4523
doviz.geDouble("satis"); //524.5567
doviz.geDouble("enDusuk"); //524.4849
doviz.geDouble("enYuksek"); //524.6879
doviz.geDouble("degisim"); //-0.0671
doviz.geDouble("degisimYuzdesi"); //-0.0001
doviz.geDouble("kapanis"); //524.6238
doviz.getString("guncellenmeTarihi"); //Sat Mar 05 2022 00:00:18 GMT+0300

//Parite bilgilerini anlık olarak çekmenize yarar.
//Kullanılabilir pariteler: EUR/USD, USD/RUB, GBP/USD, USD/EUR, JPY/USD, USD/GBP, GBP/EUR, EUR/GBP, USD/JPY, USD/CHF
JSONObject parite = api.parite("EUR/USD"); //parite
parite.getString("kod"); //EUR/USD
parite.getString("isim"); //Euro/Dolar
parite.geDouble("alis"); //1.09339
parite.geDouble("satis"); //1.09354
parite.geDouble("enDusuk"); //1.09352
parite.geDouble("enYuksek"); //1.09354
parite.geDouble("degisim"); //0
parite.geDouble("degisimYuzdesi"); //0
parite.geDouble("kapanis"); //1.09353
parite.getString("guncellenmeTarihi"); //Sat Mar 05 2022 08:00:08 GMT+0300

//Kripto para bilgilerini dolar ($) bazında anlık olarak çekmenize yarar.
//Kullanılabilir kripto paralar: BTC, ETH, XRP, BCH, LTC, USDT, EOS, ADA, XMR, VEN, TRX, POE, MANA, MTH, ELF, STRAT, XZC, WTC, ENJ, ZRX, ZEC, ZEN, ZIL, AE, ALGO, ARDR, ARK, BAT, REP, AOA, BNB, BSV, BCD, BTG, BTS, BTT, BTMX, BCN, BTM, XLINK, CRO, DAI, DCR, DENT, DGB, XMX, WAXP, ATOM, DOGE, EGT, ELA, NRG, GNT, GRIN, GXC, HOT, HT, KMD, KCS, LAMB, LEO, LSK, MAID, MKR, ETP, MONA, NANO, NAS, XEM, NEX, NEXO, NULS, OKB, ONT, PAX, NPXS, QTUM, QNT, QKC, RVN, REN, RR, SC, SOLVE, SNT, STEEM, XTZ, THETA, TUSD, USDC, VSYS, XVG, MCO, XAUT, NMR, CKB, DIVI, DX, CEL, STX, BUSD, HBAR, ETN, HUSD, GT, HYN, UBT, LUNA, MATIC, HIVE, FTT, KNC, UMA, CHZ, CETH, LRC, HAV, DOT, CELO, YFI, CHSB, AVAX, UNI, NXM, AMP, CRV, AAVE, CDAI, CAKE, 1INCH, RENBTC, NEAR, RSR, ALPHA, SOL, COMP, WBTC, KSM, FIL, BAL, EGLD, RUNE, CUSDC, SUSHI, GRT, OCEAN, ETHOS, FTM, AMPL, HBTC, MIOTA, WAVES, PPT, XLM, REQ, HSR, IOST, CND, VIBE, LUN, VIB, CMT, ICX, ETC, OMG, NEO, DSH
JSONObject kripto = api.kripto("BTC"); //kripto
kripto.getString("kod"); //BTC
kripto.getString("isim"); //Bitcoin
kripto.geDouble("alis"); //39440.837172791864
kripto.geDouble("satis"); //39440.837172791864
kripto.geDouble("enDusuk"); //38694.68
kripto.geDouble("enYuksek"); //39593.41
kripto.geDouble("degisim"); //-115.2528
kripto.geDouble("degisimYuzdesi"); //-0.0029
kripto.geDouble("kapanis"); //39556.09
kripto.getString("guncellenmeTarihi"); //Sat Mar 05 2022 22:20:56 GMT+0300

```

