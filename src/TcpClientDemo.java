import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.naming.ldap.SortKey;
public class TcpClientDemo {
    public static void main(String[] args) throws Exception {
        // 1. 创建一个socket连接
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"),9000);
        //2. 创建一个输出流
        OutputStream os = socket.getOutputStream();

        //3. 读取文件
        FileInputStream fis = new FileInputStream(new File("video.mp4"));

        //4. 写出文件
        byte[] buffer = new byte[1024];
        int len;
        while((len = fis.read(buffer)) != -1){
            os.write(buffer,0,len);
        }

        //通知服务器，我已经结束了
        socket.shutdownOutput();//我已经传输完了


        //确定服务器接收文件完毕，才能断开连接
        InputStream is = socket.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buffer2 = new byte[1024];
        int len2;
        while((len2 = is.read(buffer2)) != -1){
            baos.write(buffer2, 0, len2);
        }

        System.out.println(baos.toString());



        //5.关闭资源
        baos.close();
        is.close();
        fis.close();
        os.close();
        socket.close();


    }
}
