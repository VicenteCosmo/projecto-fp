/****************************************************************
Projecto de Fundamentos de Programação II;
Tema: Sistema de Gestão Certificados;
Nome: Viente Cosmo, N. 36479;
File Name: Analise.java;
Data: 05.06.2026.
*****************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JPanel implements ActionListener
{
	public boolean isLogged;

	private JPanel container;
	private JTextField nomeF;
	private JPasswordField passwordF;

	private Apresentacao app;

	public Login(Apresentacao app)
	{
		this.app = app;
		//super("Login");	//titulo do formulario
		this.setLayout(new GridBagLayout());

		container = new JPanel(new GridBagLayout());

		//Font
		Font font = new Font("Segoe UI", Font.PLAIN, 20);
		
		//Campo nome
		JLabel nome = new JLabel("Nome", SwingConstants.RIGHT);
		nome.setFont(font);
		nome.setForeground(Color.decode("#2d3436"));
		nomeF = new JTextField(15);
		//nomeF.setPreferredSize(new Dimension(200, 30));
		nomeF.setFont(font);

		//Campo password
		JLabel password = new JLabel("Password", SwingConstants.RIGHT);
		password.setFont(font);
		password.setForeground(Color.decode("#2d3436"));
		passwordF = new JPasswordField(15);
		passwordF.setFont(font);
		
		//Botao
		JButton btn = new JButton("Entrar");
		btn.setFocusPainted(false);
		btn.setBorderPainted(false);
		btn.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btn.setBackground(Color.decode("#d0e3fb"));

		btn.addActionListener(this);
		btn.addMouseListener(

			new MouseAdapter()
			{
			  	@Override
				public void mouseEntered(MouseEvent e)
				{
					btn.setBackground(Color.WHITE);
					btn.setFont(new Font("Segoe UI", Font.PLAIN, 22));
				}
				public void mouseExited(MouseEvent e)
				{
					btn.setFont(new Font("Segoe UI", Font.BOLD, 20));
					btn.setBackground(Color.decode("#d0e3fb"));
				}
			}
		);
		//btn.setPreferredSize(new Dimension(5, 20));

		//Configuara as posicoes
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);
		
		gbc.gridx = 0; gbc.gridy = 0;
		//gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0.0;
		container.add(nome, gbc);

		//nomeF
		gbc.gridx = 1; gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		container.add(nomeF, gbc);

		gbc.gridx = 0; gbc.gridy = 1;
		container.add(password, gbc);

		gbc.gridx = 1; gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
		container.add(passwordF, gbc);

		gbc.gridx = 0; gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		container.add(btn, gbc);

		//container.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		container.setOpaque(false);
		container.setBackground(Color.decode("#d0e3fb"));
		//container.setPreferredSize(new Dimension(350, 200));

		//
		GridBagConstraints gbcMain = new GridBagConstraints();
		gbcMain.insets = new Insets(150, 150, 150, 150);

		this.setBackground(Color.decode("#d0e3fb"));
		this.add(container, gbcMain);

	}
	public void actionPerformed(ActionEvent e)
	{
		//Apresentacao app = new Apresentacao();
		app.cardLayout.show(app.container, "DASHBOARD");
	}

}
