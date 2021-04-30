package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class SimpleCompressorOutputStream extends OutputStream {
    OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        //out.write(b);
        int rowSize = Byte.toUnsignedInt(b[0])*256+Byte.toUnsignedInt(b[1]);
        int columnSize = Byte.toUnsignedInt(b[2])*256+Byte.toUnsignedInt(b[3]);

        ArrayList<Byte> listMaze = new ArrayList<>();
        int counter=0;
        boolean color=false;// false= o,(path). true = 1,(wall)
        for(int i=0;i<rowSize;i++){
            for(int j=0;j<columnSize;j++){
                if(!color){
                    if(b[i*columnSize+j+12]==0){
                        counter++;
                    }
                    else{
                        addMazeToByteList(counter,listMaze);
                        counter=1;
                        color=true;
                    }
                }
                else{
                    if(b[i*columnSize+j+12]==1){
                        counter++;
                    }
                    else{
                        addMazeToByteList(counter,listMaze);
                        counter=1;
                        color=false;
                    }
                }
            }
            addMazeToByteList(counter, listMaze);
            counter=0;
            color=false;
        }
        byte[] compressedMaze = new byte[listMaze.size()+12];
        for(int i=0;i<12;i++){
            compressedMaze[i]=b[i];
        }
        for(int i=0;i<listMaze.size();i++){
            compressedMaze[i+12]=listMaze.get(i);
        }

        out.write(compressedMaze);
    }

    private void addMazeToByteList(int size,ArrayList<Byte> listSizes) {
        int counter=size;
        if(counter==0){
            listSizes.add((byte)0);
            return;
        }

        while(counter>0){
            if(counter>255){
                listSizes.add((byte)255);
                listSizes.add((byte)0);
            }
            else
                listSizes.add((byte)counter);
            counter=counter-255;
        }

    }
}
