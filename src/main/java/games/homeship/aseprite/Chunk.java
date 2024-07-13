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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An abstract Chunk.
 * 
 * @author Christian Lins <christian@lins.me>
 */
abstract class Chunk {
    protected long length;
    protected int type;
    
    public static Chunk readAndCreate(InputStream in) throws IOException {
        Chunk chunk = null;
        long length = ByteTools.readInt(in);
        int type = ByteTools.readShort(in);
        
        switch(type) {  
            case 0x2004:    // Layer Chunk
                chunk = new ChunkLayer();
                break;
            case 0x2005:    // Cel Chunk
                chunk = new ChunkCel();
                break;
            case 0x2006:    // Cel Extra Chunk
                chunk = new ChunkCelExtra();
                break;
            case 0x2007:    // Color Profile Chunk
                chunk = new ChunkColorProfile();
                break;
            case 0x2019:    // Palette Chunk
                chunk = new ChunkPalette();
                break;
            case 0x0004:    // Old palette chunk
            case 0x0011:    // Old palette chunk   
            default:
                // ignore unknown type
                in.skip((int)(length - 6));
                Logger.getLogger("libasesprite-java").log(Level.WARNING, "Skip chunk type {0}", type);
                break;
        }
        
        if (chunk != null) {
            chunk.length = length;
            chunk.type = type;
            chunk.read(in);
        }
        
        return chunk;
    }
    
    protected abstract void read(InputStream in) throws IOException;
}
