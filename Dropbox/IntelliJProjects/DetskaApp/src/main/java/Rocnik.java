public enum Rocnik {
    PRVY("Prvý ročník"),
    DRUHY("Druhý ročník"),
    TRETI("Tretí ročník"),
    STVRTY("Štvrtý ročník");

    private final String popis;
    Rocnik(String popis) {
        this.popis = popis;
    }
    public String getPopis() {
        return popis;
    }
}


