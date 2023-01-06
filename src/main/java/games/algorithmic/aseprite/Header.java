package games.algorithmic.aseprite;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Christian Lins <christian@lins.me>
 */
class Header {
    public static final int MAGIC = 0xA5E0;
    
    private int numFrames;
    private int imageWidth, imageHeight;
    private int flags;
    
    private short paletteTransparentColorIdx;
    private int numColors;
    private short pixelWidth, pixelHeight;
    private short gridXPos, gridYPos;
    private int gridWidth;
    
    public void read(DataInputStream in) throws IOException {
        // Skip files size
        in.skipBytes(4); 
        
        int magic = in.readInt();
        if (magic != MAGIC) {
            throw new IOException("Invalid file format.");
        }
        
        numFrames = in.readShort();
        
        imageWidth  = in.readShort();
        imageHeight = in.readShort();
        
        flags = in.readInt();
        
        // Skip deprecated speed field
        in.skipBytes(2);
        
        // Skip 8 zero bytes
        in.skipBytes(8);
        
        paletteTransparentColorIdx = in.readByte();
        in.skipBytes(3); // Skip 3 bytes
        
        numColors = in.readShort();
        if (numColors == 0) {
            numColors = 256; // for old sprites
        }
        
        // Pixel ratio is width/height
        pixelWidth  = in.readByte();
        pixelHeight = in.readByte();
        
        // Grid X/Y position, really a signed short
        gridXPos = in.readShort();
        gridYPos = in.readShort();
        
        gridWidth = in.readShort();
        
        // Skip 84 bytes (reserved for future)
        in.skipBytes(84);
    }
    
    public int getNumFrames() {
        return numFrames;
    }
}
