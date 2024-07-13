/* MIT License
 *
 * libaseprite-java
 * Copyright (c) 2023 Christian Lins <christian@lins.me>
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
 *
 * @author Christian Lins <christian@lins.me>
 */
class FrameHeader {
    public static final int MAGIC = 0xF1FA;
    
    private long numChunks;
    private int frameDuration;
    private long frameSize;
    
    public void read(InputStream in) throws IOException {
        frameSize = ByteTools.readInt(in);
        
        int magic = ByteTools.readShort(in);
        if (magic != MAGIC) {
            throw new IOException("Invalid frame header.");
        }
        
        numChunks = ByteTools.readShort(in);
        
        frameDuration = ByteTools.readShort(in);
        
        in.skip(2); // reserved
        
        if (numChunks == 0xFFFF) {
            // Use the extended field
            numChunks = ByteTools.readInt(in);
        } else {
            in.skip(4);
        }
    }
    
    public long getNumChunks() {
        return numChunks;
    }
    
    public int getFrameDuration() {
        return frameDuration;
    }
    
    public long getFrameSize() {
        return frameSize;
    }
}
