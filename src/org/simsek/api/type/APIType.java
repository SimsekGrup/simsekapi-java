package org.simsek.api.type;

public enum APIType {
	CheckAPIKey("Api Kontrol"),
	VersionControl("Güncelleme Kontrol"),
	Ball("8Ball"),
	Ataturk("Atatürk"),
	Soz("Söz"),
	Espri("Espri"),
	AskOlcer("Aşk Ölçer"),
	Keko("Keko Yazı"),
	Sifre("Şifre"),
	Renk("Renk"),
	ResimEfekti("Resim Efekti"),
	Rss("RSS"),
	Ceviri("Çeviri"),
	Namaz("Namaz"),
	Hava("Hava"),
	Renkbul("Renk Bul"),
	Eczane("Eczane"),
	SarkiSozu("Şarkı Sözü"),
	Burc("Burç"),
	VikiPedi("Vikipedi"),
	Tmp("TruckersMP"),
	TarihteBugun("Tarihte Bügün"),
	Haberler("Haberler"),
	Gazete("Gazete"),
	Instagram("Instagram"),
	Sinema("Sinema"),
	TVdeBugun("TV'de Bügün"),
	TVdeSuan("TV'de Şuan"),
	Doviz("Doviz"),
	Altin("Altın"),
	Parite("Parite"),
	Kripto("Kripto");
	
	private String name;
	
	private APIType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
