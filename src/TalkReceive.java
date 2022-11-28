import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class TalkReceive implements Runnable {
    int port;
    String msgFrom;
    DatagramSocket socket = null;
    TalkReceive(int port,String msgFrom) {
        this.port = port;
        this.msgFrom = msgFrom;
        try {
            socket = new DatagramSocket(this.port);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while(true){
           try {
             //准备接收包裹
             byte[] container = new byte[1024];

             DatagramPacket packet = new DatagramPacket(container, 0,container.length);
             socket.receive(packet); //阻塞式接收包裹
 
 
             //断开连接 bye
             byte[] data = packet.getData();
             String receiveData = new String(data,0,data.length);
             System.out.println(msgFrom+"：" + receiveData);
             if(receiveData.equals("bye")){
                 break;
             }
 
           } catch (Exception e) {
            e.printStackTrace();
           }


        }
    }
    
}
