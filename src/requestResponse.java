import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;
import Modelos.ChangeApiRecord;
import Modelos.Monedas;
import Modelos.allCodes;
import Modelos.entornoVariables;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface requestResponse {
        static void Convert(String from, String to, double amount) throws IOException, InterruptedException {
            Scanner teclado = new Scanner(System.in);
            String URL = "https://v6.exchangerate-api.com/v6/"+entornoVariables.ApiKey+"/pair/"+from+"/"+to+"/"+amount;

            HttpClient cliente  = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                 .uri(URI.create(URL))
                 .build();

            HttpResponse<String> response = cliente
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String Json = response.body();

            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();
            ChangeApiRecord changedApi = gson.fromJson(Json, ChangeApiRecord.class);

            Monedas monedaConvertida = new Monedas(changedApi);

            monedaConvertida.setCantidadBase(amount);

            System.out.println(monedaConvertida);

            Monedas.historical.add(monedaConvertida);

            System.out.println("Presione enter para continuar");
            teclado.nextLine();

    }

        static void textConvert(String codeFrom, String codeTo, double amount) throws IOException, InterruptedException{
            try{
            if(codeFrom.length() > 3 || codeTo.length() > 3){
                System.out.println("Extension del codigo mayor a 3 letras, recordar que los codigos de cambio son unicamente de 3 letras");
            }else {
                String upperFrom = codeFrom.toUpperCase();
                String upperTo = codeTo.toUpperCase();

                String URL = "https://v6.exchangerate-api.com/v6/" + entornoVariables.ApiKey + "/pair/" + upperFrom + "/" + upperTo + "/" + amount;

                HttpClient cliente = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(URL))
                        .build();

                HttpResponse<String> response = cliente
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String Json = response.body();

                if(response.body().contains("unsupported-code")){
                    System.out.println("Codigo no reconocido, recuerda mirar los codigos disponibles en la opcion #3");
                }else{
                    Gson gson = new GsonBuilder()
                            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                            .create();

                    ChangeApiRecord changedApi = gson.fromJson(Json, ChangeApiRecord.class);

                    Monedas monedaConvertida = new Monedas(changedApi);

                    monedaConvertida.setCantidadBase(amount);

                    Monedas.historical.add(monedaConvertida);

                    System.out.println(monedaConvertida);
                }



            }
        }catch (RuntimeException e){
                System.out.println("Ocurrio una excepcion" + e.getMessage());
            }



    }

    static void requestAll() throws IOException, InterruptedException {
            String URL = "https://v6.exchangerate-api.com/v6/"+entornoVariables.ApiKey+"/codes";

            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .build();

            HttpResponse<String> response = cliente
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String Json = response.body();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        allCodes changedApi = gson.fromJson(Json, allCodes.class);
        List<List<String>> monedas = changedApi.supported_codes();
        for (List<String> i : monedas) {
            String codigo = i.get(0);
            String nombre = i.get(1);
            System.out.println("- " + codigo + ": " + nombre);
        }


    }
}
