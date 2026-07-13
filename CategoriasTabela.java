/****************************************************************
Projecto de Fundamentos de Programação II;
Tema: Sistema de Gestão Certificados;
Nome: Viente Cosmo, N. 36479;
File Name: CategoriasTabela.java;
Data: 05.06.2026.
*****************************************************************/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.RandomAccessFile;
import java.util.List;

class CategoriasTabela extends JPanel
{
		private Apresentacao app;
		
		private JButton btnAdd;
		private DefaultTableModel model;
		private JTable table;

		 //COMPONENTES para o sistema de pesquisa
       	 	private JTextField txtPesquisa;
        	private JButton btnPesquisar;

		//Total dos itens
		private JLabel totalCertificados;
		private JLabel totalEstudantes;
		private JLabel totalCategorias;

		//Cards
		JPanel pnlCards;

		private int coluna, linha;
		
		public CategoriasTabela(Apresentacao app)
		{
			this.app = app;
		
			JPanel body = new JPanel();
			body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
			body.setBackground(new Color(240, 242, 245));
        		body.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

			 // Cards indicadores
		        pnlCards = new JPanel(new GridLayout(1, 3, 20, 0));
		        pnlCards.setBackground(new Color(240, 242, 245));
		        //pnlCards.setMaximumSize(new Dimension(1100, 100));


			//Total
			totalCertificados = new JLabel("0");
			totalEstudantes = new JLabel("0");
			totalCategorias = new JLabel("0");

		        pnlCards.add(criarCardDash("Total de Certificados", totalCertificados.getText(), "+12% este mês"));
		        pnlCards.add(criarCardDash("Alunos Cadastrados", totalEstudantes.getText(), "Ativos no sistema"));
		        pnlCards.add(criarCardDash("Categorias Ativas", totalCategorias.getText(), "Operacionais"));
		        body.add(pnlCards);
		        body.add(Box.createVerticalStrut(25));
		
			//Tabela de emissões
			JPanel pnlTabela = new JPanel(new BorderLayout());
			pnlTabela.setBackground(Color.WHITE);
        		//pnlTabela.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		
			JLabel lblTabTitle = new JLabel("Categorias Recentes");
			btnAdd = new JButton("Adicionar uma nova categoria");

			btnAdd.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						app.cardLayout.show(app.container, "CategoriaForm");
					}
				}
			);

			// Pesquisa
			txtPesquisa = new JTextField(15);
			btnPesquisar = new JButton("Pesquisar");

			// Filtra usando o texto digitado
			btnPesquisar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pesquisarDados(txtPesquisa.getText());
				}
			});

			// Painel para agrupar os elementos do lado direito (Pesquisa + Botão Adicionar)
			JPanel pnlAcoesDireita = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
			pnlAcoesDireita.setBackground(Color.WHITE);
			pnlAcoesDireita.add(new JLabel("Nome:"));
			pnlAcoesDireita.add(txtPesquisa);
			pnlAcoesDireita.add(btnPesquisar);
			pnlAcoesDireita.add(btnAdd);

			JPanel norteBody = new JPanel(new BorderLayout());
			norteBody.add(lblTabTitle, BorderLayout.WEST);
			norteBody.add(pnlAcoesDireita, BorderLayout.EAST);

        		lblTabTitle.setFont(new Font("Arial", Font.BOLD, 16));
        		pnlTabela.add(norteBody, BorderLayout.NORTH);
			
			String colunas[] = {"ID", "Nome", "Acção"};
			model = new DefaultTableModel(colunas, 0)
			{
				@Override
				public boolean isCellEditable(int row, int collumn)
				{
					return false;
				}
			};
		
			table = new JTable(model);
			
			table.addMouseListener( new MouseAdapter() {
				public void mouseClicked(MouseEvent e)
				{
					coluna = table.columnAtPoint(e.getPoint());
					linha = table.rowAtPoint(e.getPoint());
					
					if(coluna == 2 && linha != -1)
					{
						String idCategoria = table.getValueAt(linha, 0).toString();
						String nomeCategoria = table.getValueAt(linha, 1).toString();

						app.container.add(new CategoriaForm(app, idCategoria, nomeCategoria), "CategoriaForm2");

						app.cardLayout.show(app.container, "CategoriaForm2");
					}
				}
			});

			table.setRowHeight(30);
			table.setGridColor(new Color(230, 230, 230));
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

			pnlTabela.add(scrollPane, BorderLayout.CENTER);
			body.add(pnlTabela, BorderLayout.CENTER);

			this.setLayout(new BorderLayout());
			this.add(body, BorderLayout.CENTER);

			carregarDados();

		}


		public void carregarDados()
		{
		   if(model != null)
			model.setRowCount(0);

		    try
		    {
		        CategoriaModelo temp_model = new CategoriaModelo();
			CategoriaFile file = new CategoriaFile(temp_model);

			//Total
                        EstudanteFile est_model_file = new EstudanteFile(new EstudanteModelo());
                        CertificadoFile cert_model_file = new CertificadoFile(new CertificadoModelo());

			pnlCards.removeAll();
			pnlCards.add(criarCardDash("Total de Certificados", String.valueOf(cert_model_file.getNregistos()), "+12% este mês"));
			pnlCards.add(criarCardDash("Alunos Cadastrados", String.valueOf(est_model_file.getNregistos()), "Ativos no sistema"));
			pnlCards.add(criarCardDash("Categorias Ativas", String.valueOf(file.getNregistos()), "Operacionais"));


			List<CategoriaModelo> 	categorias = file.listarCategoriasProntas();

			for (CategoriaModelo categoria : categorias)
			{
				model.addRow(
                                	new Object[]{
						categoria.id,
                                    		categoria.nome.toStringEliminatingSpaces(),
                                    		"<html><a href='' style='color: #1e90ff; font-weight: bold; text-decoration: none;'>Editar</a></html>",
                                   	}

                            	);

			}


		        //model.fireTableDataChanged();
			//model.fireTableStructureChanged();
			//table.removeAll();
			model.fireTableDataChanged();
			this.revalidate();
			this.repaint();

			//Cards
			//pnlCards.removeAll();
                       	pnlCards.revalidate();
                       	pnlCards.repaint();


		    }
		    catch(Exception exc)
		    {
		        System.out.println("Erro no alinhamento do fluxo: " + exc.getMessage());
		    }
		}

		// Filtra e exibe os dados com base na String pesquisada
		public void pesquisarDados(String termoBusca)
		{
			// Se o campo estiver vazio, recarrega a tabela completa
			if (termoBusca == null || termoBusca.trim().isEmpty()) {
				carregarDados();
				return;
			}

			if(model != null)
				model.setRowCount(0);

			try
			{
				CategoriaModelo temp_model = new CategoriaModelo();
				CategoriaFile file = new CategoriaFile(temp_model);

				List<CategoriaModelo> categorias = file.listarCategoriasProntas();

				for (CategoriaModelo categoria : categorias)
				{
					String nomeCompleto = categoria.nome.toStringEliminatingSpaces();

					if (nomeCompleto.toLowerCase().contains(termoBusca.toLowerCase().trim())) {
						model.addRow(
							new Object[]{
								categoria.id,
								nomeCompleto,
								"<html><a href='' style='color: #1e90ff; font-weight: bold; text-decoration: none;'>Editar</a></html>",
							}
						);
					}
				}
				model.fireTableDataChanged();
			}
			catch(Exception exc)
			{
				System.out.println("Erro na pesquisa: " + exc.getMessage());
			}
		}


		private JPanel criarCardDash(String titulo, String valor, String sub) 
		{
		        JPanel card = new JPanel(new BorderLayout());
		        card.setBackground(Color.WHITE);
		        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		         
		        JLabel lblTit = new JLabel(titulo);
			 lblTit.setForeground(Color.GRAY);
		        lblTit.setFont(new Font("Arial", Font.PLAIN, 12));
		        
		        JLabel lblVal = new JLabel(valor);
		        lblVal.setFont(new Font("Arial", Font.BOLD, 24));
		        lblVal.setForeground(new Color(24, 43, 73));
		        
		        JLabel lblSub = new JLabel(sub);
		        lblSub.setForeground(new Color(40, 167, 69));
		        lblSub.setFont(new Font("Arial", Font.PLAIN, 11));

		        card.add(lblTit, BorderLayout.NORTH);
		        card.add(lblVal, BorderLayout.CENTER);
		        card.add(lblSub, BorderLayout.SOUTH);
		        return card;
		}

}
