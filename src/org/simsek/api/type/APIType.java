package org.simsek.api.type;

public enum APIType {
	CheckAPIKey("Api Kontrol"),
	VersionControl("G�ncelleme Kontrol"),
	Ball("8Ball"),
	Ataturk("Atat�rk"),
	Soz("S�z"),
	Espiri("Espiri"),
	AskOlcer("A�k �l�er"),
	Keko("Keko Yaz�"),
	Sifre("�ifre"),
	Renk("Renk"),
	ResimEfekti("Resim Efekti"),
	Rss("RSS"),
	Ceviri("�eviri"),
	Namaz("Namaz"),
	Hava("Hava"),
	Renkbul("Renk Bul"),
	Eczane("Eczane"),
	SarkiSozu("�ark� S�z�"),
	Burc("Bur�"),
	VikiPedi("Viki Pedi"),
	Tmp("TruckersMP"),
	TarihteBugun("Tarihte B�g�n"),
	Haberler("Haberler"),
	Gazete("Gazete"),
	Instagram("Instagram"),
	Sinema("Sinema"),
	TVdeBugun("TV'de B�g�n"),
	TVdeSuan("TV'de �uan"),
	Doviz("Doviz"),
	Altin("Alt�n"),
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
