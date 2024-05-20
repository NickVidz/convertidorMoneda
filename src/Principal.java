import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal{
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner teclado = new Scanner(System.in);
        boolean desition =  true;
        boolean subDesition = true;
        String codeIn;
        String codeTo;
        double amountDecimal;
        int amount ;
        int respuesta;
        do{
            try{
            System.out.println("*********************************");
            System.out.println("***** CONVERTIDOR DE MONEDA *****");
            System.out.println("1. Conversiones mas comunes");
            System.out.println("2. Ingresa la moneda que desees");
            System.out.println("3. Monedas soportadas");
            System.out.println("4. Salir.");
            System.out.println("********************************");
            respuesta = teclado.nextInt();
                switch (respuesta){
                    case 1:
                         do{
                            System.out.println("*****************************");
                            System.out.println("Elige la conversion que desees");
                            System.out.println("1. Peso Colombiano (COP) -------> Dolar Estadounidense (USD) ");
                            System.out.println("2. Dolar Estadounidense (USD) ------> Peso Colombiano (COP) ");
                            System.out.println("3. Peso Argentino (ARS) ------> Peso Colombiano (COP) ");
                            System.out.println("4. Peso Colombiano ------> Peso Argentino (ARS) ");
                            System.out.println("5. Salir");
                            System.out.println("*****************************");
                            respuesta = teclado.nextInt();
                            switch (respuesta){
                                case 1:
                                    System.out.println("Ingrese la cantidad que quiere convertir");
                                    amount = teclado.nextInt();
                                    amountDecimal = amount;

                                    requestResponse.Convert("COP", "USD", amountDecimal);
                                    break;
                                case 2:
                                    System.out.println("Ingrese la cantidad que quiere convertir");
                                    amount = teclado.nextInt();
                                    amountDecimal = amount;

                                    requestResponse.Convert("USD", "COP", amountDecimal);
                                    break;
                                case 3:
                                    System.out.println("Ingrese la cantidad que quiere convertir");
                                    amount = teclado.nextInt();
                                    amountDecimal = amount;

                                    requestResponse.Convert("ARS", "COP", amountDecimal);
                                    break;
                                case 4:
                                    System.out.println("Ingrese la cantidad que quiere convertir");
                                    amount = teclado.nextInt();
                                    amountDecimal = amount;

                                    requestResponse.Convert("COP", "ARS", amountDecimal);
                                    break;
                                case 5:
                                    subDesition = false;
                                    break;
                                default:
                                    System.out.println("Opcion invalida, intenta nuevamente");
                                    break;
                                }
                         }while (subDesition);
                         break;

                    case 2:
                        System.out.println("Ingresa el codigo de la moneda base");
                        codeIn = teclado.next();
                        teclado.nextLine();
                        System.out.println("Ingresa el codigo de la moneda a convertir");
                        codeTo = teclado.next();
                        teclado.nextLine();
                        System.out.println("Ingresa la cantidad a convertir");
                        amount = teclado.nextInt();
                        amountDecimal = amount;

                        if (codeIn.isEmpty() || codeTo.isEmpty()){
                            System.out.println("¡Ingresa todos los campos solicitados para hacer la conversion!");
                            System.out.println("Presiona enter para continuar");
                            teclado.nextLine();
                            break;
                        }else{
                            requestResponse.textConvert(codeIn, codeTo, amountDecimal);
                            break;
                        }

                    case 3:
                        requestResponse.requestAll();
                        break;
                    case 4:
                        desition = false;
                        break;
                }
            } catch (InputMismatchException e){
                System.out.println("¡Tipo de dato ingresado no valido, recuerda que debes ingresar numeros!");
                teclado.nextLine();



            } catch (RuntimeException e){
                System.out.println("Algo salio mal!" + e.getMessage());
                teclado.nextLine();
            }

        }while (desition);

        System.out.println("Gracias por usar nuestro convertidor de moneda");







    }
}