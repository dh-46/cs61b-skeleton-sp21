package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
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
        timeGetLast();
    }

    public static void timeGetLast() {

        int[] timesArray = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};

        // 測試項目數
        AList<Integer> Ns = new AList<>();
        // 測試花費時間
        AList<Double> spendTimes = new AList<>();
        // 方法執行次數
        AList<Integer> ops = new AList<>();

        int opsCount = 10000;

        for (int N : timesArray) {
            // Create list
            SLList<Integer> slList = getTestSLList(N);

            double time = runGetLast(opsCount, slList);

            // 更新測試紀錄
            spendTimes.addLast(time);
            ops.addLast(opsCount);
            Ns.addLast(N);
        }

        // 印出測試紀錄
        printTimingTable(Ns, spendTimes, ops);
    }

    /**
     * 執行測試
     * @param opsCount 呼叫次數
     * @param targetList 測試目標
     * @return 花費總時間
     */
    private static double runGetLast(int opsCount, SLList<Integer> targetList) {
        // Start timing
        Stopwatch stopwatch = new Stopwatch();

        // run getLast
        for (int j = 0; j < opsCount; j++) {
            targetList.getLast();
        }

        return stopwatch.elapsedTime();
    }

    private static SLList<Integer> getTestSLList(int n) {
        SLList<Integer> slList = new SLList<>();

        for (int i = 0; i < n; i++) {
            slList.addLast(i);
        }

        return slList;
    }

}
