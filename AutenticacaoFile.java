/****************************************************************
Projecto de Fundamentos de Programação II;
Tema: Sistema de Gestão Certificados;
Nome: Viente Cosmo, N. 36479;
File Name: AutenticacaoFile.java;
Data: 05.06.2026.
****************************************************************/

import SwingComponents.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class AutenticacaoFile extends ObjectsFile
{
	AutenticacaoModelo modelo;
	
	public AutenticacaoFile(AutenticacaoModelo model)
	{
		super("Autenticacao.DAT", model);
		
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
			
			JOptionPane.showMessageDialog(null, "Autenticacao Adicionado com Sucesso");
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao tentar registrar a Autenticacao.");
		}
	}
	
	//Salva sem incrementar, para substituir os dados
	public void salvar()
        {
                try
                {
			stream.setLength(0);

                        stream.seek(4);

                        modelo.write(stream);

                        JOptionPane.showMessageDialog(null, "Autenticacao Adicionado com Sucesso");
                }
                catch(IOException ex)
                {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao tentar registrar a Autenticacao.");
                }
        }


	//Retornar uma lista pronta dos estudantes
	public List<AutenticacaoModelo> listarAutenticacao()
	{
		List<AutenticacaoModelo> lista = new ArrayList<>();
		
		try {
        		stream.seek(4);

        		for (int i = 0; i < getNregistos(); i++) 
			{
            			// Criar uma nova instância para cada registo, evitando duplicar referências
            			AutenticacaoModelo temp_model = new AutenticacaoModelo();
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
	public AutenticacaoModelo pesquisarAutenticacaoPorId(int id)
	{

		try
		{

			stream.seek( 4 );

			for (int i = 0; i < getNregistos(); i++)
			{
				AutenticacaoModelo temp_model = new AutenticacaoModelo();
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

	//Autenticar
	public boolean autenticar(String nome, String pass)
        {

                try
                {
                        stream.seek( 4 );

                        for (int i = 0; i < getNregistos(); i++)
                        {
                                AutenticacaoModelo temp_model = new AutenticacaoModelo();
                                temp_model.read( stream );

                                if (temp_model.getNome().equals(nome.trim()))
                                	if(temp_model.getPassword().equals(pass.trim()))
						return true;
				JOptionPane.showMessageDialog(null, "Username ou password incorresta!");

                        }
                }
                catch(IOException ex)
                {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao tentar Pesquisar os dados");
                }

                return false;

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

				AutenticacaoModelo temp_model = new AutenticacaoModelo();

				temp_model.read(stream);

				if(temp_model.getId() == id)
				{
					stream.seek(posicao);
					modelo.write(stream);
					JOptionPane.showMessageDialog(null, "Registo atualizado com Sucesso");
					break;
				}
			}

		}
		catch(IOException ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao tentar atualizar as credeencias.");
		}
	}

}
