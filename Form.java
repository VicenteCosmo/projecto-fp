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
import java.io.RandomAccessFile;
import java.io.IOException;

public class Form extends JPanel implements ActionListener
{

        private JPanel container;
        private JTextField nomeF;
        private JPasswordField passwordF;

	private JTextField id;
        private JTextField numeroCertificado;
    	private JTextField slug;
        private JTextField dataInicioCurso;
     	private JTextField dataFimCurso;
      	private JTextField dataEmissao;
       	private JTextField dataValidade;


        public Form()
        {
                //super("Login");       //titulo do formulario
                this.setLayout(new GridBagLayout());

                container = new JPanel(new GridBagLayout());

                //Font
                Font font = new Font("Segoe UI", Font.PLAIN, 20);

                //Campo nome
                JLabel idLb = new JLabel("ID", SwingConstants.RIGHT);
                idLb.setFont(font);
                idLb.setForeground(Color.decode("#2d3436"));
                id = new JTextField(15);
                //nomeF.setPreferredSize(new Dimension(200, 30));
                id.setFont(font);

                //Campo password
                JLabel numCertificadoLb = new JLabel("Nº Cert.", SwingConstants.RIGHT);
                numCertificadoLb.setFont(font);
                numCertificadoLb.setForeground(Color.decode("#2d3436"));
		numeroCertificado = new JTextField(15);
                //nomeF.setPreferredSize(new Dimension(200, 30));
                numeroCertificado.setFont(font);

                //Campo slug
                JLabel slugLb = new JLabel("Slug", SwingConstants.RIGHT);
                slugLb.setFont(font);
                slugLb.setForeground(Color.decode("#2d3436"));
                slug = new JPasswordField(15);
                slug.setFont(font);

		//Campo Data de Início
                JLabel dataInicioCursoLb = new JLabel("Data de Fim", SwingConstants.RIGHT);
                dataInicioCursoLb.setFont(font);
                dataInicioCursoLb.setForeground(Color.decode("#2d3436"));
                dataInicioCurso = new JTextField(15);
                dataInicioCurso.setFont(font);
		
		//Campo Data de Término
                JLabel dataFimCursoLb = new JLabel("Data de Início", SwingConstants.RIGHT);
                dataFimCursoLb.setFont(font);
                dataFimCursoLb.setForeground(Color.decode("#2d3436"));
                dataFimCurso = new JTextField(15);
                dataFimCurso.setFont(font);

		//Campo da data de emissão
                JLabel dataEmissaoLb = new JLabel("Data de Emissão", SwingConstants.RIGHT);
                dataEmissaoLb.setFont(font);
                dataEmissaoLb.setForeground(Color.decode("#2d3436"));
                dataEmissao = new JTextField(15);
                dataEmissao.setFont(font);
		
		//Campo da data de validade
                JLabel dataValidadeLb = new JLabel("Data de Validade", SwingConstants.RIGHT);
                dataValidadeLb.setFont(font);
                dataValidadeLb.setForeground(Color.decode("#2d3436"));
                dataValidade = new JTextField(15);
                dataValidade.setFont(font);

                //Botao
                JButton btn = new JButton("Salvar");
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
                container.add(numeroCertificado, gbc);

		//L 2
                gbc.gridx = 0; gbc.gridy = 2;
                container.add(slugLb, gbc);

                gbc.gridx = 1; gbc.gridy = 2;
                gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
                container.add(slug, gbc);

		//L 3
                gbc.gridx = 0; gbc.gridy = 3;
                container.add(dataInicioCursoLb, gbc);

                gbc.gridx = 1; gbc.gridy = 3;
                gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
                container.add(dataInicioCurso, gbc);

		//L 4
                gbc.gridx = 0; gbc.gridy = 4;
                container.add(dataFimCursoLb, gbc);

                gbc.gridx = 1; gbc.gridy = 4;
                gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
                container.add(dataFimCurso, gbc);

		//L 5
                gbc.gridx = 0; gbc.gridy = 5;
                container.add(dataEmissaoLb, gbc);

                gbc.gridx = 1; gbc.gridy = 5;
                gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
                container.add(dataEmissao, gbc);

		//L 6
                gbc.gridx = 0; gbc.gridy = 6;
                container.add(dataValidadeLb, gbc);

                gbc.gridx = 1; gbc.gridy = 6;
                gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
                container.add(dataValidade, gbc);

                gbc.gridx = 0; gbc.gridy = 7;
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

		//Instanciar o modelo
		
		int idInt = Integer.parseInt(id.getText());
		
		CertificadoModelo modelo = new CertificadoModelo();
		
		modelo.setId(idInt);
		modelo.setNumeroCertificado(this.numeroCertificado.getText());
		modelo.setSlug(this.slug.getText());
		modelo.setDataInicioCurso(this.dataInicioCurso.getText());
		modelo.setDataFimCurso(this.dataFimCurso.getText());
		modelo.setDataEmissao(this.dataEmissao.getText());
		modelo.setDataValidade(this.dataValidade.getText());
		
		//Salvar dados
		try{

			RandomAccessFile file = new RandomAccessFile("certificados.dat", "rw");
			//Não sobreescrever os dados existentes
			file.seek(file.length());

			file.writeInt(idInt);
			modelo.numeroCertificado.write(file);
			modelo.slug.write(file);
			modelo.dataInicioCurso.write(file);
			modelo.dataFimCurso.write(file);
			modelo.dataEmissao.write(file);
			modelo.dataValidade.write(file);
			
			JOptionPane.showMessageDialog(this, "Dados salvos com sucesso!");
			file.close();
			//Redireciona para o Dashboard
			Apresentacao app = new Apresentacao();
			app.cardLayout.show(app.container, "DASHBOARD");

		}
		catch(Exception exc)
		{
			JOptionPane.showMessageDialog(this, "AVISO: Não foi possível salvar os dados." + "\n" + exc);
		}
		
		//JOptionPane.showMessageDialog(this, modelo.toString());
		
                //app.cardLayout.show(app.container, "DASHBOARD");
        }

}


