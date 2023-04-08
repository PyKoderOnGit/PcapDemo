import java.util.Arrays;
import java.util.HashSet;

public class PacketAnalyzer {

    private double[][] currentRMS = new double[2][4];
    private double[][] previousRMS = new double[2][4];
    private double[][] currentAmpRMS = new double[2][4];
    private double[][] newValues = new double[2][4];
    private boolean startFaultChecking = false;
    private final double setPoint = 2;

    public void toAnalyzeSignal(SvPacket svPacket) {
        newValues[0][0] = svPacket.getDataset().getInstIa() / 100.0;
        newValues[0][1] = svPacket.getDataset().getInstIb() / 100.0;
        newValues[0][2] = svPacket.getDataset().getInstIc() / 100.0;
        newValues[0][3] = svPacket.getDataset().getInstIn() / 100.0;
        newValues[1][0] = svPacket.getDataset().getInstVa() / 100.0;
        newValues[1][1] = svPacket.getDataset().getInstVb() / 100.0;
        newValues[1][2] = svPacket.getDataset().getInstVc() / 100.0;
        newValues[1][3] = svPacket.getDataset().getInstVn() / 100.0;

        HashSet<String> faultyPhasesSet = new HashSet<String>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                System.arraycopy(currentRMS, 0, previousRMS, 0, currentRMS.length);
                currentRMS[i][j] = updateRMS(currentRMS[i][j], newValues[i][j]);
                currentAmpRMS[i][j] = currentRMS[i][j] * Math.sqrt(2);

                if (startFaultChecking && i == 0 && newValues[i][j] > setPoint * currentAmpRMS[i][j]) {
                    switch (j) {
                        case 0 -> faultyPhasesSet.add("A");
                        case 1 -> faultyPhasesSet.add("B");
                        case 2 -> faultyPhasesSet.add("C");
                    }

                }
            }
        }

        printCurrentRMS(currentRMS);

        if (faultyPhasesSet.size() != 0) {
            String faultyPhases = String.join("", faultyPhasesSet);
            System.out.println("Detected a fault of type: " + faultyPhases);
        }



//        if (!Arrays.deepEquals(currentRMS, previousRMS)) {
//            printCurrentRMS(currentRMS);
//        }

    }

    private double updateRMS(double currRMS, double newValue) {
        return Math.sqrt(currRMS*currRMS + newValue*newValue);
    }

    private void printCurrentRMS(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.println(
                        "Ia_RMS = " + matrix[0][0] + "; " +
                        "Ib_RMS = " + matrix[0][1] + "; " +
                        "Ic_RMS = " + matrix[0][2] + "; " +
                        "In_RMS = " + matrix[0][3]);
                System.out.println(
                        "Va_RMS = " + matrix[1][0] + "; " +
                        "Vb_RMS = " + matrix[1][1] + "; " +
                        "Vc_RMS = " + matrix[1][2] + "; " +
                        "Vn_RMS = " + matrix[1][3]);
                System.out.println();
            }
        }
    }

}
