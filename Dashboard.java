/****************************************************************
Projecto de Fundamentos de Programação II;
Tema: Sistema de Gestão Certificados;
Nome: Viente Cosmo, N. 36479;
File Name: Analise.java;
Data: 05.06.2026.
*****************************************************************/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.RandomAccessFile;

import SwingComponents.*;

public class Dashboard extends JPanel
{

	JPanel painelEsquerdo;
	JPanel painelCentro;

	public Dashboard()
	{
		this.setLayout(new BorderLayout());
		
		painelEsquerdo = new Sidebar();
		
		painelCentro = new JPanel(new BorderLayout());
		painelCentro.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

		painelCentro.add(new Header(), BorderLayout.NORTH);
		painelCentro.add(new Body(), BorderLayout.CENTER);

		this.add(painelEsquerdo, BorderLayout.WEST);
		this.add(painelCentro, BorderLayout.CENTER);
	}

	class Body extends JPanel
	{
		
		private JButton btnAdd;
		private DefaultTableModel model;

		public Body()
		{
			JPanel body = new JPanel();
			body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
			body.setBackground(new Color(240, 242, 245));
        		body.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

			 // Cards indicadores
		        JPanel pnlCards = new JPanel(new GridLayout(1, 3, 20, 0));
		        pnlCards.setBackground(new Color(240, 242, 245));
		        //pnlCards.setMaximumSize(new Dimension(1100, 100));

		        pnlCards.add(criarCardDash("Total de Certificados", "1.284", "+12% este mês"));
		        pnlCards.add(criarCardDash("Alunos Cadastrados", "08", "Ativos no sistema"));
		        pnlCards.add(criarCardDash("Categorias Ativas", "1.284", "Operacionais"));
		        body.add(pnlCards);
		        body.add(Box.createVerticalStrut(25));
		
			//Tabela de emissões
			JPanel pnlTabela = new JPanel(new BorderLayout());
			pnlTabela.setBackground(Color.WHITE);
        		//pnlTabela.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		
			JLabel lblTabTitle = new JLabel("Certificados Recentes");
			btnAdd = new JButton("Adicionar um novo certificado");

			btnAdd.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						Apresentacao app = new Apresentacao();
						app.cardLayout.show(app.container, "FORM");
					}
				}
			);

			JPanel norteBody = new JPanel(new BorderLayout());
			norteBody.add(lblTabTitle, BorderLayout.WEST);
			norteBody.add(btnAdd, BorderLayout.EAST);

        		lblTabTitle.setFont(new Font("Arial", Font.BOLD, 16));
        		pnlTabela.add(norteBody, BorderLayout.NORTH);
			
			String colunas[] = {"Nome", "Nº Cert.", "Curso", "Data", "Status"};
			model = new DefaultTableModel(colunas, 0);
		
			/*CertificadoModelo modelo = new CertificadoModelo(
                                1,
                                "PROC-BST-2026-001",
                                "proc-bst-2026-001",
                                "01-01-2026",
                                "01-01-2026",
                                "01-01-2026",
                                "01-01-2026"
                        );

			model.addRow(new Object[]{
					modelo.getNumeroCertificado(), 
					modelo.getSlug(),
					modelo.getDataInicioCurso(),
					modelo.getDataFimCurso(),
					modelo.getDataEmissao()
				}
			);
       			model.addRow(new Object[]{"Vicente Cosmo Miguel", "PROC-HUET-2026-050", "Gestão de Segurança Offshore", "27 Jan 2026", "Pendente"});*/

			JTable table = new JTable(model);
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
		    try
		    {
		        RandomAccessFile file = new RandomAccessFile("certificados.dat", "r");
		        model.setRowCount(0); 
		        file.seek(0);

		        while(file.getFilePointer() < file.length())
		        {
		            CertificadoModelo modelo = new CertificadoModelo();
		            modelo.numeroCertificado = new StringBufferModelo(50);
		            StringBufferModelo slugTemp = new StringBufferModelo(60);
		            modelo.dataInicioCurso = new DataModelo();
		            modelo.dataFimCurso = new DataModelo();
		            modelo.dataEmissao = new DataModelo();
		            modelo.dataValidade = new DataModelo();

		            int idLido = file.readInt();
		            modelo.numeroCertificado.read(file);
		            slugTemp.read(file);
		            
		            modelo.dataInicioCurso.read(file); 
		            modelo.dataFimCurso.read(file);
		            modelo.dataEmissao.read(file);
		            modelo.dataValidade.read(file);

		            model.addRow(
		                new Object[]{
		                    modelo.numeroCertificado.toStringEliminatingSpaces(),
		                    modelo.dataInicioCurso.toString(),
		                    modelo.dataFimCurso.toString(),
		                    modelo.dataEmissao.toString(),
		                    modelo.dataValidade.toString()
		                }
		            );
		        }

		        file.close();
		        model.fireTableDataChanged();
		    }
		    catch(Exception exc)
		    {
		        System.out.println("Erro no alinhamento do fluxo: " + exc.getMessage());
		    }
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

	class Sidebar extends JPanel
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

			String menuItem[] = {"Certificados", "Categorias", "Alunos", "Configuraçoões"};
			for (String item : menuItem)
			{
				JButton btnMenu = new JButton(item);
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
	}

}
