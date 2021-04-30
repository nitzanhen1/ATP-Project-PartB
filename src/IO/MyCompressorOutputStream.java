package IO;
import java.io.IOException;
import java.io.OutputStream;
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
        int sizeCompressed = (b.length-12)/8+12;

        if((b.length-12)%8>0)
            sizeCompressed++;
        byte[] compressedMaze = new byte[sizeCompressed];

        for(int i=0;i<12;i++)
            compressedMaze[i]=b[i];

        int index=12;
        int power;
        int advance;
        for(advance=12; advance+8<= b.length;advance=advance+8){
            power = (int)Math.pow(2,7);
            for(int i=0;i<8;i++) {
                compressedMaze[index]+= b[i+advance]*power;
                power=power/2;
            }
            index++;
        }

        int lastIter=b.length-advance;
        power=(int)Math.pow(2,lastIter-1);
        for(int i=0;i<lastIter;i++){
            compressedMaze[index]+=b[i+advance]*power;
        }
      out.write(compressedMaze);

    }
}
