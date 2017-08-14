package eu.forapp.fit.service;

import eu.forapp.exception.ForAppException;

import java.io.IOException;

class ImportServiceMetaStockTest {
    private String root = "z:/stooq/data";
    //private String root = "c:/box/download/stooq/ms/daily";
    //private String root = "c:\\box\\download\\stooq\\daily";

    //@Test
    public void importDatabase() throws IOException, ForAppException {
        ShareService shareService = new ShareService();
//        ImportServiceMetaStock importServiceMetaStock = new ImportServiceMetaStock(root, shareService);
//        importServiceMetaStock.execute();
    }

}