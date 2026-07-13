/****************************************************************
Projecto de Fundamentos de Programação II;
Tema: Sistema de Gestão Certificados;
Nome: Viente Cosmo, N. 36479;
File Name: CategoriaFile.java;
Data: 05.06.2026.
****************************************************************/

import SwingComponents.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class CategoriaFile extends ObjectsFile
{
	CategoriaModelo modelo;
	
	public CategoriaFile(CategoriaModelo model)
	{
		super("Categorias.DAT", model);
		
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
			
			JOptionPane.showMessageDialog(null, "Registo da Categoria Adicionado com Sucesso");
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao tentar registrar a categoria.");
		}
	}
	
	//mostra todos os registos
	public void listarCategorias()
	{
		CategoriaModelo temp_model = new CategoriaModelo();
		String output = "Dados da Categoria\n\n";
		
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

	//Retornar uma lista pronta dos estudantes
	public List<CategoriaModelo> listarCategoriasProntas()
	{
		List<CategoriaModelo> lista = new ArrayList<>();
		
		try {
        		stream.seek(4);

        		for (int i = 0; i < getNregistos(); i++) 
			{
            			// Criar uma nova instância para cada registo, evitando duplicar referências
            			CategoriaModelo temp_model = new CategoriaModelo();
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
	public CategoriaModelo pesquisarCategoriaPorId(int id)
	{

		try
		{

			stream.seek( 4 );

			for (int i = 0; i < getNregistos(); i++)
			{
				CategoriaModelo temp_model = new CategoriaModelo();
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
	public CategoriaModelo pesquisarCategoriaPorNome(String nome)
        {

                try
                {

                        stream.seek( 4 );

                        for (int i = 0; i < getNregistos(); i++)
                        {
                               	CategoriaModelo temp_model = new CategoriaModelo();
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


	//Atualizar
	public void atualizarDadosPorId(int id)
	{

		try
		{
			stream.seek(4);
			
			for(int i = 0; i < getNregistos(); i++)
			{
				long posicao = stream.getFilePointer();

				CategoriaModelo temp_model = new CategoriaModelo();

				temp_model.read(stream);

				if(temp_model.getId() == id)
				{
					stream.seek(posicao);
					modelo.write(stream);
					
					JOptionPane.showMessageDialog(null, "Registo da Categoria Atualizado com Sucesso");

					break;
				}

			}

		}
		catch(IOException ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao tentar atualizar a categoria.");
		}
	}
}
