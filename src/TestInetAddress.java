import java.net.InetAddress;

public class TestInetAddress {
    public static void main(String[] args) throws Exception {
        //查询本机地址
        System.out.println(InetAddress.getByName("127.0.0.1"));
        System.out.println(InetAddress.getByName("localhost"));
        System.out.println(InetAddress.getLocalHost());

    }
}
