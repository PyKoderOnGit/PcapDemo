import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class SvPacket {
    private String macDst;

    private String macSrc;

    private String packetType;

    private String appID;

    private String svID;

    private int smpCount;

    private int confRef;

    private int smpSynch;

    private Dataset dataset = new Dataset();

    @Setter @Getter
    public class Dataset {
        private double instIa;
        private String qIa;
        private double instIb;
        private String qIb;
        private double instIc;
        private String qIc;
        private double instIn;
        private String qIn;

        private double instVa;
        private String qVa;
        private double instVb;
        private String qVb;
        private double instVc;
        private String qVc;
        private double instVn;
        private String qVn;

        @Override
        public String toString() {
            return "Dataset{" +
                    "instIa=" + instIa +
                    ", qIa=" + qIa +
                    ", instIb=" + instIb +
                    ", qIb=" + qIb +
                    ", instIc=" + instIc +
                    ", qIc=" + qIc +
                    ", instIn=" + instIn +
                    ", qIn=" + qIn +
                    ", instVa=" + instVa +
                    ", qVa=" + qVa +
                    ", instVb=" + instVb +
                    ", qVb=" + qVb +
                    ", instVc=" + instVc +
                    ", qVc=" + qVc +
                    ", instVn=" + instVn +
                    ", qVn=" + qVn +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SvPacket{" +
                "macDst='" + macDst + '\'' +
                ", macSrc='" + macSrc + '\'' +
                ", packetType=" + packetType +
                ", appID=" + appID +
                ", svID='" + svID + '\'' +
                ", smpCount=" + smpCount +
                ", confRef=" + confRef +
                ", smpSynch=" + smpSynch +
                ", dataset=" + dataset +
                '}';
    }
}
