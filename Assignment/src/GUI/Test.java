/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
//from w ww. ja v a  2  s  .com
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Test {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setUndecorated(true);
    frame.setBackground(new Color(0, 0, 0, 0));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(new ShadowPane());

    JPanel panel = new JPanel(new GridBagLayout());
    panel.add(new JLabel("Look ma, no hands"));

    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
  }
}

class ShadowPane extends JPanel {

  public ShadowPane() {
    setLayout(new BorderLayout());
    setOpaque(false);
    setBackground(Color.BLACK);
    setBorder(new EmptyBorder(0, 0, 10, 10));
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(200, 200);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f));
    g2d.fillRect(10, 10, getWidth(), getHeight());
    g2d.dispose();
  }
}
