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

        serviceHistoryFram=new JFrame("日志记录");



        historyArea=new JTextPane();
        historyArea.setEditable(false);

        historyAreaScrollPane=new JScrollPane(historyArea);
        historyAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        serviceHistoryFram.add(historyAreaScrollPane);


        serviceHistoryFram.setSize(500,332);
        serviceHistoryFram.setResizable(false);
        serviceHistoryFram.setLocationRelativeTo(ServiceView.serviceFrm);//位置设置在屏幕中间
        serviceHistoryFram.setVisible(true);


    }


}
