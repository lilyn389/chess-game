import javax.swing.ImageIcon;
import java.util.*;

public class King extends Piece implements Cloneable{
    
    private ImageIcon king;
    private Vector<Piece> assassin = new Vector<Piece>(); // this is the piece that is currently able to kill the King
    private Vector<Piece> pieces = new Vector<Piece>(); // same color as the King
    private Vector<Piece> enemy_pieces = new Vector<Piece>();
    //private Tile[][] grid;
    
    private boolean firstMove = true;
    
    public King(Tile[][] gridIn, String color, String name, int row, int column, int ID, boolean alive) {
        super(gridIn, color, name, row, column, ID, alive);
        if (color == "white") {
            king = new ImageIcon("white_king.png");
        }
        else {
            king = new ImageIcon("black_king.png");
        }
    }
    public King(King in)
    {
        super(in);
        firstMove = in.getFirstMove();
        if (color == "white") {
            king = new ImageIcon("white_king.png");
        }
        else {
            king = new ImageIcon("black_king.png");
        }
        for(int i = 0;i<grid.length;++i)
        {
            for(int j=0;j<grid[0].length;++j)
            {
                if(grid[i][j].getPiece() != null)
                {
                    if(this.color != grid[i][j].getPiece().color)
                    {
                        enemy_pieces.add(grid[i][j].getPiece());
                    }
                    else if(this.color == grid[i][j].getPiece().color)
                    {
                        pieces.add(grid[i][j].getPiece());
                    }
                }
            }
        }
        
    }
    public boolean getFirstMove()
    {
        return firstMove;
    }
    public Object clone() {
        try {
            return (King) super.clone();
        } catch (CloneNotSupportedException e) {
            return new King(this.grid, this.color,this.name,this.row,this.column,this.ID,this.is_alive);
        }
    }
    public Vector<Piece> getEnemyPieces() {
        return enemy_pieces;
    }
    
    public void setEnemyPieces(Vector<Piece> enemy_pieces) {
        this.enemy_pieces = enemy_pieces;
    }
    
    public Vector<Piece> getPieces() {
        return pieces;
    }
    
    public void setPieces(Vector<Piece> pieces) {
        this.pieces = pieces;
    }
    
    public ImageIcon getIcon() {
        return king;
    }
    
    public void setIcon(ImageIcon king) {
        this.king = king;
    }
    
    public Tile[][] getGrid() {
        return grid;
    }
    
    public void setGrid(Tile[][] grid) {
        this.grid = grid;
    }
    
    public boolean isValidMove(int x, int y) {
        
        if (firstMove) {
            
            if (validKingMove(x, y) || validCastle(x, y)) {
                
                firstMove = false;
                return true;
            }
            else {
                
                return false;
            }
        }
        
        return validKingMove(x, y);
    }
    
    private boolean validKingMove(int x, int y) {
        
        if (row == x && y == column) {
            return false;
        }
        if(x >= 0 && x <= 7 && y >=0 && y <= 7)
        {
            if (!grid[x][y].isEmpty()) {
                if (grid[x][y].getPiece().getColor() == color) {
                    return false;
                }
            }
            
            int row_diff = Math.abs(x - this.getRow());
            int column_diff = Math.abs(y - this.getColumn());
         
            
            if (row_diff <= 1 && row_diff >= 0 && column_diff <= 1 && column_diff >= 0) {
                
                return true;
            }
            else {
                
                return false;
            }
        }
        return false;
    }
    
    //Future feature
    private boolean validCastle(int x, int y) {
        
        return false;
    }
    
    public boolean isCheck(int x, int y) 
    {
    	boolean exist;
        boolean check = false;
        // loop through vector of enemy pieces and see if any of them can kill the king
        for (int i = 0; i < enemy_pieces.size(); i++) {
            if (enemy_pieces.get(i).isValidMove(x, y)) {
                exist = false;
                check = true;
                for (int j = 0; j < assassin.size(); j++) {
                    if (assassin.get(j).getID() == enemy_pieces.get(i).getID()) {
                        exist = true;
                        //return true;  // don't add to list since it is already there
                    }
                }
                if (!exist)
                    assassin.addElement(enemy_pieces.get(i)); // add the assassin to the list
                //return true;
            }
        }
        return check;
    }
    
    public boolean isCheck() {
        boolean exist;
        boolean check = false;
        // loop through vector of enemy pieces and see if any of them can kill the king
        for (int i = 0; i < enemy_pieces.size(); i++) {
            if (enemy_pieces.get(i).isValidMove(row, column)) {
                exist = false;
                check = true;
                for (int j = 0; j < assassin.size(); j++) {
                    if (assassin.get(j).getID() == enemy_pieces.get(i).getID()) {
                        exist = true;
                        //return true;  // don't add to list since it is already there
                    }
                }
                if (!exist)
                    assassin.addElement(enemy_pieces.get(i)); // add the assassin to the list
                //return true;
            }
        }
        return check;
    }
    
    
    
