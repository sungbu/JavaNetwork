import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class testWIfi {
    public static void main(String[] args) throws Exception {
        InetAddress host = InetAddress.getLocalHost();//获得本机的InetAddress对象
        String hostAddress = host.getHostAddress();//获取的本机的IP地址
        int pos = hostAddress.lastIndexOf(".");//获取IP地址中最后一个点的位置  9 
        String wd = hostAddress.substring(0, pos + 1); // 对本机的IP进行截取，获取网段 172.20.10.
        

        for(int i = 1; i <= 255; i ++){
            String ip = wd + i;
            PingIPThread thread = new PingIPThread(ip);
            thread.start();
        }

        
    }
    public static class PingIPThread extends Thread{
        String Ip;
        public PingIPThread(String Ip) {
            this.Ip = Ip;
        }

        @Override
        public void run() {
            Process process;
            try {
                process = Runtime.getRuntime().exec("ping " + Ip + " -w 280 -n 1");
                InputStream is = process.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader in = new BufferedReader(isr);
                String line = in.readLine();

                while(line != null){
                    if(line != null && !line.equals("")){
                        if(line.substring(0,2).equals("来自") || (line.length() > 10 && line.substring(0,10).equals("Reply from"))){
                            
                            InetAddress id = InetAddress.getByName(Ip);
                            System.out.println(Ip + " " +id.getHostName());
                        }
                    }
                    line = in.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }
}
