package TreesAndGraphs;
import java.util.*;


public class mostProfitable {
    static class Edge{
        int weight;
        int des;
        public Edge(int weight, int des) {
            this.weight = weight;
            this.des = des;
        }

        @Override
        public String toString() {
            return Integer.toString(des);
        }

    }
    public static void dijkstra(Map<Integer, List<Edge>> map, int source) {

        //Dijstra implementation: Map<Integer,List<int[]>> need a Map to track weights
        //Dijstra need a HashSet for visited nodes
        PriorityQueue<int[]> pq =  new PriorityQueue<>(Comparator.comparingInt(a->a[1]));
        pq.add(new int[]{source,0});

        Map<Integer,Integer> dist = new HashMap<>();
        dist.put(source,0);
        //  node   dist from source
        Map<Integer, Integer> parent = new HashMap<>();
        parent.put(source,null);
        Set<Integer> visited = new HashSet<>();

        while (!pq.isEmpty()) {
            int[] curNode = pq.poll();
            int node = curNode[0];
            int curDist = curNode[1];

            if(visited.contains(node)) {
                continue;
            }

            visited.add(node);

            for (Edge edge: map.getOrDefault(node, new ArrayList<>())) {
                int neighbor = edge.des;
                int newDist = curDist+edge.weight;

                if(newDist<dist.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    dist.put(neighbor,newDist);
                    parent.put(neighbor,node);
                    pq.add(new int[]{neighbor,newDist});
                }
            }
        }
        // for (Map.Entry<Integer, Integer> entry : dist.entrySet()) {
        //     System.out.println("Node " + entry.getKey() + " is at distance " + entry.getValue());
        // }
    }
   
    public static List<Integer> bfs(Map<Integer,List<Edge>> map, int source, int target) {
        Queue<Integer> q = new LinkedList<>();

        Map<Integer, Integer> parent = new HashMap<>();
        parent.put(source,null);
        Set<Integer> visited = new HashSet<>();
        List<Integer> path = new ArrayList<>();

        q.add(source);
        while (!q.isEmpty()) {
            int node = q.poll();

            if (visited.contains(node)) {
                continue;
            }
            visited.add(node);

            if (node==target) {
                break;
            }

            for (Edge edge: map.getOrDefault(node, new ArrayList<>())) {
                int neighbor = edge.des;
                if (!visited.contains(neighbor)) {
                    q.add(neighbor);
                    parent.put(neighbor, node);
                }

            }
        }

        for (Integer at = 0; at != null; at = parent.get(at) ) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    public static int dfs(List<Integer> bob_moves, int curNode, int sum, int[] amount, int parentNode, Map<Integer, List<Edge>> map, Set<Integer> visited) {
        // Base case: If Alice reaches a leaf node or all nodes are visited
        if (visited.contains(curNode)) {
            return sum;
        }
    
        // Mark the current node as visited
        visited.add(curNode);
    
        // If Bob is at the current node, Alice only takes half the amount
        if (!bob_moves.isEmpty() && bob_moves.get(0) == curNode) {
            sum += amount[curNode] / 2;
        } else {
            sum += amount[curNode];
        }
    
        // Reset the amount of the current node so it's not revisited
        amount[curNode] = 0;
        amount[bob_moves.get(0)] =0;
        // If the current node has no more children, return the sum
        if (map.getOrDefault(curNode, new ArrayList<>()).isEmpty()) {
            return sum;
        }
    
        // Recursively explore the children nodes
        for (Edge child : map.getOrDefault(curNode, new ArrayList<>())) {
            if (bob_moves.size() > 1) {
                // Recursively call DFS for the next step in Bob's path
                sum = dfs(bob_moves.subList(1, bob_moves.size()), child.des, sum, amount, curNode, map, visited);
            } else {
                // If Bob's path has finished, Alice can move freely
                sum = dfs(Arrays.asList(0), child.des, sum, amount, curNode, map, visited);
            }
        }
        
        return sum;
    }
    
    
    public static void main(String[] args) {
        int[][] edges = {{0,1},{1,2},{1,3},{3,4}};
        int bob = 3;
        int[] amount = {-2,4,2,-4,6};
        Map<Integer, List<Edge>> map = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        for (int[] edge: edges) {
            map.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(new Edge(1, edge[1]));
            map.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(new Edge(1, edge[0]));
        }
        dijkstra(map,bob);
        System.out.println(dfs(bfs(map, bob, 0),0,0,amount,-1,map,visited));
        System.out.println(bfs(map, bob, 0));
        // System.out.println(parent);
        // for (Map.Entry<Integer, List<Edge>> entry : map.entrySet()) {
        //     Integer node = entry.getKey();
        //     List<Edge> edgess = entry.getValue();
        //     System.out.println("Node " + node + " has edges: " + edgess);
        // }
        
        // bob is always going to the node 0; So although the algo doesn't know where node 0 is. But we can 
        // do a bfs to find node 0 and return the shortest path. 
        // For example the path of bob could be something like this {starting=bob, 1,2,3,0}
        // This is the hard requirement for this problem. And then at each iteration of Alice, we can pass in this bob path to do comparison
        // As bob and alice tranverse the graph, we keep a copy of the amount array for a given path and update the values inside
        // for the given bob[n] path there is only a certain paths that alice can take, we will append those to the total list -> giving us a list of list of int of all possible path with corresponding VALUES
        // we will find the sum max of this list or keep a sum already as we tranverse and return max. 

        // 1. Find the hard requirment for Bob
        // 2. Find All possible path for Alice, with Bob's path as constraint
        // 3. As we tranverse Alice Path we will keep track of the amount array, and sum of points for Alice
        // 4. When we stop at a path we return max points
        // 5 return max points for Alice= most profitable path

        //Reuseable part of this algo:
        // we need a bfs to tranverse the graph
        // Dikstra's Algo can help us find shortest path in a graph for Bob
        // Dikstra's Algo also have a weight associated, but the weight is dynamic, it updates real-time(Google Map traffic updates)
        // Adj -> tranversal
        // Amount list -> realtime updates of weights
        // need a Map of Dikstra's 

    } 
    
}
