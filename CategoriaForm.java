/****************************************************************
Projecto de Fundamentos de Programação II;
Tema: Sistema de Gestão Certificados;
Nome: Viente Cosmo, N. 36479;
File Name: CategoriaForm.java;
Data: 05.06.2026.
*****************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.RandomAccessFile;
import java.io.IOException;

public class CategoriaForm extends JPanel implements ActionListener
{

        private JPanel container;
        private JTextField nomeF;
        private JPasswordField passwordF;
	private JButton btnSalvar;
	private JButton btnVoltar;

	private JTextField id;
        private JTextField nome;

	private CategoriaFile file;
	private Apresentacao app;


        public CategoriaForm(Apresentacao app)
        {
		this.app = app;
                //super("Login");       //titulo do formulario
                this.setLayout(new GridBagLayout());

                container = new JPanel(new GridBagLayout());

		file = new CategoriaFile(new CategoriaModelo());
                //Font
                Font font = new Font("Segoe UI", Font.PLAIN, 20);

		JLabel logoLb = new JLabel();
		
		ImageIcon logoIcon = new ImageIcon("image/certificate-icon.png");
            
            	Image img = logoIcon.getImage();
            	Image novaImg = img.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            	logoLb.setIcon(new ImageIcon(novaImg));

                //Campo nome
                JLabel idLb = new JLabel("ID", SwingConstants.RIGHT);
                idLb.setFont(font);
                idLb.setForeground(Color.decode("#2d3436"));
                id = new JTextField(15);
                //nomeF.setPreferredSize(new Dimension(200, 30));
		id.setText(""+file.getNextID());
		id.setEditable(false);
                id.setFont(font);

                //Campo password
                JLabel numCertificadoLb = new JLabel("Nome:", SwingConstants.RIGHT);
                numCertificadoLb.setFont(font);
                numCertificadoLb.setForeground(Color.decode("#2d3436"));
		nome = new JTextField(15);
                //nomeF.setPreferredSize(new Dimension(200, 30));
                nome.setFont(font);

                //Botao
                btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon("image/edit24.png"));

		btnSalvar.setMnemonic(KeyEvent.VK_S);
		btnSalvar.setToolTipText("Pressione ALT + S para salvar os dados");
                btnSalvar.setFocusPainted(false);
                btnSalvar.setBorderPainted(false);
                btnSalvar.setFont(new Font("Segoe UI", Font.BOLD, 20));
                btnSalvar.setBackground(Color.decode("#d0e3fb"));

                btnSalvar.addActionListener(this);
                btnSalvar.addMouseListener(

                        new MouseAdapter()
                        {
                                @Override
                                public void mouseEntered(MouseEvent e)
                                {
                                        btnSalvar.setBackground(Color.WHITE);
                                        btnSalvar.setFont(new Font("Segoe UI", Font.PLAIN, 22));
                                }
                                public void mouseExited(MouseEvent e)
                                {
                                        btnSalvar.setFont(new Font("Segoe UI", Font.BOLD, 20));
                                        btnSalvar.setBackground(Color.decode("#d0e3fb"));
                                }
			}
                );
                //btn.setPreferredSize(new Dimension(5, 20));

		btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon("image/cancel24.png"));

		btnVoltar.setMnemonic(KeyEvent.VK_V);
		btnVoltar.setToolTipText("Pressione ALT + V para voltar à tela anterior");
		btnVoltar.setFocusPainted(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnVoltar.setBackground(Color.decode("#d0e3fb"));

		btnVoltar.addActionListener(this);
		btnVoltar.addMouseListener(
				new MouseAdapter()
				{
						@Override
						public void mouseEntered(MouseEvent e)
						{
								btnVoltar.setBackground(Color.WHITE);
								btnVoltar.setFont(new Font("Segoe UI", Font.PLAIN, 22));
						}
						public void mouseExited(MouseEvent e)
						{
								btnVoltar.setFont(new Font("Segoe UI", Font.BOLD, 20));
								btnVoltar.setBackground(Color.decode("#d0e3fb"));
						}
				}
		);

                //Configuara as posicoes L 0
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(8, 8, 8, 8);

		gbc.gridx = 0; gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		container.add(logoLb, gbc);

                gbc.gridx = 0; gbc.gridy = 1;
				gbc.gridwidth = 1;
                //gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0.0;
                container.add(idLb, gbc);

                //
                gbc.gridx = 1; gbc.gridy = 1;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                container.add(id, gbc);

		//L 1
                gbc.gridx = 0; gbc.gridy = 2;
				gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0.0;
                container.add(numCertificadoLb, gbc);

                gbc.gridx = 1; gbc.gridy = 2;
                gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
                container.add(nome, gbc);

		JPanel pnlBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
		pnlBotoes.setOpaque(false);
		pnlBotoes.add(btnSalvar);
		pnlBotoes.add(btnVoltar);

                gbc.gridx = 0; gbc.gridy = 4;
                gbc.gridwidth = 2;
                gbc.anchor = GridBagConstraints.CENTER;
				gbc.fill = GridBagConstraints.NONE;
                container.add(pnlBotoes, gbc);

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

	//Construtor 2
	public CategoriaForm(Apresentacao app, String id, String nome)
	{
		this(app);
		this.id.setText(id);
		this.nome.setText(nome);

		this.btnSalvar.setText("Atualizar");
		this.btnSalvar.setMnemonic(KeyEvent.VK_A);
		this.btnSalvar.setToolTipText("Pressione ALT + A para atualizar os dados");
	}

        public void actionPerformed(ActionEvent e)
        {
                //Apresentacao app = new Apresentacao();

		if (e.getSource() == btnVoltar) {
			app.cardLayout.show(app.container, "DASHBOARD");
			return;
		}

		//Instanciar o modelo
		
		int idInt = Integer.parseInt(id.getText());
		
		CategoriaModelo modelo = new CategoriaModelo();
		
		modelo.setId(idInt);
		modelo.setNome(this.nome.getText());
		
		//Dados da pesquisa
		CategoriaFile eFile = new CategoriaFile(modelo);
		CategoriaModelo result = eFile.pesquisarCategoriaPorNome(nome.getText());

		//Salvar dados
		try{

			if(nome.getText().trim().isEmpty())
				JOptionPane.showMessageDialog(null, "Certifique-se de que inseriu o nome da categoria!");
			else
			{
				String btnText = btnSalvar.getText();

				if("Salvar".equals(btnText))
				{
					if(result == null)
						modelo.salvarDados();
					else
						JOptionPane.showMessageDialog(null, "Categoria já registrada. \n" + "Por favor, registre uma nova categoria!");
				}
				else if("Atualizar".equals(btnText))
				{
					if(result == null)
						modelo.atualizarDadosPorId(idInt);
					else
						JOptionPane.showMessageDialog(null, "Categoria já registrada. \n" + "Por favor, registre uma nova categoria!");

				}

				app.cardLayout.show(app.container, "DASHBOARD");
			}
		}
		catch(Exception exc)
		{
			JOptionPane.showMessageDialog(this, "AVISO: Não foi possível salvar os dados." + "\n" + exc);
		}

		//JOptionPane.showMessageDialog(this, modelo.toString());
		
                //app.cardLayout.show(app.container, "DASHBOARD");
        }

}
