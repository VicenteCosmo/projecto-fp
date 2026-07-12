/****************************************************************
Projecto de Fundamentos de Programação II;
Tema: Sistema de Gestão Certificados;
Nome: Viente Cosmo, N. 36479;
File Name: certificadosTabela.java;
Data: 05.06.2026.
*****************************************************************/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.RandomAccessFile;
import java.util.List;

class CertificadosTabela extends JPanel
{
		private Apresentacao app;
		
		private JButton btnAdd;
		private DefaultTableModel model;

		public CertificadosTabela(Apresentacao app)
		{
			
			this.app = app;

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

		    if(model != null)
			model.setRowCount(0);

		    try
		    {
		        CertificadoModelo temp_model = new CertificadoModelo();
			CertificadoFile file = new CertificadoFile(temp_model);

			List<CertificadoModelo> certificados = file.listarCertificadosProntos();

			for (CertificadoModelo certificado : certificados)
				model.addRow(
                                	new Object[]{
                                    		certificado.numeroCertificado.toStringEliminatingSpaces(),
                                    		certificado.dataInicioCurso.toString(),
                                    		certificado.dataFimCurso.toString(),
                                    		certificado.dataEmissao.toString(),
                                    		certificado.dataValidade.toString()
                                   	}
                            	);


		        model.fireTableDataChanged();
		    }
		    catch(Exception exc)
		    {
		        System.out.println("Erro no alinhamento do fluxo: " + exc.getMessage());
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
