import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        EthernetListener ethernetListener = new EthernetListener();
//        ethernetListener.setNicName("Realtek PCIe GBE Family Controller");
        ethernetListener.setNicName("Oracle");

        PacketAnalyzer packetAnalyzer = new PacketAnalyzer();

        SvDecoder svDecoder = new SvDecoder();
        ethernetListener.addListener(packet -> {
            Optional<SvPacket> svPacket = SvDecoder.decode(packet);
            if(svPacket.isPresent()) {
                System.out.println(svPacket.get().toString());
                packetAnalyzer.toAnalyzeSignal(svPacket.get());
            }
        });

//        AppPacketListener appPacketListener = new AppPacketListener();
//        ethernetListener.addListener(appPacketListener);


        ethernetListener.start();
    }

}
