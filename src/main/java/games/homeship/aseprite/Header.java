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

import java.io.InputStream;
import java.io.IOException;

/**
 *
 * @author Christian Lins <christian@lins.me>
 */
class Header {
    public static final int MAGIC = 0xA5E0;
    
    private int numFrames;
    private int imageWidth, imageHeight;
    private long flags;
    
    private short paletteTransparentColorIdx;
    private int colorDepth;
    private int numColors;
    private short pixelWidth, pixelHeight;
    private int gridXPos, gridYPos;
    private int gridWidth, gridHeight;
    
    public void read(InputStream in) throws IOException {
        // Skip files size
        in.skip(4); 
        
        int magic = ByteTools.readShort(in);
        if (magic != MAGIC) {
            throw new IOException("Invalid file format.");
        }
        
        numFrames = ByteTools.readShort(in);
        
        imageWidth  = ByteTools.readShort(in);
        imageHeight = ByteTools.readShort(in);
        
        colorDepth = ByteTools.readShort(in);
        
        flags = ByteTools.readInt(in);
        
        // Skip deprecated speed field
        in.skip(2);
        
        // Skip 8 zero bytes
        in.skip(8);
        
        paletteTransparentColorIdx = (short)in.read();
        in.skip(3); // Skip 3 bytes
        
        numColors = ByteTools.readShort(in);
        if (numColors == 0) {
            numColors = 256; // for old sprites
        }
        
        // Pixel ratio is width/height
        pixelWidth  = (short)in.read();
        pixelHeight = (short)in.read();
        
        // Grid X/Y position, really a signed short
        gridXPos = ByteTools.readShort(in); 
        gridYPos = ByteTools.readShort(in);
        
        gridWidth = ByteTools.readShort(in);
        gridHeight = ByteTools.readShort(in);
        
        // Skip 84 bytes (reserved for future)
        in.skip(84);
    }
    
    public int getNumFrames() {
        return numFrames;
    }
}
