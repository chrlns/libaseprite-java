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

/**
 * Color Profile Chunk (0x2007).
 * 
 * From the documentation:
 * "WORD        Type
 *             0 - no color profile (as in old .aseprite files)
 *             1 - use sRGB
 *             2 - use the embedded ICC profile
 *  WORD        Flags
 *             1 - use special fixed gamma
 * FIXED       Fixed gamma (1.0 = linear)
 *             Note: The gamma in sRGB is 2.2 in overall but it doesn't use
 *             this fixed gamma, because sRGB uses different gamma sections
 *             (linear and non-linear). If sRGB is specified with a fixed
 *             gamma = 1.0, it means that this is Linear sRGB.
 * BYTE[8]     Reserved (set to zero)
 * + If type = ICC:
 *  DWORD     ICC profile data length
 *  BYTE[]    ICC profile data. More info: http://www.color.org/ICC1V42.pdf"
 * 
 * @author Christian Lins <christian@lins.me>
 */
class ChunkColorProfile extends Chunk {

    public static final int TYPE_NO_PROFILE = 0;
    public static final int TYPE_SRGB = 1;
    public static final int TYPE_ICC = 2;
    
    @Override
    protected void read(InputStream in) throws IOException {
        Logger.getLogger("libasesprite-java").info("Reading ColorProfile chunk");
        
        int type  = ByteTools.readShort(in); // WORD -> unsigned short
        int flags = ByteTools.readShort(in); // WORD
        double gamma = ByteTools.readFixed(in); // FIXED 
        
        // Skip reserved bytes
        in.skip(8);
        if (type == TYPE_ICC) {
            Logger.getLogger("libasesprite-java").warning("ICC profile not supported");
            long iccLen = ByteTools.readInt(in);
            in.skip(iccLen); 
        }
    }
    
}
