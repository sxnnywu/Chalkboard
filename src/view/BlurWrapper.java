package view;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class BlurWrapper extends JPanel {
    private final JPanel contentPanel;
    private BufferedImage blurImage;
    private boolean isBlurred = false;
    
    public BlurWrapper(JPanel contentPanel) {
        this.contentPanel = contentPanel;
        setLayout(null);
        contentPanel.setBounds(0, 0, 1920, 1080);
        add(contentPanel);
        setOpaque(true);
        
        // Remove the magenta background for production
        // setBackground(Color.MAGENTA);
    }
    
    public void blurContent() {
        // Wait for next paint cycle to ensure layout is complete
        SwingUtilities.invokeLater(() -> {
            int w = contentPanel.getWidth();
            int h = contentPanel.getHeight();
            
            System.out.println("Blur size: " + w + "x" + h);
            if (w == 0 || h == 0) {
                System.out.println("Content panel not laid out yet!");
                return;
            }
            
            // Capture panel as image
            BufferedImage screenshot = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = screenshot.createGraphics();
            
            // Enable antialiasing for better quality
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            contentPanel.paint(g2);
            g2.dispose();
            
            // Apply stronger blur kernel (5x5 instead of 3x3)
            float[] blurKernel = new float[25];
            for (int i = 0; i < 25; i++) {
                blurKernel[i] = 1.0f / 25.0f;
            }
            
            ConvolveOp op = new ConvolveOp(new Kernel(5, 5, blurKernel));
            blurImage = op.filter(screenshot, null);
            
            isBlurred = true;
            System.out.println("Blur image generated.");
            repaint();
        });
    }
    
    public void clearBlur() {
        isBlurred = false;
        blurImage = null;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (isBlurred && blurImage != null) {
            // Draw the blurred image
            g.drawImage(blurImage, 0, 0, null);
            System.out.println("Drawing blurred image.");
        }
    }
    
    @Override
    public void addNotify() {
        super.addNotify();
        // Automatically blur when component is added to container
        SwingUtilities.invokeLater(() -> {
            SwingUtilities.invokeLater(() -> { // Double invoke to ensure layout
                blurContent();
            });
        });
    }
}