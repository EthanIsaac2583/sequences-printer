package kz.ruanjian.data;

public class DataUtil {

    public String substring(String target, int length) {
        return target.substring(0, length);
    }

    public String padToLength(String target, int length) {
        StringBuilder sb = new StringBuilder(target);
        while (sb.length() < length) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
