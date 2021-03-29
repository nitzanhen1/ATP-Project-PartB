package algorithms.mazeGenerators;
import java.util.LinkedList;
import java.util.Random;
public class MyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rowSize, int columnSize) {
        //prim's algorithm
        Maze maze = new Maze(rowSize,columnSize);
        for (int i=0; i<rowSize; i++){
            for (int j=0;j<columnSize; j++)
                maze.setCell(i,j,1);
        }

        LinkedList<int[]> passages = new LinkedList<>();
        Random rand = new Random();

        int x = maze.getStartPosition().getRowIndex();
        int y = maze.getStartPosition().getColumnIndex();
        passages.add(new int[]{x,y,x,y});

        while ( !passages.isEmpty() ){
            int[] nextPath = passages.remove( rand.nextInt( passages.size() ) );
            x = nextPath[2];
            y = nextPath[3];
            if ( maze.getCell(x,y) == 1 )
            {
                maze.setCell(nextPath[0],nextPath[1],0);
                maze.setCell(x,y,0);
                if (maze.getCell(x-2,y) == 1 )
                    passages.add( new int[]{x-1,y,x-2,y} );
                if (maze.getCell(x,y-2) == 1 )
                    passages.add( new int[]{x,y-1,x,y-2} );
                if (maze.getCell(x+2,y) == 1 )
                    passages.add( new int[]{x+1,y,x+2,y} );
                if (maze.getCell(x,y+2) == 1 )
                    passages.add( new int[]{x,y+1,x,y+2} );
            }
        }

        x = maze.getGoalPosition().getRowIndex();
        y = maze.getGoalPosition().getColumnIndex();
        if(maze.getCell(x,y)==1) {
            maze.setCell(x, y, 0);
            if(maze.getCell(x-1,y) != 0 && maze.getCell(x+1,y) != 0 && maze.getCell(x,y-1) != 0 && maze.getCell(x,y+1) != 0)
                maze.setCell(x-1, y, 0);
        }


        return maze;
    }
}
