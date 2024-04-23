package Kniha;

public class Kniha
	{
	    private String 	 NazovKnihy;
	    private String[] Autori;
	    private int 	 RokVydania;
	    private boolean  Dostupnost;

	    public Kniha(String NazovKnihy, String[] Autori, int RokVydania, boolean Dostupnost)
	    {
	        this.NazovKnihy = NazovKnihy;
	        this.Autori 	 = Autori;
	        this.RokVydania  = RokVydania;
	        this.Dostupnost  = Dostupnost;
	    }

	    public String 	getNazovKnihy(){return NazovKnihy;}
	    public String[] getAutori() 	{return Autori;}
	    public int 		getRokVydania()	{return RokVydania;}
	    public boolean 	isDostupnost()	{return Dostupnost;}

	    public void setDostupnost(boolean Dostupnost) {this.Dostupnost = Dostupnost;}
	}


	 class Roman extends Kniha
	 {
	    private String zaner;

	    public Roman(String NazovKnihy, String[] Autori, int RokVydania, boolean Dostupnost, String zaner)
	    {
	        super(NazovKnihy, Autori, RokVydania, Dostupnost);
	        this.zaner = zaner;
	    }

	    public String getZaner() {return zaner;}
	}
	 

	class Ucebnica extends Kniha
	{
	    private int Rocnik;

	    public Ucebnica(String NazovKnihy, String[] Autori, int RokVydania, boolean Dostupnost, int Rocnik)
	    {
	        super(NazovKnihy, Autori, RokVydania, Dostupnost);
	        this.Rocnik = Rocnik;
	    }

	    public int getRocnik() {return Rocnik;}
	}

