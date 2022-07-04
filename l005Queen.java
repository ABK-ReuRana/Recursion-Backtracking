public class l005Queen {
    
    // tnq : total number of queens, tnb : total no. of boxes



    // ==================Place n queen in 1D array (anywhere) =============================
    public static int queenCombination(int tnb, int tnq, int qno,int bno, String asf){
        if(qno==tnq){
            System.out.println(asf);
            return 1;
        }
        int count=0;
        for(int i=bno;i<tnb;i++){
            count+=queenCombination(tnb, tnq, qno+1, bno+1, asf+"b"+i+"q"+qno);
        }
        return count;
    }

    public static int queenPermutation(boolean[]boxes, int tnq, int bno, int qno, String ans){
    if (qno == tnq){
        System.out.println(ans);
        return 1;
    }
    int count = 0;
    for (int i = bno; i < boxes.length; i++){
        if (!boxes[i]){
            boxes[i] = true;
            count += queenPermutation(boxes, tnq, 0, qno + 1, ans + "b" + i + "q" +qno + " ");
            boxes[i] = false;
        }
    }

    return count;
}


 // ==================Place n queen in 2D array (anywhere) =============================
int queenCombination2D(int[][] board, int tnq, int bno, String ans)
{
    if (tnq == 0)
    {
        System.out.println(ans);
        return 1;
    }

    int count = 0, n = board.length, m = board[0].length;
    for (int i = bno; i < n * m; i++)
    {
        int r = i / m;
        int c = i % m;
        count += queenCombination2D(board, tnq - 1, i + 1, ans + "(" + r + "," + c + ") ");
    }

    return count;
}

int queenPermutation2D(Boolean[][] boardboard, int tnq, int bno, String ans)
{
    if (tnq == 0)
    {
        System.out.println(ans);
        return 1;
    }

    int count = 0, n =boardboard.length, m = boardboard[0].length;
    for (int i = bno; i < n * m; i++)
    {
        int r = i / m;
        int c = i % m;
        if (!boardboard[r][c])
        {
            boardboard[r][c] = true;
            count += queenPermutation2D(boardboard, tnq - 1, 0, ans + "(" + r + "," + c + ") ");
            boardboard[r][c] = false;
        }
    }

    return count;
}



//========================Place queen in 2D array(only valid place)========================
public static boolean isSafeToPlaceQueen(boolean [][] board, int r, int c){
        int n=board.length;
        // int dir[][]={{0,-1},{-1,-1},{-1,0},{-1,1}}; // for combination
        int dir[][]={{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}}; // for permuation
        for(int d=0;d<dir.length;d++){
            for(int rad=1;rad<n;rad++){
                int nr=r+rad*dir[d][0];
                int nc=c+rad*dir[d][1];
                if(nr>=0 && nc>=0 && nr<n && nc<n){
                    if(board[nr][nc]){
                        return false;
                    }
                } else {
                    break;
                }
            }
        }
        return true;
    }
    
// way 1 :: nQueen (using for loop)
public static int nqueen_o1_combi(boolean[][] board, int tnq, int bno, String ans){
    if (tnq == 0)
    {
        System.out.println(ans);
        return 1;
    }

    int count = 0, n = board.length, m = board[0].length;
    for (int i = bno; i < n * m; i++)
    {
        int r = i / m;
        int c = i % m;
        if(isSafeToPlaceQueen(board,r,c)){
            board[r][c]=true;
            count += nqueen_o1_combi(board, tnq - 1, i + 1, ans + "(" + r + "," + c + ") ");
            board[r][c]=false;
        }
    }
    return count;
}

public static int nqueen_o1_permu(boolean[][] board, int tnq, int bno, String ans){
    if (tnq == 0)
    {
        System.out.println(ans);
        return 1;
    }

    int count = 0, n = board.length, m = board[0].length;
    for (int i = bno; i < n * m; i++)
    {
        int r = i / m;
        int c = i % m;
        if(!board[r][c] && isSafeToPlaceQueen(board,r,c)){
            board[r][c]=true;
            count += nqueen_o1_permu(board, tnq - 1,0, ans + "(" + r + "," + c + ") ");
            board[r][c]=false;
        }
    }
    return count;
}

// way 2 :: using subsequence ===========================================
public static int nqueen_o1_combi_SubseqWay(boolean[][] board, int tnq, int bno, String ans){
    int n = board.length, m = board[0].length;
    if (tnq == 0 || bno==n*m)
    {
        if(tnq==0){
            System.out.println(ans);
        }
        return tnq==0 ? 1 :0;
    }
    int count = 0;
    int r = bno/ m;
    int c = bno % m;
    if(isSafeToPlaceQueen(board,r,c)){
        board[r][c]=true;
        count += nqueen_o1_combi_SubseqWay(board, tnq - 1, bno + 1, ans + "(" + r + "," + c + ") ");
        board[r][c]=false;
    }
    count+=nqueen_o1_combi_SubseqWay(board,tnq,bno+1,ans);
    return count;
}

public static int nqueen_o1_permu_SubseqWay(boolean[][] board, int tnq, int bno, String ans){
     int n = board.length, m = board[0].length;
     if (tnq == 0 || bno==n*m)
     {
         if(tnq==0){
             System.out.println(ans);
         }
         return tnq==0 ? 1 :0;
     }
    int count = 0;
    int r = bno / m;
    int c = bno % m;   
    if(!board[r][c] && isSafeToPlaceQueen(board,r,c)){
        board[r][c]=true;
        count += nqueen_o1_permu_SubseqWay(board, tnq - 1,0, ans + "(" + r + "," + c + ") ");
        board[r][c]=false;
    }
    count += nqueen_o1_permu_SubseqWay(board, tnq,bno+1, ans); 
    return count;
}


//=========================================================================================

// way 3 :: optimize isSafeToPlaceAQueen 
      static boolean[]row;
      static boolean[]col;
      static boolean[]diag;
      static boolean[]aDiag;

      public static int nqueen_o2_combi(int n,int m, int tnq, int bno, String ans){
        if (tnq == 0)
        {
            System.out.println(ans);
            return 1;
        }
    
        int count = 0;
        for (int i = bno; i < n * m; i++)
        {
            int r = i / m;
            int c = i % m;
            if(!row[r] && !col[c] && !diag[r-c+m-1] && !aDiag[r+c]){
                row[r] =col[c] =diag[r-c+m-1] =aDiag[r+c]=true;
                count += nqueen_o2_combi(n,m, tnq - 1, i + 1, ans + "(" + r + "," + c + ") ");
                row[r] =col[c] =diag[r-c+m-1] =aDiag[r+c]=false;
            }
        }
        return count;
    }
    
     public static int nqueen_o2_permu(int n, int m, int tnq, int bno, String ans){
        if (tnq == 0)
        {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = bno; i < n * m; i++)
        {
            int r = i / m;
            int c = i % m;
            if(!row[r] && !col[c] && !diag[r-c+m-1] && !aDiag[r+c]){
                row[r] =col[c] =diag[r-c+m-1] =aDiag[r+c]=true;
                count += nqueen_o2_permu(n,m, tnq - 1,0, ans + "(" + r + "," + c + ") ");
                row[r] =col[c] =diag[r-c+m-1] =aDiag[r+c]=false;
            }
        }
        return count;
    }
    

    // way 4 :: simple optimized further :: each queen has No of col options only
    public static int nqueen_o3_combi(int n,int m, int tnq, int r, String ans){
        if (tnq == 0)
        {
            System.out.println(ans);
            return 1;
        }
    
        int count = 0;
        for (int c =0 ; c < m; c++)
        {
            if(!row[r] && !col[c] && !diag[r-c+m-1] && !aDiag[r+c]){
                row[r] =col[c] =diag[r-c+m-1] =aDiag[r+c]=true;
                count += nqueen_o2_combi(n,m, tnq - 1, r + 1, ans + "(" + r + "," + c + ") ");
                row[r] =col[c] =diag[r-c+m-1] =aDiag[r+c]=false;
            }
        }
        return count;
    }
    
    // find permutation in this approch
    public static int nqueen_o3_permut(int n,int m, int tnq, int r, String ans){
        if (tnq == 0)
        {
            System.out.println(ans);
            return 1;
        }
    
        int count = 0;
        for (int c =0 ; c < m; c++)
        {
            if(!row[r] && !col[c] && !diag[r-c+m-1] && !aDiag[r+c]){
                row[r] =col[c] =diag[r-c+m-1] =aDiag[r+c]=true;
                count += nqueen_o3_permut(n,m, tnq - 1, 0, ans + "(" + r + "," + c + ") ");
                row[r] =col[c] =diag[r-c+m-1] =aDiag[r+c]=false;
            }
        }
        count += nqueen_o3_permut(n,m, tnq - 1, r+1, ans);
        return count;
    }

    public static void queenProblems(){
        boolean [][] board=new boolean[4][4];
        // System.out.println(nqueen_o1_combi(board,4,0,""));
        // System.out.println(nqueen_o1_permu(board,4,0,""));
        // System.out.println(nqueen_o1_combi_SubseqWay(board,4,0,""));
        // System.out.println(nqueen_o1_permu_SubseqWay(board,4,0,""));
 
        int n=4, m=4;
        row=new boolean[n];
        col=new boolean[m];
        diag=new boolean[n+m-1];
        aDiag=new boolean[n+m-1];
        // System.out.println(nqueen_o2_combi(n,m,4,0,""));
        // System.out.println(nqueen_o2_permu(n,m,4,0,""));



        System.out.println(nqueen_o3_combi(n,m,4,0,""));
        

    }
    public static void main(String[] args){
        queenProblems();
    }
}
