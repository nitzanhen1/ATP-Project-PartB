package IO;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {

    InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in=in;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    @Override
    public int read(byte[] b) throws IOException {
        for(int i=0;i<12;i++)
            b[i]=(byte)in.read();
        int idx=12;
        while(idx<b.length) {
            int num = Byte.toUnsignedInt((byte)in.read());

        }
         return 0;
    }
}
