package eu.forapp.fit.service;

import eu.forapp.exception.ForAppException;
import eu.forapp.fit.enums.ShareType;
import eu.forapp.fit.pojo.Context;
import eu.forapp.util.CountryCurrency;
import eu.forapp.util.ValueCsvReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class ImportServiceStooqCsv {
    private static final String NASDAQ = "nasdaq";
    private static final String NYSE = "nyse";

    private String rootFolder;
    private ShareService shareService;

    ImportServiceStooqCsv(String rootFolder, ShareService shareService) {
        this.rootFolder = rootFolder;
        this.shareService = shareService;
    }

    void execute() throws ForAppException {
        try {
            shareService.pompCountryAndCurrency();

            read("world/indices", ShareType.Indicies, null);

            read("world/currencies/major", ShareType.Currencies, null);
            read("world/currencies/other", ShareType.Currencies, null);

            read("world/bonds", ShareType.Bonds, null);

            read("us/nyse etfs", ShareType.Etfs, NYSE);
            read("us/nyse stocks/1", ShareType.Stocks, NYSE);
            read("us/nyse stocks/2", ShareType.Stocks, NYSE);
            read("us/nysemkt stocks", ShareType.Stocks, NYSE);
            read("us/nysemkt etfs", ShareType.Etfs, NYSE);
            read("us/nasdaq etfs", ShareType.Etfs, NASDAQ);
            read("us/nasdaq stocks/1", ShareType.Stocks, NASDAQ);
            read("us/nasdaq stocks/2", ShareType.Stocks, NASDAQ);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ForAppException(e, "ImpoerService.execute() error.");
        } catch (ForAppException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public ShareService getShareService() {
        return shareService;
    }

    private void read(String folder, ShareType shareType, String marketName) throws IOException, ForAppException {
        System.out.println();       // TODO usunąć w przyszłości
        System.out.println(folder);
        String directoryName = buildDirecortyName(folder);
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream( Paths.get( directoryName));
        for( Path path : directoryStream){
            read(path, shareType, marketName);
        }
    }

    private void read(Path path, ShareType shareType, String marketName) throws FileNotFoundException, ForAppException {
        String fileName = path.getFileName().toString();
        Context topContext = new Context();
        Context bottomContext = new Context();
        fillContext(fileName, shareType, marketName, topContext, bottomContext);
        read(path, topContext, bottomContext);
    }

    private void read(Path path, Context topContext, Context bottomContext) throws FileNotFoundException, ForAppException {
        //System.out.print(path.getFileName().toString());    // TODO usunąć wydruk później
        ValueCsvReader valueCsvReader = new ValueCsvReader(path);
        shareService.addValue(topContext, bottomContext, valueCsvReader.read());
    }

    private void fillContext(String fileName, ShareType shareType, String marketName, Context topContext, Context bottomContext) {
        switch (shareType){
            case Etfs:
            case Stocks:
                assignEtfsStockContext(fileName, shareType, marketName, topContext, bottomContext);
                break;
            case Bonds:
                assignBondsContext(fileName, shareType, topContext, bottomContext);
                break;
            case Commodities:
                assignCommoditiesContext(fileName, marketName, topContext, bottomContext);
                break;
            case Currencies:
                assignCurrenciesContext(fileName, topContext, bottomContext);
                break;
            case Indicies:
                assignIndicatesContext(fileName, shareType, topContext, bottomContext);
                break;
        }
    }

    private void assignIndicatesContext(String fileName, ShareType shareType, Context topContext, Context bottomContext) {
        String share = fileName.substring(0, fileName.lastIndexOf("."));
        topContext.shareType = ShareType.Indicies;
        topContext.share = share;
        bottomContext.shareType = ShareType.Currencies;
        bottomContext.share = "POINT";   //currencyForIndex(share);
    }

    private void assignCurrenciesContext(String fileName, Context topContext, Context bottomContext) {
        topContext.share = currencyFromName(fileName, true);
        topContext.shareType = ShareType.Currencies;
        bottomContext.share = currencyFromName(fileName, false);
        bottomContext.shareType = ShareType.Currencies;
    }

    private void assignCommoditiesContext(String fileName, String marketName, Context topContext, Context bottomContext) {
    }

    private void assignBondsContext(String fileName, ShareType shareType, Context topContext, Context bottomContext) {
        String country = findCountry(fileName, shareType);
        String share = fileName.substring(0, fileName.lastIndexOf("."));

        topContext.shareType = shareType;
        topContext.country = country;
        topContext.share = share;
        bottomContext.shareType = ShareType.Currencies;
        bottomContext.share = CountryCurrency.currencyCode(country);
    }

    private void assignEtfsStockContext(String fileName, ShareType shareType, String marketName, Context topContext, Context bottomContext) {
        String country = findCountry(fileName, shareType);
        String share = fileName.substring(0, fileName.lastIndexOf("."));

        topContext.shareType = shareType;
        topContext.country = country;
        topContext.market = marketName;
        topContext.share = share;
        bottomContext.shareType = ShareType.Currencies;
        bottomContext.share = CountryCurrency.currencyCode(country);
    }

    private String findCountry(String fileName, ShareType shareType) {
        String[] names = fileName.split(Pattern.quote("."));
        int nameCount = names.length;
        switch (shareType){
            case Etfs:
            case Stocks:
                if(nameCount >= 3) return names[nameCount - 2];
            case Bonds:
                String name = names[0];
                int length = name.length();
                return name.substring(length - 3, length - 1);
            case Currencies:
                return null;
        }
        return null;
    }

    private String currencyFromName(String fileName, boolean top){
        String[] names = fileName.split(Pattern.quote("."));
        String name = names[0];
        if(top){
            return name.substring(0, 3).toUpperCase();
        } else {
            return name.substring(3, name.length()).toUpperCase();
        }
    }

    private String buildDirecortyName(String folder) {
        return this.rootFolder + "/" + folder;
    }
}

