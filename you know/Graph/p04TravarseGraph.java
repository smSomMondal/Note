package Graph;

public class p04TravarseGraph {

    public static void main(String[] args) {
        String dfsRec = """
            /********** DFS RECRTION ********************/
            FUNCTION DFS_Recursive(graph G, vertex v):
                MARK v as visited
                VISIT(v)  // Process the vertex (e.g., print it)

                FOR EACH neighbor w of v:
                    IF w is not visited:
                        DFS_Recursive(G, w)
            """;

        System.out.println(dfsRec);

        String dfs = """
                /**************** DFS ********************/              
                FUNCTION DFS_Iterative(graph G, start_vertex v):
                CREATE Stack S
                PUSH v onto S
                MARK v as visited

                WHILE S is not empty:
                    current_node = POP S
                    // Optional: Process the node here if using post-order, 
                    // otherwise, the 'VISIT' happens when initially marked visited.

                    FOR EACH neighbor w of current_node:
                        IF w is not visited:
                            MARK w as visited
                            PUSH w onto S
                """;
        System.out.println(dfs);

        String bfs = """
                /**************** BFS ********************/
                FUNCTION BFS(graph G, start_vertex s):
                CREATE Queue Q
                ENQUEUE s onto Q
                MARK s as visited

                WHILE Q is not empty:
                    v = DEQUEUE Q

                    FOR EACH neighbor w of v in G:
                        IF w is not visited:
                            MARK w as visited
                            ENQUEUE w onto Q
                            // Optional: Store parent or distance information here
                """;

        System.out.println(bfs);
    }
    
    
}
