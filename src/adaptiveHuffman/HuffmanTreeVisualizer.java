package adaptiveHuffman;

import adaptiveHuffman.tree.Node;
import adaptiveHuffman.tree.Tree;

import javax.swing.*;
import java.awt.*;

public class HuffmanTreeVisualizer extends JPanel {
    private Tree tree;
    private static final int NODE_SIZE = 40;
    private static final int LEVEL_HEIGHT = 80;
    private static final int HORIZONTAL_GAP = 50;

    public HuffmanTreeVisualizer(Tree tree) {
        this.tree = tree;
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (tree.root != null) {
            drawTree(g, tree.root, getWidth() / 2, 30, getWidth() / 4);
        }
    }

    private void drawTree(Graphics g, Node node, int x, int y, int xOffset) {
        if (node == null) return;

        // Draw node
        g.setColor(node.isNYT() ? Color.BLUE : node.isLeaf() ? Color.GREEN : Color.GRAY);
        g.fillOval(x - NODE_SIZE / 2, y - NODE_SIZE / 2, NODE_SIZE, NODE_SIZE);
        g.setColor(Color.WHITE);
        String label = node.isLeaf() ? String.valueOf((char) node.getValue()) :
                node.isNYT() ? "NYT" : node.getWeight() + "";
        g.drawString(label, x - 5, y + 5);
        g.setColor(Color.BLACK);
        g.drawString("idx:" + node.getIndex(), x - 15, y + 20);

        // Draw edges and children
        if (node.left != null) {
            int leftX = x - xOffset;
            int leftY = y + LEVEL_HEIGHT;
            g.setColor(Color.BLACK);
            g.drawLine(x, y, leftX, leftY);
            drawTree(g, node.left, leftX, leftY, xOffset / 2);
        }
        if (node.right != null) {
            int rightX = x + xOffset;
            int rightY = y + LEVEL_HEIGHT;
            g.setColor(Color.BLACK);
            g.drawLine(x, y, rightX, rightY);
            drawTree(g, node.right, rightX, rightY, xOffset / 2);
        }
    }

    public void updateTree(Tree newTree) {
        this.tree = newTree;
        repaint();
    }

    public static void visualize(Tree tree) {
        JFrame frame = new JFrame("Adaptive Huffman Tree Visualization");
        HuffmanTreeVisualizer visualizer = new HuffmanTreeVisualizer(tree);
        frame.add(visualizer);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Simulate encoding with animation
        String input = "hello world"; // Example: repeated characters
        for (char c : input.toCharArray()) {
            tree.insertInto((int) c);
            visualizer.updateTree(tree);
            try {
                Thread.sleep(1000); // 1-second delay for animation
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        visualize(tree);
    }
}