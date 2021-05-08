package br.com.testeenginv.teste.gateway.httpout.impl;

import br.com.testeenginv.teste.config.ConfigProperties;
import br.com.testeenginv.teste.domain.Company;
import br.com.testeenginv.teste.gateway.dataprovider.CompanyDataProvider;
import br.com.testeenginv.teste.gateway.httpout.FinanceProvider;
import br.com.testeenginv.teste.gateway.httpout.dto.FinanceDTO;
import br.com.testeenginv.teste.usecase.GetAllCompanies;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Named;
import java.io.IOException;
import java.util.*;

@Slf4j
@AllArgsConstructor
@Named
public class YahooFinance implements FinanceProvider {

    private final GetAllCompanies getAllCompanies;
    private final CompanyDataProvider companyDataProvider;
    private final ConfigProperties configProperties;

    @Override
    public List<Company> getTop10() {

        List<Company> companyList = getAllCompanies.execute();
        OkHttpClient client = new OkHttpClient();
        String companiesSymbols = "";
        List<String> listCodigo = new ArrayList<>();
        for (Company company : companyList) {
            Request request = new Request.Builder()
                    .url("https://apidojo-yahoo-finance-v1.p.rapidapi.com/auto-complete?q=" + company.getCodigo())
                    .get()
                    .addHeader("x-rapidapi-key", configProperties.getXRapidKey())
                    .addHeader("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
                    .build();
            try {
                Response response = client.newCall(request).execute();
                JSONObject json = new JSONObject(response.body().string());
                companiesSymbols = companiesSymbols + json.getJSONArray("quotes").getJSONObject(0).get("symbol") + ",";
            } catch (IOException ioException) {
                log.error("Erro chamada http {} {}", ioException.getClass(), ioException.getLocalizedMessage());
            }catch (JSONException jsonException){
                log.error("Erro json {} {}", jsonException.getClass(), jsonException.getLocalizedMessage());
            }
        }

        Request request = new Request.Builder()
                .url("https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/v2/get-quotes?region=US&symbols=" + companiesSymbols.substring(0, companiesSymbols.length() - 1))
                .get()
                .addHeader("x-rapidapi-key", configProperties.getXRapidKey())
                .addHeader("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject json = new JSONObject(response.body().string());
            JSONArray jsonArray = json.getJSONObject("quoteResponse").getJSONArray("result");
            List<FinanceDTO> companiesAcao = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                FinanceDTO financeDTO = FinanceDTO.builder()
                        .symbol(jsonArray.getJSONObject(i).getString("symbol")
                                .trim().replaceAll(" +", "").replaceAll("\\.SA", ""))
                        .regularMarketPrice(
                                Double.parseDouble(jsonArray.getJSONObject(i).get("regularMarketPrice").toString()))
                        .build();
                companiesAcao.add(financeDTO);
            }
            Collections.sort(companiesAcao,
                    (FinanceDTO item2, FinanceDTO item1)
                            -> item1.getRegularMarketPrice().compareTo(item2.getRegularMarketPrice()));

            companiesAcao.stream().limit(10).forEach(financeDTO -> {
                listCodigo.add(financeDTO.getSymbol());
            });

        } catch (IOException e) {
            log.error("Erro chamada http {} {}", e.getClass(), e.getLocalizedMessage());
        }catch (JSONException jsonException){
            log.error("Erro json {} {}", jsonException.getClass(), jsonException.getLocalizedMessage());
        }
        return companyDataProvider.findByCodigoIn(listCodigo);
    }


}
