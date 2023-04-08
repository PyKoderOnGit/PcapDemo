import lombok.extern.slf4j.Slf4j;
import org.pcap4j.core.PcapPacket;

import java.util.Optional;

import static java.util.Arrays.copyOfRange;

@Slf4j
public class SvDecoder {

    private static final int datasetSize = 64;

    public static Optional<SvPacket> decode(PcapPacket packet) {
        try {
            byte[] data = packet.getRawData();
            int length = data.length;

            SvPacket result = new SvPacket();

            result.setMacDst((byteArrayToMac(data, 0)));
            result.setMacSrc((byteArrayToMac(data, 6)));
            result.setPacketType(byteArrayToHexString(data, 12, 13));
            result.setAppID(byteArrayToHexString(data, 14, 15));
            result.setSvID(new String(copyOfRange(data, 33, 42 + 1)));
            result.setSmpCount(byteArrayToInt(data, 45, 46));
            result.setConfRef(byteArrayToInt(data, 49, 52));
            result.setSmpSynch(byteArrayToInt(data, 55, 55));


            result.getDataset().setInstIa(byteArrayToInt(data,58,61) / 100.0);
            result.getDataset().setQIa(byteArrayToHexString(data,62,65));
            result.getDataset().setInstIb(byteArrayToInt(data,66,69) / 100.0);
            result.getDataset().setQIb(byteArrayToHexString(data,70,73));
            result.getDataset().setInstIc(byteArrayToInt(data,74,77) / 100.0);
            result.getDataset().setQIc(byteArrayToHexString(data,78,81));
            result.getDataset().setInstIn(byteArrayToInt(data,82,85) / 100.0);
            result.getDataset().setQIn(byteArrayToHexString(data,86,89));

            result.getDataset().setInstVa(byteArrayToInt(data,90,93) / 100.0);
            result.getDataset().setQVa(byteArrayToHexString(data,94,97));
            result.getDataset().setInstVb(byteArrayToInt(data,98,101) / 100.0);
            result.getDataset().setQVb(byteArrayToHexString(data,102,105));
            result.getDataset().setInstVc(byteArrayToInt(data,106,109) / 100.0);
            result.getDataset().setQVc(byteArrayToHexString(data,110,113));
            result.getDataset().setInstVn(byteArrayToInt(data,114,117) / 100.0);
            result.getDataset().setQVn(byteArrayToHexString(data,118,121));


            return Optional.of(result);
        } catch (Exception e) { log.error("Cannot parse sv packet"); }
        return  Optional.empty();
    }

    public static String byteArrayToMac(byte[] b, int offset) {
        return String.format("%02x:%02x:%02x:%02x:%02x:%02x",
                b[offset],
                b[offset + 1],
                b[offset + 2],
                b[offset + 3],
                b[offset + 4],
                b[offset + 5]
        );
    }

//    public static String byteToHex() {
//
//    }


    public static int byteArrayToInt(byte[] b, int startIndex, int endIndex) {
        int len = endIndex - startIndex + 1;
        int result = 0;
        for (int i = 0; i < len; i++) {
            int bi = startIndex + i;
            result += (b[bi] & 0xFF) << (8 * (len - i - 1));
        }
        return result;
//        return  b[offset + 3] & 0xFF | (b[offset + 2] & 0xFF) << 8 | (b[offset + 1] & 0xFF) << 16 |(b[offset] & 0xFF) << 24;
    }

    public static String byteArrayToHexString(byte[] b, int startIndex, int endIndex) {
        String result = "0x";
        for (int i = startIndex; i <= endIndex; i++) {
            result += String.format("%02x", b[i]);
        }
        return result;
    }



}
