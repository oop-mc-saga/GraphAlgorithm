package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringJoiner;

/**
 * ファイル操作関係のユーティリティ
 * @author tadaki
 */
public class FileIO {

    /**
     * ファイル名を指定してBufferedWriterを開く
     *
     * @param filename
     * @return 開いたBufferedWriter
     * @throws IOException
     */
    public static BufferedWriter openWriter(String filename)
            throws IOException {
        FileOutputStream fStream = new FileOutputStream(new File(filename));
        return new BufferedWriter(new OutputStreamWriter(fStream));
    }

    /**
     * カンマ区切りで、対象オブジェクトを出力する
     *
     * @param out 出力先
     * @param objects 出力するオブジェクト列（不定）
     * @throws IOException
     */
    public static void writeCSV(BufferedWriter out, Object... objects)
            throws IOException {
        writeVars(out, ',', objects);
    }

    /**
     * スペース区切りで、対象オブジェクトを出力する
     *
     * @param out 出力先
     * @param objects 出力するオブジェクト列（不定）
     * @throws IOException
     */
    public static void writeSSV(BufferedWriter out, Object... objects)
            throws IOException {
        writeVars(out, ' ', objects);
    }

    /**
     * 任意の区切り文字で、対象オブジェクトを出力する
     *
     * @param out 出力先
     * @param sep 区切り文字
     * @param objects 出力するオブジェクト列（不定）
     * @throws IOException
     */
    public static void writeVars(BufferedWriter out, char sep, Object... objects)
            throws IOException {
        //objectを区切り文字で転結した文字列を生成
        StringJoiner sj = new StringJoiner(String.valueOf(sep),"","");
        for(Object o:objects){
            sj.add(o.toString());
        }
        out.write(sj.toString());
        out.newLine();
    }

    /**
     * ファイル名を指定してBufferedReaderを開く
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static BufferedReader openReader(String filename) throws
            IOException {
        FileInputStream fStream = new FileInputStream(new File(filename));
        return new BufferedReader(new InputStreamReader(fStream));
    }

    /**
     * 空白を区切り文字として、一行読みだす
     *
     * @param in
     * @return
     * @throws java.io.IOException
     */
    public static String[] readSSV(BufferedReader in) throws IOException {
        return readVars(in, "\\s+");
    }

    /**
     * カンマを区切り文字として、一行読みだす
     *
     * @param in
     * @return
     * @throws java.io.IOException
     */
    public static String[] readCSV(BufferedReader in) throws IOException {
        return readVars(in, "\\s*,\\s*");
    }

    /**
     * BufferedReaderから一行読み、sepで分割した文字列配列を返す
     *
     * @param in 入力
     * @param sep 文字列の分割を指定する正規表現
     * @return 文字列
     * @throws java.io.IOException
     */
    public static String[] readVars(BufferedReader in, String sep)
            throws IOException {
        String line;
        while ((line = in.readLine()) != null) {
            return line.split(sep);
        }
        return null;
    }

}
