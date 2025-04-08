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

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int initialX = panelWidth / 2;
        int initialY = 30;
        int offset = panelWidth / 4;

        if (tree.root != null) {
            drawTree(g, tree.root, initialX, initialY, offset);
        }
    }

    private void drawTree(Graphics g, Node node, int x, int y, int xOffset) {
        if (node == null) return;

        // Choose color based on node type
        g.setColor(node.isNYT() ? Color.BLUE : node.isLeaf() ? Color.GREEN : Color.GRAY);
        // Draw the node
        g.fillOval(x - NODE_SIZE / 2, y - NODE_SIZE / 2, NODE_SIZE, NODE_SIZE);
        g.setColor(Color.BLACK);
        g.drawOval(x - NODE_SIZE / 2, y - NODE_SIZE / 2, NODE_SIZE, NODE_SIZE);

        // Draw node text (value/weight/index), centered
        g.setColor(Color.WHITE);
        String label = node.isLeaf() ? String.valueOf((char) node.getValue()) :
                node.isNYT() ? "NYT" : String.valueOf(node.getWeight());
        FontMetrics metrics = g.getFontMetrics();
        int labelWidth = metrics.stringWidth(label);
        int labelHeight = metrics.getHeight();
        g.drawString(label, x - labelWidth / 2, y + labelHeight / 4);

        g.setColor(Color.BLACK);
        g.drawString("idx:" + node.getIndex(), x - 15, y + 20);

        // Draw edges and recursively draw children
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

        // Input simulation
        String input = "ABCCCAAAA";
        Timer timer = new Timer(1000, null); // Update every 1 second
        final int[] index = {0};

        timer.addActionListener(e -> {
            if (index[0] < input.length()) {
                tree.insertInto((int) input.charAt(index[0]));
                visualizer.updateTree(tree);
                index[0]++;
            } else {
                ((Timer) e.getSource()).stop(); // Stop timer when done
            }
        });

        timer.start();
    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        visualize(tree);
    }

}