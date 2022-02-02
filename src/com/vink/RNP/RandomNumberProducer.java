package com.vink.RNP;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class RandomNumberProducer {

    private static int flag = 0;
    private static int amount;
    private static double sn;
    private static double en;
    private static final StringBuilder sb = new StringBuilder();
    private static int perAmount;
    private static int perAmount2;

    public static void main(String[] args) throws IllegalEnterException {
        dataGetter();
        if (amount <= 100) {
            numberProducer();
        } else {
            threadNumberMaker();
        }

    }

    public static void dataGetter() throws IllegalEnterException {
        JOptionPane.showMessageDialog(null, "This is a program to produce random numbers");


        sn = Double.parseDouble(JOptionPane.showInputDialog("Please enter the START number"));
        en = Double.parseDouble(JOptionPane.showInputDialog("Please enter the END number"));

        if (sn >= en) {
            throw new IllegalEnterException("IllegalEnter(001:Start Number > End Number)");
        } else {
            amount = Integer.parseInt(JOptionPane.showInputDialog("How much numbers do you want?"));
            if (amount < 1) {
                throw new IllegalEnterException("IllegalEnter(002:amount<1)");
            } else {
                String[] options1 = new String[]{"Double", "Int"};
                if (en - sn > 1.0D) {
                    flag = JOptionPane.showOptionDialog(null, "Int or Double?", "Choose", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options1, "Double");
                }
            }
        }


    }

    private static void threadNumberMaker() {
        perAmount = Math.round(amount / 8);
        perAmount2 = amount - (perAmount * 7);
        Runnable r1 = RandomNumberProducer::threadNumberProducer;
        Runnable r2 = RandomNumberProducer::threadNumberProducer2;
        var t1 = new Thread(r1);
        var t2 = new Thread(r1);
        var t3 = new Thread(r1);
        var t4 = new Thread(r1);
        var t5 = new Thread(r1);
        var t6 = new Thread(r1);
        var t7 = new Thread(r1);
        var t8 = new Thread(r2);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();

    }

    private static void numberProducer() {

        System.out.println("result:");
        for (int i = 0; i < amount; ++i) {
            double ram = Math.random();
            double result = (en - sn) * ram + sn;
            if (flag == 0) {
                sb.append(result).append("\n");
                System.out.println(result);
            } else {
                int result2 = (int) Math.round(result);
                sb.append(result2).append("\n");
                System.out.println(result2);
            }
        }
        fileSaver();
    }

    private static void threadNumberProducer() {

        for (int i = 0; i < perAmount; ++i) {
            double ram = Math.random();
            double result = (en - sn) * ram + sn;
            if (flag == 0) {
                sb.append(result).append("\n");
                System.out.println(result);
            } else {
                int result2 = (int) Math.round(result);
                sb.append(result2).append("\n");
                System.out.println(result2);
            }
        }
    }

    private static void threadNumberProducer2() {
        for (int i = 0; i < perAmount2; ++i) {
            double ram = Math.random();
            double result = (en - sn) * ram + sn;
            if (flag == 0) {
                sb.append(result).append("\n");
                System.out.println(result);
            } else {
                int result2 = (int) Math.round(result);
                sb.append(result2).append("\n");
                System.out.println(result2);
            }
        }
        fileSaver();
    }

    private static void fileSaver() {
        String[] options2 = new String[]{"Exit", "Save Results"};
        int flag2 = JOptionPane.showOptionDialog(null, "Exit Or Save", "Choose", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, "Exit");
        if (flag2 == 1) {
            FileSystemView fsv = FileSystemView.getFileSystemView();

            String path = fsv.getDefaultDirectory().getPath() + "/result.txt";
            byte[] bytes = sb.toString().getBytes(StandardCharsets.UTF_8);

            try (var fos = new FileOutputStream(path)) {
                fos.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }

            JOptionPane.showMessageDialog(null, "Save Successfully(" + path + ")");
        }
        System.out.println("\ndone!");
   
    }
}
