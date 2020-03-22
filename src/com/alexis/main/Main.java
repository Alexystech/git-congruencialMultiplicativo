package com.alexis.main;

import test.CustomException;
import test.ValidacionesException;
import test.ValidacionOrdinariaException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Float>almacenR = new ArrayList<Float>();
    private static ArrayList<Integer>almacenZen = new ArrayList<Integer>();
    private static final InputStreamReader isr = new InputStreamReader(System.in);
    private static final BufferedReader br = new BufferedReader(isr);

    public static void main(String args[]) throws IOException{
        boolean switchMenu;
        int cantR = 0,semilla = 0,modalidadVal = 0,k = 0,g = 0;
        boolean modalidad = false;

        ValidacionesException exceptions = new ValidacionesException();
        ValidacionOrdinariaException exceptionOrdinario = new ValidacionOrdinariaException();

        do{
            try{
                switchMenu = true;
                System.out.println("ingresa la cantidad de r:");
                cantR = Integer.parseInt(br.readLine());

                try{
                    exceptionOrdinario.validarValor(cantR);
                }catch (CustomException exception){
                    exception.printStackTrace();
                    switchMenu = false;
                }
            }catch(Exception e){
                switchMenu = false;
                e.printStackTrace();
            }
        }while(!switchMenu);

        do{
            try{
                switchMenu = true;
                System.out.println("ingresa la semilla x0:");
                semilla = Integer.parseInt(br.readLine());

                try{
                    exceptions.validarValor(semilla);
                }catch(CustomException exception){
                    exception.printStackTrace();
                    switchMenu = false;
                }
            }catch(Exception e){
                switchMenu = false;
                e.printStackTrace();
            }
        }while(!switchMenu);

        do{
            try{
                switchMenu = true;
                System.out.println("modalidad, 1.- 5+8k    2.- 3+8k :");
                modalidadVal = Integer.parseInt(br.readLine());
                try{
                    exceptionOrdinario.validarValor(modalidadVal);
                }catch(CustomException exception){
                    exception.printStackTrace();
                    switchMenu = false;
                }
                if (modalidadVal == 1){
                    modalidad = true;
                }else{
                    modalidad = false;
                }
            }catch(Exception e){
                switchMenu = false;
                e.printStackTrace();
            }
        }while(!switchMenu);

        do{
            try{
                switchMenu = true;
                System.out.println("ingresa k:");
                k = Integer.parseInt(br.readLine());

                try{
                    exceptionOrdinario.validarValor(k);
                }catch(CustomException exception){
                    exception.printStackTrace();
                    switchMenu = false;
                }
            }catch(Exception e){
                switchMenu = false;
                e.printStackTrace();
            }
        }while(!switchMenu);

        do{
            try{
                System.out.println("ingresa g:");
                g = Integer.parseInt(br.readLine());

                try{
                    exceptionOrdinario.validarValor(g);
                }catch(CustomException exception){
                    exception.printStackTrace();
                    switchMenu = false;
                }
            }catch(Exception e){
                switchMenu = false;
                e.printStackTrace();
            }
        }while(!switchMenu);

        newR(cantR,modalidad,semilla,k,g);
        mostrarDatos();

    }

    private static void mostrarDatos(){
        for(int x = 0; x < almacenZen.size(); x++){
            System.out.println("x"+(x+1)+" = "+almacenZen.get(x)+" | r"+(x+1)+" = "+almacenR.get(x));
        }
    }

    private static float newR(int cantR,boolean modalidad,int semilla, int k, int g){
        if (cantR < 1){
            return 0;
        }else{
            almacenR.add((float)(newZen(semilla,modalidad,k,g)) / (modulo(g)-1));
            almacenZen.add(newZen(semilla,modalidad,k,g));
            return newR(cantR-1,modalidad,newZen(semilla,modalidad,k,g),k,g);
        }
    }

    private static int newZen(int semilla,boolean modalidad, int k, int g){
        int valueNewZen = (constanteMultiplicativa(modalidad,k) * semilla) % (modulo(g));
        return valueNewZen;
    }

    /**
     * recuerda definir el tipo de operacion que
     * quiere el usuario en
     * el metodo principal del programa*/
    private static int constanteMultiplicativa(boolean modalidad,int k){
        if (modalidad){
            return 5+(8*k);
        }else{
            return 3+(8*k);
        }
    }

    private static int modulo(int g){
        return (int)Math.pow(2,g);
    }

}
