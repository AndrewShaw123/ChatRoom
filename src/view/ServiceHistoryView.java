package view;

import javax.swing.*;
@SuppressWarnings("all")
public class ServiceHistoryView {

    public static JFrame serviceHistoryFram=null;
    public static JTextPane historyArea=null;
    public static JScrollPane historyAreaScrollPane=null;

    public static void serviceHistoryView() {
        initserviceHistoryView();
    }

    private static void initserviceHistoryView(){

        serviceHistoryFram=new JFrame("��־��¼");



        historyArea=new JTextPane();
        historyArea.setEditable(false);

        historyAreaScrollPane=new JScrollPane(historyArea);
        historyAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        serviceHistoryFram.add(historyAreaScrollPane);


        serviceHistoryFram.setSize(500,332);
        serviceHistoryFram.setResizable(false);
        serviceHistoryFram.setLocationRelativeTo(ServiceView.serviceFrm);//λ����������Ļ�м�
        serviceHistoryFram.setVisible(true);


    }


}
