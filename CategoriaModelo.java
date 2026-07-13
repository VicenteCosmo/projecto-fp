/****************************************************************
Projecto de Fundamentos de Programação II;
Tema: Sistema de Gestão Certificados;
Nome: Viente Cosmo, N. 36479;
File Name: CategoriaModelo.java;
Data: 05.06.2026.
****************************************************************/

import SwingComponents.*;
import javax.swing.*;
import java.io.*;


	
public class CategoriaModelo implements RegistGeneric
{
	//atributos
	public int id;
	public StringBufferModelo nome;
	
	//constructores
	public CategoriaModelo()
	{
		id = 0;
		nome = new StringBufferModelo(50);
	}
	public CategoriaModelo(
		int id,
                String nome
	)
	{
		this.id = id;
                this.nome = new StringBufferModelo(nome, 50);
	}
	
	//metodos get
	public int getId()
	{
		return id;
	}
	public String getNome()
	{
		return nome.toStringEliminatingSpaces();
	}
	//metodos set
	public void setId(int newId)
	{
		this.id = newId;
	}
	public void setNome(String newNome)
	{
		this.nome = new StringBufferModelo(newNome, 50);
	}

	//metodo toString
	public String toString()
	{
		String str = "Dados do Modelo Categoria\n\n";

		str += "ID: " + this.getId() + "\n";
		str += "Nome do Estudante: " + this.getNome() + "\n";

		return str;
	}

	public long sizeof()
	{
		long sizeof = 0;
		
		try
		{
			sizeof = 4 + 100;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		return sizeof;

	}

	public void write(RandomAccessFile stream)
	{
		try
                {
                        stream.writeInt(id);
			nome.write(stream);
                }
                catch(IOException ex)
                {
                        ex.printStackTrace();
                }

	}

	public void read(RandomAccessFile stream)
        {
                try
                {
                        id = stream.readInt();
                        nome.read(stream);
                }
                catch(IOException ex)
                {
                        ex.printStackTrace();
                }

        }

	//Ligar o modelo ao ficheiro
	public void salvarDados()
	{
		CategoriaFile file = new CategoriaFile(this);
		file.salvarDados();
	}

	public void atualizarDadosPorId(int id)
        {
                CategoriaFile file = new CategoriaFile(this);
                file.atualizarDadosPorId(id);
        }


}
