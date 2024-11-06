
    import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

    public class SortingVisualizer extends JPanel {

        private static final int SIZE = 200;  // Number of elements
        private static final int GAP = 4;     // Gap between lines in the graph
        private static final int DELAY = 100; // Delay for animation
        private final List<Integer> numbers = new ArrayList<>();

        public SortingVisualizer() {
            // Initialize the array with values and shuffle
            for (int i = 1; i <= SIZE; i++) {
                numbers.add(i);
            }
            long seed = System.currentTimeMillis();
            Collections.shuffle(numbers, new Random(seed));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            for (int i = 0; i < SIZE; i++) {
                int height = numbers.get(i);
                int x = i * GAP;
                int y = getHeight() - height;
                g.drawLine(x, getHeight(), x, y);
            }
        }

        private void swapColors(Graphics g, int i, int j, int x, int y) throws InterruptedException {
            g.setColor(Color.GREEN);
            g.drawLine(j * GAP, getHeight(), j * GAP, getHeight() - y);
            Thread.sleep(DELAY);

            g.setColor(getBackground());
            g.drawLine(j * GAP, getHeight(), j * GAP, getHeight() - y);

            g.setColor(Color.WHITE);
            g.drawLine(j * GAP, getHeight(), j * GAP, getHeight() - x);

            g.setColor(getBackground());
            g.drawLine(i * GAP, getHeight(), i * GAP, getHeight() - x);

            g.setColor(Color.GREEN);
            g.drawLine(i * GAP, getHeight(), i * GAP, getHeight() - y);
            Thread.sleep(DELAY);

            g.setColor(Color.WHITE);
            g.drawLine(i * GAP, getHeight(), i * GAP, getHeight() - y);
        }

        public void selectionSort() throws InterruptedException {
            Graphics g = getGraphics();
            for (int i = 0; i < SIZE - 1; i++) {
                int minIdx = i;
                for (int j = i + 1; j < SIZE; j++) {
                    if (numbers.get(j) < numbers.get(minIdx)) {
                        minIdx = j;
                    }
                }
                int temp = numbers.get(minIdx);
                numbers.set(minIdx, numbers.get(i));
                numbers.set(i, temp);

                swapColors(g, i, minIdx, numbers.get(minIdx), numbers.get(i));
                repaint();
            }
        }

        public static void main(String[] args) {
            JFrame frame = new JFrame("Selection Sort Visualizer");
          SortingVisualizer panel = new SortingVisualizer();

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(SIZE * GAP + 1, SIZE + 100);
            frame.add(panel);
            frame.setVisible(true);

            try {
                panel.selectionSort();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


