package Kniha;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Knihovnica
{	
	private Connection conn;

    public Knihovnica(Connection conn) {
        this.conn = conn;
        }
	private ArrayList<Kniha> knihy;

    public Knihovnica(){this.knihy = new ArrayList<>();}
    
    public void addKniha(Kniha kniha) {knihy.add(kniha);}

    public void editKniha(String NazovKnihy, String[] Autori, int RokVydania, boolean Dostupnost)
    {
        for (Kniha kniha : knihy)
        {
            if (kniha.getNazovKnihy().equals(NazovKnihy))
            {
                kniha.setDostupnost(Dostupnost);
                break;
            }
        }
    }

    public void deleteKniha(String NazovKnihy)
    {
    	knihy.removeIf(kniha -> kniha.getNazovKnihy().equals(NazovKnihy));
    }


    public void PozicKniha(String NazovKnihy, boolean Dostupnost)
    {
        for (Kniha kniha : knihy)
        {
            if (kniha.getNazovKnihy().equals(NazovKnihy))
            {
                kniha.setDostupnost(Dostupnost);
                break;
            }
        }
    }

    public void listKnihy()
    {
        Collections.sort(knihy, Comparator.comparing(Kniha::getNazovKnihy));
        for (Kniha kniha : knihy)
        {
            System.out.print(    "NazovKnihy: " 	+ kniha.getNazovKnihy()
            					+"|Autori: " 		+ Arrays.toString(kniha.getAutori())
            					+"|Rok vydania: " 	+ kniha.getRokVydania()
            					+"|Dostupnost: " 	+ (kniha.isDostupnost() ? "Dostupna" : "Pozicana"));

            if (kniha instanceof Roman)
            {
            	System.out.println("|Zaner: " + ((Roman) kniha).getZaner());}
            else if (kniha instanceof Ucebnica)
            {
                System.out.println("|Rocnik: " + ((Ucebnica) kniha).getRocnik());
            }
        }
    }

    public void searchKniha(String NazovKnihy) {
        for (Kniha kniha : knihy)
        {
            if (kniha.getNazovKnihy().equals(NazovKnihy))
            {
            	System.out.print(    "NazovKnihy: " 	+ kniha.getNazovKnihy()
									+"|Autori: " 		+ Arrays.toString(kniha.getAutori())
									+"|Rok vydania: " 	+ kniha.getRokVydania()
									+"|Dostupnost: " 	+ (kniha.isDostupnost() ? "Dostupna" : "Pozicana"));

            	if (kniha instanceof Roman)
            	{
            		System.out.print("|Zaner: " + ((Roman) kniha).getZaner());}
            	else if (kniha instanceof Ucebnica)
            	{
            		System.out.println("|Rocnik: " + ((Ucebnica) kniha).getRocnik());
            	}
            }
        }
        System.out.println("Kniha nenajdena.");
    }

    public void listKnihyByAutor(String NazovAutora)
    {
        ArrayList<Kniha> KnihyAutora = new ArrayList<>();
        for (Kniha kniha : knihy)
        {
            for (String autor : kniha.getAutori())
            {
                if (autor.equals(NazovAutora))
                {
                	KnihyAutora.add(kniha);
                    break;
                }
            }
        }
        Collections.sort(KnihyAutora, Comparator.comparingInt(Kniha::getRokVydania));
        for (Kniha kniha : KnihyAutora)
        {
        	System.out.print(    "NazovKnihy: " 	+ kniha.getNazovKnihy()
								+"|Autori: " 		+ Arrays.toString(kniha.getAutori())
								+"|Rok vydania: " 	+ kniha.getRokVydania()
								+"|Dostupnost: " 	+ (kniha.isDostupnost() ? "Dostupna" : "Pozicana"));

        	if (kniha instanceof Roman)
        	{
        		System.out.print("|Zaner: " + ((Roman) kniha).getZaner());}
        	else if (kniha instanceof Ucebnica)
        	{
        		System.out.println("|Rocnik: " + ((Ucebnica) kniha).getRocnik());
        	}
        }
    }


    public void listKnihyByZaner(String zaner)
    {
        for (Kniha kniha : knihy)
        {
            if (kniha instanceof Roman && ((Roman) kniha).getZaner().equals(zaner))
            {
            	System.out.print(    "NazovKnihy: " 	+ kniha.getNazovKnihy()
    								+"|Autori: " 		+ Arrays.toString(kniha.getAutori())
    								+"|Rok vydania: " 	+ kniha.getRokVydania()
    								+"|Dostupnost: " 	+ (kniha.isDostupnost() ? "Dostupna" : "Pozicana"));
            }
        }
    }

    public void listPozicaneKnihy()
    {
        for (Kniha kniha : knihy)
        {
            if (!kniha.isDostupnost())
            {
                if (kniha instanceof Roman)
                {
                	System.out.print(    "NazovKnihy: " 	+ kniha.getNazovKnihy()
										+"|Autori: " 		+ Arrays.toString(kniha.getAutori())
										+"|Rok vydania: " 	+ kniha.getRokVydania()
										+"|Typ: Roman");
                }
                else if (kniha instanceof Ucebnica)
                {
                	System.out.print(    "NazovKnihy: " 	+ kniha.getNazovKnihy()
										+"|Autori: " 		+ Arrays.toString(kniha.getAutori())
										+"|Rok vydania: " 	+ kniha.getRokVydania()
										+"|Typ: Ucebnica");
                }
            }
        }
    }


    public void saveDoSuboru(String NazovKnihy)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(NazovKnihy + ".txt"));
            for (Kniha kniha : knihy)
            {
                if (kniha.getNazovKnihy().equals(NazovKnihy))
                {
                    writer.write("NazovKnihy: " + kniha.getNazovKnihy()+ "\n");
                    String Tempautor = Arrays.toString(kniha.getAutori());
                    Tempautor = Tempautor.replaceAll("[()\\[\\]]", "");
                    
                    writer.write("Autori: " 	+ Tempautor+ "\n");
                    writer.write("RokVydania: " + kniha.getRokVydania()+ "\n");
                    writer.write("Dostupnost: " + (kniha.isDostupnost() ? "Dostupna" : "Pozicana") + "\n");
                    if (kniha instanceof Roman)
                    {
                        writer.write("Zaner: " + ((Roman) kniha).getZaner() + "\n");
                    }
                    else if (kniha instanceof Ucebnica)
                    {
                        writer.write("Rocnik: " + ((Ucebnica) kniha).getRocnik() + "\n");
                    }
                    break;
                }
            }
            writer.close();
            System.out.println("Kniha ulozena ako: " + NazovKnihy + ".txt");
        } 
        catch (IOException e)
        {
            System.out.println("Nepodarilo sa ulozit knihu do suboru.");
            e.printStackTrace();
        }
    }


    public void loadZoSuboru(String filename)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename+".txt"));
            String NazovKnihy = null;
            String[] Autori = null;
            int RokVydania = 0;
            boolean Dostupnost = false;
            String zaner = null;
            int rocnik = 0;

            String line;
            while ((line = reader.readLine()) != null) 
            {
                if 		(line.startsWith("NazovKnihy: " )) {NazovKnihy = line.substring(12);}
                else if (line.startsWith("Autori: "		)) {Autori = line.substring(8).split(", ");}	
                else if (line.startsWith("RokVydania: " )) {RokVydania = Integer.parseInt(line.substring(12));}
                else if (line.startsWith("Dostupnost: " )) {Dostupnost = line.substring(12).equals("Dostupnost");}
                else if (line.startsWith("Zaner: "		)) {zaner = line.substring(7);}
                else if (line.startsWith("\"Rocnik: "	)) {rocnik = Integer.parseInt(line.substring(8));}
            }
            reader.close();

            if (zaner != null)
            {
                addKniha(new Roman(NazovKnihy, Autori, RokVydania, Dostupnost, zaner));
            } 
            else
            {
                addKniha(new Ucebnica(NazovKnihy, Autori, RokVydania, Dostupnost, rocnik));
            }
            System.out.println("Nacitana kniha: " + NazovKnihy);
        }
        catch (IOException e)
        {
            System.out.println("Nepodarilo sa nacitat knihu zo suboru.");
            e.printStackTrace();
        }
    }
    
    public void loadDatabaza() throws SQLException
    {
        String sql = "SELECT * FROM knihy";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next())
            {
                String nazov = rs.getString("nazov");
                String[] autory = rs.getString("autory").split(", ");
                int rok = rs.getInt("rok");
                boolean dostupnost = rs.getBoolean("dostupnost");
                String zaner = rs.getString("zaner");
                int rocnik = rs.getInt("rocnik");
                if (zaner != null)
                {
                    addKniha(new Roman(nazov, autory, rok, dostupnost, zaner));
                } 
                else 
                {
                    addKniha(new Ucebnica(nazov, autory, rok, dostupnost, rocnik));
                }
            }
        }
    }
    
    public void saveDatabaza()
    {
    	
        String deleteSql = "DELETE FROM knihy";
        String insertSql = "INSERT INTO knihy (nazov, autory, rok, dostupnost, zaner, rocnik) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
             PreparedStatement insertStmt = conn.prepareStatement(insertSql))
        {


            deleteStmt.executeUpdate();


            for (Kniha kniha : knihy)
            {
                insertStmt.setString(1, kniha.getNazovKnihy());
                insertStmt.setString(2, String.join(", ", kniha.getAutori()));
                insertStmt.setInt(3, kniha.getRokVydania());
                insertStmt.setBoolean(4, kniha.isDostupnost());
                if (kniha instanceof Roman)
                {
                    insertStmt.setString(5, ((Roman) kniha).getZaner());
                    insertStmt.setNull(6, Types.INTEGER);
                }
                else if (kniha instanceof Ucebnica)
                {
                    insertStmt.setNull(5, Types.VARCHAR);
                    insertStmt.setInt(6, ((Ucebnica) kniha).getRocnik());
                }
                insertStmt.executeUpdate();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}

