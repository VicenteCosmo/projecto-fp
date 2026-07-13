/****************************************************************
Projecto de Fundamentos de Programação II;
Tema: Sistema de Gestão Certificados;
Nome: Viente Cosmo, N. 36479;
File Name: CertificadoFile.java;
Data: 05.06.2026.
****************************************************************/

import SwingComponents.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class CertificadoFile extends ObjectsFile
{
	CertificadoModelo modelo;
	
	public CertificadoFile(CertificadoModelo model)
	{
		super("Certificados.DAT", model);
		
		this.modelo = model;
	}
	
	//usado para mostrar o id automatico
	public int getNextID()
	{
		return getProximoCodigo();
	}
	public void salvarDados()
	{
		try
		{
			stream.seek( stream.length() );
			
			modelo.write(stream);
			
			//incrementa o id
			incrementarProximoCodigo();
			
			JOptionPane.showMessageDialog(null, "Registo do Certificado Adicionado com Sucesso");
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao tentar registrar o certificado.");
		}
	}
	
	//mostra todos os registos
	public void listarCertificados()
	{
		CertificadoModelo temp_model = new CertificadoModelo();
		String output = "Dados do Certificado\n\n";
		
		try
		{
			stream.seek( 4 );
					
			for (int i = 0; i < getNregistos(); i++)
			{
				temp_model.read(stream); 
				output += temp_model + "\n";
			}
//
//			 System.out.println(temp_model.toString());
			
			JTextArea area = new JTextArea(30, 20);
			area.setText( output );
			area.setEditable(false);	// nao permite editar os dados
			//area.setFocusable(false); nao permite editar nem seleccionar
			
			JOptionPane.showMessageDialog(null, new JScrollPane(area) );
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao tentar Listar os dados");
		}
	}

	//Retornar uma lista pronta dos certificados
	public List<CertificadoModelo> listarCertificadosProntos()
	{
		List<CertificadoModelo> lista = new ArrayList<>();
		
		try {
        		stream.seek(4);

        		for (int i = 0; i < getNregistos(); i++) 
			{
            			// Criar uma nova instância para cada registo, evitando duplicar referências
            			CertificadoModelo temp_model = new CertificadoModelo();
            			temp_model.read(stream); 
            			lista.add(temp_model);
        		}

    		} 
		catch(IOException ex) {
        		ex.printStackTrace();
        		JOptionPane.showMessageDialog(null, "Erro ao tentar ler os dados do arquivo.");
    		}

    		return lista;

	}

	//Pesquisar pelo id
        public CertificadoModelo pesquisarCertificadoPorId(int id)
        {

                try
                {

                        stream.seek( 4 );

                        for (int i = 0; i < getNregistos(); i++)
                        {
                                CertificadoModelo temp_model = new CertificadoModelo();
                                temp_model.read( stream );

                                if (temp_model.getId() == id)
                                        return temp_model;

                        }
                }
                catch(IOException ex)
                {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao tentar Pesquisar os dados");
                }

                return null;

        }

	//Por nome
        public CertificadoModelo pesquisarCertificadoPorNome(String nome)
        {

                try
                {

                        stream.seek( 4 );

                        for (int i = 0; i < getNregistos(); i++)
                        {
                                CertificadoModelo temp_model = new CertificadoModelo();
                                temp_model.read( stream );

                                if (temp_model.getNome().equals(nome.trim()))
                                        return temp_model;

                        }
                }
                catch(IOException ex)
                {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao tentar Pesquisar os dados");
                }

                return null;

        }

	//Por processo
        public CertificadoModelo pesquisarCertificadoPorProcesso(String nome)
        {

                try
                {

                        stream.seek( 4 );

                        for (int i = 0; i < getNregistos(); i++)
                        {
                                CertificadoModelo temp_model = new CertificadoModelo();
                                temp_model.read( stream );

                                if (temp_model.getNumeroCertificado().equals(nome.trim()))
                                        return temp_model;

                        }
                }
                catch(IOException ex)
                {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao tentar Pesquisar os dados");
                }

                return null;

        }


	//Atualizar
        public void atualizarDadosPorId(int id)
        {
                try
                {
                        stream.seek(4);

                        for(int i = 0; i < getNregistos(); i++)
                        {
                                long posicao = stream.getFilePointer();

                                CertificadoModelo temp_model = new CertificadoModelo();

                                temp_model.read(stream);

                                if(temp_model.getId() == id)
                                {
                                        stream.seek(posicao);
                                        modelo.write(stream);
                                        JOptionPane.showMessageDialog(null, "Registo do Estudante Atualizado com Sucesso");
                                        break;
                                }
                        }

                }
                catch(IOException ex)
                {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao tentar registrar o estudante.");
                }
        }

	
	public void pesquisarCertificadoPeloNumeroCerificado(String nomeProcurado)
	{
		CertificadoModelo temp_model = new CertificadoModelo();
		String output = "Resultado da Pesquisa do Nome: " + nomeProcurado + "\n\n";
		
		try
		{
			//coloca o cursor na posicao 4 do ficheiro
			stream.seek( 4 );
			
			for (int i = 0; i < getNregistos(); i++)
			{
				temp_model.read( stream );
				
				//equals(Ana, ana) //equalsIgnoreCase(Ana, ana);
				
				if (temp_model.getNumeroCertificado().equalsIgnoreCase(nomeProcurado))
					output += temp_model.toString() + "\n";
			}
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao tentar Pesquisar os dados");
		}
		
		JTextArea area = new JTextArea(20, 40);
		area.setText(output);
		area.setEditable(false);
		
		JOptionPane.showMessageDialog(null, new JScrollPane(area));
	}
}
