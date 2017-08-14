package eu.forapp.fit.service;

import eu.forapp.exception.ForAppException;
import eu.forapp.fit.db.PerstStorage;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ImportServiceStooqCsvTest {
    //private String folderRoot = "z:/stooq/data";
    private String folderRoot = "c:/box/download/stooq/daily";

    @Test
    void importDatabase() throws IOException, ForAppException {
        ShareService shareService = new ShareService();
        ImportServiceStooqCsv importServiceStooqCsv = new ImportServiceStooqCsv(folderRoot, shareService);
        //importServiceStooqCsv.execute();
        PerstStorage.close();
    }
}