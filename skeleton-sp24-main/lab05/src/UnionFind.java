import java.util.Arrays;

public class UnionFind {
    private int[] parent;
    private int[] size;

    /** Creates a UnionFind data structure holding N items. Initially, all
     items are in disjoint sets. */
    public UnionFind(int N) {
        parent = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            // Initialize parent and size.
            parent[i] = -1;
            size[i] = 1;
        }
    }

    /** Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // For any root node, the value stored in parent array is negative,
        // and its absolute value represent the size of the set.
        return size[find(v)];
    }

    /** Returns the parent of V. If V is the root of a tree, returns the
     negative size of the tree for which V is the root. */
    public int parent(int v) {
        if (parent[v] < 0) {
            return parent[v]; // If v is a root.
        } else { // v is not a root
            return parent[parent[v]]; // Climbs up the tree.
        }
    }

    /** Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /** Returns the root of the set V belongs to.
     * Path-compression is employed allowing for fast search-time.
     * If invalid items are passed into this function, throw an IllegalArgumentException. */
    public int find(int v) {
        if (v < 0 || v >= parent.length) {
            throw new IllegalArgumentException("Some comment to describe the reason for throwing.");
        }
        if (parent[v] >= 0) { // If v is not a root.
            parent[v] = find(parent[v]);
            return parent[v];
        } else return v;
    }

    /** Connects two items V1 and V2 together by connecting their respective
     sets. V1 and V2 can be any element, and a union-by-size heuristic is
     used. If the sizes of the sets are equal, tie break by connecting V1's
     root to V2's root. Union-ing an item with itself or items that are
     already connected should not change the structure. */
    public void union(int v1, int v2) {
        int root1 = find(v1);
        int root2 = find(v2);

        if (root1 != root2) {
            // Union by size
            if (size[root1] < size[root2]) {
                size[root2] += size[root1];
                parent[root1] = root2;
                parent[root2] -= size[root1];
            } else {
                size[root1] += size[root2];
                parent[root2] = root1;
                parent[root1] -= size[root2];
            }
        }
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(0, 2);
        uf.union(5, 6);
        uf.union(7, 8);
        uf.union(5, 9);
        uf.union(5, 7);
        uf.union(7, 2);

        System.out.println(Arrays.toString(uf.parent));
    }
}



