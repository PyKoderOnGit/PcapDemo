import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapPacket;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppPacketListener implements PacketListener {


//    private long receivedPacketCounter = 0;
//    private List<Optional> svPackets = new ArrayList<>();
//    private boolean receptionIsCompleted = false;

    @Override
    public void gotPacket(PcapPacket packet) {
        Optional<SvPacket> svPacket = SvDecoder.decode(packet);
        if (svPacket.isPresent()) {
            System.out.println();

        }



//        if (svPacket.isPresent() && !receptionIsCompleted) {
//            System.out.println(++receivedPacketCounter);
//            if (receivedPacketCounter == 12000) {
//                receptionIsCompleted = true;
//            }
//        }
//        if (receptionIsCompleted) {
////            System.out.println(svPacket.toString());
//            System.out.println("Number of the last received packet: " + receivedPacketCounter);
//        }


    }
}
