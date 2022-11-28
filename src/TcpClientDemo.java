import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TcpClientDemo {
    static Socket socket = null;
    static OutputStream os = null;
    public static void main(String[] args) {
        try {
            //1. 要知道服务器的地址
            InetAddress serverIP = InetAddress.getByName("127.0.0.1");
            //2.端口号
            int port = 9999;
            //3.建立一个socket连接
            socket = new Socket(serverIP,port);

            //发送消息
            os = socket.getOutputStream();

            os.write("你好！我是客户端".getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
}
