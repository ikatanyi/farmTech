/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package custom_jPanels;

/**
 *
 * @author KENT
 */
/**
 * Copyright (c) 2012,kent All rights reserved.
 * 
 * Permission to use, copy, modify, and distribute SwingJD software is freely
 * granted, provided that this notice is preserved.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.jd.swing.util.Colors;

/**
 * 
 * @author Dhilshuk Reddy
 * 
 */
public class TopPanel extends JPanel {
	private static final int HEIGHT = 30;
	private static final int WIDTH = 200;
	private int theme;
	private int titleStartPoint;
	private int titleEndPoint = 19;
	private int startPoint;
	private int endPoint;
	private boolean isCollapased = false;
	private String title;
	private String themeType;
	private Font font;
	private JPanel contentPanel;
	private BufferedImage open, closed, panelImage;
	private URL openIconURL;
	private URL closedIconURL;
	private ImageIcon icon;
	private GradientPaint gradientPaint;
	private Paint bgColor;
	private Color textColor = Color.WHITE;

	/**
	 * Constructor with title as parameter.
	 * 
	 * @param title
	 *            title of the TopPanel.
	 */
	public TopPanel(String title) {
		super();
		this.title = title;
		init();
	}

	/**
	 * Constructor which sets the title,icon,theme and container of the Heading
	 * Panel.
	 * 
	 * @param title
	 *            tile of the top panel
	 * @param icon
	 *            icon on the heading panel.
	 * 
	 * @param container
	 *            container which is collapsed and opened on click.
	 * 
	 * @param theme
	 *            theme of top panel
	 */
	public TopPanel(String title, ImageIcon icon, JPanel container, int theme) {
		this.contentPanel = container;
		this.title = title;
		this.theme = theme;
		this.icon = icon;
		init();
	}

	/**
	 * Shows and Hides the contentPanel on toggles selection.
	 */
	public void toggleSelection() {
		isCollapased = !isCollapased;
		if (contentPanel.isShowing())
			contentPanel.setVisible(false);
		else
			contentPanel.setVisible(true);
		validate();
		this.repaint();
	}

	/**
	 * Initializes.
	 */
	private void init() {
		List colors = null;
		int iconHeight = 0;
		int newHeight = 0;
		font = new Font("Thoma", Font.BOLD, 12);
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				toggleSelection();
			}

		});
		if (icon != null) {
			iconHeight = icon.getIconHeight();
			titleStartPoint = icon.getIconWidth();
		} else {
			titleStartPoint = 10;
		}
		if (iconHeight > 24) {
			newHeight = iconHeight / 2 + HEIGHT;
			setPreferredSize(new Dimension(WIDTH, newHeight));
			titleEndPoint = ((iconHeight + HEIGHT) + 8) / 2;
			startPoint = iconHeight / 2;
			endPoint = startPoint + 16;
		} else {
			setPreferredSize(new Dimension(WIDTH, HEIGHT));
		}
		themeType = getThemeType();
		if (themeType.equalsIgnoreCase("GLOSSY")) {
			colors = Colors.getInStance().getGlossyColor(theme, startPoint,
					endPoint);
			bgColor = (Paint) colors.get(1);
			textColor = (Color) colors.get(2);
		} else if (themeType.equalsIgnoreCase("STANDARD")) {
			colors = Colors.getInStance().getStandardColor(theme, startPoint,
					endPoint);
			textColor = (Color) colors.get(1);
		} else {
			colors = Colors.getInStance().getGradientColor(theme, startPoint,
					endPoint);
			textColor = (Color) colors.get(1);

		}
		gradientPaint = (GradientPaint) colors.get(0);
		openIconURL   = this.getClass().getResource("/resources/images/download-upload-iconsM24s.png");
		closedIconURL = this.getClass().getResource("/resources/images/download-upload-iconsMup24s.png");
		
	}

	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		createPanelImage();
		g2d.drawImage(panelImage, null, 0, 0);
	}

	/**
	 * Creates the Panel Image.
	 */
	private void createPanelImage() {
		panelImage = new BufferedImage(getWidth(), getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = panelImage.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(java.awt.RenderingHints.KEY_TEXT_ANTIALIASING,
				java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		drawShape(g2d);
	}

	/**
	 * Draws the shape.
	 * 
	 * @param g2d
	 *            graphics 2D Object.
	 */
	private void drawShape(Graphics2D g2d) {
		int width = getWidth();
		int pnlHght = 30;
		if (themeType.equals("GLOSSY")) {
			contentPanel.setBorder(new LineBorder(new Color(0, 0, 0, 50)));
			g2d.setPaint(bgColor);
			g2d.fillRect(0, startPoint, width, pnlHght);
			g2d.setPaint(gradientPaint);
			g2d.fillRect(2, startPoint + 2, width - 4, pnlHght / 2);
			g2d.setColor(new Color(0, 0, 0, 150));
			g2d.drawRect(0, startPoint, width - 1, pnlHght - 1);
			g2d.setColor(new Color(255, 255, 255, 100));
			g2d.drawRect(1, startPoint + 1, width - 3, pnlHght - 3);
		} else {
			g2d.setPaint(gradientPaint);
			g2d.fillRect(0, startPoint, width, pnlHght);
			g2d.setPaint(new Color(50, 50, 50, 150));
			g2d.drawRect(0, startPoint, width - 1, pnlHght - 1);
			g2d.setPaint(new Color(255, 255, 255, 50));
			g2d.drawRect(1, startPoint + 1, width - 3, pnlHght - 3);
		}
		//Draw the up or down arrow based on toggle selection
		if (isCollapased)
			g2d.drawImage(new ImageIcon(closedIconURL).getImage(), width - 25,
					startPoint + 3, null);
		else
			g2d.drawImage(new ImageIcon(openIconURL).getImage(), width - 25,
					startPoint + 3, null);

		if (icon != null)
		g2d.drawImage(icon.getImage(), 0, 0, null);
		g2d.setFont(font);
		g2d.setColor(textColor);
		g2d.drawString(title, titleStartPoint, titleEndPoint);
	}

	/**
	 * Returns the ThemeType.
	 * 
	 * @return string value
	 */
	public String getThemeType() {
		if (theme > 200) {
			themeType = "STANDARD";
		} else if (theme > 100) {
			themeType = "GRADIENT";
		} else {
			themeType = "GLOSSY";
		}

		return themeType;
	}

}

