package Fifth;

import java.io.*;
import java.nio.file.Path;

public class AuxFile implements Closeable {
    private static int indexing = 0;

    private Path pathToFile;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private int index;

    public AuxFile(String filePath) throws IOException {
        pathToFile = Path.of(filePath);
        dataInputStream = new DataInputStream(new FileInputStream(pathToFile.toFile()));
        dataOutputStream = new DataOutputStream(new FileOutputStream(pathToFile.toFile()));
        index = indexing++;
    }

    @Override
    public void close() throws IOException {
        dataInputStream.close();
        dataOutputStream.close();
    }
}
