package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ユーティリティライブラリ
 *
 * @author tadaki
 */
public class Utils {

    /**
     * 空のリストを作成
     *
     * @param <T> リストの要素のクラス
     * @return
     */
    public static <T> List<T> createList() {
        return Collections.synchronizedList(new ArrayList<T>());
    }

    /**
     * 整数（値が0からlimit-1）のでたらめな配列作成
     *
     * @param limit リスト要素の最大値-1
     * @param num 帰す要素の数
     * @return 結果の配列
     */
    public static int[] createRandomNumberList(int limit, int num) {
        //入力リスト生成
        List<Integer> inputList = createIntegerList(limit);
        //出力リスト
        List<Integer> list = createList();
        int count = 0;
        while (inputList.size() > 0 && count < num) {
            int k = (int) (inputList.size() * Math.random());
            Integer kk = inputList.remove(k);
            list.add(kk);
            count++;
        }
        //出力リストの配列化
        int out[] = new int[num];
        for (int i = 0; i < num; i++) {
            out[i] = list.get(i);
        }
        return out;
    }

    /**
     * 0 からlimit-1 までの整数が順に並んだリスト
     *
     * @param limit
     * @return
     */
    public static List<Integer> createIntegerList(int limit) {
        List<Integer> list = createList();
        for (int i = 0; i < limit; i++) {
            list.add(i);
        }
        return list;
    }

    /**
     * 整数のでたらめなリスト作成
     *
     * @param limit リスト要素の最大値-1
     * @return
     */
    public static int[] createRandomNumberList(int limit) {
        return createRandomNumberList(limit, limit);
    }

    /**
     * 空の集合の作成
     *
     * @param <T> 集合の要素のクラス
     * @return
     */
    public static <T> Set<T> createSet() {
        return Collections.synchronizedSet(new HashSet<>());
    }

    /**
     * 空の写像の作成
     *
     * @param <K> 写像のキーのクラス
     * @param <V> 写像の値のクラス
     * @return
     */
    public static <K, V> Map<K, V> createMap() {
        return Collections.synchronizedMap(new HashMap<>());
    }

    /**
     * Objectを区切り文字で連結する
     * @param sep　区切り文字
     * @param objects
     * @return 
     */
    public static String Object2String(char sep, Object... objects) {
        StringBuilder sb = new StringBuilder();
        int n = objects.length;
        for (int i = 0; i < n - 1; i++) {
            sb.append(objects[i]).append(sep);
        }
        sb.append(objects[n - 1]);
        return sb.toString();
    }

}
