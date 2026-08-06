# Notlarım - Kurulum Rehberi

Bu proje; PIN ve yüz/parmak izi tanıma ile açılan basit bir notlar uygulamasıdır.

## Uygulama Nasıl Çalışır?
- İlk açılışta senden bir **PIN** belirlemeni ister (iki kez girip onaylarsın).
- Sonraki açılışlarda PIN ile ya da **"Yüz / Parmak İzi ile Aç"** butonuyla girebilirsin.
- **Önemli:** Uygulama, yüzünü kendi içinde saklamaz. Bunun yerine telefonunun
  kendi güvenli donanımında (TrustZone) zaten kayıtlı olan yüz/parmak izini kullanır.
  Yani önce **telefonunun kendi Ayarlar > Biyometrik Veriler ve Şifre** bölümünden
  kendi yüzünü/parmak izini telefona tanıtmış olman gerekir. Uygulama sadece
  "bu kişi telefonun sahibi mi?" diye telefona sorar, cevabı telefon verir.
  Bu, yüz verinin uygulama içinde saklanmasından çok daha güvenlidir.

---

## Adım 1: Android Studio'yu Kur
1. https://developer.android.com/studio adresine git.
2. Windows için indirme butonuna bas, kurulum dosyasını çalıştır.
3. Kurulum sihirbazında hep "Next/İleri" diyerek varsayılan ayarlarla devam et.
4. İlk açılışta "Standard" kurulum seçeneğini seç, Android SDK otomatik inecek
   (biraz zaman alabilir, internet hızına göre).

## Adım 2: Projeyi Aç
1. Sana verdiğim `Notlarim.zip` dosyasını bilgisayarında bir klasöre çıkart
   (sağ tık > "Tümünü Ayıkla / Extract All").
2. Android Studio'yu aç, karşılama ekranında **"Open"** de.
3. Çıkarttığın `Notlarim` klasörünü seç, aç.
4. Android Studio projeyi tanıyıp "Gradle Sync" işlemini otomatik başlatacak.
   Bu ilk seferde birkaç dakika sürebilir (gerekli kütüphaneleri indiriyor).
   Ekranın altındaki ilerleme çubuğu bitene kadar bekle.
5. Eğer "Trust Project?" diye sorarsa **Trust Project** de.

## Adım 3: Huawei Telefonunu Bağla
1. Telefonunda **Ayarlar > Telefon Hakkında** (veya "Cihaz Hakkında") bölümüne git,
   **"Yapı Numarası" / "Build Number"** yazısına art arda 7 kez dokun.
   "Artık geliştiricisiniz" mesajı çıkacak.
2. **Ayarlar > Sistem > Geliştirici Seçenekleri**'ne git.
3. **"USB Hata Ayıklama" (USB Debugging)** seçeneğini aç.
4. Telefonu USB kablosuyla bilgisayarına bağla.
5. Telefonda "Bu bilgisayara güven / USB hata ayıklamaya izin ver?" sorusuna **İzin Ver** de.

## Adım 4: Uygulamayı Çalıştır
1. Android Studio'nun üst kısmında telefonunun adının göründüğü bir açılır menü olacak
   (bağlı cihaz algılanınca otomatik çıkar).
2. Yeşil **▶ (Run)** butonuna bas.
3. Android Studio uygulamayı derleyip otomatik olarak telefonuna kuracak ve açacak.
4. Karşına PIN belirleme ekranı çıkacak — kendi PIN'ini oluştur.
5. Ardından telefonunun Ayarlar bölümünden (eğer daha önce yapmadıysan) kendi
   yüzünü tanıt, uygulamayı kapatıp tekrar aç, **"Yüz / Parmak İzi ile Aç"**
   butonunu dene.

## Kalıcı Kurulum (Bilgisayardan Bağımsız Kullanmak İçin)
Yukarıdaki yöntemle uygulama telefonuna zaten kalıcı olarak kurulmuş olur,
USB kablosunu çıkarsan da uygulama telefonunda kalır ve normal bir uygulama
gibi çalışmaya devam eder. Bilgisayara sadece güncelleme yapmak istediğinde
tekrar ihtiyacın olur.

## Notlar Nerede Saklanıyor?
Notların, telefonunun kendi hafızasında, sadece bu uygulamaya özel bir
veritabanında (Room/SQLite) tutulur. İnternete gönderilmez, başka hiçbir
uygulama erişemez.

## Sorun Yaşarsan
- "Gradle Sync Failed" hatası: İnternet bağlantını kontrol et, Android Studio'yu
  yeniden başlatıp tekrar dene.
- Telefon görünmüyor: USB kablosunu değiştir, "Dosya Aktarımı (MTP)" modunda
  bağlı olduğundan emin ol (bildirim çubuğundan kontrol edilir).
- Yüz tanıma butonu görünmüyor: Telefonunda henüz kayıtlı bir yüz/parmak izi
  yoktur, önce telefonun kendi Ayarlar bölümünden ekle.