    /*************************************************************
     * this is a brute force algorithm for validating Check Mate *
     *************************************************************/
    public boolean isCheckMate() {
        
        /**********************************************************
         *    check if the king is able to escape on its own
         *********************************************************/
        
        King temp_king;
        // make a phantom king to move around and check if there is any way to escape being killed by the enemy
        if (color == "white") {
            temp_king = new King(grid, "white", "king", row, column, 0, true);
            temp_king.setGrid(grid);
            temp_king.setPieces(pieces);
            temp_king.setEnemyPieces(enemy_pieces);
        }
        else {
            temp_king = new King(grid, "black", "king", row, column, 0, true);
            temp_king.setGrid(grid);
            temp_king.setPieces(pieces);
            temp_king.setEnemyPieces(enemy_pieces);
        }
        
        // check all 8 spaces that the king may be able to move to
        for (int temp_row = row - 1; temp_row <= row + 1; temp_row++) {
            for (int temp_column = column - 1; temp_column <= column + 1; temp_column++) {
                if (temp_row >= 0 && temp_row < 8 && temp_column >= 0 && temp_column < 8) {
                    if (temp_king.isValidMove(temp_row, temp_column)) {
                        temp_king.setRow(temp_row);
                        temp_king.setColumn(temp_column);
                        if (!temp_king.isCheck()) {
                            return false;  // the king can escape
                        }
                    }
                }
            }
        }
        
        // if the King can't escape, and there are more than one piece currently able to kill him
        // then the king is in check mate
        if (assassin.size() > 1) {
            return true;
        }
        
        /**********************************************************
         *        check if anyone can save the king
         *********************************************************/
        
        // see if anyone can kill or block the assassin
        
        // unique check for knight
        if (assassin.get(0).getName() == "knight") {
            for (int i = 0; i < pieces.size(); i++) {  // see if one of the pieces can kill the knight
                if (pieces.get(i).isValidMove(assassin.get(0).getRow(), assassin.get(0).getColumn())) {
                    return false;  // someone is able to kill the knight
                }
            }
        }
        
        // check for all others
        int row_diff = assassin.get(0).getRow() - row;
        int column_diff = assassin.get(0).getColumn() - column;
        
        // Logic: if one of the kings pieces can kill or block the assassin, then we are not in checkmate.
        if (row_diff == 0) { // the assassin is on the same row as the king (this will be an orthogonal kill)
            
            if (column_diff < 0) { // this means that the assassin is to the left of the king
                for (int i = column; i >= assassin.get(0).getColumn(); i++) {  // check every space between assassin and king
                    for (int j = 0; j < pieces.size(); j++) {
                        if (pieces.get(j).isAlive() == true) {  // it needs to be an active piece
                            if (pieces.get(j).isValidMove(row, i)) {
                                return false;
                            }
                        }
                    }
                }
            }
            else if (column_diff > 0) { // this means that the assassin is to the right of the king
                for (int i = column; i <= assassin.get(0).getColumn(); i--) {  // check every space between assassin and king
                    for (int j = 0; j < pieces.size(); j++) {
                        if (pieces.get(j).isAlive() == true) {  // it needs to be an active piece
                            if (pieces.get(j).isValidMove(row, i)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        
        else if (column_diff == 0) { // the assassin is in the same column as the king (this will be an orthogonal kill)
            
            if (row_diff < 0) { // this means that the assassin is behind the king
                for (int i = row; i >= assassin.get(0).getRow(); i++) {  // check every space between assassin and king
                    for (int j = 0; j < pieces.size(); j++) {
                        if (pieces.get(j).isAlive() == true) {  // it needs to be an active piece
                            if (pieces.get(j).isValidMove(i, column)) {
                                return false;
                            }
                        }
                    }
                }
            }
            if (row_diff > 0) { // this means that the assassin is in front of the king
                for (int i = row; i <= assassin.get(0).getRow(); i--) {  // check every space between assassin and king
                    for (int j = 0; j < pieces.size(); j++) {   // try every piece
                        if (pieces.get(j).isAlive() == true) {  // it needs to be an active piece
                            if (pieces.get(j).isValidMove(i, column)) {  
                                return false;
                            }
                        }
                    }
                }
            }
            
        }
        
        else { // this will be a diagonal kill
            
            if (row_diff < 0 && column_diff > 0) { // attack is coming from upper right quadrant
                for (int i = row - 1, j = column + 1; i >= assassin.get(0).getRow() && j <= assassin.get(0).getColumn(); i--, j++) {
                    for (int k = 0; k < pieces.size(); k++) {
                        if (pieces.get(k).isAlive() == true) {
                            if (pieces.get(k).isValidMove(i, j)) {
                                return false;
                            }
                        }
                    }
                }
            }
            
            if (row_diff < 0 && column_diff < 0) { // attack is coming from upper left quadrant
                for (int i = row - 1, j = column - 1; i >= assassin.get(0).getRow() && j >= assassin.get(0).getColumn(); i--, j--) {
                    for (int k = 0; k < pieces.size(); k++) {
                        if (pieces.get(k).isAlive() == true) {
                            if (pieces.get(k).isValidMove(i, j)) {
                                return false;
                            }
                        }
                    }
                }
                
            }
            
            if (row_diff > 0 && column_diff < 0) { // attack is coming from bottom left quadrant
                for (int i = row + 1, j = column - 1; i <= assassin.get(0).getRow() && j >= assassin.get(0).getColumn(); i++, j--) {
                    for (int k = 0; k < pieces.size(); k++) {
                        if (pieces.get(k).isAlive() == true) {
                            if (pieces.get(k).isValidMove(i, j)) {
                                return false;
                            }
                        }
                    }
                }
                
            }
            
            if (row_diff > 0 && column_diff > 0) { // attack is coming from bottom right quadrant
                for (int i = row + 1, j = column + 1; i <= assassin.get(0).getRow() && j <= assassin.get(0).getColumn(); i++, j++) {
                    for (int k = 0; k < pieces.size(); k++) {
                        if (pieces.get(k).isAlive() == true) {
                            if (pieces.get(k).isValidMove(i, j)) {
                                return false;
                            }
                        }
                    }
                }
                
            }
            
            
        }
        return true;   // the king is in check mate
    }
    public int getValue()
    {
        return 900;
    }
}
