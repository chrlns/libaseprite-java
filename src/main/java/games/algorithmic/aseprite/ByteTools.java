package games.algorithmic.aseprite;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Christian Lins <christian@lins.me>
 */
class ByteTools {
    public static int toShort(byte[] bytes) {
        assert bytes.length >= 2;
        
        return (bytes[0] << 8) | bytes[1];
    }
    
    public static long toInt(byte[] bytes) {
        assert bytes.length >= 4;
        
        return (bytes[0] << 24) | (bytes[1] << 16) | (bytes[2] << 8) | bytes[0];
    }
    
    public static long readInt(InputStream in) throws IOException {
        byte[] buf = readNum(in, 4);
        
        return toInt(buf);
    }
    
    public static int readShort(InputStream in) throws IOException {
        byte[] buf = readNum(in, 2);
        
        return toShort(buf);
    }
    
    public static byte[] readNum(InputStream in, int len) throws IOException {
        byte[] buf = new byte[len];
        
        if (in.read(buf) != 2) {
            throw new IOException("Unexpected end of file.");
        }
        
        return buf;
    }
}
