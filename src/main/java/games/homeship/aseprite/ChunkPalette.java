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
 * Palette chunk.
 * 
 * @author Christian Lins <christian@lins.me>
 */
class ChunkPalette extends Chunk {

    @Override
    protected void read(InputStream in) throws IOException {
        Logger.getLogger("libasesprite-java").info("Reading Palette chunk");
        
        long numEntries = ByteTools.readInt(in);
        long firstColorIdx = ByteTools.readInt(in);
        long lastColorIdx = ByteTools.readInt(in);
        in.skip(8); // Skip reserved bytes
        
        for (long i = firstColorIdx; i <= lastColorIdx; i++) {
            int hasName = ByteTools.readShort(in);
            
            short r = ByteTools.readByte(in);
            short g = ByteTools.readByte(in);
            short b = ByteTools.readByte(in);
            short a = ByteTools.readByte(in);
            
            if (hasName == 1) {
                var name = ByteTools.readString(in);
                Logger.getLogger("libasesprite-java").log(Level.INFO, "Palette entry name = {0}", name);
            }
        }
    }
    
}
