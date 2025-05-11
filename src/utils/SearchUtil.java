package utils;

import model.Siswa;
import java.util.List;

public class SearchUtil {

    public static int binarySearch(List<Siswa> list, String targetNama) {
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            String nama = list.get(mid).getNama();
            int cmp = nama.compareToIgnoreCase(targetNama);
            if (cmp == 0) return mid;
            if (cmp < 0) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static double avgRecursive(List<Siswa> list) {
        return totalRecursive(list, 0) / list.size();
    }

    private static double totalRecursive(List<Siswa> list, int idx) {
        if (idx >= list.size()) return 0;
        return list.get(idx).getRataRata() + totalRecursive(list, idx + 1);
    }
}
