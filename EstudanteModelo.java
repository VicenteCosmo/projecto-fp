/****************************************************************
Projecto de Fundamentos de Programação II;
Tema: Sistema de Gestão Certificados;
Nome: Viente Cosmo, N. 36479;
File Name: EStudanteModelo.java;
Data: 05.06.2026.
****************************************************************/

import SwingComponents.*;
import javax.swing.*;
import java.io.*;


	
public class EstudanteModelo implements RegistGeneric
{
	//atributos
	public int id;
	public StringBufferModelo nome, bi;
	
	//constructores
	public EstudanteModelo()
	{
		id = 0;
		nome = new StringBufferModelo(50);
		bi = new StringBufferModelo(50);
	}
	public EstudanteModelo(
		int id,
                String nome,
                String bi
	)
	{
		this.id = id;
                this.nome = new StringBufferModelo(nome, 50);
                this.bi = new StringBufferModelo(bi, 50);
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
	public String getBI()
	{
		return bi.toStringEliminatingSpaces();
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
	public void setBI(String newBI)
	{
		this.bi = new StringBufferModelo(newBI, 50);
	}

	//metodo toString
	public String toString()
	{
		String str = "Dados do Modelo Estudante\n\n";

		str += "ID: " + this.getId() + "\n";
		str += "Nome do Estudante: " + this.getNome() + "\n";
		str += "BI do Estudante: " + getBI() + "\n";

		return str;
	}

	public long sizeof()
	{
		long sizeof = 0;
		
		try
		{
			sizeof = 4 + 100 + 100;
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
			bi.write(stream);
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
                        bi.read(stream);
                }
                catch(IOException ex)
                {
                        ex.printStackTrace();
                }

        }

	//Ligar o modelo ao ficheiro
	public void salvarDados()
	{
		EstudanteFile file = new EstudanteFile(this);
		file.salvarDados();
	}

	public void atualizarDadosPorId(int id)
        {
                EstudanteFile file = new EstudanteFile(this);
                file.atualizarDadosPorId(id);
        }


}
