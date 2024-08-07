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
import java.util.logging.Logger;
import static games.homeship.aseprite.ByteTools.*;

/**
 *
 * @author Christian Lins <christian@lins.me>
 */
public class ChunkCel extends Chunk {

    static final int TYPE_RAW = 0;
    static final int TYPE_LINKED_CEL = 1;
    static final int TYPE_COMPRESSED_IMAGE = 2;
    static final int TYPE_COMPRESSED_TILEMAP = 3;
    
    @Override
    protected void read(InputStream in) throws IOException {
        Logger.getLogger("libasesprite-java").info("Reading Cel chunk");
        
        int layerIdx = readShort(in); // WORD
        int xPos = readShort(in); // SHORT (same size as WORD but signed), does this work?
        int yPos = readShort(in); // SHORT (same size as WORD but signed), does this work?
        
        short opacityLevel = readByte(in);
        int celType = readShort(in);
        int zIdx = readShort(in); // SHORT
        
        in.skip(5); // Skip reserved bytes
        if (celType == TYPE_COMPRESSED_IMAGE) {
            int width = readShort(in);
            int height = readShort(in);
            // Read compressed pixels
        } else {
            throw new UnsupportedOperationException("Currently only compressed image cels are supported!");
        }
    }
    
}
