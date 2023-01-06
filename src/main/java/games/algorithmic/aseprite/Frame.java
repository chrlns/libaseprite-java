package games.algorithmic.aseprite;

import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author Christian Lins <christian@lins.me>
 */
class Frame {
    private FrameHeader header;
    
    public void read(DataInputStream in) throws IOException {
        header = new FrameHeader();
        header.read(in);
    }
}
