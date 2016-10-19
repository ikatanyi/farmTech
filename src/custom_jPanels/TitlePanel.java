/**
 * Copyright (c) 2012, Dhilshuk Reddy All rights reserved.
 * 
 * Permission to use, copy, modify, and distribute SwingJD software is freely
 * granted, provided that this notice is preserved.
 */
package custom_jPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import sun.security.krb5.internal.PAEncTSEnc;

import com.jd.swing.util.PanelType;
import com.jd.swing.util.Theme;

/**
 * 
 * @author dhilshuk
 * 
 */
public class TitlePanel extends JPanel {

	private int theme;
	private String title;
	private String iconPath = null;
	private JComponent contentPanel;
	private int panelType = PanelType.PANEL_ROUNDED_RECTANGLUR;

	/**
	 * Constructor with title,theme and content panel.
	 * 
	 * @param title
	 *            title on the panel
	 * @param theme
	 *            theme of the panel
	 * @param contentPanel
	 *            component to be added
	 */
	public TitlePanel(String title, int theme, JComponent contentPanel) {
		this.title = title;
		this.theme = theme;
		this.contentPanel = contentPanel;
		init();
	}

	/**
	 * Constructor with title,theme,content panel and panel type.
	 * 
	 * @param title
	 *            title on the panel
	 * @param theme
	 *            theme of the panel
	 * @param contentPanel
	 *            component to be added
	 * @param panelType
	 *            panel type
	 */
	public TitlePanel(String title, int theme, JComponent contentPanel,
			int panelType) {
		this.title = title;
		this.theme = theme;
		this.contentPanel = contentPanel;
		this.panelType = panelType;
		init();
	}

	/**
	 * Constructor with title,theme,content panel and iconPath.
	 * 
	 * @param title
	 *            title on the panel
	 * @param theme
	 *            theme of the panel
	 * @param contentPanel
	 *            component to be added
	 * @param iconPath
	 *            path of the icon
	 */
	public TitlePanel(String title, int theme, JComponent contentPanel,
			String iconPath) {
		this.title = title;
		this.theme = theme;
		this.contentPanel = contentPanel;
		this.iconPath = iconPath;
		init();
	}

	/**
	 * Constructor with title,theme,content panel and panel type.
	 * 
	 * @param title
	 *            title on the panel
	 * @param theme
	 *            theme of the panel
	 * @param contentPanel
	 *            component to be added
	 * @param panelType
	 *            panel type
	 * @param iconPath
	 */
	public TitlePanel(String title, int theme, JComponent contentPanel,
			int panelType, String iconPath) {
		this.title = title;
		this.iconPath = iconPath;
		this.theme = theme;
		this.contentPanel = contentPanel;
		this.panelType = panelType;
		init();
	}
    /**
     * Initializes
     */
	private void init() {
		setLayout(new BorderLayout());
		HeadingPanel panel = new HeadingPanel(title, theme, iconPath, panelType);
		setOpaque(false);
		panel.setPreferredSize(new Dimension(100, 35));
		contentPanel.setBorder(new LineBorder(new Color(53, 53, 53, 100)));
		add(panel, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);

	}

}