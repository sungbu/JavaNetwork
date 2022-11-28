import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class TcpServerDemo {
    static ServerSocket serverSocket = null;
    static Socket acceptSocket = null;
    static InputStream is = null;
    static ByteArrayOutputStream baos = null;
    public static void main(String[] args) {
        try {
            //1. 我得有一个地址
            serverSocket = new ServerSocket(9999);

            //2. 等待客户端连接
            acceptSocket = serverSocket.accept();
            //3. 读取客户端的消息
            is = acceptSocket.getInputStream();

            //管道流
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while((len = is.read(buffer))!= -1){
                baos.write(buffer, 0, len);
            }
            System.out.println(baos.toString());
        
            

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //关闭资源
            if(baos != null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if(acceptSocket != null){
                try {
                    acceptSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if(serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
