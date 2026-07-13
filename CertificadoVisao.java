/****************************************************************
Projecto de Fundamentos de Programação II;
Tema: Sistema de Gestão Certificados;
Nome: Viente Cosmo, N. 36479;
File Name: CertificadoVisao.java;
Data: 05.06.2026.
*****************************************************************/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.RandomAccessFile;
import java.util.List;

import SwingComponents.*;

public class CertificadoVisao extends JPanel
{
	
	private Apresentacao app;
	private JPanel painelEsquerdo;
	private JPanel painelCentro;

	private JPanel painelConteudo;
	private CardLayout cardLayoutConteudo;

	private CertificadosTabela tabelaCertificados;
	private EstudantesTabela tabelaEstudantes;
	private CategoriasTabela tabelaCategorias;
	private Settings tabelaSettings;

	public CertificadoVisao(Apresentacao app)
	{
		this.app = app;
		
		this.setLayout(new BorderLayout());
		
		painelEsquerdo = new Sidebar();
		
		painelCentro = new JPanel(new BorderLayout());
		painelCentro.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

		painelCentro.add(new Header(), BorderLayout.NORTH);

		//Conteúdo central
		cardLayoutConteudo = new CardLayout();
		painelConteudo = new JPanel(cardLayoutConteudo);

		//INtancia para atualizar a tabela depois
		tabelaCertificados = new CertificadosTabela(app);
		tabelaEstudantes = new EstudantesTabela(app);
		tabelaCategorias = new CategoriasTabela(app);
		tabelaSettings = new Settings(app);

		// Toda vez que este painel específico aparecer na tela, ele atualiza a tabela automaticamente
		tabelaEstudantes.addComponentListener(new java.awt.event.ComponentAdapter() {
    			@Override
    			public void componentShown(java.awt.event.ComponentEvent e) {
        			tabelaEstudantes.carregarDados();

				painelConteudo.revalidate();
				painelConteudo.repaint();
				cardLayoutConteudo.show(painelConteudo, "ESTUDANTES");
    			}
		});


		painelConteudo.add(tabelaCertificados, "CERTIFICADOS");
		painelConteudo.add(tabelaEstudantes, "ESTUDANTES");
		painelConteudo.add(tabelaCategorias, "CATEGORIAS");
		painelConteudo.add(tabelaSettings, "SETTINGS");

		painelCentro.add(painelConteudo, BorderLayout.CENTER);

		this.add(painelEsquerdo, BorderLayout.WEST);
		this.add(painelCentro, BorderLayout.CENTER);
	}

	class Header extends JPanel
	{
		public Header()
		{
			JPanel header = new JPanel(new BorderLayout());
			header.setBackground(new Color(240, 242, 245));

			JPanel pnlTxtHeader = new JPanel(new GridLayout(2, 1));
		        pnlTxtHeader.setBackground(new Color(240, 242, 245));
		        JLabel title = new JLabel("Bem-vindo ao Procishore Manager");
		        title.setFont(new Font("Arial", Font.BOLD, 22));
		        JLabel subtitle = new JLabel("Gerencie seus certificados e acompanhe o progresso.");
		        subtitle.setForeground(Color.GRAY);
		        pnlTxtHeader.add(title);
		        pnlTxtHeader.add(subtitle);

			JLabel userAdmin = new JLabel("Admin User", SwingConstants.RIGHT);
		        userAdmin.setFont(new Font("Arial", Font.BOLD, 14));
		        
		        header.add(pnlTxtHeader, BorderLayout.CENTER);
		        header.add(userAdmin, BorderLayout.EAST);

			this.setLayout(new BorderLayout());
			this.add(header, BorderLayout.CENTER);
		}
	}

	class Sidebar extends JPanel implements ActionListener
	{
		
		public Sidebar()
		{
			JPanel sidebar = this;
			sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
			sidebar.setBackground(new Color(24, 43, 73));
			sidebar.setPreferredSize(new Dimension(220, 720));
			sidebar.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));
			
			JLabel brand = new JLabel("Procishore");
			brand.setFont(new Font("Arial", Font.BOLD, 20));
			brand.setForeground(Color.WHITE);
			
			sidebar.add(brand);
			sidebar.add(Box.createVerticalStrut(30));

			String menuItem[] = {"Certificados", "Categorias", "Estudantes", "Configuraçoões"};
			for (String item : menuItem)
			{
				JButton btnMenu = new JButton(item);
				btnMenu.addActionListener(this);
				btnMenu.setForeground(new Color(200, 210, 225));
			        btnMenu.setBackground(new Color(24, 43, 73));
			        btnMenu.setFont(new Font("Arial", Font.PLAIN, 14));
			        btnMenu.setBorderPainted(false);
			        btnMenu.setFocusPainted(false);
			        btnMenu.setAlignmentX(Component.LEFT_ALIGNMENT);
				sidebar.add(btnMenu);
			        sidebar.add(Box.createVerticalStrut(10));
			}
		}
		@Override
		public void actionPerformed(ActionEvent ev)
		{

			if("Certificados".equals(ev.getActionCommand())) 
			{
				tabelaCertificados.carregarDados(); 
				cardLayoutConteudo.show(painelConteudo, "CERTIFICADOS");
			}
			else if("Estudantes".equals(ev.getActionCommand()))
			{ 
				tabelaEstudantes.carregarDados();

				painelConteudo.revalidate();
				painelConteudo.repaint();
				cardLayoutConteudo.show(painelConteudo, "ESTUDANTES");
			}
			else if("Categorias".equals(ev.getActionCommand()))
			{ 
				tabelaCategorias.carregarDados();

				painelConteudo.revalidate();
				painelConteudo.repaint();
				cardLayoutConteudo.show(painelConteudo, "CATEGORIAS");
			}
			else if("Configuraçoões".equals(ev.getActionCommand()))
                        {
                                painelConteudo.revalidate();
                                painelConteudo.repaint();
                                cardLayoutConteudo.show(painelConteudo, "SETTINGS");
                        }

		}
	}


}
