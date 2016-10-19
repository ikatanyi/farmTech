package com.jd.swing.demo.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import com.jd.swing.custom.component.panel.StandardPanel;
import com.jd.swing.util.PanelType;
import com.jd.swing.util.Theme;

public class Test {
	public static void main(String[] args) {
		
		//StandardPanel rectangulerPanel = new StandardPanel();
		
		//StandardPanel rectangulerPanel = new StandardPanel(Theme.STANDARD_BLUE_THEME);

		
		StandardPanel rectangulerPanel = new StandardPanel(Theme.STANDARD_BLUE_THEME);

		StandardPanel roundRectPanel = new StandardPanel(Theme.STANDARD_RED_THEME,PanelType.PANEL_ROUNDED_RECTANGLUR);

		StandardPanel ellipsePanel = new StandardPanel(Theme.STANDARD_ORANGE_THEME,PanelType.PANEL_ELLIPSE);
		
		StandardPanel roundedPanel = new StandardPanel(Theme.STANDARD_GREEN_THEME,PanelType.PANEL_ROUNDED);

		StandardPanel circularPanel = new StandardPanel(Theme.STANDARD_GOLD_THEME,PanelType.PANEL_CIRCULAR);


		rectangulerPanel.setPreferredSize(new  Dimension(300,100));
		roundRectPanel.setPreferredSize(new Dimension(300,100));
		roundedPanel.setPreferredSize(new Dimension(300,100));
		ellipsePanel.setPreferredSize(new Dimension(300,100));
		circularPanel.setPreferredSize(new Dimension(200,200));

		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.getContentPane().setBackground(Color.white);
		frame.add(rectangulerPanel);
		frame.add(roundRectPanel);
		frame.add(ellipsePanel);
		frame.add(roundedPanel);
		frame.add(circularPanel);
		frame.setVisible(true);
	}
}
