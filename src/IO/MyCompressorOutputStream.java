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
        int sizeCompressed = (b.length-12)/8+12;

        if((b.length-12)%8>0)
            sizeCompressed++;
        byte[] compressedMaze = new byte[sizeCompressed];

        for(int i=0;i<12;i++)
            compressedMaze[i]=b[i];
        HashMap<Integer,Integer> m= new HashMap<Integer, Integer>();//
        int num;
        int index=12;
        int idx=12;
        while(idx+8<b.length) {
            num = 0;
            for(int i=7;i>=0;i--){
                num=num+(int)Math.pow(2,7-i)*b[idx+i];
            }
            compressedMaze[index]=(byte)num;
            if(!m.containsKey(num))//
                m.put(num,1) ;//
            else//
                m.put(num,m.get(num)+1);//
            index++;
            idx=idx+8;
        }

        int rest= b.length-idx-1;
        num = 0;
        for(int i=rest;i>=0;i--){
            num=num+(int)Math.pow(2,rest-i)*b[idx+i];
        }
        if(!m.containsKey(num))//
            m.put(num,1) ;//
        else//
            m.put(num,m.get(num)+1);//
        compressedMaze[index]=(byte)num;
        System.out.println(m.toString());
        /*int index=12;
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
        }*/

      out.write(compressedMaze);

    }
}
