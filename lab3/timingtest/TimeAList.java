package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // 測試次數
        int[] timesArray = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};
        // 測試項目數
        AList<Integer> Ns = new AList<>();
        // 花費時間
        AList<Double> spendTimes = new AList<>();

        // 逐次執行測試
        for (int addTimes : timesArray) {
            // 測試 AList.addLast() 並回傳花費時間
            double time = runAddLast(addTimes);

            // 更新測試紀錄
            spendTimes.addLast(time);
            Ns.addLast(addTimes);
        }

        // 印出測試紀錄
        printTimingTable(Ns, spendTimes, Ns);
    }

    /**
     * 測試 AList.addLast()
     * @param addTimes 幾次
     * @return 總花費時間
     */
    private static double runAddLast(int addTimes) {
        Stopwatch stopwatch = new Stopwatch();
        AList<Integer> aList = new AList<>();

        for (int j = 0; j < addTimes; j++) {
            aList.addLast(j);
        }

        return stopwatch.elapsedTime();
    }
}
