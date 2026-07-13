/****************************************************************
Projecto de Fundamentos de Programação II;
Tema: Sistema de Gestão Certificados;
Nome: Viente Cosmo, N. 36479;
File Name: Apresentacao.java;
Data: 05.06.2026.
****************************************************************/


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;


public class Apresentacao extends JFrame
{
	public JPanel container;
	public CardLayout cardLayout;

	//constructor da class
	public Apresentacao()
	{
		super("Apresentacao do Projecto - Gestão de Certificados");

		cardLayout = new CardLayout();

		container = new JPanel();
		container.setLayout(cardLayout);
		container.setBackground(Color.GRAY);

		container.add(new PainelApresentacao(), "MAIN");
		container.add(new Login(this), "LOGIN");
		container.add(new CertificadoVisao(this), "DASHBOARD");
		//container.add(new EstudanteVisao(), "DASHBOARD");
		container.add(new Form(this), "FORM");
		container.add(new EstudanteForm(this), "EstudanteForm");
		container.add(new CategoriaForm(this), "CategoriaForm");
		container.add(new PasswordForm(this), "PasswordForm");

		this.add(container);

		cardLayout.show(container, "MAIN");

		this.setSize(1248, 720);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	//Exibe a caixa de diálogo com as instruções dos atalhos
	public void mostrarJanelaAtalhos(Component pai) {
		String textoAjuda = "GUIA DE TECLAS DE ATALHO DO SISTEMA\n\n" +
				"• [ALT + H]  - Mostrar esta tela de ajuda\n" +
				"• [ALT + S]  - Fechar / Sair do Sistema (Exit)\n" +
				"• [ALT + E]  - Botão 'Entrar' (Ecrã Inicial)\n" +
				"• [ALT + C]  - Botão 'Começar Agora' (Ecrã Inicial)\n\n" +
				"  Ecrã de Login:\n" +
				"• [ALT + E]  - Executar autenticação no sistema\n" +
				"• [ALT + R]  - Redirecionar para Recuperar Senha\n\n" +
				"  Ecrã de Recuperação de Palavra-Passe:\n" +
				"• [ALT + C]  - Cancelar operação e retornar";

		JOptionPane.showMessageDialog(pai, textoAjuda, "Mapeamento de Atalhos (Mnemónicos)", JOptionPane.INFORMATION_MESSAGE);
	}

	// 2. Executa o encerramento seguro do aplicativo inteiro
	public void fecharSistemaConfirmando(Component pai) {
		int resposta = JOptionPane.showConfirmDialog(pai, 
			"Tem a certeza de que deseja encerrar a aplicação?", 
			"Sair do Sistema", 
			JOptionPane.YES_NO_OPTION, 
			JOptionPane.QUESTION_MESSAGE);
			
		if (resposta == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}


	//class interna
	//Contém a barra de ferramentas da Apresentacao
	class PainelApresentacao extends JPanel implements ActionListener
	{
		JButton avancarJBT, fecharJBT;
		ImageIcon logo = new ImageIcon("image/certificate-icon.png");
		JLabel logoLb;

		JButton btnEntrar;
		JButton btnBody;
		
		JButton btnAjudaAtalhos;
		JButton btnSairSistema;

		public PainelApresentacao()
		{
			this.setLayout(new BorderLayout());

			//Header
			JPanel header = new JPanel();
			header.setLayout(new BorderLayout());
			header.setBorder(new EmptyBorder(15, 30, 15, 30));
			//Logo e navbar

			Image img = logo.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
			
			logoLb = new JLabel("Procishore", new ImageIcon(img), SwingConstants.LEADING);
			logoLb.setFont(new Font("Arial", Font.BOLD, 22));
			logoLb.setHorizontalTextPosition(SwingConstants.RIGHT);
			logoLb.setVerticalTextPosition(SwingConstants.CENTER);
			logoLb.setVerticalAlignment(SwingConstants.CENTER);
			logoLb.setHorizontalAlignment(SwingConstants.LEFT);

			JPanel navbar = new JPanel();
			navbar.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 35));
			
			// Inicialização do botão de atalhos (Com atalho ALT + H)
			btnAjudaAtalhos = new JButton("Atalhos");
			btnAjudaAtalhos.setMnemonic(KeyEvent.VK_H); 
			btnAjudaAtalhos.setToolTipText("Pressione ALT + H para ver os atalhos");
			btnAjudaAtalhos.setFocusPainted(false);
			btnAjudaAtalhos.addActionListener(this);
			
			btnSairSistema = new JButton("Sair");
			btnSairSistema.setMnemonic(KeyEvent.VK_S); 
			btnSairSistema.setToolTipText("Pressione ALT + S para fechar o programa");
			btnSairSistema.setFocusPainted(false);
			btnSairSistema.setBackground(Color.decode("#ff7675"));
			btnSairSistema.addActionListener(this);
			
			btnEntrar = new JButton("Entrar");
			btnEntrar.setMnemonic(KeyEvent.VK_E); 
			btnEntrar.setToolTipText("Pressione ALT + E para aceder ao login");
			btnEntrar.setFocusPainted(false);
			btnEntrar.addActionListener(this);
			
			try {
				btnAjudaAtalhos.setIcon(new ImageIcon("image/edit24.png")); 
				btnSairSistema.setIcon(new ImageIcon("image/cancel24.png")); 
			} catch(Exception e) {
				System.out.println("Aviso: Falha ao carregar ícones da navbar.");
			}
			
			navbar.add(btnAjudaAtalhos);
			navbar.add(btnSairSistema);
			navbar.add(btnEntrar);

			//Add items to header
			header.add(logoLb, BorderLayout.WEST);
			header.add(navbar, BorderLayout.EAST);

			this.add(header, BorderLayout.NORTH);

			//Body
			Box bodyBox = Box.createVerticalBox();

			JLabel lb1 = new JLabel("Solução Completa de Certificação");
			lb1.setFont(new Font("Serif", Font.BOLD, 40));
			lb1.setForeground(Color.decode("#25447B"));
			//lb1.setBorder(new EmptyBorder(10, 100, 10, 100));
			lb1.setAlignmentX(Component.CENTER_ALIGNMENT);

			bodyBox.add(Box.createVerticalStrut(140));
			bodyBox.add(lb1);

			JLabel lb2 = new JLabel("Sistema inteligente para gerar, gerenciar e compartilhar certificado de forma eficiente e eficaz");
			lb2.setBackground(Color.decode("#25447B"));
			lb2.setOpaque(true);
			lb2.setFont(new Font("Italic", Font.ITALIC, 14));
			lb2.setBorder(new EmptyBorder(20, 20, 15, 20));
			lb2.setForeground(Color.WHITE);
			lb2.setAlignmentX(Component.CENTER_ALIGNMENT);
			//lb2.setAlignmentY(Component.);
			
			bodyBox.add(Box.createVerticalStrut(20));
			bodyBox.add(lb2);

			btnBody = new JButton("Começar Agora");
			btnBody.setMnemonic(KeyEvent.VK_C);
			btnBody.setToolTipText("Pressione ALT + C para iniciar");
			btnBody.setBorder(BorderFactory.createLineBorder(Color.decode("#25447b"), 20));
			btnBody.setFocusPainted(false);
			btnBody.setBorder(new EmptyBorder(30, 20, 30, 20));
			btnBody.setFont(new Font("Serif", Font.PLAIN, 24));
			btnBody.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnBody.setContentAreaFilled(false);
			btnBody.setOpaque(true);
			btnBody.setForeground(new Color(30, 144, 255));

			btnBody.addMouseListener(
				new MouseAdapter()
				{
					public void mouseEntered(MouseEvent e)
					{
						btnBody.setForeground(Color.GRAY);
					}
					public void mouseExited(MouseEvent e)
					{
						btnBody.setForeground(new Color(30, 144, 255));
					}

				}
			);
			btnBody.addActionListener(this);

			bodyBox.add(Box.createVerticalStrut(12));
			bodyBox.add(btnBody);

			this.add(bodyBox, BorderLayout.CENTER);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			// Ouvintes para chamadas globais
			if (e.getSource() == btnAjudaAtalhos) {
				mostrarJanelaAtalhos(this);
				return;
			}
			
			if (e.getSource() == btnSairSistema) {
				fecharSistemaConfirmando(this);
				return;
			}

			if(e.getSource() == btnEntrar || e.getSource() == btnBody)
				cardLayout.show(container, "LOGIN");
		}

	}
	
	public static void main(String args[])
	{
		//liga o nosso projecto com a API da UCAN
		Vector_Tabelas.inic();
		
		new Apresentacao();
	}
}
