import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlDownload {
    public static void main(String[] args) throws Exception {
        //1.下载地址
        URL url = new URL("https://fanyi-cdn.cdn.bcebos.com/static/translation/img/header/logo_e835568.png");
    
        //2. 连接到这个资源  HTTP

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        InputStream inputStream = urlConnection.getInputStream();

        FileOutputStream fos = new FileOutputStream("test.png");
        byte[] buffer = new byte[1024];
        int len;
        while((len = inputStream.read(buffer)) != -1){
            fos.write(buffer,0,len);
        }

        fos.close();
        inputStream.close();
        urlConnection.disconnect();
    
    }
}
