package view;

import javax.swing.*;
@SuppressWarnings("all")
public class ClientHistoryView {

    public static JFrame clientHistoryFram=null;
    public static JTextPane historyArea=null;
    public static JScrollPane historyAreaScrollPane=null;

    public static void clientHistoryView() {
        initclientHistoryView();
    }

    private static void initclientHistoryView(){

        clientHistoryFram=new JFrame("消息记录");



        historyArea=new JTextPane();
        historyArea.setEditable(false);

        historyAreaScrollPane=new JScrollPane(historyArea);
        historyAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        clientHistoryFram.add(historyAreaScrollPane);


        clientHistoryFram.setSize(500,332);
        clientHistoryFram.setResizable(false);
        clientHistoryFram.setLocationRelativeTo(ClientView.clientFrm);//位置设置在屏幕中间
        clientHistoryFram.setVisible(true);


    }

}
