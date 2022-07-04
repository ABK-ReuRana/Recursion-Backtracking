import java.util.ArrayList;

public class l002Maze {
    //maze path with single jump {h,v,d}

    // top to bottom approch
    public static ArrayList<String> mazePath_HVD(int sr, int sc, int er, int ec){
        if(sr==er && sc==ec){
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> myAns=new ArrayList<>();
        if(sc+1<=ec){
            ArrayList<String> vertical=mazePath_HVD(sr, sc+1, er, ec);
            for(String s : vertical){
                myAns.add(s+"h");
            }
        }
        if(sr+1<=er){
            ArrayList<String> horizontal=mazePath_HVD(sr+1, sc, er, ec);
            for(String s : horizontal){
                myAns.add(s+"v");
            }
        }
        if(sc+1<=ec && sr+1<=er){
            ArrayList<String> diagonal=mazePath_HVD(sr+1, sc+1, er, ec);
            for(String s : diagonal){
                myAns.add(s+"d");
            }
        }
        return myAns;
    }

    //bottom to top Approch
    public static int  mazePath_HVD(int sr, int sc, int er, int ec, ArrayList<String>ans,String asf){
        if(sr==er || sc==ec){
            ans.add(asf);
            return 1;
        }
        int count=0;
        if(sc+1<=ec){
            count+=mazePath_HVD(sr, sc+1, er, ec,ans, asf+"h");
        }
        if(sr+1<=er){
            count+=mazePath_HVD(sr+1, sc, er, ec,ans, asf+"v");
        }
        if(sr+1<=er && sc+1<=ec){
            count+=mazePath_HVD(sr+1, sc+1, er, ec,ans,asf+"d");
        } 
        return count;
    }

    //maze path with multiple jump {h,v,d}
    public static ArrayList<String> mazePath_HVD_multi(int sr, int sc, int er, int ec){
        if(sr==er && sc==ec){
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> myAns=new ArrayList<>();
        for(int j=1;j+sc<=ec;j++){
            ArrayList<String> vertical=mazePath_HVD_multi(sr, sc+j, er, ec);
            for(String s : vertical){
                myAns.add(s+"h");
            }
         }
        for(int j=1;j+sr<=er;j++){
            ArrayList<String> horizontal=mazePath_HVD_multi(sr+j, sc, er, ec);
            for(String s : horizontal){
                 myAns.add(s+"v");
            }
        }
        for(int j=1;j+sc<=ec && sr+j<=er ; j++){
            ArrayList<String> diagonal=mazePath_HVD_multi(sr+j, sc+j, er, ec);
            for(String s : diagonal){
                 myAns.add(s+"d");
            }
        }
        return myAns;
    }
    public static void mazePath_HVD_multi(int sr, int sc, int er, int ec, ArrayList<String>ans,String asf){
            if(sr==er || sc==ec){
               ans.add(asf);
               return;
            }
            for(int j=1;j+sc<=ec;j++){
                mazePath_HVD_multi(sr, sc+j, er, ec,ans, asf+"h"+j);
            }
            for(int j=1;j+sr<=er;j++){
                mazePath_HVD_multi(sr+j, sc, er, ec,ans,asf+"v"+j);
            }
            for(int j=1;j+sc<=ec && sr+j<=er ; j++){
                mazePath_HVD_multi(sr+1, sc+1, er, ec,ans,asf+"d"+j);
            } 
    }

    //==================================================================================
    // Make Generic To multiple calls
    public static int  mazePath_HVD_Generic(int sr, int sc, int er, int ec, ArrayList<String>ans,String asf, int[][]dir, String []dirN){
        if(sr==er || sc==ec){
            ans.add(asf);
            return 1;
        }
        int count=0;
        for(int d=0;d<dir.length;d++){
            int r=sr+dir[d][0];
            int c=sr+dir[d][1];
            if(r>=0 && c>=0 && r<=er && c<=ec){
                count+=mazePath_HVD_Generic(r,c,er,ec,ans,asf+dirN[d],dir,dirN);
            }
        }
        return count;
    }

    //flood fill (more the >= 4 calls)
    public static int floodFill(int sr, int sc, int[][]mat, boolean[][]vis,String[] dirN, int[][]dir,ArrayList<String> ans, String asf){
        int n=mat.length, m=mat[0].length;
        if(sr==n-1 && sc==m-1){
            ans.add(asf);
            return 1;
        }

        // mark
        vis[sr][sc]=true;
        // call for unvisited nbr's
        int count=0;
        for(int d=0;d<dir.length;d++){
            int r=sr+dir[d][0];
            int c=sr+dir[d][1];
            if(r>=0 && c>=0 && r<=n-1 && c<=m-1 && !vis[r][c]){
                count+=floodFill(r,c,mat,vis,dirN,dir, ans, asf+dirN[d]);
            }
        }
        // unmark
        vis[sr][sc]=false;
        return count;
    }

    public static int floodFill_multi(int sr, int sc, int[][]mat, boolean[][]vis,String[] dirN, int[][]dir,ArrayList<String> ans, String asf){
        int n=mat.length, m=mat[0].length;
        if(sr==n-1 && sc==m-1){
            ans.add(asf);
            return 1;
        }

        // mark
        vis[sr][sc]=true;
        // call for unvisited nbr's
        int count=0;
        for(int rad=1;rad<Math.max(n,m);rad++){
            for(int d=0;d<dir.length;d++){
                int r=sr+rad*dir[d][0];
                int c=sr+rad*dir[d][1];
                if(r>=0 && c>=0 && r<=n-1 && c<=m-1){
                    if(!vis[r][c])
                        count+=floodFill_multi(r,c,mat,vis,dirN,dir, ans, asf+dirN[d]);
                } else{
                    break;
                }
            }
        }
        // unmark
        vis[sr][sc]=false;
        return count;
    }

    // Similer questions --> 
    // https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1 **
    // https://practice.geeksforgeeks.org/problems/special-matrix4201/1 **
    // https://www.geeksforgeeks.org/rat-in-a-maze-with-multiple-steps-jump-allowed/?ref=rp  **
     
    
    // find all path and it's count
    public static int floodFill(int sr, int sc, int[][] vis, String psf, ArrayList<String> res, int[][] dir,
            String[] dirS) {
        int n = vis.length, m = vis[0].length;
        if (sr == n - 1 && sc == m - 1) {
            res.add(psf);
            return 1;
        }
        vis[sr][sc] = 0; // block
        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && vis[r][c] == 1) {
                count += floodFill(r, c, vis, psf + dirS[d], res, dir, dirS);
            }
        }
        vis[sr][sc] = 1; // unblock
        return count;
    }
    public static ArrayList<String> findPath(int[][] m, int n) {
        int[][] dir = { { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 } };
        String[] dirS = { "D", "L", "R", "U" };

        ArrayList<String> res = new ArrayList<String>();
        if (m[0][0] == 0 || m[n - 1][n - 1] == 0)
            return res;

        int count = floodFill(0, 0, m, "", res, dir, dirS);
        return res;
    }

