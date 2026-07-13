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
import java.util.List;
import Calendario.*;

public class Form extends JPanel implements ActionListener
{

        private JPanel container;
        private JTextField nomeF;
	private JButton btnSalvar;
        private JPasswordField passwordF;

	private JTextField id;
	private JComboBox<String> nome;
        private JTextField numeroCertificado;
    	private JComboBox<String> curso;
        	
	private JTextFieldData dataInicioCurso;
     	private JTextFieldData dataFimCurso;
      	private JTextFieldData dataEmissao;
       	private JTextFieldData dataValidade;

	//private JTextFieldData txtData;

	private CertificadoFile file;
	private Apresentacao app;

        public Form(Apresentacao app)
        {

		this.app = app;
                //super("Login");       //titulo do formulario
                this.setLayout(new GridBagLayout());

                container = new JPanel(new GridBagLayout());

		file = new CertificadoFile(new CertificadoModelo());
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
                JLabel numCertificadoLb = new JLabel("Nº Cert.", SwingConstants.RIGHT);
                numCertificadoLb.setFont(font);
                numCertificadoLb.setForeground(Color.decode("#2d3436"));
		numeroCertificado = new JTextField(15);
                //nomeF.setPreferredSize(new Dimension(200, 30));
                numeroCertificado.setFont(font);

                //Campo nome
                JLabel nomeLb = new JLabel("Nome", SwingConstants.RIGHT);
                nomeLb.setFont(font);
                nomeLb.setForeground(Color.decode("#2d3436"));
                nome = new JComboBox<String>();
                nome.setFont(font);
		try 
                {
                        EstudanteFile estFile = new EstudanteFile(new EstudanteModelo());
                        List<EstudanteModelo> estudantes = estFile.listarEstudantesProntos();

			/*if(estudantes.isEmpty())
                        { 
                                JOptionPane.showMessageDialog(null, "Sem registro de estudantes. \nPor favor, adicione algum registro!");
                                app.cardLayout.show(app.container, "EstudanteForm");
                                return;
                        }*/

                        for (EstudanteModelo est : estudantes) {
                                nome.addItem(est.nome.toStringEliminatingSpaces()); 
                }
                } 
                catch (Exception e) 
                {
                        nome.addItem("Erro ao carregar estudantes");
                }


		//Campo curso
                JLabel cursoLb = new JLabel("Curso", SwingConstants.RIGHT);
                cursoLb.setFont(font);
                cursoLb.setForeground(Color.decode("#2d3436"));
                curso = new JComboBox<String>();
                curso.setFont(font);
		try
		{
            		CategoriaFile catFile = new CategoriaFile(new CategoriaModelo());
            		List<CategoriaModelo> cats = catFile.listarCategoriasProntas();

			/*if(cats.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Sem registro de categorias. \nPor favor, adicione algum registro!");
				app.cardLayout.show(app.container, "CategoriaForm");
				return;
			}*/

            		for (CategoriaModelo cat : cats) {
                		curso.addItem(cat.nome.toStringEliminatingSpaces());
            	}
        	}
		catch (Exception e) 
		{
            		curso.addItem("Erro ao carregar categorias");
        	}

		//Campo Data de Início
                JLabel dataInicioCursoLb = new JLabel("Data de Início", SwingConstants.RIGHT);
                dataInicioCursoLb.setFont(font);
                dataInicioCursoLb.setForeground(Color.decode("#2d3436"));
                
		JPanel painelData1 = new JPanel( new GridLayout(1, 1) );
		dataInicioCurso = new JTextFieldData("Inicio");
                dataInicioCurso.getDTestField().setFont(font);
		
		painelData1.add( dataInicioCurso.getDTestField() );
                painelData1.add( dataInicioCurso.getDButton() );
		
		//Campo Data de Término
                JLabel dataFimCursoLb = new JLabel("Data de Fim", SwingConstants.RIGHT);
                dataFimCursoLb.setFont(font);
                dataFimCursoLb.setForeground(Color.decode("#2d3436"));

		JPanel painelData2 = new JPanel( new GridLayout(1, 1) );
                dataFimCurso = new JTextFieldData("Fim");
		dataFimCurso.getDTestField().setFont(font);

                painelData2.add( dataFimCurso.getDTestField() );
                painelData2.add( dataFimCurso.getDButton() );

		//Campo da data de emissão
                JLabel dataEmissaoLb = new JLabel("Data de Emissão", SwingConstants.RIGHT);
                dataEmissaoLb.setFont(font);
                dataEmissaoLb.setForeground(Color.decode("#2d3436"));

		JPanel painelData3 = new JPanel( new GridLayout(1, 1) );
                dataEmissao = new JTextFieldData("Emissao");
                dataEmissao.getDTestField().setFont(font);

                painelData3.add( dataEmissao.getDTestField() );
                painelData3.add( dataEmissao.getDButton() );

		//Campo da data de validade
                JLabel dataValidadeLb = new JLabel("Data de Validade", SwingConstants.RIGHT);
                dataValidadeLb.setFont(font);
                dataValidadeLb.setForeground(Color.decode("#2d3436"));

		JPanel painelData4 = new JPanel( new GridLayout(1, 1) );
                dataValidade = new JTextFieldData("Validade");
                dataValidade.getDTestField().setFont(font);

                painelData4.add( dataValidade.getDTestField() );
                painelData4.add( dataValidade.getDButton() );

                //Botao
                btnSalvar = new JButton("Salvar");
                btnSalvar.setMnemonic(KeyEvent.VK_G);
                btnSalvar.setToolTipText("Pressione ALT + G para guardar ou atualizar os dados");
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

                JButton btnJanelaAtalhos = new JButton("Atalhos");
                btnJanelaAtalhos.setMnemonic(KeyEvent.VK_H);
                btnJanelaAtalhos.setToolTipText("Pressione ALT + H para ver todos os atalhos disponíveis");
                btnJanelaAtalhos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                btnJanelaAtalhos.addActionListener(e -> app.mostrarJanelaAtalhos(this));

                JButton btnJanelaVoltar = new JButton("Voltar");
                btnJanelaVoltar.setMnemonic(KeyEvent.VK_V);
                btnJanelaVoltar.setToolTipText("Pressione ALT + V para voltar ao ecrã anterior");
                btnJanelaVoltar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                btnJanelaVoltar.setBackground(Color.decode("#ff7675"));
                btnJanelaVoltar.addActionListener(e -> app.cardLayout.show(app.container, "DASHBOARD"));

                try {
                        btnJanelaAtalhos.setIcon(new ImageIcon("image/edit24.png"));
                        btnJanelaVoltar.setIcon(new ImageIcon("image/cancel24.png"));
                        btnSalvar.setIcon(new ImageIcon("image/edit24.png"));
                } catch(Exception e) {}

                JPanel pnlBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
                pnlBotoes.add(btnSalvar);
                pnlBotoes.add(btnJanelaAtalhos);
                pnlBotoes.add(btnJanelaVoltar);

                //Configuara as posicoes L 0
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(4, 8, 4, 8);

                JLabel logoLb = new JLabel();

                ImageIcon logoIcon = new ImageIcon("image/certificate-icon.png");
            
            	Image img = logoIcon.getImage();
            	Image novaImg = img.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            	logoLb.setIcon(new ImageIcon(novaImg));

                gbc.gridx = 0; gbc.gridy = 0;
                gbc.gridwidth = 2;
                gbc.fill = GridBagConstraints.NONE; gbc.weightx = 1.0;
                gbc.anchor = GridBagConstraints.CENTER;
                container.add(logoLb, gbc);

                gbc.gridwidth = 1;
                gbc.gridx = 0; gbc.gridy = 1;
                gbc.fill = GridBagConstraints.NONE; gbc.weightx = 1.0;
                container.add(idLb, gbc);

                //
                gbc.gridx = 1; gbc.gridy = 1;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                container.add(id, gbc);

		//L 1, nome
                gbc.gridx = 0; gbc.gridy = 2;
		gbc.fill = GridBagConstraints.NONE; gbc.weightx = 1.0;
                container.add(nomeLb, gbc);

                gbc.gridx = 1; gbc.gridy = 2;
                gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
                container.add(nome, gbc);
		
		//L 2
                gbc.gridx = 0; gbc.gridy = 3;
		gbc.fill = GridBagConstraints.NONE; gbc.weightx = 1.0;
                container.add(numCertificadoLb, gbc);

                gbc.gridx = 1; gbc.gridy = 3;
                gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
                container.add(numeroCertificado, gbc);

		//L 3
		gbc.gridx = 0; gbc.gridy = 4;
		gbc.fill = GridBagConstraints.NONE; gbc.weightx = 1.0;
		container.add(cursoLb, gbc);
		gbc.gridx = 1; gbc.gridy = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
		container.add(curso, gbc);
		//L 4
		gbc.gridx = 0; gbc.gridy = 5;
		gbc.fill = GridBagConstraints.NONE; gbc.weightx = 1.0;
		container.add(dataInicioCursoLb, gbc);
		gbc.gridx = 1; gbc.gridy = 5;
		gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
		container.add(painelData1, gbc);
		//L 5
		gbc.gridx = 0; gbc.gridy = 6;
		gbc.fill = GridBagConstraints.NONE; gbc.weightx = 1.0;
		container.add(dataFimCursoLb, gbc);
		gbc.gridx = 1; gbc.gridy = 6;
		gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
		container.add(painelData2, gbc);
		//L 6
		gbc.gridx = 0; gbc.gridy = 7;
		gbc.fill = GridBagConstraints.NONE; gbc.weightx = 1.0;
		container.add(dataEmissaoLb, gbc);
		gbc.gridx = 1; gbc.gridy = 7;
		gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
		container.add(painelData3, gbc);
		//L 7
		gbc.gridx = 0; gbc.gridy = 8;
		gbc.fill = GridBagConstraints.NONE; gbc.weightx = 1.0;
		container.add(dataValidadeLb, gbc);
		gbc.gridx = 1; gbc.gridy = 8;
		gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
		container.add(painelData4, gbc);
		gbc.gridx = 0; gbc.gridy = 9;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE; gbc.weightx = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;
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
	public Form(Apresentacao app, String id, String nome, String numeroCertificado, String curso, String dataInicioCurso, String dataFimCurso, String dataEmissao, String dataValidade)
	{
		this(app);
		this.id.setText(id);
		this.nome.setSelectedItem(nome);
		this.numeroCertificado.setText(numeroCertificado);
		this.curso.setSelectedItem(curso);
		this.dataInicioCurso.getDTestField().setText(dataInicioCurso);
		this.dataFimCurso.getDTestField().setText(dataFimCurso);
		this.dataEmissao.getDTestField().setText(dataEmissao);
		this.dataValidade.getDTestField().setText(dataValidade);
		this.btnSalvar.setText("Atualizar");
	}
	public void actionPerformed(ActionEvent e)
	{
		//Apresentacao app = new Apresentacao();
		//Instanciar o modelo
		int idInt = Integer.parseInt(id.getText());
		String nomeSelecionado = (String) this.nome.getSelectedItem();
		String cursoSelecionado = (String) this.curso.getSelectedItem();
		CertificadoModelo modelo = new CertificadoModelo();
		//String testData = txtData.getDTestField().getText();
		modelo.setId(idInt);
		modelo.setNome(nomeSelecionado);
		modelo.setNumeroCertificado(this.numeroCertificado.getText());
		modelo.setCurso(cursoSelecionado);
		modelo.setDataInicioCurso(this.dataInicioCurso.getDTestField().getText());
		modelo.setDataFimCurso(this.dataFimCurso.getDTestField().getText());
		modelo.setDataEmissao(this.dataEmissao.getDTestField().getText());
		modelo.setDataValidade(this.dataValidade.getDTestField().getText());
		//Dados da pesquisa
		CertificadoFile eFile = new CertificadoFile(modelo);
		CertificadoModelo result = eFile.pesquisarCertificadoPorProcesso(this.numeroCertificado.getText());
		//Salvar dados
		try
		{
			if(nomeSelecionado.trim().isEmpty())
				JOptionPane.showMessageDialog(null, "Certifique-se de que inseriu o nome!");
			else if(numeroCertificado.getText().trim().isEmpty())
				JOptionPane.showMessageDialog(null, "Certifique-se de que inseriu o número do certificado!");
			else if(cursoSelecionado.trim().isEmpty())
				JOptionPane.showMessageDialog(null, "Certifique-se de que inseriu o curso!");
			else if(dataInicioCurso.getDTestField().getText().trim().isEmpty())
				JOptionPane.showMessageDialog(null, "Certifique-se de que inseriu a data de início do curso!");
			else if(dataFimCurso.getDTestField().getText().trim().isEmpty())
				JOptionPane.showMessageDialog(null, "Certifique-se de que inseriu a data de fim do curso!");
			else if(dataEmissao.getDTestField().getText().trim().isEmpty())
				JOptionPane.showMessageDialog(null, "Certifique-se de que inseriu a data de emissão do certificado!");
			else if(dataValidade.getDTestField().getText().trim().isEmpty())
				JOptionPane.showMessageDialog(null, "Certifique-se de que inseriu a data de validade do certificado!");
			else
			{
				String btnText = btnSalvar.getText();
				if("Salvar".equals(btnText))
				{
					if(result == null)
						modelo.salvarDados();
					else
						JOptionPane.showMessageDialog(null, "Certificado já registrado. \n" + "Por favor, registre um novo certificado!");
				}
				else if("Atualizar".equals(btnText))
				{
					//if(result == null)
						modelo.atualizarDadosPorId(idInt);
					//else
						// JOptionPane.showMessageDialog(null, "Certificado já registrado. \n" + "Por favor, registre um novo certificado!");
				}
				app.cardLayout.show(app.container, "DASHBOARD");
			}
		}
		catch(Exception exc)
		{
			JOptionPane.showMessageDialog(this, "AVISO: Não foi possível salvar os dados." + "\n" + exc);
		}
	}
}

