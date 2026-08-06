# Notlarım - Android Studio KURMADAN .apk Oluşturma

Bu yöntemde hiçbir şey bilgisayarına kurmuyorsun. Kodu ücretsiz bir GitHub
hesabına yüklüyorsun, GitHub'ın kendi sunucuları senin için derliyor, sen de
sonunda ortaya çıkan .apk dosyasını indirip telefonuna atıyorsun.

Evet, Android'de kurulabilir uygulama dosyası **.apk** formatındadır. Bu dosyayı
telefonuna aktarıp üzerine dokunarak kurarsın.

---

## Adım 1: GitHub Hesabı Aç
1. https://github.com adresine git.
2. Sağ üstten **Sign up** ile ücretsiz hesap oluştur (e-posta + şifre yeterli).

## Adım 2: Yeni Bir Depo (Repository) Oluştur
1. Giriş yaptıktan sonra sağ üstteki **+** işaretine bas, **New repository** seç.
2. İsim olarak `notlarim` yaz.
3. **Public** veya **Private** fark etmez, ikisi de olur (Private daha gizli kalır, kodun sadece sende görünür).
4. **Create repository** butonuna bas.

## Adım 3: Dosyaları Yükle
1. Az önce sana verdiğim `Notlarim.zip` dosyasını bilgisayarında bir klasöre
   çıkart (sağ tık > Tümünü Ayıkla).
2. Çıkan **Notlarim** klasörünün İÇİNDEKİ tüm dosya ve klasörleri seç
   (klasörün kendisini değil, içindekileri).
3. GitHub'daki boş deponda ortada çıkan **"uploading an existing file"**
   linkine tıkla (ya da "Add file > Upload files").
4. Seçtiğin tüm dosyaları oraya sürükle-bırak yap.
5. Altta **Commit changes** butonuna bas. Yükleme başlar.

## Adım 4: Otomatik Derlemeyi İzle
1. Deponun üst kısmındaki **Actions** sekmesine tıkla.
2. "APK Derle" adında bir işlemin otomatik başladığını göreceksin
   (sarı nokta = çalışıyor, yeşil tik = tamamlandı, kırmızı çarpı = hata).
3. Tamamlanması genelde 3-6 dakika sürer. Sayfayı yenileyerek bekleyebilirsin.

## Adım 5: APK'yı İndir
1. Yeşil tik çıkınca, o işlemin üzerine tıkla.
2. Sayfanın alt kısmında **Artifacts** başlığı altında **notlarim-apk**
   adında bir dosya göreceksin, üzerine tıkla, bilgisayarına iner (zip içinde).
3. İnen zip'i aç, içinden **app-debug.apk** dosyasını çıkar.

## Adım 6: APK'yı Telefonuna Aktar
Şu yollardan biriyle apk dosyasını Huawei telefonuna gönder:
- Google Drive / Huawei Cloud'a yükleyip telefondan indir, **veya**
- Kendine e-posta ile gönderip telefonundan aç, **veya**
- USB kablosuyla telefona bağlayıp dosyayı doğrudan kopyala.

## Adım 7: Telefonda Kur
1. Telefonunda dosyayı (Dosyalar uygulaması, Mail, ya da Drive üzerinden) bul, üzerine dokun.
2. Karşına "Bilinmeyen kaynaklardan yükleme" uyarısı çıkabilir — bu normal,
   Play Store dışından kurduğun için çıkıyor. **"İzin Ver" / "Yine de Kur"** de.
3. **Kur** butonuna bas, kurulum bitince **Aç**'a bas.
4. Karşına PIN belirleme ekranı çıkacak, kendi PIN'ini oluştur.

---

## Hata Alırsan (Actions sekmesinde kırmızı çarpı çıkarsa)
Kırmızı çarpıya tıkla, açılan loglardan hangi adımda hata verdiğini gör,
o mesajın ekran görüntüsünü bana gönder, birlikte düzeltelim. En sık
karşılaşılan durum, yükleme sırasında bir dosyanın eksik veya yanlış
klasöre kopyalanmış olmasıdır — bu durumda "Notlarim" klasörünün İÇİNDEKİ
her şeyin (gizli **.github** klasörü dahil) yüklendiğinden emin ol.

## Notlar Nerede Saklanıyor?
Notların tamamen telefonunun kendi hafızasında kalır, hiçbir sunucuya
gönderilmez. GitHub sadece kodu derlemek için kullanılıyor, senin
notlarınla hiç teması olmaz.
