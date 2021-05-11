package IO;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {

    InputStream in;

    public MyDecompressorInputStream(InputStream in) {this.in=in;}

    @Override
    public int read() throws IOException {return 0;}

    @Override
    public int read(byte[] b) throws IOException {
        //receive a compressed maze, each cell represent 8 cell in the original maze
        //except the first 12 that represent the maze sizes and position
        int num;
        for(int i=0;i<12;i++)
            b[i]=(byte)in.read();
        int idx=12;
        //transform each cell of received bytearray to 8 cell in the bytearray to send to client
        while(idx+8<b.length) {
            num = Byte.toUnsignedInt((byte)in.read());
            for(int i=7;i>=0;i--){
                b[idx+i]=(byte)(num%2);
                num=num/2;
            }
            idx=idx+8;
        }
        // the main loop transform all of the maze except the last cell that might not need 8 cells
        int rest= b.length-idx-1;
        num = Byte.toUnsignedInt((byte)in.read());
        for(int i=rest;i>=0;i--){
            b[idx+i]=(byte)(num%2);
            num=num/2;
        }
         return 0;
    }

}
