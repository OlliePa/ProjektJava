package Kniha;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;


public class Program
{
	public static void main(String[] args) throws SQLException
	{
		try (Connection conn = Databaza.getConnection())
		{
            Databaza.initializeDatabase();
            Knihovnica knihovnicaDB = new Knihovnica(conn);
            knihovnicaDB.loadDatabaza();
            
		
        Scanner scanner = new Scanner(System.in);
        Knihovnica knihovnica = new Knihovnica();

        while (true)
        {
            System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────┐");
        	System.out.println("│                              Vyberte jednu z moznosti:                               │");
            System.out.println("├──────────────────────────────────────────────────────────────────────────────────────┤");
            System.out.println("│1. pridat knihu    2. upravit knihu    3. odstranit knihu    4. vypozicat/vratit knihu│");
            System.out.println("├──────────────────────────────────────────────────────────────────────────────────────┤");
            System.out.println("│5. vypisat vsetky knihy                                   6. vyhladat knihu           │");
            System.out.println("│7. vypisat knihy podla autora                             8. vypisat knihy podla zanru│");
            System.out.println("│                              9. vypisat vypozicane knihy                             │");
            System.out.println("├──────────────────────────────────────────────────────────────────────────────────────┤");
            System.out.println("│10. ulozit kniznicu do suboru                           11. nacitat kniznicu zo suboru│");
            System.out.println("├──────────────────────────────────────────────────────────────────────────────────────┤");
            System.out.println("│0. Exit                                                                               │");
            System.out.println("└──────────────────────────────────────────────────────────────────────────────────────┘");
            System.out.println(" Vyberte moznost: ");
            
            int moznost = scanner.nextInt();scanner.nextLine();
            switch (moznost)
            {
                case 1:
                    System.out.print("Vlozte typ knihy (1 = Roman, 2 = Ucebnica): ");
                    int TypKnihy = scanner.nextInt();
                    scanner.nextLine();
                    
                    System.out.print("Vlozte nazov knihy");
                    String Nazov_Knihy = scanner.nextLine();
                    
                    System.out.print("Vlozte autorov oddelenych ciarkov: ");
                    String[] Autori = scanner.nextLine().split(",");
                    
                    System.out.print("Rok vydania knihy: ");
                    int RokVydania = scanner.nextInt();
                    scanner.nextLine();
                    
                    System.out.print("Je tato kniha dostupna? (true/false): ");
                    boolean Dostupnost = scanner.nextBoolean();
                    scanner.nextLine();
                    
                    if (TypKnihy == 1)
                    {
                        System.out.print("Vlozte zaner romanu: ");
                        String Zaner = scanner.nextLine();
                        knihovnica.addKniha(new Roman(Nazov_Knihy, Autori, RokVydania, Dostupnost, Zaner));
                        
                    } else if (TypKnihy == 2) {
                        System.out.print("Vlozte vhodny rocnik: ");
                        int Rocnik = scanner.nextInt();
                        scanner.nextLine();
                        knihovnica.addKniha(new Ucebnica(Nazov_Knihy, Autori, RokVydania, Dostupnost, Rocnik));
                    } else {
                        System.out.println("Zly typ knihy.");
                    }
                    break;
                    
                case 2:
                    System.out.print("Vlozte nazov knihy ktoru chcete editovat: ");
                    String NazovKnihyEdit = scanner.nextLine();
                    System.out.println("Co chcete upravit?");
                    System.out.println("1. Autora/Autorov");
                    System.out.println("2. Rok vydania");
                    System.out.println("3. Dostupnost");
                    moznost = scanner.nextInt();
                    scanner.nextLine();
                    switch (moznost) {
                        case 1:
                            System.out.print("Vlozte autorov oddelenych ciarkov: ");
                            String[] newAutori = scanner.nextLine().split(", ");
                            knihovnica.editKniha(NazovKnihyEdit, newAutori, -1, true);
                            break;
                            
                        case 2:
                            System.out.print("Rok vydania knihy: ");
                            int newRokVydania = scanner.nextInt();
                            scanner.nextLine();
                            knihovnica.editKniha(NazovKnihyEdit, null, newRokVydania, true);
                            break;
                            
                        case 3:
                            System.out.print("Je tato kniha dostupna? (true/false): ");
                            boolean newDostupnost = scanner.nextBoolean();
                            scanner.nextLine();
                            knihovnica.editKniha(NazovKnihyEdit, null, -1, newDostupnost);
                            break;
                            
                        default:
                            System.out.println("Neplatna volba");
                            break;
                    }
                    break;
                    
                case 3:
                    System.out.print("Vlozte nazov knihy ktoru chcete odstranit: ");
                    String NazovKnihyDelete = scanner.nextLine();
                    knihovnica.deleteKniha(NazovKnihyDelete);
                    break;
                    
                case 4:
                    System.out.print("Vlozte nazov knihy na nastavenie: ");
                    String NazovKnihyPozicanie = scanner.nextLine();
                    System.out.print("Je tato kniha vypozicana (true/false): ");
                    boolean StatusPozicania = scanner.nextBoolean();
                    scanner.nextLine();
                    knihovnica.PozicKniha(NazovKnihyPozicanie, !StatusPozicania);
                    break;
                    
                case 5:
                    knihovnica.listKnihy();
                    break;
                    
                case 6:
                    System.out.print("Zadajte nazov knihy ktoru chcete hladat: ");
                    String searchNazov_Knihy = scanner.nextLine();
                    knihovnica.searchKniha(searchNazov_Knihy);
                    break;
                    
                case 7:
                    System.out.print("Zadajte meno autora: ");
                    String autorName = scanner.nextLine();
                    knihovnica.listKnihyByAutor(autorName);
                    break;
                    
                case 8:
                    System.out.print("Zadajte zaner: ");
                    String Zaner = scanner.nextLine();
                    knihovnica.listKnihyByZaner(Zaner);
                    break;
                case 9:
                	knihovnica.listPozicaneKnihy();
                	break;
                    
                case 10:
                    System.out.print("Zadajte nazov knihy ktoru chcete ulozit: ");
                    String saveNazovKnihy = scanner.nextLine();
                    knihovnica.saveDoSuboru(saveNazovKnihy);
                    break;
                    
                case 11:
                    System.out.print("Zadajte nazov suboru ktory chcete nacitat: ");
                    String filename = scanner.nextLine();
                    knihovnica.loadZoSuboru(filename);
                    break;
                    
                case 0:
                    knihovnica.saveDatabaza();
                    System.out.println("Program ukonceny.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Zla moznost, skuste znova.");
            }
        }
		} catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
	
