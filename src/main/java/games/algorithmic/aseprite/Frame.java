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

package games.algorithmic.aseprite;

import java.io.InputStream;
import java.io.IOException;

/**
 * An ASE Frame.
 * 
 * @author Christian Lins <christian@lins.me>
 */
class Frame {
    private FrameHeader header;
    private Chunk[] chunks;
    
    public void read(InputStream in) throws IOException {
        header = new FrameHeader();
        header.read(in);
        
        // We cannot support > 2^31-1 chunks
        chunks = new Chunk[(int)header.getNumChunks()];
        for (int i = 0; i < header.getNumChunks(); i++) {
            chunks[i] = Chunk.readAndCreate(in);
        }
    }
}
