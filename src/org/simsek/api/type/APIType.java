package org.simsek.api.type;

public enum APIType {
	CheckAPIKey("Api Kontrol"),
	VersionControl("Güncelleme Kontrol"),
	Ball("8Ball"),
	Ataturk("Atatürk"),
	Soz("Söz"),
	Espiri("Espiri"),
	AskOlcer("Aþk Ölçer"),
	Keko("Keko Yazý"),
	Sifre("Þifre"),
	Renk("Renk"),
	ResimEfekti("Resim Efekti"),
	Rss("RSS"),
	Ceviri("Çeviri"),
	Namaz("Namaz"),
	Hava("Hava"),
	Renkbul("Renk Bul"),
	Eczane("Eczane"),
	SarkiSozu("Þarký Sözü"),
	Burc("Burç"),
	VikiPedi("Viki Pedi"),
	Tmp("TruckersMP"),
	TarihteBugun("Tarihte Bügün"),
	Haberler("Haberler"),
	Gazete("Gazete"),
	Instagram("Instagram"),
	Sinema("Sinema"),
	TVdeBugun("TV'de Bügün"),
	TVdeSuan("TV'de Þuan"),
	Doviz("Doviz"),
	Altin("Altýn"),
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
