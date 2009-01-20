package brickcitybank;

import java.awt.Frame;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JSlider;

/**
 * @author  Louis Duke
 */
public class Test extends Frame {

	private static final long serialVersionUID = 1L;
	/**
	 * @uml.property  name="hitme"
	 */
	private JButton hitme = null;
	/**
	 * @uml.property  name="jPanel"
	 */
	private JPanel jPanel = null;
	/**
	 * @uml.property  name="jPanel1"
	 */
	private JPanel jPanel1 = null;
	/**
	 * @uml.property  name="jButton"
	 */
	private JButton jButton = null;
	/**
	 * @uml.property  name="jSlider"
	 */
	private JSlider jSlider = null;

	/**
	 * This is the default constructor
	 */
	public Test() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(505, 279);
		this.setTitle("Frame");

		this.add(getHitme(), BorderLayout.WEST);
		this.add(getJPanel(), BorderLayout.EAST);
		this.add(getJPanel1(), BorderLayout.CENTER);
	}

	/**
	 * This method initializes hitme	
	 * @return  javax.swing.JButton
	 * @uml.property  name="hitme"
	 */
	private JButton getHitme() {
		if (hitme == null) {
			hitme = new JButton();
			hitme.setText("kugiugiug");
			hitme.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return hitme;
	}

	/**
	 * This method initializes jPanel	
	 * @return  javax.swing.JPanel
	 * @uml.property  name="jPanel"
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * @return  javax.swing.JPanel
	 * @uml.property  name="jPanel1"
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.gridx = 9;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 9;
			gridBagConstraints.gridheight = 2;
			gridBagConstraints.gridwidth = 2;
			gridBagConstraints.gridy = 12;
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout());
			jPanel1.add(getJButton(), gridBagConstraints);
			jPanel1.add(getJSlider(), gridBagConstraints1);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jButton	
	 * @return  javax.swing.JButton
	 * @uml.property  name="jButton"
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("kjgk");
		}
		return jButton;
	}

	/**
	 * This method initializes jSlider	
	 * @return  javax.swing.JSlider
	 * @uml.property  name="jSlider"
	 */
	private JSlider getJSlider() {
		if (jSlider == null) {
			jSlider = new JSlider();
		}
		return jSlider;
	}

}  //  @jve:decl-index=0:visual-constraint="216,34"
