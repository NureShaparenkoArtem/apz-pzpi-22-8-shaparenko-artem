abstract class DataMiner {
    public final void mine() {
        openFile();
        extractData();
        processData();
        closeFile();
    }
    abstract void openFile();
    abstract void extractData();
    void processData() {
        System.out.println("Обробка даних...");
    }
    abstract void closeFile();
}

class PdfMiner extends DataMiner {
    void openFile() { System.out.println("Відкриття PDF-файлу"); }
    void extractData() { System.out.println("Видобування даних з PDF"); }
    void closeFile() { System.out.println("Закриття PDF-файлу"); }
}

class DocMiner extends DataMiner {
    void openFile() { System.out.println("Відкриття DOC-файлу"); }
    void extractData() { System.out.println("Видобування даних з DOC"); }
    void closeFile() { System.out.println("Закриття DOC-файлу"); }
}
