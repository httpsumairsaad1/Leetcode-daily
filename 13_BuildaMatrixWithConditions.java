class Solution {
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        int[] row = topsort(k, rowConditions);
        int[] col = topsort(k, colConditions);

        if (row == null || col == null) return new int[0][0];

        int[][] result = new int[k][k];
        int[] rowPos = new int[k];
        int[] colPos = new int[k];

        for (int i = 0; i < k; i++) {
            rowPos[row[i]] = i;
            colPos[col[i]] = i;
        }

        for (int i = 0; i < k; i++) {
            result[rowPos[i]][colPos[i]] = i + 1;
        }

        return result;
    }

    public static int[] topsort(int k, int[][] conditions) {
        List<Integer>[] adj = new ArrayList[k];
        for (int i = 0; i < k; i++) {
            adj[i] = new ArrayList<>();
        }

        int[] indegree = new int[k];
        for (int[] condition : conditions) {
            int u = condition[0] - 1;
            int v = condition[1] - 1;
            adj[u].add(v);
            indegree[v]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int[] topo = new int[k];
        int i = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            topo[i++] = node;
            for (int it : adj[node]) {
                indegree[it]--;
                if (indegree[it] == 0) {
                    q.add(it);
                }
            }
        }

        return i == k ? topo : null; 
    }
}
