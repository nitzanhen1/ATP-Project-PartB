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
        /*too long, maybe need set position*/
        maze.setCell(maze.getStartPosition().getRowIndex(),maze.getStartPosition().getColumnIndex(),0);
        maze.setCell(maze.getGoalPosition().getRowIndex(),maze.getGoalPosition().getColumnIndex(),0);

        LinkedList<int[]> passages = new LinkedList<>();
        Random rand = new Random();
        //random start or start point?
        int x = rand.nextInt(rowSize);
        int y = rand.nextInt(columnSize);
        passages.add(new int[]{x,y,x,y});

        while ( !passages.isEmpty() ){
            int[] nextPath = passages.remove( rand.nextInt( passages.size() ) );
            x = nextPath[2];
            y = nextPath[3];
            if ( maze.getCell(x,y) == 1 )
            {
                maze.setCell(nextPath[0],nextPath[1],0);
                maze.setCell(x,y,0);
                if ( x >= 2 && maze.getCell(x-2,y) == 1 )
                    passages.add( new int[]{x-1,y,x-2,y} );
                if ( y >= 2 && maze.getCell(x,y-2) == 1 )
                    passages.add( new int[]{x,y-1,x,y-2} );
                if ( x < rowSize-2 && maze.getCell(x+2,y) == 1 )
                    passages.add( new int[]{x+1,y,x+2,y} );
                if ( y < columnSize-2 && maze.getCell(x,y+2) == 1 )
                    passages.add( new int[]{x,y+1,x,y+2} );
            }
        }
        return maze;
    }
}