    public static class pair {
        String psf = "";
        int len = 0;

        pair(String psf, int len) {
            this.len = len;
            this.psf = psf;
        }
    }

    public static pair longestPath(int sr, int sc, boolean[][] vis, int[][] dir, String[] dirS) {
        int n = vis.length, m = vis[0].length;
        if (sr == n - 1 && sc == m - 1) {
            return new pair("", 0);
        }

        vis[sr][sc] = true; // blocked
        // must intialize with -1 {mark h ki ans nhi mila}
        pair myAns = new pair("", -1); 
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m) {
                if (!vis[r][c]) {
                    pair recAns = longestPath(r, c, vis, dir, dirS);
                    if (recAns.len != -1 && recAns.len + 1 > myAns.len) {
                        myAns.len = recAns.len + 1;
                        myAns.psf = dirS[d] + recAns.psf;
                    }
                }
            }
        }

        vis[sr][sc] = false; // unblocked
        return myAns;
    }

    public static pair shortestPath(int sr, int sc, boolean[][] vis, int[][] dir, String[] dirS) {
        int n = vis.length, m = vis[0].length;
        if (sr == n - 1 && sc == m - 1) {
            return new pair("", 0);
        }

        vis[sr][sc] = true; // blocked
        pair myAns = new pair("", (int)1e9);
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m) {
                if (!vis[r][c]) {
                    pair recAns = shortestPath(r, c, vis, dir, dirS);
                    if (recAns.len != (int)1e9 && recAns.len + 1 < myAns.len) {
                        myAns.len = recAns.len + 1;
                        myAns.psf = dirS[d] + recAns.psf;
                    }
                }
            }
        }

        vis[sr][sc] = false; // unblocked
        return myAns;
    }

    public static void longestShortestPath() {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        String[] dirS = { "D", "R", "U", "L" };

        boolean[][] vis = new boolean[3][3];
        // vis[1][1] = vis[1][2] = vis[2][1] = true;

        pair ans = longestPath(0, 0, vis, dir, dirS);
        System.out.println(ans.psf + " @ " + ans.len);
    }

    public static void mazePath() {
        int sr = 0, sc = 0, er = 2, ec = 2;
        ArrayList<String> ans = new ArrayList<>();
        int[][] dir = { { 1, 0 }, { 0, 1 }, { 1, 1 }, { -1, 1 }, };
        String[] dirS = { "V", "H", "D", "E" };
        // System.out.println(mazePath_HVD_multi(sr, sc, er, ec));
        // System.out.println(mazePath_HVD_Generic(sr, sc, er, ec, ans, "", dir, dirS));

        System.out.println(ans);
    }

    public static void floodFill() {
        int sr = 0, sc = 0, n = 3, m = 3;
        boolean[][] vis = new boolean[n][m];
        // int[][] dir = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1
        // }, { 0, -1 }, { -1, -1 } };
        // String[] dirS = { "U", "E", "L", "S", "D", "N", "R", "W" };

        int[][] dir = { { 1, 0 }, { 0, 1 }, { 1, 1 } };
        String[] dirS = { "V", "H", "D" };
        ArrayList<String> ans = new ArrayList<>();
        // System.out.println(floodFill_multi(sr, sc, vis, dir, dirS, ans, ""));
        System.out.println(ans);
    }
    public static void main(String[] args){
        mazePath();
    }
}
