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
    public void write(int b) throws IOException {out.write(b);}

    @Override
    public void write(byte[] b) throws IOException {

        //after the first 12 cell that represent sizes and position
        //we go thought the bytearray and sum sequences of neighboring  1 and 0
        //each sequence of 1 or 0 will convert to decimal number and will enter the compressed byteArray
        //if the sequence is larger that 255, split the number to 3 cells- 255,0,(rest of the number)
        ArrayList<Byte> listMaze = new ArrayList<>();
        boolean color=false;// false= 0,(path). true = 1,(wall)
        int counter=0;

        //color determine if the sequence is of 0 or zeros and when to add to the maze (when the color changes)
        for(int i=12;i<b.length;i++) {
            if (!color) {
                if (b[i] == 0) {
                    counter++;
                } else {
                    addMazeToByteList(counter, listMaze);
                    counter = 1;
                    color = true;
                }
            }
            else {
                if (b[i] == 1) {
                    counter++;
                } else {
                    addMazeToByteList(counter, listMaze);
                    counter = 1;
                    color = false;
                }
            }
        }
        addMazeToByteList(counter, listMaze);
        //adding the sizes to byte array
        byte[] compressedMaze = new byte[listMaze.size()+12];
        for(int i=0;i<12;i++){
            compressedMaze[i]=b[i];
        }
        //adding ArrayList to the byte array
        for(int i=0;i<listMaze.size();i++) {
            compressedMaze[i + 12] = listMaze.get(i);
        }
        //send the compressed maze to the output stream
        out.write(compressedMaze);
    }


    private void addMazeToByteList(int size,ArrayList<Byte> listSizes) {
        //convert the counter >255 to several cells and add to the list
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
