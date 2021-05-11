package IO;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

public class MyCompressorOutputStream extends OutputStream {
    OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        //receive a byteArray representation of a maze and compress it.
        //each maze will be compressed to 1/8 of it size +12 first cells that determine the sizes and positions

        int sizeCompressed = (b.length-12)/8+12;
        if((b.length-12)%8>0)
            sizeCompressed++;
        byte[] compressedMaze = new byte[sizeCompressed];

        //get the first 12 cells without changes, that represent maze row and col size, start and goal pos
        for(int i=0;i<12;i++)
            compressedMaze[i]=b[i];

        //going through the maze,
        //from each 8 close cells, that represent an 8 digit binary number convert to decimal number
        //and saving it as one cell byte number
        //example: [0,0,0,0,1,1,1,1] => 15 => save (byte)15 inside the array
        int num;
        int index=12;
        int idx=12;
        while(idx+8<b.length) {
            num = 0;
            for(int i=7;i>=0;i--){
                num=num+(int)Math.pow(2,7-i)*b[idx+i];
            }
            compressedMaze[index]=(byte)num;
            index++;
            idx=idx+8;
        }
        // the main loop transform all of the maze except the last few cell that might not divide by 8
        int rest= b.length-idx-1;
        num = 0;
        for(int i=rest;i>=0;i--){
            num=num+(int)Math.pow(2,rest-i)*b[idx+i];
        }
        compressedMaze[index]=(byte)num;
      out.write(compressedMaze);
    }
}
