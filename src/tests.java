import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

    public static void main(String[] args) throws UnsupportedEncodingException {
        
        String[] redTrue = {"00","33","66","99","CC","FF"};
        String[] greenTrue = {"00","24","49","6D","92","B6","DB","FF"};
        String[] blueTrue = {"00","40","80","C0","FF"};
        

        String result = hexStringToByteArray(redTrue[1]);
        System.out.println(result);
    }
}