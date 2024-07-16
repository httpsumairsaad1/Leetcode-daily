class Solution {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        TreeNode lca = lca(root, startValue, destValue);
        ArrayList<Character> startList = new ArrayList<>();
        ArrayList<Character> destList = new ArrayList<>();
        findPath(lca, startValue, startList);
        findPath(lca, destValue, destList);
        Collections.reverse(destList);
        return "U".repeat(startList.size()) + destList.stream().map(x -> "" + x).collect(Collectors.joining());
    }

    private String findPath(TreeNode node, int val, String path) {
        if (node == null) return null;
        if (node.val == val) return path;
        var leftCheck = findPath(node.left, val, path + "L");
        var rightCheck = findPath(node.right, val, path + "R");
        if (leftCheck != null) return leftCheck;
        return rightCheck;
    }

    private boolean findPath(TreeNode node, int val, List<Character> path) {
        if (node == null) return false;
        if (node.val == val) return true;
        if (findPath(node.left, val, path)) {
            path.add('L');
            return true;
        } else if (findPath(node.right, val, path)) {
            path.add('R');
            return true;
        }
        return false;
    }

    TreeNode lca(TreeNode root, int n1, int n2) {
        if (root == null) return null;
        if (root.val == n1 || root.val == n2) return root;
        var left = lca(root.left, n1, n2);
        var right = lca(root.right, n1, n2);
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }
}
