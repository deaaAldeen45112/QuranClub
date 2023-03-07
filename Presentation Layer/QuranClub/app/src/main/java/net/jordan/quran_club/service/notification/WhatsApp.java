package net.jordan.quran_club.service.notification;

public class WhatsApp extends IMessage{
    @Override
    protected void send(String message) {
//        PackageManager packageManager = context.getPackageManager();
//        Intent i = new Intent(Intent.ACTION_VIEW);
//        try {
//            String url = "https://api.whatsapp.com/send?phone=" + number + "&text=" + URLEncoder.encode(CommonStrings.SHARING_APP_MSG, "UTF-8");
//            i.setPackage("com.whatsapp");
//            i.setData(Uri.parse(url));
//            if (i.resolveActivity(packageManager) != null) {
//                context.startActivity(i);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
