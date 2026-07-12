/****************************************************************
Projecto de Fundamentos de Programação II;
Tema: Sistema de Gestão Certificados;
Nome: Viente Cosmo, N. 36479;
File Name: Form.java;
Data: 05.06.2026.
*****************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.RandomAccessFile;
import java.io.IOException;

public class EstudanteForm extends JPanel implements ActionListener
{

        private JPanel container;
        private JTextField nomeF;
        private JPasswordField passwordF;
	private JButton btnSalvar;

	private JTextField id;
        private JTextField nome;
    	private JTextField bi;

	private EstudanteFile file;
	private Apresentacao app;


        public EstudanteForm(Apresentacao app)
        {
		this.app = app;
                //super("Login");       //titulo do formulario
                this.setLayout(new GridBagLayout());

                container = new JPanel(new GridBagLayout());

		file = new EstudanteFile(new EstudanteModelo());
                //Font
                Font font = new Font("Segoe UI", Font.PLAIN, 20);

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

                //Campo slug
                JLabel slugLb = new JLabel("Nº.", SwingConstants.RIGHT);
                slugLb.setFont(font);
                slugLb.setForeground(Color.decode("#2d3436"));
                bi = new JTextField(15);
                bi.setFont(font);

                //Botao
                btnSalvar = new JButton("Salvar");
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

                //Configuara as posicoes L 0
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(8, 8, 8, 8);

                gbc.gridx = 0; gbc.gridy = 0;
                //gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0.0;
                container.add(idLb, gbc);

                //
                gbc.gridx = 1; gbc.gridy = 0;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                container.add(id, gbc);

		//L 1
                gbc.gridx = 0; gbc.gridy = 1;
                container.add(numCertificadoLb, gbc);

                gbc.gridx = 1; gbc.gridy = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
                container.add(nome, gbc);

		//L 2
                gbc.gridx = 0; gbc.gridy = 2;
                container.add(slugLb, gbc);

                gbc.gridx = 1; gbc.gridy = 2;
                gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
                container.add(bi, gbc);

                gbc.gridx = 0; gbc.gridy = 4;
                gbc.gridwidth = 2;
                gbc.anchor = GridBagConstraints.CENTER;
                container.add(btnSalvar, gbc);

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
	public EstudanteForm(Apresentacao app, String id, String nome, String bi)
	{
		this(app);
		this.id.setText(id);
		this.nome.setText(nome);
		this.bi.setText(bi);

		this.btnSalvar.setText("Atualizar");
	}

        public void actionPerformed(ActionEvent e)
        {
                //Apresentacao app = new Apresentacao();

		//Instanciar o modelo
		
		int idInt = Integer.parseInt(id.getText());
		
		EstudanteModelo modelo = new EstudanteModelo();
		
		modelo.setId(idInt);
		modelo.setNome(this.nome.getText());
		modelo.setBI(this.bi.getText());
		
		//Dados da pesquisa
		EstudanteFile eFile = new EstudanteFile(modelo);
		EstudanteModelo result = eFile.pesquisarEstudantePorNome(nome.getText());

		//Salvar dados
		try{

			if(nome.getText().trim().isEmpty())
				JOptionPane.showMessageDialog(null, "Certifique-se de que inseriu o nome do estudante!");
			else if(bi.getText().trim().isEmpty())
				JOptionPane.showMessageDialog(null, "Certifique-se de que inseriu o BI do estudante!");
			else
			{
				String btnText = btnSalvar.getText();

				if("Salvar".equals(btnText))
				{
					if(result == null)
						modelo.salvarDados();
					else
						JOptionPane.showMessageDialog(null, "Estudante já registrado. \n" + "Por favor, registre um novo estudante!");
				}
				else if("Atualizar".equals(btnText))
				{
					if(result == null)
						modelo.atualizarDadosPorId(idInt);
					else
						JOptionPane.showMessageDialog(null, "Estudante já registrado. \n" + "Por favor, registre um novo estudante!");

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



