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

package games.algorithmic.aseprite;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An Aseprite file.
 * 
 * @author Christian Lins <christian@lins.me>
 */
public class AsepriteFile {
    private final BufferedInputStream in;
    
    private Header  header;
    private Frame[] frames;
            
    public AsepriteFile(InputStream in) {
        if (in instanceof BufferedInputStream) {
            this.in = (BufferedInputStream)in;
        } else {
            this.in = new BufferedInputStream(in);
        }
    }
    
    public Image getImage() throws IOException {
        readASEFile();
        return null;
    }
    
    /**
     * Read the ASE file.
     */
    private void readASEFile() throws IOException {
        header = new Header();
        header.read(in);
        
        frames = new Frame[header.getNumFrames()];
        
        for (int i = 0; i < header.getNumFrames(); i++) {
            Logger.getLogger("libasesprite-java").log(Level.INFO, "Reading frame #{0}", i);
            frames[i] = new Frame();
            frames[i].read(in);
        }
    }
}
