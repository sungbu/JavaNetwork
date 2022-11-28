import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServerDemo {
    public static void main(String[] args) throws Exception {
        //1.创建服务
        ServerSocket serverSocket = new ServerSocket(9000);

        //2.监听客户端的连接
        Socket socket = serverSocket.accept();
        //获取输入流
        InputStream is = socket.getInputStream();
        //文件输出
        FileOutputStream fos = new FileOutputStream(new File("receive.mp4"));

        byte[] buffer = new byte[1024];
        int len;
        while((len = is.read(buffer)) != -1){
            fos.write(buffer,0,len);
        }


        // 通知客户端接收完毕
        OutputStream os = socket.getOutputStream() ;
        os.write("我接收完毕了，你可以断开了".getBytes());

        //关闭资源
        fos.close();
        is.close();
        socket.close();
        serverSocket.close();
        
    }
}
