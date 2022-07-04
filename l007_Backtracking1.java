// BackTracking 
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class l007_Backtracking1 {
    
    // leetcode 37 :: Sudoku Solver
    public boolean isSafeToPLaceNumber(char[][] board, int r, int c, int num){
        // row
        for(int i=0;i<9;i++){
            if(board[r][i]-'0' == num){
                return false;
            }
        }
        //col
        for(int i=0;i<9;i++){
            if(board[i][c]-'0' == num){
                return false;
            }
        }
        // 3*3 matrix at particular cell
        int stRow=(r/3)*3;
        int stCol=(c/3)*3;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[stRow+i][stCol+j]-'0' == num){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean sudokuSolver(char[][]board, ArrayList<Integer> emptyCells, int idx){
        if(idx==emptyCells.size()){
            // than all empty cells are filled
            return true; // mark that suduko is solved
        }
        int cell1Didx=emptyCells.get(idx);
        int r=cell1Didx/9;
        int c=cell1Didx%9;
        // at empty cell :: we had 9 options (1-9)
        for(int num=1;num<=9;num++){
            if(isSafeToPLaceNumber(board,r,c,num)){
                board[r][c] = (char)(num+'0');
                // agr sukodu solve ho gya tab ni krenge unmark :: number sahi jagah place kiya tha
                if(sudokuSolver(board,emptyCells,idx+1)) {
                    return true;
                }
                board[r][c] = '.';
            }
        }
        return false;
    }
    public void solveSudoku(char[][] board) {
        int n=board.length, m=board[0].length;
        ArrayList<Integer> emptyCells=new ArrayList<>();
        // fill ArrayList with all cells that are empty
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(board[i][j]=='.'){
                    emptyCells.add(i*9+j);
                }
            }
        }
        // make call only from empty cell
        System.out.println(sudokuSolver(board,emptyCells,0));
    }
   

    // optimize :: Sudoku Solver :: using bit manipulation
    int[] row=new int[9];
    int[]col=new int[9];
    int[][]matrix=new int[3][3];
    public boolean sudokuSolver_bits(char[][]board, ArrayList<Integer> emptyCells, int idx){
        if(idx==emptyCells.size()){
            // than all empty cells are filled
            return true; // mark that suduko is solved
        }
        int cell1Didx=emptyCells.get(idx);
        int r=cell1Didx/9;
        int c=cell1Didx%9;
        // at empty cell :: we had 9 options (1-9)
        
        for(int num=1;num<=9;num++){
            int mask=1<<num;
            
            if((row[r] & mask) == 0 && (col[c] & mask) == 0 && (matrix[r/3][c/3] & mask) == 0){
                
                row[r] ^=(1<<num);
                col[c] ^= (1<<num);
                matrix[r/3][c/3] ^=(1<<num);
                
                board[r][c] = (char)(num+'0');
                // agr sukodu solve ho gya tab ni krenge unmark :: number sahi jagah place kiya tha
                if(sudokuSolver_bits(board,emptyCells,idx+1)) {
                    return true;
                }
                board[r][c] = '.';
                
                row[r] ^=(1<<num);
                col[c] ^= (1<<num);
                matrix[r/3][c/3] ^=(1<<num);
                
            }
        }
        return false;
    }

    public void solveSudoku_bits(char[][] board) {
        int n=board.length, m=board[0].length;
        ArrayList<Integer> emptyCells=new ArrayList<>();
        // fill ArrayList with all cells that are empty
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(board[i][j]=='.'){
                    emptyCells.add(i*9+j);
                } else{
                    int mask= 1<<(board[i][j]-'0');
                    
                    row[i] ^=mask; // row |=mask
                    col[j] ^=mask;
                    matrix[i/3][j/3] ^=mask;
                }
            }
        }
        // make call only from empty cell
       boolean ans=sudokuSolver_bits(board,emptyCells,0);
    }
   
   
    // valid sudoko : leetcode 36
    public boolean isValidSudoku(char[][] board) {
        int n=board.length, m=board[0].length;
        // fill ArrayList with all cells that are empty
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(board[i][j] !='.'){
                    int mask= 1<<(board[i][j]-'0');
                    if((row[i] & mask) != 0 || (col[j] & mask) != 0 || (matrix[i/3][j/3] & mask) != 0){
                        return false;
                    }
                    
                    row[i] ^=mask; 
                    col[j] ^=mask;
                    matrix[i/3][j/3] ^=mask;
                }
            }
        }
        return true;
    }

// ======================================================================================
   
   // Word Break :: (used DP for submit in leetcode)
   public boolean wordBreakPro(String s, HashSet<String> wordDict,int maxLen, String asf) {
    if(s.length()==0){
        System.out.println(asf);
        return true;
    }

    for(int len=1;len<=s.length();len++){
        String smallstr=s.substring(0,len);
        if(smallstr.length()>maxLen){
            break;
        }
        if(wordDict.contains(smallstr)){
            if(wordBreakPro(s.substring(len),wordDict,maxLen,asf+smallstr+" ")){
                return true;
            }
        }
    }
    return false;
}
   public boolean wordBreak(String s, List<String> wordDict) {
   HashSet<String> set=new HashSet<>(); 
    int maxLen=0;
   for(int i=0;i<wordDict.size();i++){
       set.add(wordDict.get(i));
       maxLen=Math.max(maxLen,wordDict.get(i).length());
   }
    return wordBreakPro(s,set, maxLen,"");
}


//==================================================================================
  
//  friends Pairing  :: for String
public static int friendsPairing(int n, String ans, boolean[] used) {
    int idx = 0; // first Unsed Friend
    while (idx <= n) {
        if (!used[idx])
            break;
        idx++;
    }

    if (idx > n) {
        System.out.println(ans);
        return 1;
    }

    int count = 0;
    used[idx] = true;
    count += friendsPairing(n, ans + "(" + idx + ") ", used); // single

    for (int i = idx + 1; i <= n; i++) {
        if (!used[i]) {
            String friend = "(" + idx + "" + i + ") ";
            used[i] = true;
            count += friendsPairing(n, ans + friend, used); // pairing
            used[i] = false;
        }
    }

    used[idx] = false;

    return count;
}

 // friends pairing for numbers
 public static int friendsPairing_num(int n){
    if(n==0){
        return 1;
    }
    int single=friendsPairing_num(n-1);
    int pair=friendsPairing_num(n-2);
    
    return single+(n-1)*pair;
 }

 //===============================================================================


    public static void main(String [] args){
       int n=friendsPairing(4, "",new boolean[4]);
    }
}
