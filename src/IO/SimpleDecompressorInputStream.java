package IO;

import algorithms.mazeGenerators.Position;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {

    InputStream in;

    public SimpleDecompressorInputStream(InputStream in) {
        this.in=in;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    @Override
    public int read(byte[] b) throws IOException {

        for(int i=0;i<12;i++)
            b[i]=(byte)in.read();

        int num;
        int idx=12;
        boolean flag=false;
        while (idx<b.length){
            num= Byte.toUnsignedInt((byte)in.read());
            while (num>0){
                if (!flag)
                    b[idx]=0;
                else
                    b[idx]=1;
                idx++;
                num--;
            }
            flag=!flag;
        }


        return 0;
    }
}
