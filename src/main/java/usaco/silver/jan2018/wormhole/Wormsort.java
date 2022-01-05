package usaco.silver.jan2018.wormhole;

import java.util.*;
import java.io.*;

public class Wormsort {

    public static int n;
    public static int numE;
    public static int[] perm;
    public static ArrayList<edge> eList;

    public static void main(String[] args) throws Exception {
        String path = "src/main/resources";
        BufferedReader stdin = new BufferedReader(new FileReader(path + "/wormsort.in"));
        StringTokenizer tok = new StringTokenizer(stdin.readLine());
        n = Integer.parseInt(tok.nextToken());
        numE = Integer.parseInt(tok.nextToken());
        perm = new int[n];

        // Read perm.
        tok = new StringTokenizer(stdin.readLine());
        for (int i=0; i<n; i++)
            perm[i] = Integer.parseInt(tok.nextToken()) - 1;

        // Read graph.
        eList = new ArrayList<edge>();
        for (int i=0; i<numE; i++) {
            tok = new StringTokenizer(stdin.readLine());
            int u = Integer.parseInt(tok.nextToken()) - 1;
            int v = Integer.parseInt(tok.nextToken()) - 1;
            int w = Integer.parseInt(tok.nextToken());
            eList.add(new edge(u, v, w));
        }

        // See how many cows are already ok.
        int numOk = 0;
        for (int i=0; i<n; i++)
            if (perm[i] == i)
                numOk++;

        // Check for no wormholes needed case.
        int res = numOk == n ? -1 : 0;

        // Regular case, do some work.
        if (res == 0) {

            // Sort it!
            eList.sort((e1,e2)->e2.w.compareTo(e1.w));

            // Binary search the number of edges we have to use from greatest weight to least.
            int low = 0, high = numE-1;
            while (low < high) {
                int mid = (low+high)/2;
                if (canDo(mid))
                    high = mid;
                else
                    low = mid+1;
            }

            // This is our answer.
            res = eList.get(low).w;
        }

        // Ta da!
        PrintWriter out = new PrintWriter(new FileWriter(path + "/wormsort.out"));
        out.println(res);
        out.close();
        stdin.close();
    }

    public static boolean canDo(int idx) {

        // Union each of the edges upto idx.
        djset dj = new djset(n);
        for (int i=0; i<=idx; i++)
            dj.union(eList.get(i).u, eList.get(i).v);

        // If anyone isn't in the same group as their home, this didn't work.
        for (int i=0; i<n; i++)
            if (dj.find(i) != dj.find(perm[i]))
                return false;

        // If we get here, we are good.
        return true;
    }

}

class edge {

    public int u;
    public int v;
    public Integer w;

    public edge(int myu, int myv, int myw) {
        u = myu;
        v = myv;
        w = myw;
    }
}

// A Disjoint Set class
class djset {

    public int[] par;

    public djset(int n) {
        par = new int[n];
        for (int i=0; i<n; i++)
            par[i] = i;
    }

    public int find(int v) {
        if (par[v] == v) return v;
        return par[v] = find(par[v]);
    }

    public boolean union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);
        if (rootU == rootV) return false;
        par[rootV] = rootU;
        return true;
    }
}
