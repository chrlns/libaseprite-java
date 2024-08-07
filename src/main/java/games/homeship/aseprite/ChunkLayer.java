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
 * A Layer Chunk.
 * 
 * @author Christian Lins <christian@lins.me>
 */
class ChunkLayer extends Chunk {

    private int flags;
    private int type;
    private int childLevel;
    private int blendMode;
    
    @Override
    protected void read(InputStream in) throws IOException {
        Logger.getLogger("libasesprite-java").info("Reading Layer chunk");
        
        flags = ByteTools.readShort(in);
        type = ByteTools.readShort(in);
        childLevel = ByteTools.readShort(in);
        int defaultLayerWidth = ByteTools.readShort(in); // ignored
        int defaultLayerHeight = ByteTools.readShort(in); // ignored
        blendMode = ByteTools.readShort(in);
        short opacity = ByteTools.readByte(in);
        
        in.skip(3); // Skip reserved bytes
        String name = ByteTools.readString(in);
        Logger.getLogger("libasesprite-java").log(Level.INFO, "Read Layer {0}", name);
        
        if (LayerType.TYPE_TILEMAP.equals(type)) {
            long tilesetIdx = ByteTools.readInt(in);
        }
    }
    
}
