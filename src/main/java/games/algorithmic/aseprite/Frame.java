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
            // TODO We have to handle the different types here
            chunks[i] = new Chunk();
            chunks[i].read(in);
        }
    }
}
