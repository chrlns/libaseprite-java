/* MIT License
 *
 * libaseprite-java
 * Copyright (c) 2023-2024 Christian Lins <christian@lins.me>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package games.homeship.aseprite;

import java.io.IOException;
import java.io.InputStream;

/**
 * Utility functions for getting little endians from bytes.
 * 
 * @author Christian Lins <christian@lins.me>
 */
class ByteTools {
    public static long toInt(byte[] bytes) {
        assert bytes.length >= 4;
        
        return (bytes[3] << 24) | 
              ((bytes[2] << 16) & 0xFF0000) | 
              ((bytes[1] << 8) & 0xFF00) | 
               (bytes[0] & 0xFF);
    }
    
    public static int toShort(byte[] bytes) {
        assert bytes.length >= 2;
        
        return ((bytes[1] << 8) & 0xFF00) | (0xFF & bytes[0]);
    }
    
    public static short toByte(byte[] bytes) {
        return (short)(~bytes[0] + 1);
    }
    
    public static long readInt(InputStream in) throws IOException {
        byte[] buf = readBytes(in, 4);
        
        return toInt(buf);
    }
    
    public static int readShort(InputStream in) throws IOException {
        byte[] buf = readBytes(in, 2);
        
        return toShort(buf);
    }
    
    public static short readByte(InputStream in) throws IOException {
        byte[] buf = readBytes(in, 1);
        
        return toByte(buf);
    }
    
    /**
     * Reads a fixed point 32bit number. The number has 16 bits before and after
     * the decimal point. We assume that each of this numbers are signed 16-bit
     * integers stored in little endian.
     * @param in
     * @return
     * @throws IOException 
     */
    public static double readFixed(InputStream in) throws IOException {
        double num0 = readShort(in);
        double num1 = readShort(in);
        
        // Shifting the point...
        while (num1 > 0) {
            num1 /= 10.0;
        }
        
        return num0 + num1;
    }
    
    public static byte[] readBytes(InputStream in, int len) throws IOException {
        byte[] buf = new byte[len];
        
        if (in.read(buf) != len) {
            throw new IOException("Unexpected end of file.");
        }
        
        return buf;
    }
    
    public static String readString(InputStream in) throws IOException {
        int len = readShort(in);
        var buf = readBytes(in, len);
        var str = new String(buf);
        return str;
    }
}
