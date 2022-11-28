import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class TalkSend implements Runnable{
    DatagramSocket socket = null;
    BufferedReader reader = null;
    int fromPort;
    String toIP;
    int toProt;

    TalkSend(int fromPort,String toIP,int toProt) {
        this.fromPort = fromPort;
        this.toIP = toIP;
        this.toProt = toProt;

        try {
            socket = new DatagramSocket(fromPort);

             //准备数据：控制台读取 System.in
            reader = new BufferedReader(new InputStreamReader(System.in));
        } catch (SocketException e) {
            e.printStackTrace();
        }

        
        

    }

    @Override
    public void run() {
        
        while(true){
            try {
                    String data = reader.readLine();
                    byte[] datas = data.getBytes();
        
                    DatagramPacket packet = new DatagramPacket(datas,0,datas.length,new InetSocketAddress(this.toIP, this.toProt));
        
        
                    socket.send(packet);
        
                    if(data.equals("bye")){
                        break;
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        

        socket.close();
        
    }
    
}
